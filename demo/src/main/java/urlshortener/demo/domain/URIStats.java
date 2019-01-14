package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

/**
 * Stats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class URIStats {

    @JsonProperty("uri.accesses")
    private BigDecimal uriAccesses = null;

    public BigDecimal getUriAccesses() {
        return uriAccesses;
    }

    public void setUriAccesses(BigDecimal uriAccesses) {
        this.uriAccesses = uriAccesses;
    }
}
