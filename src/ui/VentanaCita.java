package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.Prescripcion;
import logic.cita.Cita;
import logic.cita.Enum_acudio;
import logic.diagnostico.Diagnostico;
import logic.procedimiento.Procedimiento;
import nexus.GestorCitas;

public class VentanaCita extends JFrame {

	private Cita c;
	private GestorCitas gestor = new GestorCitas();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnCentral;
	private JPanel pnInferior;
	private JButton btAceptar;
	private JPanel pnAcude;
	private JLabel lbAcude;
	private JButton btVerHistorial;
	private JPanel pnRBtAcude;
	private ButtonGroup rdBtsAcude = new ButtonGroup();
	private JRadioButton rdBtSiAcude;
	private JRadioButton rdBtNoAcude;
	private JRadioButton rdBtNoFiguraAcude;
	private JList<String> listCausasAñadidas;
	private JLabel lbCausasAñadidas;
	private JPanel pnCamposGenerales;
	private JPanel pnPreviewGenerales;
	private JScrollPane spPreview;
	private JTextArea taPreview;
	private JPanel pnHora;
	private JLabel lbHora;
	private JButton btAlterar;
	private Component horizontalStrut;
	private JPanel pnCamposCausas;
	private JPanel pnCamposProcedimientos;
	private JPanel pnPrescripcionesCombo;
	private JPanel pnPreviewCausas;
	private JPanel pnPreviewProcedimientos;
	private JPanel pnPreviewPrescripciones;
	private JLabel lbCausas;
	private JComboBox<String> cmbCausas;
	private JButton btAñadirCausas;
	private JButton btEliminarCausas;
	private JButton btNuevaCausa;
	private JLabel lbPrescripciones;
	private JComboBox<Prescripcion> cbPrescripciones;
	private JButton btnAddPrescripciones;
	private JPanel pnCamposPrescripciones;
	private JPanel pnPrescripcionesDetalles;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	private Component horizontalStrut_1;
	private JLabel lblIntervalo;
	private JSpinner spIntervalo;
	private JLabel lblIntervaloHoras;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component horizontalStrut_4;
	private JLabel lblDuracion;
	private JSpinner spDuracion;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private JLabel lblOtrosDatos;
	private JTextField txtOtrosDatos;
	private JLabel lblPrescripcionesAdded;
	private JList<Prescripcion> listPrescripcionesAdded;
	private JPanel pnCamposDiagnosticos;
	private JPanel pnPreviewDiagnosticos;
	private JLabel lblProcedimientosAdded;
	private JList<Procedimiento> listProcedimientosAdded;
	private JLabel lblDiagnosticosAdded;
	private JList<Diagnostico> listDiagnosticosAdded;
	private JButton btnProcedimientosAdd;
	private JButton btnProcedimientosRemove;
	private JButton btnDiagnosticosAdd;
	private JButton btnDiagnosticosRemove;
	private Component horizontalStrut_7;
	private Component horizontalStrut_8;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1041, 732);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
		contentPane.add(getPnInferior(), BorderLayout.SOUTH);
		contentPane.add(getBtVerHistorial(), BorderLayout.NORTH);
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(5, 2, 0, 0));
			pnCentral.add(getPnCamposGenerales());
			pnCentral.add(getPnPreviewGenerales());
			pnCentral.add(getPnCamposCausas());
			pnCentral.add(getPnPreviewCausas());
			pnCentral.add(getPnCamposProcedimientos());
			pnCentral.add(getPnPreviewProcedimientos());
			pnCentral.add(getPnCamposDiagnosticos());
			pnCentral.add(getPnPreviewDiagnosticos());
			pnCentral.add(getPnCamposPrescripciones());
			pnCentral.add(getPnPreviewPrescripciones());
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
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.setAcudio(acudio());
					guardarDatos();
					JOptionPane.showMessageDialog(null, "La información de la cita se ha guaradado.");
					dispose();
				}
			});
		}
		return btAceptar;
	}

	protected Enum_acudio acudio() {
		if (rdBtSiAcude.isSelected())
			return Enum_acudio.ACUDE;
		else if (rdBtNoAcude.isSelected())
			return Enum_acudio.NO_ACUDE;
		return Enum_acudio.NO_FIGURA;
	}

	/**
	 * Llama al gestor para actualizar la cita.
	 */
	protected void guardarDatos() {
		gestor.actualizarCita(c);
	}

	private JPanel getPnAcude() {
		if (pnAcude == null) {
			pnAcude = new JPanel();
			pnAcude.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnAcude.add(getLbAcude());
			pnAcude.add(getPnRBtAcude());
		}
		return pnAcude;
	}

	private JLabel getLbAcude() {
		if (lbAcude == null) {
			lbAcude = new JLabel("Acude:");
		}
		return lbAcude;
	}

