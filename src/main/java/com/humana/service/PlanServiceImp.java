package com.humana.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.humana.model.Plan;
import com.humana.repository.PlanRepository;
 
@Service
@Transactional
public class PlanServiceImp implements PlanService {
 
   @Autowired
   private PlanRepository planRepository;
 
   @Override
   public List<Plan> getPlans() {
      return planRepository.findAll();
   }
 
   @Override
    public Plan savePlan(Plan plan) {
       return (Plan) planRepository.save(plan);
    }
   
   @Override
    public Plan getPlan(int id) {
       return planRepository.findById(id).get();
    }
 
    @Override
    public void updatePlan(int id, Plan plan) {
        plan.setId(id);
        planRepository.save(plan);
    }
   
    @Override
    public void deletePlan(int id) {
        planRepository.deleteById(id);
    }
    
    @Override
    public boolean existsPlan(int id) {
    	return planRepository.existsById(id);
    }
}