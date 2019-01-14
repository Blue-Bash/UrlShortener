package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
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
import urlshortener.demo.exception.IncorrectHashPassException;
import urlshortener.demo.exception.InvalidRequestParametersException;
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

    //Limit to K redirections in one hour
    private static final long MAX_REDIRECTION_TIME = 3600000L;

    //Limit to 100 redirections in X ms.
    private static final long MAX_REDIRECTIONS = 100;

    private static final Logger log = LoggerFactory.getLogger(UriApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final URIRepository uriService;

    private final MeterRegistry meterRegistry;

    @org.springframework.beans.factory.annotation.Autowired
    public UriApiController(ObjectMapper objectMapper, HttpServletRequest request, URIRepository uriService, MeterRegistry meterRegistry) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.uriService = uriService;
        this.meterRegistry = meterRegistry;
    }

    public ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        ParameterUtils.checkParameter(name);
        ParameterUtils.checkParameter(body.getUri());

        URIItem item = (URIItem) new URIItem().id(name).redirection(body.getUri()).hashpass(StringUtils.randomHash());
        uriService.add(item);
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

        URIItem item = uriService.get(id);
        if(item == null){
            throw new UnknownEntityException(1, "Unknown URI: " + id);
        }

        String redirection = item.getRedirection();
        URI location = null;
        try {
            location = new URI(redirection);
        } catch (URISyntaxException e) {
            throw new InvalidRequestParametersException(HttpStatus.BAD_REQUEST.value(), "");
        }

        if(uriService.getRedirectionAmount(id, MAX_REDIRECTION_TIME) > MAX_REDIRECTIONS){
            return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        meterRegistry.counter("uri." + id + ".accessed").increment();
        meterRegistry.counter("uri.accessed").increment();

        return new ResponseEntity<Void>(responseHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

}
