
function Validar(Cadena){
	var Fecha= new String(Cadena)	// Crea un string
	var RealFecha= new Date()	// Para sacar la fecha de hoy
	// Cadena Año
	var Ano= new String(Fecha.substring(Fecha.lastIndexOf("/")+1,Fecha.length))
	// Cadena Mes
	var Mes= new String(Fecha.substring(Fecha.indexOf("/")+1,Fecha.lastIndexOf("/")))
	// Cadena Día
	var Dia= new String(Fecha.substring(0,Fecha.indexOf("/")))

	// Valido el año
	if (isNaN(Ano) || Ano.length<4 || parseFloat(Ano)<1900){
        	//alert('Año inválido')
		return false
	}
	// Valido el Mes
	if (isNaN(Mes) || parseFloat(Mes)<1 || parseFloat(Mes)>12){
		//alert('Mes inválido')
		return false
	}
	// Valido el Dia
	if (isNaN(Dia) || parseInt(Dia, 10)<1 || parseInt(Dia, 10)>31){
		//alert('Día inválido')
		return false
	}
	if (Mes==4 || Mes==6 || Mes==9 || Mes==11 || Mes==2) {
		if (Mes==2 && Dia > 28 || Dia>30) {
			//alert('Día inválido')
			return false
		}
	}

  //para que envie los datos, quitar las  2 lineas siguientes
  //alert("Fecha correcta.")
  return true
}

function getFechaActual(){
	var d = new Date();
	var curr_date = String(d.getDate());
	var curr_month = String(d.getMonth()+1);

	if(curr_date.length == 1){
		curr_date = "0"+curr_date;
	}

	if(curr_month.length == 1){
		curr_month  = "0"+curr_month;
	}
	var curr_year = d.getFullYear();
	var hoy = curr_date + "/" + curr_month + "/" + curr_year;
	return hoy;
}

      function compare_dates(fecha, fecha2)
      {
        var xMonth=fecha.substring(3, 5);
        var xDay=fecha.substring(0, 2);
        var xYear=fecha.substring(6,10);
        var yMonth=fecha2.substring(3, 5);
        var yDay=fecha2.substring(0, 2);
        var yYear=fecha2.substring(6,10);
        if (xYear> yYear)
        {
            return(true)
        }
        else
        {
          if (xYear == yYear)
          {
            if (xMonth> yMonth)
            {
                return(true)
            }
            else
            {
              if (xMonth == yMonth)
              {
                if (xDay> yDay)
                  return(true);
                else
                  return(false);
              }
              else
                return(false);
            }
          }
          else
            return(false);
        }
    }



      function compare_dateseq(fecha, fecha2)
      {
        var xMonth=fecha.substring(3, 5);
        var xDay=fecha.substring(0, 2);
        var xYear=fecha.substring(6,10);
        var yMonth=fecha2.substring(3, 5);
        var yDay=fecha2.substring(0, 2);
        var yYear=fecha2.substring(6,10);
        if (xYear> yYear)
        {
            return(true)
        }
        else
        {
          if (xYear == yYear)
          {
            if (xMonth> yMonth)
            {
                return(true)
            }
            else
            {
              if (xMonth == yMonth)
              {
                if (xDay>= yDay)
                  return(true);
                else
                  return(false);
              }
              else
                return(false);
            }
          }
          else
            return(false);
        }
    }