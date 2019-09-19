package com.humana.service;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import com.humana.model.Benefit;
import com.humana.repository.BenefitRepository;
 
@Service
@Transactional
public class BenefitServiceImp implements BenefitService {
 
   @Autowired
   private BenefitRepository benefitRepository;
 
   @Override
   public List<Benefit> getBenefits() {
      return benefitRepository.findAll();
   }
 
   @Override
    public Benefit saveBenefit(Benefit benefit) {
       return (Benefit) benefitRepository.save(benefit);
    }
   
   @Override
    public Benefit getBenefit(int id) {
       return benefitRepository.findById(id).get();
    }
 
    @Override
    public void updateBenefit(int id, Benefit benefit) {
        benefit.setId(id);
        benefitRepository.save(benefit);
    }
   
    @Override
    public void deleteBenefit(int id) {
        benefitRepository.deleteById(id);
    }
    
    @Override
    public boolean existsBenefit(int id) {
    	return benefitRepository.existsById(id);
    }
}