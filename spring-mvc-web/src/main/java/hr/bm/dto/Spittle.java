package hr.bm.dto;

import javax.validation.constraints.NotNull;

public class Spittle {

  @NotNull
  private Long id;

  @NotNull
  private String message;

  public Spittle() {

  }

  public Spittle(Long id, String message) {
    this.id = id;
    this.message = message;
  }

  public Long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Spittle other = (Spittle) obj;
    if (!id.equals(other.id)) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id.hashCode();
    return result;
  }

  @Override
  public String toString() {
    String ret = "";
    ret += "spittle = " + message;
    return ret;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
