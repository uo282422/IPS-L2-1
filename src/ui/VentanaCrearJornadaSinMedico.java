package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JCalendar;

import logic.jornada.Jornada;
import nexus.GestorJornada;

public class VentanaCrearJornadaSinMedico extends JFrame {

	/**
	 * 
	 */

	private GestorJornada gestor = new GestorJornada();
	private VentanaCrearCalendarios vcc;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnInferior;
	private JButton btAceptar;
	private JButton btCancelar;
	private JLabel lbSuperior;
	private JPanel pnCentral;
	private JPanel pnCentralSuperior;
	private JPanel pnCentralInferior;
	private JPanel pnCentralSuperiorIzq;
	private JPanel pnCentralSuperiorDer;
	private JLabel lbNombre;
	private JTextField txtNombre;
	private JLabel lbHoraInicio;
	private JFormattedTextField txtHoraInicio;
	private JLabel lbHoraFin;
	private JFormattedTextField txtHoraFin;
	private JToggleButton btLunes;
	private JToggleButton btMartes;
	private JToggleButton btMiercoles;
	private JToggleButton btJueves;
	private JToggleButton btViernes;
	private JToggleButton btSabado;
	private JToggleButton btDomingo;
	private JLabel lbDesde;
	private JCalendar desde = new JCalendar();
	private JCalendar hasta = new JCalendar();
	private JLabel lbHasta;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaCrearJornadaSinMedico frame = new VentanaCrearJornadaSinMedico();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public VentanaCrearJornadaSinMedico(VentanaCrearCalendarios vcc) {
		this.vcc = vcc;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnInferior(), BorderLayout.SOUTH);
		contentPane.add(getLbSuperior(), BorderLayout.NORTH);
		contentPane.add(getPnCentral(), BorderLayout.CENTER);
		resetCalendarios();
	}

	private JPanel getPnInferior() {
		if (pnInferior == null) {
			pnInferior = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnInferior.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnInferior.add(getBtAceptar());
			pnInferior.add(getBtCancelar());
		}
		return pnInferior;
	}

	private JButton getBtAceptar() {
		if (btAceptar == null) {
			btAceptar = new JButton("Aceptar");
			btAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkNombre() && checkDias())
						crearJornada();
				}
			});
		}
		return btAceptar;
	}

	protected boolean checkDias() {
		for (Component c : pnCentralSuperiorDer.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			if (b.isSelected())
				return true;
		}
		return false;
	}

	protected void crearJornada() {
		String nombre = txtNombre.getText();
		String dias = getDias();
		String hInicio = getHora(txtHoraInicio);
		String hFin = getHora(txtHoraFin);
		String inicio = new SimpleDateFormat(
				"dd/MM/yyyy").format(desde.getDate());
		String fin = new SimpleDateFormat("dd/MM/yyyy").format(hasta.getDate());

		gestor.guardarJornada(new Jornada(gestor.generarId(), nombre, dias,
				hInicio, hFin, inicio, fin));
		vcc.actualizarJornadas();
		reset();
	}

	private void reset() {
		txtNombre.setText("");
		txtHoraInicio.setText("  :  ");
		txtHoraFin.setText("  :  ");
		resetDias();
		resetCalendarios();
	}

	private void resetCalendarios() {
		desde.setCalendar(Calendar.getInstance());
		hasta.setCalendar(Calendar.getInstance());
		hasta.setMinSelectableDate(desde.getDate());
	}

	private void resetDias() {
		for (Component c : pnCentralSuperiorDer.getComponents()) {
			JToggleButton b = (JToggleButton) c;
			b.setSelected(false);
		}
	}

	private String getDias() {
		String dias = "";
		for (Component c : getPnCentralSuperiorDer().getComponents()) {
			JToggleButton b = (JToggleButton) c;
			if (b.isSelected())
				dias += b.getName();
		}
		return dias;
	}

	private String getHora(JFormattedTextField txt) {
		if (txt.getText().equals("  :  "))
			return "00:00";
		else
			return txt.getText();
	}

	private boolean checkNombre() {
		return !txtNombre.getText().isBlank() && !txtNombre.getText().isEmpty();
	}

	private JButton getBtCancelar() {
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					reset();
				}
			});
		}
		return btCancelar;
	}

	private JLabel getLbSuperior() {
		if (lbSuperior == null) {
			lbSuperior = new JLabel("Crear nueva jornada",
					SwingConstants.CENTER);
		}
		return lbSuperior;
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new GridLayout(2, 0, 0, 0));
			pnCentral.add(getPnCentralSuperior());
			pnCentral.add(getPnCentralInferior());
		}
		return pnCentral;
	}

	private JPanel getPnCentralSuperior() {
		if (pnCentralSuperior == null) {
			pnCentralSuperior = new JPanel();
			pnCentralSuperior.setLayout(new GridLayout(0, 2, 0, 0));
			pnCentralSuperior.add(getPnCentralSuperiorIzq());
			pnCentralSuperior.add(getPnCentralSuperiorDer());
		}
		return pnCentralSuperior;
	}

	private JPanel getPnCentralInferior() {
		if (pnCentralInferior == null) {
			pnCentralInferior = new JPanel();
			pnCentralInferior.setLayout(new GridLayout(2, 2, 0, 0));
			pnCentralInferior.add(getLbDesde());
			pnCentralInferior.add(desde);
			pnCentralInferior.add(getLbHasta());
			pnCentralInferior.add(hasta);
		}
		return pnCentralInferior;
	}

	private JPanel getPnCentralSuperiorIzq() {
		if (pnCentralSuperiorIzq == null) {
			pnCentralSuperiorIzq = new JPanel();
			pnCentralSuperiorIzq.setLayout(new GridLayout(6, 0, 0, 0));
			pnCentralSuperiorIzq.add(getLbNombre());
			pnCentralSuperiorIzq.add(getTxtNombre());
			pnCentralSuperiorIzq.add(getLbHoraInicio());
			pnCentralSuperiorIzq.add(getTxtHoraInicio());
			pnCentralSuperiorIzq.add(getLbHoraFin());
			pnCentralSuperiorIzq.add(getTxtHoraFin());
		}
		return pnCentralSuperiorIzq;
	}

	private JPanel getPnCentralSuperiorDer() {
		if (pnCentralSuperiorDer == null) {
			pnCentralSuperiorDer = new JPanel();
			pnCentralSuperiorDer.setLayout(new GridLayout(0, 1, 0, 0));
			pnCentralSuperiorDer.add(getBtLunes());
			pnCentralSuperiorDer.add(getBtMartes());
			pnCentralSuperiorDer.add(getBtMiercoles());
			pnCentralSuperiorDer.add(getBtJueves());
			pnCentralSuperiorDer.add(getBtViernes());
			pnCentralSuperiorDer.add(getBtSabado());
			pnCentralSuperiorDer.add(getBtDomingo());
		}
		return pnCentralSuperiorDer;
	}

	private JLabel getLbNombre() {
		if (lbNombre == null) {
			lbNombre = new JLabel("Nombre de la jornada:",
					SwingConstants.CENTER);
			lbNombre.setLabelFor(getTxtNombre());
		}
		return lbNombre;
	}

	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}

	private JLabel getLbHoraInicio() {
		if (lbHoraInicio == null) {
			lbHoraInicio = new JLabel("Hora de inicio:", SwingConstants.CENTER);
			lbHoraInicio.setLabelFor(getTxtHoraInicio());
		}
		return lbHoraInicio;
	}

	private JFormattedTextField getTxtHoraInicio() {
		try {
			MaskFormatter mf = new MaskFormatter("##:##");
			txtHoraInicio = new JFormattedTextField(mf);
			txtHoraInicio.setHorizontalAlignment(SwingConstants.CENTER);

			txtHoraInicio.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return txtHoraInicio;
	}

	private JLabel getLbHoraFin() {
		if (lbHoraFin == null) {
			lbHoraFin = new JLabel("Hora de fin:", SwingConstants.CENTER);
			lbHoraFin.setLabelFor(getTxtHoraFin());
		}
		return lbHoraFin;
	}

	private JTextField getTxtHoraFin() {
		try {
			MaskFormatter mf = new MaskFormatter("##:##");
			txtHoraFin = new JFormattedTextField(mf);
			txtHoraFin.setHorizontalAlignment(SwingConstants.CENTER);

			txtHoraFin.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return txtHoraFin;
	}

	private JToggleButton getBtLunes() {
		if (btLunes == null) {
			btLunes = new JToggleButton("Lunes");
		}
		return btLunes;
	}

	private JToggleButton getBtMartes() {
		if (btMartes == null) {
			btMartes = new JToggleButton("Martes");
		}
		return btMartes;
	}

	private JToggleButton getBtMiercoles() {
		if (btMiercoles == null) {
			btMiercoles = new JToggleButton("Miercoles");
		}
		return btMiercoles;
	}

	private JToggleButton getBtJueves() {
		if (btJueves == null) {
			btJueves = new JToggleButton("Jueves");
		}
		return btJueves;
	}

	private JToggleButton getBtViernes() {
		if (btViernes == null) {
			btViernes = new JToggleButton("Viernes");
		}
		return btViernes;
	}

	private JToggleButton getBtSabado() {
		if (btSabado == null) {
			btSabado = new JToggleButton("Sabado");
		}
		return btSabado;
	}

	private JToggleButton getBtDomingo() {
		if (btDomingo == null) {
			btDomingo = new JToggleButton("Domingo");
		}
		return btDomingo;
	}

	private JLabel getLbDesde() {
		if (lbDesde == null) {
			lbDesde = new JLabel("Desde:", SwingConstants.CENTER);
		}
		return lbDesde;
	}

	private JLabel getLbHasta() {
		if (lbHasta == null) {
			lbHasta = new JLabel("Hasta:", SwingConstants.CENTER);
		}
		return lbHasta;
	}

	{
		desde.getDayChooser().setAlwaysFireDayProperty(true);
		desde.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
			/**
			 * Cada vez que se selecciona un día del calendario de día de inicio
			 * se actualiza el calendario de día de fin, bloqueando todos los
			 * días previos al día seleccionado en el día de inicio de la
			 * jornada.
			 * 
			 * @param evt
			 */
			public void propertyChange(PropertyChangeEvent evt) {
				hasta.setMinSelectableDate(desde.getDate());
			}
		});
	}

}
