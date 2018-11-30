package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * URIItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class URIItem   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("redirection")
  private String redirection = null;

  @JsonProperty("hashpass")
  private String hashpass = null;

  public URIItem id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "d290f1ee6c", required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public URIItem redirection(String redirection) {
    this.redirection = redirection;
    return this;
  }

  /**
   * Get redirection
   * @return redirection
  **/
  @ApiModelProperty(example = "https://google.es/", required = true, value = "")
  @NotNull


  public String getRedirection() {
    return redirection;
  }

  public void setRedirection(String redirection) {
    this.redirection = redirection;
  }

  public URIItem hashpass(String hashpass) {
    this.hashpass = hashpass;
    return this;
  }

  /**
   * Get hashpass
   * @return hashpass
  **/
  @ApiModelProperty(example = "adsgv674fjhag", required = true, value = "")
  @NotNull


  public String getHashpass() {
    return hashpass;
  }

  public void setHashpass(String hashpass) {
    this.hashpass = hashpass;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    URIItem urIItem = (URIItem) o;
    return Objects.equals(this.id, urIItem.id) &&
        Objects.equals(this.redirection, urIItem.redirection) &&
        Objects.equals(this.hashpass, urIItem.hashpass);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, redirection, hashpass);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URIItem {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    redirection: ").append(toIndentedString(redirection)).append("\n");
    sb.append("    hashpass: ").append(toIndentedString(hashpass)).append("\n");
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

