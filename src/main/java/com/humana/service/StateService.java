package com.humana.service;
 
import java.util.List;
import com.humana.model.State;
 
public interface StateService {
 
   public List<State> getStates();
 
   public State saveState(State state);
 
   public State getState(int id);
   
   public void updateState(int id, State state);
 
   public void deleteState(int id);
   
   public boolean existsState(int id);
}