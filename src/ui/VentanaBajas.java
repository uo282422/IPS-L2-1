package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JCalendar;

import logic.Medico;
import nexus.GestorBajas;

public class VentanaBajas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnListMedicos;
	private JList<Medico> listMedicos;
	private JPanel pnBaja;
	private JPanel pnCentralBajaSuperior;
	private JPanel pnFechaBaja;
	private JPanel pnFecha;
	private JCalendar calendarInicio = new JCalendar();
	private JPanel pnHora;
	private JLabel lbFFin;
	private JCalendar calendarFin = new JCalendar();
	private JLabel lbHInicio;
	private JFormattedTextField txtHInicio;
	private JLabel lbHFin;
	private JFormattedTextField txtHFin;
	private JLabel lbTitulo;
	private JLabel lbFInicio;
	private JPanel pnCentralBajaInferior;
	private JPanel pnTipos;
	private ButtonGroup groupTipos = new ButtonGroup();
	private JRadioButton rdBaja;
	private JRadioButton rdVacaciones;
	private JRadioButton rdMoscosos;
	private JRadioButton rdOtros;
	private JPanel pnObservaciones;
	private JLabel lbObservaciones;
	private JTextArea txtObservaciones;
	private JButton btAñadir;

	private GestorBajas gestor = new GestorBajas();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaBajas frame = new VentanaBajas();
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
	public VentanaBajas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1075, 761);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(getPnListMedicos());
		contentPane.add(getPnBaja());
	}

	private JPanel getPnListMedicos() {
		if (pnListMedicos == null) {
			pnListMedicos = new JPanel();
			pnListMedicos.setLayout(new GridLayout(0, 1, 0, 0));
			pnListMedicos.add(new JScrollPane(getListMedicos()));
		}
		return pnListMedicos;
	}

	private JList<Medico> getListMedicos() {
		if (listMedicos == null) {
			listMedicos = new JList<Medico>();
			listMedicos.setModel(setUpListMed(gestor.cargarMedicos()));
		}
		return listMedicos;
	}

	private JPanel getPnBaja() {
		if (pnBaja == null) {
			pnBaja = new JPanel();
			pnBaja.setLayout(new BorderLayout(0, 0));
			pnBaja.add(getPnCentralBajaSuperior(), BorderLayout.CENTER);
			pnBaja.add(getBtAñadir(), BorderLayout.SOUTH);
		}
		return pnBaja;
	}

	private JPanel getPnCentralBajaSuperior() {
		if (pnCentralBajaSuperior == null) {
			pnCentralBajaSuperior = new JPanel();
			pnCentralBajaSuperior.setLayout(new GridLayout(2, 1, 0, 0));
			pnCentralBajaSuperior.add(getPnFechaBaja());
			pnCentralBajaSuperior.add(getPnCentralBajaInferior());
		}
		return pnCentralBajaSuperior;
	}

	private JPanel getPnFechaBaja() {
		if (pnFechaBaja == null) {
			pnFechaBaja = new JPanel();
			pnFechaBaja.setLayout(new BorderLayout(0, 0));
			pnFechaBaja.add(getPnFecha(), BorderLayout.CENTER);
			pnFechaBaja.add(getPnHora(), BorderLayout.SOUTH);
			pnFechaBaja.add(getLbTitulo(), BorderLayout.NORTH);

		}
		return pnFechaBaja;
	}

	private JPanel getPnFecha() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.setLayout(new GridLayout(2, 2, 0, 0));
			setCalendars();
			calendarInicio.getDayChooser().setAlwaysFireDayProperty(true);
			calendarInicio.getDayChooser().addPropertyChangeListener("day",
					new PropertyChangeListener() {
						/**
						 * Cada vez que se selecciona un día del calendario de
						 * día de inicio se actualiza el calendario de día de
						 * fin, bloqueando todos los días previos al día
						 * seleccionado en el día de inicio de la jornada.
						 * 
						 * @param evt
						 */
						public void propertyChange(PropertyChangeEvent evt) {
							setCalendars();
						}
					});
			pnFecha.add(getLbFInicio());
			pnFecha.add(getCalendarInicio());
			pnFecha.add(getLbFFin());
			pnFecha.add(getCalendarFin());

		}
		return pnFecha;
	}

	private JCalendar getCalendarInicio() {
		if (calendarInicio == null) {
			calendarInicio = new JCalendar();
		}
		return calendarInicio;
	}

	private JPanel getPnHora() {
		if (pnHora == null) {
			pnHora = new JPanel();
			pnHora.add(getLbHInicio());
			pnHora.add(getTxtHInicio());
			pnHora.add(getLbHFin());
			pnHora.add(getTxtHFin());
		}
		return pnHora;
	}

	private JLabel getLbFFin() {
		if (lbFFin == null) {
			lbFFin = new JLabel("Fecha de Fin:", SwingConstants.CENTER);
			lbFFin.setLabelFor(getCalendarFin());
		}
		return lbFFin;
	}

	private JCalendar getCalendarFin() {
		if (calendarFin == null) {
			calendarFin = new JCalendar();
		}
		return calendarFin;
	}

	private JLabel getLbHInicio() {
		if (lbHInicio == null) {
			lbHInicio = new JLabel("Hora de inicio:");
		}
		return lbHInicio;
	}

	private JTextField getTxtHInicio() {
		if (txtHInicio == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHInicio = new JFormattedTextField(mf);
				txtHInicio.setHorizontalAlignment(SwingConstants.CENTER);

				txtHInicio.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtHInicio;
	}

	private JLabel getLbHFin() {
		if (lbHFin == null) {
			lbHFin = new JLabel("Hora de fin:");
		}
		return lbHFin;
	}

	private JTextField getTxtHFin() {
		if (txtHFin == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHFin = new JFormattedTextField(mf);
				txtHFin.setHorizontalAlignment(SwingConstants.CENTER);

				txtHFin.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtHFin;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Definir baja:", SwingConstants.CENTER);
		}
		return lbTitulo;
	}

	private JLabel getLbFInicio() {
		if (lbFInicio == null) {
			lbFInicio = new JLabel("Fecha de Inicio:", SwingConstants.CENTER);
			lbFInicio.setLabelFor(getCalendarInicio());
		}
		return lbFInicio;
	}

	private JPanel getPnCentralBajaInferior() {
		if (pnCentralBajaInferior == null) {
			pnCentralBajaInferior = new JPanel();
			pnCentralBajaInferior.setLayout(new GridLayout(2, 0, 0, 0));
			pnCentralBajaInferior.add(getPnTipos());
			pnCentralBajaInferior.add(getPnObservaciones());
		}
		return pnCentralBajaInferior;
	}

	private JPanel getPnTipos() {
		if (pnTipos == null) {
			pnTipos = new JPanel();
			pnTipos.setLayout(new GridLayout(0, 4, 0, 0));
			groupTipos.add(getRdBaja());
			groupTipos.add(getRdVacaciones());
			groupTipos.add(getRdMoscosos());
			groupTipos.add(getRdOtros());
			pnTipos.add(getRdBaja());
			pnTipos.add(getRdVacaciones());
			pnTipos.add(getRdMoscosos());
			pnTipos.add(getRdOtros());
		}
		return pnTipos;
	}

	private JRadioButton getRdBaja() {
		if (rdBaja == null) {
			rdBaja = new JRadioButton("Baja");
			rdBaja.setSelected(true);
		}
		return rdBaja;
	}

	private JRadioButton getRdVacaciones() {
		if (rdVacaciones == null) {
			rdVacaciones = new JRadioButton("Vacaciones");
		}
		return rdVacaciones;
	}

	private JRadioButton getRdMoscosos() {
		if (rdMoscosos == null) {
			rdMoscosos = new JRadioButton("Moscosos");
		}
		return rdMoscosos;
	}

	private JRadioButton getRdOtros() {
		if (rdOtros == null) {
			rdOtros = new JRadioButton("Otros");
		}
		return rdOtros;
	}

	private JPanel getPnObservaciones() {
		if (pnObservaciones == null) {
			pnObservaciones = new JPanel();
			pnObservaciones.setLayout(new BorderLayout(0, 0));
			pnObservaciones.add(getLbObservaciones(), BorderLayout.NORTH);
			pnObservaciones.add(new JScrollPane(getTxtObservaciones()),
					BorderLayout.CENTER);
		}
		return pnObservaciones;
	}

	private JLabel getLbObservaciones() {
		if (lbObservaciones == null) {
			lbObservaciones = new JLabel("Observaciones:",
					SwingConstants.CENTER);
		}
		return lbObservaciones;
	}

	private JTextArea getTxtObservaciones() {
		if (txtObservaciones == null) {
			txtObservaciones = new JTextArea();
		}
		return txtObservaciones;
	}

	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("Añadir");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadirBaja();
				}
			});
		}
		return btAñadir;
	}

	protected void añadirBaja() {
		if (checkHours() && checkMed()) {
			if (JOptionPane.showConfirmDialog(null, String.format(
					"Se añadirá baja a %s, ¿Es correcto?",
					listMedicos.getSelectedValue())) == JOptionPane.YES_OPTION)
				saveBaja();
		} else
			JOptionPane.showMessageDialog(null,
					"Los campos rellenados no son válidos");

	}

	private boolean checkHours() {
		String[] hInicio = txtHInicio.getText().split(":");
		String[] hFin = txtHFin.getText().split(":");
		if (!hInicio[0].isBlank() && Integer.parseInt(hInicio[0]) > 23
				|| !hFin[0].isBlank() && Integer.parseInt(hFin[0]) > 23)
			return false;
		if (!hInicio[1].isBlank() && Integer.parseInt(hInicio[1]) > 59
				|| !hFin[1].isBlank() && Integer.parseInt(hFin[1]) > 59)
			return false;
		if (hInicio[0].isBlank())
			txtHInicio.setText("00:00");
		if (hFin[0].isBlank())
			txtHFin.setText("00:00");
		return true;
	}

	/**
	 * Llama a la clase GestorBaja con todos los datos necesarios parar guardar
	 * la baja en la bd
	 */
	private void saveBaja() {
		Medico m = listMedicos.getSelectedValue();
		gestor.save(m, calendarInicio.getDate(), calendarFin.getDate(),
				txtHInicio.getText(), txtHFin.getText(), getBtTipo().getText(),
				txtObservaciones.getText());
	}

	private AbstractButton getBtTipo() {
		for (Enumeration<AbstractButton> bts = groupTipos.getElements(); bts
				.hasMoreElements();) {
			AbstractButton bt = bts.nextElement();
			if (bt.isSelected())
				return bt;
		}
		return rdBaja;
	}

	/**
	 * Comprueba que hay un medico seleccionado, si este tiene una baja que
	 * colisione con la que se desea poner, se avisa al administrativo y si este
	 * acepta, se sustituye
	 * 
	 * @return boolean conteniendo true or false en función de lo estipulado
	 *         arriba
	 */
	private boolean checkMed() {
		return !listMedicos.isSelectionEmpty() && checkBajasMedico();
	}

	/**
	 * Comprueba si existen bajas que colisionen para el medico seleccionado
	 * 
	 * Una baja colisiona con otra si ambas abarcan un momento en común
	 * 
	 * @return
	 */
	private boolean checkBajasMedico() {
		if (!gestor.checkColisiones(listMedicos.getSelectedValue(),
				calendarInicio.getDate(), calendarFin.getDate(),
				txtHInicio.getText(), txtHFin.getText())) {
			JOptionPane.showMessageDialog(null,
					"Hay una baja existente para este medico que colisiona con esta. Se sobreescribirá.");
			gestor.update(true);
		}
		return true;
	}

	/**
	 * Genera el ComboBoxModel adecuado para mostrar en la medCombo.
	 * 
	 * @param meds conteniendo la lista de médicos de la que se busca sacar un
	 *             ComboBoxModel.
	 * @return ComboBoxModel<Medico> con cada médico.
	 */
	private ListModel<Medico> setUpListMed(List<Medico> meds) {
		DefaultListModel<Medico> m = new DefaultListModel<Medico>();
		for (Medico med : meds)
			m.addElement(med);
		return m;
	}

	/**
	 * Se encarga de ajustar de manera adecuada las fechas de los calendarios.
	 */
	private void setCalendars() {
		calendarFin.setMinSelectableDate(calendarInicio.getDate());
	}
}