package model.logic;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.LinkedList;
import model.data_structures.Queue;
import model.logic.Viaje.Category;

public class MVCModelo {
	
	private LinkedList<Viaje>[] month;
	private LinkedList<Viaje>[] day;
	private LinkedList<Viaje>[] hour;

	@SuppressWarnings("unchecked")
	public MVCModelo()
	{
		month = (LinkedList<Viaje>[]) new LinkedList<?>[3];
		day = (LinkedList<Viaje>[]) new LinkedList<?>[7];
		hour = (LinkedList<Viaje>[]) new LinkedList<?>[24];
		
		for(int i=0; i< month.length; i++) {
			month[i] = new LinkedList<Viaje>();
		}
		
		for(int i=0; i< day.length; i++) {
			day[i] = new LinkedList<Viaje>();
		}
		
		for(int i=0; i< hour.length; i++) {
			hour[i] = new LinkedList<Viaje>();
		}
	}
	//TODO A1
	public Queue<Viaje> A1(int origen,int destino, int mes){
		Queue<Viaje> resp = new Queue<Viaje>();
		
		if(!month[mes].isEmpty()) {
			for(Viaje viaje : month[mes]){
				if(viaje.getSourceid() == origen && viaje.getDstid() == destino){
					resp.enqueue(viaje);
				}
			}
		}		
		
		return resp;
	}
	
	//TODO A2
	public Queue<Viaje> A2(int num,int mes){
		Queue<Viaje> resp = new Queue<Viaje>();
		ComparatorViaje comp = new ComparatorViaje();
		
		for(Viaje viaje:month[mes]) {
			if(resp.size()<=num) {
				resp.addOrderDelete(viaje, new ComparatorViaje());
			}else if(comp.compare(viaje,resp.get())>0){
					resp.addOrderDelete(viaje, comp);
					resp.dequeue();
				}
			}
		
		return resp;
	}
	
	//TODO A3
	public Queue<Viaje[]> A3(int menor, int origen,int mayor,int mes){
		Queue<Viaje[]> resp = new Queue<Viaje[]>();
		Viaje[] tempViaje = new Viaje[2];
		
		for(int i=0 ; i <=mayor-menor;i++) {
			//para los viajes con origen = soureid
			tempViaje[0] = new Viaje(Category.MONTH,origen,(menor+i),-1,-1,-1,-1,-1);
			//para los viajes con origen = dstid
			tempViaje[1] = new Viaje(Category.MONTH,(menor+i),origen,-1,-1,-1,-1,-1);
			for(Viaje viaje : month[mes]) {
				if(viaje.getSourceid() == origen && viaje.getDstid()==menor+i){
					tempViaje[0] = viaje;
				}else if(viaje.getDstid() == origen && viaje.getSourceid()==menor+i ){ //si aparece origen == Dstid
					tempViaje[1] = viaje;
				}
			}
			resp.enqueue(tempViaje);
		}
		
		return resp;
	}
	
	//TODO B1
	public Queue<Viaje> B1(int origen,int destino, int dia){
		Queue<Viaje> resp = new Queue<Viaje>();
		
		if(!day[dia].isEmpty()) {
			for(Viaje viaje : day[dia]){
				if(viaje.getSourceid() == origen && viaje.getDstid() == destino){
					resp.enqueue(viaje);
				}
			}
		}
		
		return resp;
	}
	
	//TODO B2
	public Queue<Viaje> B2(int num,int dia){
		Queue<Viaje> resp = new Queue<Viaje>();
		ComparatorViaje comp = new ComparatorViaje();
		
		for(Viaje viaje:day[dia]) {
			if(resp.size()<=num) {
				resp.addOrderDelete(viaje, new ComparatorViaje());
			}else if(comp.compare(viaje,resp.get())>0){
					resp.addOrderDelete(viaje, comp);
					resp.dequeue();
				}
			}
		
		return resp;
	}
	
	//TODO B3
	public Queue<Viaje[]> B3(int menor, int origen,int mayor,int dia){
		Queue<Viaje[]> resp = new Queue<Viaje[]>();
		Viaje[] tempViaje = new Viaje[2];
		
		for(int i=0 ; i <=mayor-menor;i++) {
			//para los viajes con origen = soureid
			tempViaje[0] = new Viaje(Category.MONTH,origen,(menor+i),-1,-1,-1,-1,-1);
			//para los viajes con origen = dstid
			tempViaje[1] = new Viaje(Category.MONTH,(menor+i),origen,-1,-1,-1,-1,-1);
			for(Viaje viaje : day[dia]) {
				if(viaje.getSourceid() == origen && viaje.getDstid()==menor+i){
					tempViaje[0] = viaje;
				}else if(viaje.getDstid() == origen && viaje.getSourceid()==menor+i ){ //si aparece origen == Dstid
					tempViaje[1] = viaje;
				}
			}
			resp.enqueue(tempViaje);
		}
		
		return resp;
	}
	
