package jardinsdeflore.suivikine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class AccueilController {

    @GetMapping("/accueil")
    public String accueil() {
        return "accueil";
    }  
}
