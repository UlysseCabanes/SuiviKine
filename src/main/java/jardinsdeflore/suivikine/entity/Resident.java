package jardinsdeflore.suivikine.entity;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "resident")
@IdClass(ResidentId.class)
public class Resident implements Serializable {

    //Attributs
    @Id
    @NotNull
    @NotEmpty
    @Column(name="nom")
    private String nom;

    @Id
    @NotNull
    @NotEmpty
    @Column(name="prenom")
    private String prenom;

    @Id
    @NotNull
    @Column(name="date_naissance")
    private String dateNaissance;

    @NotNull
    @NotEmpty
    @Column(name="sexe")
    private String sexe;

    @NotNull
    @NotEmpty
    @Column(name="n_secu_sociale")
    private String nSecuSociale = "000000000000000";

    @NotNull
    @NotEmpty
    @Column(name="medecin_prescripteur")
    private String medecinPrescripteur;

    @NotNull
    @Column(name="equipe_kine")
    private int equipeKine;

    @Column(name="date_prescription")
    private String datePrescription;

    @Column(name="prescription_quantitative")
    private String prescriptionQuantitative;

    private String renouvellement;

    @Column(name="indication_medicale")
    private String indicationMedicale;

    @Column(name="nb_protocole_therapeutique")
    private int nbProtocoleTherapeutique;

    @Column(name="rythme_seances")
    private int rythmeSeances;

    @Column(name="travail_groupe")
    private String travailGroupe;

    @Column(name="date_premiere_seance")
    private String datePremiereSeance;

    @Column(name="techniques")
    private String techniques;

    @Column(name="intitules")
    private String intitules;

    @Column(name="demarrage_date")
    private String demarrageDate;

    @Column(name="intermediaire_date")
    private String intermediaireDate;

    @Column(name="finale_date")
    private String finaleDate;

    @Column(name = "articulairesD", columnDefinition="TEXT")
    private String articulairesD;

    @Column(name = "articulairesI", columnDefinition="TEXT")
    private String articulairesI;

    @Column(name = "articulairesF", columnDefinition="TEXT")
    private String articulairesF;

    @Column(name="force_musculaireD", columnDefinition="TEXT")
    private String forceMusculaireD;

    @Column(name="force_musculaireI", columnDefinition="TEXT")
    private String forceMusculaireI;

    @Column(name="force_musculaireF", columnDefinition="TEXT")
    private String forceMusculaireF;

    @Column(name="douleursD", columnDefinition="TEXT")
    private String douleursD;

    @Column(name="douleursI", columnDefinition="TEXT")
    private String douleursI;

    @Column(name="douleursF", columnDefinition="TEXT")
    private String douleursF;

    @Column(name="trophiquesD", columnDefinition="TEXT")
    private String trophiquesD;

    @Column(name="trophiquesI", columnDefinition="TEXT")
    private String trophiquesI;

    @Column(name="trophiquesF", columnDefinition="TEXT")
    private String trophiquesF;

    @Column(name="bilan_deficits_fonctionnelsD", columnDefinition="TEXT")
    private String bilanDeficitsFonctionnelsD;

    @Column(name="bilan_deficits_fonctionnelsI", columnDefinition="TEXT")
    private String bilanDeficitsFonctionnelsI;

    @Column(name="bilan_deficits_fonctionnelsF", columnDefinition="TEXT")
    private String bilanDeficitsFonctionnelsF;

    @Column(name="autres_problemesD", columnDefinition="TEXT")
    private String autresProblemesD;

    @Column(name="autres_problemesI", columnDefinition="TEXT")
    private String autresProblemesI;

    @Column(name="autres_problemesF", columnDefinition="TEXT")
    private String autresProblemesF;

    @Column(name="objectifs_court_termeD", columnDefinition="TEXT")
    private String objectifsCourtTermeD;

    @Column(name="objectifs_court_termeI", columnDefinition="TEXT")
    private String objectifsCourtTermeI;

    @Column(name="objectifs_court_termeF", columnDefinition="TEXT")
    private String objectifsCourtTermeF;

    @Column(name="objectifs_moyen_termeD", columnDefinition="TEXT")
    private String objectifsMoyenTermeD;

    @Column(name="objectifs_moyen_termeI", columnDefinition="TEXT")
    private String objectifsMoyenTermeI;

