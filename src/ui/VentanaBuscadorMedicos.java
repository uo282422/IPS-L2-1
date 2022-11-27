package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logic.Medico;
import nexus.GestorEspecialidades;
import nexus.GestorMedicos;

public class VentanaBuscadorMedicos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panelSur;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel panelCentral;
	private JPanel panelFiltros;

	private VentanaAsignarCalendarios vac;
	private VentanaCrearCita vcc;
	private VentanaAñadirMedicos vam;
	private DefaultTableModel tm;
	private DefaultTableModel tma;
	private JPanel panelNombre;
	private JLabel lbNombre;
	private JTextField tfNombre;
	private JCheckBox chckbxNombre;
	private JPanel panelApellido;
	private JLabel lbApellido;
	private JTextField tfApellido;
	private JCheckBox chckbxApellido;
	private JPanel panelDni;
	private JLabel lbDni;
	private JTextField tfDni;
	private JCheckBox chckbxDni;
	private JPanel panelColegiado;
	private JLabel lbColegiado;
	private JTextField tfColegiado;
	private JCheckBox chckbxColegiado;
	private JPanel panelEspecialidad;
	private JLabel lbEspecialidad;
	private JCheckBox chckbxEspecialidad;
	private JPanel panelBotones;
	private JPanel panelFiltrar;
	private JPanel panelVerTodos;
	private JButton btFiltrar;
	private JButton btVerTodos;
	private JComboBox<String> cbEspecialidades;
	private JPanel panelVistas;
	private JScrollPane scrollPaneTabla;
	private JScrollPane scrollPaneVista;
	private JTable table;
	private JTable tableVista;
	private JButton btBorrar;
	private JButton btAñadir;

	private GestorEspecialidades gestorEspecialidades = new GestorEspecialidades();
	private GestorMedicos gestorMedicos = new GestorMedicos();

	private ArrayList<Medico> añadidos = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public VentanaBuscadorMedicos(VentanaAñadirMedicos vam) {
		this.vam = vam;
		this.vcc = vam.getVCC();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 595);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(getPanelSur(), BorderLayout.SOUTH);
		panel.add(getPanelCentral(), BorderLayout.CENTER);
		ponerModeloDefault();
		ponerModeloAñadidos();
	}

	/**
	 * Create the frame.
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public VentanaBuscadorMedicos(VentanaAsignarCalendarios vac) {
		this.vac = vac;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 595);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(getPanelSur(), BorderLayout.SOUTH);
		panel.add(getPanelCentral(), BorderLayout.CENTER);
		ponerModeloDefault();
		ponerModeloAñadidos();
	}

	private void ponerModeloDefault() {
		cargarModeloCompletoTabla();
		table.setModel(tm);
	}

	private void ponerModeloAñadidos() {
		actualizarModeloAñadidos();
		tableVista.setModel(tma);
	}

	private void actualizarModeloAñadidos() {
		tma = new DefaultTableModel(new String[] { "Dni", "Nombre", "Apellido",
				"Especialidad", "NºColegiado" }, añadidos.size());
		for (int i = 0; i < añadidos.size(); i++) {
			Medico m = añadidos.get(i);
			tma.setValueAt(m.getDni(), i, 0);
			tma.setValueAt(m.getNombre(), i, 1);
			tma.setValueAt(m.getApellido(), i, 2);
			tma.setValueAt(gestorEspecialidades.buscarPorId(m.getEspecialidad()).getNombre_esp(), i, 3);
			tma.setValueAt(m.getColegiado(), i, 4);
		}
	}

	public ArrayList<Medico> getAñadidos() {
		return añadidos;
	}

	private void cargarModeloCompletoTabla() {
		tm = new DefaultTableModel(new String[] { "Dni", "Nombre", "Apellido",
				"Especialidad", "NºColegiado" },
				gestorMedicos.getMedicos().size());
		for (int i = 0; i < gestorMedicos.getMedicos().size(); i++) {
			Medico m = gestorMedicos.getMedicos().get(i);
			tm.setValueAt(m.getDni(), i, 0);
			tm.setValueAt(m.getNombre(), i, 1);
			tm.setValueAt(m.getApellido(), i, 2);
			tm.setValueAt(gestorEspecialidades.buscarPorId(m.getEspecialidad()).getNombre_esp(), i, 3);
			tm.setValueAt(m.getColegiado(), i, 4);
		}

	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSur.add(getBtAñadir());
			panelSur.add(getBtBorrar());
			panelSur.add(getBtAceptar());
			panelSur.add(getBtCancelar());
		}
		return panelSur;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (vac != null)
						vac.actualizarMedicos();
					if (vam != null)
						vam.añadirMedicos(añadidos);
					;
					dispose();
				}
			});
		}
		return btAceptar;
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

	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new GridLayout(0, 2, 5, 0));
			panelCentral.add(getPanelFiltros());
			panelCentral.add(getPanelVistas());
		}
		return panelCentral;
	}

	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			panelFiltros = new JPanel();
			panelFiltros.setLayout(new GridLayout(6, 1, 0, 0));
			panelFiltros.add(getPanel_1_1());
			panelFiltros.add(getPanelApellido());
			panelFiltros.add(getPanelDni());
			panelFiltros.add(getPanelColegiado());
			panelFiltros.add(getPanelEspecialidad());
			panelFiltros.add(getPanelBotones());
		}
		return panelFiltros;
	}

	private JPanel getPanel_1_1() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.setLayout(new GridLayout(0, 3, 0, 0));
			panelNombre.add(getLbNombre());
			panelNombre.add(getTfNombre());
			panelNombre.add(getChckbxNombre());
		}
		return panelNombre;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre: ");
			lbNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbNombre;
	}

	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}

	private JCheckBox getChckbxNombre() {
		if (chckbxNombre == null) {
			chckbxNombre = new JCheckBox("");
			chckbxNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxNombre;
	}

	private JPanel getPanelApellido() {
		if (panelApellido == null) {
			panelApellido = new JPanel();
			panelApellido.setLayout(new GridLayout(0, 3, 0, 0));
			panelApellido.add(getLbApellido());
			panelApellido.add(getTfApellido());
			panelApellido.add(getChckbxApellido());
		}
		return panelApellido;
	}

	private JLabel getLbApellido() {
		if (lbApellido == null) {
			lbApellido = new JLabel("Apellido:");
			lbApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbApellido.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbApellido;
	}

	private JTextField getTfApellido() {
		if (tfApellido == null) {
			tfApellido = new JTextField();
			tfApellido.setColumns(10);
		}
		return tfApellido;
	}

	private JCheckBox getChckbxApellido() {
		if (chckbxApellido == null) {
			chckbxApellido = new JCheckBox("");
			chckbxApellido.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxApellido;
	}

	private JPanel getPanelDni() {
		if (panelDni == null) {
			panelDni = new JPanel();
			panelDni.setLayout(new GridLayout(0, 3, 0, 0));
			panelDni.add(getLbDni());
			panelDni.add(getTfDni());
			panelDni.add(getChckbxDni());
		}
		return panelDni;
	}

	private JLabel getLbDni() {
		if (lbDni == null) {
			lbDni = new JLabel("Dni: ");
			lbDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbDni;
	}

	private JTextField getTfDni() {
		if (tfDni == null) {
			tfDni = new JTextField();
			tfDni.setColumns(10);
		}
		return tfDni;
	}

	private JCheckBox getChckbxDni() {
		if (chckbxDni == null) {
			chckbxDni = new JCheckBox("");
			chckbxDni.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxDni;
	}

	private JPanel getPanelColegiado() {
		if (panelColegiado == null) {
			panelColegiado = new JPanel();
			panelColegiado.setLayout(new GridLayout(0, 3, 0, 0));
			panelColegiado.add(getLbColegiado());
			panelColegiado.add(getTfColegiado());
			panelColegiado.add(getChckbxColegiado());
		}
		return panelColegiado;
	}

	private JLabel getLbColegiado() {
		if (lbColegiado == null) {
			lbColegiado = new JLabel("NºColegiado: ");
			lbColegiado.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbColegiado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbColegiado;
	}

	private JTextField getTfColegiado() {
		if (tfColegiado == null) {
			tfColegiado = new JTextField();
			tfColegiado.setColumns(10);
		}
		return tfColegiado;
	}

	private JCheckBox getChckbxColegiado() {
		if (chckbxColegiado == null) {
			chckbxColegiado = new JCheckBox("");
			chckbxColegiado.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxColegiado;
	}

	private JPanel getPanelEspecialidad() {
		if (panelEspecialidad == null) {
			panelEspecialidad = new JPanel();
			panelEspecialidad.setLayout(new GridLayout(0, 3, 0, 0));
			panelEspecialidad.add(getLbEspecialidad());
			panelEspecialidad.add(getCbEspecialidades());
			panelEspecialidad.add(getChckbxEspecialidad());
		}
		return panelEspecialidad;
	}

	private JLabel getLbEspecialidad() {
		if (lbEspecialidad == null) {
			lbEspecialidad = new JLabel("Especialidad: ");
			lbEspecialidad.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbEspecialidad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbEspecialidad;
	}

	private JCheckBox getChckbxEspecialidad() {
		if (chckbxEspecialidad == null) {
			chckbxEspecialidad = new JCheckBox("");
			chckbxEspecialidad.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxEspecialidad;
	}

	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new GridLayout(0, 2, 0, 0));
			panelBotones.add(getPanelFiltrar());
			panelBotones.add(getPanelVerTodos());
		}
		return panelBotones;
	}

	private JPanel getPanelFiltrar() {
		if (panelFiltrar == null) {
			panelFiltrar = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelFiltrar.getLayout();
			flowLayout.setVgap(30);
			panelFiltrar.add(getBtFiltrar());
		}
		return panelFiltrar;
	}

	private JPanel getPanelVerTodos() {
		if (panelVerTodos == null) {
			panelVerTodos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelVerTodos.getLayout();
			flowLayout.setVgap(30);
			panelVerTodos.add(getBtVerTodos());
		}
		return panelVerTodos;
	}

	private JButton getBtFiltrar() {
		if (btFiltrar == null) {
			btFiltrar = new JButton("Filtrar");
			btFiltrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					filtrar();
				}
			});
			btFiltrar.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btFiltrar;
	}

	private void filtrar() {
		ArrayList<Medico> listaTodos = gestorMedicos.getMedicos();
		ArrayList<Medico> listaFiltrados = new ArrayList<>();
		for (Medico m : listaTodos) {
			if (cumpleFiltros(m))
				listaFiltrados.add(m);
		}
		ponerModeloFiltrado(listaFiltrados);

	}

	private void ponerModeloFiltrado(ArrayList<Medico> lista) {
		cargarModeloFiltrado(lista);
		getTable().setModel(tm);
	}

	private void cargarModeloFiltrado(ArrayList<Medico> lista) {
		tm = new DefaultTableModel(new String[] { "Dni", "Nombre", "Apellido",
				"Especialidad", "NºColegiado" }, lista.size());
		for (int i = 0; i < lista.size(); i++) {
			Medico m = lista.get(i);
			tm.setValueAt(m.getDni(), i, 0);
			tm.setValueAt(m.getNombre(), i, 1);
			tm.setValueAt(m.getApellido(), i, 2);
			tm.setValueAt(gestorEspecialidades.buscarPorId(m.getEspecialidad()).getNombre_esp(), i, 3);
			tm.setValueAt(m.getColegiado(), i, 4);
		}

	}

	private boolean cumpleFiltros(Medico m) {
		boolean valido = true;
		if (getChckbxNombre().isSelected() && getTfNombre().getText() != "") {
			if (!m.getNombre().startsWith(getTfNombre().getText()))
				valido = false;
		}
		if (getChckbxApellido().isSelected()
				&& getTfApellido().getText() != "") {
			if (!m.getApellido().startsWith(getTfApellido().getText()))
				valido = false;
		}
		if (getChckbxDni().isSelected() && getTfDni().getText() != "") {
			if (!m.getDni().startsWith(getTfDni().getText()))
				valido = false;
		}
		if (getChckbxColegiado().isSelected()
				&& getTfColegiado().getText() != "") {
			if (!m.getColegiado().startsWith(getTfColegiado().getText()))
				valido = false;
		}
		if (getChckbxEspecialidad().isSelected()) {
			if (!gestorEspecialidades.buscarPorId(m.getEspecialidad()).getNombre_esp().equals(cbEspecialidades.getSelectedItem()))
				valido = false;
		}

		return valido;

	}

	private JButton getBtVerTodos() {
		if (btVerTodos == null) {
			btVerTodos = new JButton("Ver todos");
			btVerTodos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					resetear();

				}
			});
			btVerTodos.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return btVerTodos;
	}

	protected void resetear() {
		ponerModeloDefault();

		getTfNombre().setText("");
		getTfApellido().setText("");
		getTfDni().setText("");
		getTfColegiado().setText("");
		getCbEspecialidades().setSelectedIndex(0);
		;

		getChckbxNombre().setSelected(false);
		getChckbxApellido().setSelected(false);
		getChckbxDni().setSelected(false);
		getChckbxColegiado().setSelected(false);
		getChckbxEspecialidad().setSelected(false);

	}

	private JComboBox<String> getCbEspecialidades() {
		if (cbEspecialidades == null) {
			cbEspecialidades = new JComboBox();
			cargarComboEspecialidades();
		}
		return cbEspecialidades;
	}

	private void cargarComboEspecialidades() {
		for (Especialidad esp : gestorEspecialidades.getListaEspecialidades()) {
			cbEspecialidades.addItem(esp.getNombre_esp());
		}

	}

	private JPanel getPanelVistas() {
		if (panelVistas == null) {
			panelVistas = new JPanel();
			panelVistas.setLayout(new GridLayout(2, 1, 0, 0));
			panelVistas.add(getScrollPaneTabla());
			panelVistas.add(getScrollPane_1());
		}
		return panelVistas;
	}

	private JScrollPane getScrollPaneTabla() {
		if (scrollPaneTabla == null) {
			scrollPaneTabla = new JScrollPane();
			scrollPaneTabla.setViewportView(getTable());
		}
		return scrollPaneTabla;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPaneVista == null) {
			scrollPaneVista = new JScrollPane();
			scrollPaneVista.setViewportView(getTableVista());
		}
		return scrollPaneVista;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setDefaultEditor(Object.class, null);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFocusable(false);
		}
		return table;
	}

	private JTable getTableVista() {
		if (tableVista == null) {
			tableVista = new JTable();
			tableVista.setDefaultEditor(Object.class, null);
			tableVista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tableVista.setFocusable(false);
		}
		return tableVista;
	}

	private JButton getBtBorrar() {
		if (btBorrar == null) {
			btBorrar = new JButton("Borrar");
			btBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borrar();
				}
			});
		}
		return btBorrar;
	}

	private JButton getBtAñadir() {
		if (btAñadir == null) {
			btAñadir = new JButton("Añadir");
			btAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					añadir();
				}
			});
		}
		return btAñadir;
	}

	protected void borrar() {
		String d = (String) tma.getValueAt(tableVista.getSelectedRow(), 0);

		añadidos.remove(gestorMedicos.buscarPorDni(d));
		ponerModeloAñadidos();

	}

	protected void añadir() {
		String d = (String) tm.getValueAt(table.getSelectedRow(), 0);

		añadidos.add(gestorMedicos.buscarPorDni(d));
		ponerModeloAñadidos();

	}

}
