package co.edu.unal.se1back.repository;

import co.edu.unal.se1back.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by riperezro on 24/11/19.
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}