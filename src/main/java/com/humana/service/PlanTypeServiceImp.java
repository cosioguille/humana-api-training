package com.humana.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.humana.model.PlanType;
import com.humana.repository.PlanTypeRepository;
 
@Service
@Transactional
public class PlanTypeServiceImp implements PlanTypeService {
 
   @Autowired
   private PlanTypeRepository planTypeRepository;
 
   @Override
   public List<PlanType> getPlanTypes() {
      return planTypeRepository.findAll();
   }
 
   @Override
    public PlanType savePlanType(PlanType planType) {
       return (PlanType) planTypeRepository.save(planType);
    }
   
   @Override
    public PlanType getPlanType(int id) {
       return planTypeRepository.findById(id).get();
    }
 
    @Override
    public void updatePlanType(int id, PlanType planType) {
        planType.setId(id);
        planTypeRepository.save(planType);
    }
   
    @Override
    public void deletePlanType(int id) {
        planTypeRepository.deleteById(id);
    }
    
    @Override
    public boolean existsPlanType(int id) {
    	return planTypeRepository.existsById(id);
    }
}