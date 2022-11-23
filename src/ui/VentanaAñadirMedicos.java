package ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import logic.Medico;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPopupMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAñadirMedicos extends JFrame {

	private JPanel panelPrincipal;
	private JPanel panelSur;
	private JPanel panelCentro;
	private JPanel panelFiltros;
	private JButton btGuardarMedicos;
	private JPanel panelEspecialidades;
	private JLabel lbEspecialidades;
	private JComboBox cbEspecialidades;
	private JButton btAñadirEspecialidad;

	public VentanaCrearCita vcc;
	private JLabel lbGuardado;
	
	
	public ArrayList<Medico> meds=null;
	private ArrayList<Especialidad> esp=null;
	private JTextArea txtAVisualizar;
	private JButton btnBorrar;
	private JPanel panelBuscador;
	private JButton btnBuscar;
	
	
	

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
			panelSur.setLayout(new GridLayout(0, 3, 0, 0));
			panelSur.add(getBtnBorrar());
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
			panelFiltros.setLayout(new GridLayout(2, 0, 0, 0));
			panelFiltros.add(getPanelEspecialidades());
			panelFiltros.add(getPanelBuscador());
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
		for(Especialidad e :esp) {
			vcc.getTaMedicos().setText(vcc.getTaMedicos().getText()+e.getNombre_esp()+"\n");
			vcc.agregarEspecialidad(e.getId_esp());
			
			
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
			cbEspecialidades.addItem("Sin especificar");
			cbEspecialidades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(cbEspecialidades.getSelectedItem().equals("Sin especificar"))
						getBtAñadirEspecialidad().setEnabled(false);
					else getBtAñadirEspecialidad().setEnabled(true);
				}
			});
			
			for(Especialidad esp : vcc.getGE().getListaEspecialidades()) {
				cbEspecialidades.addItem(esp.getNombre_esp());
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
					Especialidad cand=vcc.getGE().buscarPorNombre(getCbEspecialidades().getSelectedItem().toString());
					if(!esp.contains(cand)) {
						cand.aumentarUnidades();
						esp.add(cand);
						
					}
					else esp.get(esp.indexOf(cand)).aumentarUnidades();
					getLbGuardado().setVisible(false);
					getTextAreaVisualizar().setText(getTextAreaVisualizar().getText()+"\n"+ getCbEspecialidades().getSelectedItem());
					
				}
			});
		}
		return btAñadirEspecialidad;
	}
	
	public void añadirMedicos(ArrayList<Medico>lista) {
		for(Medico m :meds) {
			System.out.println(m.toString());
		}
		for(Medico m : lista) {
			if(!meds.contains(m)) {
				meds.add(m);
				getLbGuardado().setVisible(false);
				getTextAreaVisualizar().setText(getTextAreaVisualizar().getText()+"\n"+ m.toString()+" ("+(m.getEspecialidad()+")"));
			}else JOptionPane.showMessageDialog(rootPane, "El medico ya está asignado!");
				
		}
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
	public JLabel getLbGuardado() {
		if (lbGuardado == null) {
			lbGuardado = new JLabel("Guardado!");
			lbGuardado.setHorizontalAlignment(SwingConstants.RIGHT);
			lbGuardado.setVisible(false);
			lbGuardado.setForeground(Color.RED);
		}
		return lbGuardado;
	}
	public ArrayList<Especialidad> getEsp() {
		return esp;
	}
	
	private JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar ultimo");
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(getTextAreaVisualizar().getText().isEmpty()) {}
					else {
						String[] txt=getTextAreaVisualizar().getText().split("\n");
						String ultima =txt[txt.length-1];
						
						if(txt.length==1)getTextAreaVisualizar().setText("");
						else {
							for(int i=0;i<txt.length-1;i++) {
								getTextAreaVisualizar().setText(txt[i]);
							
							}
						}
						if(ultima.contains("(")) {//el ultimo fue un medico
							meds.remove(meds.size()-1);
						}else esp.remove(esp.size()-1);//si no, el ultimo fue especialidad
						System.out.println(meds.size());
						System.out.println(esp.size());
					}
				}
			});
		}
		return btnBorrar;
	}
	private JPanel getPanelBuscador() {
		if (panelBuscador == null) {
			panelBuscador = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelBuscador.getLayout();
			flowLayout.setVgap(15);
			panelBuscador.add(getBtnBuscar());
		}
		return panelBuscador;
	}
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("Buscar medicos ");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaBuscador();
				}
			});
		}
		return btnBuscar;
	}
	protected void abrirVentanaBuscador() {
		VentanaBuscadorMedicos vb=new VentanaBuscadorMedicos(this);
		vb.setVisible(true);
		
	}
}
