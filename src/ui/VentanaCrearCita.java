package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.Medico;
import logic.Paciente;
import logic.Sala;
import nexus.GestorCitas;
import nexus.GestorEspecialidades;
import nexus.GestorMedicos;
import nexus.GestorPacientes;
import nexus.GestorSalas;

public class VentanaCrearCita extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipalCrearCita;
	private JPanel panelCampos;
	private JPanel panelPaciente;
	private JLabel lbPaciente;
	private JPanel panelHoraEntrada;
	private JLabel lbHoraEntrada;
	private JPanel panelFecha;
	private JLabel lbFecha;
	private JPanel panelSala;
	private JLabel lbSala;
	private JPanel panelUrgente;
	private JLabel lbUrgente;
	private JFormattedTextField tfHoraEntrada;
	private JFormattedTextField tfFecha;
	private JPanel panelRb;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JPanel panelTitulo;
	private JLabel lbTitulo;
	private JPanel panelBotonCrear;
	private JButton btCrear;

	private ButtonGroup bgUrgente;
	private GestorSalas gS;
	private GestorCitas gC;
	private GestorPacientes gP;
	private GestorMedicos gM;
	private GestorEspecialidades gE;
	private DialogoInfoContacto dialContacto;
	private JPanel panelContacto;
	private JButton btContacto;
	private JComboBox cbPacientes;
	private JPanel panelHoraSalida;
	private JLabel lbHoraSalida;
	private JFormattedTextField tfHoraSalida;
	private JPanel panelMedicos;
	private JComboBox<Medico> cbMedicos;
	private JPanel panelAdd;
	private JButton btAdd;
	private JComboBox<Sala> cbSala;
	private JButton btMotivos;
	private JPanel panelAgregarMedicos;
	private JButton btAñadirMedicos;
	private JPanel panelMedicosAgregados;
	private JTextArea taMedicos;
	private JLabel ln0;
	
	private ArrayList<Medico>medicosAgregados;
	private String motivosI="";

	/**
	 * Create the frame.
	 */
	public VentanaCrearCita() {
		gP = new GestorPacientes();
		gC = new GestorCitas();
		gM = new GestorMedicos();
		gS= new GestorSalas();
		gE= new GestorEspecialidades();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		medicosAgregados=new ArrayList<>();
		setBounds(100, 100, 715, 600);
		panelPrincipalCrearCita = new JPanel();
		panelPrincipalCrearCita.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgUrgente = new ButtonGroup();
		setContentPane(panelPrincipalCrearCita);
		panelPrincipalCrearCita.setLayout(new BorderLayout(0, 0));
		panelPrincipalCrearCita.add(getPanelCampos(), BorderLayout.CENTER);
		panelPrincipalCrearCita.add(getPanelTitulo(), BorderLayout.NORTH);
	}

	private JPanel getPanelCampos() {
		if (panelCampos == null) {
			panelCampos = new JPanel();
			panelCampos.setLayout(new GridLayout(0, 1, 10, 10));
			panelCampos.add(getPanelPaciente());
			panelCampos.add(getPanelHoraEntrada());
			panelCampos.add(getPanelHoraSalida());
			panelCampos.add(getPanelFecha());
			panelCampos.add(getPanelSala());
			panelCampos.add(getPanelAgregarMedicos1());
			panelCampos.add(getPanelMedicosAgregados_1());
			panelCampos.add(getPanelUrgente());
			panelCampos.add(getPanelBotonCrear());
		}
		return panelCampos;
	}
	
	public void setMotivosI(String str) {
		motivosI=str;
	}

	private JPanel getPanelPaciente() {
		if (panelPaciente == null) {
			panelPaciente = new JPanel();
			panelPaciente.setLayout(new GridLayout(0, 3, 10, 0));
			panelPaciente.add(getLbPaciente());
			panelPaciente.add(getCbPacientes());
			panelPaciente.add(getPanelContacto_1());
		}
		return panelPaciente;
	}

	private JLabel getLbPaciente() {
		if (lbPaciente == null) {
			lbPaciente = new JLabel("Paciente:");
			lbPaciente.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbPaciente;
	}

	private JPanel getPanelHoraEntrada() {
		if (panelHoraEntrada == null) {
			panelHoraEntrada = new JPanel();
			panelHoraEntrada.setLayout(new GridLayout(0, 3, 10, 0));
			panelHoraEntrada.add(getLbHora_1());
			panelHoraEntrada.add(getTfHoraEntrada());
		}
		return panelHoraEntrada;
	}

	private JLabel getLbHora_1() {
		if (lbHoraEntrada == null) {
			lbHoraEntrada = new JLabel("Hora entrada :");
			lbHoraEntrada.setLabelFor(getTfHoraEntrada());
			lbHoraEntrada.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHoraEntrada.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHoraEntrada;
	}

	private JPanel getPanelFecha() {
		if (panelFecha == null) {
			panelFecha = new JPanel();
			panelFecha.setLayout(new GridLayout(0, 3, 10, 0));
			panelFecha.add(getLbFecha_1());
			panelFecha.add(getTfFecha());
		}
		return panelFecha;
	}

	private JLabel getLbFecha_1() {
		if (lbFecha == null) {
			lbFecha = new JLabel("Fecha :");
			lbFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lbFecha.setLabelFor(getTfFecha());
		}
		return lbFecha;
	}

	private JPanel getPanelSala() {
		if (panelSala == null) {
			panelSala = new JPanel();
			panelSala.setLayout(new GridLayout(0, 3, 10, 0));
			panelSala.add(getLbSala_1());
			panelSala.add(getCbSala());
		}
		return panelSala;
	}

	private JLabel getLbSala_1() {
		if (lbSala == null) {
			lbSala = new JLabel("Sala :");
			lbSala.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbSala;
	}

	private JPanel getPanelUrgente() {
		if (panelUrgente == null) {
			panelUrgente = new JPanel();
			panelUrgente.setLayout(new GridLayout(0, 3, 10, 0));
			panelUrgente.add(getLbUrgente_1());
			panelUrgente.add(getPanelRb());
		}
		return panelUrgente;
	}

	private JLabel getLbUrgente_1() {
		if (lbUrgente == null) {
			lbUrgente = new JLabel("Urgente :");
			lbUrgente.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbUrgente;
	}

	private JTextField getTfHoraEntrada() {
		if (tfHoraEntrada == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				tfHoraEntrada = new JFormattedTextField(mf);
				tfHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);

				tfHoraEntrada.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return tfHoraEntrada;
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

	private JPanel getPanelRb() {
		if (panelRb == null) {
			panelRb = new JPanel();
			bgUrgente.add(getRbSi());
			bgUrgente.add(getRbNo());
			panelRb.add(getRbSi());
			panelRb.add(getRbNo());
			

		}
		return panelRb;
	}

	private JRadioButton getRbSi() {
		if (rbSi == null) {
			rbSi = new JRadioButton("Si");
		}
		return rbSi;
	}

	private JRadioButton getRbNo() {
		if (rbNo == null) {
			rbNo = new JRadioButton("No");
			rbNo.setSelected(true);
		}
		return rbNo;
	}

	private JPanel getPanelTitulo() {
		if (panelTitulo == null) {
			panelTitulo = new JPanel();
			panelTitulo.add(getLbTitulo());
		}
		return panelTitulo;
	}

	private JLabel getLbTitulo() {
		if (lbTitulo == null) {
			lbTitulo = new JLabel("CREAR NUEVA CITA");
			lbTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		}
		return lbTitulo;
	}

	private JPanel getPanelBotonCrear() {
		if (panelBotonCrear == null) {
			panelBotonCrear = new JPanel();
			panelBotonCrear.add(getBtMotivos());
			panelBotonCrear.add(getBtCrear());
		}
		return panelBotonCrear;
	}

	private JButton getBtCrear() {
		if (btCrear == null) {
			btCrear = new JButton("Crear cita");
			btCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarYCrear();

				}

			});
		}
		return btCrear;
	}

	protected void comprobarYCrear() {
		boolean valido = true;
		
		if(gC.getMedicosAgregados().size()==0 && gC.getEspecialidadesAgregadas().size()==0) {
			valido=false;
			new JOptionPane().showMessageDialog(this, "Error, No hay medicos ni especialidades agregados");
		}
		String[] nomApe = getCbPacientes().getSelectedItem().toString().split(" ");
		String nombre = nomApe[0];
		String apellido = nomApe[1];
		int idPaciente = gP.buscarIdPaciente(nombre, apellido);
		if (idPaciente == -1)
			valido = false;
		String horaE = getTfHoraEntrada().getText();;
		String horaS = getTfHoraSalida().getText();
		

		String fecha = getTfFecha().getText();
		if (gC.comprobarCitaEnJornada(fecha,horaE, horaS,gC.getMedicosAgregados() )==false) {
			
			valido = false;
			new JOptionPane().showMessageDialog(this, "Error, la fecha y horas escritas no están en la jornada de los medicos seleccionados");
		}
			
		if(gC.comprobarCitasEnHorario(fecha, horaE, horaS, getgM().getMedicos())==false) {
			valido=false;
			new JOptionPane().showMessageDialog(this, "Error, alguno de los medicos asignados ya tienen citas asignadas para el perido establecido");

		}
			
		
		
		int sala = ((Sala)getCbSala().getSelectedItem()).getId();
		String motivos=motivosI;
	
		boolean urg;
		if (getRbSi().isSelected()) {
			urg = true;
		} else
			urg = false;

		if (valido)
			crearCita(idPaciente, nombre, fecha, horaE, horaS, sala, urg, motivos);

	}

	

	private void crearCita(int idPaciente, String nombre, String fecha, String horaE, String horaS, int sala,
			boolean urg, String motivos) {

		gC.nuevaCita(idPaciente, nombre, fecha, horaE, horaS, sala, urg, motivos);
		resetear();

	}

	private void resetear() {
		getCbPacientes().setSelectedIndex(0);
		actualizarInfoProvisional();
		getTfFecha().setText("");
		getTfHoraEntrada().setText("");
		getTfHoraSalida().setText("");
		getCbSala().setSelectedIndex(0);
		getRbSi().setSelected(false);
		getRbNo().setSelected(false);
		getTaMedicos().setText("");
		

	}

	private void mostrarVentanaInfoContacto() {
		String[] nomApe = getCbPacientes().getSelectedItem().toString().split(" ");
		dialContacto = new DialogoInfoContacto(gC, gP.buscarIdPaciente(nomApe[0], nomApe[1]));
		dialContacto.setVisible(true);
	}

	private JPanel getPanelContacto_1() {
		if (panelContacto == null) {
			panelContacto = new JPanel();
			panelContacto.add(getBtContacto_1());
		}
		return panelContacto;
	}

	private JButton getBtContacto_1() {
		if (btContacto == null) {
			btContacto = new JButton("Info contacto");
			btContacto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaInfoContacto();
				}

			});
		}
		return btContacto;
	}

	private JComboBox getCbPacientes() {
		if (cbPacientes == null) {
			cbPacientes = new JComboBox();
			cbPacientes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarInfoProvisional();
				}
			});
			cargarComboPacientes();
			actualizarInfoProvisional();
		}
		
	
		return cbPacientes;
	}

	private void actualizarInfoProvisional() {
		String[] nomApe = getCbPacientes().getSelectedItem().toString().split(" ");
		String nombre = nomApe[0];
		String apellido = nomApe[1];
		int idPaciente = gP.buscarIdPaciente(nombre, apellido);//id del paciente seleccionado al inicio
		gC.añadirInfoContactoProv(gP.getPaciente(idPaciente));
	}

	private void cargarComboPacientes() {
		for (Paciente p : gP.getListaPacientes()) {
			getCbPacientes().addItem(p.getNombre() + " " + p.getApellido());
		}
	}

	private JPanel getPanelHoraSalida() {
		if (panelHoraSalida == null) {
			panelHoraSalida = new JPanel();
			panelHoraSalida.setLayout(new GridLayout(0, 3, 10, 0));
			panelHoraSalida.add(getLbHoraSalida());
			panelHoraSalida.add(getTfHoraSalida());
		}
		return panelHoraSalida;
	}

	private JLabel getLbHoraSalida() {
		if (lbHoraSalida == null) {
			lbHoraSalida = new JLabel("Hora salida:");
			lbHoraSalida.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHoraSalida.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHoraSalida;
	}

	private JFormattedTextField getTfHoraSalida() {
		if (tfHoraSalida == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				tfHoraSalida = new JFormattedTextField(mf);
				tfHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);

				tfHoraSalida.setColumns(10);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return tfHoraSalida;
	}
	private JComboBox<Sala> getCbSala() {
		if (cbSala == null) {
			cbSala = new JComboBox<Sala>();
			cargarComboSalas();
		}
		return cbSala;
	}

	private void cargarComboSalas() {
		for (Sala s : gS.getListaSalas()) {
			getCbSala().addItem(s);
		}
		
	}
	private JButton getBtMotivos() {
		if (btMotivos == null) {
			btMotivos = new JButton("Añadir Motivos Cita");
			btMotivos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaMotivos();
				}
			});
		}
		return btMotivos;
	}

	protected void abrirVentanaMotivos() {
		VentanaMotivos vm=new VentanaMotivos(this);
		vm.setVisible(true);
		
	}

	protected void abrirVentanaMedicos() {
		VentanaAñadirMedicos vam=new VentanaAñadirMedicos(this);
		vam.setVisible(true);
		
	}

	public GestorMedicos getgM() {
		return gM;
	}

	
	private JPanel getPanelAgregarMedicos1() {
		if (panelAgregarMedicos == null) {
			panelAgregarMedicos = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelAgregarMedicos.getLayout();
			panelAgregarMedicos.add(getBtAñadirMedicos());
		}
		return panelAgregarMedicos;
	}
	private JButton getBtAñadirMedicos() {
		if (btAñadirMedicos == null) {
			btAñadirMedicos = new JButton("Añadir médicos");
			btAñadirMedicos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					abrirVentanaCrearMedicos();
				}
			});
		}
		return btAñadirMedicos;
	}
	protected void abrirVentanaCrearMedicos() {
		VentanaAñadirMedicos vam=new VentanaAñadirMedicos(this);
		vam.setVisible(true);
		
	}

	private JPanel getPanelMedicosAgregados_1() {
		if (panelMedicosAgregados == null) {
			panelMedicosAgregados = new JPanel();
			panelMedicosAgregados.setBorder(null);
			panelMedicosAgregados.setLayout(new GridLayout(0, 3, 0, 0));
			panelMedicosAgregados.add(getLn0());
			panelMedicosAgregados.add(getTaMedicos());
		}
		return panelMedicosAgregados;
	}
	public JTextArea getTaMedicos() {
		if (taMedicos == null) {
			taMedicos = new JTextArea();
			taMedicos.setAutoscrolls(false);
		}
		return taMedicos;
	}
	private JLabel getLn0() {
		if (ln0 == null) {
			ln0 = new JLabel("");
		}
		return ln0;
	}

	public void agregarMedico(String id) {
		for(Medico m:gM.getMedicos()) {
			if(m.getId()==id) {//si el str es un medico se le asocia a la cita
				gC.agregarMedico(m);
			}
			
		}
		
		
	}
	
	
	public void agregarEspecialidad(String id) {
		for(Especialidad e:gE.getListaEspecialidades()) {
			if(e.getId_esp()==id) {//si el str es un medico se le asocia a la cita
				gC.agregarEspecialidad(e);
			}
		}
	}
	
	public GestorEspecialidades getGE() {
		return gE;
	}

}
