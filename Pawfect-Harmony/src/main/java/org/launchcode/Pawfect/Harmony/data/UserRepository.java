package org.launchcode.Pawfect.Harmony.data;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
