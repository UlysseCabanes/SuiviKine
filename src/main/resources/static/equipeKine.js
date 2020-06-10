//Récupérer tous les boutons modifier
let boutonsModifier = document.getElementsByClassName("boutonModifier");
for (let bm of boutonsModifier) {
    //Appeler la fonction enregistrerModifications() au click sur le bouton
    bm.addEventListener("click", enregistrerModifications);
}

//Enregistrer les modifications de l'équipe kiné dans la BDD
function enregistrerModifications(event) {
    //Récupérer l'id du bouton qui a déclenché la fonction
    let id = event.target.id;
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