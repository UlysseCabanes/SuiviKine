////----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Enregistrer les modifications du m�decin dans la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function enregistrerModifications(event) {
        //Id du bouton qui a d�clench� la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formMedecin-" + id;
        //Appel ajax vers la fonction modifierMedecin du controller Medecin
        $.ajax({
            url: "modifierMedecin",
            type: "POST",
            //Donn�es extraites du formulaire formMedecin
            data:  $(formId).serialize()
        });
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Retirer le m�decin de la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function retirerMedecin(event) {
        //Id du bouton qui a d�clench� la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formMedecin-" + id;
        //Appel ajax vers la fonction retirerMedecin du controller Medecin
        $.ajax({
            url: "retirerMedecin",
            type: "GET",
            //Donn�es extraites du formulaire formMedecin
            data:  $(formId).serialize(),
            //Recharger la page pour que le m�decin supprim� ne soit plus visible
            success : function() {
                        window.location.reload(true);
                      }
        });
    }