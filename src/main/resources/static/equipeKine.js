////------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Enregistrer les modifications de l'�quipe kin� dans la BDD
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function enregistrerModifications(event) {
        //R�cup�rer l'id du bouton qui a d�clench� la fonction
        let id = event.id;
        //R�cup�rer l'id du formulaire correspondant
        let formId = "#formEquipeKine-" + id;
        //Appel ajax vers la fonction modifierEquipekine du controller Equipekine
        $.ajax({
            url: "modifierEquipeKine",
            type: "POST",
            //Donn�es extraites du formulaire formEquipekine
            data:  $(formId).serialize(),
            //Recharger la page pour que les informations fausses soient ramplac�es par les anciennes 
            //informations si jamais elles ne correspondent pas aux crit�res de validation
            success : function() {
                        window.location.reload(true);
                    }
        });
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Retirer l'�quipe de la BDD
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    function retirerEquipeKine(event) {
        //Id du bouton qui a d�clench� la fonction
        let id = event.id;
        //Id du formulaire correspondant
        let formId = "#formEquipeKine-" + id;
        //Appel ajax vers la fonction retirerEquipeKine du controller EquipeKine
        $.ajax({
            url: "retirerEquipeKine",
            type: "GET",
            //Donn�es extraites du formulaire formEquipeKine
            data:  $(formId).serialize(),
            //Recharger la page pour que l'�quipe supprim�e ne soit plus visible
            success : function() {
                        window.location.reload(true);
                      }
        });
    }