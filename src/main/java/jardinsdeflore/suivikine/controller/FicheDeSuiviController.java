package jardinsdeflore.suivikine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FicheDeSuiviController {

	@GetMapping("/ficheDeSuivi")
	public String accueil() {
		return "ficheDeSuivi";
	}

}
