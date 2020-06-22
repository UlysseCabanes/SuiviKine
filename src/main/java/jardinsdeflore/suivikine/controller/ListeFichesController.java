package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
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
    
    @Autowired
    EquipeKineRepository equipeKineRepository;
    
	@GetMapping("/listeFiches")
	public String listeFiches(HttpSession session, Model model) {
            //R�cup�rer l'id de l'�quipe kin� connect�e
            int idEquipe = (int) session.getAttribute("idEquipe");
            //Envoyer l'id � la vue pour changer l'action du bouton "accueil" : accueil admin si l'id vaut 1, sinon accueil
            model.addAttribute("idEquipe", idEquipe);
            //Si l'id vaut 1
            if (idEquipe == 1) {
                //Cr�er une liste de tous les r�sidents
                Iterable<Resident> lesResidents = residentRepository.findAll();
                //Envoyer la liste � la vue
                model.addAttribute("residentsEquipe", lesResidents);
            }
            else {
                //Cr�er une liste des r�sidents cr��s par l'�quipe kin� connect�e
                Iterable<Resident> residentsEquipe = residentRepository.findByEquipeKine(idEquipe);
                //Envoyer la liste � la vue
                model.addAttribute("residentsEquipe", residentsEquipe);
            }
            return "listeFiches";
	}
}