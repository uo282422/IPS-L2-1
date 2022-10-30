package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logic.Enfermedad;
import logic.Medico;
import logic.Paciente;
import logic.Vacuna;
import logic.cita.Cita;
import nexus.GestorCitas;
import nexus.GestorPacientes;
import nexus.GestorSalas;

public class HistorialPaciente extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JScrollPane spMain;
	private JPanel pnMain;
	private Component verticalStrut;
	private JLabel lblGeneral;
	private JTextArea taGeneral;
	private Component verticalStrut_1;
	private JLabel lblCitas;
	private JTextArea taCitas;
	private Component verticalStrut_2;
	private JLabel lblEnfermedades;
	private JTextArea taEnfermedades;
	private Component verticalStrut_3;
	private JLabel lblVacunas;
	private JTextArea taVacunas;
	private JButton btnCerrar;

	private GestorPacientes gP;
	private GestorCitas gC;
	private GestorSalas gS;

	private Paciente paciente = null;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HistorialPaciente frame = new HistorialPaciente(106);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public HistorialPaciente(int idPaciente) {

		this.gP = new GestorPacientes();
		System.out.println(idPaciente);
		this.paciente = gP.getPaciente(idPaciente);

		this.gC = new GestorCitas();
		this.gS = new GestorSalas();

		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 802, 919);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getSpMain(), BorderLayout.CENTER);
		contentPane.add(getBtnCerrar(), BorderLayout.SOUTH);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel(String.format("Historial de %s %s", paciente.getNombre(), paciente.getApellido()));
			lblTitulo.setFont(new Font("Cantarell", Font.BOLD, 26));
		}
		return lblTitulo;
	}

	private JScrollPane getSpMain() {
		if (spMain == null) {
			spMain = new JScrollPane();
			spMain.setViewportView(getPnMain());
			spMain.getVerticalScrollBar().setUnitIncrement(10);
		}
		return spMain;
	}

	private JPanel getPnMain() {
		if (pnMain == null) {
			pnMain = new JPanel();
			pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
			pnMain.add(getVerticalStrut());
			pnMain.add(getLblGeneral());
			pnMain.add(getTaGeneral());
			pnMain.add(getVerticalStrut_1());
			pnMain.add(getLblCitas());
			pnMain.add(getTaCitas());
			pnMain.add(getVerticalStrut_2());
			pnMain.add(getLblEnfermedades());
			pnMain.add(getTaEnfermedades());
			pnMain.add(getVerticalStrut_3());
			pnMain.add(getLblVacunas());
			pnMain.add(getTaVacunas());
		}
		return pnMain;
	}

	private Component getVerticalStrut() {
		if (verticalStrut == null) {
			verticalStrut = Box.createVerticalStrut(20);
		}
		return verticalStrut;
	}

	private JLabel getLblGeneral() {
		if (lblGeneral == null) {
			lblGeneral = new JLabel("Informacion general:");
			lblGeneral.setFont(new Font("Cantarell", Font.BOLD, 18));
		}
		return lblGeneral;
	}

	private JTextArea getTaGeneral() {
		if (taGeneral == null) {
			taGeneral = new JTextArea();
			taGeneral.setEditable(false);
			taGeneral.setText(String.format("\nNOMBRE:\t%s\nAPELLIDO:\t%s\nCONTACTO:\t%d - %s\nOTROS DATOS:\t%s\n",
					paciente.getNombre(), paciente.getApellido(), paciente.getTelefono(), paciente.getCorreo(),
					paciente.getOtrosContactos()));
		}
		return taGeneral;
	}

	private Component getVerticalStrut_1() {
		if (verticalStrut_1 == null) {
			verticalStrut_1 = Box.createVerticalStrut(20);
		}
		return verticalStrut_1;
	}

	private JLabel getLblCitas() {
		if (lblCitas == null) {
			lblCitas = new JLabel("Citas:");
			lblCitas.setFont(new Font("Cantarell", Font.BOLD, 18));
		}
		return lblCitas;
	}

	private JTextArea getTaCitas() {
		if (taCitas == null) {
			taCitas = new JTextArea();
			taCitas.setEditable(false);
			String text = "";
			for (Cita c : gC.cargarCitasOrdenadas(paciente.getId())) {
				text += String.format("\nNº CITA:\t%d\nFECHA:\t%s, %s - %s\nMEDICOS:", c.getIdCita(), c.getFecha(), c.getHoraE(), c.getHoraS());
				for (Medico m : gC.cargarMedicos(c.getIdCita())) {
					text += String.format("\t%s %s\n", m.getNombre(), m.getEmail());
				}
				text += c.isUrgente() ? "URGENTE" : "NO URGENTE";
				text += String.format("\nSALA:\t%s", gS.cargarSala(c.getSala()));
				text += String.format("\nCONTACTO:\t%s - %s\n", c.getTelefonoCita(), c.getCorreoCita());
				text += c.isAcudio();
				text += String.format("\nCAUSA:\t%s", c.getCausaCita());
				text += "\n----------\n";
			}
			taCitas.setText(text);
		}
		return taCitas;
	}

	private Component getVerticalStrut_2() {
		if (verticalStrut_2 == null) {
			verticalStrut_2 = Box.createVerticalStrut(20);
		}
		return verticalStrut_2;
	}

	private JLabel getLblEnfermedades() {
		if (lblEnfermedades == null) {
			lblEnfermedades = new JLabel("Enfermedades:");
			lblEnfermedades.setFont(new Font("Cantarell", Font.BOLD, 18));
		}
		return lblEnfermedades;
	}

	private JTextArea getTaEnfermedades() {
		if (taEnfermedades == null) {
			taEnfermedades = new JTextArea();
			taEnfermedades.setEditable(false);
			String text = "";
			for (Enfermedad e : gP.cargarEnfermedades(paciente.getId())) {
				text += String.format("\nNOMBRE:\t%s\nDESCRIPCION:\t%s\n", e.getNombre(), e.getDescripcion());
				text += e.isEnCita() ? String.format("CITA:\t%d", e.getIdCita()) : "CITA:\tfuera de cita";
				text += "\n----------\n";
			}
			taEnfermedades.setText(text);
		}
		return taEnfermedades;
	}

	private Component getVerticalStrut_3() {
		if (verticalStrut_3 == null) {
			verticalStrut_3 = Box.createVerticalStrut(20);
		}
		return verticalStrut_3;
	}

	private JLabel getLblVacunas() {
		if (lblVacunas == null) {
			lblVacunas = new JLabel("Vacunas:");
			lblEnfermedades.setFont(new Font("Cantarell", Font.BOLD, 18));
		}
		return lblVacunas;
	}

	private JTextArea getTaVacunas() {
		if (taVacunas == null) {
			taVacunas = new JTextArea();
			taVacunas.setEditable(false);
			String text = "";
			for (Vacuna v : gP.cargarVacunas(paciente.getId())) {
				text += String.format("\nNOMBRE:\t%s\nDESCRIPCION:\t%s\n", v.getNombre(), v.getDescripcion());
				text += v.isEnCita() ? String.format("CITA:\t%d", v.getIdCita()) : "CITA:\tfuera de cita";
				text += "\n----------\n";
			}
			taVacunas.setText(text);
		}
		return taVacunas;
	}

	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton("Cerrar");
			btnCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCerrar;
	}
}