//	private JPanel getPnCausas() {
//		if (pnCausas == null) {
//			pnCausas = new JPanel();
//			pnCausas.setLayout(new BoxLayout(pnCausas, BoxLayout.Y_AXIS));
//			pnCausas.add(getPnCausasSuperior());
//			pnCausas.add(getPnCausasInferior());
//
//		}
//		return pnCausas;
//	}

	private JButton getBtVerHistorial() {
		if (btVerHistorial == null) {
			btVerHistorial = new JButton("Ver historial");
			btVerHistorial.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verHistorial();
				}
			});
		}
		return btVerHistorial;
	}

	protected void verHistorial() {
		HistorialPaciente hP = new HistorialPaciente(c.getIdPaciente());
		hP.setVisible(true);
	}

	private JPanel getPnRBtAcude() {
		if (pnRBtAcude == null) {
			pnRBtAcude = new JPanel();
			pnRBtAcude.add(getRdBtNoFiguraAcude());
			pnRBtAcude.add(getRdBtSiAcude());
			pnRBtAcude.add(getRdBtNoAcude());
			pnRBtAcude.add(getHorizontalStrut());
		}
		return pnRBtAcude;
	}

	private JRadioButton getRdBtSiAcude() {
		if (rdBtSiAcude == null) {
			rdBtSiAcude = new JRadioButton("Sí");
			rdBtsAcude.add(rdBtSiAcude);
		}
		return rdBtSiAcude;
	}

	private JRadioButton getRdBtNoAcude() {
		if (rdBtNoAcude == null) {
			rdBtNoAcude = new JRadioButton("No");
			rdBtsAcude.add(rdBtNoAcude);
		}
		return rdBtNoAcude;
	}

	private JRadioButton getRdBtNoFiguraAcude() {
		if (rdBtNoFiguraAcude == null) {
			rdBtNoFiguraAcude = new JRadioButton("No figura");
			rdBtNoFiguraAcude.setSelected(true);
			rdBtsAcude.add(rdBtNoFiguraAcude);
		}
		return rdBtNoFiguraAcude;
	}

	/**
	 * Este método recibe una lista y devuelve un modelo de Combo lleno con la lista
	 * recibida.
	 * 
	 * @param l List<String> con la que se quiere generar el modelo de combo
	 * @return DefaultComboBoxModel<String> conteniendo la lista recibida.
	 */
	private DefaultComboBoxModel<String> setUpComboModel(List<String> l) {
		String[] array = l.toArray(new String[l.size()]);
		return new DefaultComboBoxModel<String>(array);
	}

	private void updateListaCausas() {
		listCausasAñadidas.setModel(toListModel(c.getCausas()));
	}

	private ListModel<String> toListModel(List<String> l) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String c : l)
			model.addElement(c);
		return model;
	}

	private JList<String> getListCausasAñadidas() {
		if (listCausasAñadidas == null) {
			listCausasAñadidas = new JList<String>();
			listCausasAñadidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listCausasAñadidas;
	}

	private JLabel getLbCausasAñadidas() {
		if (lbCausasAñadidas == null) {
			lbCausasAñadidas = new JLabel("Causas añadidas:");
		}
		return lbCausasAñadidas;
	}

	private JPanel getPnCamposGenerales() {
		if (pnCamposGenerales == null) {
			pnCamposGenerales = new JPanel();
			FlowLayout fl_pnCamposGenerales = (FlowLayout) pnCamposGenerales.getLayout();
			fl_pnCamposGenerales.setAlignment(FlowLayout.LEFT);
			pnCamposGenerales.add(getPnAcude());
			pnCamposGenerales.add(getPnHora());
		}
		return pnCamposGenerales;
	}

	private JPanel getPnPreviewGenerales() {
		if (pnPreviewGenerales == null) {
			pnPreviewGenerales = new JPanel();
			pnPreviewGenerales.setLayout(new BorderLayout(0, 0));
			pnPreviewGenerales.add(getSpPreview(), BorderLayout.CENTER);
		}
		return pnPreviewGenerales;
	}

	private JScrollPane getSpPreview() {
		if (spPreview == null) {
			spPreview = new JScrollPane();
			spPreview.setViewportView(getTaPreview());
		}
		return spPreview;
	}

	private JTextArea getTaPreview() {
		if (taPreview == null) {
			taPreview = new JTextArea();
			taPreview.setEditable(false);
		}
		refrescarInfo();
		return taPreview;
	}

	protected void refrescarInfo() {
		StringBuilder sb = new StringBuilder(String.format("  CITA Nº%d\n  --------------------\n\n", c.getIdCita()));
		sb.append(String.format("  Nombre del paciente: %s %s\n", c.getNombrePaciente(), c.getApellidoPaciente()));
		sb.append(String.format("  Hora: %s\n", c.getHoraE()));
		sb.append(String.format("  Fecha: %s\n", c.getFecha()));
		sb.append(String.format("  Sala: %d\n", c.getSala()));
		sb.append(String.format("  Urgente: %s\n", c.isAcudio().toString()));
		sb.append(String.format("  Hora de entrada: %s\n",
				c.getHoraEntrada() == null ? "NO ESTABLECIDA" : c.getHoraEntrada()));
		sb.append(String.format("  Hora de salida: %s\n",
				c.getHoraSalida() == null ? "NO ESTABLECIDA" : c.getHoraSalida()));

		taPreview.setText(sb.toString());
		
		final DefaultListModel<Diagnostico> modelD = new DefaultListModel<Diagnostico>();
		for (Diagnostico d : c.getDiagnosticos()) {
			modelD.addElement(d);
		}
		getListDiagnosticosAdded().setModel(modelD);
		
		final DefaultListModel<Procedimiento> modelP = new DefaultListModel<Procedimiento>();
		for (Procedimiento p : c.getProcedimientos()) {
			modelP.addElement(p);
		}
		getListProcedimientosAdded().setModel(modelP);
		
		validate();
	}

	private JPanel getPnHora() {
		if (pnHora == null) {
			pnHora = new JPanel();
			pnHora.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnHora.add(getLbHora());
			pnHora.add(getBtAlterar());
		}
		return pnHora;
	}

	private JLabel getLbHora() {
		if (lbHora == null) {
			lbHora = new JLabel("Hora Entrada/Salida");
		}
		return lbHora;
	}

	private JButton getBtAlterar() {
		if (btAlterar == null) {
			btAlterar = new JButton("Añadir");
			btAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaHora();
				}
			});
		}
		return btAlterar;
	}

	@SuppressWarnings("deprecation")
	private void abrirVentanaHora() {
		VentanaHora vh = new VentanaHora(c, this);
		vh.show();
	}

	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}

	private JPanel getPnCamposCausas() {
		if (pnCamposCausas == null) {
			pnCamposCausas = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnCamposCausas.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnCamposCausas.add(getLbCausas());
			pnCamposCausas.add(getCmbCausas());
			pnCamposCausas.add(getBtAñadirCausas());
			pnCamposCausas.add(getBtEliminarCausas());
			pnCamposCausas.add(getBtNuevaCausa());
		}
		return pnCamposCausas;
	}

	private JPanel getPnCamposProcedimientos() {
		if (pnCamposProcedimientos == null) {
			pnCamposProcedimientos = new JPanel();
			pnCamposProcedimientos.add(getBtnProcedimientosAdd());
			pnCamposProcedimientos.add(getHorizontalStrut_8());
			pnCamposProcedimientos.add(getBtnProcedimientosRemove());
		}
		return pnCamposProcedimientos;
	}

	private JPanel getPnPrescripcionesCombo() {
		if (pnPrescripcionesCombo == null) {
			pnPrescripcionesCombo = new JPanel();
			FlowLayout fl_pnPrescripcionesCombo = (FlowLayout) pnPrescripcionesCombo.getLayout();
			fl_pnPrescripcionesCombo.setAlignment(FlowLayout.LEFT);
			pnPrescripcionesCombo.add(getLbPrescripciones());
			pnPrescripcionesCombo.add(getCbPrescripciones());
			pnPrescripcionesCombo.add(getBtAñadirPrescripciones());
		}
		return pnPrescripcionesCombo;
	}

	private JPanel getPnPreviewCausas() {
		if (pnPreviewCausas == null) {
			pnPreviewCausas = new JPanel();
			pnPreviewCausas.setLayout(new BorderLayout(0, 0));
			pnPreviewCausas.add(getLbCausasAñadidas(), BorderLayout.NORTH);
			pnPreviewCausas.add(getListCausasAñadidas(), BorderLayout.CENTER);
		}
		return pnPreviewCausas;
	}

	private JPanel getPnPreviewProcedimientos() {
		if (pnPreviewProcedimientos == null) {
			pnPreviewProcedimientos = new JPanel();
			pnPreviewProcedimientos.setLayout(new BorderLayout(0, 0));
			pnPreviewProcedimientos.add(getLblProcedimientosAdded(), BorderLayout.NORTH);
			pnPreviewProcedimientos.add(getListProcedimientosAdded(), BorderLayout.CENTER);
		}
		return pnPreviewProcedimientos;
	}

	private JPanel getPnPreviewPrescripciones() {
		if (pnPreviewPrescripciones == null) {
			pnPreviewPrescripciones = new JPanel();
			pnPreviewPrescripciones.setLayout(new BorderLayout(0, 0));
			pnPreviewPrescripciones.add(getLblPrescripcionesAdded(), BorderLayout.NORTH);
			pnPreviewPrescripciones.add(getListPrescripcionesAdded(), BorderLayout.CENTER);

		}
		return pnPreviewPrescripciones;
	}

	private JLabel getLbCausas() {
		if (lbCausas == null) {
			lbCausas = new JLabel("Causas:");
			lbCausas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbCausas;
	}

	private JComboBox<String> getCmbCausas() {
		if (cmbCausas == null) {
			cmbCausas = new JComboBox<String>();
			cmbCausas.setModel(setUpComboModel(gestor.cargarTodasCausas()));
		}
		return cmbCausas;
	}

	private JButton getBtAñadirCausas() {
		if (btAñadirCausas == null) {
			btAñadirCausas = new JButton("Añadir");
			btAñadirCausas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addCausa();
				}
			});
		}
		return btAñadirCausas;
	}

	protected void addCausa() {
		c.addCausa((String) cmbCausas.getSelectedItem());
		updateListaCausas();
	}

	private JButton getBtEliminarCausas() {
		if (btEliminarCausas == null) {
			btEliminarCausas = new JButton("Eliminar");
		}
		return btEliminarCausas;
	}

	private JButton getBtNuevaCausa() {
		if (btNuevaCausa == null) {
			btNuevaCausa = new JButton("Nueva Causa");
			btNuevaCausa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestor.nuevaCausa(JOptionPane.showInputDialog("Introduzca la nueva causa"));
				}
			});
		}
		return btNuevaCausa;
	}

	private JLabel getLbPrescripciones() {
		if (lbPrescripciones == null) {
			lbPrescripciones = new JLabel("Prescripciones:");
			lbPrescripciones.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbPrescripciones;
	}

	private JComboBox<Prescripcion> getCbPrescripciones() {
		if (cbPrescripciones == null) {
			cbPrescripciones = new JComboBox<Prescripcion>();
			for (Prescripcion p : gestor.cargarPrescripcionesSinParam()) {
				cbPrescripciones.addItem(p);
			}
		}
		return cbPrescripciones;
	}

	private JButton getBtAñadirPrescripciones() {
		if (btnAddPrescripciones == null) {
			btnAddPrescripciones = new JButton("Añadir");
			btnAddPrescripciones.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarPrescripciones();
				}
			});
		}
		return btnAddPrescripciones;
	}

	protected void actualizarPrescripciones() {
		Prescripcion p = ((Prescripcion) getCbPrescripciones().getSelectedItem());
		Prescripcion nuevaP = new Prescripcion(p.getIdPrescripcion(), p.toString()); // Obligatorio para mantener modelo
		nuevaP.setCantidad(getTxtCantidad().getText());
		nuevaP.setDuracion((Integer) getSpDuracion().getValue());
		nuevaP.setIntervalo((Integer) getSpIntervalo().getValue());
		nuevaP.setOtrosDatos(getTxtOtrosDatos().getText());

		c.getPrescripciones().add(nuevaP);
		final DefaultListModel<Prescripcion> model = new DefaultListModel<Prescripcion>();
		for (Prescripcion presc : c.getPrescripciones()) {
			model.addElement(presc);
		}
		getListPrescripcionesAdded().setModel(model);
		txtCantidad.setText("");
		txtOtrosDatos.setText("");
	}

	private JPanel getPnCamposPrescripciones() {
		if (pnCamposPrescripciones == null) {
			pnCamposPrescripciones = new JPanel();
			pnCamposPrescripciones.setLayout(new GridLayout(2, 2, 0, 0));
			pnCamposPrescripciones.add(getPnPrescripcionesCombo());
			pnCamposPrescripciones.add(getPnPrescripcionesDetalles());
		}
		return pnCamposPrescripciones;
	}

	private JPanel getPnPrescripcionesDetalles() {
		if (pnPrescripcionesDetalles == null) {
			pnPrescripcionesDetalles = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrescripcionesDetalles.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnPrescripcionesDetalles.add(getLblCantidad());
			pnPrescripcionesDetalles.add(getTxtCantidad());
			pnPrescripcionesDetalles.add(getHorizontalStrut_1());
			pnPrescripcionesDetalles.add(getHorizontalStrut_4());
			pnPrescripcionesDetalles.add(getLblIntervalo());
			pnPrescripcionesDetalles.add(getSpIntervalo());
			pnPrescripcionesDetalles.add(getLblIntervaloHoras());
			pnPrescripcionesDetalles.add(getHorizontalStrut_2());
			pnPrescripcionesDetalles.add(getHorizontalStrut_3());
			pnPrescripcionesDetalles.add(getLblDuracion());
			pnPrescripcionesDetalles.add(getSpDuracion());
			pnPrescripcionesDetalles.add(getHorizontalStrut_5());
			pnPrescripcionesDetalles.add(getHorizontalStrut_6());
			pnPrescripcionesDetalles.add(getLblOtrosDatos());
			pnPrescripcionesDetalles.add(getTxtOtrosDatos());
		}
		return pnPrescripcionesDetalles;
	}

	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel("Cantidad:");
		}
		return lblCantidad;
	}

	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setColumns(10);
		}
		return txtCantidad;
	}

	private Component getHorizontalStrut_1() {
		if (horizontalStrut_1 == null) {
			horizontalStrut_1 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_1;
	}

	private JLabel getLblIntervalo() {
		if (lblIntervalo == null) {
			lblIntervalo = new JLabel("Intervalo:");
		}
		return lblIntervalo;
	}

	private JSpinner getSpIntervalo() {
		if (spIntervalo == null) {
			spIntervalo = new JSpinner();
			spIntervalo
					.setModel(new SpinnerNumberModel(Integer.valueOf(6), Integer.valueOf(6), null, Integer.valueOf(6)));
		}
		return spIntervalo;
	}

	private JLabel getLblIntervaloHoras() {
		if (lblIntervaloHoras == null) {
			lblIntervaloHoras = new JLabel("  horas");
		}
		return lblIntervaloHoras;
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

	private Component getHorizontalStrut_4() {
		if (horizontalStrut_4 == null) {
			horizontalStrut_4 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_4;
	}

	private JLabel getLblDuracion() {
		if (lblDuracion == null) {
			lblDuracion = new JLabel("Duración:");
		}
		return lblDuracion;
	}

	private JSpinner getSpDuracion() {
		if (spDuracion == null) {
			spDuracion = new JSpinner();
			spDuracion
					.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
		}
		return spDuracion;
	}

	private Component getHorizontalStrut_5() {
		if (horizontalStrut_5 == null) {
			horizontalStrut_5 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_5;
	}

	private Component getHorizontalStrut_6() {
		if (horizontalStrut_6 == null) {
			horizontalStrut_6 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_6;
	}

	private JLabel getLblOtrosDatos() {
		if (lblOtrosDatos == null) {
			lblOtrosDatos = new JLabel("Otros datos:");
		}
		return lblOtrosDatos;
	}

	private JTextField getTxtOtrosDatos() {
		if (txtOtrosDatos == null) {
			txtOtrosDatos = new JTextField();
			txtOtrosDatos.setColumns(10);
		}
		return txtOtrosDatos;
	}

	private JLabel getLblPrescripcionesAdded() {
		if (lblPrescripcionesAdded == null) {
			lblPrescripcionesAdded = new JLabel("Prescripciones: ");
		}
		return lblPrescripcionesAdded;
	}

	private JList<Prescripcion> getListPrescripcionesAdded() {
		if (listPrescripcionesAdded == null) {
			listPrescripcionesAdded = new JList<Prescripcion>();
			listCausasAñadidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		}
		return listPrescripcionesAdded;
	}

	private JPanel getPnCamposDiagnosticos() {
		if (pnCamposDiagnosticos == null) {
			pnCamposDiagnosticos = new JPanel();
			pnCamposDiagnosticos.add(getBtnDiagnosticosAdd());
			pnCamposDiagnosticos.add(getHorizontalStrut_7());
			pnCamposDiagnosticos.add(getBtnDiagnosticosRemove());
		}
		return pnCamposDiagnosticos;
	}

	private JPanel getPnPreviewDiagnosticos() {
		if (pnPreviewDiagnosticos == null) {
			pnPreviewDiagnosticos = new JPanel();
			pnPreviewDiagnosticos.setLayout(new BorderLayout(0, 0));
			pnPreviewDiagnosticos.add(getLblDiagnosticosAdded(), BorderLayout.NORTH);
			pnPreviewDiagnosticos.add(getListDiagnosticosAdded(), BorderLayout.CENTER);
		}
		return pnPreviewDiagnosticos;
	}

	private JLabel getLblProcedimientosAdded() {
		if (lblProcedimientosAdded == null) {
			lblProcedimientosAdded = new JLabel("Procedimientos:");
		}
		return lblProcedimientosAdded;
	}

	private JList<Procedimiento> getListProcedimientosAdded() {
		if (listProcedimientosAdded == null) {
			listProcedimientosAdded = new JList<Procedimiento>();
		}
		return listProcedimientosAdded;
	}

	private JLabel getLblDiagnosticosAdded() {
		if (lblDiagnosticosAdded == null) {
			lblDiagnosticosAdded = new JLabel("Diagnosticos:");
		}
		return lblDiagnosticosAdded;
	}

	private JList<Diagnostico> getListDiagnosticosAdded() {
		if (listDiagnosticosAdded == null) {
			listDiagnosticosAdded = new JList<Diagnostico>();
		}
		return listDiagnosticosAdded;
	}

	private JButton getBtnProcedimientosAdd() {
		if (btnProcedimientosAdd == null) {
			btnProcedimientosAdd = new JButton("Nuevo procedimiento");
			btnProcedimientosAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaProcedimiento();
				}
			});
		}
		return btnProcedimientosAdd;
	}

	@SuppressWarnings("deprecation")
	private void abrirVentanaProcedimiento() {
		VentanaProcedimiento vp = new VentanaProcedimiento(c, this);
		vp.show();
	}

	private JButton getBtnProcedimientosRemove() {
		if (btnProcedimientosRemove == null) {
			btnProcedimientosRemove = new JButton("Eliminar");
		}
		return btnProcedimientosRemove;
	}

	private JButton getBtnDiagnosticosAdd() {
		if (btnDiagnosticosAdd == null) {
			btnDiagnosticosAdd = new JButton("Nuevo diagnostico");
			btnDiagnosticosAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaDiagnostico();
				}
			});
		}
		return btnDiagnosticosAdd;
	}

	@SuppressWarnings("deprecation")
	private void abrirVentanaDiagnostico() {
		VentanaDiagnostico vh = new VentanaDiagnostico(c, this);
		vh.show();
	}

	private JButton getBtnDiagnosticosRemove() {
		if (btnDiagnosticosRemove == null) {
			btnDiagnosticosRemove = new JButton("Eliminar");
		}
		return btnDiagnosticosRemove;
	}

	private Component getHorizontalStrut_7() {
		if (horizontalStrut_7 == null) {
			horizontalStrut_7 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_7;
	}

	private Component getHorizontalStrut_8() {
		if (horizontalStrut_8 == null) {
			horizontalStrut_8 = Box.createHorizontalStrut(20);
		}
		return horizontalStrut_8;
	}
}