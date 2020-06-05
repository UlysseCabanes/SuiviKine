/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jardinsdeflore.suivikine.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;

public class UtilDate {
    //Transforme une date de type String du format ddMMyyyy au format yyyyMMdd
    public static String getDateFormatyyyyMMdd(String dateNaissanceParam) throws ParseException {
        String jour = dateNaissanceParam.substring(0,2);
        String mois = dateNaissanceParam.substring(3,5);
        String annee = dateNaissanceParam.substring(6);
        String dateNaissance = annee + "-" + mois + "-" + jour;
        return dateNaissance;
    }
    
    //Transforme une date de type String du format yyyyMMdd au format ddMMyyyy
    public static String getDateFormatddMMyyyy(String dateNaissanceParam) throws ParseException {
        String jour = dateNaissanceParam.substring(8);
        String mois = dateNaissanceParam.substring(5,7);
        String annee = dateNaissanceParam.substring(0,4);
        String dateNaissance = jour + "/" + mois + "/" + annee;
        return dateNaissance;
    }
    
    //Renvoie l'âge en années en fonction de la date de naissance (au format dd/MM/yyyy) par rapport à la date actuelle
    public static int getAge(String dateNaissanceParam) throws ParseException {
        String dateNaissance = getDateFormatyyyyMMdd(dateNaissanceParam);
        LocalDate localdate = LocalDate.parse(dateNaissance);
        int age = Period.between(localdate, LocalDate.now()).getYears();
        return age;
    }
}
