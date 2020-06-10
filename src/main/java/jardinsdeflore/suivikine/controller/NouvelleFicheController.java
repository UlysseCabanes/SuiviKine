package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Medecin;
import jardinsdeflore.suivikine.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NouvelleFicheController {

    @Autowired
    MedecinRepository medecinRepository;
    
    @GetMapping("/nouvelleFiche")
    public String nouvelleFiche(Model model) {
        //Cr�er une liste de tous les m�decins de la BDD
        Iterable<Medecin> lesMedecins = medecinRepository.findAll();
        //Envoyer la liste � la vue
        model.addAttribute("medecins", lesMedecins);
        return "nouvelleFiche";
    }
}
