package ru.simatov.labs.lab3.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String text, heading, author;
	private Long author_id;
	private Date date;
	
	public Post() {
		
	}
	
	public Post(String text, String heading, String author, Long author_id) {
		this.text = text;
		this.heading = heading;
		this.author = author;
		this.setAuthor_id(author_id);
	}
	
	public Post(String text, String heading, String author, Long author_id, Date date) {
		this.text = text;
		this.heading = heading;
		this.author = author;
		this.setAuthor_id(author_id);
		this.date = date;
	}
	
	public Post(Long id, String text, String heading, String author, Long author_id, Date date) {
		this.id = id;
		this.text = text;
		this.heading = heading;
		this.author = author;
		this.setAuthor_id(author_id);
		this.date = date;
	}
	
	public Long getId() {
		return this.id;
	}
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}
}
