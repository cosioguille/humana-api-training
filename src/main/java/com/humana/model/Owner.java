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
 
@Entity(name = "owners")
public class Owner
{
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int id;
   
    @Column(name = "owner_first_name")
    private String firstName;
   
    @Column(name = "owner_second_name")
    private String secondName;
   
    @Column(name = "owner_first_surname")
    private String firstSurname;
   
    @Column(name = "owner_second_surname")
    private String secondSurname;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Plan> plans;
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getFirstName() {
        return firstName;
    }
 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
 
    public String getSecondName() {
        return secondName;
    }
 
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
 
    public String getFirstSurname() {
        return firstSurname;
    }
 
    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }
 
    public String getSecondSurname() {
        return secondSurname;
    }
 
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

	public Set<Plan> getPlans() {
		return plans;
	}

	public void setPlans(Set<Plan> plans) {
		this.plans = plans;
	}

    @Override
    public String toString() {
    	return String.format("Id: %s | First Name: %s | Second Name: %s | First Surname: %s | Second Surname: %s",
    			getId(), getFirstName(), getSecondName(), getFirstSurname(), getSecondSurname());
    }
}