package main;

import java.awt.EventQueue;

import nexus.GestorCitas;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					new DataBase().cargarDatosDePrueba();
//					VentanaCrearCita frame = new VentanaCrearCita();
//					frame.setVisible(true);
					GestorCitas gC = new GestorCitas();
					gC.cargarCitasOrdenadas("1", "29/12/2022");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		

	}

}
