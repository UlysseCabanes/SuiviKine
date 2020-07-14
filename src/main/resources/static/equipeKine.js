////------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Enregistrer les modifications de l'équipe kiné dans la BDD
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function enregistrerModifications(event) {
        //Récupérer l'id du bouton qui a déclenché la fonction
        let id = event.id;
        //Récupérer l'id du formulaire correspondant
        let formId = "#formEquipeKine-" + id;
        //Appel ajax vers la fonction modifierEquipekine du controller Equipekine
        $.ajax({
            url: "modifierEquipeKine",
            type: "POST",
            //Données extraites du formulaire formEquipekine
            data:  $(formId).serialize(),
            //Recharger la page pour que les informations fausses soient ramplacées par les anciennes 
            //informations si jamais elles ne correspondent pas aux critères de validation
            success : function() {
                        window.location.reload(true);
                    }
        });
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Retirer l'équipe de la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function retirerEquipeKine(event) {
        //Id du bouton qui a déclenché la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formEquipeKine-" + id;
        //Appel ajax vers la fonction retirerEquipeKine du controller EquipeKine
        $.ajax({
            url: "retirerEquipeKine",
            type: "GET",
            //Données extraites du formulaire formEquipeKine
            data:  $(formId).serialize(),
            //Recharger la page pour que l'équipe supprimée ne soit plus visible
            success : function() {
                        window.location.reload(true);
                      }
        });
    }