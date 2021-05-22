package classes;

import java.awt.*;
import java.awt.MultipleGradientPaint.ColorSpaceType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.swing.*;

public class Board implements MouseListener, ActionListener{
	
	JFrame frame;
	JPanel panel;
	
	JPanel utility;
	JPanel speedPanel;
	Painter painter;
	
	JButton start;
	JButton pause;
	JButton reset;
	
	JButton increase;
	JButton decrease;
	
	static ArrayList<Square> arr = new ArrayList<Square>();
	
	private boolean init = false;
	
	public static boolean started = false;
	public static boolean paused = false;
	public static boolean gameOver = false;
	public static boolean reseted = false;
	
	public static int width;
	public static int height;
	
	public Board() {
	
		panel = new JPanel(new BorderLayout());
		frame = new JFrame("Game_of_life");
		painter = new Painter();
		
		painter.setLayout(null);
		painter.addMouseListener(this);
		
		initUtilityPanel();
		//initSpeedPanel();
		
		panel.add(painter, BorderLayout.CENTER);
		panel.add(utility, BorderLayout.SOUTH);
		//panel.add(speedPanel, BorderLayout.WEST);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public void initUtilityPanel() {
		
		utility = new JPanel(new GridBagLayout());
		
		start = new JButton("Start");
		pause = new JButton("Pause");
		reset = new JButton("Reset");
		
		start.addActionListener(this);
		pause.addActionListener(this);
		reset.addActionListener(this);
		
		utility.add(start);
		utility.add(pause);
		utility.add(reset);
		
		utility.setBackground(Color.yellow);
	}
	
	public void initSpeedPanel() {
		
		speedPanel = new JPanel(new GridBagLayout());
		
		increase = new JButton("+");
		decrease = new JButton("-");
		
		speedPanel.add(increase);
		speedPanel.add(decrease);
		
		speedPanel.setBackground(Color.yellow);
		
		speedPanel.setMaximumSize(new Dimension(100, 500));
		
	}
	

	public void paintBoard(Graphics g) {
		
		g.setColor(Color.darkGray);
		int distance = 20;
		
		for(int i = 1; i < 96; ++i) {
			g.drawLine(distance, 0, distance, 1080);
			distance += 20;
		}
		
		distance = 20;
		
		for(int i = 1; i < 54; ++i) {
			g.drawLine(0, distance, 1920, distance);
			distance += 20;
		}
		
		
		g.setColor(Color.yellow);
		g.fillRect(1681, 21, 219, 39);
		
		g.setColor(Color.black);
		g.setFont(new Font("System San Francisco Display", Font.BOLD, 24));
		g.drawString("Generation : " + String.valueOf(Game.generation), 1700, 47);
		
	}
	
	public void paintLife(Graphics g) {
		
		if(init) {
			
			g.setColor(Color.black);
			for(Square i : arr) {
				if(Game.m[i.i][i.j] == 1)
					g.fillRect(i.x - (i.x % 20) + 1, i.y - (i.y % 20) + 1, 19, 19);
			}
			
			/*for(int i = 1; i <= arr.size(); i++) {
				if(Game.m[arr.get(i).i][arr.get(i).j] == 1) {
					g.fillRect(arr.get(i).x - (arr.get(i).x % 20) + 1, arr.get(i).y - (arr.get(i).y % 20) + 1, 19, 19);
				}
			}*/
		}
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		
		if(!started && !paused) {
			Square square = new Square(mouse.getX(), mouse.getY());
			
			Game.m[(square.y - (square.y % 20)) / 20 + 1][(square.x - (square.x % 20)) / 20 + 1] = 1;
			
			square.setI((square.y - (square.y % 20)) / 20 + 1);
			square.setJ((square.x - (square.x % 20)) / 20 + 1);
			
			arr.add(square);
			
			init = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {

		if(action.getActionCommand().equals(reset.getText())) {
			
			arr.clear();
			
			started = false;
			paused = false;
			reseted =true;
			init = false;
			
			Game.clearMatrix();
		}
		
		if(action.getActionCommand().equals(start.getText())) {
			started = true;
			paused = false;
			gameOver = false;
		}
		
		if(action.getActionCommand().equals(pause.getText())) {
			paused = true;
			started = false;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
