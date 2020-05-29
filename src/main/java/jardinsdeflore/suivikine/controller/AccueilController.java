package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccueilController {

    @Autowired
    ResidentRepository residentRepository;
    
    @GetMapping("/accueil")
    public String accueil() {
            return "accueil";
    }

    @GetMapping("/connexion")
    public String connexion(
            @RequestParam("login") String login, 
            @RequestParam("mdp") String mdp) {


            return "accueil";
    }
}
