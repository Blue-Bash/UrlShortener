package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class URIStats extends Stats {

    @JsonProperty("last-accesses")
    private Long lastAccesses  = null;

    public URIStats(String hash) {
        setId(hash);
    }


    public URIStats lastAccesses(Long lastAccesses) {
        this.lastAccesses = lastAccesses;
        return this;
    }
    
    @ApiModelProperty(example = "858", required = true, value = "")
    @NotNull
    public Long getLastAccesses() {
        return lastAccesses;
    }

    public void setLastAccesses(Long lastAccesses) {
        this.lastAccesses = lastAccesses;
    }

}
