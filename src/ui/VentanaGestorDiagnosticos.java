package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logic.Diagnostico;
import logic.Paciente;
import logic.Seguimiento;
import logic.cita.Cita;
import nexus.GestorCitas;
import nexus.GestorDiagnosticos;
import nexus.GestorPacientes;
import nexus.GestorSeguimientos;

public class VentanaGestorDiagnosticos extends JFrame {

	private JPanel panel;
	private JPanel panelSur;
	private JPanel panelCentral;
	private JPanel panelFiltros;
	private JScrollPane scrollPane;
	private JTable table;
	
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
	private JPanel panelHoraEntrada;
	private JLabel lbHoraEntrada;
	private JTextField tfFecha;
	private JCheckBox chckbxFecha;
	private JPanel panelConSeguimiento;
	private JCheckBox chckbxConSeguimiento;
	private JLabel lblSloConSeguimiento;

	private GestorSeguimientos gS;
	private GestorDiagnosticos gD;
	private GestorPacientes gP;
	private GestorCitas gC;
	private ArrayList<Diagnostico> diag;
	private JButton btAbrir;
	private JButton btCerrar;
	private JButton btVerComentarios;
	

	/**
	 * Create the frame.
	 */
	public VentanaGestorDiagnosticos() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1396, 611);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(new BorderLayout(0, 0));
		panel.add(getPanelSur(), BorderLayout.SOUTH);
		panel.add(getPanelCentral(), BorderLayout.CENTER);
		recuperarDatos();
		ponerModeloDefault();
	}
	
	private void recuperarDatos() {
		this.gD=new GestorDiagnosticos();
		this.gC=new GestorCitas();
		this.gP=new GestorPacientes();
		this.gS=new GestorSeguimientos();
		this.diag=gD.getListaDiagnosticos();
		
		
		
	}

	private void ponerModeloDefault() {
		cargarModeloCompletoTabla();
		getTable().setModel(tm);
	}

	private void cargarModeloCompletoTabla() {
		tm=new DefaultTableModel(new String[] {"Cita_id","Estado seguimiento", "Nombre paciente", "Apellido paciente","NHC paciente",  "NºTarjeta paciente"},diag.size() );
		for(int i=0;i<diag.size();i++) {
			Diagnostico d=diag.get(i);
			String estado=d.getSeguimiento() ? estado=gS.getSeguimientoCita(d.getCitaId()).getEstado() : "Sin seguimiento";
			Paciente p=buscarPaciente(d.getCitaId());
			tm.setValueAt(d.getCitaId(), i, 0);
			tm.setValueAt(estado, i, 1);
			tm.setValueAt(p.getNombre(), i, 2);
			tm.setValueAt(p.getApellido(), i, 3);
			tm.setValueAt(p.getNHC(), i, 4);
			tm.setValueAt(p.getTarjeta(), i, 5);
		}
		
	}

	private Paciente buscarPaciente(String citaId) {
		return gP.getPaciente(gC.getCita(citaId).getIdPaciente());
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSur.add(getBtVerComentarios());
			panelSur.add(getBtAbrir());
			panelSur.add(getBtCerrar());
		}
		return panelSur;
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
			panelFiltros.setLayout(new GridLayout(8, 1, 0, 0));
			panelFiltros.add(getPanelHoraEntrada());
			panelFiltros.add(getPanel_1_1());
			panelFiltros.add(getPanelApellido());
			panelFiltros.add(getPanelDni());
			panelFiltros.add(getPanelNhc());
			panelFiltros.add(getPanelTarjeta());
			panelFiltros.add(getPanelConSeguimiento());
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
			lbNombre = new JLabel("Nombre paciente: ");
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
			lbApellido = new JLabel("Apellido paciente:");
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
			lbDni = new JLabel("Dni paciente: ");
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
			lbNhc = new JLabel("Nhc paciente: ");
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
			lbTarjeta = new JLabel("Tarjeta paciente: ");
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
			panelBotones.setLayout(new GridLayout(0, 3, 0, 0));
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
		
		ArrayList<Diagnostico> listaFiltrados=new ArrayList<>();
		for(Diagnostico d : diag) {
			if(cumpleFiltros(d))
				listaFiltrados.add(d);
		}
		ponerModeloFiltrado(listaFiltrados);
		
	}
	private void ponerModeloFiltrado(ArrayList<Diagnostico> lista) {
		cargarModeloFiltrado(lista);
		getTable().setModel(tm);
	}
	private void cargarModeloFiltrado(ArrayList<Diagnostico> lista) {
		tm=new DefaultTableModel(new String[] {"Cita_id","Estado seguimiento", "Nombre paciente", "Apellido paciente","NHC paciente",  "NºTarjeta paciente"},lista.size() );
		for(int i=0;i<lista.size();i++) {
			Diagnostico d=lista.get(i);
			String estado=d.getSeguimiento() ? estado=gS.getSeguimientoCita(d.getCitaId()).getEstado() : "Sin seguimiento";
			Paciente p=buscarPaciente(d.getCitaId());
			tm.setValueAt(d.getCitaId(), i, 0);
			tm.setValueAt(estado, i, 1);
			tm.setValueAt(p.getNombre(), i, 2);
			tm.setValueAt(p.getApellido(), i, 3);
			tm.setValueAt(p.getNHC(), i, 4);
			tm.setValueAt(p.getTarjeta(), i, 5);
		}
		
		
	}

	private boolean cumpleFiltros(Diagnostico d) {
		
		Paciente p=buscarPaciente(d.getCitaId());
		Cita c=gC.getCita(d.getCitaId());
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
		if(getChckbxFecha().isSelected() && getTfFecha().getText()!="") {
			if(!c.getFecha().equals(getTfFecha().getText()))
				valido=false;
		}
		if(getChckbxConSeguimiento().isSelected()) {
			if(!d.getSeguimiento())
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
		recuperarDatos();
		ponerModeloDefault();
		
		getTfFecha().setText("");
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
		getChckbxConSeguimiento().setSelected(false);
		getChckbxFecha().setSelected(false);
		
		
		
	}
	private JPanel getPanelHoraEntrada() {
		if (panelHoraEntrada == null) {
			panelHoraEntrada = new JPanel();
			panelHoraEntrada.setLayout(new GridLayout(0, 3, 0, 0));
			panelHoraEntrada.add(getLbHoraEntrada());
			panelHoraEntrada.add(getTfFecha());
			panelHoraEntrada.add(getChckbxFecha());
		}
		return panelHoraEntrada;
	}
	private JLabel getLbHoraEntrada() {
		if (lbHoraEntrada == null) {
			lbHoraEntrada = new JLabel("Fecha :");
			lbHoraEntrada.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lbHoraEntrada.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lbHoraEntrada;
	}
	private JTextField getTfFecha() {
		if (tfFecha == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##/##/####");
				tfFecha = new JFormattedTextField(mf);
				tfFecha.setHorizontalAlignment(SwingConstants.CENTER);
				tfFecha.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return tfFecha;
	}
	private JCheckBox getChckbxFecha() {
		if (chckbxFecha == null) {
			chckbxFecha = new JCheckBox("");
			chckbxFecha.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxFecha;
	}
	private JPanel getPanelConSeguimiento() {
		if (panelConSeguimiento == null) {
			panelConSeguimiento = new JPanel();
			panelConSeguimiento.setLayout(new GridLayout(0, 2, 0, 0));
			panelConSeguimiento.add(getLblSloConSeguimiento());
			panelConSeguimiento.add(getChckbxConSeguimiento());
		}
		return panelConSeguimiento;
	}
	private JCheckBox getChckbxConSeguimiento() {
		if (chckbxConSeguimiento == null) {
			chckbxConSeguimiento = new JCheckBox("");
			chckbxConSeguimiento.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return chckbxConSeguimiento;
	}
	private JLabel getLblSloConSeguimiento() {
		if (lblSloConSeguimiento == null) {
			lblSloConSeguimiento = new JLabel("Sólo con seguimiento:");
			lblSloConSeguimiento.setHorizontalAlignment(SwingConstants.CENTER);
			lblSloConSeguimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblSloConSeguimiento;
	}
	private JButton getBtAbrir() {
		if (btAbrir == null) {
			btAbrir = new JButton("Abrir/modificar seguimiento");
			btAbrir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirSeguimiento();
				}
			});
		}
		return btAbrir;
	}
	

	private JButton getBtCerrar() {
		if (btCerrar == null) {
			btCerrar = new JButton("Cerrar seguimiento");
			btCerrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cerrarSeguimiento();
				}
			});
		}
		return btCerrar;
	}

	protected void abrirSeguimiento() {
		Diagnostico d=gD.getDiagnosticoCita((String) tm.getValueAt(table.getSelectedRow(), 0));
		
		gD.actualizarSeguimientoDiagnostico(d,true);
		
		String res=JOptionPane.showInputDialog("Inserte comentarios de seguimiento");
		gS.actualizarSeguimiento(d,res);
		
		recuperarDatos();
		filtrar();

		
	}
	protected void cerrarSeguimiento() {
		Seguimiento s=gS.getSeguimientoCita((String) tm.getValueAt(table.getSelectedRow(), 0));
		if(s.getEstado().equals("ABIERTO")) {
			gS.cerrarSeguimiento(s);
			recuperarDatos();
			filtrar();
		}
		
	}
	
	protected void verSeguimiento() {
		Diagnostico d=gD.getDiagnosticoCita((String) tm.getValueAt(table.getSelectedRow(), 0));
		Seguimiento s=gS.getSeguimientoCita((String) tm.getValueAt(table.getSelectedRow(), 0));
		if(d.getSeguimiento()) {
			JOptionPane.showMessageDialog(this, s.getComentarios(), "Comentarios de seguimiento de diagnostico en cita "+d.getCitaId(), JOptionPane.PLAIN_MESSAGE);
		}
		
	}
	
	private JButton getBtVerComentarios() {
		if (btVerComentarios == null) {
			btVerComentarios = new JButton("Ver seguimiento");
			btVerComentarios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verSeguimiento();
				}
			});
		}
		return btVerComentarios;
	}

	
}
