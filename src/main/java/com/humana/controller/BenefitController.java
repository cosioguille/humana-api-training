package com.humana.controller;
 
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.humana.model.Benefit;
import com.humana.service.BenefitService;
 
@RestController
@RequestMapping("/benefits")
public class BenefitController {
 
   @Autowired
   private BenefitService benefitService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getBenefits()
   {  
      List<Benefit> benefits = benefitService.getBenefits();
      
      if(benefits.isEmpty()){
    	  
    	  return new ResponseEntity<>("There is no benefits in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(benefits, HttpStatus.OK);
          
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> saveBenefit(@RequestBody Benefit benefit)
   {
	   benefit.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(benefit.getName() == null){
		   
		   error += "\nBenefit's name cannot be empty!";
		   
	   } else if (benefit.getName().length() > 255) {
		   
		   error += "\nBenefit's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (benefit.getDescription() == null) {	
		   
		   error += "\nBenefit's description cannot be empty!";
		   
	   } else if (benefit.getDescription().length() > 255) {
		   
		   error += "\nBenefit's description cannot be longer than 255 characters!";
		   
	   }
	   
	   if (benefit.getDescription() != null && benefit.getComments().length() > 255) {
		   
		   error += "\nBenefit's comments cannot be longer than 255 characters!";
		   
	   }
		   
	   if(!error.equals("")) {
			   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
		   
	   } else {
		
		   try {
			   
			   Benefit saved = benefitService.saveBenefit(benefit);
			   message = String.format("New benefit added correctly!\n%s", saved);
			   status = HttpStatus.OK;
			   
		   } catch (Exception ex) {
			   
			   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
			   
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getBenefit (@PathVariable int id)
   {
	   try {
		   
		   Benefit benefit = benefitService.getBenefit(id);
		   return new ResponseEntity<>(benefit, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		  String message = String.format("[BAD REQUEST] Benefit with the Id: %s do not exist in the database!", id);
		  return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		  
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updateBenefit(@PathVariable int id, @RequestBody Benefit benefit)
   {
	   benefit.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(benefit.getName() == null){
		   
		   error += "\nBenefit's name cannot be empty!";
		   
	   } else if (benefit.getName().length() > 255) {
		   
		   error += "\nBenefit's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (benefit.getDescription() == null) {	
		   
		   error += "\nBenefit's description cannot be empty!";
		   
	   } else if (benefit.getDescription().length() > 255) {
		   
		   error += "\nBenefit's description cannot be longer than 255 characters!";
		   
	   }
	   
	   if (benefit.getDescription() != null && benefit.getComments().length() > 255) {
		   
		   error += "\nBenefit's comments cannot be longer than 255 characters!";
		   
	   }
		   
	   if(!error.equals("")) {
			   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
		   
	   } else {
		   
		   if(!benefitService.existsBenefit(id)) {
			   
			   message = String.format("[BAD REQUEST] Benefit with the Id: %s do not exist in the database!", id);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {

			  try {
				  
				  benefitService.updateBenefit(id, benefit);
				  message = String.format("Benefit with the Id: %s successfully updated!\n%s", id, benefit);
			      status = HttpStatus.OK;
				  
			  } catch (Exception ex){
				   
				  message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				  status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			  }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
   }
}