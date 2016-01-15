package hr.bm.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Spittr {

  @NotNull
  @Size(min = 5, max = 15, message = "Ime - nedovoljan broj znakova")
  private String firstName;

  @NotNull
  @Size(min = 5, max = 12, message = "Prezime - nedovoljan broj znakova")
  private String lastName;

  @NotNull
  @Size(min = 5, max = 15)
  private String username;

  @NotNull
  private String password;

  public Spittr() {}

  public Spittr(String firstName, String lastName, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return new String("username = " + username +
        ", firstName = " + firstName +
        ", lastName = " + lastName +
        ", password = " + password);
  }

}
