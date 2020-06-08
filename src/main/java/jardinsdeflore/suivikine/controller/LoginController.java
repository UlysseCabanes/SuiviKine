package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.EquipeKine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    EquipeKineRepository equipeKineRepository;

    @GetMapping("/login")
    public String deconnexion() {
        return "login";
    }

    @GetMapping("/connexion")
    public String connexion(
    @RequestParam("login") String login, 
    @RequestParam("mdp") String mdp,
    HttpSession session,
    Model model) {

        EquipeKine equipe;
        if(!login.isEmpty() && !mdp.isEmpty()) {
            equipe = equipeKineRepository.findByLogin(login);
        }
        else{
            return "login";
        }
        if(equipe.getMdp().equals(mdp)) {
            if(equipe.getLogin().equals("admin")) {
                session.setAttribute("idEquipe", equipe.getIdEquipeKine());
                return "accueilAdmin";
            }
            session.setAttribute("idEquipe", equipe.getIdEquipeKine());
            return "accueil";
        }
        return "login";
    }
}