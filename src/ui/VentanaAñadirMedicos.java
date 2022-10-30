package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logic.Medico;

public class VentanaAñadirMedicos extends JFrame {

	private JPanel panelPrincipal;
	private JPanel panelSur;
	private JPanel panelCentro;
	private JPanel panelFiltros;
	private JButton btGuardarMedicos;
	private JPanel panelEspecialidades;
	private JPanel panelMedicos;
	private JLabel lbEspecialidades;
	private JComboBox cbEspecialidades;
	private JButton btAñadirEspecialidad;
	private JLabel lbMedicos;
	private JComboBox cbMedicos;
	private JButton btAñadirMedicos;

	private VentanaCrearCita vcc;
	private JLabel lbGuardado;
	
	
	private ArrayList<Medico> meds=null;
	private ArrayList<String> esp=null;
	private JTextArea txtAVisualizar;
	
	
	

	/**
	 * Create the frame.
	 * @param ventanaCrearCita 
	 */
	public VentanaAñadirMedicos(VentanaCrearCita ventanaCrearCita) {
		meds=new ArrayList<>();
		esp=new ArrayList<>();
		this.vcc=ventanaCrearCita;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipal.add(getPanelSur(), BorderLayout.SOUTH);
		panelPrincipal.add(getPanelCentro(), BorderLayout.CENTER);
	}
	public VentanaCrearCita getVCC() {
		return this.vcc;
	}

