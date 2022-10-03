package ui;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.ComponentOrientation;

public class VentanaCrearCita extends JFrame {

	private JPanel panelPrincipalCrearCita;
	private JPanel panelCampos;
	private JPanel panelID;
	private JPanel panelNombre;
	private JLabel lbPacienteId;
	private JLabel lbNombre;
	private JTextField tfNombre;
	private JTextField tfId;
	private JPanel panelHora;
	private JLabel lbHora;
	private JPanel panelFecha;
	private JLabel lbFecha;
	private JPanel panelSala;
	private JLabel lbSala;
	private JPanel panelUrgente;
	private JLabel lbUrgente;
	private JTextField tfHora;
	private JTextField tfFecha;
	private JTextField tfSala;
	private JPanel panelRb;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JPanel panelTitulo;
	private JLabel lbTitulo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCrearCita frame = new VentanaCrearCita();
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
	public VentanaCrearCita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 334, 303);
		panelPrincipalCrearCita = new JPanel();
		panelPrincipalCrearCita.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipalCrearCita);
		panelPrincipalCrearCita.setLayout(new BorderLayout(0, 0));
		panelPrincipalCrearCita.add(getPanelCampos(), BorderLayout.CENTER);
		panelPrincipalCrearCita.add(getPanelTitulo(), BorderLayout.NORTH);
	}
	private JPanel getPanelCampos() {
		if (panelCampos == null) {
			panelCampos = new JPanel();
			panelCampos.setLayout(new GridLayout(0, 1, 10, 10));
			panelCampos.add(getPanelID());
			panelCampos.add(getPanelNombre());
			panelCampos.add(getPanelHora());
			panelCampos.add(getPanelFecha());
			panelCampos.add(getPanelSala());
			panelCampos.add(getPanelUrgente());
		}
		return panelCampos;
	}
	private JPanel getPanelID() {
		if (panelID == null) {
			panelID = new JPanel();
			panelID.setLayout(new GridLayout(0, 3, 10, 0));
			panelID.add(getLbPacienteId_1());
			panelID.add(getTextField_1_1());
		}
		return panelID;
	}
	private JPanel getPanelNombre() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.setLayout(new GridLayout(0, 3, 10, 0));
			panelNombre.add(getLbNombre());
			panelNombre.add(getTfNombre());
		}
		return panelNombre;
	}
	private JLabel getLbPacienteId_1() {
		if (lbPacienteId == null) {
			lbPacienteId = new JLabel("Id del paciente :");
			lbPacienteId.setHorizontalAlignment(SwingConstants.RIGHT);
			lbPacienteId.setLabelFor(getTextField_1_1());
		}
		return lbPacienteId;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre :");
			lbNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lbNombre.setLabelFor(getTfNombre());
		}
		return lbNombre;
	}
	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}
	private JTextField getTextField_1_1() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JPanel getPanelHora() {
		if (panelHora == null) {
			panelHora = new JPanel();
			panelHora.setLayout(new GridLayout(0, 3, 10, 0));
			panelHora.add(getLbHora_1());
			panelHora.add(getTfHora());
		}
		return panelHora;
	}
	private JLabel getLbHora_1() {
		if (lbHora == null) {
			lbHora = new JLabel("Hora :");
			lbHora.setLabelFor(getTfHora());
			lbHora.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHora.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHora;
	}
	private JPanel getPanelFecha() {
		if (panelFecha == null) {
			panelFecha = new JPanel();
			panelFecha.setLayout(new GridLayout(0, 3, 10, 0));
			panelFecha.add(getLbFecha_1());
			panelFecha.add(getTfFecha());
		}
		return panelFecha;
	}
	private JLabel getLbFecha_1() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha :");
			lbFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lbFecha.setLabelFor(getTfFecha());
		}
		return lbFecha;
	}
	private JPanel getPanelSala() {
		if (panelSala == null) {
			panelSala = new JPanel();
			panelSala.setLayout(new GridLayout(0, 3, 10, 0));
			panelSala.add(getLbSala_1());
			panelSala.add(getTfSala());
		}
		return panelSala;
	}
	private JLabel getLbSala_1() {
		if (lbSala == null) {
			lbSala = new JLabel("Sala :");
			lbSala.setHorizontalAlignment(SwingConstants.RIGHT);
			lbSala.setLabelFor(getTfSala());
		}
		return lbSala;
	}
	private JPanel getPanelUrgente() {
		if (panelUrgente == null) {
			panelUrgente = new JPanel();
			panelUrgente.setLayout(new GridLayout(0, 3, 10, 0));
			panelUrgente.add(getLbUrgente_1());
			panelUrgente.add(getPanelRb());
		}
		return panelUrgente;
	}
	private JLabel getLbUrgente_1() {
		if (lbUrgente == null) {
			lbUrgente = new JLabel("Urgente :");
			lbUrgente.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbUrgente;
	}
	private JTextField getTfHora() {
		if (tfHora == null) {
			tfHora = new JTextField();
			tfHora.setHorizontalAlignment(SwingConstants.RIGHT);
			tfHora.setColumns(10);
		}
		return tfHora;
	}
	private JTextField getTfFecha() {
		if (tfFecha == null) {
			tfFecha = new JTextField();
			tfFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			tfFecha.setColumns(10);
		}
		return tfFecha;
	}
	private JTextField getTfSala() {
		if (tfSala == null) {
			tfSala = new JTextField();
			tfSala.setHorizontalAlignment(SwingConstants.RIGHT);
			tfSala.setColumns(10);
		}
		return tfSala;
	}
	private JPanel getPanelRb() {
		if (panelRb == null) {
			panelRb = new JPanel();
			panelRb.add(getRbSi());
			panelRb.add(getRbNo());
		}
		return panelRb;
	}
	private JRadioButton getRbSi() {
		if (rbSi == null) {
			rbSi = new JRadioButton("Si");
		}
		return rbSi;
	}
	private JRadioButton getRbNo() {
		if (rbNo == null) {
			rbNo = new JRadioButton("No");
		}
		return rbNo;
	}
	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.add(getLbTitulo());
		}
		return panelTitulo;
	}
	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("CREAR NUEVA CITA");
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lbTitulo;
	}
}
