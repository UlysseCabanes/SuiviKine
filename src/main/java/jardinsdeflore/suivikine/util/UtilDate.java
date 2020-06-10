package jardinsdeflore.suivikine.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

public class UtilDate {
    
    //Transforme une date de type String du format dd/MM/yyyy au format yyyy-MM-dd (ex: 28/05/1999 => 1999-05-28)
    public static String getDateFormatyyyyMMdd(String dateNaissanceParam) throws ParseException {
        //Jour = caractères aux index 0 et 1 du paramètre
        String jour = dateNaissanceParam.substring(0,2);
        //Mois = caractères aux index 3 et 4 du paramètre
        String mois = dateNaissanceParam.substring(3,5);
        //Annee = caractères aux index 6, 7, 8 et 9 du paramètre
        String annee = dateNaissanceParam.substring(6);
        //Concaténer les trois sous-parties de la date en ajoutant des tirets au milieu
        String dateNaissance = annee + "-" + mois + "-" + jour;
        return dateNaissance;
    }
    
    //Transforme une date de type String du format yyyy-MM-dd au format dd/MM/yyyy (ex: 1999-05-28 => 28/05/1999)
    public static String getDateFormatddMMyyyy(String dateNaissanceParam) throws ParseException {
        //Jour = caractères aux index 8 et 9 du paramètre
        String jour = dateNaissanceParam.substring(8);
        //Mois = caractères aux index 5 et 6 du paramètre
        String mois = dateNaissanceParam.substring(5,7);
        //Annee = caractères aux index 0, 1, 2 et 3 du paramètre
        String annee = dateNaissanceParam.substring(0,4);
        //Concaténer les trois sous-parties de la date en ajoutant des slashs au milieu
        String dateNaissance = jour + "/" + mois + "/" + annee;
        return dateNaissance;
    }
    
    //Renvoie l'âge en années en fonction de la date de naissance (au format dd/MM/yyyy) par rapport à la date actuelle
    public static int getAge(String dateNaissanceParam) throws ParseException {
        //Formater la date en yyyy-MM-dd
        String dateNaissance = getDateFormatyyyyMMdd(dateNaissanceParam);
        //Créer une LocalDate avec comme valeur la date de naissance au format yyyy-MM--dd
        LocalDate localdate = LocalDate.parse(dateNaissance);
        //Age (entier) = nombre d'années entre la date de naissance et la date actuelle
        int age = Period.between(localdate, LocalDate.now()).getYears();
        return age;
    }
}