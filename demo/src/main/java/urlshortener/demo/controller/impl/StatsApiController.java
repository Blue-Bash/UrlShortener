package urlshortener.demo.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import urlshortener.demo.controller.StatsApi;
import urlshortener.demo.domain.Stats;
import urlshortener.demo.domain.URIStats;
import urlshortener.demo.repository.StatsRepository;

import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-21T05:15:43.072Z[GMT]")

@Controller
public class StatsApiController implements StatsApi {

    private static final Logger log = LoggerFactory.getLogger(StatsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final StatsRepository statsRepository;

    @org.springframework.beans.factory.annotation.Autowired
    public StatsApiController(ObjectMapper objectMapper, HttpServletRequest request, StatsRepository statsRepository) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.statsRepository = statsRepository;
    }

    public ResponseEntity<Stats> getStats() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Stats>(statsRepository.getStats(), HttpStatus.OK);
    }

    public ResponseEntity<URIStats> getUriStats(String keyID) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<URIStats>(statsRepository.getUriStats(keyID), HttpStatus.OK);
    }

}
