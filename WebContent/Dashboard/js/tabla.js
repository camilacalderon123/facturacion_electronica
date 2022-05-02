function agregarFila(){
    document.getElementById("tablaprueba").insertRow(-1).innerHTML = '<td><select><option disabled selected>Buscar...</option><option>A50</option></select></td><td></td><td></td><td></td><td></td><td></td>';
  }
  
  function eliminarFila(){
    var table = document.getElementById("tablaprueba");
    var rowCount = table.rows.length;
    
    if(rowCount <= 1)
      alert('No se puede eliminar el encabezado');
    else
      table.deleteRow(rowCount -1);
  }