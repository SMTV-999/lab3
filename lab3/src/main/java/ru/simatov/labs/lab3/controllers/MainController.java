package ru.simatov.labs.lab3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String mainPage() {	
	return "main-page";
	}
	
}
