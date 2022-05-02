<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<button onclick="init()">MOSTRAR</button>
<div id="info"></div>
<script type="text/javascript"
	src="http://ajax.microsoft.com/ajax/jQuery/jquery-1.4.4.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
		llenarDepartamentos();
		
		function loadJSON(callback) {
			var xobj = new XMLHttpRequest();
			xobj.overrideMimeType("application/json");
			xobj.open("GET", "colombia.json", true); // Reemplaza colombia-json.json con el nombre que le hayas puesto
			xobj.onreadystatechange = function () {
				if (xobj.readyState == 4 && xobj.status == "200") {
					callback(xobj.responseText);
				}
			};
			xobj.send(null);
		}
		function llenarDepartamentos() {
			loadJSON(function (response) {
				// Parse JSON string into object
				
				var datos = JSON.parse(response);
				
				for(var i in datos){
					$('#departamento').prepend("<option value='"+datos[(datos.length-1)-i].id+"' >"+datos[(datos.length-1)-i].departamento+"</option>");
					if(i==21){
					console.log(datos[i].ciudades);
				}
				}
				$('#departamento').prepend("<option value='' selected>Seleccione departamento</option>");
				
			});
		}
		
		$('#departamento').change(function() {
			cambiarMunicipio(this, '#municipio');
		});
		
		function cambiarMunicipio(combo1, combo2) {
			combo2 = $(combo2);
			limpiarCombo(combo2);
			combo2.disabled = true;
			llenarMunicipios(combo1.selectedIndex);
			combo1.disabled =false;
			combo2.disabled =false;
		}
		
		function limpiarCombo(combo){
			$('#municipio').empty();
			$('#municipio').prepend("<option value='' selected>Seleccione municipio</option>");
		}
		
		function llenarMunicipios(id) {
			loadJSON(function (response) {
				var datos = JSON.parse(response);
				$('#municipio').empty();
				for(var i=datos[id-1].ciudades.length-1; i>=0;i--){
					console.log(datos[id-1].ciudades[i]);
					$('#municipio').prepend("<option value='"+datos[(id-1)].ciudades[i]+"' >"+datos[(id-1)].ciudades[i]+"</option>");
				}
				$('#municipio').prepend("<option value='' selected>Seleccione municipio</option>");
			});
		}

});

</script>

<div class="input-box">
										<span class="details">Departamento</span> 
										<select name="departamento" id="departamento"></select>
									</div>
									<div class="input-box">
										<span class="details">Municipio/ciudad</span> <select
											name="municipio" id="municipio">
											<option>La primera opción</option>
										</select>
									</div>
</body>
</html>