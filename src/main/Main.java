package main;

import java.awt.EventQueue;

import ui.VentanaLogin;
import ui.VentanaCrearCita;


public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		

	}

}
