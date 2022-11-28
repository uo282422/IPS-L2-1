package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.cita.Cita;
import logic.procedimiento.Procedimiento;
import logic.procedimiento.ProcedimientoSeccion;
import logic.procedimiento.ProcedimientoSistema;
import logic.procedimiento.ProcedimientoTipo;
import nexus.GestorProcedimientos;

public class VentanaProcedimiento extends JFrame {

	private static final long serialVersionUID = 1L;

	private Cita cita;
	private VentanaCita vc;
	private GestorProcedimientos gp;

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnGuardar;
	private JPanel pnCental;
	private JPanel pnCombos;
	private JPanel pnDetalles;
	private JPanel pnFecha;
	private JPanel pnHora;
	private Component verticalStrut_1;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblHora;
	private JTextField txtHora;
	private JPanel pnSeccion;
	private JPanel pnSistema;
	private JPanel pnTipo;
	private JLabel lblSeccion;
	private JComboBox<ProcedimientoSeccion> cbSeccion;
	private JLabel lblSistema;
	private JComboBox<ProcedimientoSistema> cbSistema;
	private JLabel lblTipo;
	private JComboBox<ProcedimientoTipo> cbTipo;

	private DefaultComboBoxModel<ProcedimientoSeccion> secciones = new DefaultComboBoxModel<ProcedimientoSeccion>();
	private DefaultComboBoxModel<ProcedimientoSistema> sistemas = new DefaultComboBoxModel<ProcedimientoSistema>();
	private DefaultComboBoxModel<ProcedimientoTipo> tipos = new DefaultComboBoxModel<ProcedimientoTipo>();

