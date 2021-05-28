package ru.simatov.labs.lab3.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.simatov.labs.lab3.models.User;
import ru.simatov.labs.lab3.repos.UserRepository;

@RestController
public class AjaxController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(path = "/ajax/getusers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getList() {
		System.out.println(userRepository.doList().get(0).getFullName());
		return userRepository.doList();
	}
	
	@RequestMapping(path = "/ajax/adduser", produces = MediaType.APPLICATION_JSON_VALUE)
	public void addUser(@RequestParam String login, @RequestParam String fullName) {
		User user = new User(login, fullName);
		userRepository.save(user);
	}
	
	
}