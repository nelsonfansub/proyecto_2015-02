package Clases;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Limpiar {
	
	public Limpiar(){
		
	}
	
	public static void limpiarCampos(Container contenedor) {
		Component[] componentes = contenedor.getComponents();
		
		for (int i = 0; i < componentes.length; i++)
		{
			if(componentes[i] instanceof JTextField)
			{
				((JTextField)componentes[i]).setText("");
			}
			else if(componentes[i] instanceof JComboBox)
			{
				((JComboBox)componentes[i]).setSelectedIndex(0);
			}
		}
	}

}
