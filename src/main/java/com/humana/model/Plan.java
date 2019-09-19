package com.humana.model;
 
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
 
@Entity(name = "plans")
public class Plan
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private int id;
   
    @Column(name = "plan_humana_id")
    private String humanaId;
   
    @Column(name = "plan_name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name="plan_type_id", nullable=false)
    private PlanType planType;
    
    @ManyToOne
    @JoinColumn(name="owner_id", nullable=false)
    private Owner owner;
    
    @Column(name = "copayment_amount")
    private double copaymentAmount;
    
    @ManyToMany(fetch = FetchType.EAGER,
    		cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
	  name = "plan_benefit",
	  joinColumns = @JoinColumn(name = "plan_id"), 
	  inverseJoinColumns = @JoinColumn(name = "benefit_id"))
    private Set<Benefit> benefits;
    
    @ManyToMany(fetch = FetchType.EAGER,
    		cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinTable(
	  name = "plan_state", 
	  joinColumns = @JoinColumn(name = "plan_id"),
	  inverseJoinColumns = @JoinColumn(name = "state_id"))
    private Set<State> states;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHumanaId() {
		return humanaId;
	}

	public void setHumanaId(String humanaId) {
		this.humanaId = humanaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public double getCopaymentAmount() {
		return copaymentAmount;
	}

	public void setCopaymentAmount(double copaymentAmount) {
		this.copaymentAmount = copaymentAmount;
	}

	public Set<Benefit> getBenefits() {
		return benefits;
	}

	public void setBenefits(Set<Benefit> benefits) {
		this.benefits = benefits;
	}

	public Set<State> getStates() {
		return states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}
	
    @Override
    public String toString() {
    	
    	String message = String.format("Id: %s | Humana Id: %s | Name: %s | Copayment Amount: U$S %s\nPlan Type: %s\nOwner: %s",
    			getId(), getHumanaId(), getName(), getCopaymentAmount(), getPlanType(), getOwner());
    	
    	if(!getBenefits().isEmpty()) {
    		
    		message += "\nBenefits:";
    		
    		for(Benefit b : getBenefits()) {
    			message += String.format("\n%s", b);
    		}
    		
    	}
    	
    	if(!getStates().isEmpty()) {
    		
    		message += "\nStates:";
    		
    		for(State s : getStates()) {
    			message += String.format("\n%s", s);
    		}
    		
    	}
    	
    	return message;
    }
}