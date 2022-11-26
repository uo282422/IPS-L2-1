package ui;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.Medico;
import logic.jornada.Calendario;
import nexus.GestorCalendario;

public class VentanaAsignarCalendarios extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private VentanaBuscadorMedicos vbm;
	private VentanaCrearCalendarios vcc;

	private GestorCalendario gestorCalendarios = new GestorCalendario();

	private JPanel pnMedicos;
	private JPanel pnGestionarMedico;
	private JLabel lbMedicos;
	private JScrollPane scrListaMedicos;
	private JList<Medico> listaMedicos;
	private JPanel pnCalendarios;
	private JPanel pnFiltrarCalendarios;
	private JLabel lbCalendarios;
	private JPanel pnAgregarEditarCalendario;
	private JButton btAgregarCalendario;
	private JPanel pnBuscarCalendarios;
	private JLabel lbNombreCalendario;
	private JTextField txtBuscarCalendario;
	private JButton btBuscarCalendario;
	private JScrollPane scrListaCalendarios;
	private JList<Calendario> listaCalendarios;
	private JButton btSeleccionarMedicos;
	private JPanel pnBotones;
	private JButton btAceptar;
	private JButton btEditar;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaAsignarCalendarios frame = new VentanaAsignarCalendarios(
//							new VentanaCrearJornada());
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
	public VentanaAsignarCalendarios() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnMedicos(), BorderLayout.WEST);
		contentPane.add(getPnCalendarios());
		contentPane.add(getPnBotones(), BorderLayout.SOUTH);
	}

	protected void actualizarCalendarios() {
		listaCalendarios.setModel(toListModel(gestorCalendarios.cargarCalendarios()));
	}

	private JPanel getPnMedicos() {
		if (pnMedicos == null) {
			pnMedicos = new JPanel();
			pnMedicos.setLayout(new BorderLayout(0, 0));
			pnMedicos.add(getPnGestionarMedico(), BorderLayout.NORTH);
			pnMedicos.add(getScrListaMedicos(), BorderLayout.CENTER);
		}
		return pnMedicos;
	}

	private JPanel getPnGestionarMedico() {
		if (pnGestionarMedico == null) {
			pnGestionarMedico = new JPanel();
			pnGestionarMedico.setLayout(new BorderLayout(0, 0));
			pnGestionarMedico.add(getLbMedicos(), BorderLayout.NORTH);
			pnGestionarMedico.add(getBtSeleccionarMedicos(), BorderLayout.SOUTH);
		}
		return pnGestionarMedico;
	}

	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Médicos", SwingConstants.CENTER);
		}
		return lbMedicos;
	}

	private JScrollPane getScrListaMedicos() {
		if (scrListaMedicos == null) {
			scrListaMedicos = new JScrollPane(
					listaMedicos = new JList<Medico>());
			listaMedicos.setSelectionInterval(-1, -1);
		}
		return scrListaMedicos;
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
			pnFiltrarCalendarios.add(getLbCalendarios(), BorderLayout.NORTH);
			pnFiltrarCalendarios.add(getPnAgregarEditarCalendario(), BorderLayout.SOUTH);
			pnFiltrarCalendarios.add(getPnBuscarCalendarios(), BorderLayout.CENTER);
		}
		return pnFiltrarCalendarios;
	}

	private JLabel getLbCalendarios() {
		if (lbCalendarios == null) {
			lbCalendarios = new JLabel("Calendarios", SwingConstants.CENTER);
		}
		return lbCalendarios;
	}

	private JPanel getPnAgregarEditarCalendario() {
		if (pnAgregarEditarCalendario == null) {
			pnAgregarEditarCalendario = new JPanel();
			pnAgregarEditarCalendario.setLayout(new GridLayout(0, 2, 0, 0));
			pnAgregarEditarCalendario.add(getBtAgregarCalendario());
			pnAgregarEditarCalendario.add(getBtEditar());
		}
		return pnAgregarEditarCalendario;
	}

	private JButton getBtAgregarCalendario() {
		if (btAgregarCalendario == null) {
			btAgregarCalendario = new JButton("Agregar nuevo");
			btAgregarCalendario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirCreadorCalendarios();
				}
			});
		}
		return btAgregarCalendario;
	}

	protected void abrirCreadorCalendarios() {
		if (vcc == null)
			vcc = new VentanaCrearCalendarios(this);
		vcc.setLocationRelativeTo(this);
		vcc.setVisible(true);
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
			lbNombreCalendario = new JLabel("Buscar por nombre:",
					SwingConstants.CENTER);
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
			btBuscarCalendario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buscarCalendarios();
				}
			});
		}
		return btBuscarCalendario;
	}

	protected void buscarCalendarios() {
		List<Calendario> buscados = new ArrayList<Calendario>();
		for (Calendario c : gestorCalendarios.cargarCalendarios()) {
			if (c.getNombre().contains(txtBuscarCalendario.getText()))
				buscados.add(c);
		}
		listaCalendarios.setModel(toListModel(buscados));
	}

	private ListModel<Calendario> toListModel(List<Calendario> lista) {
		DefaultListModel<Calendario> model = new DefaultListModel<Calendario>();
		for (Calendario c : lista)
			model.addElement(c);
		return model;
	}

	private JScrollPane getScrListaCalendarios() {
		if (scrListaCalendarios == null) {
			scrListaCalendarios = new JScrollPane(
					listaCalendarios = new JList<Calendario>());
			listaCalendarios.setModel(toListModel(gestorCalendarios.cargarCalendarios()));
		}
		return scrListaCalendarios;
	}

	private JButton getBtSeleccionarMedicos() {
		if (btSeleccionarMedicos == null) {
			btSeleccionarMedicos = new JButton("Seleccionar");
			btSeleccionarMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirBuscador();
				}
			});
		}
		return btSeleccionarMedicos;
	}

	protected void actualizarMedicos() {
		listaMedicos.setModel(recibirMedicos());
	}

	protected void abrirBuscador() {
		if (vbm == null)
			vbm = new VentanaBuscadorMedicos(this);
		vbm.setVisible(true);
		vbm.setLocationRelativeTo(this);
	}

	protected ListModel<Medico> recibirMedicos() {
		DefaultListModel<Medico> ms = new DefaultListModel<Medico>();
		for (Medico m : vbm.getAñadidos()) {
			ms.addElement(m);
		}
		return ms;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnBotones.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnBotones.add(getBtAceptar());
		}
		return pnBotones;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkSeleccion()) {
						guardar();
						JOptionPane.showMessageDialog(null, "Se ha asignado el calendario a los médicos seleccionados");
						reset();
					} else
						JOptionPane.showMessageDialog(null, "Error, o no se han seleccionado médicos o no se han seleccionado calendarios.");
				}
			});
		}
		return btAceptar;
	}

	protected void reset() {
		vbm.getAñadidos().clear();
		actualizarMedicos();
		listaCalendarios.clearSelection();
	}

	protected void guardar() {
		gestorCalendarios.guardarCalendarioParaMedicos(listaCalendarios.getSelectedValue(), getMedicos());
	}

	private List<Medico> getMedicos() {
		List<Medico> medicos = new ArrayList<Medico>();
		for (int i = 0; i < listaMedicos.getModel().getSize(); i++) {
			medicos.add(listaMedicos.getModel().getElementAt(i));
		}
		return medicos;
	}

	protected boolean checkSeleccion() {
		return listaMedicos.getModel().getSize() > 0
				&& !listaCalendarios.isSelectionEmpty();
	}

	private JButton getBtEditar() {
		if (btEditar == null) {
			btEditar = new JButton("Editar");
			btEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (listaCalendarios.getSelectedValue() != null) {
						abrirCreadorCalendarios();
						vcc._setCalendario(listaCalendarios.getSelectedValue());
						vcc.setEditarCalendario();
					}
				}
			});
		}
		return btEditar;
	}
}
