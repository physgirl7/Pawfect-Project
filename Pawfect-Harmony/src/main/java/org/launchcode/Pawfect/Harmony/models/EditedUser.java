package org.launchcode.Pawfect.Harmony.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

    public class EditedUser {
        @NotBlank
        @Size(max= 25)
        private String firstName;

        @NotBlank
        @Size(max = 25)
        private String lastName;

        @Size(max=2)
        private String location;

        @Email
        private String email;

        @Size(min=10, max=10, message = "Phone number must have 10 digits. No dashes and no spaces")
        private String phone;

        private Boolean isAdmin = false;

        public EditedUser(){

        }

        public EditedUser(String firstName, String lastName, String location, String email, String phone, Boolean isAdmin) {
            this();
            this.firstName = firstName;
            this.lastName = lastName;
            this.location = location;
            this.email = email;
            this.phone = phone;
            this.isAdmin = isAdmin;
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
    }