	//TODO C1
	public Queue<Viaje> C1(int origen,int destino, int ini, int fin) throws Exception{
		Queue<Viaje> resp = new Queue<Viaje>();
		if(ini>fin){
			throw new Exception("ini > fin");
		}	
		for(int i=fin-ini;i>=0;i--){
			if(!hour[ini+i].isEmpty()) {
				for(Viaje viaje : hour[ini+i]){
					if(viaje.getSourceid() == origen && viaje.getDstid() == destino){
						resp.enqueue(viaje);
					}
				}
			}
		}
				
		return resp;
	}
	
	//TODO C2
	public Queue<Viaje> C2(int num,int hora){
		Queue<Viaje> resp = new Queue<Viaje>();
		ComparatorViaje comp = new ComparatorViaje();
		
		for(Viaje viaje:hour[hora]) {
			if(resp.size()<=num) {
				resp.addOrderDelete(viaje, new ComparatorViaje());
			}else if(comp.compare(viaje,resp.get())>0){
					resp.addOrderDelete(viaje, comp);
					resp.dequeue();
				}
			}
		
		return resp;
	}
	
	//TODO C3
		public int[] C3(int origen,int destino){
			
			double cant = 0;
			int[] resp = new int[24];
			for(int i=0; i < resp.length;i++) {
				resp[i] = 0;
			}
			
			for(int i=0; i< hour.length;i++) {
				if(!hour[i].isEmpty()) {
					for(Viaje viaje : hour[i]){
						if(viaje.getSourceid() == origen && viaje.getDstid() == destino){
							cant = viaje.getMean();
							resp[i] =(int) ((cant%60>29)?((cant/60)+1):(cant/60));
						}
					}
				}
			}
			
			return resp;
		}
	
	//TODO Carga
	public int[] carga(int num){
		CSVReader readerMonth = null;
		CSVReader readerDay = null;
		CSVReader readerHour = null;
		int[] respuesta = new int[5];
		
		for(int i=0;i<respuesta.length;i++) {
			respuesta[i] = 0;
		}
		
		try {
			String[] nextLine;
			int numTemp = 0;
			
			readerMonth = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+num+"-All-MonthlyAggregate.csv"));
			readerDay = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+num+"-WeeklyAggregate.csv"));
			readerHour = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+num+"-All-HourlyAggregate.csv"));
			
			readerMonth.readNext();
			readerDay.readNext();
			readerHour.readNext();
			
			while ((nextLine = readerMonth.readNext()) != null) {
				numTemp = Integer.parseInt(nextLine[2]);
				month[((numTemp-1)%3)].add(createViaje(Category.MONTH, nextLine));
				
				numTemp = Integer.parseInt(nextLine[0]);
				if(respuesta[3]>numTemp) { respuesta[3] = numTemp;
				}else if(respuesta[4] < numTemp) {respuesta[4] = numTemp;}
			}
			
			while ((nextLine = readerDay.readNext()) != null) {
				numTemp = Integer.parseInt(nextLine[2]);
				day[(numTemp-1)].add(createViaje(Category.DAY, nextLine));
				
				numTemp = Integer.parseInt(nextLine[0]);
				if(respuesta[3]>numTemp) { respuesta[3] = numTemp;
				}else if(respuesta[4] < numTemp) {respuesta[4] = numTemp;}
			}
			
			while ((nextLine = readerHour.readNext()) != null) {
				numTemp = Integer.parseInt(nextLine[2]);
				day[numTemp].add(createViaje(Category.HOUR, nextLine));
				
				numTemp = Integer.parseInt(nextLine[0]);
				if(respuesta[3]>numTemp) { respuesta[3] = numTemp;
				}else if(respuesta[4] < numTemp) {respuesta[4] = numTemp;}
			}
				
			respuesta[0] = month[0].size() + month[1].size() + month[2].size();
			
			for(int i=0;i< day.length;i++) {
				respuesta[1] += day[i].size();
			}
			
			for(int i=0;i< hour.length;i++) {
				respuesta[2] += hour[i].size();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if (readerMonth != null) {
				try {
					readerMonth.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (readerDay != null) {
				try {
					readerDay.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (readerHour != null) {
				try {
					readerHour.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return respuesta;
	}
	
	//metodo para que la carga sea mas comoda
	private Viaje createViaje(Category category,String[] datos) {
		
		int sourceid = Integer.parseInt(datos[0]); 
		int dstid = Integer.parseInt(datos[1]);
		int x = Integer.parseInt(datos[2]);  //x es dia,mes o hora
		double mean = Double.parseDouble(datos[3]);
		double standard_deviation = Double.parseDouble(datos[4]);
		double geometric_mean = Double.parseDouble(datos[5]);
		double geometric_standard_deviation = Double.parseDouble(datos[6]);
		
		return new Viaje(category,sourceid,dstid,x,mean,standard_deviation,geometric_mean,geometric_standard_deviation);
	}
	
	
}
