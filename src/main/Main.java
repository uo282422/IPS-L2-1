package main;

import java.awt.EventQueue;

import ui.VentanaCrearCita;
import util.DataBase;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBase bd=new DataBase();
					bd.cargarDatosDePrueba();
					VentanaCrearCita frame = new VentanaCrearCita(bd);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		

	}

}
