package ru.simatov.labs.lab3.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.simatov.labs.lab3.models.Post;
import ru.simatov.labs.lab3.models.User;
import ru.simatov.labs.lab3.repos.PostBase;
import ru.simatov.labs.lab3.repos.UserBase;



@Controller
public class UsersController {

	@Autowired
	private UserBase userBase = new UserBase();
	@Autowired
	private PostBase postBase = new PostBase();
	
	@GetMapping("/users-editor")
	public String usersEditing (Model model) {
		ArrayList<User> users = userBase.doList();
		model.addAttribute("users", users);
		return "users-editor";
	}
	
	/*@GetMapping("/users-editor/add")
	public String blogAdd(Model model) {
		return "users-editor";
	}*/
	
	@PostMapping("/users-editor/add")
	public String blogPostAdd(@RequestParam String login, @RequestParam String fullName, Model model) {
		if (!login.trim().isEmpty() && !fullName.trim().isEmpty()) {
			User user = new User(login, fullName, 0);
			userBase.save(user);
		}
		return "redirect:/users-editor";
	}
	
	@GetMapping("/users-editor/{id}/remove")
	public String blogRemove(@PathVariable("id") Long id) {
		userBase.remove(id);
		return "redirect:/users-editor";
	}
	
	@GetMapping("/users-editor/{id}")
	public String userPage(@PathVariable("id") Long id, Model model) {
		User user = userBase.findById(id);
		ArrayList<Post> posts = postBase.getUserPosts(id);
		model.addAttribute("posts", posts);
		model.addAttribute("user", user);
		return "user-page";
	}
	
	@GetMapping("/ajax/users")
	public String ajaxUsers() {
		return "ajax";
	}
}
