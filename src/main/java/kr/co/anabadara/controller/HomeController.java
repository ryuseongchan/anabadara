package kr.co.anabadara.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/loginSuccess")
	public String index() {
		return "index";
	}
	
}
