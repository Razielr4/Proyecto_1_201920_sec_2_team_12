package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("Por favor seleccione un trimestre");
			System.out.println("1. Primero (Enero,Febrero,Marzo");
			System.out.println("2. Segundo (Abril,Mayo,junio)");
			System.out.println("3. Tercero (Julio,Agosto,Septiembre)");
			System.out.println("4. Cuarto (Octubre,Noviembre,Diciembre)");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printOpciones() {
			System.out.println("Por favor seleccione una opcion");
			System.out.println("1. Consultar el tiempo promedio de viaje y su desviación estándar de los viajes");
			System.out.println("2. Consultar viajes con mayor tiempo promedio");
			System.out.println("3. Comparar los tiempos promedios de los viajes");
			
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		
		public void printOpcionesC() {
			System.out.println("Por favor seleccione una opcion");
			System.out.println("1. Consultar el tiempo promedio de viaje y su desviación estándar de los viajes");
			System.out.println("2. Consultar viajes con mayor tiempo promedio");
			System.out.println("3. Generar una gráfica ASCII");
			
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}
		
		public void printArchivos() {
			System.out.println("Por favor seleccione la opcion que desee consultar");
			System.out.println("1. Mes");
			System.out.println("2. Dia de la semana");
			System.out.println("3. Hora");
			
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
