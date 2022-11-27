package ui;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.Baja;
import nexus.GestorBajas;
import nexus.GestorEspecialidades;

public class VentanaConsultarBajaParaMedico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VentanaConsultarJornadasYBajasPorMedicos v;

	private GestorEspecialidades gestorE = new GestorEspecialidades();
	private GestorBajas gestorB = new GestorBajas();

	private Baja b;
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
	private JPanel pnBajaID;
	private JLabel lbIDBaja;
	private JLabel lbID;
	private JPanel pnCentral;
	private JLabel lbEstadoBaja;
	private JLabel lbEstado;
	private JPanel pnTipo;
	private JPanel pnHoraInicio;
	private JPanel pnHoraFin;
	private JPanel pnFechaInicio;
	private JPanel pnFechaFin;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JButton btCancelar;
	private JLabel lbTipoBaja;
	private JLabel lbHoraInicio;
	private JFormattedTextField txtHoraInicio;
	private JLabel lbHoraFin;
	private JFormattedTextField txtHoraFin;
	private JLabel lbFechaInicio;
	private JFormattedTextField txtFechaInicio;
	private JLabel lbFechaFin;
	private JFormattedTextField txtFechaFin;
	private JLabel lbTipo;
	private JPanel pnObservaciones;
	private JLabel lbObservaciones;
	private JScrollPane scrObservaciones;
	private JTextArea txtObservaciones;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaConsultarBajaParaMedico frame = new VentanaConsultarBajaParaMedico();
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
	public VentanaConsultarBajaParaMedico(
			VentanaConsultarJornadasYBajasPorMedicos v, Baja b, String estado) {
		this.v = v;
		this.b = b;
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
			pnSuperior.add(getPnBajaID());
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

	private JPanel getPnBajaID() {
		if (pnBajaID == null) {
			pnBajaID = new JPanel();
			pnBajaID.add(getLbIDBaja());
			pnBajaID.add(getLbID());
			pnBajaID.add(getLbEstadoBaja());
			pnBajaID.add(getLbEstado());
		}
		return pnBajaID;
	}

	private JLabel getLbIDBaja() {
		if (lbIDBaja == null) {
			lbIDBaja = new JLabel("Baja ID:");
		}
		return lbIDBaja;
	}

	private JLabel getLbID() {
		if (lbID == null) {
			lbID = new JLabel("Sin ID");
			lbID.setText(b.getId());
		}
		return lbID;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(6, 0, 0, 0));
			pnCentral.add(getPnTipo());
			pnCentral.add(getPnHoraInicio());
			pnCentral.add(getPnHoraFin());
			pnCentral.add(getPnFechaInicio());
			pnCentral.add(getPnFechaFin());
			pnCentral.add(getPnObservaciones());
		}
		return pnCentral;
	}

	private JLabel getLbEstadoBaja() {
		if (lbEstadoBaja == null) {
			lbEstadoBaja = new JLabel("Estado: ");
		}
		return lbEstadoBaja;
	}

	private JLabel getLbEstado() {
		if (lbEstado == null) {
			lbEstado = new JLabel("En curso");
			lbEstado.setText(estado);
		}
		return lbEstado;
	}

	private JPanel getPnTipo() {
		if (pnTipo == null) {
			pnTipo = new JPanel();
			pnTipo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnTipo.add(getLbTipoBaja());
			pnTipo.add(getLbTipo());
		}
		return pnTipo;
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
					actualizarBaja();
					dispose();
				}
			});
		}
		return btAceptar;
	}

	protected void actualizarBaja() {
		if (!txtHoraInicio.getText().equals("  :  ")) {
			b.sethInicio(txtHoraInicio.getText());
		}
		if (!txtHoraFin.getText().equals("  :  ")) {
			b.sethFin(txtHoraFin.getText());
		}

		if (!txtFechaInicio.getText().equals("  /  /    ")) {
			b.setfInicio(txtFechaInicio.getText());
		}

		if (!txtFechaFin.getText().equals("  /  /    ")) {
			b.setfFin(txtFechaFin.getText());
		}
		b.setObservaciones(txtObservaciones.getText());
		gestorB.actualizarBajar(b);
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btCancelar;
	}

	private JLabel getLbTipoBaja() {
		if (lbTipoBaja == null) {
			lbTipoBaja = new JLabel("Tipo de baja: ");
		}
		return lbTipoBaja;
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
			txtHoraInicio.setText(b.gethInicio());
			txtHoraInicio.setEditable(enCurso());
		}
		return txtHoraInicio;
	}

	private boolean enCurso() {
		return estado.equals("en curso") || estado.equals("por empezar");
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
			txtHoraFin.setText(b.gethFin());
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
			txtFechaInicio.setText(b.getfInicio());
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
			txtFechaFin.setText(b.getfFin());
			txtFechaFin.setEditable(enCurso());
		}
		return txtFechaFin;
	}

	private JLabel getLbTipo() {
		if (lbTipo == null) {
			lbTipo = new JLabel("Sin tipo");
			lbTipo.setText(b.getTipo().name());
		}
		return lbTipo;
	}

	private JPanel getPnObservaciones() {
		if (pnObservaciones == null) {
			pnObservaciones = new JPanel();
			pnObservaciones.setLayout(new BorderLayout(0, 0));
			pnObservaciones.add(getLbObservaciones(), BorderLayout.NORTH);
			pnObservaciones.add(getScrObservaciones(), BorderLayout.CENTER);
		}
		return pnObservaciones;
	}

	private JLabel getLbObservaciones() {
		if (lbObservaciones == null) {
			lbObservaciones = new JLabel("Obsevaciones: ",
					SwingConstants.CENTER);
		}
		return lbObservaciones;
	}

	private JScrollPane getScrObservaciones() {
		if (scrObservaciones == null) {
			scrObservaciones = new JScrollPane(getTxtObservaciones());
		}
		return scrObservaciones;
	}

	private JTextArea getTxtObservaciones() {
		if (txtObservaciones == null) {
			txtObservaciones = new JTextArea();
			txtObservaciones.setText(b.getObservaciones());
			txtObservaciones.setEditable(enCurso());
		}
		return txtObservaciones;
	}
}
