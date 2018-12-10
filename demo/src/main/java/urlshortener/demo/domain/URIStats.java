package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        URIStats stats = (URIStats) o;
        return Objects.equals(this.accesses, stats.accesses) &&
                Objects.equals(super.getId(), stats.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getId(),accesses);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class URIStats {\n");

        sb.append("    acceses: ").append(toIndentedString(accesses)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}
