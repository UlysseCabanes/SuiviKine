package jardinsdeflore.suivikine.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Resident extends AbstractEntity implements Serializable {

    //Attributs
    @NotNull
    @NotEmpty
    private String nom;

    @NotNull
    @NotEmpty
    private String prenom;

    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;

    @NotNull
    @NotEmpty
    private String sexe;

    @NotNull
    @NotEmpty
    private String nSecuSociale;

    @NotNull
    @NotEmpty
    private String medecinPrescripteur;

    private int equipekine;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePrescription;

    private String prescriptionQuantitative;

    private String renouvellement;

    private String indicationMedicale;

    private int nbprotocoleTherapeutique;

    private int rythmeSences;

    private String lieuseances;

    private String travailGroupe;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePremiereSeance;

    private String techniques;

    private String intitules;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date demarrageDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date intermediaireDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date finaleDate;

    private String articulairesD;

    private String articulairesI;

    private String articulairesF;

    private String forceMusculaireD;

    private String forceMusculaireI;

    private String forceMusculaireF;

    private String douleursD;

    private String douleursI;

    private String douleursF;

    private String trophiquesD;

    private String trophiquesI;

    private String trophiquesF;

    private String bilanDeficitsFonctionnelsD;

    private String bilanDeficitsFonctionnelsI;

    private String bilanDeficitsFonctionnelsF;

    private String autresProblemesD;

    private String autresProblemesI;

    private String autresProblemesF;

    private String ObjectifsCourtTermeD;

    private String ObjectifsCourtTermeI;

    private String ObjectifsCourtTermeF;

    private String ObjectifsMoyenTermeD;

    private String ObjectifsMoyenTermeI;

    private String ObjectifsMoyenTermeF;

    private String ObjectifsLongTermeD;

    private String ObjectifsLongTermeI;

    private String ObjectifsLongTermeF;

    private String diagnosticD;

    private String diagnosticI;

    private String diagnosticF;

    private String conseilsD;

    private String conseilsI;

    private String conseilsF;

    private String propositionsD;

    private String propositionsI;

    private String propositionsF;

    private String commentairesD;

    private String commentairesI;

    private String cotation;

    //Constructeurs
    public Resident() {
    }

    public Resident(String nom, String prenom, Date dateNaissance, String sexe, String medecinPrescripteur, String nSecuSociale) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.medecinPrescripteur = medecinPrescripteur;
        this.nSecuSociale = nSecuSociale;
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
        return nSecuSociale;
    }

    public String getMedecinPrescripteur() {
        return medecinPrescripteur;
    }

    public int getEquipekine() {
        return equipekine;
    }

    public Date getDatePrescription() {
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

    public int getNbprotocoleTherapeutique() {
        return nbprotocoleTherapeutique;
    }

    public int getRythmeSences() {
        return rythmeSences;
    }

    public String getLieuseances() {
        return lieuseances;
    }

    public String getTravailGroupe() {
        return travailGroupe;
    }

    public Date getDatePremiereSeance() {
        return datePremiereSeance;
    }

    public String getTechniques() {
        return techniques;
    }

    public String getIntitules() {
        return intitules;
    }

    public Date getDemarrageDate() {
        return demarrageDate;
    }

    public Date getIntermediaireDate() {
        return intermediaireDate;
    }

    public Date getFinaleDate() {
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
        return ObjectifsCourtTermeD;
    }

    public String getObjectifsCourtTermeI() {
        return ObjectifsCourtTermeI;
    }

    public String getObjectifsCourtTermeF() {
        return ObjectifsCourtTermeF;
    }

    public String getObjectifsMoyenTermeD() {
        return ObjectifsMoyenTermeD;
    }

    public String getObjectifsMoyenTermeI() {
        return ObjectifsMoyenTermeI;
    }

    public String getObjectifsMoyenTermeF() {
        return ObjectifsMoyenTermeF;
    }

    public String getObjectifsLongTermeD() {
        return ObjectifsLongTermeD;
    }

    public String getObjectifsLongTermeI() {
        return ObjectifsLongTermeI;
    }

    public String getObjectifsLongTermeF() {
        return ObjectifsLongTermeF;
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
        this.nSecuSociale = nSecuSociale;
    }

    public void setMedecinPrescripteur(String medecinPrescripteur) {
        this.medecinPrescripteur = medecinPrescripteur;
    }

    public void setEquipekine(int equipekine) {
        this.equipekine = equipekine;
    }

    public void setDatePrescription(Date datePrescription) {
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

    public void setNbprotocoleTherapeutique(int nbprotocoleTherapeutique) {
        this.nbprotocoleTherapeutique = nbprotocoleTherapeutique;
    }

    public void setRythmeSences(int rythmeSences) {
        this.rythmeSences = rythmeSences;
    }

    public void setLieuseances(String lieuseances) {
        this.lieuseances = lieuseances;
    }

    public void setTravailGroupe(String travailGroupe) {
        this.travailGroupe = travailGroupe;
    }

    public void setDatePremiereSeance(Date datePremiereSeance) {
        this.datePremiereSeance = datePremiereSeance;
    }

    public void setTechniques(String techniques) {
        this.techniques = techniques;
    }

    public void setIntitules(String intitules) {
        this.intitules = intitules;
    }

    public void setDemarrageDate(Date demarrageDate) {
        this.demarrageDate = demarrageDate;
    }

    public void setIntermediaireDate(Date intermediaireDate) {
        this.intermediaireDate = intermediaireDate;
    }

    public void setFinaleDate(Date finaleDate) {
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
        this.ObjectifsCourtTermeD = ObjectifsCourtTermeD;
    }

    public void setObjectifsCourtTermeI(String ObjectifsCourtTermeI) {
        this.ObjectifsCourtTermeI = ObjectifsCourtTermeI;
    }

    public void setObjectifsCourtTermeF(String ObjectifsCourtTermeF) {
        this.ObjectifsCourtTermeF = ObjectifsCourtTermeF;
    }

    public void setObjectifsMoyenTermeD(String ObjectifsMoyenTermeD) {
        this.ObjectifsMoyenTermeD = ObjectifsMoyenTermeD;
    }

    public void setObjectifsMoyenTermeI(String ObjectifsMoyenTermeI) {
        this.ObjectifsMoyenTermeI = ObjectifsMoyenTermeI;
    }

    public void setObjectifsMoyenTermeF(String ObjectifsMoyenTermeF) {
        this.ObjectifsMoyenTermeF = ObjectifsMoyenTermeF;
    }

    public void setObjectifsLongTermeD(String ObjectifsLongTermeD) {
        this.ObjectifsLongTermeD = ObjectifsLongTermeD;
    }

    public void setObjectifsLongTermeI(String ObjectifsLongTermeI) {
        this.ObjectifsLongTermeI = ObjectifsLongTermeI;
    }

    public void setObjectifsLongTermeF(String ObjectifsLongTermeF) {
        this.ObjectifsLongTermeF = ObjectifsLongTermeF;
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
