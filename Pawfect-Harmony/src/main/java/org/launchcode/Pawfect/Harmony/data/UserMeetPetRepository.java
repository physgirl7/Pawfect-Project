package org.launchcode.Pawfect.Harmony.data;

import org.launchcode.Pawfect.Harmony.models.UserMeetPet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMeetPetRepository extends CrudRepository<UserMeetPet, Integer> {
}
