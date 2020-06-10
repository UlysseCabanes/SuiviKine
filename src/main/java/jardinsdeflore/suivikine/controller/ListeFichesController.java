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
            //Récupérer l'id de l'équipe kiné connectée
            int idEquipe = (int) session.getAttribute("idEquipe");
            //Envoyer l'id à la vue pour changer l'action du bouton "accueil" : accueil admin si l'id vaut 0, sinon accueil
            model.addAttribute("idEquipeKine", idEquipe);
            //Si l'id vaut 0
            if (idEquipe == 0) {
                //Créer une liste de tous les résidents
                Iterable<Resident> lesResidents = residentRepository.findAll();
                //Envoyer la liste à la vue
                model.addAttribute("residentsEquipe", lesResidents);
            }
            else {
                //Créer une liste des résidents créés par l'équipe kiné connectée
                Iterable<Resident> residentsEquipe = residentRepository.findByEquipeKine(idEquipe);
                //Envoyer la liste à la vue
                model.addAttribute("residentsEquipe", residentsEquipe);
            }
            return "listeFiches";
	}

}
