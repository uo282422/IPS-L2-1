package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import logic.Seguimiento;
import logic.cita.Cita;
import logic.diagnostico.Diagnostico;
import logic.diagnostico.DiagnosticoCapitulo;
import logic.diagnostico.DiagnosticoGrupo;
import logic.diagnostico.DiagnosticoSubgrupo;
import logic.diagnostico.DiagnosticoTabla;
import nexus.GestorDiagnosticos;
import nexus.GestorSalas;
import nexus.GestorSeguimientos;
import util.DataBase;

public class VentanaDiagnostico extends JFrame {

	private static final long serialVersionUID = 1L;

	private Cita cita;
	private VentanaCita vc;
	private GestorDiagnosticos gd;

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JButton btnGuardar;
	private JPanel pnCentral;
	private JPanel pnCombos;
	private JPanel pnTabla;
	private JPanel pnGrupo;
	private JPanel pnSubgrupo;
	private JPanel pnCapitulo;
	private JLabel lblTabla;
	private JComboBox<DiagnosticoTabla> cbTabla;
	private JLabel lblGrupo;
	private JComboBox<DiagnosticoGrupo> cbGrupo;
	private JLabel lblSubgrupo;
	private JComboBox<DiagnosticoSubgrupo> cbSubgrupo;
	private JLabel lblCapitulo;
	private JComboBox<DiagnosticoCapitulo> cbCapitulo;
	private JPanel pnDetalles;
	private JPanel pnFecha;
	private JPanel pnHora;
	private JPanel pnSeguimientoCheck;
	private JLabel lblFecha;
	private JTextField txtFecha;
	private JLabel lblHora;
	private JTextField txtHora;
	private JLabel lblSeguimiento;
	private JCheckBox chckbxSeguimiento;
	private JTextArea taSeguimiento;
	private DataBase bd;
	private GestorSeguimientos gS;
	private DefaultComboBoxModel<DiagnosticoTabla> tablas = new DefaultComboBoxModel<DiagnosticoTabla>();
	private DefaultComboBoxModel<DiagnosticoGrupo> grupos = new DefaultComboBoxModel<DiagnosticoGrupo>();
	private DefaultComboBoxModel<DiagnosticoSubgrupo> subgrupos = new DefaultComboBoxModel<DiagnosticoSubgrupo>();
	private DefaultComboBoxModel<DiagnosticoCapitulo> capitulos = new DefaultComboBoxModel<DiagnosticoCapitulo>();

