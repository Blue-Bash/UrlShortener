package urlshortener.demo.controller;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlshortener.demo.domain.URICreate;
import urlshortener.demo.domain.URIItem;
import urlshortener.demo.domain.URIUpdate;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-23T14:33:33.583Z[GMT]")

@Api(value = "uri", description = "the uri API")
public interface UriApi {

    @ApiOperation(value = "Assigns the name of a redirection", nickname = "changeURI", notes = "Assigns the name of a redirection", response = URIItem.class, tags={ "F3 -  The app will let the user choose a name for the shorted URI", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "Name has been assigned successfully", response = URIItem.class),
        @ApiResponse(code = 400, message = "Error creating resource") })
    @RequestMapping(value = "/uri/{name}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<URIItem> changeURI(@ApiParam(value = "Optional description in *Markdown*" ,required=true )  @Valid @RequestBody URICreate body,@ApiParam(value = "",required=true) @PathVariable("name") String name);


    @ApiOperation(value = "Creates a new redirection", nickname = "createURI", notes = "Create a new URI redirection ", response = URIItem.class, tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
            @ApiResponse(code = 201, message = "The URI redirection has been successfully created", response = URIItem.class),
            @ApiResponse(code = 400, message = "The URI was not reachable", response = URIItem.class)
    })
    @RequestMapping(value = "/uri",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<URIItem> createURI(@ApiParam(value = "URI" ,required=true )  @Valid @RequestBody URICreate body);


    @ApiOperation(value = "Deletes an existing URI and its content.", nickname = "deleteURI", notes = "Remove a URI redirection ", tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Resource has been removed successfully"),
        @ApiResponse(code = 404, message = "There is no URI with that hash") })
    @RequestMapping(value = "/uri/{id}",
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteURI(@ApiParam(value = "",required=true) @PathVariable("id") String id, @RequestHeader("URIHassPass") String hashpass);


    @ApiOperation(value = "Returns the data of a redirection", nickname = "getURI", notes = "Get a URI redirection ", tags={ "F0 - The app will short, storage and get URI&#39;s", })
    @ApiResponses(value = { 
            @ApiResponse(code = 307, message = "Redirect to the real URI"),
            @ApiResponse(code = 404, message = "The given URI couldn't be found")
    })
    @RequestMapping(value = "/uri/{id}",
        method = RequestMethod.GET)
    ResponseEntity<Void> getURI(@ApiParam(value = "",required=true) @PathVariable("id") String id);

}
