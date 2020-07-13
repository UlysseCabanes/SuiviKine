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
        //Créer une liste de tous les médecins de la BDD triés par ordre alphabétique
        Iterable<Medecin> lesMedecins = medecinRepository.findAllByOrderByNomAsc();
        //Envoyer la liste à la vue
        model.addAttribute("medecins", lesMedecins);
        return "nouvelleFiche";
    }
}
