/**
 * Diese Methode prueft die Eingabendaten für das Formular in aendern.jsp und in hinzufuegen.jsp  
 */
function checkInputFields() {
	const fid = document.getElementById("fid");
	const kennung = document.getElementById("kennung");
	const ort = document.getElementById("ort");
	const email = document.getElementById("email");
	const bundesland = document.getElementById("bundesland");
	var fehler = false;

	// Ueberpruefen ob die Eingabe Nummer ist
	if (fid.value.length == 0 || isNaN(fid.value.valueOf())) {
		fid.style.backgroundColor = "red";
		fehler = true;
	}
	
	// Ueberpruefen ob die Eingabe nich leer ist
	fehler |= checkInputLength(kennung);
	fehler |= checkInputLength(ort);

	// Ueberpruefen ob die Eingabe nich leer ist und enthaelt @ als Validierer
	if (email.value.length < 7 || email.value.indexOf("@") == -1) {
		email.style.backgroundColor = "red";
		fehler = true;
	}
	
	// Ueberpruefen ob das Bundesland nich leer ist
	if (!bundesland.value) {
		bundesland.style.backgroundColor = "red";
		fehler = true;
	}

	if (fehler) {
		errorMessage(fid, kennung, ort, email, bundesland)
		return false;
	}
	return true;
}

function checkInputLength(input){
	var vInput = input.value;
	if (vInput.length < 3) {
		input.style.backgroundColor = "red";
		return true;
	}
	return false;
}

function errorMessage(input1, input2, input3, input4, input5){
	window.alert("Bitte Eingaben überprüfen!");
	input1.style.backgroundColor = "white";
	input2.style.backgroundColor = "white";
	input3.style.backgroundColor = "white";
	input4.style.backgroundColor = "white";
	input5.style.backgroundColor = "white";	
}


