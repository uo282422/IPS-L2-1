package main;

import java.awt.EventQueue;

import javax.swing.UIManager;

import ui.VentanaLogin;
import util.DataBase;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					VentanaLogin frame = new VentanaLogin();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		

	}

}
