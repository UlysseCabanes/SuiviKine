package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Equipe_kine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jardinsdeflore.suivikine.repository.Equipe_kineRepository;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    Equipe_kineRepository equipeKineRepository;

    @GetMapping("/login")
    public String deconnexion() {
        return "login";
    }

    @GetMapping("/connexion")
    public String connexion(
    @RequestParam("login") String login, 
    @RequestParam("mdp") String mdp) {

        Equipe_kine equipe;
        if(!login.isEmpty() && !mdp.isEmpty()) {
            equipe = equipeKineRepository.findByLogin(login);
        }
        else{
            return "login";
        }
        if(equipe.getMdp().equals(mdp)) {
            if(equipe.getLogin().equals("admin")) {
                return "accueilAdmin";
            }
            return "accueil";
        }
        return "login";
    }
}