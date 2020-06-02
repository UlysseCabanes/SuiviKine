package jardinsdeflore.suivikine.entity;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ResidentId.class)
public class Resident implements Serializable {

    //Attributs
    @Id
    @NotNull
    @NotEmpty
    private String nom;

    @Id
    @NotNull
    @NotEmpty
    private String prenom;

    @Id
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name="date_naissance")
    private Date dateNaissance = new Date();

    @NotNull
    @NotEmpty
    private String sexe;

    @NotNull
    @NotEmpty
    private String n_secu_sociale = "000000000000000";

    @NotNull
    @NotEmpty
    private String medecin_prescripteur;

    @NotNull
    @Column(name="equipe_kine")
    private int equipeKine;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_prescription;

    private String prescription_quantitative;

    private String renouvellement;

    private String indication_medicale;

    private int nb_protocole_therapeutique;

    private int rythme_seances;

    private String lieu_seances;

    private String travail_groupe;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date_premiere_seance;

    private String techniques;

    private String intitules;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date demarrage_date;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date intermediaire_date;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finale_date;

    @Column(columnDefinition="TEXT")
    private String articulairesD;

    @Column(columnDefinition="TEXT")
    private String articulairesI;

    @Column(columnDefinition="TEXT")
    private String articulairesF;

    @Column(columnDefinition="TEXT")
    private String force_musculaireD;

    @Column(columnDefinition="TEXT")
    private String force_musculaireI;

    @Column(columnDefinition="TEXT")
    private String force_musculaireF;

    @Column(columnDefinition="TEXT")
    private String douleursD;

    @Column(columnDefinition="TEXT")
    private String douleursI;

    @Column(columnDefinition="TEXT")
    private String douleursF;

    @Column(columnDefinition="TEXT")
    private String trophiquesD;

    @Column(columnDefinition="TEXT")
    private String trophiquesI;

    @Column(columnDefinition="TEXT")
    private String trophiquesF;

    @Column(columnDefinition="TEXT")
    private String bilan_deficits_fonctionnelsD;

    @Column(columnDefinition="TEXT")
    private String bilan_deficits_fonctionnelsI;

    @Column(columnDefinition="TEXT")
    private String bilan_deficits_fonctionnelsF;

    @Column(columnDefinition="TEXT")
    private String autres_problemesD;

    @Column(columnDefinition="TEXT")
    private String autres_problemesI;

    @Column(columnDefinition="TEXT")
    private String autres_problemesF;

    @Column(columnDefinition="TEXT")
    private String objectifs_court_termeD;

    @Column(columnDefinition="TEXT")
    private String objectifs_court_termeI;

    @Column(columnDefinition="TEXT")
    private String objectifs_court_termeF;

    @Column(columnDefinition="TEXT")
    private String objectifs_moyen_termeD;

    @Column(columnDefinition="TEXT")
    private String objectifs_moyen_termeI;

    @Column(columnDefinition="TEXT")
    private String objectifs_moyen_termeF;

    @Column(columnDefinition="TEXT")
    private String objectifs_long_termeD;

    @Column(columnDefinition="TEXT")
    private String objectifs_long_termeI;

    @Column(columnDefinition="TEXT")
    private String objectifs_long_termeF;

    @Column(columnDefinition="TEXT")
    private String diagnosticD;

    @Column(columnDefinition="TEXT")
    private String diagnosticI;

    @Column(columnDefinition="TEXT")
    private String diagnosticF;

    @Column(columnDefinition="TEXT")
    private String conseilsD;

    @Column(columnDefinition="TEXT")
    private String conseilsI;

    @Column(columnDefinition="TEXT")
    private String conseilsF;

    @Column(columnDefinition="TEXT")
    private String propositionsD;

    @Column(columnDefinition="TEXT")
    private String propositionsI;

    @Column(columnDefinition="TEXT")
    private String propositionsF;

    @Column(columnDefinition="TEXT")
    private String commentairesD;

    @Column(columnDefinition="TEXT")
    private String commentairesI;

    private String cotation;

    //Constructeurs
    public Resident() {
    }

    public Resident(String nom, String prenom, Date dateNaissance, String sexe, String nSecuSociale, String medecinPrescripteur, int equipeKine) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.n_secu_sociale = nSecuSociale;
        this.medecin_prescripteur = medecinPrescripteur;
        this.equipeKine = equipeKine;
    }

    //Getter
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public String getnSecuSociale() {
        return n_secu_sociale;
    }

    public String getMedecinPrescripteur() {
        return medecin_prescripteur;
    }

    public int getEquipekine() {
        return equipeKine;
    }

    public Date getDatePrescription() {
        return date_prescription;
    }

    public String getPrescriptionQuantitative() {
        return prescription_quantitative;
    }

    public String getRenouvellement() {
        return renouvellement;
    }

    public String getIndicationMedicale() {
        return indication_medicale;
    }

    public int getNbprotocoleTherapeutique() {
        return nb_protocole_therapeutique;
    }

    public int getRythmeSences() {
        return rythme_seances;
    }

    public String getLieuseances() {
        return lieu_seances;
    }

    public String getTravailGroupe() {
        return travail_groupe;
    }

    public Date getDatePremiereSeance() {
        return date_premiere_seance;
    }

    public String getTechniques() {
        return techniques;
    }

    public String getIntitules() {
        return intitules;
    }

    public Date getDemarrageDate() {
        return demarrage_date;
    }

    public Date getIntermediaireDate() {
        return intermediaire_date;
    }

    public Date getFinaleDate() {
        return finale_date;
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
        return force_musculaireD;
    }

    public String getForceMusculaireI() {
        return force_musculaireI;
    }

    public String getForceMusculaireF() {
        return force_musculaireF;
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
        return bilan_deficits_fonctionnelsD;
    }

    public String getBilanDeficitsFonctionnelsI() {
        return bilan_deficits_fonctionnelsI;
    }

    public String getBilanDeficitsFonctionnelsF() {
        return bilan_deficits_fonctionnelsF;
    }

    public String getAutresProblemesD() {
        return autres_problemesD;
    }

    public String getAutresProblemesI() {
        return autres_problemesI;
    }

    public String getAutresProblemesF() {
        return autres_problemesF;
    }

    public String getObjectifsCourtTermeD() {
        return objectifs_court_termeD;
    }

    public String getObjectifsCourtTermeI() {
        return objectifs_court_termeI;
    }

    public String getObjectifsCourtTermeF() {
        return objectifs_court_termeF;
    }

    public String getObjectifsMoyenTermeD() {
        return objectifs_moyen_termeD;
    }

    public String getObjectifsMoyenTermeI() {
        return objectifs_moyen_termeI;
    }

    public String getObjectifsMoyenTermeF() {
        return objectifs_moyen_termeF;
    }

    public String getObjectifsLongTermeD() {
        return objectifs_long_termeD;
    }

    public String getObjectifsLongTermeI() {
        return objectifs_long_termeI;
    }

    public String getObjectifsLongTermeF() {
        return objectifs_long_termeF;
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

    //Setter
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public void setnSecuSociale(String nSecuSociale) {
        this.n_secu_sociale = nSecuSociale;
    }

    public void setMedecinPrescripteur(String medecinPrescripteur) {
        this.medecin_prescripteur = medecinPrescripteur;
    }

    public void setEquipekine(int equipekine) {
        this.equipeKine = equipekine;
    }

    public void setDatePrescription(Date datePrescription) {
        this.date_prescription = datePrescription;
    }

    public void setPrescriptionQuantitative(String prescriptionQuantitative) {
        this.prescription_quantitative = prescriptionQuantitative;
    }

    public void setRenouvellement(String renouvellement) {
        this.renouvellement = renouvellement;
    }

    public void setIndicationMedicale(String indicationMedicale) {
        this.indication_medicale = indicationMedicale;
    }

    public void setNbprotocoleTherapeutique(int nbprotocoleTherapeutique) {
        this.nb_protocole_therapeutique = nbprotocoleTherapeutique;
    }

    public void setRythmeSences(int rythmeSences) {
        this.rythme_seances = rythmeSences;
    }

    public void setLieuseances(String lieuseances) {
        this.lieu_seances = lieuseances;
    }

    public void setTravailGroupe(String travailGroupe) {
        this.travail_groupe = travailGroupe;
    }

    public void setDatePremiereSeance(Date datePremiereSeance) {
        this.date_premiere_seance = datePremiereSeance;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public void setIntitules(String intitules) {
        this.intitules = intitules;
    }

    public void setDemarrageDate(Date demarrageDate) {
        this.demarrage_date = demarrageDate;
    }

    public void setIntermediaireDate(Date intermediaireDate) {
        this.intermediaire_date = intermediaireDate;
    }

    public void setFinaleDate(Date finaleDate) {
        this.finale_date = finaleDate;
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
        this.force_musculaireD = forceMusculaireD;
    }

    public void setForceMusculaireI(String forceMusculaireI) {
        this.force_musculaireI = forceMusculaireI;
    }

    public void setForceMusculaireF(String forceMusculaireF) {
        this.force_musculaireF = forceMusculaireF;
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
        this.bilan_deficits_fonctionnelsD = bilanDeficitsFonctionnelsD;
    }

    public void setBilanDeficitsFonctionnelsI(String bilanDeficitsFonctionnelsI) {
        this.bilan_deficits_fonctionnelsI = bilanDeficitsFonctionnelsI;
    }

    public void setBilanDeficitsFonctionnelsF(String bilanDeficitsFonctionnelsF) {
        this.bilan_deficits_fonctionnelsF = bilanDeficitsFonctionnelsF;
    }

    public void setAutresProblemesD(String autresProblemesD) {
        this.autres_problemesD = autresProblemesD;
    }

    public void setAutresProblemesI(String autresProblemesI) {
        this.autres_problemesI = autresProblemesI;
    }

    public void setAutresProblemesF(String autresProblemesF) {
        this.autres_problemesF = autresProblemesF;
    }

    public void setObjectifsCourtTermeD(String ObjectifsCourtTermeD) {
        this.objectifs_court_termeD = ObjectifsCourtTermeD;
    }

    public void setObjectifsCourtTermeI(String ObjectifsCourtTermeI) {
        this.objectifs_court_termeI = ObjectifsCourtTermeI;
    }

    public void setObjectifsCourtTermeF(String ObjectifsCourtTermeF) {
        this.objectifs_court_termeF = ObjectifsCourtTermeF;
    }

    public void setObjectifsMoyenTermeD(String ObjectifsMoyenTermeD) {
        this.objectifs_moyen_termeD = ObjectifsMoyenTermeD;
    }

    public void setObjectifsMoyenTermeI(String ObjectifsMoyenTermeI) {
        this.objectifs_moyen_termeI = ObjectifsMoyenTermeI;
    }

    public void setObjectifsMoyenTermeF(String ObjectifsMoyenTermeF) {
        this.objectifs_moyen_termeF = ObjectifsMoyenTermeF;
    }

    public void setObjectifsLongTermeD(String ObjectifsLongTermeD) {
        this.objectifs_long_termeD = ObjectifsLongTermeD;
    }

    public void setObjectifsLongTermeI(String ObjectifsLongTermeI) {
        this.objectifs_long_termeI = ObjectifsLongTermeI;
    }

    public void setObjectifsLongTermeF(String ObjectifsLongTermeF) {
        this.objectifs_long_termeF = ObjectifsLongTermeF;
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
}