    @Column(name="objectifs_moyen_termeF", columnDefinition="TEXT")
    private String objectifsMoyenTermeF;

    @Column(name="objectifs_long_termeD", columnDefinition="TEXT")
    private String objectifsLongTermeD;

    @Column(name="objectifs_long_termeI", columnDefinition="TEXT")
    private String objectifsLongTermeI;

    @Column(name="objectifs_long_termeF", columnDefinition="TEXT")
    private String objectifsLongTermeF;

    @Column(name="diagnosticD", columnDefinition="TEXT")
    private String diagnosticD;

    @Column(name="diagnosticI", columnDefinition="TEXT")
    private String diagnosticI;

    @Column(name="diagnosticF", columnDefinition="TEXT")
    private String diagnosticF;

    @Column(name="conseilsD", columnDefinition="TEXT")
    private String conseilsD;

    @Column(name="conseilsI", columnDefinition="TEXT")
    private String conseilsI;

    @Column(name="conseilsF", columnDefinition="TEXT")
    private String conseilsF;

    @Column(name="propositionsD", columnDefinition="TEXT")
    private String propositionsD;

    @Column(name="propositionsI", columnDefinition="TEXT")
    private String propositionsI;

    @Column(name="propositionsF", columnDefinition="TEXT")
    private String propositionsF;

    @Column(name="commentairesD", columnDefinition="TEXT")
    private String commentairesD;

    @Column(name="commentairesI", columnDefinition="TEXT")
    private String commentairesI;

    @Column(name="cotation")
    private String cotation;
    
    @NotNull
    @NotEmpty
    @Column(name="archive")
    private String archive = "Non";

    //Constructeurs
    public Resident() {
    }

    public Resident(String nom, String prenom, String dateNaissance, String sexe, String nSecuSociale, String medecinPrescripteur, int equipeKine) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.nSecuSociale = nSecuSociale;
        this.medecinPrescripteur = medecinPrescripteur;
        this.equipeKine = equipeKine;
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getnSecuSociale() {
        return nSecuSociale;
    }

    public String getMedecinPrescripteur() {
        return medecinPrescripteur;
    }

    public int getEquipeKine() {
        return equipeKine;
    }

    public String getDatePrescription() {
        return datePrescription;
    }

    public String getPrescriptionQuantitative() {
        return prescriptionQuantitative;
    }

    public String getRenouvellement() {
        return renouvellement;
    }

    public String getIndicationMedicale() {
        return indicationMedicale;
    }

    public int getNbProtocoleTherapeutique() {
        return nbProtocoleTherapeutique;
    }

    public int getRythmeSeances() {
        return rythmeSeances;
    }

    public String getTravailGroupe() {
        return travailGroupe;
    }

    public String getDatePremiereSeance() {
        return datePremiereSeance;
    }

    public String getTechniques() {
        return techniques;
    }

    public String getIntitules() {
        return intitules;
    }

    public String getDemarrageDate() {
        return demarrageDate;
    }

    public String getIntermediaireDate() {
        return intermediaireDate;
    }

    public String getFinaleDate() {
        return finaleDate;
    }

    public String getArticulairesD() {
        return articulairesD;
    }

    public String getArticulairesI() {
        return articulairesI;
    }

    public String getArticulairesF() {
        return articulairesF;
    }

    public String getForceMusculaireD() {
        return forceMusculaireD;
    }

    public String getForceMusculaireI() {
        return forceMusculaireI;
    }

    public String getForceMusculaireF() {
        return forceMusculaireF;
    }

    public String getDouleursD() {
        return douleursD;
    }

    public String getDouleursI() {
        return douleursI;
    }

    public String getDouleursF() {
        return douleursF;
    }

    public String getTrophiquesD() {
        return trophiquesD;
    }

    public String getTrophiquesI() {
        return trophiquesI;
    }

    public String getTrophiquesF() {
        return trophiquesF;
    }

    public String getBilanDeficitsFonctionnelsD() {
        return bilanDeficitsFonctionnelsD;
    }

    public String getBilanDeficitsFonctionnelsI() {
        return bilanDeficitsFonctionnelsI;
    }

    public String getBilanDeficitsFonctionnelsF() {
        return bilanDeficitsFonctionnelsF;
    }

