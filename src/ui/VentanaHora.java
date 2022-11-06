package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.cita.Cita;

public class VentanaHora extends JFrame {

	private static final long serialVersionUID = 1L;

	private Cita cita;
	private VentanaCita vc;

	private JPanel contentPane;
	private JPanel pnInferior;
	private JButton btnCancelar;
	private JButton btnConfirmar;
	private JPanel pnCentral;
	private JPanel pnHoraEntrada;
	private JPanel pnHoraSalida;
	private JLabel lblHoraEntrada;
	private JLabel lblHoraSalida;
	private JTextField txtHoraEntrada;
	private JTextField txtHoraSalida;

	/**
	 * Create the frame.
	 */
	public VentanaHora(Cita cita, VentanaCita vc) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.vc = vc;
		this.cita = cita;

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnInferior(), BorderLayout.SOUTH);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
	}

	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			pnInferior.setLayout(new GridLayout(1, 0, 0, 0));
			pnInferior.add(getBtnCancelar());
			pnInferior.add(getBtnConfirmar());
		}
		return pnInferior;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cita.setHoraEntrada(getTxtHoraEntrada().getText());
					cita.setHoraSalida(getTxtHoraSalida().getText());
					vc.refrescarInfo();
					dispose();
				}
			});
		}
		return btnConfirmar;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(0, 1, 0, 0));
			pnCentral.add(getPnHoraEntrada());
			pnCentral.add(getPnHoraSalida());
		}
		return pnCentral;
	}

	private JPanel getPnHoraEntrada() {
		if (pnHoraEntrada == null) {
			pnHoraEntrada = new JPanel();
			pnHoraEntrada.add(getLblHoraEntrada());
			pnHoraEntrada.add(getTxtHoraEntrada());
		}
		return pnHoraEntrada;
	}

	private JPanel getPnHoraSalida() {
		if (pnHoraSalida == null) {
			pnHoraSalida = new JPanel();
			pnHoraSalida.add(getLblHoraSalida());
			pnHoraSalida.add(getTxtHoraSalida());
		}
		return pnHoraSalida;
	}

	private JLabel getLblHoraEntrada() {
		if (lblHoraEntrada == null) {
			lblHoraEntrada = new JLabel("Hora de entrada:");
		}
		return lblHoraEntrada;
	}

	private JLabel getLblHoraSalida() {
		if (lblHoraSalida == null) {
			lblHoraSalida = new JLabel("Hora de salida:");
		}
		return lblHoraSalida;
	}

	private JTextField getTxtHoraEntrada() {
		if (txtHoraEntrada == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHoraEntrada = new JFormattedTextField(mf);
				txtHoraEntrada.setHorizontalAlignment(SwingConstants.CENTER);
				txtHoraEntrada.setColumns(10);
				
				LocalTime now = LocalTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				txtHoraEntrada.setText(cita.getHoraEntrada() == null ? now.format(formatter) : cita.getHoraEntrada());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtHoraEntrada;
	}

	private JTextField getTxtHoraSalida() {
		if (txtHoraSalida == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHoraSalida = new JFormattedTextField(mf);
				txtHoraSalida.setHorizontalAlignment(SwingConstants.CENTER);
				txtHoraSalida.setColumns(10);
				
				LocalTime now = LocalTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				txtHoraSalida.setText(cita.getHoraEntrada() == null ? now.format(formatter) : cita.getHoraEntrada());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtHoraSalida;
	}
}
