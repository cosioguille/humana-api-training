package com.humana.service;
 
import java.util.List;
import com.humana.model.Plan;
 
public interface PlanService {
 
   public List<Plan> getPlans();
 
   public Plan savePlan(Plan plan);
 
   public Plan getPlan(int id);
   
   public void updatePlan(int id, Plan plan);
 
   public void deletePlan(int id);
   
   public boolean existsPlan(int id);
}