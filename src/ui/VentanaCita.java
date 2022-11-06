package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
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
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.cita.Cita;
import logic.cita.Enum_acudio;
import nexus.GestorCitas;
import java.awt.Component;
import javax.swing.Box;

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
	private JPanel pnCausas;
	private JPanel pnCausasSuperior;
	private JLabel lbCausas;
	private JComboBox<String> cmbCausas;
	private JButton btAñadirCausas;
	private JButton btEliminarCausas;
	private JButton btNuevaCausa;
	private JPanel pnCausasInferior;
	private JList<String> listCausasAñadidas;
	private JLabel lbCausasAñadidas;
	private JPanel pnCampos;
	private JPanel pnPreview;
	private JScrollPane spPreview;
	private JTextArea taPreview;
	private JPanel pnHora;
	private JLabel lbHora;
	private JButton btAlterar;
	private Component horizontalStrut;

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
		setBounds(100, 100, 938, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
		contentPane.add(getPnInferior(), BorderLayout.SOUTH);
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentral.add(getPnCampos());
			pnCentral.add(getPnPreview());
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

	private JPanel getPnCausas() {
		if (pnCausas == null) {
			pnCausas = new JPanel();
			pnCausas.setLayout(new BoxLayout(pnCausas, BoxLayout.Y_AXIS));
			pnCausas.add(getPnCausasSuperior());
			pnCausas.add(getPnCausasInferior());

		}
		return pnCausas;
	}

	private JLabel getLbCausas() {
		if (lbCausas == null) {
			lbCausas = new JLabel("Causas:");
			lbCausas.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbCausas;
	}

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

	@SuppressWarnings("deprecation")
	protected void verHistorial() {
		HistorialPaciente hP = new HistorialPaciente(c.getIdPaciente());
		hP.show();
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

	private JPanel getPnCausasSuperior() {
		if (pnCausasSuperior == null) {
			pnCausasSuperior = new JPanel();
			pnCausasSuperior.add(getLbCausas());
			pnCausasSuperior.add(getCmbCausas());
			pnCausasSuperior.add(getBtAñadirCausas());
			pnCausasSuperior.add(getBtEliminarCausas());
			pnCausasSuperior.add(getBtNuevaCausa());
		}
		return pnCausasSuperior;
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

	private void updateListaCausas() {
		listCausasAñadidas.setModel(toListModel(c.getCausas()));
	}

	private ListModel<String> toListModel(List<String> l) {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String c : l)
			model.addElement(c);
		return model;
	}

	private JButton getBtEliminarCausas() {
		if (btEliminarCausas == null) {
			btEliminarCausas = new JButton("Eliminar");
			btEliminarCausas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					deleteCausa();
				}
			});
		}
		return btEliminarCausas;
	}

	protected void deleteCausa() {
		c.deleteCausa((String) cmbCausas.getSelectedItem());
		updateListaCausas();
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

	private JPanel getPnCausasInferior() {
		if (pnCausasInferior == null) {
			pnCausasInferior = new JPanel();
			pnCausasInferior.setLayout(new BorderLayout(0, 0));
			pnCausasInferior.add(getListCausasAñadidas());
			pnCausasInferior.add(getLbCausasAñadidas(), BorderLayout.NORTH);
		}
		return pnCausasInferior;
	}

	private JList<String> getListCausasAñadidas() {
		if (listCausasAñadidas == null) {
			listCausasAñadidas = new JList<String>();
			listCausasAñadidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCausasAñadidas.setEnabled(false);
		}
		return listCausasAñadidas;
	}

	private JLabel getLbCausasAñadidas() {
		if (lbCausasAñadidas == null) {
			lbCausasAñadidas = new JLabel("Causas añadidas:");
		}
		return lbCausasAñadidas;
	}

	private JPanel getPnCampos() {
		if (pnCampos == null) {
			pnCampos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnCampos.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnCampos.add(getPnAcude());
			pnCampos.add(getPnHora());
			pnCampos.add(getPnCausas());
		}
		return pnCampos;
	}

	private JPanel getPnPreview() {
		if (pnPreview == null) {
			pnPreview = new JPanel();
			pnPreview.setLayout(new BorderLayout(0, 0));
			pnPreview.add(getBtVerHistorial(), BorderLayout.NORTH);
			pnPreview.add(getSpPreview(), BorderLayout.CENTER);
		}
		return pnPreview;
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

	private void refrescarInfo() {
		StringBuilder sb = new StringBuilder(String.format("  CITA Nº%d\n  --------------------\n\n", c.getIdCita()));
		sb.append(String.format("  Nombre del paciente: %s %s\n", c.getNombrePaciente(), c.getApellidoPaciente()));
		sb.append(String.format("  Hora: %s\n", c.getHoraE()));
		sb.append(String.format("  Fecha: %s\n", c.getFecha()));
		sb.append(String.format("  Sala: %d\n", c.getSala()));
		sb.append(String.format("  Urgente: %s", c.isAcudio().toString()));

		taPreview.setText(sb.toString());
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
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					VentanaHora vh = new VentanaHora(c);
					vh.show();
				}
			});
		}
		return btAlterar;
	}
	private Component getHorizontalStrut() {
		if (horizontalStrut == null) {
			horizontalStrut = Box.createHorizontalStrut(20);
		}
		return horizontalStrut;
	}
}
