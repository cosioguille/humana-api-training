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
 
import com.humana.model.PlanType;
import com.humana.service.PlanTypeService;

@RestController
@RequestMapping("/plan-types")
public class PlanTypeController {
	
   @Autowired
   private PlanTypeService planTypeService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getPlanTypes()
   {  
      List<PlanType> planTypes = planTypeService.getPlanTypes();
      
      if(planTypes.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no plan types in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(planTypes, HttpStatus.OK);
          
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> savePlanType(@RequestBody PlanType planType)
   {
	   planType.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(planType.getName() == null) {
		   
		   error += "\nPlan Type's name cannot be empty!";
		   
	   } else if (planType.getName().length() > 255) {
		   
		   error += "\nPlan Type's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (planType.getDescription() == null){
		   
		   error += "\nPlan Type's description cannot be empty!";
		   
	   } else if (planType.getDescription().length() > 255) {
		   
		   error += "\nPlan Type's description cannot be longer than 255 characters!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
		   
	   } else {
	   
		   try {
			   
			   PlanType saved = planTypeService.savePlanType(planType);
			   message = String.format("New plan type added correctly!\n%s", saved);
			   status = HttpStatus.OK;
			   
		   } catch (Exception ex) {
			   
			   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
			   
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getPlanType (@PathVariable int id)
   {
	   try {
		   
		   PlanType planType = planTypeService.getPlanType(id);
		   return new ResponseEntity<>(planType, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan Type with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updatePlanType(@PathVariable int id, @RequestBody PlanType planType)
   {
	   planType.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(planType.getName() == null) {
		   
		   error += "\nPlan Type's name cannot be empty!";
		   
	   } else if (planType.getName().length() > 255) {
		   
		   error += "\nPlan Type's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (planType.getDescription() == null){
		   
		   error += "\nPlan Type's description cannot be empty!";
		   
	   } else if (planType.getDescription().length() > 255) {
		   
		   error += "\nPlan Type's description cannot be longer than 255 characters!";
		   
	   }
		   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
		   
	   } else {
		   
		   if(!planTypeService.existsPlanType(id)) {
			   
			   message = String.format("[BAD REQUEST] Plan Type with the Id: %s do not exist in the database!", id);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
	   
			  try {
				  
				  planTypeService.updatePlanType(id, planType);
				  message = String.format("Plan Type with the Id: %s successfully updated!\n%s", id, planType);
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
   public ResponseEntity<?> deletePlanType (@PathVariable int id)
   {
	   try {
		   
		   planTypeService.deletePlanType(id);
		   String message = String.format("Plan Type with the Id: %s successfully erased!", id);
		   return new ResponseEntity<>(message, HttpStatus.OK);
		   
	   } catch (DataIntegrityViolationException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan Type with the Id: %s cannot be erased because is related to a plan!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan Type with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}