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
 
@Entity(name = "benefits")
public class Benefit
{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "benefit_id")
    private int id;
 
    @Column(name = "benefit_name")
    private String name;
   
    @Column(name = "benefit_description")
    private String description;
   
    @Column(name = "benefit_comments")
    private String comments;
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="benefits")
    @JsonIgnore
    private Set<Plan> plans;
   
    public int getId()
    {
        return this.id;
    }
   
    public String getName()
    {
        return this.name;
    }
   
    public String getDescription()
    {
        return this.description;
    }
   
    public String getComments()
    {
        return this.comments;
    }
   
    public void setId(int id)
    {
        this.id = id;
    }
   
    public void setName(String name)
    {
        this.name = name;
    }
   
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}
    
    @Override
    public String toString() {
    	return String.format("Id: %s | Name: %s | Description: %s | Comments: %s",
    			getId(), getName(), getDescription(), getComments());
    }
}