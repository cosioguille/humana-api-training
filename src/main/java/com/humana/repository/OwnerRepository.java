package com.humana.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.humana.model.Owner;
 
@Repository("ownerRepository")
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
   
}