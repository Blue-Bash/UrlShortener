package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Stats
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class URIStats {

    @JsonProperty("uri.accesses")
    private BigDecimal uriAccesses = BigDecimal.ZERO;

    public BigDecimal getUriAccesses() {
        return uriAccesses;
    }

    public void setUriAccesses(BigDecimal uriAccesses) {
        this.uriAccesses = uriAccesses;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        URIStats stats = (URIStats) o;
        return Objects.equals(this.uriAccesses, stats.uriAccesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uriAccesses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stats {\n");

        sb.append("    cpuUsage: ").append(toIndentedString(uriAccesses)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
