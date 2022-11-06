package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.toedter.calendar.JCalendar;

import logic.jornada.Calendario;
import nexus.GestorCalendario;

public class VentanaCalendariosGuardados extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnPrincipal;
	private JButton btAceptar;
	private JPanel pnGestion;
	private JPanel pnCalendarios;
	private JLabel lbInicio;
	private JCalendar calendarInicio;
	private JLabel lbFin;
	private JCalendar calendarFin;

	private GestorCalendario g = new GestorCalendario();
	private JPanel pnBotones;
	private JButton btGuardar;
	private JButton btEliminar;
	private JPanel pnLista;
	private JList<Calendario> list;

	private VentanaCrearJornada v = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCalendariosGuardados frame = new VentanaCalendariosGuardados(
							new VentanaCrearJornada());
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
	public VentanaCalendariosGuardados(VentanaCrearJornada v) {
		this.v = v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1019, 703);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnPrincipal(), BorderLayout.NORTH);
		contentPane.add(getBtAceptar(), BorderLayout.SOUTH);
		setCalendars();
		pack();
	}

	private JPanel getPnPrincipal() {
		if (pnPrincipal == null) {
			pnPrincipal = new JPanel();
			pnPrincipal.setLayout(new GridLayout(0, 2, 0, 0));
			pnPrincipal.add(getPnLista());
			pnPrincipal.add(getPnGestion());

		}
		return pnPrincipal;
	}

	private ListModel<Calendario> cargarModeloCalendarios() {
		DefaultListModel<Calendario> m = new DefaultListModel<Calendario>();
		for (Calendario c : g.cargarCalendarios())
			m.addElement(c);
		return m;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (compruebaSeleccion()) {
						v._setCalendarsValues(calendarInicio, calendarFin);
					}
					dispose();
				}
			});
		}
		return btAceptar;
	}

	private JPanel getPnGestion() {
		if (pnGestion == null) {
			pnGestion = new JPanel();
			pnGestion.setLayout(new BorderLayout(0, 0));
			pnGestion.add(getPnCalendarios(), BorderLayout.CENTER);
			pnGestion.add(getPnBotones(), BorderLayout.SOUTH);
		}
		return pnGestion;
	}

	private JPanel getPnCalendarios() {
		if (pnCalendarios == null) {
			pnCalendarios = new JPanel();
			pnCalendarios.setLayout(new GridLayout(4, 0, 0, 0));
			pnCalendarios.add(getLbInicio());
			pnCalendarios.add(getCalendarInicio());
			pnCalendarios.add(getLbFin());
			pnCalendarios.add(getCalendarFin());
		}
		return pnCalendarios;
	}

	private JLabel getLbInicio() {
		if (lbInicio == null) {
			lbInicio = new JLabel("Inicio", SwingConstants.CENTER);
		}
		return lbInicio;
	}

	private JCalendar getCalendarInicio() {
		if (calendarInicio == null) {
			calendarInicio = new JCalendar();
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
		}
		return calendarInicio;
	}

	private JLabel getLbFin() {
		if (lbFin == null) {
			lbFin = new JLabel("Fin", SwingConstants.CENTER);
		}
		return lbFin;
	}

	private JCalendar getCalendarFin() {
		if (calendarFin == null) {
			calendarFin = new JCalendar();
		}
		return calendarFin;
	}

	protected void guardarCalendario() {
		g.guardar(calendarInicio.getDate(), calendarFin.getDate());
	}

	/**
	 * Se encarga de ajustar de manera adecuada las fechas de los calendarios.
	 */
	private void setCalendars() {
		calendarFin.setMinSelectableDate(calendarInicio.getDate());
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setLayout(new GridLayout(0, 2, 0, 0));
			pnBotones.add(getBtEliminar());
			pnBotones.add(getBtGuardar());
		}
		return pnBotones;
	}

	private JButton getBtGuardar() {
		if (btGuardar == null) {
			btGuardar = new JButton("Guardar calendario");
			btGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null,
							"Se guardará el calendario seleccionado, ¿continuar?") == JOptionPane.YES_OPTION) {
						guardarCalendario();
						actualizarLista();
					}
				}
			});
		}
		return btGuardar;
	}

	protected void actualizarLista() {
		list.setModel(cargarModeloCalendarios());
		list.setSelectedIndex(-1);
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar Calendario");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (compruebaSeleccion())
						eliminarCalendario();
					else
						JOptionPane.showMessageDialog(null,
								"Debes seleccionar un calendario para poder eliminarlo");
				}
			});
		}
		return btEliminar;
	}

	protected void eliminarCalendario() {
		g.eliminarCalendario(list.getSelectedValue());
		actualizarLista();
	}

	protected boolean compruebaSeleccion() {
		return !list.isSelectionEmpty();
	}

	private JPanel getPnLista() {
		if (pnLista == null) {
			pnLista = new JPanel();
			pnLista.setLayout(new GridLayout(0, 1, 0, 0));
			pnLista.add(new JScrollPane(getList()));
		}
		return pnLista;
	}

	private JList<Calendario> getList() {
		if (list == null) {
			list = new JList<Calendario>();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					actualizarCalendarios();
				}
			});
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			actualizarLista();
		}
		return list;
	}

	protected void actualizarCalendarios() {
		calendarInicio.setDate(list.getSelectedValue().getDiaInicio());
		calendarFin.setDate(list.getSelectedValue().getDiaFin());
	}
}