	public VentanaProcedimiento(Cita c, VentanaCita vc) {

		this.cita = c;
		this.vc = vc;
		this.gp = new GestorProcedimientos();

		for (ProcedimientoSeccion s : gp.getSecciones()) {
			secciones.addElement(s);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 960, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getBtnGuardar(), BorderLayout.SOUTH);
		contentPane.add(getPnCental(), BorderLayout.CENTER);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Creacion de procedimiento");
			lblTitulo.setFont(new Font("Dialog", Font.BOLD, 34));
		}
		return lblTitulo;
	}

	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.setFont(new Font("Dialog", Font.BOLD, 20));
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Procedimiento p = new Procedimiento(String.valueOf(cita.getIdCita()),
							((ProcedimientoTipo) cbTipo.getSelectedItem()).getIdTipo(), txtFecha.getText(),
							txtHora.getText());
					p.setDescripcion(((ProcedimientoTipo) cbTipo.getSelectedItem()).getNombre());
					cita.addProcedimiento(p);
					vc.refrescarInfo();
					dispose();
				}
			});
		}
		return btnGuardar;
	}

	private JPanel getPnCental() {
		if (pnCental == null) {
			pnCental = new JPanel();
			pnCental.setLayout(new GridLayout(0, 2, 0, 0));
			pnCental.add(getPnCombos());
			pnCental.add(getPnDetalles());
		}
		return pnCental;
	}

	private JPanel getPnCombos() {
		if (pnCombos == null) {
			pnCombos = new JPanel();
			pnCombos.setLayout(new GridLayout(0, 1, 0, 0));
			pnCombos.add(getPnSeccion());
			pnCombos.add(getPnSistema());
			pnCombos.add(getPnTipo());
		}
		return pnCombos;
	}

	private JPanel getPnDetalles() {
		if (pnDetalles == null) {
			pnDetalles = new JPanel();
			pnDetalles.setLayout(new GridLayout(0, 1, 0, 0));
			pnDetalles.add(getPnFecha());
			pnDetalles.add(getPnHora());
			pnDetalles.add(getVerticalStrut_1());
		}
		return pnDetalles;
	}

	private JPanel getPnFecha() {
		if (pnFecha == null) {
			pnFecha = new JPanel();
			pnFecha.add(getLblFecha());
			pnFecha.add(getTxtFecha());
		}
		return pnFecha;
	}

	private JPanel getPnHora() {
		if (pnHora == null) {
			pnHora = new JPanel();
			pnHora.add(getLblHora());
			pnHora.add(getTxtHora());
		}
		return pnHora;
	}

	private Component getVerticalStrut_1() {
		if (verticalStrut_1 == null) {
			verticalStrut_1 = Box.createVerticalStrut(20);
		}
		return verticalStrut_1;
	}

	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
		}
		return lblFecha;
	}

	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##/##/####");
				txtFecha = new JFormattedTextField(mf);
				txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
				txtFecha.setColumns(10);

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtFecha;
	}

	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora:");
		}
		return lblHora;
	}

	private JTextField getTxtHora() {
		if (txtHora == null) {
			try {
				MaskFormatter mf = new MaskFormatter("##:##");
				txtHora = new JFormattedTextField(mf);
				txtHora.setHorizontalAlignment(SwingConstants.CENTER);
				txtHora.setColumns(10);

				LocalTime now = LocalTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
				txtHora.setText(now.format(formatter));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return txtHora;
	}

	private JPanel getPnSeccion() {
		if (pnSeccion == null) {
			pnSeccion = new JPanel();
			pnSeccion.add(getLblSeccion());
			pnSeccion.add(getCbSeccion());
		}
		return pnSeccion;
	}

	private JPanel getPnSistema() {
		if (pnSistema == null) {
			pnSistema = new JPanel();
			pnSistema.add(getLblSistema());
			pnSistema.add(getCbSistema());
		}
		return pnSistema;
	}

	private JPanel getPnTipo() {
		if (pnTipo == null) {
			pnTipo = new JPanel();
			pnTipo.add(getLblTipo());
			pnTipo.add(getCbTipo());
		}
		return pnTipo;
	}

	private JLabel getLblSeccion() {
		if (lblSeccion == null) {
			lblSeccion = new JLabel("Seccion:");
		}
		return lblSeccion;
	}

	private JComboBox<ProcedimientoSeccion> getCbSeccion() {
		if (cbSeccion == null) {
			cbSeccion = new JComboBox<ProcedimientoSeccion>(secciones);
			cbSeccion.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cargarSistemas();
				}
			});
		}
		return cbSeccion;
	}

	private void cargarSistemas() {

		if (cbSeccion.getSelectedItem() == null)
			return;

		String idSeccion = ((ProcedimientoSeccion) cbSeccion.getSelectedItem()).getIdSeccion();

		sistemas.removeAllElements();
		for (ProcedimientoSistema i : gp.getSistemasPorSeccion(idSeccion)) {
			sistemas.addElement(i);
		}

		cbSistema.setEnabled(true);
		cbTipo.setEnabled(false);
		btnGuardar.setEnabled(false);
	}

	private JLabel getLblSistema() {
		if (lblSistema == null) {
			lblSistema = new JLabel("Sistema:");
		}
		return lblSistema;
	}

	private JComboBox<ProcedimientoSistema> getCbSistema() {
		if (cbSistema == null) {
			cbSistema = new JComboBox<ProcedimientoSistema>(sistemas);
			cbSistema.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					cargarTipos();
				}
			});
		}
		return cbSistema;
	}

	private void cargarTipos() {

		if (cbSistema.getSelectedItem() == null)
			return;

		String idSistema = ((ProcedimientoSistema) cbSistema.getSelectedItem()).getIdSistema();

		tipos.removeAllElements();

		for (ProcedimientoTipo t : gp.getTiposPorSistema(idSistema)) {
			tipos.addElement(t);
		}

		cbTipo.setEnabled(true);
		btnGuardar.setEnabled(false);
	}

	private JLabel getLblTipo() {
		if (lblTipo == null) {
			lblTipo = new JLabel("Tipo:");
		}
		return lblTipo;
	}

	private JComboBox<ProcedimientoTipo> getCbTipo() {
		if (cbTipo == null) {
			cbTipo = new JComboBox<ProcedimientoTipo>(tipos);
			cbTipo.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnGuardar.setEnabled(true);
				}
			});
		}
		return cbTipo;
	}
}
