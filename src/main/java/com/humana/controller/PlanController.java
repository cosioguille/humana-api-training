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
import org.springframework.web.client.RestTemplate;

import com.humana.model.Plan;
import com.humana.pojo.Translation;
import com.humana.service.PlanService;

@RestController
@RequestMapping("/plans")
public class PlanController {
 
   @Autowired
   private PlanService planService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getPlans()
   {  
      List<Plan> plans = planService.getPlans();
      
      if(plans.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no plans in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(plans, HttpStatus.OK);
          
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> savePlan(@RequestBody Plan plan)
   {
	   
	   plan.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(plan.getHumanaId() == null) {
		   
		   error += "\nPlan's humana id cannot be empty!";
		   
	   } else if (plan.getHumanaId().length() > 255) {
		   
		   error += "\nPlan's humana id cannot be longer than 255 characters!";
		   
	   }
	   
	   if (plan.getName() == null){
		   
		   error += "\nPlan's name cannot be empty!";

	   } else if (plan.getName().length() > 255) {
		   
		   error += "\nPlan's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (plan.getPlanType() == null){
		   
		   error += "\nPlan's plan type id cannot be empty!";
		   
	   }
	   
	   if (plan.getOwner() == null){
		   
		   error += "\nPlan's owner id cannot be empty!";
		   
	   }
	   
	   if (plan.getCopaymentAmount() < 0){
		   
		   error += "\nPlan's copayment amount dont have a valid value!";
		   
	   }
		   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
		   
	   } else {
	   
		   try {
			   
			   Plan saved = planService.savePlan(plan);
			   message = String.format("New plan added correctly!\n%s", saved);
			   status = HttpStatus.OK;
			   
		   } catch (DataIntegrityViolationException ex) {
			   
			   message = String.format("[BAD REQUEST] There is some problem with the nested objects!");
			   status = HttpStatus.BAD_REQUEST;
			   
		   } catch (Exception ex){
			   
			   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
			   status = HttpStatus.INTERNAL_SERVER_ERROR;
			   
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getPlan (@PathVariable int id)
   {
	   try {
		   
		   Plan plan = planService.getPlan(id);
		   return new ResponseEntity<>(plan, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updatePlan(@PathVariable int id, @RequestBody Plan plan)
   {
	   plan.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(plan.getHumanaId() == null) {
		   
		   error += "\nPlan's humana id cannot be empty!";
		   
	   } else if (plan.getHumanaId().length() > 255) {
		   
		   error += "\nPlan's humana id cannot be longer than 255 characters!";
		   
	   }
	   
	   if (plan.getName() == null){
		   
		   error += "\nPlan's name cannot be empty!";

	   } else if (plan.getName().length() > 255) {
		   
		   error += "\nPlan's name cannot be longer than 255 characters!";
		   
	   }
	   
	   if (plan.getPlanType() == null){
		   
		   error += "\nPlan's plan type id cannot be empty!";
		   
	   }
	   
	   if (plan.getOwner() == null){
		   
		   error += "\nPlan's owner id cannot be empty!";
		   
	   }
	   
	   if (plan.getCopaymentAmount() < 0){
		   
		   error += "\nPlan's copayment amount dont have a valid value!";
		   
	   }
	   
	   if(!error.equals("")) {
		   
		   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
		   status = HttpStatus.BAD_REQUEST;
	   
	   } else {
		   
		   if(!planService.existsPlan(id)) {
			   
		   message = String.format("[BAD REQUEST] Plan with the Id: %s do not exist in the database!", id);
		   status = HttpStatus.BAD_REQUEST;
		   
		   } else {
			   
			  try {
					  
				 planService.updatePlan(id, plan);
				 message = String.format("Plan with the Id: %s successfully updated!\n%s", id, plan);
				 status = HttpStatus.OK;
				   
			  } catch (DataIntegrityViolationException ex) {
				   
				 message = String.format("[BAD REQUEST] There is some problem with the nested objects!");
				 status = HttpStatus.BAD_REQUEST;
				   
			  } catch (Exception ex){
				  
				 message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				 status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			  }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deletePlan (@PathVariable int id)
   {
	   try {
		   
		   planService.deletePlan(id);
		   String message = String.format("Plan with the Id: %s successfully erased!", id);
		   return new ResponseEntity<>(message, HttpStatus.OK);
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
   
   @RequestMapping(value = "/translate/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> translatePlan (@PathVariable int id)
   {
	   try {
		   
		   Plan plan = planService.getPlan(id);
		   
		   String text = plan.toString();
		   String key = "key=trnsl.1.1.20190911T153252Z.71983c0081d03f1a.5d95e46b8616c2c526e2b5339b1b2af0feee1b83";
		   String lang = "en-es";
		   
		   String uri = String.format("https://translate.yandex.net/api/v1.5/tr.json/translate?%s&text=%s&lang=%s", key, text, lang);
		   
		   RestTemplate restTemplate = new RestTemplate();
		   String result = restTemplate.getForObject(uri, Translation.class).getText().get(0);
		   
		   return new ResponseEntity<>(result, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] Plan with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}