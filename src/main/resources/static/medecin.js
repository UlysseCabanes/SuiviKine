//Enregistrer les modifications du m�decin dans la BDD
function enregistrerModifications(event) {
    let id = event.id;
    let formId = "#formMedecin-" + id;
    //Appel ajax vers la fonction modifierMedecin du controller Medecin
    $.ajax({
        url: "modifierMedecin",
        type: "POST",
        //Donn�es extraites du formulaire formMedecin
        data:  $(formId).serialize()
    });
}

//Retirer le m�decin de la BDD
function retirerMedecin(event) {
    let id = event.id;
    let formId = "#formMedecin-" + id;
    //Appel ajax vers la fonction retirerMedecin du controller Medecin
    $.ajax({
        url: "retirerMedecin",
        type: "GET",
        //Donn�es extraites du formulaire formMedecin
        data:  $(formId).serialize()
    });
}