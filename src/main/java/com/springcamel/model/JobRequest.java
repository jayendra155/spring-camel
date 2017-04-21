package com.springcamel.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "new-request", query = "SELECT request FROM JobRequest request ORDER BY id DESC")
public class JobRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	Long id;
	String jobName;
	String intitiatingUsername;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getIntitiatingUsername() {
		return intitiatingUsername;
	}
	public void setIntitiatingUsername(String intitiatingUsername) {
		this.intitiatingUsername = intitiatingUsername;
	}
	
}
