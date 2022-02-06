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

    //Voir tous les m�decins de la BDD 
    @GetMapping("/medecin")
    public String medecin(Model model) {

        //Cr�er une liste de tous les m�decins de la BDD tri�s par ordre alphab�tique
        Iterable<Medecin> lesMedecins = medecinRepository.findAllByOrderByNomAsc();
        //Envoyer la liste � la vue
        model.addAttribute("lesMedecins", lesMedecins);

        return "medecin";
    }

    //Modifier un m�decin
    @Transactional
    @RequestMapping(value = "/modifierMedecin", method = RequestMethod.POST)
    public void modifierMedecin(
            @RequestParam("idMedecin") int idMedecin,
            @RequestParam("nom") String nomParam
    ) {

        //Enlever tous les espaces avant et apr�s le nom
        String nom = nomParam.trim();
        //Trouver le m�decin correspondant � l'id renseign� (Cl� primaire)
        Medecin medecin = em.find(Medecin.class, idMedecin);
        if (!nom.isEmpty()) {
            //Modifier le nom du m�decin
            medecin.setNom(nom);
        }
    }

    //Ajouter un m�decin � la BDD
    @GetMapping("/ajouterMedecin")
    public String ajouterMedecin() {

        ///Cr�er un nouveau m�decin
        Medecin medecin = new Medecin("nom");
        //Enregistrer le m�decin dans la bdd
        medecinRepository.save(medecin);

        return "redirect:/medecin";
    }

    //Retirer un m�decin de la BDD
    @GetMapping("/retirerMedecin")
    public String retirerMedecin(
            @RequestParam("idMedecin") int idMedecin) {
        //Retirer le m�decin correspondant � l'id renseign�
        medecinRepository.deleteById(idMedecin);

        return "redirect:/medecin";
    }
}