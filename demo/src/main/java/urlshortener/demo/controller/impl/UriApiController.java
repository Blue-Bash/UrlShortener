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
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;
import urlshortener.demo.utils.CheckAlive;
import urlshortener.demo.exception.UnknownEntityException;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.ParameterUtils;
import urlshortener.demo.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class UriApiController implements UriApi {

    private static final Logger log = LoggerFactory.getLogger(UriApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final URIRepository uriService;

    @org.springframework.beans.factory.annotation.Autowired
    public UriApiController(ObjectMapper objectMapper, HttpServletRequest request, URIRepository uriService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.uriService = uriService;
    }

    public ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        CheckAlive c = new CheckAlive();

        ParameterUtils.checkParameter(name);
        ParameterUtils.checkParameter(body.getUri());

        URIItem item = (URIItem) new URIItem().id(name).redirection(body.getUri()).hashpass(StringUtils.randomHash());


        try {
            if (c.makeRequest(redirection) == 200) {
                uriService.add(item);

                return new ResponseEntity<URIItem>(item, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<URIItem>(HttpStatus.BAD_REQUEST);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<URIItem>(HttpStatus.BAD_REQUEST);

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

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

}
