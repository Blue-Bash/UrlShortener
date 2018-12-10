package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import urlshortener.demo.controller.StatsApi;
import urlshortener.demo.domain.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import urlshortener.demo.domain.SystemStats;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.StatsRepository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@RestController
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private StatsRepository service;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request, StatsRepository service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
        service.add(new SystemStats());
    }

    public ResponseEntity<SystemStats> getStats() {
        String accept = request.getHeader("Accept");
        SystemStats stats = service.getSystemStats();
        return new ResponseEntity<SystemStats>(stats, HttpStatus.OK);
    }

    public ResponseEntity<URIStats> getStats(String hash) {
        String accept = request.getHeader("Accept");
        URIStats stats = service.getURIStats(hash);
        return new ResponseEntity<URIStats>(stats, HttpStatus.OK);
    }


}
