package org.launchcode.Pawfect.Harmony.data;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.models.User;
import org.launchcode.Pawfect.Harmony.models.UserMeetPet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMeetPetRepository extends CrudRepository<UserMeetPet, Integer> {
    List<UserMeetPet> findAllByUser(User user);
}
