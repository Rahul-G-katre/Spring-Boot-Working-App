package crud2.Crud2.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString


public class JwtRequest {

  private String email ;
  private String password;

  public String getEmail() {
    return email;
  }

  // Add setter for email
  public void setEmail(String email) {
    this.email = email;
  }

  public JwtRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }


  // Add getter for password
  public String getPassword() {
    return password;
  }

  // Add setter for password
  public void setPassword(String password) {
    this.password = password;
  }
}
