package ru.simatov.labs.lab3.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String login, fullName;	
	private int countposts;
	
	public User() {}
	
	public User(String login, String fullName) {
		this.login = login;
		this.fullName = fullName;
	}
	
	public User(String login, String fullName, int countpost) {
		this.login = login;
		this.fullName = fullName;
		this.countposts = countpost;
	}
	
	public User(Long id, String login, String fullName, int countpost) {
		this.id = id;
		this.login = login;
		this.fullName = fullName;
		this.countposts = countpost;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public int getCountposts() {
		return countposts;
	}
	
	public void setCountposts(int countposts) {
		this.countposts = countposts;
	}
}
