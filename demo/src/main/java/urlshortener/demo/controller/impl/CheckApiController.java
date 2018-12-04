package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import urlshortener.demo.controller.CheckApi;
import urlshortener.demo.repository.URIRepository;
import urlshortener.demo.utils.CheckAlive;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class CheckApiController implements CheckApi {

    private static final Logger log = LoggerFactory.getLogger(CheckApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final URIRepository uriService;

    @org.springframework.beans.factory.annotation.Autowired
    public CheckApiController(ObjectMapper objectMapper, HttpServletRequest request, URIRepository uriService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.uriService = uriService;
    }

    public ResponseEntity<Void> checkURI(@ApiParam(value = "",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        //String uri = uriService.get(id);      //Esta línea deberá descomentarse cuando esté implementada la BD
        String uri = "https://google.com";       //Se recupera la URI asociada a al parámetro "id"

        CheckAlive c = new CheckAlive();

        try {
            HttpStatus httpStatus = HttpStatus.valueOf(c.makeRequest(uri));

            return new ResponseEntity<Void>(httpStatus);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
    }
}
