package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        int equipeKine = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateNaissance);
        Resident resident = new Resident(nom, prenom, date, sexe, nSecu, medecinPrescripteur, equipeKine);
        /*
        if (residentRepository.existsByNomPrenomDate_naissance(nom, prenom, date)){
            String redirect= "redirect:/login";
            return redirect;
        }
        else {
        */
            residentRepository.save(resident);
            return "ficheDeSuivi";
        //}
    }
}