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
        //Lors du clic sur le bouton "d�connexion", renvoyer � la page de login
        return "login";
    }

    //Se connecter avec un compte admin ou �quipe kin�
    @GetMapping("/connexion")
    public String connexion(
            @RequestParam("login") String loginParam,
            @RequestParam("mdp") String mdpParam,
            HttpSession session) {

        //Enlever tous les espaces avant et apr�s l'identifiant et le mot de passe
        String login = loginParam.trim();
        String mdp = mdpParam.trim();

        //V�rifier si l'identifiant et le mot de passe saisis contiennent tous les deux au moins un caract�re
        if (!login.isEmpty() && !mdp.isEmpty()) {
            //Trouver l'�quipe kin� correspondant au login saisit
            EquipeKine equipe = equipeKineRepository.findByLogin(login);
            //V�rifier si le mot de passe de cette �quipe kin� correspond au mot de passe saisit
            if (equipe.getMdp().equals(mdp)) {
                //Passer l'id de l'�quipe kin� en attribut de session
                session.setAttribute("idEquipe", equipe.getIdEquipeKine());
                //V�rifier si le login saisit est celui de l'administrateur
                if (equipe.getNom().equals("admin")) {
                    //Rediriger vers l'accueil de l'administrateur
                    return "accueilAdmin";
                }
                //Rediriger vers l'accueil
                return "accueil";
            }
        }
        //En cas d'�chec, rester sur la page de login
        return "login";
    }

    //G�rer les erreurs 
    @ExceptionHandler({NullPointerException.class})
    public String nullPointerError() {
        return "login";
    }
}
