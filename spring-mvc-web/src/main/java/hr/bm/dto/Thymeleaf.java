package hr.bm.dto;

import java.util.List;

public class Thymeleaf {

  private String emailAddress;

  private User user;

  private List<User> list;

  public String getFirstName() {
    return user.getFirstName();
  }

  public void setFirstName(String firstName) {
    if (user == null) {
    	user = new User();
    }
    user.setFirstName(firstName);
  }

  public String getLastName() {
    return user.getLastName();
  }

  public void setLastName(String lastName) {
    if (user == null) {
    	user = new User();
    }
    user.setLastName(lastName);
  }

  public String getUsername() {
    return user.getUsername();
  }

  public void setUsername(String username) {
    if (user == null) {
    	user = new User();
    }
    user.setUsername(username);
  }

  public String getPassword() {
    return user.getPassword();
  }

  public void setPassword(String password) {
    if (user == null) {
    	user = new User();
    }
    user.setPassword(password);
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

  public User getSpittr() {
    return user;
  }

  public void setSpittr(User spittr) {
    this.user = spittr;
  }

public List<User> getList() {
	return list;
}

public void setList(List<User> list) {
	this.list = list;
}

}
