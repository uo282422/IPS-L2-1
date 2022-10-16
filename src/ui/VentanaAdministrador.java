package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdministrador extends JFrame {

	private JPanel panelPrincipal;
	private JButton btCrearCita;
	private JButton btCrearJornada;



	/**
	 * Create the frame.
	 */
	public VentanaAdministrador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(2, 0, 0, 0));
		panelPrincipal.add(getBtCrearCita());
		panelPrincipal.add(getBtCrearJornada());
	}

	private JButton getBtCrearCita() {
		if (btCrearCita == null) {
			btCrearCita = new JButton("Crear cita");
			btCrearCita.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					irVentanaCrearCita();
				}
			});
		}
		return btCrearCita;
	}
	protected void irVentanaCrearCita() {
		VentanaCrearCita vCC=new VentanaCrearCita();
		vCC.show();
		
	}

	private JButton getBtCrearJornada() {
		if (btCrearJornada == null) {
			btCrearJornada = new JButton("Crear jornada");
			btCrearJornada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					irVentanaCrearJornada();
				}
			});
		}
		return btCrearJornada;
	}

	protected void irVentanaCrearJornada() {
		VentanaCrearJornada vCJ=new VentanaCrearJornada();
		vCJ.show();
		
	}
}