	/**
	 * Create the frame.
	 */
	public VentanaDiagnostico(Cita c, VentanaCita vc) {
		this.bd=new DataBase();
		this.cita = c;
		this.vc = vc;
		this.gd = new GestorDiagnosticos();
		this.gS=new GestorSeguimientos();
		for (DiagnosticoTabla t : gd.getTablas()) {
			tablas.addElement(t);
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1325, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getBtnGuardar(), BorderLayout.SOUTH);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Creacion de diagnostico");
			lblTitulo.setFont(new Font("Dialog", Font.BOLD, 34));
		}
		return lblTitulo;
	}

	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("Guardar");
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Diagnostico d = new Diagnostico(String.valueOf(cita.getIdCita()),
							((DiagnosticoCapitulo) cbCapitulo.getSelectedItem()).getIdCapitulo(), txtFecha.getText(),
							txtHora.getText(), chckbxSeguimiento.isSelected());
					d.setDescripcion(((DiagnosticoCapitulo) cbCapitulo.getSelectedItem()).getNombre());
					
					
					if(chckbxSeguimiento.isSelected()) {
						
						gS.insertarNuevoSeguimiento(d, "ABIERTO", getTaSeguimiento().getText());
					}else {
						gS.insertarNuevoSeguimiento(d,"SIN SEGUIMIENTO", getTaSeguimiento().getText());
					}
					
					cita.addDiagnostico(d);
					vc.refrescarInfo();
					dispose();
				}
			});
			btnGuardar.setFont(new Font("Dialog", Font.BOLD, 18));
		}
		return btnGuardar;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentral.add(getPnCombos());
			pnCentral.add(getPnDetalles());
		}
		return pnCentral;
	}

	private JPanel getPnCombos() {
		if (pnCombos == null) {
			pnCombos = new JPanel();
			pnCombos.setLayout(new GridLayout(0, 1, 0, 0));
			pnCombos.add(getPnTabla());
			pnCombos.add(getPnGrupo());
			pnCombos.add(getPnSubgrupo());
			pnCombos.add(getPnCapitulo());
		}
		return pnCombos;
	}

	private JPanel getPnTabla() {
		if (pnTabla == null) {
			pnTabla = new JPanel();
			pnTabla.add(getLblTabla());
			pnTabla.add(getCbTabla());
		}
		return pnTabla;
	}

	private JPanel getPnGrupo() {
		if (pnGrupo == null) {
			pnGrupo = new JPanel();
			pnGrupo.add(getLblGrupo());
			pnGrupo.add(getCbGrupo());
		}
		return pnGrupo;
	}

	private JPanel getPnSubgrupo() {
		if (pnSubgrupo == null) {
			pnSubgrupo = new JPanel();
			pnSubgrupo.add(getLblSubgrupo());
			pnSubgrupo.add(getCbSubgrupo());
		}
		return pnSubgrupo;
	}

	private JPanel getPnCapitulo() {
		if (pnCapitulo == null) {
			pnCapitulo = new JPanel();
			pnCapitulo.add(getLblCapitulo());
			pnCapitulo.add(getCbCapitulo());
		}
		return pnCapitulo;
	}

	private JLabel getLblTabla() {
		if (lblTabla == null) {
			lblTabla = new JLabel("Tabla:");
		}
		return lblTabla;
	}

	private JComboBox<DiagnosticoTabla> getCbTabla() {
		if (cbTabla == null) {
			cbTabla = new JComboBox<DiagnosticoTabla>(tablas);
			cbTabla.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarGrupos();
				}
			});
		}
		return cbTabla;
	}

	private void cargarGrupos() {

		if (cbTabla.getSelectedItem() == null)
			return;

		String idTabla = ((DiagnosticoTabla) cbTabla.getSelectedItem()).getIdTabla();

		grupos.removeAllElements();
		for (DiagnosticoGrupo g : gd.getGruposPorTabla(idTabla)) {
			grupos.addElement(g);
		}

		cbGrupo.setEnabled(true);
		cbSubgrupo.setEnabled(false);
		cbCapitulo.setEnabled(false);
		btnGuardar.setEnabled(false);
	}

	private JLabel getLblGrupo() {
		if (lblGrupo == null) {
			lblGrupo = new JLabel("Grupo:");
		}
		return lblGrupo;
	}

	private JComboBox<DiagnosticoGrupo> getCbGrupo() {
		if (cbGrupo == null) {
			cbGrupo = new JComboBox<DiagnosticoGrupo>(grupos);
			cbGrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarSubgrupos();
				}
			});
			cbGrupo.setEnabled(false);
		}
		return cbGrupo;
	}

	private void cargarSubgrupos() {

		if (cbGrupo.getSelectedItem() == null)
			return;

		String idGrupo = ((DiagnosticoGrupo) cbGrupo.getSelectedItem()).getIdGrupo();

		subgrupos.removeAllElements();

		List<DiagnosticoSubgrupo> ds = gd.getSubgruposPorGrupo(idGrupo);

		if (ds.isEmpty()) {
			cargarCapitulos(idGrupo);
		} else {
			for (DiagnosticoSubgrupo s : ds) {
				subgrupos.addElement(s);
			}

			cbSubgrupo.setEnabled(true);
			cbCapitulo.setEnabled(false);
			btnGuardar.setEnabled(false);
		}
	}

	private JLabel getLblSubgrupo() {
		if (lblSubgrupo == null) {
			lblSubgrupo = new JLabel("Subgrupo:");
		}
		return lblSubgrupo;
	}

	private JComboBox<DiagnosticoSubgrupo> getCbSubgrupo() {
		if (cbSubgrupo == null) {
			cbSubgrupo = new JComboBox<DiagnosticoSubgrupo>(subgrupos);
			cbSubgrupo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cargarCapitulos(((DiagnosticoSubgrupo) cbSubgrupo.getSelectedItem()).getIdSubgrupo());
				}
			});
			cbSubgrupo.setEnabled(false);
		}
		return cbSubgrupo;
	}

	private void cargarCapitulos(String idSubgrupo) {

		if (cbSubgrupo.getSelectedItem() == null && cbGrupo.getSelectedItem() == null)
			return;

		capitulos.removeAllElements();
		for (DiagnosticoCapitulo c : gd.getCapitulosPorSubgrupo(idSubgrupo)) {
			capitulos.addElement(c);
		}

		cbCapitulo.setEnabled(true);
		btnGuardar.setEnabled(false);
	}

	private JLabel getLblCapitulo() {
		if (lblCapitulo == null) {
			lblCapitulo = new JLabel("Capitulo:");
		}
		return lblCapitulo;
	}

	private JComboBox<DiagnosticoCapitulo> getCbCapitulo() {
		if (cbCapitulo == null) {
			cbCapitulo = new JComboBox<DiagnosticoCapitulo>(capitulos);
			cbCapitulo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnGuardar.setEnabled(true);
				}
			});
			cbCapitulo.setEnabled(false);
		}
		return cbCapitulo;
	}

	private JPanel getPnDetalles() {
		if (pnDetalles == null) {
			pnDetalles = new JPanel();
			pnDetalles.setLayout(new GridLayout(0, 1, 0, 0));
			pnDetalles.add(getPnFecha());
			pnDetalles.add(getPnHora());
			pnDetalles.add(getPnSeguimientoCheck());
			pnDetalles.add(getTaSeguimiento());
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

	private JPanel getPnSeguimientoCheck() {
		if (pnSeguimientoCheck == null) {
			pnSeguimientoCheck = new JPanel();
			pnSeguimientoCheck.setLayout(new BorderLayout(0, 0));
			pnSeguimientoCheck.add(getLblSeguimiento(), BorderLayout.NORTH);
			pnSeguimientoCheck.add(getChckbxSeguimiento(), BorderLayout.SOUTH);
		}
		return pnSeguimientoCheck;
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

	private JLabel getLblSeguimiento() {
		if (lblSeguimiento == null) {
			lblSeguimiento = new JLabel("Info Seguimiento:");
			lblSeguimiento.setHorizontalAlignment(SwingConstants.CENTER);
			lblSeguimiento.setFont(new Font("Dialog", Font.BOLD, 16));
		}
		return lblSeguimiento;
	}

	public JCheckBox getChckbxSeguimiento() {
		if (chckbxSeguimiento == null) {
			chckbxSeguimiento = new JCheckBox("Realizar seguimiento");
			chckbxSeguimiento.setFont(new Font("Dialog", Font.BOLD, 14));
		}
		return chckbxSeguimiento;
	}

	public JTextArea getTaSeguimiento() {
		if (taSeguimiento == null) {
			taSeguimiento = new JTextArea();
		}
		return taSeguimiento;
	}
}
