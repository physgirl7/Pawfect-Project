package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters.")
    private String username;

    @NotBlank
    @Size(max= 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    private String lastName;

    @Size(max =2)
    private String location;

    @Email(message = "Must enter a valid email")
    private String email;

    @Size(min=10, max=10, message = "Phone number must have 10 digits. No dashes and no spaces")
    private String phone;

    @NotNull
    private String pwHash;

    private Boolean isAdmin = false;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<AnimalProfile> animalProfile = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<UserMeetPet> meetPets = new ArrayList<>();

    public User(){

    }

    public User(String username, String firstName, String lastName, String location, String email, String phone, String password, Boolean isAdmin) {
        this();
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.email = email;
        this.phone = phone;
        this.pwHash=encoder.encode(password);
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getLocation(){return location;}

    public void setLocation(String location){this.location = location;}

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

    public Boolean getIsAdmin() { return isAdmin; }

    public void setIsAdmin(Boolean isAdmin) { this.isAdmin=isAdmin; }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

}

