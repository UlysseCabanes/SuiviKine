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
    
    public static int getAge(String dateNaissanceParam) throws ParseException {
        String jour = dateNaissanceParam.substring(0,2);
        String mois = dateNaissanceParam.substring(3,5);
        String annee = dateNaissanceParam.substring(6,10);
        String dateNaissance = annee + "-" + mois + "-" + jour;
        LocalDate localdate = LocalDate.parse(dateNaissance);
        int age = Period.between(localdate, LocalDate.now()).getYears();
        return age;
    }
}
