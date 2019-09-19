package com.humana.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.humana.model.Benefit;
 
@Repository("benefitRepository")
public interface BenefitRepository extends JpaRepository<Benefit, Integer> {
   
}