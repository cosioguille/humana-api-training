package com.humana.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.humana.model.Plan;
 
@Repository("planRepository")
public interface PlanRepository extends JpaRepository<Plan, Integer> {
   
}