    public String getAutresProblemesD() {
        return autresProblemesD;
    }

    public String getAutresProblemesI() {
        return autresProblemesI;
    }

    public String getAutresProblemesF() {
        return autresProblemesF;
    }

    public String getObjectifsCourtTermeD() {
        return objectifsCourtTermeD;
    }

    public String getObjectifsCourtTermeI() {
        return objectifsCourtTermeI;
    }

    public String getObjectifsCourtTermeF() {
        return objectifsCourtTermeF;
    }

    public String getObjectifsMoyenTermeD() {
        return objectifsMoyenTermeD;
    }

    public String getObjectifsMoyenTermeI() {
        return objectifsMoyenTermeI;
    }

    public String getObjectifsMoyenTermeF() {
        return objectifsMoyenTermeF;
    }

    public String getObjectifsLongTermeD() {
        return objectifsLongTermeD;
    }

    public String getObjectifsLongTermeI() {
        return objectifsLongTermeI;
    }

    public String getObjectifsLongTermeF() {
        return objectifsLongTermeF;
    }

    public String getDiagnosticD() {
        return diagnosticD;
    }

    public String getDiagnosticI() {
        return diagnosticI;
    }

    public String getDiagnosticF() {
        return diagnosticF;
    }

    public String getConseilsD() {
        return conseilsD;
    }

    public String getConseilsI() {
        return conseilsI;
    }

    public String getConseilsF() {
        return conseilsF;
    }

    public String getPropositionsD() {
        return propositionsD;
    }

    public String getPropositionsI() {
        return propositionsI;
    }

    public String getPropositionsF() {
        return propositionsF;
    }

    public String getCommentairesD() {
        return commentairesD;
    }

    public String getCommentairesI() {
        return commentairesI;
    }

    public String getCotation() {
        return cotation;
    }
    
    public String getArchive() {
        return archive;
    }

    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setNSecuSociale(String nSecuSociale) {
        this.nSecuSociale = nSecuSociale;
    }

    public void setMedecinPrescripteur(String medecinPrescripteur) {
        this.medecinPrescripteur = medecinPrescripteur;
    }

    public void setEquipeKine(int equipeKine) {
        this.equipeKine = equipeKine;
    }

    public void setDatePrescription(String datePrescription) {
        this.datePrescription = datePrescription;
    }

    public void setPrescriptionQuantitative(String prescriptionQuantitative) {
        this.prescriptionQuantitative = prescriptionQuantitative;
    }

    public void setRenouvellement(String renouvellement) {
        this.renouvellement = renouvellement;
    }

    public void setIndicationMedicale(String indicationMedicale) {
        this.indicationMedicale = indicationMedicale;
    }

    public void setNbProtocoleTherapeutique(int nbprotocoleTherapeutique) {
        this.nbProtocoleTherapeutique = nbprotocoleTherapeutique;
    }

    public void setRythmeSeances(int rythmeSences) {
        this.rythmeSeances = rythmeSences;
    }

    public void setTravailGroupe(String travailGroupe) {
        this.travailGroupe = travailGroupe;
    }

