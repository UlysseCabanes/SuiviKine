package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListeFichesController {

    @Autowired
    ResidentRepository residentRepository;
    
	@GetMapping("/listeFiches")
	public String listeFiches(HttpSession session, Model model) {
                int idEquipe = (int) session.getAttribute("idEquipe");
                Iterable<Resident> residentsEquipe = residentRepository.findByEquipeKine(idEquipe);
		model.addAttribute("residentsEquipe", residentsEquipe);
                return "listeFiches";
	}

}
