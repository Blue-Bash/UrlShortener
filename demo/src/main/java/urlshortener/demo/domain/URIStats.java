package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class URIStats extends Stats {

    @JsonProperty("last-accesses")
    private List<Long> accesses = new ArrayList<>();

    public URIStats(String hash) {
        super.setId(hash);
    }

    public void addAccess(long time){
        this.accesses.add(time);
    }

    public long getAccesssesAfter(long time){
        return accesses.stream().filter(t -> t > time).count();
    }

}
