////----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Enregistrer les modifications du médecin dans la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function enregistrerModifications(event) {
        //Id du bouton qui a déclenché la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formMedecin-" + id;
        //Appel ajax vers la fonction modifierMedecin du controller Medecin
        $.ajax({
            url: "modifierMedecin",
            type: "POST",
            //Données extraites du formulaire formMedecin
            data:  $(formId).serialize()
        });
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Retirer le médecin de la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function retirerMedecin(event) {
        //Id du bouton qui a déclenché la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formMedecin-" + id;
        //Appel ajax vers la fonction retirerMedecin du controller Medecin
        $.ajax({
            url: "retirerMedecin",
            type: "GET",
            //Données extraites du formulaire formMedecin
            data:  $(formId).serialize(),
            //Recharger la page pour que le médecin supprimé ne soit plus visible
            success : function() {
                        window.location.reload(true);
                      }
        });
    }