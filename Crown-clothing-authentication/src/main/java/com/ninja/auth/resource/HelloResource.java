package com.ninja.auth.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy/")
public class HelloResource {
	@GetMapping
	public String hello() {
		return "Hello World";
	}
}
