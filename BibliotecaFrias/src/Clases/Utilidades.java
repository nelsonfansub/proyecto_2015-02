package Clases;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utilidades {
	
	private static String[] fecha;
		     
	private static void generarFechas()
	{
			fecha = new String[120];
			for (int j = 0; j < fecha.length; j++)
			{
				fecha[j] = "" +	(j+1950);
			}
			
	}
	
	public static String[] obtenerFechas()
	{
			generarFechas();	
			return fecha;
			
	}
	
	public static boolean isNumber(String string) {
	    try {
	        Long.parseLong(string);
	    } catch (Exception e) {
	        return false;
	    }
	    return true;
	}
	
	protected static void confirmarCierre(JFrame ventana) {
		int opcion = JOptionPane.showConfirmDialog(	ventana, "Desea Cerrar La Aplicacion?",
										"Confirmacion", JOptionPane.YES_NO_OPTION);
		
		if( opcion == JOptionPane.YES_OPTION )
		{
			ventana.dispose();
		}
	}
}
