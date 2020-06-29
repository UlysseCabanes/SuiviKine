package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListeFichesArchiveesController {

    @Autowired
    ResidentRepository residentRepository;
    
	@GetMapping("/listeFichesArchivees")
	public String listeFichesArchivees(HttpSession session, Model model) {
            //Récupérer l'id de l'équipe kiné connectée
            int idEquipe = (int) session.getAttribute("idEquipe");
            //Envoyer l'id à la vue pour changer l'action du bouton "accueil" : accueil admin si l'id vaut 1, sinon accueil
            model.addAttribute("idEquipe", idEquipe);
            //Si l'id vaut 1
            if (idEquipe == 1) {
                //Créer une liste de tous les résidents dont la fiche est archivée
                Iterable<Resident> lesResidents = residentRepository.findByArchive("Oui");
                //Envoyer la liste à la vue
                model.addAttribute("residentsArchives", lesResidents);
            }
            else {
                //Créer une liste des résidents créés par l'équipe kiné connectée et dont la liste est archivée
                Iterable<Resident> residentsEquipe = residentRepository.findByArchiveAndEquipeKine("Oui", idEquipe);
                //Envoyer la liste à la vue
                model.addAttribute("residentsArchives", residentsEquipe);
            }
            return "listeFichesArchivees";
	}
}