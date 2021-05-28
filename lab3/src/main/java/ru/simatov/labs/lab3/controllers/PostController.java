package ru.simatov.labs.lab3.controllers;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
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
public class PostController {

	private PostBase postBase = new PostBase();
	private UserBase userBase = new UserBase();
	
	@GetMapping("/posts-editor")
	public String postsEditing (Model model) {
		ArrayList<Post> posts = postBase.getList();
		model.addAttribute("posts", posts);
		return "posts-editor";
	}
	
	@GetMapping("/posts-editor/{id}")
	public String postPage(@PathVariable("id") Long id, Model model) {
		Post post = postBase.findById(id);
		User user = userBase.findById(post.getAuthor_id());
		model.addAttribute("user", user);
		model.addAttribute("post", post);
		return "post-page";
	}
	
	@PostMapping("/users-editor/{id}/addpost")
	public String blogPostAdd(@RequestParam String text, @RequestParam String heading, @PathVariable("id") Long id, Model model) {
		if (!text.trim().isEmpty() && !heading.trim().isEmpty()) {
			User user = userBase.findById(id);
			model.addAttribute("user", user);
			Post post = new Post(text, heading, user.getLogin(), user.getId());
			postBase.addPost(post);
		}
		return "redirect:/posts-editor";
	}
	
	@GetMapping("/posts-editor/{id}/remove")
	public String postRemove(@PathVariable("id") Long id) {
		postBase.deletePost(id);
		return "redirect:/posts-editor";
	}
	
	@PostMapping("/posts-editor/{id}/change")
	public String changeText(@RequestParam String text, @PathVariable("id") Long id, Model model) {
		if (!text.trim().isEmpty()) {
			postBase.changeText(id, text);
		}
		return "redirect:/posts-editor";
	}
	
	
	@GetMapping("/posts-editor/getListByDate")
	public String postPage(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, Model model) {
		ArrayList<Post> posts = postBase.getPostsToDate(date);
		model.addAttribute("posts", posts);
		model.addAttribute("date", date);
		return "posts-by-date";
	}
	
	
	
	
	/*@PostMapping("/posts-editor/{id}/change")
	public String blogPostAdd(@RequestParam String text, Model model) {
		if (!text.trim().isEmpty()) {
			Post
			User user = new User(login, fullName, 0);
			userBase.save(user);
		}
		return "redirect:/users-editor";
	}*/
}
