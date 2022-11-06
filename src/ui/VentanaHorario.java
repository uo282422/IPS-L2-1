package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;

import logic.Medico;
import logic.Paciente;
import logic.Sala;
import logic.cita.Cita;
import nexus.GestorCitas;
import nexus.GestorMedicos;
import nexus.GestorPacientes;
import nexus.GestorSalas;

public class VentanaHorario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel pnHeader;
	private JComboBox<Medico> cbMedicos;
	private JScrollPane spHorario;
	private JPanel pnResizeContainer;
	private JPanel pnHorario;
	private JCalendar calFecha = new JCalendar();

	private GestorCitas gC;
	private GestorPacientes gP;
	private GestorSalas gS;
	private GestorMedicos gM;
	private Component horizontalStrut;
	private JButton btnVerHorario;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private JPanel pnCbMedicos;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaHorario frame = new VentanaHorario();
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
	public VentanaHorario() {

		this.gC = new GestorCitas();
		this.gP = new GestorPacientes();
		this.gS = new GestorSalas(gC);
		this.gM = new GestorMedicos();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnHeader(), BorderLayout.PAGE_START);
		contentPane.add(getSpHorario(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JPanel getPnHeader() {
		if (pnHeader == null) {
			pnHeader = new JPanel();
			pnHeader.setLayout(new BoxLayout(pnHeader, BoxLayout.X_AXIS));
			pnHeader.add(getCalFecha());
			pnHeader.add(getHorizontalStrut_2());
			pnHeader.add(getHorizontalStrut());
			pnHeader.add(getPnCbMedicos());
			pnHeader.add(getHorizontalStrut_3());
			pnHeader.add(getHorizontalStrut_1_1());
			pnHeader.add(getBtnVerHorario());
		}
		return pnHeader;
	}

	private JComboBox<Medico> getCbMedicos() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox<Medico>();
			for (Medico m : gM.getMedicos()) {
				cbMedicos.addItem(m);
			}
		}
		return cbMedicos;
	}

	private JScrollPane getSpHorario() {
		if (spHorario == null) {
			spHorario = new JScrollPane();
			spHorario.setViewportView(getPnResizeContainer());
		}
		return spHorario;
	}

	private JPanel getPnResizeContainer() {
		if (pnResizeContainer == null) {
			pnResizeContainer = new JPanel();
			pnResizeContainer.setLayout(new BoxLayout(pnResizeContainer, BoxLayout.Y_AXIS));
			pnResizeContainer.add(getPnHorario());
		}
		return pnResizeContainer;
	}

	@SuppressWarnings("deprecation")
	private JPanel getPnHorario() {
		if (pnHorario == null) {
			pnHorario = new JPanel();
			pnHorario.setLayout(new GridLayout(0, 1, 0, 0));

			String idMedico = ((Medico) getCbMedicos().getSelectedItem()).getId();
			Date fecha = getCalFecha().getDate();
			String fechaFormateada = String.format("%s/%s/%d",
					String.format("%1$2s", fecha.getDate()).replace(' ', '0'),
					String.format("%1$2s", fecha.getMonth() + 1).replace(' ', '0'), fecha.getYear() + 1900);

			System.out.println(String.format("id=%s - fecha=%s", idMedico, fechaFormateada));

			for (Cita c : gC.cargarCitasOrdenadas(idMedico, fechaFormateada)) {
				pnHorario.add(crearPanelCita(c));
				pnHorario.validate();
				pnHorario.repaint();
			}
			
		}
		return pnHorario;
	}

	private JPanel crearPanelCita(Cita c) {
		JPanel panelCita = new JPanel();
		panelCita.setLayout(new GridLayout(0, 3));
		panelCita.setBorder(new LineBorder(new Color(0, 0, 0)));

		JPanel panelBasicInfo = new JPanel();
		Paciente p = gP.getPaciente(c.getIdPaciente());
		Sala s = gS.cargarSala(c.getSala());
		panelBasicInfo.setLayout(new GridLayout(5, 0));
		panelBasicInfo.add(new Label(String.format("%s - %s", c.getHoraE(), c.getHoraS())));
		panelBasicInfo.add(new Label("    "));
		panelBasicInfo.add(new Label(String.format("PACIENTE: %s %s", p.getNombre(), p.getApellido())));
		panelBasicInfo.add(new Label("    "));
		panelBasicInfo.add(new Label(String.format("SALA: %s", s.getNombre())));

		JPanel panelEspaciado = new JPanel();

		JButton btnAccesoCita = new JButton("Acceder a la cita");
		btnAccesoCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirDetallesCita(c.getIdCita());
			}
		});

		panelCita.add(panelBasicInfo);
		panelCita.add(panelEspaciado);
		panelCita.add(btnAccesoCita);

		return panelCita;
	}

	private JCalendar getCalFecha() {
		if (calFecha == null) {
			calFecha = new JCalendar();
			calFecha.getDayChooser().setAlwaysFireDayProperty(true);
		}
		return calFecha;
	}

	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}

	private JButton getBtnVerHorario() {
		if (btnVerHorario == null) {
			btnVerHorario = new JButton("Ver Horario");
			btnVerHorario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pnHorario = null;
					getPnResizeContainer().add(getPnHorario());
					spHorario.validate();
					spHorario.repaint();
				}
			});
		}
		return btnVerHorario;
	}

	private Component getHorizontalStrut_1_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}

	private Component getHorizontalStrut_2() {
		if (horizontalStrut_2 == null) {
			horizontalStrut_2 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_2;
	}

	private Component getHorizontalStrut_3() {
		if (horizontalStrut_3 == null) {
			horizontalStrut_3 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_3;
	}

	private JPanel getPnCbMedicos() {
		if (pnCbMedicos == null) {
			pnCbMedicos = new JPanel();
			pnCbMedicos.setLayout(new BorderLayout(0, 0));
			pnCbMedicos.add(getCbMedicos(), BorderLayout.CENTER);
		}
		return pnCbMedicos;
	}

	@SuppressWarnings("deprecation")
	private void abrirDetallesCita(int idCita) {
		// Abrir la ventana de ManuH
		VentanaCita vC=new VentanaCita(idCita+"");
		vC.show();
	}
}
