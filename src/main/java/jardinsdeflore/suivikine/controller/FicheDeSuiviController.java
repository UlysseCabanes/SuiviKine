package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import jardinsdeflore.suivikine.service.ResidentService;
import jardinsdeflore.suivikine.util.UtilDate;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FicheDeSuiviController {

    @Autowired
    ResidentService residentService;
    
    @Autowired
    ResidentRepository residentRepository;
    
    @Autowired
    EntityManager em;
    
    @GetMapping("/creerFicheDeSuivi")
    public String creerFicheDeSuivi(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        @RequestParam("sexe") String sexe,
        @RequestParam("medecinPrescripteur") String medecinPrescripteur,
        @RequestParam("nSecu") String nSecu,
        HttpSession session,
        Model model) throws ParseException {

        String dateNaissance = UtilDate.getDateFormatddMMyyyy(dateNaissanceParam);
        //Récupérer l'id de l'équipe kiné connectée et le mettre en attribut de session
        int idEquipe = (int) session.getAttribute("idEquipe");
        //Créer un résident avec les paramètres de la requête
        Resident resident = new Resident(nom, prenom, dateNaissance, sexe, nSecu, medecinPrescripteur, idEquipe);
        //Vérifier si le résident existe déja
        if(residentRepository.existsById(new ResidentId(nom, prenom, dateNaissance))) {
            //Si il existe, on renvoit à la même page et on ne sauvegarde pas le résident dans la BDD
            return "nouvelleFiche";
        } 
        else {
            //Sinon, on renvoit à la page "ficheDeSuivi" et on sauvegarde le résident dans la BDD
            residentRepository.save(resident);
            return "redirect:/voirFicheDeSuivi?nom="+nom+"&prenom="+prenom+"&dateNaissance="+dateNaissance;
        }
    }
    
    @GetMapping("/voirFicheDeSuivi")
    public String voirFicheDeSuivi(
        @RequestParam("nom") String nomParam,
        @RequestParam("prenom") String prenomParam,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        Model model) throws ParseException {
        
        //Trouver le résident correspondant aux nom, prénom et date de naissance renseignés (Clé primaire)
        Resident resident = em.find(Resident.class, new ResidentId(nomParam, prenomParam, dateNaissanceParam));
        //Envoyer tous les attributs du résident à la vue
        model.addAttribute("nom", resident.getNom());
        model.addAttribute("prenom", resident.getPrenom());
        model.addAttribute("dateNaissance", resident.getDateNaissance());
        //Calculer l'âge à partir de la date de naissance
        int age = UtilDate.getAge(dateNaissanceParam);
        model.addAttribute("age", age);
        model.addAttribute("sexe", resident.getSexe());
        model.addAttribute("nSecuSociale", resident.getnSecuSociale());
        model.addAttribute("medecin", resident.getMedecinPrescripteur());
        model.addAttribute("equipeKine", resident.getEquipeKine());
        
        String datePrescription = resident.getDatePrescription();
        if (datePrescription != null && !datePrescription.isEmpty()) {
            model.addAttribute("datePrescription", UtilDate.getDateFormatyyyyMMdd(datePrescription));
        }
        model.addAttribute("prescriptionQuantitative", resident.getPrescriptionQuantitative());
        model.addAttribute("renouvellement", resident.getRenouvellement());
        model.addAttribute("indicationMedicale", resident.getIndicationMedicale());
        model.addAttribute("nbProtocoleTherapeutique", resident.getNbProtocoleTherapeutique());
        model.addAttribute("rythmeSeances", resident.getRythmeSeances());
        model.addAttribute("lieuSeances", resident.getLieuSeances());
        model.addAttribute("travailGroupe", resident.getTravailGroupe());
        model.addAttribute("datePremiereSeance", resident.getDatePremiereSeance());
        model.addAttribute("techniques", resident.getTechniques());
        model.addAttribute("intitules", resident.getIntitules());
        model.addAttribute("demarrageDate", resident.getDemarrageDate());
        model.addAttribute("intermediaireDate", resident.getIntermediaireDate());
        model.addAttribute("finaleDate", resident.getFinaleDate());
        model.addAttribute("articulairesD", resident.getArticulairesD());
        model.addAttribute("articulairesI", resident.getArticulairesI());
        model.addAttribute("articulairesF", resident.getArticulairesF());
        model.addAttribute("forceMusculaireD", resident.getForceMusculaireD());
        model.addAttribute("forceMusculaireI", resident.getForceMusculaireI());
        model.addAttribute("forceMusculaireF", resident.getForceMusculaireF());
        model.addAttribute("douleursD", resident.getDouleursD());
        model.addAttribute("douleursI", resident.getDouleursI());
        model.addAttribute("douleursF", resident.getDouleursF());
        model.addAttribute("trophiquesD", resident.getTrophiquesD());
        model.addAttribute("trophiquesI", resident.getTrophiquesI());
        model.addAttribute("trophiquesF", resident.getTrophiquesF());
        model.addAttribute("bilanDeficitsFonctionnelsD", resident.getBilanDeficitsFonctionnelsD());
        model.addAttribute("bilanDeficitsFonctionnelsI", resident.getBilanDeficitsFonctionnelsI());
        model.addAttribute("bilanDeficitsFonctionnelsF", resident.getBilanDeficitsFonctionnelsF());
        model.addAttribute("autresProblemesD", resident.getAutresProblemesD());
        model.addAttribute("autresProblemesI", resident.getAutresProblemesI());
        model.addAttribute("autresProblemesF", resident.getAutresProblemesF());
        model.addAttribute("objectifsCourtTermeD", resident.getObjectifsCourtTermeD());
        model.addAttribute("objectifsCourtTermeI", resident.getObjectifsCourtTermeI()); 
        model.addAttribute("objectifsCourtTermeF", resident.getObjectifsCourtTermeF());
        model.addAttribute("objectifsMoyenTermeD", resident.getObjectifsMoyenTermeD());
        model.addAttribute("objectifsMoyenTermeI", resident.getObjectifsMoyenTermeI());
        model.addAttribute("objectifsMoyenTermeF", resident.getObjectifsMoyenTermeF());
        model.addAttribute("objectifsLongTermeD", resident.getObjectifsLongTermeD());
        model.addAttribute("objectifsLongTermeI", resident.getObjectifsLongTermeI());
        model.addAttribute("objectifsLongTermeF", resident.getObjectifsLongTermeF());
        model.addAttribute("diagnosticD", resident.getDiagnosticD());
        model.addAttribute("diagnosticI", resident.getDiagnosticI());
        model.addAttribute("diagnosticF", resident.getDiagnosticF());
        model.addAttribute("conseilsD", resident.getConseilsD());
        model.addAttribute("conseilsI", resident.getConseilsI());
        model.addAttribute("conseilsF", resident.getConseilsF());
        model.addAttribute("propositionsD", resident.getPropositionsD());
        model.addAttribute("propositionsI", resident.getPropositionsI());
        model.addAttribute("propositionsF", resident.getPropositionsF());
        model.addAttribute("commentairesD", resident.getCommentairesD());
        model.addAttribute("commentairesI", resident.getCommentairesI());
        model.addAttribute("cotation", resident.getCotation());
        //Afficher la vue
        return "ficheDeSuivi";
    }
    
    @Transactional
    @GetMapping("/modifierFicheDeSuivi")
    public String modifierFicheDeSuivi(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        @RequestParam("datePrescription") String datePrescriptionParam
        ) throws ParseException {

        //Trouver le résident correspondant aux nom, prénom et date de naissance renseignés (Clé primaire)
        Resident resident = em.find(Resident.class, new ResidentId(nom, prenom, dateNaissanceParam));
        
        String datePrescription = UtilDate.getDateFormatddMMyyyy(datePrescriptionParam);
        resident.setDatePrescription(datePrescription);
        
        return "redirect:/voirFicheDeSuivi?nom="+nom+"&prenom="+prenom+"&dateNaissance="+dateNaissanceParam;
    }
}