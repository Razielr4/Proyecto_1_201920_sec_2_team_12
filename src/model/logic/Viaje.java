package model.logic;

public class Viaje {
/*
 * sourceid: Identificador único de la zona de origen de los viajes relacionados
 dstid: Identificador único de la zona de destino de los viajes relacionados
 month: número del mes de los viajes relacionados
 dow (day of week): día de la semana de los viajes relacionados
 hod (hour of day): hora (entera) del día de los viajes relacionados
 mean_travel_time: tiempo promedio en segundos de los viajes relacionados
 standard_deviation_travel_time: desviación estándar de los viajes relacionados
 geometric_mean_travel_time: tiempo promedio geométrico en segundos de los viajes relacionados
 geometric_standard_deviation_travel_time: desviación estándar geométrica de los viajes relacionados
 */
	private int sourceid;
	private int dstid;
	private int month;
	private int dow;
	private int hod;
	private double mean;
	private double standard_deviation;
	private double geometric_mean;
	private double geometric_standard_deviation;
	
	public enum Category { MONTH,DAY,HOUR} 
	
	public Viaje(Category category, int pSourceid, int pDstid, int x, double pMean, double pStandard, double pGeometricM, double pGeometricS) {
		sourceid = pSourceid;
		dstid = pDstid;
		mean = pMean;
		standard_deviation = pStandard;
		geometric_mean = pGeometricM;
		geometric_standard_deviation = pGeometricS;
		
		switch (category){
			case MONTH:
				month = x;
				dow = -1;
				hod = -1;
				break;
			case DAY:
				month = -1;
				dow = x;
				hod = -1;
				break;
			case HOUR:
				month = -1;
				dow = -1;
				hod = x;
				break;
		}
		
	}
	
	public int getSourceid() {
		return sourceid;
	}
	
	public int getDstid() {
		return dstid;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDow() {
		return dow;
	}
	
	public int getHod() {
		return hod;
	}
	
	public double getMean() {
		return mean;
	}
	
	public double getStandard_deviation() {
		return standard_deviation;
	}
	
	public double getGeometric_mean_travel_time() {
		return geometric_mean;
	}
	
	public double getGeometric_standard_deviation() {
		return geometric_standard_deviation;
	}	
}
