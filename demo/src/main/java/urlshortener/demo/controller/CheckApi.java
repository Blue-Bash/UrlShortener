package urlshortener.demo.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

@Api(value = "check", description = "the check API")
public interface CheckApi {

    @ApiOperation(value = "Checks the state of the original URI", nickname = "checkURI", notes = "Checks the state of the original URI", tags={ "F5", })
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "The original URI is alive"),
            @ApiResponse(code = 404, message = "The original URI is dead or the shortened URI doesn't exist")
    })
    @RequestMapping(value = "/check/{id}",
        method = RequestMethod.GET)
    ResponseEntity<Void> checkURI(@ApiParam(value = "",required=true) @PathVariable("id") String id);

}
