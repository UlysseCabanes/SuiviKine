package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.EquipeKine;
import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import jardinsdeflore.suivikine.service.EquipeKineService;
import java.sql.SQLException;
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
    ResidentRepository residentRepository;
            
    @Autowired
    EquipeKineService equipeKineService;
    
    @Autowired
    EntityManager em;
        
    //Voir toutes les �quipes de la bdd    
    @GetMapping("/equipeKine")
    public String equipeKine(Model model) {

        //R�cup�rer toutes les �quipes
        Iterable<EquipeKine> lesEquipesKine = equipeKineRepository.findAll();
        //Envoyer la liste � la vue
        model.addAttribute("lesEquipesKine", lesEquipesKine);
        //Afficher la vue
        return "equipeKine";
    }

    @Transactional
    @RequestMapping(value = "/modifierEquipeKine", method = RequestMethod.POST)
    public void modifierEquipeKine(
        @RequestParam("idEquipeKine") int idEquipeKine,
        @RequestParam("nom") String nomParam,
        @RequestParam("login") String loginParam,
        @RequestParam("mdp") String mdpParam
    ) {
        
        //Enlever tous les espaces avant et apr�s le nom, l'identifiant et le mot de passe
        String nom = nomParam.trim();
        String login = loginParam.trim();
        String mdp = mdpParam.trim();
        
        //Trouver l'�quipe kin� correspondant � l'id renseign� (Cl� primaire)
        EquipeKine equipe = em.find(EquipeKine.class, idEquipeKine);
        //Modifier le nom, le login et le mot de passe de l'�quipe
        if (!nom.isEmpty()) {
            equipe.setNom(nom);
        }
        if (!login.isEmpty()) {
            equipe.setLogin(login);
        }
        if (!mdp.isEmpty()) {
            equipe.setMdp(mdp);
        }
        
    }

    //Ajouter une �quipe � la BDD
    @GetMapping("/ajouterEquipeKine")
    public String ajouterEquipeKine() {
        
        ///Cr�er une nouvelle �quipe
        EquipeKine equipe = new EquipeKine("nom", "login", "mdp");
        //Enregistrer l'�quipe dans la bdd
        equipeKineRepository.save(equipe);
        //Afficher la vue
        return "redirect:/equipeKine";
    }

    //Retirer une �quipe de la BDD
    @GetMapping("/retirerEquipeKine")
    public String retirerEquipeKine(
        @RequestParam("idEquipeKine") int idEquipe) 
    {
        //Cr�er une liste de tous les r�sidents
        Iterable<Resident> lesResidents = residentRepository.findAll();
        //V�rifier i l'�quipe n'est pas renseign�e dans une fiche de suivi
        for (Resident r : lesResidents) {
            if (r.getEquipeKine() == idEquipe) {
                //Si c'est le cas, renvoyer vers la page sans supprimer l'�quipe
                return "redirect:/equipeKine";
            }
        }
        //Sinon, retirer l'�quipe correspondant � l'id renseign�
        equipeKineRepository.deleteById(idEquipe);
        
        return "redirect:/equipeKine";
    }
        
    @ExceptionHandler({SQLException.class, ConstraintViolationException.class})
    public String databaseError() {
        return "redirect:/equipeKine";
    }
}
