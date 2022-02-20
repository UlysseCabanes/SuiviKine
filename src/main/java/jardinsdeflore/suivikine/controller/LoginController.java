package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.EquipeKine;
import jardinsdeflore.suivikine.entity.Resident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("login")
public class LoginController {

    @Autowired
    ResidentRepository residentRepository;
    
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

        /*
        Ajout d'une fiche de suivi pour les tests
        */
        Resident res = new Resident("MOREL", "Laura", "24/03/1923", "F", "223033450012765", "DURANT Paul", 1);
        if(!residentRepository.existsById(new ResidentId(res.getNom(), res.getPrenom(), res.getDateNaissance()))) {
            residentRepository.save(res);
        }
        
        
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