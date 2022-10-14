package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.Medico;
import logic.Paciente;
import nexus.GestorCitas;
import nexus.GestorMedico;
import nexus.GestorPacientes;
import util.DataBase;

public class VentanaCrearCita extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipalCrearCita;
	private JPanel panelCampos;
	private JPanel panelPaciente;
	private JLabel lbPaciente;
	private JPanel panelHoraEntrada;
	private JLabel lbHoraEntrada;
	private JPanel panelFecha;
	private JLabel lbFecha;
	private JPanel panelSala;
	private JLabel lbSala;
	private JPanel panelUrgente;
	private JLabel lbUrgente;
	private JFormattedTextField tfHoraEntrada;
	private JFormattedTextField tfFecha;
	private JTextField tfSala;
	private JPanel panelRb;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JPanel panelTitulo;
	private JLabel lbTitulo;
	private JPanel panelBotonCrear;
	private JButton btCrear;

	private ButtonGroup bgUrgente;
	private GestorCitas gC;
	private GestorPacientes gP;
	private GestorMedico gM;
	private DialogoInfoContacto dialContacto;
	private JPanel panelContacto;
	private JButton btContacto;
	private JComboBox cbPacientes;
	private JPanel panelHoraSalida;
	private JLabel lbHoraSalida;
	private JFormattedTextField tfHoraSalida;
	private JPanel panelMedicos;
	private JLabel lblMedico;
	private JComboBox<Medico> cbMedicos;
	private JPanel panelAdd;
	private JButton btAdd;
	private JPanel panelMedicosAgregados;
	private JTextArea taMedicos;
	private Component horizontalStrut;
	private Component horizontalStrut_1;

	/**
	 * Create the frame.
	 */
	public VentanaCrearCita() {
		gP = new GestorPacientes();
		gC = new GestorCitas();
		gM = new GestorMedico();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 408);
		panelPrincipalCrearCita = new JPanel();
		panelPrincipalCrearCita.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgUrgente = new ButtonGroup();
		setContentPane(panelPrincipalCrearCita);
		panelPrincipalCrearCita.setLayout(new BorderLayout(0, 0));
		panelPrincipalCrearCita.add(getPanelCampos(), BorderLayout.CENTER);
		panelPrincipalCrearCita.add(getPanelTitulo(), BorderLayout.NORTH);
	}

	private JPanel getPanelCampos() {
		if (panelCampos == null) {
			panelCampos = new JPanel();
			panelCampos.setLayout(new GridLayout(0, 1, 10, 10));
			panelCampos.add(getPanelMedicos());
			panelCampos.add(getPanelMedicosAgregados());
			panelCampos.add(getPanelPaciente());
			panelCampos.add(getPanelHoraEntrada());
			panelCampos.add(getPanelHoraSalida());
			panelCampos.add(getPanelFecha());
			panelCampos.add(getPanelSala());
			panelCampos.add(getPanelUrgente());
			panelCampos.add(getPanelBotonCrear());
		}
		return panelCampos;
	}

	private JPanel getPanelPaciente() {
		if (panelPaciente == null) {
			panelPaciente = new JPanel();
			panelPaciente.setLayout(new GridLayout(0, 3, 10, 0));
			panelPaciente.add(getLbPaciente());
			panelPaciente.add(getCbPacientes());
			panelPaciente.add(getPanelContacto_1());
		}
		return panelPaciente;
	}

	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente:");
			lbPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbPaciente;
	}

	private JPanel getPanelHoraEntrada() {
		if (panelHoraEntrada == null) {
			panelHoraEntrada = new JPanel();
			panelHoraEntrada.setLayout(new GridLayout(0, 3, 10, 0));
			panelHoraEntrada.add(getLbHora_1());
			panelHoraEntrada.add(getTfHoraEntrada());
		}
		return panelHoraEntrada;
	}

	private JLabel getLbHora_1() {
		if (lbHoraEntrada == null) {
			lbHoraEntrada = new JLabel("Hora entrada :");
			lbHoraEntrada.setLabelFor(getTfHoraEntrada());
			lbHoraEntrada.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHoraEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHoraEntrada;
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

	private JTextField getTfHoraEntrada() {
		if (tfHoraEntrada == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				tfHoraEntrada = new JFormattedTextField(mf);
				tfHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);

				tfHoraEntrada.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return tfHoraEntrada;
	}

	private JTextField getTfFecha() {
		if (tfFecha == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##/##/####");
				tfFecha = new JFormattedTextField(mf);
				tfFecha.setHorizontalAlignment(SwingConstants.CENTER);
				tfFecha.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return tfFecha;
	}

	private JTextField getTfSala() {
		if (tfSala == null) {
			tfSala = new JTextField();
			tfSala.setHorizontalAlignment(SwingConstants.LEFT);
			tfSala.setColumns(10);
		}
		return tfSala;
	}

	private JPanel getPanelRb() {
		if (panelRb == null) {
			panelRb = new JPanel();
			bgUrgente.add(getRbSi());
			bgUrgente.add(getRbNo());
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

	private JPanel getPanelBotonCrear() {
		if (panelBotonCrear == null) {
			panelBotonCrear = new JPanel();
			panelBotonCrear.add(getBtCrear());
		}
		return panelBotonCrear;
	}

	private JButton getBtCrear() {
		if (btCrear == null) {
			btCrear = new JButton("Crear cita");
			btCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarYCrear();

				}

			});
		}
		return btCrear;
	}

	protected void comprobarYCrear() {
		boolean valido = true;
		String[] nomApe = getCbPacientes().getSelectedItem().toString().split(" ");
		String nombre = nomApe[0];
		String apellido = nomApe[1];
		int idPaciente = gP.buscarIdPaciente(nombre, apellido);
		if (idPaciente == -1)
			valido = false;
		String horaE = "";
		String horaS = "";
		if (comprobarHoraDisponible(nomApe)) {
			horaE = getTfHoraEntrada().getText();
			horaS = getTfHoraSalida().getText();
		} else
			valido = false;

		String fecha = "";
		if (comprobarFechaDisponible(nomApe)) {
			fecha = getTfFecha().getText();
		} else
			valido = false;

		int sala = -1;
		if (!getTfSala().getText().isBlank() && !getTfSala().getText().isEmpty() && !getTfSala().getText().equals("-1"))
			sala = Integer.parseInt(getTfSala().getText());
		else
			valido = false;
		boolean urg;
		if (getRbSi().isSelected()) {
			urg = true;
		} else
			urg = false;

		if (valido)
			crearCita(idPaciente, nombre, fecha, horaE, horaS, sala, urg);

	}

	private boolean comprobarFechaDisponible(String[] nomApe) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean comprobarHoraDisponible(String[] nomApe) {
		// TODO Auto-generated method stub
		return true;
	}

	private void crearCita(int idPaciente, String nombre, String fecha, String horaE, String horaS, int sala,
			boolean urg) {

		gC.nuevaCita(idPaciente, nombre, fecha, horaE, horaS, sala, urg);
		resetear();

	}

	private void resetear() {
		getCbPacientes().setSelectedIndex(0);
		getTfFecha().setText("");
		getTfHoraEntrada().setText("");
		getTfHoraSalida().setText("");
		getTfSala().setText("");
		getRbSi().setSelected(false);
		getRbNo().setSelected(false);

	}

	private void mostrarVentanaInfoContacto() {
		String[] nomApe = getCbPacientes().getSelectedItem().toString().split(" ");
		dialContacto = new DialogoInfoContacto(gP, gP.buscarIdPaciente(nomApe[0], nomApe[1]));
		dialContacto.setVisible(true);
	}

	private JPanel getPanelContacto_1() {
		if (panelContacto == null) {
			panelContacto = new JPanel();
			panelContacto.add(getBtContacto_1());
		}
		return panelContacto;
	}

	private JButton getBtContacto_1() {
		if (btContacto == null) {
			btContacto = new JButton("Info contacto");
			btContacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaInfoContacto();
				}

			});
		}
		return btContacto;
	}

	private JComboBox getCbPacientes() {
		if (cbPacientes == null) {
			cbPacientes = new JComboBox();
			cargarComboPacientes();
		}
		return cbPacientes;
	}

	private void cargarComboPacientes() {
		for (Paciente p : gP.getListaPacientes()) {
			getCbPacientes().addItem(p.getNombre() + " " + p.getApellido());
		}
	}

	private JPanel getPanelHoraSalida() {
		if (panelHoraSalida == null) {
			panelHoraSalida = new JPanel();
			panelHoraSalida.setLayout(new GridLayout(0, 3, 10, 0));
			panelHoraSalida.add(getLbHoraSalida());
			panelHoraSalida.add(getTfHoraSalida());
		}
		return panelHoraSalida;
	}

	private JLabel getLbHoraSalida() {
		if (lbHoraSalida == null) {
			lbHoraSalida = new JLabel("Hora salida:");
			lbHoraSalida.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHoraSalida.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHoraSalida;
	}

	private JFormattedTextField getTfHoraSalida() {
		if (tfHoraSalida == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				tfHoraSalida = new JFormattedTextField(mf);
				tfHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);

				tfHoraSalida.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return tfHoraSalida;
	}

	private JPanel getPanelMedicos() {
		if (panelMedicos == null) {
			panelMedicos = new JPanel();
			panelMedicos.setLayout(new GridLayout(0, 3, 10, 0));
			panelMedicos.add(getLblMedico());
			panelMedicos.add(getCbMedicos());
			panelMedicos.add(getPanelAdd());
		}
		return panelMedicos;
	}

	private JLabel getLblMedico() {
		if (lblMedico == null) {
			lblMedico = new JLabel("Medicos:");
			lblMedico.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblMedico;
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

	private JPanel getPanelAdd() {
		if (panelAdd == null) {
			panelAdd = new JPanel();
			panelAdd.add(getBtAdd());
		}
		return panelAdd;
	}

	private JButton getBtAdd() {
		if (btAdd == null) {
			btAdd = new JButton("Agregar");
			btAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					moverMedicoATxtArea();
				}
			});
		}
		return btAdd;
	}

	private void moverMedicoATxtArea() {
		if (gC.agregarMedico((Medico) getCbMedicos().getSelectedItem())) {
			JOptionPane.showMessageDialog(this, "Este medico ya se ha agregado");
		}
		StringBuilder sb = new StringBuilder("");
		for (Medico m : gC.getMedicosAgregados()) {
			sb.append(m + "\n");
		}
		getTaMedicos().setText(sb.toString());
	}

	private JPanel getPanelMedicosAgregados() {
		if (panelMedicosAgregados == null) {
			panelMedicosAgregados = new JPanel();
			panelMedicosAgregados.setLayout(new GridLayout(0, 3, 0, 0));
			panelMedicosAgregados.add(getHorizontalStrut());
			panelMedicosAgregados.add(getTaMedicos());
			panelMedicosAgregados.add(getHorizontalStrut_1());
		}
		return panelMedicosAgregados;
	}

	private JTextArea getTaMedicos() {
		if (taMedicos == null) {
			taMedicos = new JTextArea();
			taMedicos.setText("Aun no se han agregado medicos");
			taMedicos.setRows(10);
		}
		return taMedicos;
	}

	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}

	private Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}
}
