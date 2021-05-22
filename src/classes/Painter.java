package classes;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Painter extends JPanel{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Game.board.paintBoard(g);
		Game.board.paintLife(g);
	}
	
}
