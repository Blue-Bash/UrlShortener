package urlshortener.demo.domain;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

/**
 * URICreate
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class URICreate   {
  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("name")
  private String name = "";

  public URICreate uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Get uri
   * @return uri
  **/
  @ApiModelProperty(example = "https://google.es/", required = true, value = "")
  @NotNull
  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  /**
   * Get uri
   * @return uri
   **/
  @ApiModelProperty(example = "https://google.es/", required = true, value = "")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public URICreate name(String name) {
    this.name = name;
    return this;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    URICreate urICreate = (URICreate) o;
    return Objects.equals(this.uri, urICreate.uri) && Objects.equals(this.name, urICreate.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri,name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class URICreate {\n");

    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

