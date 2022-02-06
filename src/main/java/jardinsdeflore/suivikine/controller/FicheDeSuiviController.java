package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.EquipeKineRepository;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import jardinsdeflore.suivikine.util.UtilDate;
import java.text.ParseException;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FicheDeSuiviController {
    
    @Autowired
    ResidentRepository residentRepository;
    
    @Autowired
    EquipeKineRepository equipeKineRepository;
    
    @Autowired
    EntityManager em;
    
    //Ajouter une fiche de suivi à la BDD
    @GetMapping("/creerFicheDeSuivi")
    public String creerFicheDeSuivi(
        @RequestParam("nom") String nomParam,
        @RequestParam("prenom") String prenomParam,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        @RequestParam("sexe") String sexe,
        @RequestParam("medecinPrescripteur") String medecinPrescripteur,
        @RequestParam("nSecu") String nSecuParam,
        HttpSession session,
        Model model) throws ParseException {

        //Enlever tous les espaces avant et après le nom, le prénom et le numéro de sécurité sociale
        String nom = nomParam.trim();
        String prenom = prenomParam.trim();
        String nSecu = nSecuParam.trim();
        
        String dateNaissance = UtilDate.getDateFormatddMMyyyy(dateNaissanceParam);
        //Récupérer l'id de l'équipe kiné connectée
        int idEquipe = (int) session.getAttribute("idEquipe");
        //Créer un résident avec les paramètres
        Resident resident = new Resident(nom, prenom, dateNaissance, sexe, nSecu, medecinPrescripteur, idEquipe);
        //Vérifier si le résident existe déja
        if(residentRepository.existsById(new ResidentId(nom, prenom, dateNaissance))) {
            //Si il existe, on renvoit à la même page et on ne sauvegarde pas le résident dans la BDD
            return "redirect:/nouvelleFiche";
        } 
        else {
            //Sinon, on on sauvegarde le résident dans la BDD et on redirige vers la page "ficheDeSuivi"
            residentRepository.save(resident);
            return "redirect:/voirFicheDeSuivi?nom="+nom+"&prenom="+prenom+"&dateNaissance="+dateNaissance;
        }
    }
    
    //Voir une fiche de suivi
    @GetMapping("/voirFicheDeSuivi")
    public String voirFicheDeSuivi(
        @RequestParam("nom") String nomParam,
        @RequestParam("prenom") String prenomParam,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        HttpSession session,
        Model model) throws ParseException {
        
        //Récupérer l'id de l'équipe kiné connectée
        int idEquipe = (int) session.getAttribute("idEquipe");
        //Envoyer l'id à la vue pour changer l'action du bouton "accueil" : accueil admin si l'id vaut 1, sinon accueil
        model.addAttribute("idEquipe", idEquipe);
            
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
        String nomEquipe = equipeKineRepository.findById(resident.getEquipeKine()).get().getNom();
        model.addAttribute("equipeKine", nomEquipe);
        //Vérifier que les informations facultatives existent et qu'elles ne sont pas vides, puis les ajouter à la vue
        String datePrescription = resident.getDatePrescription();
        if (datePrescription != null && !datePrescription.isEmpty()) {
            model.addAttribute("datePrescriptionLabel", datePrescription);
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
        String travailGroupe = resident.getTravailGroupe();
        if (travailGroupe != null && !travailGroupe.isEmpty()) {
            model.addAttribute("travailGroupe", travailGroupe);
        }
        String datePremiereSeance = resident.getDatePremiereSeance();
        if (datePremiereSeance != null && !datePremiereSeance.isEmpty()) {
            model.addAttribute("datePremiereSeanceLabel", datePremiereSeance);
            model.addAttribute("datePremiereSeance", UtilDate.getDateFormatyyyyMMdd(datePremiereSeance));
        }
        String techniques = resident.getTechniques();
        if (techniques != null && !techniques.isEmpty()) {
            model.addAttribute("techniques", techniques);
        }
        String intitules = resident.getIntitules();
        if (intitules != null && !intitules.isEmpty()) {
            model.addAttribute("intitules", intitules);
        }
        String demarrageDate = resident.getDemarrageDate();
        if (demarrageDate != null && !demarrageDate.isEmpty()) {
            model.addAttribute("demarrageDateLabel", demarrageDate);
            model.addAttribute("demarrageDate", UtilDate.getDateFormatyyyyMMdd(demarrageDate));
        }
        String intermediaireDate = resident.getIntermediaireDate();
        if (intermediaireDate != null && !intermediaireDate.isEmpty()) {
            model.addAttribute("intermediaireDateLabel", intermediaireDate);
            model.addAttribute("intermediaireDate", UtilDate.getDateFormatyyyyMMdd(intermediaireDate));
        }
        String finaleDate = resident.getFinaleDate();
        if (finaleDate != null && !finaleDate.isEmpty()) {
            model.addAttribute("finaleDateLabel", finaleDate);
            model.addAttribute("finaleDate", UtilDate.getDateFormatyyyyMMdd(finaleDate));
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
        model.addAttribute("archive", resident.getArchive());
        //Afficher la vue
        return "ficheDeSuivi";
    }
    
    //Modifier une fiche de suivi
    @Transactional
    @RequestMapping(value = "/modifierFicheDeSuivi", method = RequestMethod.POST)
    public void modifierFicheDeSuivi(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        @RequestParam("datePrescription") Optional<String> datePrescriptionParam,
        @RequestParam("prescriptionQuantitative") Optional<String> prescriptionQuantitative,
        @RequestParam("renouvellement") Optional<String> renouvellement,
        @RequestParam("indicationMedicale") Optional<String> indicationMedicale,
        @RequestParam("nbProtocoleTherapeutique") int nbProtocoleTherapeutique,
        @RequestParam("rythmeSeances") int rythmeSeances,
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
        @RequestParam("cotation") Optional<String> cotation,
        @RequestParam("archive") Optional<String> archive
        ) throws ParseException {

        //Trouver le résident correspondant aux nom, prénom et date de naissance renseignés (Clé primaire)
        Resident resident = em.find(Resident.class, new ResidentId(nom, prenom, dateNaissanceParam));
        //Vérifier que les informations facultatives existent et qu'elles ne sont pas vides, puis les ajouter à la BDD
        if (datePrescriptionParam.isPresent() && !datePrescriptionParam.get().isEmpty()) {
            String datePrescription = UtilDate.getDateFormatddMMyyyy(datePrescriptionParam.get());
            resident.setDatePrescription(datePrescription);
        }
        if (prescriptionQuantitative.isPresent()) {
            resident.setPrescriptionQuantitative(prescriptionQuantitative.get());
        }
        if (renouvellement.isPresent()) {
            resident.setRenouvellement(renouvellement.get());
        }
        if (indicationMedicale.isPresent()) {
            resident.setIndicationMedicale(indicationMedicale.get().trim());
        }
        resident.setNbProtocoleTherapeutique(nbProtocoleTherapeutique);
        resident.setRythmeSeances(rythmeSeances);
        if (travailGroupe.isPresent()) {
            resident.setTravailGroupe(travailGroupe.get());
        }
        if (datePremiereSeanceParam.isPresent() && !datePremiereSeanceParam.get().isEmpty()) {
            String datePremiereSeance = UtilDate.getDateFormatddMMyyyy(datePremiereSeanceParam.get());
            resident.setDatePremiereSeance(datePremiereSeance);
        }
        if (techniques.isPresent()) {
            resident.setTechniques(techniques.get().trim());
        }
        if (intitules.isPresent()) {
            resident.setIntitules(intitules.get().trim());
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
            resident.setArticulairesD(articulairesD.get().trim());
        }
        if (articulairesI.isPresent()) {
            resident.setArticulairesI(articulairesI.get().trim());
        }
        if (articulairesF.isPresent()) {
            resident.setArticulairesF(articulairesF.get().trim());
        }
        if (forceMusculaireD.isPresent()) {
            resident.setForceMusculaireD(forceMusculaireD.get().trim());
        }
        if (forceMusculaireI.isPresent()) {
            resident.setForceMusculaireI(forceMusculaireI.get().trim());
        }
        if (forceMusculaireF.isPresent()) {
            resident.setForceMusculaireF(forceMusculaireF.get().trim());
        }
        if (douleursD.isPresent()) {
            resident.setDouleursD(douleursD.get().trim());
        }
        if (douleursI.isPresent()) {
            resident.setDouleursI(douleursI.get().trim());
        }
        if (douleursF.isPresent()) {
            resident.setDouleursF(douleursF.get().trim());
        }
        if (trophiquesD.isPresent()) {
            resident.setTrophiquesD(trophiquesD.get().trim());
        }
        if (trophiquesI.isPresent()) {
            resident.setTrophiquesI(trophiquesI.get().trim());
        }
        if (trophiquesF.isPresent()) {
            resident.setTrophiquesF(trophiquesF.get().trim());
        }
        if (bilanDeficitsFonctionnelsD.isPresent()) {
            resident.setBilanDeficitsFonctionnelsD(bilanDeficitsFonctionnelsD.get().trim());
        }
        if (bilanDeficitsFonctionnelsI.isPresent()) {
            resident.setBilanDeficitsFonctionnelsI(bilanDeficitsFonctionnelsI.get().trim());
        }
        if (bilanDeficitsFonctionnelsF.isPresent()) {
            resident.setBilanDeficitsFonctionnelsF(bilanDeficitsFonctionnelsF.get().trim());
        }
        if (autresProblemesD.isPresent()) {
            resident.setAutresProblemesD(autresProblemesD.get().trim());
        }
        if (autresProblemesI.isPresent()) {
            resident.setAutresProblemesI(autresProblemesI.get().trim());
        }
        if (autresProblemesF.isPresent()) {
            resident.setAutresProblemesF(autresProblemesF.get().trim());
        }
        if (objectifsCourtTermeD.isPresent()) {
            resident.setObjectifsCourtTermeD(objectifsCourtTermeD.get().trim());
        }
        if (objectifsCourtTermeI.isPresent()) {
            resident.setObjectifsCourtTermeI(objectifsCourtTermeI.get().trim());
        }
        if (objectifsCourtTermeF.isPresent()) {
            resident.setObjectifsCourtTermeF(objectifsCourtTermeF.get().trim());
        }
        if (objectifsMoyenTermeD.isPresent()) {
            resident.setObjectifsMoyenTermeD(objectifsMoyenTermeD.get().trim());
        }
        if (objectifsMoyenTermeI.isPresent()) {
            resident.setObjectifsMoyenTermeI(objectifsMoyenTermeI.get().trim());
        }
        if (objectifsMoyenTermeF.isPresent()) {
            resident.setObjectifsMoyenTermeF(objectifsMoyenTermeF.get().trim());
        }
        if (objectifsLongTermeD.isPresent()) {
            resident.setObjectifsLongTermeD(objectifsLongTermeD.get().trim());
        }
        if (objectifsLongTermeI.isPresent()) {
            resident.setObjectifsLongTermeI(objectifsLongTermeI.get().trim());
        }
        if (objectifsLongTermeF.isPresent()) {
            resident.setObjectifsLongTermeF(objectifsLongTermeF.get().trim());
        }
        if (diagnosticD.isPresent()) {
            resident.setDiagnosticD(diagnosticD.get().trim());
        }
        if (diagnosticI.isPresent()) {
            resident.setDiagnosticI(diagnosticI.get().trim());
        }
        if (diagnosticF.isPresent()) {
            resident.setDiagnosticF(diagnosticF.get().trim());
        }
        if (conseilsD.isPresent()) {
            resident.setConseilsD(conseilsD.get().trim());
        }
        if (conseilsI.isPresent()) {
            resident.setConseilsI(conseilsI.get().trim());
        }
        if (conseilsF.isPresent()) {
            resident.setConseilsF(conseilsF.get().trim());
        }
        if (propositionsD.isPresent()) {
            resident.setPropositionsD(propositionsD.get().trim());
        }
        if (propositionsI.isPresent()) {
            resident.setPropositionsI(propositionsI.get().trim());
        }
        if (propositionsF.isPresent()) {
            resident.setPropositionsF(propositionsF.get().trim());
        }
        if (commentairesD.isPresent()) {
            resident.setCommentairesD(commentairesD.get().trim());
        }
        if (commentairesI.isPresent()) {
            resident.setCommentairesI(commentairesI.get().trim());
        }
        if (cotation.isPresent()) {
            resident.setCotation(cotation.get().trim());
        }
        if (archive.isPresent()) {
            resident.setArchive(archive.get().trim());
        }
    }
    
    //Supprimer une fiche de suivi de la BDD
    @GetMapping("/supprimerFicheDeSuivi")
    public String supprimerFicheDeSuivi(
        @RequestParam("nom") String nomParam,
        @RequestParam("prenom") String prenomParam,
        @RequestParam("dateNaissance") String dateNaissance,
        HttpSession session
        ) throws ParseException {

        //Enlever tous les espaces avant et après le nom et le prénom
        String nom = nomParam.trim();
        String prenom = prenomParam.trim();

        //Supprimer le résident de la BDD
        residentRepository.deleteById(new ResidentId(nom, prenom, dateNaissance));
        
        //Récupérer l'id de l'équipe kiné connectée
        int idEquipe = (int) session.getAttribute("idEquipe");
        if (idEquipe == 1) {
            //Renvoyer à l'accueil admin
            return "accueilAdmin";
        }
        else {
            //Renvoyer à l'accueil
            return "accueil";
        }
    }
    
    //Gérer les erreurs 
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public String databaseError() {
        return "accueil";
    } 
}