package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import logic.Medico;

public class VentanaBuscador extends JFrame {

	private JPanel panelP;
	private VentanaAñadirMedicos vam;
	private JPanel panelBuscar;
	private JPanel panelResultados;
	private JLabel lbBuscar;
	private JTextField tfBuscador;
	private JButton btBuscar;

	/**
	 * Create the frame.
	 */
	public VentanaBuscador(VentanaAñadirMedicos v) {
		this.vam=v;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 100, 787, 223);
		panelP = new JPanel();
		panelP.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelP);
		panelP.setLayout(new BorderLayout(0, 0));
		panelP.add(getPanelBuscar(), BorderLayout.NORTH);
		panelP.add(getPanelResultados(), BorderLayout.CENTER);
	}

	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			panelBuscar = new JPanel();
			panelBuscar.setLayout(new BorderLayout(10, 0));
			panelBuscar.add(getLbBuscar(), BorderLayout.WEST);
			panelBuscar.add(getTfBuscador(), BorderLayout.CENTER);
			panelBuscar.add(getBtBuscar(), BorderLayout.EAST);
		}
		return panelBuscar;
	}
	private JPanel getPanelResultados() {
		if (panelResultados == null) {
			panelResultados = new JPanel();
			panelResultados.setLayout(new GridLayout(1, 0, 0, 0));
			
		}
		return panelResultados;
	}
	
	
	private JPanel crearPanelMedico(Medico m) {
		JPanel panelMedico = new JPanel();
		panelMedico.setLayout(new GridLayout(0, 2));
		panelMedico.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMedico.setMaximumSize(new Dimension(this.getWidth(),40));
		panelMedico.setMinimumSize(new Dimension(this.getWidth(),40));
		panelMedico.setPreferredSize(new Dimension(this.getWidth(),40));


		JPanel panelBasicInfo = new JPanel();
		panelBasicInfo.setLayout(new GridLayout(1, 0));
		panelBasicInfo.add(new Label(m.getNombre()+" "+m.getApellido()+" "+m.getEspecialidad()));
		

		JButton btnAñadir = new JButton("Añadir medico");
		btnAñadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				añadir(m);
			}
		});

		panelMedico.add(panelBasicInfo);
		panelMedico.add(btnAñadir);

		return panelMedico;
	}
	protected void añadir(Medico m) {
		if(!vam.meds.contains(m)) {
			vam.meds.add(m);
			
			vam.getLbGuardado().setVisible(false);
			vam.getTextAreaVisualizar().setText(vam.getTextAreaVisualizar()
					.getText()+"\n"+ m.toString()+" ("+(m.getEspecialidad()+")"));
		}
		
	}

	private JLabel getLbBuscar() {
		if (lbBuscar == null) {
			lbBuscar = new JLabel("Buscar medico: ");
		}
		return lbBuscar;
	}
	private JTextField getTfBuscador() {
		if (tfBuscador == null) {
			tfBuscador = new JTextField();
			tfBuscador.setColumns(10);
		}
		return tfBuscador;
	}
	private JButton getBtBuscar() {
		if (btBuscar == null) {
			btBuscar = new JButton("Buscar");
			btBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actualizarResultados();
				}
			});
		}
		return btBuscar;
	}

	protected void actualizarResultados() {
		panelResultados.removeAll();
		for (Medico m : vam.vcc.getgM().getMedicos()) {
			if(m.getNombre().startsWith(getTfBuscador().getText()) || m.getApellido().startsWith(getTfBuscador().getText())) {
				panelResultados.add(crearPanelMedico(m));
				System.out.println("Creado panel de "+m.toString());
				panelResultados.validate();
				panelResultados.repaint();
			}
			
		}
		
	}
}
