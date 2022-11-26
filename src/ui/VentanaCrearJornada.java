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
import java.util.Calendar;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JCalendar;

import logic.Medico;
import nexus.GestorJornada;

public class VentanaCrearJornada extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel medPn;
	private JPanel jornadaPn;
	private JLabel diaFLb;
	private JLabel diaILb;
	private JLabel medLb;
	private JLabel hInicioLb;
	private JLabel hFinalLb;
	private JLabel diasLb;
	private JComboBox<Medico> medCmb;
	private JFormattedTextField txtHInicio;
	private JFormattedTextField txtHFin;
	private JCalendar calendarI = new JCalendar();
	private JCalendar calendarF = new JCalendar();
	private JPanel semanaPn;
	private GestorJornada gestorJornada = new GestorJornada();

	/**
	 * Create the dialog.
	 */
	public VentanaCrearJornada() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 930, 559);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			medPn = new JPanel();
		}
		{
			jornadaPn = new JPanel();
		}
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		contentPanel.add(medPn);
		medPn.setLayout(new GridLayout(8, 0, 0, 0));
		{
			medLb = new JLabel("Médico:");
			medPn.add(medLb);
		}
		{
			medCmb = new JComboBox<>();
			medLb.setLabelFor(medCmb);
			medCmb.setModel(setUpComboModelMed(gestorJornada.cargarMedicos()));
			medPn.add(medCmb);
		}
		{
			hInicioLb = new JLabel("Hora de inicio: ");
			medPn.add(hInicioLb);
		}
		{
			if (txtHInicio == null) {
				try {
					MaskFormatter mf = new MaskFormatter("##:##");
					txtHInicio = new JFormattedTextField(mf);
					txtHInicio.setHorizontalAlignment(SwingConstants.CENTER);

					txtHInicio.setColumns(10);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			medPn.add(txtHInicio);
		}
		{
			hFinalLb = new JLabel("Hora de fin:");
			medPn.add(hFinalLb);
		}
		{
			if (txtHFin == null) {
				try {
					MaskFormatter mf = new MaskFormatter("##:##");
					txtHFin = new JFormattedTextField(mf);
					txtHFin.setHorizontalAlignment(SwingConstants.CENTER);

					txtHFin.setColumns(10);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			medPn.add(txtHFin);
		}
		{
			diasLb = new JLabel("Días:");
			medPn.add(diasLb);
		}
		{
			semanaPn = new JPanel();
			diasLb.setLabelFor(semanaPn);
			medPn.add(semanaPn);
			semanaPn.setLayout(new GridLayout(0, 7, 0, 0));
			{
				JToggleButton lunesTgl = new JToggleButton("L");
				semanaPn.add(lunesTgl);
			}
			{
				JToggleButton martesTgl = new JToggleButton("M");
				semanaPn.add(martesTgl);
			}
			{
				JToggleButton miercolesTgl = new JToggleButton("X");
				semanaPn.add(miercolesTgl);
			}
			{
				JToggleButton juevesTgl = new JToggleButton("J");
				semanaPn.add(juevesTgl);
			}
			{
				JToggleButton viernesTgl = new JToggleButton("V");
				semanaPn.add(viernesTgl);
			}
			{
				JToggleButton sabadoTgl = new JToggleButton("S");
				semanaPn.add(sabadoTgl);
			}
			{
				JToggleButton domingoTgl = new JToggleButton("D");
				semanaPn.add(domingoTgl);
			}
		}
		contentPanel.add(jornadaPn);
		jornadaPn.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnCalendarios = new JPanel();
			jornadaPn.add(pnCalendarios);
			pnCalendarios.setLayout(new GridLayout(4, 0, 0, 0));
			{
				diaILb = new JLabel("Día inicio jornada:");
				diaILb.setHorizontalAlignment(SwingConstants.CENTER);
				pnCalendarios.add(diaILb);
			}
			{
				setCalendars();
				calendarI.getDayChooser().setAlwaysFireDayProperty(true);
				calendarI.getDayChooser().addPropertyChangeListener("day",
						new PropertyChangeListener() {
							/**
							 * Cada vez que se selecciona un día del calendario
							 * de día de inicio se actualiza el calendario de
							 * día de fin, bloqueando todos los días previos al
							 * día seleccionado en el día de inicio de la
							 * jornada.
							 * 
							 * @param evt
							 */
							public void propertyChange(
									PropertyChangeEvent evt) {
								setCalendars();
							}
						});
				diaILb.setLabelFor(calendarI);
				pnCalendarios.add(calendarI);
			}
			{
				diaFLb = new JLabel("Día final jornada:");
				diaFLb.setHorizontalAlignment(SwingConstants.CENTER);
				pnCalendarios.add(diaFLb);
			}
			{
				diaFLb.setLabelFor(calendarF);
				pnCalendarios.add(calendarF);
			}
		}
		{
			JPanel btnPn = new JPanel();
			btnPn.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(btnPn, BorderLayout.SOUTH);
			{
				JButton confirmarBtn = new JButton("Confirmar");
				confirmarBtn.addActionListener(new ActionListener() {
					/**
					 * Se ha de comprobar que la jornada se puede crear sin
					 * complicaciones. Una vez comprobado se realiza una
					 * pregunta de confirmación y en caso de aceptarse se crea
					 * dicha jornada.
					 * 
					 * @param e
					 */
					public void actionPerformed(ActionEvent e) {
						if (comprobarCampos()) {
							if (JOptionPane.showConfirmDialog(null,
									"¿Seguro que quiere crear la jornada?") == JOptionPane.YES_OPTION) {
								guardarDatos();
								JOptionPane.showMessageDialog(null,
										"Se ha creado la jornada");
								reset();
							} else {
								JOptionPane.showMessageDialog(null,
										"La jornada no se ha creado");
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Los datos seleccionados no son viables");
						}
					}
				});
				confirmarBtn.setActionCommand("OK");
				btnPn.add(confirmarBtn);
				getRootPane().setDefaultButton(confirmarBtn);
			}
			{
				JButton cancelarBtn = new JButton("Atrás");
				cancelarBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (JOptionPane.showConfirmDialog(null,
								"Se cerrará la ventana, ¿seguro que desea cancelar?") == JOptionPane.YES_OPTION)
							dispose();
					}
				});
				cancelarBtn.setActionCommand("Cancel");
				btnPn.add(cancelarBtn);
			}
		}
	}

	/**
	 * Genera el ComboBoxModel adecuado para mostrar en la medCombo.
	 * 
	 * @param meds conteniendo la lista de médicos de la que se busca sacar un
	 *             ComboBoxModel.
	 * @return ComboBoxModel<Medico> con cada médico.
	 */
	private ComboBoxModel<Medico> setUpComboModelMed(List<Medico> meds) {
		Medico[] array = new Medico[meds.size()];
		for (int i = 0; i < array.length; i++) {
			array[i] = meds.get(i);
		}
		return new DefaultComboBoxModel<Medico>(array);
	}

	/**
	 * Método encargado de dejar todos los componentes de la ventana en su
	 * selección por defecto.
	 */
	private void reset() {
		medCmb.setSelectedIndex(0);
		txtHInicio.setText("");
		txtHFin.setText("");
		for (Component c : semanaPn.getComponents()) {
			JToggleButton jtb = (JToggleButton) c;
			jtb.setSelected(false);
		}
		calendarI.setCalendar(Calendar.getInstance());
		calendarF.setCalendar(Calendar.getInstance());
		setCalendars();
	}

	/**
	 * Se encarga de ajustar de manera adecuada las fechas de los calendarios.
	 */
	private void setCalendars() {
		calendarF.setMinSelectableDate(calendarI.getDate());
	}

	/**
	 * Revisa que los campos están rellenados de manera adecuada.
	 * 
	 * @return
	 */
	private boolean comprobarCampos() {
		return compruebaDias();
	}

	/**
	 * Comprueba que el día de inicio es distinto al día de fin, y que no son
	 * nulas
	 * 
	 * @return true si se cumple esta condición
	 */
	private boolean compruebaDias() {
		return calendarI.getDate() != null && calendarF.getDate() != null
				&& calendarI.getDate().before(calendarF.getDate());
	}

	/**
	 * Envía a la clase gestorJornada los datos seleccionados en la ventana
	 */
	private void guardarDatos() {
		gestorJornada.parse(medCmb.getSelectedItem().toString(),
				txtHInicio.getText(), txtHFin.getText(), calendarI.getDate(),
				calendarF.getDate(), getDays());
		gestorJornada.guardarJornada();
	}

	/**
	 * Método que elabora un String con los días de la togglebuttos de los dias
	 * de la semana activos
	 */
	private String getDays() {
		String semana = "";
		for (Component c : semanaPn.getComponents()) {
			JToggleButton day = (JToggleButton) c;
			if (day.isSelected())
				semana += day.getText();
		}
		return semana;
	}

	void _setCalendarsValues(JCalendar inicio, JCalendar fin) {
		calendarI.setDate(inicio.getDate());
		calendarF.setDate(fin.getDate());
	}
}