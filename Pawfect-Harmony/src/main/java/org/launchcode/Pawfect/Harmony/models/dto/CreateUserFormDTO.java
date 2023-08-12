package org.launchcode.Pawfect.Harmony.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateUserFormDTO extends LoginFormDTO{
// public User(String username, String firstName, String lastName, String email, String phone, String password) {
    @NotBlank
    @Size(max= 25)
    private String firstName;
    @NotBlank
    @Size(max = 25)
    private String lastName;
    @Email(message = "Must enter a valid email")
    private String email;
    @Size(min=10, max=10, message = "Phone number must have 10 digits. No dashes and no spaces.")
    private String phone;
        private String verifyPassword;

        public String getVerifyPassword() {
            return verifyPassword;
        }

        public void setVerifyPassword(String verifyPassword) {
            this.verifyPassword = verifyPassword;
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
}