package com.humana.service;
 
import java.util.List;
import com.humana.model.PlanType;
 
public interface PlanTypeService {
 
   public List<PlanType> getPlanTypes();
 
   public PlanType savePlanType(PlanType planType);
 
   public PlanType getPlanType(int id);
   
   public void updatePlanType(int id, PlanType planType);
 
   public void deletePlanType(int id);
   
   public boolean existsPlanType(int id);
}