package jardinsdeflore.suivikine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilAdminController {

	@GetMapping("/accueilAdmin")
	public String accueilAdmin() {
		return "accueilAdmin";
	}

}