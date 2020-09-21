package _02_Pixel_Art;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;

import _05_Serialization.SaveData;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private JButton saveButton;
	private JButton load;
	ColorSelectionPanel csp;
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		setUpGrid();
	}
	
	public void setUpGrid() {
		csp = new ColorSelectionPanel();
		saveButton = new JButton("Save");
		load = new JButton("Load");
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		window.add(saveButton, BorderLayout.SOUTH);
		window.add(load, BorderLayout.SOUTH);
		gp.repaint();
		gp.addMouseListener(this);
		saveButton.addActionListener(this);
		load.addActionListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	public void saveFile(GridPanel gp) {

		try (FileOutputStream fos = new FileOutputStream(new File("src/_02_Pixel_Art/savedArt"));
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(gp);

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("saved file");

	}
	
	
	public void loadFile2() {
		try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/savedArt")); ObjectInputStream ois = new ObjectInputStream(fis)) {
			window.remove(this.gp);
			this.gp = (GridPanel) ois.readObject();
		
//			window.remove(this.csp);
//			window.remove(this.saveButton);
//			window.remove(this.load);
			window.add(this.gp);
//			window.add(this.csp);
//			window.add(this.saveButton);
//			window.add(this.load);
			window.pack();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
		}
	}

	public void loadFile() {

		try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/savedArt"));
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			
//			window.remove(this.gp);
//			window.remove(csp);
//			window.remove(saveButton);
//			window.remove(load);
//			window = new JFrame();
			
			this.gp = (GridPanel) ois.readObject();
			window.add(gp);
			window.pack();
		//	setUpGrid();
			ois.close();
			gp.repaint();
			System.out.println("loaded file");
		} catch (IOException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == saveButton) {
			saveFile(gp);
		} else if (e.getSource() == load) {
			loadFile2();
		}

	}
}
