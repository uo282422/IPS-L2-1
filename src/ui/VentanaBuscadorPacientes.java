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

import logic.Paciente;

public class VentanaBuscadorPacientes extends JFrame {

	private JPanel panel;
	private JPanel panelSur;
	private JButton btAceptar;
	private JButton btCancelar;
	private JPanel panelCentral;
	private JPanel panelFiltros;
	private JScrollPane scrollPane;
	private JTable table;
	
	private VentanaCrearCita vcc;
	private DefaultTableModel tm;
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
	private JPanel panelNhc;
	private JLabel lbNhc;
	private JTextField tfNhc;
	private JCheckBox chckbxNhc;
	private JPanel panelTarjeta;
	private JLabel lbTarjeta;
	private JTextField tfTarjeta;
	private JCheckBox chckbxTarjeta;
	private JPanel panelBotones;
	private JPanel panelFiltrar;
	private JPanel panelVerTodos;
	private JButton btFiltrar;
	private JButton btVerTodos;

	

	/**
	 * Create the frame.
	 */
	public VentanaBuscadorPacientes(VentanaCrearCita vcc) {
		this.vcc=vcc;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 595);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(getPanelSur(), BorderLayout.SOUTH);
		panel.add(getPanelCentral(), BorderLayout.CENTER);
		ponerModeloDefault();
	}
	
	private void ponerModeloDefault() {
		cargarModeloCompletoTabla();
		getTable().setModel(tm);
	}

	private void cargarModeloCompletoTabla() {
		tm=new DefaultTableModel(new String[] {"Dni", "Nombre", "Apellido", "NHC", "NºTarjeta"},vcc.getGP().getListaPacientes().size() );
		for(int i=0;i<vcc.getGP().getListaPacientes().size();i++) {
			Paciente p=vcc.getGP().getListaPacientes().get(i);
			tm.setValueAt(p.getDni(), i, 0);
			tm.setValueAt(p.getNombre(), i, 1);
			tm.setValueAt(p.getApellido(), i, 2);
			tm.setValueAt(p.getNHC(), i, 3);
			tm.setValueAt(p.getTarjeta(), i, 4);
		}
		
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
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
					
					String n=(String) tm.getValueAt(table.getSelectedRow(), 1);
					String a=(String) tm.getValueAt(table.getSelectedRow(), 2);
					vcc.getTfPaciente().setText(n+" "+a);
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
			panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
			panelCentral.add(getPanelFiltros());
			panelCentral.add(getScrollPane());
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
			panelFiltros.add(getPanelNhc());
			panelFiltros.add(getPanelTarjeta());
			panelFiltros.add(getPanelBotones());
		}
		return panelFiltros;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
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
	private JPanel getPanelNhc() {
		if (panelNhc == null) {
			panelNhc = new JPanel();
			panelNhc.setLayout(new GridLayout(0, 3, 0, 0));
			panelNhc.add(getLbNhc());
			panelNhc.add(getTfNhc());
			panelNhc.add(getChckbxNhc());
		}
		return panelNhc;
	}
	private JLabel getLbNhc() {
		if (lbNhc == null) {
			lbNhc = new JLabel("Nhc: ");
			lbNhc.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbNhc.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbNhc;
	}
	private JTextField getTfNhc() {
		if (tfNhc == null) {
			tfNhc = new JTextField();
			tfNhc.setColumns(10);
		}
		return tfNhc;
	}
	private JCheckBox getChckbxNhc() {
		if (chckbxNhc == null) {
			chckbxNhc = new JCheckBox("");
			chckbxNhc.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxNhc;
	}
	private JPanel getPanelTarjeta() {
		if (panelTarjeta == null) {
			panelTarjeta = new JPanel();
			panelTarjeta.setLayout(new GridLayout(0, 3, 0, 0));
			panelTarjeta.add(getLbTarjeta());
			panelTarjeta.add(getTfTarjeta());
			panelTarjeta.add(getChckbxTarjeta());
		}
		return panelTarjeta;
	}
	private JLabel getLbTarjeta() {
		if (lbTarjeta == null) {
			lbTarjeta = new JLabel("Tarjeta: ");
			lbTarjeta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbTarjeta;
	}
	private JTextField getTfTarjeta() {
		if (tfTarjeta == null) {
			tfTarjeta = new JTextField();
			tfTarjeta.setColumns(10);
		}
		return tfTarjeta;
	}
	private JCheckBox getChckbxTarjeta() {
		if (chckbxTarjeta == null) {
			chckbxTarjeta = new JCheckBox("");
			chckbxTarjeta.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxTarjeta;
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
		ArrayList<Paciente> listaTodos=vcc.getGP().getListaPacientes();
		ArrayList<Paciente> listaFiltrados=new ArrayList<>();
		for(Paciente p : listaTodos) {
			if(cumpleFiltros(p))
				listaFiltrados.add(p);
		}
		ponerModeloFiltrado(listaFiltrados);
		
	}
	private void ponerModeloFiltrado(ArrayList<Paciente> lista) {
		cargarModeloFiltrado(lista);
		getTable().setModel(tm);
	}
	private void cargarModeloFiltrado(ArrayList<Paciente> lista) {
		tm=new DefaultTableModel(new String[] {"Dni", "Nombre", "Apellido", "NHC", "NºTarjeta"},vcc.getGP().getListaPacientes().size() );
		for(int i=0;i<lista.size();i++) {
			Paciente p=lista.get(i);
			tm.setValueAt(p.getDni(), i, 0);
			tm.setValueAt(p.getNombre(), i, 1);
			tm.setValueAt(p.getApellido(), i, 2);
			tm.setValueAt(p.getNHC(), i, 3);
			tm.setValueAt(p.getTarjeta(), i, 4);
		}
		
		
	}

	private boolean cumpleFiltros(Paciente p) {
		boolean valido=true;
		if(getChckbxNombre().isSelected() && getTfNombre().getText()!="") {
			if(!p.getNombre().startsWith(getTfNombre().getText()))
				valido=false;
		}
		if(getChckbxApellido().isSelected() && getTfApellido().getText()!="") {
			if(!p.getApellido().startsWith(getTfApellido().getText()))
				valido=false;
		}
		if(getChckbxDni().isSelected() && getTfDni().getText()!="") {
			if(!p.getDni().startsWith(getTfDni().getText()))
				valido=false;
		}
		if(getChckbxNhc().isSelected() && getTfNhc().getText()!="") {
			if(!p.getNHC().startsWith(getTfNhc().getText()))
				valido=false;
		}
		if(getChckbxTarjeta().isSelected() && getTfTarjeta().getText()!="") {
			if(!p.getTarjeta().startsWith(getTfTarjeta().getText()))
				valido=false;
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
		getTfNhc().setText("");
		getTfTarjeta().setText("");
		
		getChckbxNombre().setSelected(false);
		getChckbxApellido().setSelected(false);
		getChckbxDni().setSelected(false);
		getChckbxNhc().setSelected(false);
		getChckbxTarjeta().setSelected(false);
		
	}
}
