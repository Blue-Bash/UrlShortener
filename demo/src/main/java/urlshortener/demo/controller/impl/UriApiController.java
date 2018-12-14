package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import urlshortener.demo.controller.UriApi;
import urlshortener.demo.domain.QRItem;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.exception.IncorrectHashPassException;
import urlshortener.demo.exception.InvalidRequestParametersException;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.QRRepository;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.CheckAlive;
import urlshortener.demo.utils.ParameterUtils;
import urlshortener.demo.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class UriApiController implements UriApi {

    // Limit to K redirections in one hour
    private static final long MAX_REDIRECTION_TIME = 3600000L;

    // Limit to 100 redirections in X ms.
    private static final long MAX_REDIRECTIONS = 100;

    // Default size of QR code
    private static final int QR_SIZE = 500;

    private static final Logger log = LoggerFactory.getLogger(UriApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final URIRepository uriService;
    
    private final QRRepository qrService;

    @org.springframework.beans.factory.annotation.Autowired
    public UriApiController(ObjectMapper objectMapper, HttpServletRequest request, URIRepository uriService, QRRepository qrService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.uriService = uriService;
        this.qrService = qrService;
    }

    public ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        CheckAlive c = new CheckAlive();

        ParameterUtils.checkParameter(name);
        ParameterUtils.checkParameter(body.getUri());

        URIItem item = (URIItem) new URIItem().id(name).redirection(body.getUri()).hashpass(StringUtils.randomHash());

        try {
            if (Integer.valueOf(c.makeRequest(item.getRedirection())) == 200) {
                uriService.add(item);

                return new ResponseEntity<URIItem>(item, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<URIItem>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            throw new InvalidRequestParametersException(HttpStatus.BAD_REQUEST.value(), "There was a problem with the parameters.");
        }
        uriService.add(item);

        // Save default QR to QRRepository if it doesn't already exist
        if(!qrService.contains(body.getUri())){
            QRItem qrItem = new QRItem();
            qrItem.setUri(body.getUri());
            qrItem.convertBase64(QR_SIZE, QR_SIZE);

            this.qrService.add(qrItem);
        }

        return new ResponseEntity<URIItem>(item, HttpStatus.CREATED);
    }

    public ResponseEntity<URIItem> createURI(@ApiParam(value = "URI" ,required=true )  @Valid @RequestBody URICreate body) {
        String accept = request.getHeader("Accept");
        return changeURI(body, Long.toHexString(uriService.getNextID()));
    }

    public ResponseEntity<Void> deleteURI(@ApiParam(value = "",required=true) @PathVariable("id") String id, @RequestHeader("URIHashPass") String hashpass) {
        String accept = request.getHeader("Accept");
        ParameterUtils.checkParameter(id);
        ParameterUtils.checkParameter(hashpass);

        URIItem uri = uriService.get(id);
        if(uri != null && uri.checkHashPass(hashpass)) {
            uriService.remove(id);
            return ResponseEntity.ok().build();
        }else{
            throw new IncorrectHashPassException(HttpStatus.BAD_REQUEST.value(), "Given hashpass doesn't match " + id + " hashpass.");
        }
    }

    public ResponseEntity<Void> getURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        CheckAlive c = new CheckAlive();
        URI location = null;


        String redirection;
        URIItem item = uriService.get(id);
        if(item == null){
            throw new UnknownEntityException(1, "Unknown URI: " + id);
        }

        redirection = item.getRedirection();

        try {
            if (c.makeRequest(redirection) == 200){
                //OK
                //Para esa URI, se registra la fecha actual como útima fecha en la que estuvo viva
                location = new URI(redirection);
            }
            else {
                //Cualquier otra cosa aparte de un código 200 significará que la URI está muerta
                //Se obtiene la última vez que la URI estuvo viva
                //  -Si la diferencia entre la fecha actual y la fecha recuperada es >= K, entonces la URI se borra
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }

        } catch (URISyntaxException | IOException  e) {
            throw new InvalidRequestParametersException(HttpStatus.BAD_REQUEST.value(), "");
        }

        if(uriService.getRedirectionAmount(id, MAX_REDIRECTION_TIME) > MAX_REDIRECTIONS){
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

}
