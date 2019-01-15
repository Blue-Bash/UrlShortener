package urlshortener.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Objects;

// Libraries for QR Generation

/**
 * QRItem
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-23T14:33:33.583Z[GMT]")
public class QRItem implements BaseEntity<String>{
  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("qr")
  private String qr = null;

  public QRItem uri(String uri) {
    this.uri = uri;
    return this;
  }

  public QRItem(){}

  @Override
  public String getId() {
    return uri;
  }

  /**
   * Get uri
   * @return uri
  **/
  @ApiModelProperty(example = "d290f1ee6c", required = true, value = "")
  @NotNull


  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public QRItem qr(String qr) {
    this.qr = qr;
    return this;
  }

  public void convertBase64(int width, int height){

    ByteArrayOutputStream oStream = new ByteArrayOutputStream();
    try {
      BitMatrix bitmatrix = new MultiFormatWriter().encode(this.uri, BarcodeFormat.QR_CODE, width, height);
      MatrixToImageWriter.writeToStream(bitmatrix, MediaType.IMAGE_PNG.getSubtype(), oStream, new MatrixToImageConfig());
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.qr = Base64.getEncoder().encodeToString(oStream.toByteArray());

  }

  /**
   * Get qr
   * @return qr
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getQr() {
    return qr;
  }

  public void setQr(String qr) {
    this.qr = qr;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QRItem qrItem = (QRItem) o;
    return Objects.equals(this.uri, qrItem.uri) &&
        Objects.equals(this.qr, qrItem.qr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uri, qr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QRItem {\n");
    
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    qr: ").append(toIndentedString(qr)).append("\n");
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

