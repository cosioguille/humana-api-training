package com.humana.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.humana.model.State;
import com.humana.repository.StateRepository;
 
@Service
@Transactional
public class StateServiceImp implements StateService {
 
   @Autowired
   private StateRepository stateRepository;
 
   @Override
   public List<State> getStates() {
      return stateRepository.findAll();
   }
 
   @Override
    public State saveState(State state) {
       return (State) stateRepository.save(state);
    }
   
   @Override
    public State getState(int id) {
       return stateRepository.findById(id).get();
    }
 
    @Override
    public void updateState(int id, State state) {
        state.setId(id);
        stateRepository.save(state);
    }
   
    @Override
    public void deleteState(int id) {
        stateRepository.deleteById(id);
    }
    
    @Override
    public boolean existsState(int id) {
    	return stateRepository.existsById(id);
    }
}