function request() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
		document.getElementById('taquin').innerHTML=xhr.responseText;
	}
	};
	xhr.open("GET", "Resolution.php?", true);
	xhr.send(null);
}

function request2() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
		document.getElementById('taquin2').innerHTML=xhr.responseText;
	}
	};
	xhr.open("GET", "Statistique.php?", true);
	xhr.send(null);
}
function request3() {
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
	if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
		document.getElementById('taquin2').innerHTML=xhr.responseText;
	}
	};
	xhr.open("GET", "Statistique2.php?", true);
	xhr.send(null);
}