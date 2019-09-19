package com.humana.service;
 
import java.util.List;
import com.humana.model.Benefit;
 
public interface BenefitService {
 
   public List<Benefit> getBenefits();
 
   public Benefit saveBenefit(Benefit benefit);
 
   public Benefit getBenefit(int id);
   
   public void updateBenefit(int id, Benefit benefit);
 
   public void deleteBenefit(int id);
   
   public boolean existsBenefit(int id);
}