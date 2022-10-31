package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaAdministrador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JButton btCrearCita;
	private JButton btCrearJornada;
	private JButton btBajasVacaciones;

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
		panelPrincipal.setLayout(new GridLayout(3, 0, 0, 0));
		panelPrincipal.add(getBtCrearCita());
		panelPrincipal.add(getBtCrearJornada());
		panelPrincipal.add(getBtBajasVacaciones());
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
		VentanaCrearCita vCC = new VentanaCrearCita();
		vCC.setVisible(true);
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
		VentanaCrearJornada vCJ = new VentanaCrearJornada();
		vCJ.setVisible(true);

	}

	private JButton getBtBajasVacaciones() {
		if (btBajasVacaciones == null) {
			btBajasVacaciones = new JButton("Bajas y vacaciones");
			btBajasVacaciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		}
		return btBajasVacaciones;
	}
}
