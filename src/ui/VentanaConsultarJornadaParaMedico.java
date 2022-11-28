package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.jornada.Jornada;
import nexus.GestorEspecialidades;
import nexus.GestorJornada;

public class VentanaConsultarJornadaParaMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VentanaConsultarJornadasYBajasPorMedicos v;

	private GestorEspecialidades gestorE = new GestorEspecialidades();
	private GestorJornada gestorJ = new GestorJornada();

	private Jornada j;
	private String estado;

	private JPanel contentPane;
	private JPanel pnSuperior;
	private JPanel pnMedico;
	private JPanel pnLabelsMedico;
	private JPanel pnDatosMedico;
	private JLabel lbTituloId;
	private JLabel lbTituloNombre;
	private JLabel lbTituloApellidos;
	private JLabel lbTituloEmail;
	private JLabel lbTituloEspecialidad;
	private JLabel lbTituloDNI;
	private JLabel lbTituloColegiado;
	private JLabel lbId;
	private JLabel lbNombre;
	private JLabel lbApellidos;
	private JLabel lbEmail;
	private JLabel lbEspecialidad;
	private JLabel lbDNI;
	private JLabel lbColegiado;
	private JPanel pnJornadaID;
	private JLabel lbIDJornada;
	private JLabel lbID;
	private JPanel pnCentral;
	private JLabel lbEstadoJornada;
	private JLabel lbEstado;
	private JPanel pnNombre;
	private JPanel pnDias;
	private JPanel pnHoraInicio;
	private JPanel pnHoraFin;
	private JPanel pnFechaInicio;
	private JPanel pnFechaFin;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JButton btCancelar;
	private JLabel lbNombreJornada;
	private JTextField txtNombreJornada;
	private JPanel pnDisplayDias;
	private JPanel pnEditardias;
	private JToggleButton btL;
	private JToggleButton btM;
	private JToggleButton btX;
	private JToggleButton btJ;
	private JToggleButton btV;
	private JToggleButton btS;
	private JToggleButton btD;
	private JLabel lbDiasplayDias;
	private JLabel lbDias;
	private JLabel lbHoraInicio;
	private JFormattedTextField txtHoraInicio;
	private JLabel lbHoraFin;
	private JFormattedTextField txtHoraFin;
	private JLabel lbFechaInicio;
	private JFormattedTextField txtFechaInicio;
	private JLabel lbFechaFin;
	private JFormattedTextField txtFechaFin;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaConsultarJornadaParaMedico frame = new VentanaConsultarJornadaParaMedico();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * 
	 * @param ventanaConsultarJornadasYBajasPorMedicos
	 */
	public VentanaConsultarJornadaParaMedico(
			VentanaConsultarJornadasYBajasPorMedicos v, Jornada j,
			String estado) {
		this.v = v;
		this.j = j;
		this.estado = estado;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnSuperior(), BorderLayout.NORTH);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
	}

	private JPanel getPnSuperior() {
		if (pnSuperior == null) {
			pnSuperior = new JPanel();
			pnSuperior.setLayout(new GridLayout(2, 0, 0, 0));
			pnSuperior.add(getPnMedico());
			pnSuperior.add(getPnJornadaID());
		}
		return pnSuperior;
	}

	private JPanel getPnMedico() {
		if (pnMedico == null) {
			pnMedico = new JPanel();
			pnMedico.setLayout(new GridLayout(2, 0, 0, 0));
			pnMedico.add(getPnLabelsMedico());
			pnMedico.add(getPnDatosMedico());
		}
		return pnMedico;
	}

	private JPanel getPnLabelsMedico() {
		if (pnLabelsMedico == null) {
			pnLabelsMedico = new JPanel();
			pnLabelsMedico.setLayout(new GridLayout(0, 7, 0, 0));
			pnLabelsMedico.add(getLbTituloId());
			pnLabelsMedico.add(getLbTituloNombre());
			pnLabelsMedico.add(getLbTituloApellidos());
			pnLabelsMedico.add(getLbTituloEmail());
			pnLabelsMedico.add(getLbTituloEspecialidad());
			pnLabelsMedico.add(getLbTituloDNI());
			pnLabelsMedico.add(getLbTituloColegiado());
		}
		return pnLabelsMedico;
	}

	private JPanel getPnDatosMedico() {
		if (pnDatosMedico == null) {
			pnDatosMedico = new JPanel();
			pnDatosMedico.setLayout(new GridLayout(0, 7, 0, 0));
			pnDatosMedico.add(getLbId());
			pnDatosMedico.add(getLbNombre());
			pnDatosMedico.add(getLbApellidos());
			pnDatosMedico.add(getLbEmail());
			pnDatosMedico.add(getLbEspecialidad());
			pnDatosMedico.add(getLbDNI());
			pnDatosMedico.add(getLbColegiado());
		}
		return pnDatosMedico;
	}

	private JLabel getLbTituloId() {
		if (lbTituloId == null) {
			lbTituloId = new JLabel("ID", SwingConstants.CENTER);
		}
		return lbTituloId;
	}

	private JLabel getLbTituloNombre() {
		if (lbTituloNombre == null) {
			lbTituloNombre = new JLabel("Nombre", SwingConstants.CENTER);
		}
		return lbTituloNombre;
	}

	private JLabel getLbTituloApellidos() {
		if (lbTituloApellidos == null) {
			lbTituloApellidos = new JLabel("Apellidos", SwingConstants.CENTER);
		}
		return lbTituloApellidos;
	}

	private JLabel getLbTituloEmail() {
		if (lbTituloEmail == null) {
			lbTituloEmail = new JLabel("Email", SwingConstants.CENTER);
		}
		return lbTituloEmail;
	}

	private JLabel getLbTituloEspecialidad() {
		if (lbTituloEspecialidad == null) {
			lbTituloEspecialidad = new JLabel("Especialidad",
					SwingConstants.CENTER);
		}
		return lbTituloEspecialidad;
	}

	private JLabel getLbTituloDNI() {
		if (lbTituloDNI == null) {
			lbTituloDNI = new JLabel("DNI", SwingConstants.CENTER);
		}
		return lbTituloDNI;
	}

	private JLabel getLbTituloColegiado() {
		if (lbTituloColegiado == null) {
			lbTituloColegiado = new JLabel("Colegiado", SwingConstants.CENTER);
		}
		return lbTituloColegiado;
	}

	private JLabel getLbId() {
		if (lbId == null) {
			lbId = new JLabel("Sin ID", SwingConstants.CENTER);
			lbId.setText(v.getMedico().getId());
		}
		return lbId;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Sin nombre", SwingConstants.CENTER);
			lbNombre.setText(v.getMedico().getNombre());
		}
		return lbNombre;
	}

	private JLabel getLbApellidos() {
		if (lbApellidos == null) {
			lbApellidos = new JLabel("Sin apellidos", SwingConstants.CENTER);
			lbApellidos.setText(v.getMedico().getApellido());
		}
		return lbApellidos;
	}

	private JLabel getLbEmail() {
		if (lbEmail == null) {
			lbEmail = new JLabel("Sin email", SwingConstants.CENTER);
			lbEmail.setText(v.getMedico().getEmail());
		}
		return lbEmail;
	}

	private JLabel getLbEspecialidad() {
		if (lbEspecialidad == null) {
			lbEspecialidad = new JLabel("Sin especialidad",
					SwingConstants.CENTER);
			lbEspecialidad.setText(gestorE.buscarPorId(v.getMedico().getEspecialidad()).getNombre_esp());
		}
		return lbEspecialidad;
	}

	private JLabel getLbDNI() {
		if (lbDNI == null) {
			lbDNI = new JLabel("Sin DNI", SwingConstants.CENTER);
			lbDNI.setText(v.getMedico().getDni());
		}
		return lbDNI;
	}

	private JLabel getLbColegiado() {
		if (lbColegiado == null) {
			lbColegiado = new JLabel("Sin colegiado", SwingConstants.CENTER);
			lbColegiado.setText(v.getMedico().getColegiado());
		}
		return lbColegiado;
	}

	private JPanel getPnJornadaID() {
		if (pnJornadaID == null) {
			pnJornadaID = new JPanel();
			pnJornadaID.add(getLbIDJornada());
			pnJornadaID.add(getLbID());
			pnJornadaID.add(getLbEstadoJornada());
			pnJornadaID.add(getLbEstado());
		}
		return pnJornadaID;
	}

	private JLabel getLbIDJornada() {
		if (lbIDJornada == null) {
			lbIDJornada = new JLabel("Jornada ID:");
		}
		return lbIDJornada;
	}

	private JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("Sin ID");
			lbID.setText(j.getId());
		}
		return lbID;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(6, 0, 0, 0));
			pnCentral.add(getPnNombre());
			pnCentral.add(getPnDias());
			pnCentral.add(getPnHoraInicio());
			pnCentral.add(getPnHoraFin());
			pnCentral.add(getPnFechaInicio());
			pnCentral.add(getPnFechaFin());
		}
		return pnCentral;
	}

	private JLabel getLbEstadoJornada() {
		if (lbEstadoJornada == null) {
			lbEstadoJornada = new JLabel("Estado: ");
		}
		return lbEstadoJornada;
	}

	private JLabel getLbEstado() {
		if (lbEstado == null) {
			lbEstado = new JLabel("En curso");
			lbEstado.setText(estado);
		}
		return lbEstado;
	}

	private JPanel getPnNombre() {
		if (pnNombre == null) {
			pnNombre = new JPanel();
			pnNombre.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnNombre.add(getLbNombreJornada());
			pnNombre.add(getTxtNombreJornada());
		}
		return pnNombre;
	}

	private JPanel getPnDias() {
		if (pnDias == null) {
			pnDias = new JPanel();
			pnDias.setLayout(new GridLayout(2, 1, 0, 0));
			pnDias.add(getPnDisplayDias());
			pnDias.add(getPnEditardias());
		}
		return pnDias;
	}

	private JPanel getPnHoraInicio() {
		if (pnHoraInicio == null) {
			pnHoraInicio = new JPanel();
			pnHoraInicio.add(getLbHoraInicio());
			pnHoraInicio.add(getTxtHoraInicio());
		}
		return pnHoraInicio;
	}

	private JPanel getPnHoraFin() {
		if (pnHoraFin == null) {
			pnHoraFin = new JPanel();
			pnHoraFin.add(getLbHoraFin());
			pnHoraFin.add(getTxtHoraFin());
		}
		return pnHoraFin;
	}

	private JPanel getPnFechaInicio() {
		if (pnFechaInicio == null) {
			pnFechaInicio = new JPanel();
			pnFechaInicio.add(getLbFechaInicio());
			pnFechaInicio.add(getTxtFechaInicio());
		}
		return pnFechaInicio;
	}

	private JPanel getPnFechaFin() {
		if (pnFechaFin == null) {
			pnFechaFin = new JPanel();
			pnFechaFin.add(getLbFechaFin());
			pnFechaFin.add(getTxtFechaFin());
		}
		return pnFechaFin;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtAceptar());
			pnBotones.add(getBtCancelar());
		}
		return pnBotones;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarJornada();
					dispose();
				}
			});
		}
		return btAceptar;
	}

	protected void actualizarJornada() {
		j.setNombre(txtNombreJornada.getText());
		j.setDias(getDias());
		if (!txtHoraInicio.getText().equals("  :  ")) {
			j.setHoraComienzo(txtHoraInicio.getText());
		}
		if (!txtHoraFin.getText().equals("  :  ")) {
			j.setHoraFinal(txtHoraFin.getText());
		}

		if (!txtFechaInicio.getText().equals("  /  /    ")) {
			j.setInicio(txtFechaInicio.getText());
		}

		if (!txtFechaFin.getText().equals("  /  /    ")) {
			j.setFin(txtFechaFin.getText());
		}
		gestorJ.actualizarJornada(j);
	}

	private String getDias() {
		String dias = "";
		for (Component c : pnEditardias.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			if (b.isSelected())
				if (b.getText().equals("Miércoles"))
					dias += "X";
				else
					dias += b.getText().charAt(0);
		}
		return dias;
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					v.reset();
					dispose();
				}
			});
		}
		return btCancelar;
	}

	private JLabel getLbNombreJornada() {
		if (lbNombreJornada == null) {
			lbNombreJornada = new JLabel("Nombre: ");
		}
		return lbNombreJornada;
	}

	private JTextField getTxtNombreJornada() {
		if (txtNombreJornada == null) {
			txtNombreJornada = new JTextField();
			txtNombreJornada.setColumns(10);
			txtNombreJornada.setText(j.getNombre());
			if (estado.equals("terminada"))
				txtNombreJornada.setEditable(false);
		}
		return txtNombreJornada;
	}

	private JPanel getPnDisplayDias() {
		if (pnDisplayDias == null) {
			pnDisplayDias = new JPanel();
			pnDisplayDias.add(getLbDiasplayDias());
			pnDisplayDias.add(getLbDias());
		}
		return pnDisplayDias;
	}

	private JPanel getPnEditardias() {
		if (pnEditardias == null) {
			pnEditardias = new JPanel();
			pnEditardias.setLayout(new GridLayout(0, 7, 0, 0));
			pnEditardias.add(getBtL());
			pnEditardias.add(getBtM());
			pnEditardias.add(getBtX());
			pnEditardias.add(getBtJ());
			pnEditardias.add(getBtV());
			pnEditardias.add(getBtS());
			pnEditardias.add(getBtD());
			setDias();
		}
		return pnEditardias;
	}

	private JToggleButton getBtL() {
		if (btL == null) {
			btL = new JToggleButton("Lunes");
		}
		return btL;
	}

	private JToggleButton getBtM() {
		if (btM == null) {
			btM = new JToggleButton("Martes");
		}
		return btM;
	}

	private JToggleButton getBtX() {
		if (btX == null) {
			btX = new JToggleButton("Miércoles");
		}
		return btX;
	}

	private JToggleButton getBtJ() {
		if (btJ == null) {
			btJ = new JToggleButton("Jueves");
		}
		return btJ;
	}

	private JToggleButton getBtV() {
		if (btV == null) {
			btV = new JToggleButton("Viernes");
		}
		return btV;
	}

	private JToggleButton getBtS() {
		if (btS == null) {
			btS = new JToggleButton("Sábado");
		}
		return btS;
	}

	private JToggleButton getBtD() {
		if (btD == null) {
			btD = new JToggleButton("Domingo");
		}
		return btD;
	}

	private JLabel getLbDiasplayDias() {
		if (lbDiasplayDias == null) {
			lbDiasplayDias = new JLabel("Días: ");
		}
		return lbDiasplayDias;
	}

	private JLabel getLbDias() {
		if (lbDias == null) {
			lbDias = new JLabel("-------");
			lbDias.setText(j.getDias());
		}
		return lbDias;
	}

	private void setDias() {
		for (Component c : pnEditardias.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			if (b.getText().equals("Miércoles")) {
				if (j.getDias().contains("X")) {
					b.setSelected(true);
				}
			} else {
				if (j.getDias().contains(b.getText().charAt(0) + "")) {
					b.setSelected(true);
				} else {
					b.setSelected(false);
				}
				if (b.getText().equals("Miércoles"))
					if (j.getDias().contains("X")) {
						b.setSelected(true);
					}
				b.setEnabled(enCurso());
			}
		}
	}

	private JLabel getLbHoraInicio() {
		if (lbHoraInicio == null) {
			lbHoraInicio = new JLabel("Hora de inicio: ");
		}
		return lbHoraInicio;
	}

	private JFormattedTextField getTxtHoraInicio() {
		if (txtHoraInicio == null) {
			txtHoraInicio = new JFormattedTextField();
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHoraInicio = new JFormattedTextField(mf);
				txtHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);

				txtHoraInicio.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtHoraInicio.setText(j.getHoraComienzo());
			txtHoraInicio.setEditable(enCurso());
		}
		return txtHoraInicio;
	}

	private JLabel getLbHoraFin() {
		if (lbHoraFin == null) {
			lbHoraFin = new JLabel("Hora de fin: ");
		}
		return lbHoraFin;
	}

	private JFormattedTextField getTxtHoraFin() {
		if (txtHoraFin == null) {
			txtHoraFin = new JFormattedTextField();
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHoraFin = new JFormattedTextField(mf);
				txtHoraFin.setHorizontalAlignment(SwingConstants.CENTER);

				txtHoraFin.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtHoraFin.setText(j.getHoraFinal());
			txtHoraFin.setEditable(enCurso());
		}
		return txtHoraFin;
	}

	private JLabel getLbFechaInicio() {
		if (lbFechaInicio == null) {
			lbFechaInicio = new JLabel("Fecha de inicio: ");
		}
		return lbFechaInicio;
	}

	private JFormattedTextField getTxtFechaInicio() {
		if (txtFechaInicio == null) {
			txtFechaInicio = new JFormattedTextField();
			try {
				MaskFormatter mf = new MaskFormatter("##/##/####");
				txtFechaInicio = new JFormattedTextField(mf);
				txtFechaInicio.setHorizontalAlignment(SwingConstants.CENTER);
				txtFechaInicio.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtFechaInicio.setText(j.getInicio());
			txtFechaInicio.setEditable(enCurso());
		}
		return txtFechaInicio;
	}

	private JLabel getLbFechaFin() {
		if (lbFechaFin == null) {
			lbFechaFin = new JLabel("Fecha de fin: ");
		}
		return lbFechaFin;
	}

	private JFormattedTextField getTxtFechaFin() {
		if (txtFechaFin == null) {
			txtFechaFin = new JFormattedTextField();
			try {
				MaskFormatter mf = new MaskFormatter("##/##/####");
				txtFechaFin = new JFormattedTextField(mf);
				txtFechaFin.setHorizontalAlignment(SwingConstants.CENTER);
				txtFechaFin.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			txtFechaFin.setText(j.getFin());
			txtFechaFin.setEditable(enCurso());
		}
		return txtFechaFin;
	}

	private boolean enCurso() {
		return estado.equals("en curso") || estado.equals("por empezar");
	}

}
