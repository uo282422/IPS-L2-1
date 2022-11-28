package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.Baja;
import logic.Medico;
import logic.jornada.Jornada;
import nexus.GestorBajas;
import nexus.GestorJornada;

public class VentanaConsultarJornadasYBajasPorMedicos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GestorJornada gestorJ = new GestorJornada();
	private GestorBajas gestorB = new GestorBajas();

	private Medico m;

	private JPanel contentPane;
	private JPanel pnSuperior;
	private JLabel lbTitulo;
	private JPanel pnSeleccionMédico;
	private JLabel lbSeleccionarMedico;
	private JButton btSeleccionarMedico;
	private JPanel pnMedicoSeleccionado;
	private JLabel lbMedicoSeleccionado;
	private JLabel lbMedico;
	private JPanel pnCentral;
	private JPanel pnCentralSuperior;
	private JPanel pnTituloJornadas;
	private JPanel pnTituloBajas;
	private JLabel lbTituloJornadas;
	private JLabel lbTituloBajas;
	private JPanel pnCentralCentro;
	private JPanel pnListaJornadas;
	private JPanel pnListaBajas;
	private JScrollPane scrListaJornadas;
	private JList<Jornada> listaJornadas;
	private JPanel pnFiltrarJornadas;
	private JToggleButton btJornadasEnCurso;
	private JToggleButton btJornadasTerminadas;
	private JPanel pnFiltrarBajas;
	private JToggleButton btBajasEnCurso;
	private JToggleButton btBajasTerminadas;
	private JScrollPane scrListaBajas;
	private JList<Baja> listaBajas;
	private JPanel pnEditarVerJornada;
	private JButton btEditarVerJornada;
	private JPanel pnEditarVerBajas;
	private JButton btEditarVerBaja;
	private JPanel pnInferior;
	private JButton btAceptar;
	private JToggleButton btJornadasPorEmpezar;
	private JToggleButton btBajasPorEmpezar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConsultarJornadasYBajasPorMedicos frame = new VentanaConsultarJornadasYBajasPorMedicos();
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
	public VentanaConsultarJornadasYBajasPorMedicos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 711);
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
			pnSuperior.setLayout(new BorderLayout(0, 0));
			pnSuperior.add(getLbTitulo(), BorderLayout.NORTH);
			pnSuperior.add(getPnSeleccionMédico(), BorderLayout.CENTER);
			pnSuperior.add(getPnMedicoSeleccionado(), BorderLayout.SOUTH);
		}
		return pnSuperior;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("Consultar jornadas y bajas para un médico",
					SwingConstants.CENTER);
		}
		return lbTitulo;
	}

	private JPanel getPnSeleccionMédico() {
		if (pnSeleccionMédico == null) {
			pnSeleccionMédico = new JPanel();
			pnSeleccionMédico.add(getLbSeleccionarMedico());
			pnSeleccionMédico.add(getBtSeleccionarMedico());
		}
		return pnSeleccionMédico;
	}

	private JLabel getLbSeleccionarMedico() {
		if (lbSeleccionarMedico == null) {
			lbSeleccionarMedico = new JLabel("Seleccionar médico: ");
		}
		return lbSeleccionarMedico;
	}

	private JButton getBtSeleccionarMedico() {
		if (btSeleccionarMedico == null) {
			btSeleccionarMedico = new JButton("Seleccionar");
			btSeleccionarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					seleccionarMedico();
				}
			});
		}
		return btSeleccionarMedico;
	}

	protected void seleccionarMedico() {
		VentanaBuscadorDeUnMedico vbum = new VentanaBuscadorDeUnMedico(this);
		vbum.setLocationRelativeTo(this);
		vbum.setVisible(true);
	}

	private JPanel getPnMedicoSeleccionado() {
		if (pnMedicoSeleccionado == null) {
			pnMedicoSeleccionado = new JPanel();
			pnMedicoSeleccionado.add(getLbMedicoSeleccionado());
			pnMedicoSeleccionado.add(getLbMedico());
		}
		return pnMedicoSeleccionado;
	}

	private JLabel getLbMedicoSeleccionado() {
		if (lbMedicoSeleccionado == null) {
			lbMedicoSeleccionado = new JLabel("Médico seleccionado: ");
		}
		return lbMedicoSeleccionado;
	}

	private JLabel getLbMedico() {
		if (lbMedico == null) {
			lbMedico = new JLabel("Sin seleccionar");
		}
		return lbMedico;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new BorderLayout(0, 0));
			pnCentral.add(getPnCentralSuperior(), BorderLayout.NORTH);
			pnCentral.add(getPnCentralCentro(), BorderLayout.CENTER);
		}
		return pnCentral;
	}

	private JPanel getPnCentralSuperior() {
		if (pnCentralSuperior == null) {
			pnCentralSuperior = new JPanel();
			pnCentralSuperior.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentralSuperior.add(getPnTituloJornadas());
			pnCentralSuperior.add(getPnTituloBajas());
		}
		return pnCentralSuperior;
	}

	private JPanel getPnTituloJornadas() {
		if (pnTituloJornadas == null) {
			pnTituloJornadas = new JPanel();
			pnTituloJornadas.add(getLbTituloJornadas());
		}
		return pnTituloJornadas;
	}

	private JPanel getPnTituloBajas() {
		if (pnTituloBajas == null) {
			pnTituloBajas = new JPanel();
			pnTituloBajas.add(getLbTituloBajas());
		}
		return pnTituloBajas;
	}

	private JLabel getLbTituloJornadas() {
		if (lbTituloJornadas == null) {
			lbTituloJornadas = new JLabel("Jornadas");
		}
		return lbTituloJornadas;
	}

	private JLabel getLbTituloBajas() {
		if (lbTituloBajas == null) {
			lbTituloBajas = new JLabel("Bajas");
		}
		return lbTituloBajas;
	}

	private JPanel getPnCentralCentro() {
		if (pnCentralCentro == null) {
			pnCentralCentro = new JPanel();
			pnCentralCentro.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentralCentro.add(getPnListaJornadas());
			pnCentralCentro.add(getPnListaBajas());
		}
		return pnCentralCentro;
	}

	private JPanel getPnListaJornadas() {
		if (pnListaJornadas == null) {
			pnListaJornadas = new JPanel();
			pnListaJornadas.setLayout(new BorderLayout(0, 0));
			pnListaJornadas.add(getPnFiltrarJornadas(), BorderLayout.NORTH);
			pnListaJornadas.add(getScrListaJornadas());
			pnListaJornadas.add(getPnEditarVerJornada(), BorderLayout.SOUTH);
		}
		return pnListaJornadas;
	}

	private JPanel getPnListaBajas() {
		if (pnListaBajas == null) {
			pnListaBajas = new JPanel();
			pnListaBajas.setLayout(new BorderLayout(0, 0));
			pnListaBajas.add(getPnFiltrarBajas(), BorderLayout.NORTH);
			pnListaBajas.add(getScrListaBajas(), BorderLayout.CENTER);
			pnListaBajas.add(getPnEditarVerBajas(), BorderLayout.SOUTH);
		}
		return pnListaBajas;
	}

	private JScrollPane getScrListaJornadas() {
		if (scrListaJornadas == null) {
			scrListaJornadas = new JScrollPane(getListaJornadas());
		}
		return scrListaJornadas;
	}

	private JList<Jornada> getListaJornadas() {
		if (listaJornadas == null) {
			listaJornadas = new JList<Jornada>();
			listaJornadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaJornadas.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (listaJornadas.getSelectedValue() != null)
						if (jornadaEnCurso(listaJornadas.getSelectedValue()))
							btEditarVerJornada.setText("Editar");
						else
							btEditarVerJornada.setText("Ver");
				}
			});
		}

		return listaJornadas;
	}

	private JPanel getPnFiltrarJornadas() {
		if (pnFiltrarJornadas == null) {
			pnFiltrarJornadas = new JPanel();
			pnFiltrarJornadas.add(getBtJornadasEnCurso());
			pnFiltrarJornadas.add(getBtJornadasTerminadas());
			pnFiltrarJornadas.add(getBtJornadasPorEmpezar());
		}
		return pnFiltrarJornadas;
	}

	private JToggleButton getBtJornadasEnCurso() {
		if (btJornadasEnCurso == null) {
			btJornadasEnCurso = new JToggleButton("En curso");
			btJornadasEnCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosJornada();
				}
			});
		}
		return btJornadasEnCurso;
	}

	protected void checkFiltrosJornada() {
		DefaultListModel<Jornada> modelo = new DefaultListModel<Jornada>();
		if (btJornadasEnCurso.isSelected()) {
			for (Jornada j : obtenerJornadasEnCurso()) {
				modelo.addElement(j);
			}
		}
		if (btJornadasTerminadas.isSelected()) {
			for (Jornada j : obtenerJornadasTerminadas()) {
				modelo.addElement(j);
			}
		}
		if (btJornadasPorEmpezar.isSelected()) {
			for (Jornada j : obtenerJornadasPorEmpezar()) {
				modelo.addElement(j);
			}
		}
		listaJornadas.setModel(modelo);
	}

	protected void checkFiltrosBaja() {
		DefaultListModel<Baja> modelo = new DefaultListModel<Baja>();
		if (btBajasEnCurso.isSelected()) {
			for (Baja b : obtenerBajasEnCurso()) {
				modelo.addElement(b);
			}
		}
		if (btBajasTerminadas.isSelected()) {
			for (Baja b : obtenerBajasTerminadas()) {
				modelo.addElement(b);
			}
		}
		if (btBajasPorEmpezar.isSelected()) {
			for (Baja b : obtenerBajasPorEmpezar()) {
				modelo.addElement(b);
			}
		}
		listaBajas.setModel(modelo);
	}

	private List<Baja> obtenerBajasPorEmpezar() {
		List<Baja> bajas = new ArrayList<Baja>();

		for (Baja b : gestorB.cargarBajasParaMedico(m)) {
			if (bajaEnCurso(b)) {
				bajas.add(b);
			}
		}
		return bajas;
	}

	private boolean bajaEnCurso(Baja b) {
		return b.estado().equals("en curso");
	}

	private List<Baja> obtenerBajasTerminadas() {
		List<Baja> bajas = new ArrayList<Baja>();

		for (Baja b : gestorB.cargarBajasParaMedico(m)) {
			if (bajaTerminada(b)) {
				bajas.add(b);
			}
		}
		return bajas;
	}

	private boolean bajaTerminada(Baja b) {
		return b.estado().equals("terminada");
	}

	private List<Baja> obtenerBajasEnCurso() {
		List<Baja> bajas = new ArrayList<Baja>();

		for (Baja b : gestorB.cargarBajasParaMedico(m)) {
			if (bajaPorEmpezar(b)) {
				bajas.add(b);
			}
		}
		return bajas;
	}

	private boolean bajaPorEmpezar(Baja b) {
		return b.estado().equals("por empezar");
	}

	private List<Jornada> obtenerJornadasPorEmpezar() {
		List<Jornada> jornadas = new ArrayList<Jornada>();

		for (Jornada j : gestorJ.cargarJornadasParaMedico(m)) {
			if (jornadaPorEmpezar(j)) {
				jornadas.add(j);
			}
		}
		return jornadas;
	}

	private boolean jornadaPorEmpezar(Jornada j) {
		return j.estado().equals("por empezar");
	}

	private List<Jornada> obtenerJornadasTerminadas() {
		List<Jornada> jornadas = new ArrayList<Jornada>();

		for (Jornada j : gestorJ.cargarJornadasParaMedico(m)) {
			if (jornadaTerminada(j)) {
				jornadas.add(j);
			}
		}
		return jornadas;
	}

	private boolean jornadaTerminada(Jornada j) {
		return j.estado().equals("terminada");
	}

	protected List<Jornada> obtenerJornadasEnCurso() {
		List<Jornada> jornadas = new ArrayList<Jornada>();

		for (Jornada j : gestorJ.cargarJornadasParaMedico(m)) {
			if (jornadaEnCurso(j)) {
				jornadas.add(j);
			}
		}
		return jornadas;
	}

	private boolean jornadaEnCurso(Jornada j) {
		return j.estado().equals("en curso");
	}

	private JToggleButton getBtJornadasTerminadas() {
		if (btJornadasTerminadas == null) {
			btJornadasTerminadas = new JToggleButton("Terminadas");
			btJornadasTerminadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosJornada();
				}
			});
		}
		return btJornadasTerminadas;
	}

	private JPanel getPnFiltrarBajas() {
		if (pnFiltrarBajas == null) {
			pnFiltrarBajas = new JPanel();
			pnFiltrarBajas.add(getBtBajasEnCurso());
			pnFiltrarBajas.add(getBtBajasTerminadas());
			pnFiltrarBajas.add(getBtBajasPorEmpezar());
		}
		return pnFiltrarBajas;
	}

	private JToggleButton getBtBajasEnCurso() {
		if (btBajasEnCurso == null) {
			btBajasEnCurso = new JToggleButton("En curso");
			btBajasEnCurso.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosBaja();
				}
			});
		}
		return btBajasEnCurso;
	}

	private JToggleButton getBtBajasTerminadas() {
		if (btBajasTerminadas == null) {
			btBajasTerminadas = new JToggleButton("Terminadas");
			btBajasTerminadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosBaja();
				}
			});
			;
		}
		return btBajasTerminadas;
	}

	private JScrollPane getScrListaBajas() {
		if (scrListaBajas == null) {
			scrListaBajas = new JScrollPane(getListaBajas());
		}
		return scrListaBajas;
	}

	private JList<Baja> getListaBajas() {
		if (listaBajas == null) {
			listaBajas = new JList<Baja>();
			listaBajas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaBajas.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (listaBajas.getSelectedValue() != null)
						if (bajaEnCurso(listaBajas.getSelectedValue()))
							btEditarVerBaja.setText("Editar");
						else
							btEditarVerBaja.setText("Ver");
				}
			});
		}
		return listaBajas;
	}

	private JPanel getPnEditarVerJornada() {
		if (pnEditarVerJornada == null) {
			pnEditarVerJornada = new JPanel();
			pnEditarVerJornada.add(getBtEditarVerJornada());
		}
		return pnEditarVerJornada;
	}

	private JButton getBtEditarVerJornada() {
		if (btEditarVerJornada == null) {
			btEditarVerJornada = new JButton("Ver");
			btEditarVerJornada.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVistaJornada(listaJornadas.getSelectedValue());
				}
			});
		}
		return btEditarVerJornada;
	}

	protected void abrirVistaJornada(Jornada j) {
		VentanaConsultarJornadaParaMedico vcjm = new VentanaConsultarJornadaParaMedico(
				this, j, jornadaEnCurso(j) ? "en curso"
						: jornadaPorEmpezar(j) ? "por empezar" : "terminada");
		vcjm.setLocationRelativeTo(this);
		vcjm.setVisible(true);
	}

	private JPanel getPnEditarVerBajas() {
		if (pnEditarVerBajas == null) {
			pnEditarVerBajas = new JPanel();
			pnEditarVerBajas.add(getBtEditarVerBaja());
		}
		return pnEditarVerBajas;
	}

	private JButton getBtEditarVerBaja() {
		if (btEditarVerBaja == null) {
			btEditarVerBaja = new JButton("Ver");
			btEditarVerBaja.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVistaBaja(listaBajas.getSelectedValue());
				}
			});
		}
		return btEditarVerBaja;
	}

	protected void abrirVistaBaja(Baja b) {
		VentanaConsultarBajaParaMedico vcbm = new VentanaConsultarBajaParaMedico(
				this, b, bajaEnCurso(b) ? "en curso"
						: bajaPorEmpezar(b) ? "por empezar" : "terminada");
		vcbm.setLocationRelativeTo(this);
		vcbm.setVisible(true);
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
					dispose();
				}
			});
		}
		return btAceptar;
	}

	public void setMedico(Medico m) {
		this.m = m;
	}

	private JToggleButton getBtJornadasPorEmpezar() {
		if (btJornadasPorEmpezar == null) {
			btJornadasPorEmpezar = new JToggleButton("Por empezar");
			btJornadasPorEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosJornada();
				}
			});
		}
		return btJornadasPorEmpezar;
	}

	private JToggleButton getBtBajasPorEmpezar() {
		if (btBajasPorEmpezar == null) {
			btBajasPorEmpezar = new JToggleButton("Por empezar");
			btBajasPorEmpezar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkFiltrosBaja();
				}
			});
		}
		return btBajasPorEmpezar;
	}

	public void configurar() {
		lbMedico.setText(m.getNombre() + " " + m.getApellido() + " | ID: "
				+ m.getId());
		cargarListasPorDefecto();
	}

	private void cargarListasPorDefecto() {
		listaJornadas.setModel(toModelList(gestorJ.cargarJornadasParaMedico(m)));
		listaBajas.setModel(toModelList(gestorB.cargarBajasParaMedico(m)));
	}

	private <T> ListModel<T> toModelList(List<T> lista) {
		DefaultListModel<T> model = new DefaultListModel<T>();
		for (T t : lista)
			model.addElement(t);
		return model;
	}

	public Medico getMedico() {
		return m;
	}

	public void reset() {
		listaJornadas.setSelectedIndex(-1);
		listaBajas.setSelectedIndex(-1);
		for (Component c : pnFiltrarJornadas.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			b.setSelected(false);
		}

		for (Component c : pnFiltrarBajas.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			b.setSelected(false);
		}

	}
}
