package com.humana.controller;
 
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.humana.model.Owner;
import com.humana.service.OwnerService;

@RestController
@RequestMapping("/owners")
public class OwnerController {
 
   @Autowired
   private OwnerService ownerService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getOwners()
   {  
      List<Owner> owners = ownerService.getOwners();
      
      if(owners.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no owners in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(owners, HttpStatus.OK);
          
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> saveOwner(@RequestBody Owner owner)
   {
	   owner.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(owner.getFirstName() == null) {
		   
		   error += "\nOwner's first name cannot be empty!";
		   
	   } else if (owner.getFirstName().length() > 255) {
		   
		   error += "\nOwner's first name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (owner.getSecondName() != null && owner.getSecondName().length() > 255) {
		   
		   error += "\nOwner's second name cannot be longer than 255 characters!";
		   
	   }
		   
	   if (owner.getFirstSurname() == null){
		   
		   error += "\nOwner's first surname cannot be empty!";
		   
	   } else if (owner.getFirstSurname().length() > 255) {
		   
		   error += "\nOwner's first surname cannot be longer than 255 characters!";
		   
	   }
	   
	   if (owner.getSecondSurname() == null){
		   
		   error += "\nOwner's second surname cannot be empty!";
	
	   } else if (owner.getSecondSurname().length() > 255) {
		   
		   error += "\nOwner's second surname cannot be longer than 255 characters!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
	   
	   } else {
		   
		   try {
			   
			   Owner saved = ownerService.saveOwner(owner);
			   message = String.format("New owner added correctly!\n%s", saved);
			   status = HttpStatus.OK;
			   
		   } catch (Exception ex) {
			   
			   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
			   
		   }
		   
	   }
	   
	   return new ResponseEntity<>(message, status);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getOwner (@PathVariable int id)
   {
	   try {
		   
		   Owner owner = ownerService.getOwner(id);
		   return new ResponseEntity<>(owner, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] Owner with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updateOwner(@PathVariable int id, @RequestBody Owner owner)
   {
	   owner.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(owner.getFirstName() == null) {
		   
		   error += "\nOwner's first name cannot be empty!";
		   
	   } else if (owner.getFirstName().length() > 255) {
		   
		   error += "\nOwner's first name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (owner.getSecondName() != null && owner.getSecondName().length() > 255) {
		   
		   error += "\nOwner's second name cannot be longer than 255 characters!";
		   
	   }
		   
	   if (owner.getFirstSurname() == null){
		   
		   error += "\nOwner's first surname cannot be empty!";
		   
	   } else if (owner.getFirstSurname().length() > 255) {
		   
		   error += "\nOwner's first surname cannot be longer than 255 characters!";
		   
	   }
	   
	   if (owner.getSecondSurname() == null){
		   
		   error += "\nOwner's second surname cannot be empty!";
	
	   } else if (owner.getSecondSurname().length() > 255) {
		   
		   error += "\nOwner's second surname cannot be longer than 255 characters!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
	   
	   } else {
		   
		   if(!ownerService.existsOwner(id)) {
			   
			   message = String.format("[BAD REQUEST] Owner with the Id: %s do not exist in the database!", id);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
		   
			  try {
				  
				  ownerService.updateOwner(id, owner);
				  message = String.format("Owner with the Id: %s successfully updated!\n%s", id, owner);
			      status = HttpStatus.OK;
				   
			  } catch (Exception ex){
				  
				   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				   status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			  }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteOwner (@PathVariable int id)
   {
	   try {
		   
		   ownerService.deleteOwner(id);
		   String message = String.format("Owner with the Id: %s successfully erased!", id);
		   return new ResponseEntity<>(message, HttpStatus.OK);
		   
	   } catch (DataIntegrityViolationException ex) {
		   
		   String message = String.format("[BAD REQUEST] Owner with the Id: %s cannot be erased because is related to a plan!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] Owner with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}