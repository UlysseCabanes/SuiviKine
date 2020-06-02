package jardinsdeflore.suivikine.controller;

import jardinsdeflore.suivikine.composite.domains.ResidentId;
import jardinsdeflore.suivikine.entity.Resident;
import jardinsdeflore.suivikine.repository.ResidentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FicheDeSuiviController {

    @Autowired
    ResidentRepository residentRepository;
    
    @GetMapping("/voirFicheDeSuivi")
    public String voirFicheDeSuivi(
        @RequestParam("nom") String nomParam,
        @RequestParam("prenom") String prenomParam,
        @RequestParam("dateNaissance") String dateNaissanceParam,
        Model model) throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateNaissanceParam);
        Resident resident = residentRepository.findResidentByNomAndPrenomAndDateNaissance(nomParam, prenomParam, date);
        String nom = resident.getNom();
        model.addAttribute("nom", nom);
        return "ficheDeSuivi";
    }
}