package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMedico extends JFrame {

	private JPanel panelPrincipal;
	private JButton btHorario;
	private JButton btDiagnosticos;

	/**
	 * Create the frame.
	 */
	public VentanaMedico() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(2, 0, 0, 0));
		panelPrincipal.add(getBtHorario());
		panelPrincipal.add(getBtDiagnosticos());
	}

	private JButton getBtHorario() {
		if (btHorario == null) {
			btHorario = new JButton("Ver horarios");
			btHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaHorario vH=new VentanaHorario();
					vH.show();
				}
			});
		}
		return btHorario;
	}
	private JButton getBtDiagnosticos() {
		if (btDiagnosticos == null) {
			btDiagnosticos = new JButton("Gestionar diagnósticos");
			btDiagnosticos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaGestorDiagnosticos vgd=new VentanaGestorDiagnosticos();
					vgd.show();
				}
			});
		}
		return btDiagnosticos;
	}
}
