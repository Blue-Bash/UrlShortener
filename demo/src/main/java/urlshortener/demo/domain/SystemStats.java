package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class SystemStats extends Stats {

    public static final String SYSTEM_STATS_ID = "~system";

    @JsonProperty("redirected-uris")
    private Integer redirectedUris = null;

    @JsonProperty("server-load")
    private BigDecimal serverLoad = null;

    @JsonProperty("generated-qr")
    private Integer generatedQr = null;

    public SystemStats() {
        setId(SYSTEM_STATS_ID); // Unique ID for System Stats
    }

    public SystemStats redirectedUris(Integer redirectedUris) {
        this.redirectedUris = redirectedUris;
        return this;
    }

    /**
     * Get redirectedUris
     * @return redirectedUris
     **/
    @ApiModelProperty(example = "3457", required = true, value = "")
    @NotNull
    public Integer getRedirectedUris() {
        return redirectedUris;
    }

    public void setRedirectedUris(Integer redirectedUris) {
        this.redirectedUris = redirectedUris;
    }

    public SystemStats serverLoad(BigDecimal serverLoad) {
        this.serverLoad = serverLoad;
        return this;
    }

    /**
     * Get serverLoad
     * @return serverLoad
     **/
    @ApiModelProperty(example = "0.79", required = true, value = "")
    @NotNull
    @Valid
    public BigDecimal getServerLoad() {
        return serverLoad;
    }

    public void setServerLoad(BigDecimal serverLoad) {
        this.serverLoad = serverLoad;
    }

    public SystemStats generatedQr(Integer generatedQr) {
        this.generatedQr = generatedQr;
        return this;
    }

    /**
     * Get generatedQr
     * @return generatedQr
     **/
    @ApiModelProperty(example = "1243", required = true, value = "")
    @NotNull
    public Integer getGeneratedQr() {
        return generatedQr;
    }

    public void setGeneratedQr(Integer generatedQr) {
        this.generatedQr = generatedQr;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SystemStats stats = (SystemStats) o;
        return Objects.equals(this.redirectedUris, stats.redirectedUris) &&
                Objects.equals(this.serverLoad, stats.serverLoad) &&
                Objects.equals(this.generatedQr, stats.generatedQr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(redirectedUris, serverLoad, generatedQr);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Stats {\n");

        sb.append("    redirectedUris: ").append(toIndentedString(redirectedUris)).append("\n");
        sb.append("    serverLoad: ").append(toIndentedString(serverLoad)).append("\n");
        sb.append("    generatedQr: ").append(toIndentedString(generatedQr)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
