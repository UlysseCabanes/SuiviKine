//R�cup�rer tous les boutons modifier
let boutonsModifier = document.getElementsByClassName("boutonModifier");
for (let bm of boutonsModifier) {
    //Appeler la fonction enregistrerModifications() au click sur le bouton
    bm.addEventListener("click", enregistrerModifications);
}

//Enregistrer les modifications de l'�quipe kin� dans la BDD
function enregistrerModifications(event) {
    //R�cup�rer l'id du bouton qui a d�clench� la fonction
    let id = event.target.id;
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