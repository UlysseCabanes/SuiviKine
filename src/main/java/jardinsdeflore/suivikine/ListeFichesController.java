package jardinsdeflore.suivikine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListeFichesController {

	@GetMapping("/listeFiches")
	public String accueil() {
		return "listeFiches";
	}

}
