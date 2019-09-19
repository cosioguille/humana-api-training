package com.humana.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
 
@Entity(name = "plan_types")
public class PlanType
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_type_id")
    private int id;
 
    @Column(name = "plan_type_name")
    private String name;
   
    @Column(name = "plan_type_description")
    private String description;
    
    @OneToMany(mappedBy = "planType", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Plan> plans;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    @Override
    public String toString() {
    	return String.format("Id: %s | Name: %s | Description: %s",
    			getId(), getName(), getDescription());
    }

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}
}