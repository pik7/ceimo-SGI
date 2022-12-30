package com.ceimo.gestion.service.utilitaires;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DifferenceDateMois {

	public static int nbOfMonthsBetweenTwoDates(String dateString1, String dateString2) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = sdf.parse(dateString1);
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date1);
		Date date2 = sdf.parse(dateString2);
		GregorianCalendar gc2 = new GregorianCalendar();
		gc2.setTime(date2);
		int gap = 0;
		gc1.add(GregorianCalendar.MONTH, 1);
		while (gc1.compareTo(gc2) <= 0) {
			gap++;
			gc1.add(GregorianCalendar.MONTH, 1);
		}
		return gap;
	}

	public static long autreMethodeCalculDiffMois(String dateDebut, String dateFin) {

		long monthsBetween = ChronoUnit.MONTHS.between(
				LocalDate.parse(dateDebut).withDayOfMonth(1),
				LocalDate.parse(dateFin).withDayOfMonth(1));
		return monthsBetween+1;
	}
	
	
	public static String mois(int numero) {
		switch (numero) {
		case 1:
			return "JANVIER";
		case 2:
			return "FEVRIER";
		case 3:
			return "MARS";
		case 4:
			return "AVRIL";
		case 5:
			return "MAI";
		case 6:
			return "JUIN";
		case 7:
			return "JUILLET";
		case 8:
			return "AOUT";
		case 9:
			return "SEPTEMBRE";
		case 10:
			return "OCTOBRE";
		case 11:
			return "NOVEMBRE";
		default:
			return "DECEMBRE";
		}
	}
	
	public static ArrayList<String> listOfMonths(String dateDepart, long nbreMois) throws ParseException{
		ArrayList<String> lesMois = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = sdf.parse(dateDepart);
		GregorianCalendar gc1 = new GregorianCalendar();
		gc1.setTime(date1);
		for(int i=0;i<nbreMois;i++) {
			lesMois.add(mois(gc1.get(GregorianCalendar.MONTH)));
		}
		return lesMois;
	}
	
	

}
