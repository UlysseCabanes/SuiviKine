//Récupérer tous les boutons modifier
let boutonsModifier = document.getElementsByClassName("boutonModifier");
for (let bm of boutonsModifier) {
    //Appeler la fonction enregistrerModifications() au click sur le bouton
    bm.addEventListener("click", enregistrerModifications);
}

//Enregistrer les modifications de l'équipe kiné dans la BDD
function enregistrerModifications(event) {
    let id = event.target.id;
    let formId = "#formEquipeKine-" + id;
    console.log(formId);
    //Appel ajax vers la fonction modifierEquipekine du controller Equipekine
    $.ajax({
        url: "modifierEquipeKine",
        type: "POST",
        //Données extraites du formulaire formEquipekine
        data:  $(formId).serialize()
    });
}

//Enregistrer les modifications de l'équipe kiné dans la BDD
function ajouterEquipeKine() {
    //Appel ajax vers la fonction modifierEquipekine du controller Equipekine
    $.ajax({
        url: "ajouterEquipeKine",
        type: "POST",
        //Données extraites du formulaire formEquipekine
        data:  {login : "login", mdp : "mdp"}
    });
}