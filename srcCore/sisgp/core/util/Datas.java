package sisgp.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Datas {
	
	public static String dateToString(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data);
	}
	
	public static Date stringToDate(String data) throws ParseException {
		if(data == null || data.equals("")) {
			return null;
		}
		
		java.sql.Date dt = null;
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		dt = new java.sql.Date(df.parse(data).getTime());
		return dt;
				
	}
	
	public static String calendarToString(Calendar data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(data.getTime());
	}
	
	public static Calendar stringToCalendar(String data) throws ParseException {
		if(data == null || data.equals("")) {
			return null;
		}
		
		Calendar dt = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		dt.setTime(df.parse(data));
		return dt;
	}
	
	public static Double getDifferenceInDays(Date inicio, Date fim) {
		Double diferenca = 0.0;
		
		Date dataI = inicio;
		Date dataF = fim;
		
		if(dataI != null && dataF != null) {
			LocalDate dataInicio = dataI.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dataFim = dataF.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			Period periodo = Period.between(dataInicio, dataFim);
			diferenca = (double) periodo.getDays();
		}		
		
		return diferenca;
	}
	

}
