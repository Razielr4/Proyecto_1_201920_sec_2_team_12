package controller;

import java.util.Scanner;

import model.data_structures.Queue;
import model.logic.MVCModelo;
import model.logic.Viaje;
import view.MVCView;

public class Controller {

	private MVCModelo modelo;
	
	private MVCView view;
	
	private int trimestre;
	
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
		trimestre =0;
	}
		
	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Queue<Viaje> respuesta = new Queue<Viaje>();
		
		
		//TODO Carga
		view.printMenu();
		int option = lector.nextInt();
		
		while(6 != option && (option < 1 || option > 4) ) {
			System.out.println("--------- \n Opcion Invalida !! \n---------");
			view.printMenu();
			option = lector.nextInt();
		}
		
		if(6 != option) {
			
			trimestre =option;
			
		    modelo = new MVCModelo();
		    int[] datos = modelo.carga(option);
			System.out.println("\n - Total de viajes en el archivo de meses:"+datos[0]);
			System.out.println("- Total de viajes en el archivo de días: "+datos[1]);
			System.out.println("- Total de viajes en el archivo de horas: "+datos[2]);
			System.out.println("- La zona con menor identificador en todos los archivos: "+datos[3]);
			System.out.println("- La zona con mayor identificador en todos los archivos: "+datos[4]);
			
			//TODO Requerimientos, control de excepciones falta
			while(!fin){
				view.printArchivos();
				option = lector.nextInt();
				datos = new int[4];
				
				switch(option){
				
				case 1://mes
					while(!fin) {
						view.printOpciones();
						option = lector.nextInt();
						
						switch(option){
						case 1://TODO A1
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de destino");
							datos[1] = lector.nextInt();
							System.out.println("Ingresa el mes deseado");
							datos[2] = lector.nextInt();
							
							respuesta = modelo.A1(datos[0], datos[1],((datos[2]-1)%3));
							
							if(respuesta.isEmpty()) {
								System.out.println("No existe informacion.");
							}else {
								for(Viaje viaje: respuesta) {
									System.out.println("  *tiempo promedio de viaje: "+viaje.getMean()+". desviación estándar: "+viaje.getStandard_deviation());
								}
							}
							
							break;
							
						case 2://TODO A2
							System.out.println("Ingresa numero de viajes que desea ver");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el mes");
							datos[1] = lector.nextInt();
							System.out.println("\n los resultados muestran para cada viaje su zona origen, zona destino, el tiempo promedio de viaje y su desviación estándar, en ese orden");
							respuesta = modelo.A2(datos[0], ((datos[1]-1)%3));
							
							for(Viaje viaje: respuesta) {
								System.out.println("  *"+viaje.getSourceid()+" | "+viaje.getDstid()+" | "+viaje.getMean()+" | "+viaje.getStandard_deviation());
							}
							
							break;
							
						case 3://TODO A3
							System.out.println("Ingresa el identificador de la zona menor");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[1] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona mayor");
							datos[2] = lector.nextInt();
							System.out.println("Ingresa el mes deseado");
							datos[3] = lector.nextInt();
							
							Queue<Viaje[]> dato = modelo.A3(datos[0],datos[1],datos[2],((datos[3]-1)%3));
							
							for(Viaje[] viaje: dato) {
								//<tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de <Zona X> a <Zona dada>
								String m3 = "No hay viajes> de <";
								String m0 = "> de <"+viaje[0].getSourceid()+"> a <"+viaje[0].getDstid();
								String m1 = "> de <"+viaje[1].getSourceid()+"> a <"+viaje[1].getDstid();
								
								m0 = (viaje[0].getMonth() == -1)?m3+m0 : viaje[0].getMean()+m0;
								m1 = (viaje[0].getMonth() == -1)?m3+m1 : viaje[1].getMean()+m1;
										
								System.out.println("<"+m0+"> vs <"+m1+">");
							}
							
							break;
							
						case 6: 
							System.out.println("--------- \n Hasta pronto !! \n---------");
							fin = true;
							break;
							
						default: 
							System.out.println("--------- \n Opcion Invalida !! \n---------");
							break;
					
						}
					}
					fin = false;
					break;
					
				case 2://dia
					while(!fin) {
						view.printOpciones();
						option = lector.nextInt();
						
						switch(option){
						case 1://TODO B1
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de destino");
							datos[1] = lector.nextInt();
							System.out.println("Ingresa el dia deseado");
							datos[2] = lector.nextInt();
							
							respuesta = modelo.B1(datos[0], datos[1], datos[2]);
							
							if(respuesta.isEmpty()) {
								System.out.println("No existe informacion.");
							}else {
								for(Viaje viaje: respuesta) {
									System.out.println("  *tiempo promedio de viaje: "+viaje.getMean()+". desviación estándar: "+viaje.getStandard_deviation());
								}
							}
							
							break;
							
						case 2://TODO B2
							System.out.println("Ingresa numero de viajes que desea ver");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el dia");
							datos[1] = lector.nextInt();
							System.out.println("\n los resultados muestran para cada viaje su zona origen, zona destino, el tiempo promedio de viaje y su desviación estándar, en ese orden");
							respuesta = modelo.B2(datos[0], datos[1]);
							
							for(Viaje viaje: respuesta) {
								System.out.println("  *"+viaje.getSourceid()+" | "+viaje.getDstid()+" | "+viaje.getMean()+" | "+viaje.getStandard_deviation());
							}
							
							break;
							
						case 3://TODO B3
							System.out.println("Ingresa el identificador de la zona menor");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[1] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona mayor");
							datos[2] = lector.nextInt();
							System.out.println("Ingresa el dia deseado");
							datos[3] = lector.nextInt();
							
							Queue<Viaje[]> dato = modelo.B3(datos[0],datos[1],datos[2],datos[3]); 
							 
							
							for(Viaje[] viaje: dato) {
								//<tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de <Zona X> a <Zona dada>
								String m3 = "No hay viajes> de <";
								String m0 = "> de <"+viaje[0].getSourceid()+"> a <"+viaje[0].getDstid();
								String m1 = "> de <"+viaje[1].getSourceid()+"> a <"+viaje[1].getDstid();
								
								m0 = (viaje[0].getMonth() == -1)?m3+m0 : viaje[0].getMean()+m0;
								m1 = (viaje[0].getMonth() == -1)?m3+m1 : viaje[1].getMean()+m1;
								
								System.out.println("<"+m0+"> vs <"+m1+">");
										
							}			
							
							break;
							
						case 6: 
							System.out.println("--------- \n Hasta pronto !! \n---------");
							fin = true;
							break;
							
						default: 
							System.out.println("--------- \n Opcion Invalida !! \n---------");
							break;
					
						}
					}
					fin = false;
					break;
					
				case 3:
					while(!fin) {
						view.printOpcionesC();
						option = lector.nextInt();
						
						switch(option){
						case 1://TODO C1
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de destino");
							datos[1] = lector.nextInt();
							System.out.println("Ingresa la hora inicial");
							datos[2] = lector.nextInt();
							System.out.println("Ingresa la hora final");
							datos[3] = lector.nextInt();
							
							try {
								respuesta = modelo.C1(datos[0], datos[1], datos[2], datos[3]);
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							if(respuesta.isEmpty()) {
								System.out.println("No existe informacion.");
							}else {
								for(Viaje viaje: respuesta) {
									System.out.println("  *tiempo promedio de viaje: "+viaje.getMean()+". desviación estándar: "+viaje.getStandard_deviation());
								}
							}
							
							break;
							
						case 2://TODO C2
							System.out.println("Ingresa numero de viajes que desea ver");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa hora");
							datos[1] = lector.nextInt();
							System.out.println("\n los resultados muestran para cada viaje su zona origen, zona destino, el tiempo promedio de viaje y su desviación estándar, en ese orden");
							respuesta = modelo.C2(datos[0], datos[1]);
							
							for(Viaje viaje: respuesta) {
								System.out.println("  *"+viaje.getSourceid()+" | "+viaje.getDstid()+" | "+viaje.getMean()+" | "+viaje.getStandard_deviation());
							}
							
							break;
							
						case 3://TODO C3
							System.out.println("Ingresa el identificador de la zona de origen");
							datos[0] = lector.nextInt();
							System.out.println("Ingresa el identificador de la zona de destino");
							datos[1] = lector.nextInt();
							
							int[] datosTemp = modelo.C3(datos[0],datos[1]);
							
							System.out.println("Aproximación en minutos de viajes entre zona origen y zona destino.");
							System.out.println("Trimestre "+trimestre+" del 2018 detallado por cada hora del día");
							System.out.println("Zona Origen: "+datos[0]);
							System.out.println("Zona Destino: "+datos[1]);
							System.out.println("Hora| # de minutos");
							
							for(int i=0; i<24;i++) {
								String s = (i<10)?"0"+i+" | ": i+" | ";
								 s += (datosTemp[i] == 0)?"hora sin viajes":"";
								while(datosTemp[i] != 0) {
									s = s+"*";
									datosTemp[i]--;
								}
								System.out.println(s);
							}
							break;
							
						case 6: 
							System.out.println("--------- \n Hasta pronto !! \n---------");
							fin = true;
							break;
							
						default: 
							System.out.println("--------- \n Opcion Invalida !! \n---------");
							break;
					
						}
					}
					fin = false;
					break;
					
				case 6: 
					System.out.println("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;
					
				default: 
					System.out.println("--------- \n Opcion Invalida !! \n---------");
					break;
				}
			}
			
		}else {
			System.out.println("--------- \n Hasta pronto !! \n---------"); 
			lector.close();
			fin = true;
		}
	}	
}