    public void setDatePremiereSeance(String datePremiereSeance) {
        this.datePremiereSeance = datePremiereSeance;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public void setIntitules(String intitules) {
        this.intitules = intitules;
    }

    public void setDemarrageDate(String demarrageDate) {
        this.demarrageDate = demarrageDate;
    }

    public void setIntermediaireDate(String intermediaireDate) {
        this.intermediaireDate = intermediaireDate;
    }

    public void setFinaleDate(String finaleDate) {
        this.finaleDate = finaleDate;
    }

    public void setArticulairesD(String articulairesD) {
        this.articulairesD = articulairesD;
    }

    public void setArticulairesI(String articulairesI) {
        this.articulairesI = articulairesI;
    }

    public void setArticulairesF(String articulairesF) {
        this.articulairesF = articulairesF;
    }

    public void setForceMusculaireD(String forceMusculaireD) {
        this.forceMusculaireD = forceMusculaireD;
    }

    public void setForceMusculaireI(String forceMusculaireI) {
        this.forceMusculaireI = forceMusculaireI;
    }

    public void setForceMusculaireF(String forceMusculaireF) {
        this.forceMusculaireF = forceMusculaireF;
    }

    public void setDouleursD(String douleursD) {
        this.douleursD = douleursD;
    }

    public void setDouleursI(String douleursI) {
        this.douleursI = douleursI;
    }

    public void setDouleursF(String douleursF) {
        this.douleursF = douleursF;
    }

    public void setTrophiquesD(String trophiquesD) {
        this.trophiquesD = trophiquesD;
    }

    public void setTrophiquesI(String trophiquesI) {
        this.trophiquesI = trophiquesI;
    }

    public void setTrophiquesF(String trophiquesF) {
        this.trophiquesF = trophiquesF;
    }

    public void setBilanDeficitsFonctionnelsD(String bilanDeficitsFonctionnelsD) {
        this.bilanDeficitsFonctionnelsD = bilanDeficitsFonctionnelsD;
    }

    public void setBilanDeficitsFonctionnelsI(String bilanDeficitsFonctionnelsI) {
        this.bilanDeficitsFonctionnelsI = bilanDeficitsFonctionnelsI;
    }

    public void setBilanDeficitsFonctionnelsF(String bilanDeficitsFonctionnelsF) {
        this.bilanDeficitsFonctionnelsF = bilanDeficitsFonctionnelsF;
    }

    public void setAutresProblemesD(String autresProblemesD) {
        this.autresProblemesD = autresProblemesD;
    }

    public void setAutresProblemesI(String autresProblemesI) {
        this.autresProblemesI = autresProblemesI;
    }

    public void setAutresProblemesF(String autresProblemesF) {
        this.autresProblemesF = autresProblemesF;
    }

    public void setObjectifsCourtTermeD(String ObjectifsCourtTermeD) {
        this.objectifsCourtTermeD = ObjectifsCourtTermeD;
    }

    public void setObjectifsCourtTermeI(String ObjectifsCourtTermeI) {
        this.objectifsCourtTermeI = ObjectifsCourtTermeI;
    }

    public void setObjectifsCourtTermeF(String ObjectifsCourtTermeF) {
        this.objectifsCourtTermeF = ObjectifsCourtTermeF;
    }

    public void setObjectifsMoyenTermeD(String ObjectifsMoyenTermeD) {
        this.objectifsMoyenTermeD = ObjectifsMoyenTermeD;
    }

    public void setObjectifsMoyenTermeI(String ObjectifsMoyenTermeI) {
        this.objectifsMoyenTermeI = ObjectifsMoyenTermeI;
    }

    public void setObjectifsMoyenTermeF(String ObjectifsMoyenTermeF) {
        this.objectifsMoyenTermeF = ObjectifsMoyenTermeF;
    }

    public void setObjectifsLongTermeD(String ObjectifsLongTermeD) {
        this.objectifsLongTermeD = ObjectifsLongTermeD;
    }

    public void setObjectifsLongTermeI(String ObjectifsLongTermeI) {
        this.objectifsLongTermeI = ObjectifsLongTermeI;
    }

    public void setObjectifsLongTermeF(String ObjectifsLongTermeF) {
        this.objectifsLongTermeF = ObjectifsLongTermeF;
    }

    public void setDiagnosticD(String diagnosticD) {
        this.diagnosticD = diagnosticD;
    }

    public void setDiagnosticI(String diagnosticI) {
        this.diagnosticI = diagnosticI;
    }

    public void setDiagnosticF(String diagnosticF) {
        this.diagnosticF = diagnosticF;
    }

    public void setConseilsD(String conseilsD) {
        this.conseilsD = conseilsD;
    }

    public void setConseilsI(String conseilsI) {
        this.conseilsI = conseilsI;
    }

    public void setConseilsF(String conseilsF) {
        this.conseilsF = conseilsF;
    }

    public void setPropositionsD(String propositionsD) {
        this.propositionsD = propositionsD;
    }

    public void setPropositionsI(String propositionsI) {
        this.propositionsI = propositionsI;
    }

    public void setPropositionsF(String propositionsF) {
        this.propositionsF = propositionsF;
    }

    public void setCommentairesD(String commentairesD) {
        this.commentairesD = commentairesD;
    }

    public void setCommentairesI(String commentairesI) {
        this.commentairesI = commentairesI;
    }

    public void setCotation(String cotation) {
        this.cotation = cotation;
    }
    
    public void setArchive(String archive) {
        this.archive = archive;
    }
}