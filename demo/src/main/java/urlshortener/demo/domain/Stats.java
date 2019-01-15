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

public class Stats   {
  @JsonProperty("cpu.usage")
  private BigDecimal cpuUsage = BigDecimal.ZERO;

  @JsonProperty("system.cpu.usage")
  private BigDecimal serverUsage = BigDecimal.ZERO;

  @JsonProperty("system.memory.usage")
  private Integer memoryUsage = 0;

  @JsonProperty("uris.created")
  private BigDecimal urisCreated = BigDecimal.ZERO;

  @JsonProperty("uris.accessed")
  private BigDecimal urisAccessed = BigDecimal.ZERO;

  @JsonProperty("uris.removed")
  private BigDecimal urisRemoved = BigDecimal.ZERO;

  @JsonProperty("uris.now")
  private BigDecimal urisNow = BigDecimal.ZERO;

  @JsonProperty("qr.created")
  private BigDecimal qrCreated = BigDecimal.ZERO;

  @JsonProperty("qr.removed")
  private BigDecimal qrRemoved = BigDecimal.ZERO;

  @JsonProperty("qr.now")
  private BigDecimal qrNow = BigDecimal.ZERO;

  @JsonProperty("qr.accessed")
  private BigDecimal qrAccessed = BigDecimal.ZERO;


  public void setCpuUsage(BigDecimal cpuUsage) {
    this.cpuUsage = cpuUsage;
  }

  public void setServerUsage(BigDecimal serverUsage) {
    this.serverUsage = serverUsage;
  }

  public void setMemoryUsage(Integer memoryUsage) {
    this.memoryUsage = memoryUsage;
  }

  public void setUrisCreated(BigDecimal urisCreated) {
    this.urisCreated = urisCreated;
  }

  public void setUrisRemoved(BigDecimal urisRemoved) {
    this.urisRemoved = urisRemoved;
  }

  public void setUrisNow(BigDecimal urisNow) {
    this.urisNow = urisNow;
  }

  public void setQrCreated(BigDecimal qrCreated) {
    this.qrCreated = qrCreated;
  }

  public void setQrRemoved(BigDecimal qrRemoved) {
    this.qrRemoved = qrRemoved;
  }

  public void setQrNow(BigDecimal qrNow) {
    this.qrNow = qrNow;
  }

  public BigDecimal getQrAccessed() {
    return qrAccessed;
  }

  public void setQrAccessed(BigDecimal qrAccessed) {
    this.qrAccessed = qrAccessed;
  }

  public BigDecimal getUrisAccessed() {
    return urisAccessed;
  }

  public void setUrisAccessed(BigDecimal urisAccessed) {
    this.urisAccessed = urisAccessed;
  }

  public BigDecimal getCpuUsage() {
    return cpuUsage;
  }

  public BigDecimal getServerUsage() {
    return serverUsage;
  }

  public Integer getMemoryUsage() {
    return memoryUsage;
  }

  public BigDecimal getUrisCreated() {
    return urisCreated;
  }

  public BigDecimal getUrisRemoved() {
    return urisRemoved;
  }

  public BigDecimal getUrisNow() {
    return urisNow;
  }

  public BigDecimal getQrCreated() {
    return qrCreated;
  }

  public BigDecimal getQrRemoved() {
    return qrRemoved;
  }

  public BigDecimal getQrNow() {
    return qrNow;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Stats stats = (Stats) o;
    return Objects.equals(this.cpuUsage, stats.cpuUsage) &&
        Objects.equals(this.serverUsage, stats.serverUsage) &&
        Objects.equals(this.urisCreated, stats.urisCreated) &&
        Objects.equals(this.urisRemoved, stats.urisRemoved) &&
        Objects.equals(this.urisNow, stats.urisNow) &&
        Objects.equals(this.qrCreated, stats.qrCreated) &&
        Objects.equals(this.qrRemoved, stats.qrRemoved) &&
        Objects.equals(this.qrNow, stats.qrNow) &&
        Objects.equals(this.memoryUsage, stats.memoryUsage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cpuUsage, serverUsage, memoryUsage, urisCreated, urisNow, urisRemoved, qrCreated, qrRemoved, qrNow);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Stats {\n");
    
    sb.append("    cpuUsage: ").append(toIndentedString(cpuUsage)).append("\n");
    sb.append("    serverUsage: ").append(toIndentedString(serverUsage)).append("\n");
    sb.append("    memoryUsage: ").append(toIndentedString(memoryUsage)).append("\n");
    sb.append("    urisCreated: ").append(toIndentedString(urisCreated)).append("\n");
    sb.append("    urisRemoved: ").append(toIndentedString(urisRemoved)).append("\n");
    sb.append("    urisNow: ").append(toIndentedString(urisNow)).append("\n");
    sb.append("    qrCreated: ").append(toIndentedString(qrCreated)).append("\n");
    sb.append("    qrRemoved: ").append(toIndentedString(qrRemoved)).append("\n");
    sb.append("    qrNow: ").append(toIndentedString(qrNow)).append("\n");
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

