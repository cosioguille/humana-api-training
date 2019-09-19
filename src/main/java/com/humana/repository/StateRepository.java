package com.humana.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.humana.model.State;
 
@Repository("stateRepository")
public interface StateRepository extends JpaRepository<State, Integer> {
   
}