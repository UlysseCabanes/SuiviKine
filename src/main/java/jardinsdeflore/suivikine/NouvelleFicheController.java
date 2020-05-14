package jardinsdeflore.suivikine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NouvelleFicheController {

	@GetMapping("/nouvelleFiche")
	public String accueil() {
		return "nouvelleFiche";
	}

}
