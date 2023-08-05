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

        @Email
        private String email;

        @Size(min=10, max=10, message = "Phone number must have 10 digits.")
        private String phone;

        public EditedUser(){

        }

        public EditedUser(String firstName, String lastName, String email, String phone) {
            this();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
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
