package core.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private Image image;

	public ImagePanel(String cheminImage) {
		super();
		this.image = getToolkit().getImage(cheminImage); 
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

}
