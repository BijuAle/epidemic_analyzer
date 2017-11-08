/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ea.biju.repo;

import ea.biju.model.Patient;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Biju Ale
 */
@Repository
@Transactional
public interface PatientRepository extends CrudRepository<Patient, Integer> {

    @Query("SELECT count(id) FROM Patient")
    public int getPopulation();
}
