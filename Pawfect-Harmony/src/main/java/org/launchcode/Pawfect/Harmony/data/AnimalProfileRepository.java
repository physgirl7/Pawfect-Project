package org.launchcode.Pawfect.Harmony.data;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalProfileRepository extends CrudRepository<AnimalProfile, Integer> {

}
