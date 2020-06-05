package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import jardinsdeflore.suivikine.service.ResidentService;
import jardinsdeflore.suivikine.util.UtilDate;
import java.text.ParseException;
import java.util.Optional;
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
        String prescriptionQuantitative = resident.getPrescriptionQuantitative();
        if (prescriptionQuantitative != null && !prescriptionQuantitative.isEmpty()) {
            model.addAttribute("prescriptionQuantitative", prescriptionQuantitative);
        }
        String renouvellement = resident.getRenouvellement();
        if (renouvellement != null && !renouvellement.isEmpty()) {
            model.addAttribute("renouvellement", resident.getRenouvellement());
        }
        String indicationMedicale = resident.getIndicationMedicale();
        if (indicationMedicale != null && !indicationMedicale.isEmpty()) {
            model.addAttribute("indicationMedicale", indicationMedicale);
        }
        model.addAttribute("nbProtocoleTherapeutique", resident.getNbProtocoleTherapeutique());
        model.addAttribute("rythmeSeances", resident.getRythmeSeances());
        String lieuSeances = resident.getLieuSeances();
        if (lieuSeances != null && !lieuSeances.isEmpty()) {
            model.addAttribute("lieuSeances", resident.getLieuSeances());
        }
        String travailGroupe = resident.getTravailGroupe();
        if (travailGroupe != null && !travailGroupe.isEmpty()) {
            model.addAttribute("travailGroupe", travailGroupe);
        }
        String datePremiereSeance = resident.getDatePremiereSeance();
        if (datePremiereSeance != null && !datePremiereSeance.isEmpty()) {
            model.addAttribute("datePremiereSeance", datePremiereSeance);
        }
        String techniques = resident.getTechniques();
        if (techniques != null && !techniques.isEmpty()) {
            model.addAttribute("techniques", techniques);
        }
        String intitules = resident.getIntitules();
        if (intitules != null && !intitules.isEmpty()) {
            model.addAttribute("intitules", techniques);
        }
        String demarrageDate = resident.getDemarrageDate();
        if (demarrageDate != null && !demarrageDate.isEmpty()) {
            model.addAttribute("demarrageDate", demarrageDate);
        }
        String intermediaireDate = resident.getIntermediaireDate();
        if (intermediaireDate != null && !intermediaireDate.isEmpty()) {
            model.addAttribute("intermediaireDate", intermediaireDate);
        }
        String finaleDate = resident.getFinaleDate();
        if (finaleDate != null && !finaleDate.isEmpty()) {
            model.addAttribute("finaleDate", finaleDate);
        }
        String articulairesD = resident.getArticulairesD();
        if (articulairesD != null && !articulairesD.isEmpty()) {
            model.addAttribute("articulairesD", articulairesD);
        }
        String articulairesI = resident.getArticulairesI();
        if (articulairesI != null && !articulairesI.isEmpty()) {
            model.addAttribute("articulairesI", articulairesI);
        }
        String articulairesF = resident.getArticulairesF();
        if (articulairesF != null && !articulairesF.isEmpty()) {
            model.addAttribute("articulairesF", articulairesF);
        }
        String forceMusculaireD = resident.getForceMusculaireD();
        if (forceMusculaireD != null && !forceMusculaireD.isEmpty()) {
            model.addAttribute("forceMusculaireD", forceMusculaireD);
        }
        String forceMusculaireI = resident.getForceMusculaireI();
        if (forceMusculaireI != null && !forceMusculaireI.isEmpty()) {
            model.addAttribute("forceMusculaireI", forceMusculaireI);
        }
        String forceMusculaireF = resident.getForceMusculaireF();
        if (forceMusculaireF != null && !forceMusculaireF.isEmpty()) {
            model.addAttribute("forceMusculaireF", forceMusculaireF);
        }
        String douleursD = resident.getDouleursD();
        if (douleursD != null && !douleursD.isEmpty()) {
            model.addAttribute("douleursD", douleursD);
        }
        String douleursI = resident.getDouleursI();
        if (douleursI != null && !douleursI.isEmpty()) {
            model.addAttribute("douleursI", douleursI);
        }
        String douleursF = resident.getDouleursF();
        if (douleursF != null && !douleursF.isEmpty()) {
            model.addAttribute("douleursF", douleursF);
        }
        String trophiquesD = resident.getTrophiquesD();
        if (trophiquesD != null && !trophiquesD.isEmpty()) {
            model.addAttribute("trophiquesD", trophiquesD);
        }
        String trophiquesI = resident.getTrophiquesI();
        if (trophiquesI != null && !trophiquesI.isEmpty()) {
            model.addAttribute("trophiquesI", trophiquesI);
        }
        String trophiquesF = resident.getTrophiquesF();
        if (trophiquesF != null && !trophiquesF.isEmpty()) {
            model.addAttribute("trophiquesF", trophiquesF);
        }
        String bilanDeficitsFonctionnelsD = resident.getBilanDeficitsFonctionnelsD();
        if (bilanDeficitsFonctionnelsD != null && !bilanDeficitsFonctionnelsD.isEmpty()) {
            model.addAttribute("bilanDeficitsFonctionnelsD", bilanDeficitsFonctionnelsD);
        }
        String bilanDeficitsFonctionnelsI = resident.getBilanDeficitsFonctionnelsI();
        if (bilanDeficitsFonctionnelsI != null && !bilanDeficitsFonctionnelsI.isEmpty()) {
            model.addAttribute("bilanDeficitsFonctionnelsI", bilanDeficitsFonctionnelsI);
        }
        String bilanDeficitsFonctionnelsF = resident.getBilanDeficitsFonctionnelsF();
        if (bilanDeficitsFonctionnelsF != null && !bilanDeficitsFonctionnelsF.isEmpty()) {
            model.addAttribute("bilanDeficitsFonctionnelsF", bilanDeficitsFonctionnelsF);
        }
        String autresProblemesD = resident.getAutresProblemesD();
        if (autresProblemesD != null && !autresProblemesD.isEmpty()) {
            model.addAttribute("autresProblemesD", autresProblemesD);
        }
        String autresProblemesI = resident.getAutresProblemesI();
        if (autresProblemesI != null && !autresProblemesI.isEmpty()) {
            model.addAttribute("autresProblemesI", autresProblemesI);
        }
        String autresProblemesF = resident.getAutresProblemesF();
        if (autresProblemesF != null && !autresProblemesF.isEmpty()) {
            model.addAttribute("autresProblemesF", autresProblemesF);
        }
        String objectifsCourtTermeD = resident.getObjectifsCourtTermeD();
        if (objectifsCourtTermeD != null && !objectifsCourtTermeD.isEmpty()) {
            model.addAttribute("objectifsCourtTermeD", objectifsCourtTermeD);
        }
        String objectifsCourtTermeI = resident.getObjectifsCourtTermeI();
        if (objectifsCourtTermeI != null && !objectifsCourtTermeI.isEmpty()) {
            model.addAttribute("objectifsCourtTermeI", objectifsCourtTermeI);
        }
        String objectifsCourtTermeF = resident.getObjectifsCourtTermeF();
        if (objectifsCourtTermeF != null && !objectifsCourtTermeF.isEmpty()) {
            model.addAttribute("objectifsCourtTermeF", objectifsCourtTermeF);
        }
        String objectifsMoyenTermeD = resident.getObjectifsMoyenTermeD();
        if (objectifsMoyenTermeD != null && !objectifsMoyenTermeD.isEmpty()) {
            model.addAttribute("objectifsMoyenTermeD", objectifsMoyenTermeD);
        }
        String objectifsMoyenTermeI = resident.getObjectifsMoyenTermeI();
        if (objectifsMoyenTermeI != null && !objectifsMoyenTermeI.isEmpty()) {
            model.addAttribute("objectifsMoyenTermeI", objectifsMoyenTermeI);
        }
        String objectifsMoyenTermeF = resident.getObjectifsMoyenTermeF();
        if (objectifsMoyenTermeF != null && !objectifsMoyenTermeF.isEmpty()) {
            model.addAttribute("objectifsMoyenTermeF", objectifsMoyenTermeF);
        }
        String objectifsLongTermeD = resident.getObjectifsLongTermeD();
        if (objectifsLongTermeD != null && !objectifsLongTermeD.isEmpty()) {
            model.addAttribute("objectifsLongTermeD", objectifsLongTermeD);
        }
        String objectifsLongTermeI = resident.getObjectifsLongTermeI();
        if (objectifsLongTermeI != null && !objectifsLongTermeI.isEmpty()) {
            model.addAttribute("objectifsLongTermeI", objectifsLongTermeI);
        }
        String objectifsLongTermeF = resident.getObjectifsLongTermeF();
        if (objectifsLongTermeF != null && !objectifsLongTermeF.isEmpty()) {
            model.addAttribute("objectifsLongTermeF", objectifsLongTermeF);
        }
        String diagnosticD = resident.getDiagnosticD();
        if (diagnosticD != null && !diagnosticD.isEmpty()) {
            model.addAttribute("diagnosticD", diagnosticD);
        }
        String diagnosticI = resident.getDiagnosticI();
        if (diagnosticI != null && !diagnosticI.isEmpty()) {
            model.addAttribute("diagnosticI", diagnosticI);
        }
        String diagnosticF = resident.getDiagnosticF();
        if (diagnosticF != null && !diagnosticF.isEmpty()) {
            model.addAttribute("diagnosticF", diagnosticF);
        }
        String conseilsD = resident.getConseilsD();
        if (conseilsD != null && !conseilsD.isEmpty()) {
            model.addAttribute("conseilsD", conseilsD);
        }
        String conseilsI = resident.getConseilsI();
        if (conseilsI != null && !conseilsI.isEmpty()) {
            model.addAttribute("conseilsI", conseilsI);
        }
        String conseilsF = resident.getConseilsF();
        if (conseilsF != null && !conseilsF.isEmpty()) {
            model.addAttribute("conseilsF", conseilsF);
        }
        String propositionsD = resident.getPropositionsD();
        if (propositionsD != null && !propositionsD.isEmpty()) {
            model.addAttribute("propositionsD", propositionsD);
        }
        String propositionsI = resident.getPropositionsI();
        if (propositionsI != null && !propositionsI.isEmpty()) {
            model.addAttribute("propositionsI", propositionsI);
        }
        String propositionsF = resident.getPropositionsF();
        if (propositionsF != null && !propositionsF.isEmpty()) {
            model.addAttribute("propositionsF", propositionsF);
        }
        String commentairesD = resident.getCommentairesD();
        if (commentairesD != null && !commentairesD.isEmpty()) {
            model.addAttribute("commentairesD", commentairesD);
        }
        String commentairesI = resident.getCommentairesI();
        if (commentairesI != null && !commentairesI.isEmpty()) {
            model.addAttribute("commentairesI", commentairesI);
        }
        String cotation = resident.getCotation();
        if (cotation != null && !cotation.isEmpty()) {
            model.addAttribute("cotation", cotation);
        }
        //Afficher la vue
        return "ficheDeSuivi";
    }
    
    @Transactional
    @GetMapping("/modifierFicheDeSuivi")
    public String modifierFicheDeSuivi(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        @RequestParam("datePrescription") Optional<String> datePrescriptionParam,
        @RequestParam("prescriptionQuantitative") Optional<String> prescriptionQuantitative,
        @RequestParam("renouvellement") Optional<String> renouvellement,
        @RequestParam("indicationMedicale") Optional<String> indicationMedicale,
        @RequestParam("nbProtocoleTherapeutique") int nbProtocoleTherapeutique,
        @RequestParam("rythmeSeances") int rythmeSeances,
        @RequestParam("lieuSeances") Optional<String> lieuSeances,
        @RequestParam("travailGroupe") Optional<String> travailGroupe,
        @RequestParam("datePremiereSeance") Optional<String> datePremiereSeanceParam,
        @RequestParam("techniques") Optional<String> techniques,
        @RequestParam("intitules") Optional<String> intitules,
        @RequestParam("demarrageDate") Optional<String> demarrageDateParam,
        @RequestParam("intermediaireDate") Optional<String> intermediaireDateParam,
        @RequestParam("finaleDate") Optional<String> finaleDateParam,
        @RequestParam("articulairesD") Optional<String> articulairesD,
        @RequestParam("articulairesI") Optional<String> articulairesI,
        @RequestParam("articulairesF") Optional<String> articulairesF,
        @RequestParam("forceMusculaireD") Optional<String> forceMusculaireD,
        @RequestParam("forceMusculaireI") Optional<String> forceMusculaireI,
        @RequestParam("forceMusculaireF") Optional<String> forceMusculaireF,
        @RequestParam("douleursD") Optional<String> douleursD,
        @RequestParam("douleursI") Optional<String> douleursI,
        @RequestParam("douleursF") Optional<String> douleursF,
        @RequestParam("trophiquesD") Optional<String> trophiquesD,
        @RequestParam("trophiquesI") Optional<String> trophiquesI,
        @RequestParam("trophiquesF") Optional<String> trophiquesF,
        @RequestParam("bilanDeficitsFonctionnelsD") Optional<String> bilanDeficitsFonctionnelsD,
        @RequestParam("bilanDeficitsFonctionnelsI") Optional<String> bilanDeficitsFonctionnelsI,
        @RequestParam("bilanDeficitsFonctionnelsF") Optional<String> bilanDeficitsFonctionnelsF,
        @RequestParam("autresProblemesD") Optional<String> autresProblemesD,
        @RequestParam("autresProblemesI") Optional<String> autresProblemesI,
        @RequestParam("autresProblemesF") Optional<String> autresProblemesF,
        @RequestParam("objectifsCourtTermeD") Optional<String> objectifsCourtTermeD,
        @RequestParam("objectifsCourtTermeI") Optional<String> objectifsCourtTermeI,
        @RequestParam("objectifsCourtTermeF") Optional<String> objectifsCourtTermeF,
        @RequestParam("objectifsMoyenTermeD") Optional<String> objectifsMoyenTermeD,
        @RequestParam("objectifsMoyenTermeI") Optional<String> objectifsMoyenTermeI,
        @RequestParam("objectifsMoyenTermeF") Optional<String> objectifsMoyenTermeF,
        @RequestParam("objectifsLongTermeD") Optional<String> objectifsLongTermeD,
        @RequestParam("objectifsLongTermeI") Optional<String> objectifsLongTermeI,
        @RequestParam("objectifsLongTermeF") Optional<String> objectifsLongTermeF,
        @RequestParam("diagnosticD") Optional<String> diagnosticD,
        @RequestParam("diagnosticI") Optional<String> diagnosticI,
        @RequestParam("diagnosticF") Optional<String> diagnosticF,
        @RequestParam("conseilsD") Optional<String> conseilsD,
        @RequestParam("conseilsI") Optional<String> conseilsI,
        @RequestParam("conseilsF") Optional<String> conseilsF,
        @RequestParam("propositionsD") Optional<String> propositionsD,
        @RequestParam("propositionsI") Optional<String> propositionsI,
        @RequestParam("propositionsF") Optional<String> propositionsF,
        @RequestParam("commentairesD") Optional<String> commentairesD,
        @RequestParam("commentairesI") Optional<String> commentairesI,
        @RequestParam("cotation") Optional<String> cotation
        ) throws ParseException {

        //Trouver le résident correspondant aux nom, prénom et date de naissance renseignés (Clé primaire)
        Resident resident = em.find(Resident.class, new ResidentId(nom, prenom, dateNaissanceParam));
        
        if (datePrescriptionParam.isPresent() && !datePrescriptionParam.get().isEmpty()) {
            String datePrescription = UtilDate.getDateFormatddMMyyyy(datePrescriptionParam.get());
            resident.setDatePrescription(datePrescription);
        }
        if (prescriptionQuantitative.isPresent() && !prescriptionQuantitative.get().isEmpty()) {
            resident.setPrescriptionQuantitative(prescriptionQuantitative.get());
        }
        if (renouvellement.isPresent() && !renouvellement.get().isEmpty()) {
            resident.setRenouvellement(renouvellement.get());
        }
        if (indicationMedicale.isPresent() && !indicationMedicale.get().isEmpty()) {
            resident.setIndicationMedicale(indicationMedicale.get());
        }
        resident.setNbProtocoleTherapeutique(nbProtocoleTherapeutique);
        resident.setRythmeSeances(rythmeSeances);
        if (lieuSeances.isPresent() && !lieuSeances.get().isEmpty()) {
            resident.setLieuSeances(lieuSeances.get());
        }
        if (travailGroupe.isPresent() && !travailGroupe.get().isEmpty()) {
            resident.setTravailGroupe(travailGroupe.get());
        }
        if (datePremiereSeanceParam.isPresent() && !datePremiereSeanceParam.get().isEmpty()) {
            String datePremiereSeance = UtilDate.getDateFormatddMMyyyy(datePremiereSeanceParam.get());
            resident.setDatePremiereSeance(datePremiereSeance);
        }
        if (techniques.isPresent() && !techniques.get().isEmpty()) {
            resident.setTravailGroupe(techniques.get());
        }
        if (intitules.isPresent() && !intitules.get().isEmpty()) {
            resident.setTravailGroupe(intitules.get());
        }
        if (demarrageDateParam.isPresent() && !demarrageDateParam.get().isEmpty()) {
            String demarrageDate = UtilDate.getDateFormatddMMyyyy(demarrageDateParam.get());
            resident.setDemarrageDate(demarrageDate);
        }
        if (intermediaireDateParam.isPresent() && !intermediaireDateParam.get().isEmpty()) {
            String intermediaireDate = UtilDate.getDateFormatddMMyyyy(intermediaireDateParam.get());
            resident.setIntermediaireDate(intermediaireDate);
        }
        if (finaleDateParam.isPresent() && !finaleDateParam.get().isEmpty()) {
            String finaleDate = UtilDate.getDateFormatddMMyyyy(finaleDateParam.get());
            resident.setFinaleDate(finaleDate);
        }
        if (articulairesD.isPresent()) {
            resident.setTravailGroupe(articulairesD.get());
        }
        if (articulairesI.isPresent()) {
            resident.setTravailGroupe(articulairesI.get());
        }
        if (articulairesF.isPresent()) {
            resident.setTravailGroupe(articulairesF.get());
        }
        if (forceMusculaireD.isPresent()) {
            resident.setTravailGroupe(forceMusculaireD.get());
        }
        if (forceMusculaireI.isPresent()) {
            resident.setTravailGroupe(forceMusculaireI.get());
        }
        if (forceMusculaireF.isPresent()) {
            resident.setTravailGroupe(forceMusculaireF.get());
        }
        if (douleursD.isPresent()) {
            resident.setTravailGroupe(douleursD.get());
        }
        if (douleursI.isPresent()) {
            resident.setTravailGroupe(douleursI.get());
        }
        if (douleursF.isPresent()) {
            resident.setTravailGroupe(douleursF.get());
        }
        if (trophiquesD.isPresent()) {
            resident.setTravailGroupe(trophiquesD.get());
        }
        if (trophiquesI.isPresent()) {
            resident.setTravailGroupe(trophiquesI.get());
        }
        if (trophiquesF.isPresent()) {
            resident.setTravailGroupe(trophiquesF.get());
        }
        if (bilanDeficitsFonctionnelsD.isPresent()) {
            resident.setTravailGroupe(bilanDeficitsFonctionnelsD.get());
        }
        if (bilanDeficitsFonctionnelsI.isPresent()) {
            resident.setTravailGroupe(bilanDeficitsFonctionnelsI.get());
        }
        if (bilanDeficitsFonctionnelsF.isPresent()) {
            resident.setTravailGroupe(bilanDeficitsFonctionnelsF.get());
        }
        if (autresProblemesD.isPresent()) {
            resident.setTravailGroupe(autresProblemesD.get());
        }
        if (autresProblemesI.isPresent()) {
            resident.setTravailGroupe(autresProblemesI.get());
        }
        if (autresProblemesF.isPresent()) {
            resident.setTravailGroupe(autresProblemesF.get());
        }
        if (objectifsCourtTermeD.isPresent()) {
            resident.setTravailGroupe(objectifsCourtTermeD.get());
        }
        if (objectifsCourtTermeI.isPresent()) {
            resident.setTravailGroupe(objectifsCourtTermeI.get());
        }
        if (objectifsCourtTermeF.isPresent()) {
            resident.setTravailGroupe(objectifsCourtTermeF.get());
        }
        if (objectifsMoyenTermeD.isPresent()) {
            resident.setTravailGroupe(objectifsMoyenTermeD.get());
        }
        if (objectifsMoyenTermeI.isPresent()) {
            resident.setTravailGroupe(objectifsMoyenTermeI.get());
        }
        if (objectifsMoyenTermeF.isPresent()) {
            resident.setTravailGroupe(objectifsMoyenTermeF.get());
        }
        if (objectifsLongTermeD.isPresent()) {
            resident.setTravailGroupe(objectifsLongTermeD.get());
        }
        if (objectifsLongTermeI.isPresent()) {
            resident.setTravailGroupe(objectifsLongTermeI.get());
        }
        if (objectifsLongTermeF.isPresent()) {
            resident.setTravailGroupe(objectifsLongTermeF.get());
        }
        if (diagnosticD.isPresent()) {
            resident.setTravailGroupe(diagnosticD.get());
        }
        if (diagnosticI.isPresent()) {
            resident.setTravailGroupe(diagnosticI.get());
        }
        if (diagnosticF.isPresent()) {
            resident.setTravailGroupe(diagnosticF.get());
        }
        if (conseilsD.isPresent()) {
            resident.setTravailGroupe(conseilsD.get());
        }
        if (conseilsI.isPresent()) {
            resident.setTravailGroupe(conseilsI.get());
        }
        if (conseilsF.isPresent()) {
            resident.setTravailGroupe(conseilsF.get());
        }
        if (propositionsD.isPresent()) {
            resident.setTravailGroupe(propositionsD.get());
        }
        if (propositionsI.isPresent()) {
            resident.setTravailGroupe(propositionsI.get());
        }
        if (propositionsF.isPresent()) {
            resident.setTravailGroupe(propositionsF.get());
        }
        if (commentairesD.isPresent()) {
            resident.setTravailGroupe(commentairesD.get());
        }
        if (commentairesI.isPresent()) {
            resident.setTravailGroupe(commentairesI.get());
        }
        if (cotation.isPresent()) {
            resident.setTravailGroupe(cotation.get());
        }
        return "redirect:/voirFicheDeSuivi?nom="+nom+"&prenom="+prenom+"&dateNaissance="+dateNaissanceParam;
    }
}