package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logic.Cita;
import nexus.GestorCitas;

public class VentanaCita extends JFrame {

	private Cita c;
	private GestorCitas gestor = new GestorCitas();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnSuperior;
	private JPanel pnPaciente;
	private JLabel lbPaciente;
	private JLabel lbNombrePaciente;
	private JPanel pnFechaHora;
	private JLabel lbHora;
	private JLabel lbMuestraHora;
	private JLabel lbFecha;
	private JLabel lbMuestraFecha;
	private JPanel pnSalaUrgente;
	private JLabel lbSala;
	private JLabel lbNombreSala;
	private JLabel lbUrgente;
	private JLabel lbEsUrgente;
	private JPanel pnCentral;
	private JPanel pnInferior;
	private JButton btAceptar;
	private JPanel pnAcude;
	private JLabel lbAcude;
	private JCheckBox chbxAcude;
	private JPanel pnCausas;
	private JLabel lbCausas;
	private JTextArea txtACausas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCita frame = new VentanaCita("401");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCita(String idCita) {
		this.c = gestor.getCita(idCita);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnSuperior(), BorderLayout.NORTH);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
		contentPane.add(getPnInferior(), BorderLayout.SOUTH);
	}

	private JPanel getPnSuperior() {
		if (pnSuperior == null) {
			pnSuperior = new JPanel();
			pnSuperior.setLayout(new BoxLayout(pnSuperior, BoxLayout.Y_AXIS));
			pnSuperior.add(getPnPaciente());
			pnSuperior.add(getPnFechaHora());
			pnSuperior.add(getPnSalaUrgente());
		}
		return pnSuperior;
	}

	private JPanel getPnPaciente() {
		if (pnPaciente == null) {
			pnPaciente = new JPanel();
			pnPaciente.setLayout(new BoxLayout(pnPaciente, BoxLayout.X_AXIS));
			pnPaciente.add(getLbPaciente());
			pnPaciente.add(getLbNombrePaciente());
		}
		return pnPaciente;
	}

	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente:");
			lbPaciente.setLabelFor(lbNombrePaciente);
		}

		return lbPaciente;
	}

	private JLabel getLbNombrePaciente() {
		if (lbNombrePaciente == null) {
			lbNombrePaciente = new JLabel("Nombre de Paciente");
			lbNombrePaciente.setText(c.getNombrePaciente());
		}
		return lbNombrePaciente;
	}

	private JPanel getPnFechaHora() {
		if (pnFechaHora == null) {
			pnFechaHora = new JPanel();
			pnFechaHora.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnFechaHora.add(getLbHora());
			pnFechaHora.add(getLbMuestraHora());
			pnFechaHora.add(getLbFecha());
			pnFechaHora.add(getLbMuestraFecha());
		}
		return pnFechaHora;
	}

	private JLabel getLbHora() {
		if (lbHora == null) {
			lbHora = new JLabel("Hora:");
			lbHora.setLabelFor(getLbMuestraHora());
		}

		return lbHora;
	}

	private JLabel getLbMuestraHora() {
		if (lbMuestraHora == null) {
			lbMuestraHora = new JLabel("00:00");
			lbMuestraHora.setText(c.getHora());
		}
		return lbMuestraHora;
	}

	private JLabel getLbFecha() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha:");
		}

		return lbFecha;
	}

	private JLabel getLbMuestraFecha() {
		if (lbMuestraFecha == null) {
			lbMuestraFecha = new JLabel("01/01/1970");
			lbMuestraFecha.setText(c.getFecha());
		}
		return lbMuestraFecha;
	}

	private JPanel getPnSalaUrgente() {
		if (pnSalaUrgente == null) {
			pnSalaUrgente = new JPanel();
			pnSalaUrgente.add(getLbSala());
			pnSalaUrgente.add(getLbNombreSala());
			pnSalaUrgente.add(getLbUrgente());
			pnSalaUrgente.add(getLbEsUrgente());
		}
		return pnSalaUrgente;
	}

	private JLabel getLbSala() {
		if (lbSala == null) {
			lbSala = new JLabel("Sala:");
		}
		return lbSala;
	}

	private JLabel getLbNombreSala() {
		if (lbNombreSala == null) {
			lbNombreSala = new JLabel("Nombre de Sala");
			lbNombreSala.setText(c.getSala() + "");
		}
		return lbNombreSala;
	}

	private JLabel getLbUrgente() {
		if (lbUrgente == null) {
			lbUrgente = new JLabel("URGENTE:");
		}
		return lbUrgente;
	}

	private JLabel getLbEsUrgente() {
		if (lbEsUrgente == null) {
			lbEsUrgente = new JLabel("SÍ");
			lbEsUrgente.setText(parseUrgente(c.urgente()));
		}
		return lbEsUrgente;
	}

	private String parseUrgente(boolean u) {
		return u ? "SÍ" : "NO";
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentral.add(getPnAcude());
			pnCentral.add(getPnCausas());
		}
		return pnCentral;
	}

	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			pnInferior.setLayout(new BorderLayout(0, 0));
			pnInferior.add(getBtAceptar(), BorderLayout.EAST);
		}
		return pnInferior;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
		}
		return btAceptar;
	}

	private JPanel getPnAcude() {
		if (pnAcude == null) {
			pnAcude = new JPanel();
			pnAcude.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnAcude.add(getLbAcude());
			pnAcude.add(getChbxAcude());
		}
		return pnAcude;
	}

	private JLabel getLbAcude() {
		if (lbAcude == null) {
			lbAcude = new JLabel("Acude:");
			lbAcude.setLabelFor(getChbxAcude());
		}
		return lbAcude;
	}

	private JCheckBox getChbxAcude() {
		if (chbxAcude == null) {
			chbxAcude = new JCheckBox("");
			chbxAcude.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					activarCausas(chbxAcude.isSelected());
				}
			});
		}
		return chbxAcude;
	}

	protected void activarCausas(boolean selected) {
		for (Component c : pnCausas.getComponents()) {
			c.setEnabled(selected);
		}
		txtACausas.setEditable(selected);
	}

	private JPanel getPnCausas() {
		if (pnCausas == null) {
			pnCausas = new JPanel();
			pnCausas.add(getLbCausas());
			pnCausas.add(getTxtACausas());
		}
		return pnCausas;
	}

	private JLabel getLbCausas() {
		if (lbCausas == null) {
			lbCausas = new JLabel("Causas:");
		}
		return lbCausas;
	}

	private JTextArea getTxtACausas() {
		if (txtACausas == null) {
			txtACausas = new JTextArea();
			txtACausas.setColumns(55);
			txtACausas.setRows(24);
		}
		return txtACausas;
	}
}
