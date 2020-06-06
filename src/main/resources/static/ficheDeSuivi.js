//Récupérer toutes les "textarea"
let textarea = document.getElementsByTagName('textarea');
//Attribuer la fonction "autosize" à l'appui d'une touche sur toutes les "textarea"
for(let t of textarea) {
    t.addEventListener('keydown', autosize(t));
}
//Mettre les textarea à la bonne taille au chargement de la page
window.onload = function() {
    for(let t of textarea) {
        autosize(t);
    }
};
//Fonction pour ajuster la taille d'un élément à son contenu textuel          
function autosize(input){
    setTimeout(function(){ 
        input.style.cssText = 'height:auto; padding:0';
        input.style.cssText = 'height:' + input.scrollHeight + 'px';
    },0);
}

//Attribuer la fonction "masquer" au click sur tous les boutons "+" et "-"
for(let i = 2; i <= 4; i++) {
    document.getElementById("masquer" + i).addEventListener("click", masquer);
}
//Fonction pour masquer une colonne du tableau afin d'améliorer la lisibilité
function masquer(event) {
    //Savoir quel bouton a déclenché la fonction
    let id = event.target.id;
    let bouton = document.getElementById(id);
    //Chaque bouton servant à masquer
    let bouton2 = document.getElementById("masquer2");
    let bouton3 = document.getElementById("masquer3");
    let bouton4 = document.getElementById("masquer4");
    //Vérifier si c'est un bouton "-" (la colonne a une taille normale)
    if(bouton.value === "-") {
        //Savoir quelle colonne doit être masquée
        if(id === "masquer2") {
            //Rétrécir la colonne 1 et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "10%";
            document.getElementById("col" + 3).style.width = "37%";
            document.getElementById("col" + 4).style.width = "38%";
            //Attribuer la valeur "-" aux boutons des colonnes 2 et 3 et "+" au bouton de la colonne 1
            bouton2.value="+";
            bouton3.value="-";
            bouton4.value="-";
        }
        if(id === "masquer3") {
            //Rétrécir la colonne 2 et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "37%";
            document.getElementById("col" + 3).style.width = "10%";
            document.getElementById("col" + 4).style.width = "38%";
            //Attribuer la valeur "-" aux boutons des colonnes 1 et 3 et "+" au bouton de la colonne 2
            bouton2.value="-";
            bouton3.value="+";
            bouton4.value="-";
        }
        if(id === "masquer4") {
            //Rétrécir la colonne 3 et agrandir les deux autres 
            document.getElementById("col" + 2).style.width = "37%";
            document.getElementById("col" + 3).style.width = "38%";
            document.getElementById("col" + 4).style.width = "10%";
            //Attribuer la valeur "-" aux boutons des colonnes 1 et 2 et "+" au bouton de la colonne 3
            bouton2.value="-";
            bouton3.value="-";
            bouton4.value="+";
        } 
    }
    //Sinon, c'est un bouton "+" (la colonne est rétrécie donc il faut l'agrandir et rétrécir les autres)
    else{
        //Donner aux trois colonnes une largeur standard
        document.getElementById("col" + 2).style.width = "28%";
        document.getElementById("col" + 3).style.width = "28%";
        document.getElementById("col" + 4).style.width = "29%";
        //Attribuer la valeur "-" aux boutons (car toutes les colonnes ont une largeur standard)
        bouton2.value="-";
        bouton3.value="-";
        bouton4.value="-";
    }
}
//Atribuer la fonction imprimer() au bouton d'impression
document.getElementById("boutonImpression").addEventListener("click", imprimer);

//Fonction d'impression de la fiche
function imprimer() {
    //Donner aux trois colonnes une largeur standard
    document.getElementById("col" + 2).style.width = "33%";
    document.getElementById("col" + 3).style.width = "33%";
    document.getElementById("col" + 4).style.width = "34%";
    //Imprimer la fiche
    window.print();
}

//Récupérer tous les input de type date
let date = document.querySelectorAll('input[type=date]');
for (let d of date) {
    //Appeler la fonction enregisterModifications() lorsque l'on change la valeur de l'input
    d.addEventListener('change', enregistrerModifications);
}
//Récupérer tous les input de type number
let number = document.querySelectorAll('input[type=number]');
for (let n of number) {
    //Appeler la fonction enregisterModifications() lorsque l'on change la valeur de l'input
    n.addEventListener('change', enregistrerModifications);
    //Appeler la fonction enregisterModifications() lorsque l'on relâche une touche dans l'input
    n.addEventListener('keyup', enregistrerModifications);
    //Appeler la fonction enregisterModifications() lorsque l'on quitte l'input
    n.addEventListener('blur', enregistrerModifications);
}
//Récupérer tous les input de type text
let text = document.querySelectorAll('input[type=text]');
for (let t of text) {
    //Appeler la fonction enregisterModifications() lorsque l'on relâche une touche dans l'input
    t.addEventListener('keyup', enregistrerModifications);
    //Appeler la fonction enregisterModifications() lorsque l'on quitte l'input
    t.addEventListener('blur', enregistrerModifications);
}
//Récupérer tous les input de type radio
let radio = document.querySelectorAll('input[type=radio]');
for (let r of radio) {
    //Appeler la fonction enregisterModifications() lorsque l'on change la valeur de l'input
    r.addEventListener('change', enregistrerModifications);
}
//Récupérer tous les input de type textarea
for (let ta of textarea) {
    //Appeler la fonction enregisterModifications() lorsque l'on relâche une touche dans l'input
    ta.addEventListener('keyup', enregistrerModifications);
    //Appeler la fonction enregisterModifications() lorsque l'on quitte l'input
    ta.addEventListener('blur', enregistrerModifications);
}

//Enregistrer les modifications de la fiche dans la BDD
function enregistrerModifications() {
    //Appel ajax vers la fonction modifierFicheDeSuivi du controller FicheDeSuivi
    $.ajax({
        url: "modifierFicheDeSuivi",
        type: "POST",
        //Données extraites du formulaire formFicheDeSuivi
        data:  $('#formFicheDeSuivi').serialize()
    });
}