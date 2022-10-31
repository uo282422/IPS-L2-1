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
import javax.swing.JCheckBox;
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
			lbMuestraHora.setText(c.getHoraE());
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
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					c.setAcudio(acudio());
					guardarDatos();
					JOptionPane.showMessageDialog(null,
							"La información de la cita se ha guaradado.");
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
			pnAcude.add(getBtVerHistorial());
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
	 * Este método recibe una lista y devuelve un modelo de Combo lleno con la
	 * lista recibida.
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
					gestor.nuevaCausa(JOptionPane
							.showInputDialog("Introduzca la nueva causa"));
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

	private JList getListCausasAñadidas() {
		if (listCausasAñadidas == null) {
			listCausasAñadidas = new JList<String>();
			listCausasAñadidas
					.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
}
