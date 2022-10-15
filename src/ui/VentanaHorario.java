package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logic.Medico;

public class VentanaHorario extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel pnHeader;
	private JComboBox<Medico> cbMedicos;
	private JScrollPane spHorario;
	private JPanel pnResizeContainer;
	private JPanel pnHorario;
	private JPanel pnCita1;
	private JButton btnCita;
	private JPanel pnCita1_1;
	private JButton btnCita_1;
	private JPanel pnCita1_2;
	private JButton btnCita_2;
	private JPanel pnCita1_3;
	private JButton btnCita_3;
	private JPanel pnCita1_4;
	private JButton btnCita_4;
	private JPanel pnCita1_5;
	private JButton btnCita_5;
	private JPanel pnCita1_1_1;
	private JButton btnCita_1_1;
	private JPanel pnCita1_2_1;
	private JButton btnCita_2_1;
	private JPanel pnCita1_3_1;
	private JButton btnCita_3_1;
	private JPanel pnCita1_4_1;
	private JButton btnCita_4_1;
	private JPanel pnCita1_6;
	private JButton btnCita_6;
	private JPanel pnCita1_1_2;
	private JButton btnCita_1_2;
	private JPanel pnCita1_2_2;
	private JButton btnCita_2_2;
	private JPanel pnCita1_3_2;
	private JButton btnCita_3_2;
	private JPanel pnCita1_4_2;
	private JButton btnCita_4_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaHorario frame = new VentanaHorario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaHorario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnHeader(), BorderLayout.PAGE_START);
		contentPane.add(getSpHorario(), BorderLayout.CENTER);
	}

	private JPanel getPnHeader() {
		if (pnHeader == null) {
			pnHeader = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnHeader.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnHeader.add(getCbMedicos());
		}
		return pnHeader;
	}

	private JComboBox<Medico> getCbMedicos() {
		if (cbMedicos == null) {
			cbMedicos = new JComboBox<Medico>();
		}
		return cbMedicos;
	}

	private JScrollPane getSpHorario() {
		if (spHorario == null) {
			spHorario = new JScrollPane();
			spHorario.getViewport().add(getPnResizeContainer());
		}
		return spHorario;
	}

	private JPanel getPnResizeContainer() {
		if (pnResizeContainer == null) {
			pnResizeContainer = new JPanel();
			pnResizeContainer.add(getPnHorario());
		}
		return pnResizeContainer;
	}

	private JPanel getPnHorario() {
		if (pnHorario == null) {
			pnHorario = new JPanel();
			pnHorario.setLayout(new GridLayout(0, 1, 0, 0));
			pnHorario.add(getPnCita1());
			pnHorario.add(getPnCita1_1());
			pnHorario.add(getPnCita1_2());
			pnHorario.add(getPnCita1_3());
			pnHorario.add(getPnCita1_4());
			pnHorario.add(getPnCita1_5());
			pnHorario.add(getPnCita1_1_1());
			pnHorario.add(getPnCita1_2_1());
			pnHorario.add(getPnCita1_3_1());
			pnHorario.add(getPnCita1_4_1());
			pnHorario.add(getPnCita1_6());
			pnHorario.add(getPnCita1_1_2());
			pnHorario.add(getPnCita1_2_2());
			pnHorario.add(getPnCita1_3_2());
			pnHorario.add(getPnCita1_4_2());
		}
		return pnHorario;
	}

	private JPanel getPnCita1() {
		if (pnCita1 == null) {
			pnCita1 = new JPanel();
			pnCita1.add(getBtnCita());
		}
		return pnCita1;
	}

	private JButton getBtnCita() {
		if (btnCita == null) {
			btnCita = new JButton("Cita");
		}
		return btnCita;
	}

	private JPanel getPnCita1_1() {
		if (pnCita1_1 == null) {
			pnCita1_1 = new JPanel();
			pnCita1_1.add(getBtnCita_1_2());
		}
		return pnCita1_1;
	}

	private JButton getBtnCita_1_2() {
		if (btnCita_1 == null) {
			btnCita_1 = new JButton("Cita");
		}
		return btnCita_1;
	}

	private JPanel getPnCita1_2() {
		if (pnCita1_2 == null) {
			pnCita1_2 = new JPanel();
			pnCita1_2.add(getBtnCita_2_1());
		}
		return pnCita1_2;
	}

	private JButton getBtnCita_2_1() {
		if (btnCita_2 == null) {
			btnCita_2 = new JButton("Cita");
		}
		return btnCita_2;
	}

	private JPanel getPnCita1_3() {
		if (pnCita1_3 == null) {
			pnCita1_3 = new JPanel();
			pnCita1_3.add(getBtnCita_3_1());
		}
		return pnCita1_3;
	}

	private JButton getBtnCita_3_1() {
		if (btnCita_3 == null) {
			btnCita_3 = new JButton("Cita");
		}
		return btnCita_3;
	}

	private JPanel getPnCita1_4() {
		if (pnCita1_4 == null) {
			pnCita1_4 = new JPanel();
			pnCita1_4.add(getBtnCita_4());
		}
		return pnCita1_4;
	}

	private JButton getBtnCita_4() {
		if (btnCita_4 == null) {
			btnCita_4 = new JButton("Cita");
		}
		return btnCita_4;
	}

	private JPanel getPnCita1_5() {
		if (pnCita1_5 == null) {
			pnCita1_5 = new JPanel();
			pnCita1_5.add(getBtnCita_5());
		}
		return pnCita1_5;
	}

	private JButton getBtnCita_5() {
		if (btnCita_5 == null) {
			btnCita_5 = new JButton("Cita");
		}
		return btnCita_5;
	}

	private JPanel getPnCita1_1_1() {
		if (pnCita1_1_1 == null) {
			pnCita1_1_1 = new JPanel();
			pnCita1_1_1.add(getBtnCita_1_1_1());
		}
		return pnCita1_1_1;
	}

	private JButton getBtnCita_1_1_1() {
		if (btnCita_1_1 == null) {
			btnCita_1_1 = new JButton("Cita");
		}
		return btnCita_1_1;
	}

	private JPanel getPnCita1_2_1() {
		if (pnCita1_2_1 == null) {
			pnCita1_2_1 = new JPanel();
			pnCita1_2_1.add(getBtnCita_2_1_1());
		}
		return pnCita1_2_1;
	}

	private JButton getBtnCita_2_1_1() {
		if (btnCita_2_1 == null) {
			btnCita_2_1 = new JButton("Cita");
		}
		return btnCita_2_1;
	}

	private JPanel getPnCita1_3_1() {
		if (pnCita1_3_1 == null) {
			pnCita1_3_1 = new JPanel();
			pnCita1_3_1.add(getBtnCita_3_1_1());
		}
		return pnCita1_3_1;
	}

	private JButton getBtnCita_3_1_1() {
		if (btnCita_3_1 == null) {
			btnCita_3_1 = new JButton("Cita");
		}
		return btnCita_3_1;
	}

	private JPanel getPnCita1_4_1() {
		if (pnCita1_4_1 == null) {
			pnCita1_4_1 = new JPanel();
			pnCita1_4_1.add(getBtnCita_4_1());
		}
		return pnCita1_4_1;
	}

	private JButton getBtnCita_4_1() {
		if (btnCita_4_1 == null) {
			btnCita_4_1 = new JButton("Cita");
		}
		return btnCita_4_1;
	}

	private JPanel getPnCita1_6() {
		if (pnCita1_6 == null) {
			pnCita1_6 = new JPanel();
			pnCita1_6.add(getBtnCita_6());
		}
		return pnCita1_6;
	}

	private JButton getBtnCita_6() {
		if (btnCita_6 == null) {
			btnCita_6 = new JButton("Cita");
		}
		return btnCita_6;
	}

	private JPanel getPnCita1_1_2() {
		if (pnCita1_1_2 == null) {
			pnCita1_1_2 = new JPanel();
			pnCita1_1_2.add(getBtnCita_1_2_1());
		}
		return pnCita1_1_2;
	}

	private JButton getBtnCita_1_2_1() {
		if (btnCita_1_2 == null) {
			btnCita_1_2 = new JButton("Cita");
		}
		return btnCita_1_2;
	}

	private JPanel getPnCita1_2_2() {
		if (pnCita1_2_2 == null) {
			pnCita1_2_2 = new JPanel();
			pnCita1_2_2.add(getBtnCita_2_2());
		}
		return pnCita1_2_2;
	}

	private JButton getBtnCita_2_2() {
		if (btnCita_2_2 == null) {
			btnCita_2_2 = new JButton("Cita");
		}
		return btnCita_2_2;
	}

	private JPanel getPnCita1_3_2() {
		if (pnCita1_3_2 == null) {
			pnCita1_3_2 = new JPanel();
			pnCita1_3_2.add(getBtnCita_3_2());
		}
		return pnCita1_3_2;
	}

	private JButton getBtnCita_3_2() {
		if (btnCita_3_2 == null) {
			btnCita_3_2 = new JButton("Cita");
		}
		return btnCita_3_2;
	}

	private JPanel getPnCita1_4_2() {
		if (pnCita1_4_2 == null) {
			pnCita1_4_2 = new JPanel();
			pnCita1_4_2.add(getBtnCita_4_2());
		}
		return pnCita1_4_2;
	}

	private JButton getBtnCita_4_2() {
		if (btnCita_4_2 == null) {
			btnCita_4_2 = new JButton("Cita");
		}
		return btnCita_4_2;
	}
}
