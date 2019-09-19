package com.humana.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
@Entity(name = "states")
public class State
{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private int id;
 
    @Column(name = "state_name")
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="states")
    @JsonIgnore
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

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}
	
    @Override
    public String toString() {
    	return String.format("Id: %s | Name: %s",
    			getId(), getName());
    }
}