package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.EquipeKine;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EquipeKineController {

    @Autowired
    EquipeKineRepository equipeKineRepository;
    
    @Autowired
    EntityManager em;
            
	@GetMapping("/equipeKine")
	public String equipeKine(Model model) {
            Iterable<EquipeKine> lesEquipesKine = equipeKineRepository.findAll();
            model.addAttribute("lesEquipesKine", lesEquipesKine);
		return "equipeKine";
	}
        
        @Transactional
        @RequestMapping(value = "/modifierEquipeKine", method = RequestMethod.POST)
	public void modifierEquipeKine(
            @RequestParam("idEquipeKine") int idEquipeKine,
            @RequestParam("login") String login,
            @RequestParam("mdp") String mdp
        ) {
            //Trouver l'équipe kiné correspondante à l'id renseigné (Clé primaire)
            EquipeKine equipe = em.find(EquipeKine.class, idEquipeKine);
            //Modifier le login et le mot de passe de l'équipe
            equipe.setLogin(login);
            equipe.setMdp(mdp);
	}
}
