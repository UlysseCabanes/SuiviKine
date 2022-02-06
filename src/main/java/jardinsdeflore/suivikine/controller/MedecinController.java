package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Medecin;
import jardinsdeflore.suivikine.repository.MedecinRepository;
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
public class MedecinController {

    @Autowired
    MedecinRepository medecinRepository;

    @Autowired
    EntityManager em;

    //Voir tous les médecins de la BDD 
    @GetMapping("/medecin")
    public String medecin(Model model) {

        //Créer une liste de tous les médecins de la BDD triés par ordre alphabétique
        Iterable<Medecin> lesMedecins = medecinRepository.findAllByOrderByNomAsc();
        //Envoyer la liste à la vue
        model.addAttribute("lesMedecins", lesMedecins);

        return "medecin";
    }

    //Modifier un médecin
    @Transactional
    @RequestMapping(value = "/modifierMedecin", method = RequestMethod.POST)
    public void modifierMedecin(
            @RequestParam("idMedecin") int idMedecin,
            @RequestParam("nom") String nomParam
    ) {

        //Enlever tous les espaces avant et après le nom
        String nom = nomParam.trim();
        //Trouver le médecin correspondant à l'id renseigné (Clé primaire)
        Medecin medecin = em.find(Medecin.class, idMedecin);
        if (!nom.isEmpty()) {
            //Modifier le nom du médecin
            medecin.setNom(nom);
        }
    }

    //Ajouter un médecin à la BDD
    @GetMapping("/ajouterMedecin")
    public String ajouterMedecin() {

        ///Créer un nouveau médecin
        Medecin medecin = new Medecin("nom");
        //Enregistrer le médecin dans la bdd
        medecinRepository.save(medecin);

        return "redirect:/medecin";
    }

    //Retirer un médecin de la BDD
    @GetMapping("/retirerMedecin")
    public String retirerMedecin(
            @RequestParam("idMedecin") int idMedecin) {
        //Retirer le médecin correspondant à l'id renseigné
        medecinRepository.deleteById(idMedecin);

        return "redirect:/medecin";
    }
}