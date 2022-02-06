package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.EquipeKine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    EquipeKineRepository equipeKineRepository;

    @GetMapping("/login")
    public String deconnexion() {
        //Lors du clic sur le bouton "déconnexion", renvoyer à la page de login
        return "login";
    }

    //Se connecter avec un compte admin ou équipe kiné
    @GetMapping("/connexion")
    public String connexion(
            @RequestParam("login") String loginParam,
            @RequestParam("mdp") String mdpParam,
            HttpSession session) {

        //Enlever tous les espaces avant et après l'identifiant et le mot de passe
        String login = loginParam.trim();
        String mdp = mdpParam.trim();

        //Vérifier si l'identifiant et le mot de passe saisis contiennent tous les deux au moins un caractère
        if (!login.isEmpty() && !mdp.isEmpty()) {
            //Trouver l'équipe kiné correspondant au login saisit
            EquipeKine equipe = equipeKineRepository.findByLogin(login);
            //Vérifier si le mot de passe de cette équipe kiné correspond au mot de passe saisit
            if (equipe.getMdp().equals(mdp)) {
                //Passer l'id de l'équipe kiné en attribut de session
                session.setAttribute("idEquipe", equipe.getIdEquipeKine());
                //Vérifier si le login saisit est celui de l'administrateur
                if (equipe.getNom().equals("admin")) {
                    //Rediriger vers l'accueil de l'administrateur
                    return "accueilAdmin";
                }
                //Rediriger vers l'accueil
                return "accueil";
            }
        }
        //En cas d'échec, rester sur la page de login
        return "login";
    }

    //Gérer les erreurs 
    @ExceptionHandler({NullPointerException.class})
    public String nullPointerError() {
        return "login";
    }
}
