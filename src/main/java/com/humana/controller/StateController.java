package com.humana.controller;
 
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.humana.model.State;
import com.humana.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {
 
   @Autowired
   private StateService stateService;
 
   @RequestMapping(value = "", method = RequestMethod.GET)
   public ResponseEntity<?> getStates()
   {  
      List<State> states = stateService.getStates();
      
      if(states.isEmpty()) {
    	  
    	  return new ResponseEntity<>("There is no states in the database yet!", HttpStatus.NOT_FOUND);
    	  
      } else {
    	  
          return new ResponseEntity<>(states, HttpStatus.OK);
      }
   }
   
   @RequestMapping(value = "", method = RequestMethod.POST)
   public ResponseEntity<String> saveState(@RequestBody State state)
   {
	   
	   state.setId(0);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(state.getName() == null) {
		   
		   error += "\nState's name cannot be empty!";
		   
	   } else if (state.getName().length() > 255) {
		   
		   error += "\nState's name cannot be longer than 255 characters!";
		   
	   } else {
		   
		   if(!error.equals("")) {
			   
			   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
		   
			   try {
			   
				   State saved = stateService.saveState(state);
				   message = String.format("New state added correctly!\n%s", saved);
				   status = HttpStatus.OK;
				   
			   } catch (Exception ex) {
				   
				   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
				   status = HttpStatus.INTERNAL_SERVER_ERROR;
				   
			   }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public ResponseEntity<?> getState (@PathVariable int id)
   {
	   try {
		   
		   State state = stateService.getState(id);
		   return new ResponseEntity<>(state, HttpStatus.OK);
		   
	   } catch (NoSuchElementException ex) {
		   
		   String message = String.format("[BAD REQUEST] State with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
       
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> updateState(@PathVariable int id, @RequestBody State state)
   {
	   state.setId(id);
	   
	   String error = "";
	   String message = "";
	   HttpStatus status = HttpStatus.OK;
	   
	   if(state.getName() == null) {
		   
		   error += "\nState's name cannot be empty!";
		   
	   } else if (state.getName().length() > 255) {
		   
		   error += "\nState's name cannot be longer than 255 characters!";
		   
	   } else {
		   
		   if(!error.equals("")) {
			   
			   message = String.format("[BAD REQUEST] The request is not correct because the following errors:%s", error);
			   status = HttpStatus.BAD_REQUEST;
			   
		   } else {
			   
			   if(!stateService.existsState(id)) {
				   
				   message = String.format("[BAD REQUEST] State with the Id: %s do not exist in the database!", id);
				   status = HttpStatus.BAD_REQUEST;
				   
			   } else {
		   
				  try {
					  
					  stateService.updateState(id, state);
					  message = String.format("State with the Id: %s successfully updated!\n%s", id, state);
				      status = HttpStatus.OK;
					   
				  } catch (Exception ex){
					  
					   message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
					   status = HttpStatus.INTERNAL_SERVER_ERROR;
					   
				  }
			   }
		   }
	   }
	   
	   return new ResponseEntity<>(message, status);
	   
   }
   
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> deleteState (@PathVariable int id)
   {
	   try {
		   
		   stateService.deleteState(id);
		   
		   try {
			   
			   stateService.getState(id);
			   String message = String.format("[BAD REQUEST] State with the Id: %s cannot be erased because is related to a plan!", id);
			   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
			   
		   } catch (Exception ex) {
			   
			   String message = String.format("State with the Id: %s successfully erased!", id);
			   return new ResponseEntity<>(message, HttpStatus.OK);
			   
		   }
		   
	   } catch (EmptyResultDataAccessException ex) {
		   
		   String message = String.format("[BAD REQUEST] State with the Id: %s do not exist in the database!", id);
		   return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		   
	   } catch (Exception ex){
		   
		   String message = String.format("[INTERNAL SERVER ERROR] \n%s", ex);
		   return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
		   
	   }
   }
}