package main;

import java.awt.EventQueue;

import ui.VentanaCrearCita;
import util.DataBase;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					new DataBase().cargarDatosDePrueba();
					VentanaCrearCita frame = new VentanaCrearCita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		

	}

}
