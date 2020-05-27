package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.text.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FicheDeSuiviController {

    @Autowired
    ResidentRepository residentRepository;
    
    @GetMapping("/voirFicheDeSuivi")
    public String voirFicheDeSuivi() {
        return "ficheDeSuivi";
    }

    @GetMapping("/creerFicheDeSuivi")
    public String creerFicheDeSuivi(
        @RequestParam("nom") String nom,
        @RequestParam("prenom") String prenom,
        @RequestParam("dateNaissance") String dateNaissance,
        @RequestParam("sexe") String sexe,
        @RequestParam("medecinPrescripteur") String medecinPrescripteur,
        @RequestParam("nSecu") String nSecu) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(dateNaissance);
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        String date2 = sdf2.format(date1);
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf3.parse(date2);
        Resident resident = new Resident(nom, prenom, date, sexe, medecinPrescripteur, nSecu);
        residentRepository.save(resident);
        return "ficheDeSuivi";
    }

}
