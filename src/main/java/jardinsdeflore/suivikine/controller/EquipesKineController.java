package jardinsdeflore.suivikine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EquipesKineController {

	@GetMapping("/equipesKine")
	public String equipesKine() {
		return "equipesKine";
	}

}
