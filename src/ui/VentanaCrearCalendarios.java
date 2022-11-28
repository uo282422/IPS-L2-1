package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.jornada.Calendario;
import logic.jornada.Jornada;
import nexus.GestorCalendario;
import nexus.GestorJornada;

public class VentanaCrearCalendarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Calendario c;
	private List<Jornada> agregadas = new ArrayList<Jornada>();
	private GestorCalendario gestorCalendarios = new GestorCalendario();
	private GestorJornada gestorJornada = new GestorJornada();

	private VentanaAsignarCalendarios vac;
	private VentanaCrearJornadaSinMedico vcj;

	private JPanel contentPane;
	private JPanel pnSuperior;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JPanel pnCentral;
	private JPanel pnInferior;
	private JButton btAceptar;
	private JButton btCrearJornada;
	private JPanel pnBuscarJornadas;
	private JLabel lblJornadasGuardadas;
	private JPanel pnBuscadorJornadas;
	private JLabel lbBuscarJornadas;
	private JTextField txtBuscarJornadas;
	private JButton btBuscarJornadas;
	private JPanel pnJornadas;
	private JList<Jornada> listaJornadas;
	private JList<Jornada> listaAgregadas;
	private JPanel pnAgregarJornadas;
	private JScrollPane scrListaJornadas;
	private JButton btAgregar;
	private JPanel pnJornadasAgregadas;
	private JScrollPane scrListaAgregadas;
	private JButton btEliminar;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCrearCalendarios frame = new VentanaCrearCalendarios();
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
	public VentanaCrearCalendarios(VentanaAsignarCalendarios vac) {
		this.vac = vac;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 686, 517);
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
			pnSuperior.add(getLbNombre());
			pnSuperior.add(getTxtNombre());
		}
		return pnSuperior;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre:");
			lbNombre.setLabelFor(getTxtNombre());
		}
		return lbNombre;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new BorderLayout(0, 0));
			pnCentral.add(getBtCrearJornada(), BorderLayout.SOUTH);
			pnCentral.add(getPnBuscarJornadas(), BorderLayout.NORTH);
			pnCentral.add(getPnJornadas(), BorderLayout.CENTER);
		}
		return pnCentral;
	}

	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnInferior.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnInferior.add(getBtAceptar());
		}
		return pnInferior;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkNombre()
							&& listaAgregadas.getModel().getSize() > 0) {
						guardarCalendario();
						dispose();
					}
				}
			});
		}
		return btAceptar;
	}

	protected boolean checkNombre() {
		return !txtNombre.getText().isBlank() && !txtNombre.getText().isEmpty();
	}

	protected void guardarCalendario() {
		if (c != null) {
			editarCalendario();
		} else {
			c = new Calendario(gestorCalendarios.generarId(),
					txtNombre.getText());
			for (int i = 0; i < listaAgregadas.getModel().getSize(); i++) {
				c.addJornada(listaAgregadas.getModel().getElementAt(i));
			}
			gestorCalendarios.guardar(c);
		}
		vac.actualizarCalendarios();
		reset();
	}

	private void editarCalendario() {
		c.setNombre(txtNombre.getText());
		c.getJornadas().clear();
		for (int i = 0; i < listaAgregadas.getModel().getSize(); i++) {
			c.addJornada(listaAgregadas.getModel().getElementAt(i));
		}
		gestorCalendarios.editar(c);
	}

	private void reset() {
		agregadas.clear();
		txtNombre.setText("");
	}

	private JButton getBtCrearJornada() {
		if (btCrearJornada == null) {
			btCrearJornada = new JButton("Crear jornada");
			btCrearJornada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirCreadorJornadas();
				}
			});
		}
		return btCrearJornada;
	}

	protected void actualizarJornadas() {
		listaJornadas.setModel(toListModel(gestorJornada.cargarJornadas()));
	}

	private ListModel<Jornada> toListModel(List<Jornada> jornadas) {
		DefaultListModel<Jornada> model = new DefaultListModel<Jornada>();
		for (Jornada j : jornadas)
			model.addElement(j);
		return model;
	}

	protected void abrirCreadorJornadas() {
		if (vcj == null)
			vcj = new VentanaCrearJornadaSinMedico(this);
		vcj.setLocationRelativeTo(this);
		vcj.setVisible(true);
	}

	private JPanel getPnBuscarJornadas() {
		if (pnBuscarJornadas == null) {
			pnBuscarJornadas = new JPanel();
			pnBuscarJornadas.setLayout(new BorderLayout(0, 0));
			pnBuscarJornadas.add(getLbJornadas(), BorderLayout.NORTH);
			pnBuscarJornadas.add(getPnBuscadorJornadas(), BorderLayout.CENTER);
		}
		return pnBuscarJornadas;
	}

	private JLabel getLbJornadas() {
		if (lblJornadasGuardadas == null) {
			lblJornadasGuardadas = new JLabel("Jornadas guardadas",
					SwingConstants.CENTER);
		}
		return lblJornadasGuardadas;
	}

	private JPanel getPnBuscadorJornadas() {
		if (pnBuscadorJornadas == null) {
			pnBuscadorJornadas = new JPanel();
			pnBuscadorJornadas.setLayout(new FlowLayout(FlowLayout.CENTER, 5,
					5));
			pnBuscadorJornadas.add(getLbBuscarJornadas());
			pnBuscadorJornadas.add(getTxtBuscarJornadas());
			pnBuscadorJornadas.add(getBtBuscarJornadas());
		}
		return pnBuscadorJornadas;
	}

	private JLabel getLbBuscarJornadas() {
		if (lbBuscarJornadas == null) {
			lbBuscarJornadas = new JLabel("Buscar por nombre: ");
			lbBuscarJornadas.setLabelFor(getTxtBuscarJornadas());
		}
		return lbBuscarJornadas;
	}

	private JTextField getTxtBuscarJornadas() {
		if (txtBuscarJornadas == null) {
			txtBuscarJornadas = new JTextField();
			txtBuscarJornadas.setColumns(10);
		}
		return txtBuscarJornadas;
	}

	private JButton getBtBuscarJornadas() {
		if (btBuscarJornadas == null) {
			btBuscarJornadas = new JButton("Buscar");
			btBuscarJornadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarJornadas();
				}
			});
		}
		return btBuscarJornadas;
	}

	protected void buscarJornadas() {
		List<Jornada> buscadas = new ArrayList<Jornada>();
		for (Jornada j : gestorJornada.cargarJornadas()) {
			if (j.getNombre().contains(txtBuscarJornadas.getText()))
				buscadas.add(j);
		}
		listaJornadas.setModel(toListModel(buscadas));
	}

	private JPanel getPnJornadas() {
		if (pnJornadas == null) {
			pnJornadas = new JPanel();
			pnJornadas.setLayout(new GridLayout(0, 2, 0, 0));
			pnJornadas.add(getPnAgregarJornadas());
			pnJornadas.add(getPnJornadasAgregadas());
		}
		return pnJornadas;
	}

	private JList<Jornada> getListaJornadas() {
		if (listaJornadas == null)
			listaJornadas = new JList<Jornada>();
		return listaJornadas;
	}

	private JPanel getPnAgregarJornadas() {
		if (pnAgregarJornadas == null) {
			pnAgregarJornadas = new JPanel();
			pnAgregarJornadas.setLayout(new BorderLayout(0, 0));
			pnAgregarJornadas.add(getScrListaJornadas());
			pnAgregarJornadas.add(getBtAgregar(), BorderLayout.SOUTH);
		}
		return pnAgregarJornadas;
	}

	private JScrollPane getScrListaJornadas() {
		if (scrListaJornadas == null) {
			scrListaJornadas = new JScrollPane(getListaJornadas());
			listaJornadas.setModel(toListModel(gestorJornada.cargarJornadas()));
		}
		return scrListaJornadas;
	}

	private JList<Jornada> getListaAgregadas() {
		if (listaAgregadas == null)
			listaAgregadas = new JList<Jornada>();
		return listaAgregadas;
	}

	private JButton getBtAgregar() {
		if (btAgregar == null) {
			btAgregar = new JButton("Agregar");
			btAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						agregarJornada();
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btAgregar;
	}

	protected void agregarJornada() throws ParseException {
		if (agregadas.contains(listaJornadas.getSelectedValue()))
			JOptionPane.showMessageDialog(null, "Esta jornada ya pertenece al calendario.");
		else
			agregadas.add(listaJornadas.getSelectedValue());
		listaAgregadas.setModel(toListModel(agregadas));
	}

	private JPanel getPnJornadasAgregadas() {
		if (pnJornadasAgregadas == null) {
			pnJornadasAgregadas = new JPanel();
			pnJornadasAgregadas.setLayout(new BorderLayout(0, 0));
			pnJornadasAgregadas.add(getScrListaAgregadas());
			pnJornadasAgregadas.add(getBtEliminar(), BorderLayout.SOUTH);
		}
		return pnJornadasAgregadas;
	}

	private JScrollPane getScrListaAgregadas() {
		if (scrListaAgregadas == null) {
			scrListaAgregadas = new JScrollPane(getListaAgregadas());
		}
		return scrListaAgregadas;
	}

	private JButton getBtEliminar() {
		if (btEliminar == null) {
			btEliminar = new JButton("Eliminar");
			btEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarJornada();
				}
			});
		}
		return btEliminar;
	}

	protected void eliminarJornada() {
		if (agregadas.contains(listaAgregadas.getSelectedValue()))
			agregadas.remove(listaAgregadas.getSelectedValue());
		listaAgregadas.setModel(toListModel(agregadas));
	}

	public void filtrarBusqueda(String dias, String turnos, String desde,
			String hasta) {
		List<Jornada> filtradas = new ArrayList<Jornada>();
		for (Jornada j : gestorJornada.cargarJornadas()) {
			if (checkFiltroDias(j, dias) && checkFiltroTurnos(j, turnos)
					&& checkFiltroFechas(j, desde, hasta)) {
				filtradas.add(j);
			}
		}
		listaJornadas.setModel(toListModel(filtradas));
	}

	private boolean checkFiltroFechas(Jornada j, String desde, String hasta) {
		try {
			if (desde.equals("  /  /    "))
				desde = "01/01/0001";
			if (hasta.equals("  /  /    "))
				hasta = "01/01/0001";
			if ((desde + hasta).equals(""))
				return true;
			Date inicio = new SimpleDateFormat(
					"dd/MM/yyyy").parse(j.getInicio());
			Date fin = new SimpleDateFormat("dd/MM/yyyy").parse(j.getFin());
			Date desdeD = new SimpleDateFormat("dd/MM/yyyy").parse(desde);
			Date hastaD = new SimpleDateFormat("dd/MM/yyyy").parse(hasta);
			if (inicio.before(desdeD) && fin.before(desdeD))
				return true;
			else if (inicio.after(hastaD) && fin.after(hastaD))
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean checkFiltroTurnos(Jornada j, String turnos) {
		LocalTime inicio = LocalTime.parse(j.getHoraComienzo());
		if (turnos.contains("M") && inicio.isAfter(LocalTime.parse("06:00:00"))
				&& inicio.isBefore(LocalTime.parse("14:00:00")))
			return true;
		if (turnos.contains("T") && inicio.isAfter(LocalTime.parse("14:00:00"))
				&& inicio.isBefore(LocalTime.parse("22:00:00")))
			return true;
		if (turnos.contains("N") && inicio.isAfter(LocalTime.parse("22:00:00"))
				&& inicio.isBefore(LocalTime.parse("06:00:00")))
			return true;
		return false;
	}

	private boolean checkFiltroDias(Jornada j, String dias) {
		for (int i = 0; i < dias.length(); i++) {
			if (j.getDias().indexOf(dias.charAt(i)) == -1)
				return false;
		}
		return true;
	}

	void _setCalendario(Calendario c) {
		this.c = c;
	}

	public void setEditarCalendario() {
		txtNombre.setText(c.getNombre());
		listaAgregadas.setModel(toListModel(c.getJornadas()));
		agregadas = c.getJornadas();
	}
}
