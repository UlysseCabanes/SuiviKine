package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.EquipeKine;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import jardinsdeflore.suivikine.service.EquipeKineService;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EquipeKineController {

    @Autowired
    EquipeKineRepository equipeKineRepository;
    
    @Autowired
    EquipeKineService equipeKineService;
    
    @Autowired
    EntityManager em;
        
    //Voir toutes les équipes de la bdd    
    @GetMapping("/equipeKine")
    public String equipeKine(Model model) {

        //Récupérer toutes les équipes
        Iterable<EquipeKine> lesEquipesKine = equipeKineRepository.findAll();
        //Envoyer la liste à la vue
        model.addAttribute("lesEquipesKine", lesEquipesKine);
        //Afficher la vue
        return "equipeKine";
    }

    @Transactional
    @RequestMapping(value = "/modifierEquipeKine", method = RequestMethod.POST)
    public void modifierEquipeKine(
        @RequestParam("idEquipeKine") int idEquipeKine,
        @RequestParam("login") String loginParam,
        @RequestParam("mdp") String mdpParam
    ) {
        
        //Enlever tous les espaces avant et après l'identifiant et le mot de passe
        String login = loginParam.trim();
        String mdp = mdpParam.trim();
        
        //Trouver l'équipe kiné correspondante à l'id renseigné (Clé primaire)
        EquipeKine equipe = em.find(EquipeKine.class, idEquipeKine);
        //Modifier le login et le mot de passe de l'équipe
        equipe.setLogin(login);
        equipe.setMdp(mdp);
    }

    //Ajouter une équipe à la bdd
    @GetMapping("/ajouterEquipeKine")
    public String ajouterEquipeKine() {
        
        //Récupérer la dernière équipe kiné
        EquipeKine derniere = equipeKineService.findLast(equipeKineRepository);
        //Récupérer l'id de la dernière équipe
        int id = derniere.getIdEquipeKine();
        ///Créer une nouvelle équipe en incrémentant l'id
        EquipeKine equipe = new EquipeKine(id+1,"login", "mdp");
        //Enregistrer l'équipe dans la bdd
        equipeKineRepository.save(equipe);
        //Afficher la vue
        return "redirect:/equipeKine";
    }

    //Retirer la dernière équipe de la bdd
    @GetMapping("/retirerEquipeKine")
    public String retirerEquipeKine() {

        //Récupérer la dernière équipe kiné
        EquipeKine derniere = equipeKineService.findLast(equipeKineRepository);
        //Retirer l'équipe de la bdd
        equipeKineRepository.delete(derniere);

        return "redirect:/equipeKine";
    }
        
    @ExceptionHandler({SQLException.class, ConstraintViolationException.class})
    public String databaseError() {
        return "redirect:/equipeKine";
    }
}
