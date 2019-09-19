package com.humana.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.humana.model.Owner;
import com.humana.repository.OwnerRepository;
 
@Service
@Transactional
public class OwnerServiceImp implements OwnerService {
 
   @Autowired
   private OwnerRepository ownerRepository;
 
   @Override
   public List<Owner> getOwners() {
      return ownerRepository.findAll();
   }
 
   @Override
    public Owner saveOwner(Owner owner) {
       return (Owner) ownerRepository.save(owner);
    }
   
   @Override
    public Owner getOwner(int id) {
       return ownerRepository.findById(id).get();
    }
 
    @Override
    public void updateOwner(int id, Owner owner) {
        owner.setId(id);
        ownerRepository.save(owner);
    }
   
    @Override
    public void deleteOwner(int id) {
        ownerRepository.deleteById(id);
    }
    
    @Override
    public boolean existsOwner(int id) {
    	return ownerRepository.existsById(id);
    }
}