package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.Medico;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAlternativos extends JFrame {

	private JPanel contentPane;
	private JPanel pnNorte;
	private JLabel lblAtencionParaEl;
	private JLabel lbInfo3;
	private JPanel pnCentro;
	private JComboBox cbAlternativos;
	private JLabel lbInfo2;
	private JLabel lb0;
	private JLabel lb0_1;
	private JButton btAsignar;

	private VentanaAñadirMedicos vam;
	private String especialidad;
	private Medico asignado;

	/**
	 * Create the frame.
	 * @param esp 
	 * @param ventanaAñadirMedicos 
	 */
	public VentanaAlternativos(VentanaAñadirMedicos vam, String esp) {
		this.vam=vam;
		this.especialidad =esp;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnNorte(), BorderLayout.NORTH);
		contentPane.add(getPnCentro(), BorderLayout.CENTER);
	}
	private JPanel getPnNorte() {
		if (pnNorte == null) {
			pnNorte = new JPanel();
			pnNorte.setLayout(new GridLayout(3, 1, 0, 0));
			pnNorte.add(getLblAtencionParaEl());
			pnNorte.add(getLbInfo2());
			pnNorte.add(getLbInfo3());
		}
		return pnNorte;
	}
	private JLabel getLblAtencionParaEl() {
		if (lblAtencionParaEl == null) {
			lblAtencionParaEl = new JLabel("ATENCION: Para el horario establecido");
		}
		return lblAtencionParaEl;
	}
	private JLabel getLbInfo3() {
		if (lbInfo3 == null) {
			lbInfo3 = new JLabel("Selecciona uno");
		}
		return lbInfo3;
	}
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setLayout(new GridLayout(0, 1, 0, 0));
			pnCentro.add(getCbAlternativos());
			pnCentro.add(getLb0());
			pnCentro.add(getLb0_1());
			pnCentro.add(getBtAsignar());
		}
		return pnCentro;
	}
	private JComboBox<Medico> getCbAlternativos() {
		if (cbAlternativos == null) {
			cbAlternativos = new JComboBox<Medico>();
			cargarCb();
		}
		return cbAlternativos;
	}
	private void cargarCb() {
		for(Medico m : vam.getVCC().getgM().getMedicos()) {
			if(m.getEspecialidad().equals(especialidad))
				cbAlternativos.addItem(m);
		}
		
	}
	private JLabel getLbInfo2() {
		if (lbInfo2 == null) {
			lbInfo2 = new JLabel("están disponibles los siguientes medicos");
		}
		return lbInfo2;
	}
	private JLabel getLb0() {
		if (lb0 == null) {
			lb0 = new JLabel("");
		}
		return lb0;
	}
	private JLabel getLb0_1() {
		if (lb0_1 == null) {
			lb0_1 = new JLabel("");
		}
		return lb0_1;
	}
	private JButton getBtAsignar() {
		if (btAsignar == null) {
			btAsignar = new JButton("Asignar");
			btAsignar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					asignado=(Medico) getCbAlternativos().getSelectedItem();
					vam.getTextAreaVisualizar().setText(vam.getTextAreaVisualizar().getText()+" - "+asignado.toString()+"\n");
					vam.getVCC().getTaMedicos().setText(vam.getVCC().getTaMedicos().getText()+asignado.toString()+" ("+asignado.getEspecialidad()+")\n");
					vam.getVCC().agregarMedico(asignado.getId());
					vam.getEsp().remove(especialidad);
					dispose();
				}
			});
		}
		return btAsignar;
	}

	public Medico getAsignado() {
		return asignado;
	}
}
