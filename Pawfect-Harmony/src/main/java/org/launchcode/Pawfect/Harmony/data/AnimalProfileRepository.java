package org.launchcode.Pawfect.Harmony.data;

import org.launchcode.Pawfect.Harmony.models.AnimalProfile;
import org.launchcode.Pawfect.Harmony.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalProfileRepository extends CrudRepository<AnimalProfile, Integer> {
    List<AnimalProfile> findAllByUser(User user);
    List<AnimalProfile> findByLocationLikeAndSpeciesLikeAndGenderLikeAndBreedLikeIgnoreCase(String location, String species, String gender, String breed);

//    List<AnimalProfile> findAll();
//
//    @Query("SELECT a FROM AnimalProfile a WHERE a.name LIKE %:query%")
//    List<AnimalProfile> searchProfiles(@Param("query") String query);
//
//    List<AnimalProfile> findByName(String name);
}