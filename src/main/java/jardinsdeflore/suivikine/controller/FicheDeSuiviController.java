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
        @RequestParam("datePrescription") String datePrescriptionParam,
        @RequestParam("prescriptionQuantitative") String prescriptionQuantitative,
        @RequestParam("renouvellement") String renouvellement,
        @RequestParam("indicationMedicale") String indicationMedicale,
        @RequestParam("nbProtocoleTherapeutique") int nbProtocoleTherapeutique,
        @RequestParam("rythmeSeances") int rythmeSeances,
        @RequestParam("lieuSeances") String lieuSeances,
        @RequestParam("travailGroupe") String travailGroupe,
        @RequestParam("datePremiereSeance") String datePremiereSeanceParam,
        @RequestParam("techniques") String techniques,
        @RequestParam("intitules") String intitules,
        @RequestParam("demarrageDate") String demarrageDateParam,
        @RequestParam("intermediaireDate") String intermediaireDateParam,
        @RequestParam("finaleDate") String finaleDateParam,
        @RequestParam("articulairesD") String articulairesD,
        @RequestParam("articulairesI") String articulairesI,
        @RequestParam("articulairesF") String articulairesF,
        @RequestParam("forceMusculaireD") String forceMusculaireD,
        @RequestParam("forceMusculaireI") String forceMusculaireI,
        @RequestParam("forceMusculaireF") String forceMusculaireF,
        @RequestParam("douleursD") String douleursD,
        @RequestParam("douleursI") String douleursI,
        @RequestParam("douleursF") String douleursF,
        @RequestParam("trophiquesD") String trophiquesD,
        @RequestParam("trophiquesI") String trophiquesI,
        @RequestParam("trophiquesF") String trophiquesF,
        @RequestParam("bilanDeficitsFonctionnelsD") String bilanDeficitsFonctionnelsD,
        @RequestParam("bilanDeficitsFonctionnelsI") String bilanDeficitsFonctionnelsI,
        @RequestParam("bilanDeficitsFonctionnelsF") String bilanDeficitsFonctionnelsF,
        @RequestParam("autresProblemesD") String autresProblemesD,
        @RequestParam("autresProblemesI") String autresProblemesI,
        @RequestParam("autresProblemesF") String autresProblemesF,
        @RequestParam("objectifsCourtTermeD") String objectifsCourtTermeD,
        @RequestParam("objectifsCourtTermeI") String objectifsCourtTermeI,
        @RequestParam("objectifsCourtTermeF") String objectifsCourtTermeF,
        @RequestParam("objectifsMoyenTermeD") String objectifsMoyenTermeD,
        @RequestParam("objectifsMoyenTermeI") String objectifsMoyenTermeI,
        @RequestParam("objectifsMoyenTermeF") String objectifsMoyenTermeF,
        @RequestParam("objectifsLongTermeD") String objectifsLongTermeD,
        @RequestParam("objectifsLongTermeI") String objectifsLongTermeI,
        @RequestParam("objectifsLongTermeF") String objectifsLongTermeF,
        @RequestParam("diagnosticD") String diagnosticD,
        @RequestParam("diagnosticI") String diagnosticI,
        @RequestParam("diagnosticF") String diagnosticF,
        @RequestParam("conseilsD") String conseilsD,
        @RequestParam("conseilsI") String conseilsI,
        @RequestParam("conseilsF") String conseilsF,
        @RequestParam("propositionsD") String propositionsD,
        @RequestParam("propositionsI") String propositionsI,
        @RequestParam("propositionsF") String propositionsF,
        @RequestParam("commentairesD") String commentairesD,
        @RequestParam("commentairesI") String commentairesI,
        @RequestParam("cotation") String cotation
        ) throws ParseException {

        //Trouver le résident correspondant aux nom, prénom et date de naissance renseignés (Clé primaire)
        Resident resident = em.find(Resident.class, new ResidentId(nom, prenom, dateNaissanceParam));
        
        String datePrescription = UtilDate.getDateFormatddMMyyyy(datePrescriptionParam);
        resident.setDatePrescription(datePrescription);
        resident.setPrescriptionQuantitative(prescriptionQuantitative);
        resident.setRenouvellement(renouvellement);
        resident.setIndicationMedicale(indicationMedicale);
        resident.setNbProtocoleTherapeutique(nbProtocoleTherapeutique);
        resident.setRythmeSeances(rythmeSeances);
        resident.setLieuSeances(lieuSeances);
        resident.setTravailGroupe(travailGroupe);
        String datePremiereSeance = UtilDate.getDateFormatddMMyyyy(datePremiereSeanceParam);
        resident.setDatePremiereSeance(datePremiereSeance);
        resident.setTechniques(techniques);
        resident.setIntitules(intitules);
        String demarrageDate = UtilDate.getDateFormatddMMyyyy(demarrageDateParam);
        resident.setDemarrageDate(demarrageDate);
        String intermediaireDate = UtilDate.getDateFormatddMMyyyy(intermediaireDateParam);
        resident.setIntermediaireDate(intermediaireDate);
        String finaleDate = UtilDate.getDateFormatddMMyyyy(finaleDateParam);
        resident.setFinaleDate(finaleDate);
        resident.setArticulairesD(articulairesD);
        resident.setArticulairesI(articulairesI);
        resident.setArticulairesF(articulairesF);
        resident.setForceMusculaireD(forceMusculaireD);
        resident.setForceMusculaireI(forceMusculaireI);
        resident.setForceMusculaireF(forceMusculaireF);
        resident.setDouleursD(douleursD);
        resident.setDouleursI(douleursI);
        resident.setDouleursF(douleursF);
        resident.setTrophiquesD(trophiquesD);
        resident.setTrophiquesI(trophiquesI);
        resident.setTrophiquesF(trophiquesF);
        resident.setBilanDeficitsFonctionnelsD(bilanDeficitsFonctionnelsD);
        resident.setBilanDeficitsFonctionnelsI(bilanDeficitsFonctionnelsI);
        resident.setBilanDeficitsFonctionnelsF(bilanDeficitsFonctionnelsF);
        resident.setAutresProblemesD(autresProblemesD);
        resident.setAutresProblemesI(autresProblemesI);
        resident.setAutresProblemesF(autresProblemesF);
        resident.setObjectifsCourtTermeD(objectifsCourtTermeD);
        resident.setObjectifsCourtTermeI(objectifsCourtTermeI);
        resident.setObjectifsCourtTermeF(objectifsCourtTermeF);
        resident.setObjectifsMoyenTermeD(objectifsMoyenTermeD);
        resident.setObjectifsMoyenTermeI(objectifsMoyenTermeI);
        resident.setObjectifsMoyenTermeF(objectifsMoyenTermeF);
        resident.setObjectifsLongTermeD(objectifsLongTermeD);
        resident.setObjectifsLongTermeI(objectifsLongTermeI);
        resident.setObjectifsLongTermeF(objectifsLongTermeF);
        resident.setDiagnosticD(diagnosticD);
        resident.setDiagnosticI(diagnosticI);
        resident.setDiagnosticF(diagnosticF);
        resident.setConseilsD(conseilsD);
        resident.setConseilsI(conseilsI);
        resident.setConseilsF(conseilsF);
        resident.setPropositionsD(propositionsD);
        resident.setPropositionsI(propositionsI);
        resident.setPropositionsF(propositionsF);
        resident.setCommentairesD(commentairesD);
        resident.setCommentairesI(commentairesI);
        resident.setCotation(cotation);
        return "redirect:/voirFicheDeSuivi?nom="+nom+"&prenom="+prenom+"&dateNaissance="+dateNaissanceParam;
    }
}