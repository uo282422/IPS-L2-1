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

import logic.Medico;
import logic.jornada.Calendario;
import nexus.GestorCalendario;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Component;

public class VentanaAsignarCalendarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private GestorCalendario g = new GestorCalendario();

	private VentanaCrearJornada v = null;
	private JPanel pnMedicos;
	private JPanel pnBuscarMedico;
	private JLabel lbMedicos;
	private JScrollPane scrListaMedicos;
	private JList<Medico> listaMedicos;
	private JPanel pnBuscador;
	private JLabel lbBuscarNombre;
	private JTextField txtBuscarMedicos;
	private JPanel pnBotonesMedicos;
	private JButton btEliminarMedicos;
	private JButton btAgregarMedicos;
	private JButton btBuscarMedicos;
	private JPanel pnCalendarios;
	private JPanel pnFiltrarCalendarios;
	private JLabel lbCalendarios;
	private JPanel pnFiltrarAgregarCalendario;
	private JButton btFiltrarCalendarios;
	private JButton btAgregarCalendario;
	private JPanel pnBuscarCalendarios;
	private JLabel lbNombreCalendario;
	private JTextField txtBuscarCalendario;
	private JButton btBuscarCalendario;
	private JScrollPane scrListaCalendarios;
	private JList<Calendario> listaCalendarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAsignarCalendarios frame = new VentanaAsignarCalendarios(
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
	public VentanaAsignarCalendarios(VentanaCrearJornada v) {
		this.v = v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(getPnMedicos());
		contentPane.add(getPnCalendarios());
		setCalendars();
		//pack();
	}

	private ListModel<Calendario> cargarModeloCalendarios() {
		DefaultListModel<Calendario> m = new DefaultListModel<Calendario>();
		for (Calendario c : g.cargarCalendarios())
			m.addElement(c);
		return m;
	}

	/**
	 * Se encarga de ajustar de manera adecuada las fechas de los calendarios.
	 */
	private void setCalendars() {
	}

	protected void actualizarLista() {
	}

	private JPanel getPnMedicos() {
		if (pnMedicos == null) {
			pnMedicos = new JPanel();
			pnMedicos.setLayout(new BorderLayout(0, 0));
			pnMedicos.add(getPnBuscarMedico(), BorderLayout.NORTH);
			pnMedicos.add(getScrListaMedicos(), BorderLayout.CENTER);
			pnMedicos.add(getPnBotonesMedicos(), BorderLayout.SOUTH);
		}
		return pnMedicos;
	}
	private JPanel getPnBuscarMedico() {
		if (pnBuscarMedico == null) {
			pnBuscarMedico = new JPanel();
			pnBuscarMedico.setLayout(new BorderLayout(0, 0));
			pnBuscarMedico.add(getLbMedicos(), BorderLayout.NORTH);
			pnBuscarMedico.add(getPnBuscador(), BorderLayout.CENTER);
		}
		return pnBuscarMedico;
	}
	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Médicos", SwingConstants.CENTER);
		}
		return lbMedicos;
	}
	private JScrollPane getScrListaMedicos() {
		if (scrListaMedicos == null) {
			scrListaMedicos = new JScrollPane(listaMedicos = new JList<Medico>());
		}
		return scrListaMedicos;
	}
	private JPanel getPnBuscador() {
		if (pnBuscador == null) {
			pnBuscador = new JPanel();
			pnBuscador.setLayout(new GridLayout(0, 3, 0, 0));
			pnBuscador.add(getLbBuscarNombre());
			pnBuscador.add(getTxtBuscarMedicos());
			pnBuscador.add(getBtBuscarMedicos());
		}
		return pnBuscador;
	}
	private JLabel getLbBuscarNombre() {
		if (lbBuscarNombre == null) {
			lbBuscarNombre = new JLabel("Buscar por nombre:", SwingConstants.CENTER);
		}
		return lbBuscarNombre;
	}
	private JTextField getTxtBuscarMedicos() {
		if (txtBuscarMedicos == null) {
			txtBuscarMedicos = new JTextField();
			txtBuscarMedicos.setColumns(10);
		}
		return txtBuscarMedicos;
	}
	private JPanel getPnBotonesMedicos() {
		if (pnBotonesMedicos == null) {
			pnBotonesMedicos = new JPanel();
			pnBotonesMedicos.setLayout(new GridLayout(0, 2, 0, 0));
			pnBotonesMedicos.add(getBtEliminarMedicos());
			pnBotonesMedicos.add(getBtAgregarMedicos());
		}
		return pnBotonesMedicos;
	}
	private JButton getBtEliminarMedicos() {
		if (btEliminarMedicos == null) {
			btEliminarMedicos = new JButton("Eliminar");
		}
		return btEliminarMedicos;
	}
	private JButton getBtAgregarMedicos() {
		if (btAgregarMedicos == null) {
			btAgregarMedicos = new JButton("Agregar");
		}
		return btAgregarMedicos;
	}
	private JButton getBtBuscarMedicos() {
		if (btBuscarMedicos == null) {
			btBuscarMedicos = new JButton("Buscar");
		}
		return btBuscarMedicos;
	}
	private JPanel getPnCalendarios() {
		if (pnCalendarios == null) {
			pnCalendarios = new JPanel();
			pnCalendarios.setLayout(new BorderLayout(0, 0));
			pnCalendarios.add(getPnFiltrarCalendarios(), BorderLayout.NORTH);
			pnCalendarios.add(getScrListaCalendarios(), BorderLayout.CENTER);
		}
		return pnCalendarios;
	}
	private JPanel getPnFiltrarCalendarios() {
		if (pnFiltrarCalendarios == null) {
			pnFiltrarCalendarios = new JPanel();
			pnFiltrarCalendarios.setLayout(new BorderLayout(0, 0));
			pnFiltrarCalendarios.add(getLbCalendarios_1(), BorderLayout.NORTH);
			pnFiltrarCalendarios.add(getPnFiltrarAgregarCalendario(), BorderLayout.SOUTH);
			pnFiltrarCalendarios.add(getPnBuscarCalendarios(), BorderLayout.CENTER);
		}
		return pnFiltrarCalendarios;
	}
	private JLabel getLbCalendarios_1() {
		if (lbCalendarios == null) {
			lbCalendarios = new JLabel("Calendarios", SwingConstants.CENTER);
		}
		return lbCalendarios;
	}
	private JPanel getPnFiltrarAgregarCalendario() {
		if (pnFiltrarAgregarCalendario == null) {
			pnFiltrarAgregarCalendario = new JPanel();
			pnFiltrarAgregarCalendario.setLayout(new GridLayout(0, 2, 0, 0));
			pnFiltrarAgregarCalendario.add(getBtFiltrarCalendarios());
			pnFiltrarAgregarCalendario.add(getBtAgregarCalendario());
		}
		return pnFiltrarAgregarCalendario;
	}
	private JButton getBtFiltrarCalendarios() {
		if (btFiltrarCalendarios == null) {
			btFiltrarCalendarios = new JButton("Filtrar calendarios");
		}
		return btFiltrarCalendarios;
	}
	private JButton getBtAgregarCalendario() {
		if (btAgregarCalendario == null) {
			btAgregarCalendario = new JButton("Agregar nuevo");
		}
		return btAgregarCalendario;
	}
	private JPanel getPnBuscarCalendarios() {
		if (pnBuscarCalendarios == null) {
			pnBuscarCalendarios = new JPanel();
			pnBuscarCalendarios.setLayout(new GridLayout(0, 3, 0, 0));
			pnBuscarCalendarios.add(getLbNombreCalendario());
			pnBuscarCalendarios.add(getTxtBuscarCalendario());
			pnBuscarCalendarios.add(getBtBuscarCalendario());
		}
		return pnBuscarCalendarios;
	}
	private JLabel getLbNombreCalendario() {
		if (lbNombreCalendario == null) {
			lbNombreCalendario = new JLabel("Buscar por nombre:", SwingConstants.CENTER);
		}
		return lbNombreCalendario;
	}
	private JTextField getTxtBuscarCalendario() {
		if (txtBuscarCalendario == null) {
			txtBuscarCalendario = new JTextField();
			txtBuscarCalendario.setColumns(10);
		}
		return txtBuscarCalendario;
	}
	private JButton getBtBuscarCalendario() {
		if (btBuscarCalendario == null) {
			btBuscarCalendario = new JButton("Buscar");
		}
		return btBuscarCalendario;
	}
	private JScrollPane getScrListaCalendarios() {
		if (scrListaCalendarios == null) {
			scrListaCalendarios = new JScrollPane(listaCalendarios = new JList<Calendario>());
		}
		return scrListaCalendarios;
	}
}
