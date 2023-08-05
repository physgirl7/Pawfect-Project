package org.launchcode.Pawfect.Harmony.models;

import javax.persistence.*;


@Entity
public class UserMeetPet extends AbstractEntity{

    @ManyToOne
    private User user;

    @OneToOne
    private AnimalProfile animalProfile;

    public UserMeetPet(){
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AnimalProfile getAnimalProfile() {
        return animalProfile;
    }

    public void setAnimalProfile(AnimalProfile animalProfile) {
        this.animalProfile = animalProfile;
    }
}
