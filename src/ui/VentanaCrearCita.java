package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import nexus.GestorCitas;
import nexus.GestorPacientes;

public class VentanaCrearCita extends JFrame {

	private JPanel panelPrincipalCrearCita;
	private JPanel panelCampos;
	private JPanel panelID;
	private JPanel panelNombre;
	private JLabel lbPacienteId;
	private JLabel lbNombre;
	private JTextField tfNombre;
	private JTextField tfId;
	private JPanel panelHora;
	private JLabel lbHora;
	private JPanel panelFecha;
	private JLabel lbFecha;
	private JPanel panelSala;
	private JLabel lbSala;
	private JPanel panelUrgente;
	private JLabel lbUrgente;
	private JTextField tfHora;
	private JTextField tfFecha;
	private JTextField tfSala;
	private JPanel panelRb;
	private JRadioButton rbSi;
	private JRadioButton rbNo;
	private JPanel panelTitulo;
	private JLabel lbTitulo;
	private JPanel panelBotonCrear;
	private JButton btCrear;

	private ButtonGroup bgUrgente;
	private GestorCitas gestorCitas;
	private JPanel panelContacto;
	private JButton btContacto;
	private GestorPacientes gP;
	private DialogoInfoContacto dialContacto;

	/**
	 * Create the frame.
	 */
	public VentanaCrearCita() {
		gP=new GestorPacientes();
		gestorCitas=new GestorCitas();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 408);
		panelPrincipalCrearCita = new JPanel();
		panelPrincipalCrearCita.setBorder(new EmptyBorder(5, 5, 5, 5));
		bgUrgente=new ButtonGroup();
		setContentPane(panelPrincipalCrearCita);
		panelPrincipalCrearCita.setLayout(new BorderLayout(0, 0));
		panelPrincipalCrearCita.add(getPanelCampos(), BorderLayout.CENTER);
		panelPrincipalCrearCita.add(getPanelTitulo(), BorderLayout.NORTH);
	}
	private JPanel getPanelCampos() {
		if (panelCampos == null) {
			panelCampos = new JPanel();
			panelCampos.setLayout(new GridLayout(0, 1, 10, 10));
			panelCampos.add(getPanelID());
			panelCampos.add(getPanelNombre());
			panelCampos.add(getPanelHora());
			panelCampos.add(getPanelFecha());
			panelCampos.add(getPanelSala());
			panelCampos.add(getPanelUrgente());
			panelCampos.add(getPanelBotonCrear());
		}
		return panelCampos;
	}
	private JPanel getPanelID() {
		if (panelID == null) {
			panelID = new JPanel();
			panelID.setLayout(new GridLayout(0, 3, 10, 0));
			panelID.add(getLbPacienteId_1());
			panelID.add(getTfId());
			panelID.add(getPanelContacto());
		}
		return panelID;
	}
	private JPanel getPanelNombre() {
		if (panelNombre == null) {
			panelNombre = new JPanel();
			panelNombre.setLayout(new GridLayout(0, 3, 10, 0));
			panelNombre.add(getLbNombre());
			panelNombre.add(getTfNombre());
		}
		return panelNombre;
	}
	private JLabel getLbPacienteId_1() {
		if (lbPacienteId == null) {
			lbPacienteId = new JLabel("Id del paciente :");
			lbPacienteId.setHorizontalAlignment(SwingConstants.RIGHT);
			lbPacienteId.setLabelFor(getTfId());
		}
		return lbPacienteId;
	}
	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre :");
			lbNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lbNombre.setLabelFor(getTfNombre());
		}
		return lbNombre;
	}
	private JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setHorizontalAlignment(SwingConstants.LEFT);
			tfNombre.setColumns(10);
		}
		return tfNombre;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setHorizontalAlignment(SwingConstants.LEFT);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JPanel getPanelHora() {
		if (panelHora == null) {
			panelHora = new JPanel();
			panelHora.setLayout(new GridLayout(0, 3, 10, 0));
			panelHora.add(getLbHora_1());
			panelHora.add(getTfHora());
		}
		return panelHora;
	}
	private JLabel getLbHora_1() {
		if (lbHora == null) {
			lbHora = new JLabel("Hora :");
			lbHora.setLabelFor(getTfHora());
			lbHora.setHorizontalTextPosition(SwingConstants.CENTER);
			lbHora.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lbHora;
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
			panelSala.add(getTfSala());
		}
		return panelSala;
	}
	private JLabel getLbSala_1() {
		if (lbSala == null) {
			lbSala = new JLabel("Sala :");
			lbSala.setHorizontalAlignment(SwingConstants.RIGHT);
			lbSala.setLabelFor(getTfSala());
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
	private JTextField getTfHora() {
		if (tfHora == null) {
			tfHora = new JTextField();
			tfHora.setHorizontalAlignment(SwingConstants.LEFT);
			tfHora.setColumns(10);
		}
		return tfHora;
	}
	private JTextField getTfFecha() {
		if (tfFecha == null) {
			tfFecha = new JTextField();
			tfFecha.setHorizontalAlignment(SwingConstants.LEFT);
			tfFecha.setColumns(10);
		}
		return tfFecha;
	}
	private JTextField getTfSala() {
		if (tfSala == null) {
			tfSala = new JTextField();
			tfSala.setHorizontalAlignment(SwingConstants.LEFT);
			tfSala.setColumns(10);
		}
		return tfSala;
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
			panelBotonCrear.add(getBtCrear());
		}
		return panelBotonCrear;
	}
	private JButton getBtCrear() {
		if (btCrear == null) {
			btCrear = new JButton("Crear cita");
			btCrear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					crearCita();
				}

				
			});
		}
		return btCrear;
	}
	
	private void crearCita() {
		int idPaciente=Integer.parseInt(getTfId().getText());
		String nombre=getTfNombre().getText();
		String fecha=getTfFecha().getText();
		String hora=getTfHora().getText();
		int sala=Integer.parseInt(getTfSala().getText());
		
		boolean urg;
		if(getRbSi().isSelected()) {
			urg=true;
		}else urg=false;
		//gestorCitas.nuevaCita(idPaciente, nombre, fecha, hora, sala, urg);
		resetear();
		
		
	}

	private void resetear() {
		getTfId().setText("");
		getTfNombre().setText("");
		getTfFecha().setText("");
		getTfHora().setText("");
		getTfSala().setText("");
		getRbSi().setSelected(false);
		getRbNo().setSelected(false);
		
		
		
	}
	private JPanel getPanelContacto() {
		if (panelContacto == null) {
			panelContacto = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panelContacto.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelContacto.add(getBtContacto());
		}
		return panelContacto;
	}
	private JButton getBtContacto() {
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
	
	private void mostrarVentanaInfoContacto() {
		dialContacto=new DialogoInfoContacto(gP,Integer.parseInt(getTfId().getText()));
		dialContacto.setVisible(true);
	}
}