	private JPanel getPanelSur() {
		if (panelSur == null) {
			panelSur = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelSur.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panelSur.add(getLbGuardado());
			panelSur.add(getBtGuardarMedicos());
		}
		return panelSur;
	}
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new GridLayout(2, 1, 0, 0));
			panelCentro.add(getPanelFiltros());
			panelCentro.add(getTextAreaVisualizar());
		}
		return panelCentro;
	}
	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			panelFiltros = new JPanel();
			panelFiltros.setLayout(new GridLayout(2, 0, 0, 10));
			panelFiltros.add(getPanelEspecialidades());
			panelFiltros.add(getPanelMedicos());
		}
		return panelFiltros;
	}
	private JButton getBtGuardarMedicos() {
		if (btGuardarMedicos == null) {
			
			btGuardarMedicos = new JButton("Guardar");
			btGuardarMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(esp.size()!=0) {
						volcarACrearCita();
						getLbGuardado().setVisible(true);
					}else {
						for(Medico m:meds) {
							vcc.getTaMedicos().setText(vcc.getTaMedicos().getText()+m.toString()+" ("+m.getEspecialidad()+")\n");
							vcc.agregarMedico(m.getId());
						}
						dispose();
					}
					
					
				}
			});
		}
		return btGuardarMedicos;
	}
	protected void volcarACrearCita() {
	

		for(Medico m:meds) {
			vcc.getTaMedicos().setText(vcc.getTaMedicos().getText()+m.toString()+" ("+m.getEspecialidad()+")\n");
			vcc.agregarMedico(m.getId());
		}
		System.out.println("---"+esp.size());
		for(String str :esp) {
			seleccionarMedicosAlternativosSegunEspecialidad(str);
			continue;
			
			
		}
		
		
		
	}

	private void seleccionarMedicosAlternativosSegunEspecialidad(String esp) {
		VentanaAlternativos va=new VentanaAlternativos(this, esp);
		va.setVisible(true);
		
		
	}

	private JPanel getPanelEspecialidades() {
		if (panelEspecialidades == null) {
			panelEspecialidades = new JPanel();
			panelEspecialidades.setLayout(new BorderLayout(10, 5));
			panelEspecialidades.add(getLbEspecialidades(), BorderLayout.WEST);
			panelEspecialidades.add(getCbEspecialidades(), BorderLayout.CENTER);
			panelEspecialidades.add(getBtAñadirEspecialidad(), BorderLayout.EAST);
		}
		return panelEspecialidades;
	}
	private JPanel getPanelMedicos() {
		if (panelMedicos == null) {
			panelMedicos = new JPanel();
			panelMedicos.setLayout(new BorderLayout(10, 5));
			panelMedicos.add(getLbMedicos(), BorderLayout.WEST);
			panelMedicos.add(getCbMedicos());
			panelMedicos.add(getBtAñadirMedicos(), BorderLayout.EAST);
		}
		return panelMedicos;
	}
	private JLabel getLbEspecialidades() {
		if (lbEspecialidades == null) {
			lbEspecialidades = new JLabel("Especialidades");
			lbEspecialidades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lbEspecialidades;
	}
	private JComboBox<String> getCbEspecialidades() {
		if (cbEspecialidades == null) {
			cbEspecialidades = new JComboBox<String>();
			cbEspecialidades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarComboMedicos();
					if(cbEspecialidades.getSelectedItem().equals("Sin especificar"))
						getBtAñadirEspecialidad().setEnabled(false);
					else getBtAñadirEspecialidad().setEnabled(true);
				}
			});
			cbEspecialidades.addItem("Sin especificar");
			for(String nombre : vcc.getEspecialidades()) {
				cbEspecialidades.addItem(nombre);
			}
			
		}
		return cbEspecialidades;
	}
	private JButton getBtAñadirEspecialidad() {
		if (btAñadirEspecialidad == null) {
			btAñadirEspecialidad = new JButton("+");
			btAñadirEspecialidad.setEnabled(false);
			btAñadirEspecialidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					esp.add( ((Medico) getCbMedicos().getSelectedItem()).getEspecialidad());
					getLbGuardado().setVisible(false);
					getTextAreaVisualizar().setText(getTextAreaVisualizar().getText()+"\n"+ getCbEspecialidades().getSelectedItem());
					
				}
			});
		}
		return btAñadirEspecialidad;
	}
	private JLabel getLbMedicos() {
		if (lbMedicos == null) {
			lbMedicos = new JLabel("Medicos");
			lbMedicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lbMedicos;
	}
	private JComboBox<Medico> getCbMedicos() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox<Medico>();
			for(Medico m : vcc.getgM().getMedicos()) {
				cbMedicos.addItem(m);
			}
			System.out.println(vcc.getgM().getMedicos().size());
		}
		
		
		return cbMedicos;
	}
	
	

	private void actualizarComboMedicos() {
		getCbMedicos().removeAllItems();
		for (Medico m : vcc.getgM().getMedicos()) {
			if(getCbEspecialidades().getSelectedItem().equals("Sin especificar")) {
					cbMedicos.addItem(m);
			}else if(m.getEspecialidad().equals(getCbEspecialidades().getSelectedItem())) {
				cbMedicos.addItem(m);
			}
			
		}
	}
	private JButton getBtAñadirMedicos() {
		if (btAñadirMedicos == null) {
			btAñadirMedicos = new JButton("+");
			btAñadirMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!meds.contains(getCbMedicos().getSelectedItem())) {
						meds.add((Medico) getCbMedicos().getSelectedItem());
						
						getLbGuardado().setVisible(false);
						getTextAreaVisualizar().setText(getTextAreaVisualizar().getText()+"\n"+ getCbMedicos().getSelectedItem().toString()+" ("+((Medico) getCbMedicos().getSelectedItem()).getEspecialidad()+")");
					}else JOptionPane.showMessageDialog(rootPane, "El medico ya está asignado!");
					
					
				}
			});
		}
		return btAñadirMedicos;
	}
	
	public JTextArea getTextAreaVisualizar() {
		if (txtAVisualizar == null) {
			txtAVisualizar = new JTextArea();
			txtAVisualizar.setEnabled(true);
			txtAVisualizar.setEditable(false);
			txtAVisualizar.setVisible(true);
			validate();
			repaint();
		}
		return txtAVisualizar;
	}
	private JLabel getLbGuardado() {
		if (lbGuardado == null) {
			lbGuardado = new JLabel("Guardado!");
			lbGuardado.setVisible(false);
			lbGuardado.setForeground(Color.RED);
		}
		return lbGuardado;
	}
	public ArrayList<String> getEsp() {
		return esp;
	}
	
}
