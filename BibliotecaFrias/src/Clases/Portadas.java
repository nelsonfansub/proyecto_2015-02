package Clases;

import java.awt.*;
import javax.swing.*;

public class Portadas extends JPanel {
	
	private Image imagen;

	public Portadas(Image imagen) {
		this.imagen = imagen;
	}
	Dimension dimenciones = new Dimension (imagen.getHeight(null), imagen.getWidth(null));

	@Override
	protected void paintComponent(Graphics img) {
		img.drawImage(imagen, 0, 0, this);
	}
	
	
}
