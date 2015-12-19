import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class ImagenDeFondo extends JPanel {
	ImageIcon imagen;
	
	public ImagenDeFondo(String Ubicacion) {
		super();
		initialize();
		establecerImagen(Ubicacion);
	}
	
	public void seleccionarImagen()
	{
		JFileChooser selector = new JFileChooser();
		selector.showOpenDialog(this.getParent());		
		establecerImagen( selector.getSelectedFile().getPath() );
		
	}

	public void establecerImagen(String Ubicacion) {
		imagen = new ImageIcon(Ubicacion);
		repaint();
	}
	
	
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(imagen.getImage(),0 , 0, this.getWidth(), this.getHeight(), null);
		this.setOpaque(false);
	}
	
	private void initialize(){
		this.setSize(300, 200);
		this.setLayout(new GridBagLayout());
	}
}
