package com.humana.service;
 
import java.util.List;
import com.humana.model.Owner;
 
public interface OwnerService {
 
   public List<Owner> getOwners();
 
   public Owner saveOwner(Owner owner);
 
   public Owner getOwner(int id);
   
   public void updateOwner(int id, Owner owner);
 
   public void deleteOwner(int id);
   
   public boolean existsOwner(int id);
}