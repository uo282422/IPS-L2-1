package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMotivos extends JFrame {

	private JPanel pnPrinc;

	

	/**
	 * Create the frame.
	 */
	private VentanaCrearCita vcc;
	private JLabel lbInserta;
	private JTextArea textArea;
	private JButton btGuardar;
	public VentanaMotivos(VentanaCrearCita vcc) {
		this.vcc=vcc;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 294, 151);
		pnPrinc = new JPanel();
		pnPrinc.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnPrinc);
		pnPrinc.setLayout(new BorderLayout(0, 0));
		pnPrinc.add(getLbInserta(), BorderLayout.NORTH);
		pnPrinc.add(getTextArea(), BorderLayout.CENTER);
		pnPrinc.add(getBtGuardar(), BorderLayout.SOUTH);
	}

	private JLabel getLbInserta() {
		if (lbInserta == null) {
			lbInserta = new JLabel("Inserte los motivos o las causas iniciales de la cita");
		}
		return lbInserta;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
		}
		return textArea;
	}
	private JButton getBtGuardar() {
		if (btGuardar == null) {
			btGuardar = new JButton("Guardar");
			btGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					guardarMotivos();
					dispose();
				}
			});
		}
		return btGuardar;
	}

	protected void guardarMotivos() {
		vcc.setMotivosI(getTextArea().getText());
		
	}
}
