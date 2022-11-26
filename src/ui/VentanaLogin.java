package ui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {

	private JPanel panelPrincipal;
	private JPanel panelMedico;
	private JPanel panelAdministrativo;
	private JButton btEntrarMedico;
	private JButton btEntrarAdministrativo;

	

	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new GridLayout(1, 0, 0, 0));
		panelPrincipal.add(getPanelMedico());
		panelPrincipal.add(getPanelAdministrativo());
	}

	private JPanel getPanelMedico() {
		if (panelMedico == null) {
			panelMedico = new JPanel();
			panelMedico.setLayout(new BorderLayout(0, 0));
			panelMedico.add(getBtEntrarMedico());
		}
		return panelMedico;
	}
	private JPanel getPanelAdministrativo() {
		if (panelAdministrativo == null) {
			panelAdministrativo = new JPanel();
			panelAdministrativo.setLayout(new BorderLayout(0, 0));
			panelAdministrativo.add(getBtEntrarAdministrativo());
		}
		return panelAdministrativo;
	}
	private JButton getBtEntrarMedico() {
		if (btEntrarMedico == null) {
			btEntrarMedico = new JButton("Entrar como médico");
			btEntrarMedico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					entrarComoMedico();
				}
			});
			btEntrarMedico.setBackground(Color.WHITE);
		}
		return btEntrarMedico;
	}
	protected void entrarComoMedico() {
		VentanaMedico vm=new VentanaMedico();
		vm.show();
		
	}

	private JButton getBtEntrarAdministrativo() {
		if (btEntrarAdministrativo == null) {
			btEntrarAdministrativo = new JButton("Entrar como Administrativo");
			btEntrarAdministrativo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					entrarComoAdministrativo();
				}
			});
			btEntrarAdministrativo.setBackground(Color.WHITE);
		}
		return btEntrarAdministrativo;
	}

	protected void entrarComoAdministrativo() {
		VentanaAdministrador vA=new VentanaAdministrador();
		vA.show();
		
	}
}
