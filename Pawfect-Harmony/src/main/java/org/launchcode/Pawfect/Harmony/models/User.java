package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotBlank
    @Size(min=5, max=15)
    private String username;

    @NotBlank
    @Size(max= 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @Email
    private String email;

    @Size(min=10, max=10, message = "Phone number must have 10 digits.")
    private String phone;

    @NotNull
    private String pwHash;


    public User(){

    }

    public User(String username, String firstName, String lastName, String email, String phone, String password) {
        this();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}

