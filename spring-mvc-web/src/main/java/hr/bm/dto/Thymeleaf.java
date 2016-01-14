package hr.bm.dto;

import java.util.List;

public class Thymeleaf {

  private String emailAddress;

  private Spittr spittr;

  private List<Spittr> list;

  public String getFirstName() {
    return spittr.getFirstName();
  }

  public void setFirstName(String firstName) {
    if (spittr == null) {
      spittr = new Spittr();
    }
    spittr.setFirstName(firstName);
  }

  public String getLastName() {
    return spittr.getLastName();
  }

  public void setLastName(String lastName) {
    if (spittr == null) {
      spittr = new Spittr();
    }
    spittr.setLastName(lastName);
  }

  public String getUsername() {
    return spittr.getUsername();
  }

  public void setUsername(String username) {
    if (spittr == null) {
      spittr = new Spittr();
    }
    spittr.setUsername(username);
  }

  public String getPassword() {
    return spittr.getPassword();
  }

  public void setPassword(String password) {
    if (spittr == null) {
      spittr = new Spittr();
    }
    spittr.setPassword(password);
  }

  public String getPropertie() {
    return "propertieTest";
  }

  public String method(String str) {
    return "thymeleafMethod: " + str;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Spittr getSpittr() {
    return spittr;
  }

  public void setSpittr(Spittr spittr) {
    this.spittr = spittr;
  }

public List<Spittr> getList() {
	return list;
}

public void setList(List<Spittr> list) {
	this.list = list;
}

}
