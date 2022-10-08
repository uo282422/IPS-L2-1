package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import nexus.GestorPacientes;

public class DialogoInfoContacto extends JDialog {

	private final JPanel panelDialogoPrincipal = new JPanel();


	private GestorPacientes gP;
	private JTextArea textAreaPrincipal;
	private int idPaciente;
	/**
	 * Create the dialog.
	 */
	public DialogoInfoContacto(GestorPacientes gestorP, int id) {
		idPaciente=id;
		gP=gestorP;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panelNorte = new JPanel();
			getContentPane().add(panelNorte, BorderLayout.NORTH);
			{
				JLabel lbInfoContacto = new JLabel("Información de contacto:");
				lbInfoContacto.setFont(new Font("Tahoma", Font.PLAIN, 14));
				panelNorte.add(lbInfoContacto);
			}
			{
				JLabel lbIdPaciente = new JLabel(String.valueOf(idPaciente));
				panelNorte.add(lbIdPaciente);
			}
		}
		panelDialogoPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelDialogoPrincipal, BorderLayout.CENTER);
		panelDialogoPrincipal.setLayout(new BorderLayout(0, 0));
		{
			textAreaPrincipal = new JTextArea();
			textAreaPrincipal.setEditable(true);
			textAreaPrincipal.setText(gP.getContactoPaciente(idPaciente));
			panelDialogoPrincipal.add(textAreaPrincipal, BorderLayout.CENTER);
		}
		{
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
			{
				JButton btGuardar = new JButton("Guardar");
				btGuardar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						guardar();
					}

					private void guardar() {
						textAreaPrincipal.setEditable(false);
						String str=textAreaPrincipal.getText();
						
						gP.actualizarContactoPaciente(idPaciente,str);
						textAreaPrincipal.setText(gP.getContactoPaciente(idPaciente));
					}
				});
				panelBotones.add(btGuardar);
			}
			{
				JButton btCancelar = new JButton("Cancelar");
				btCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btCancelar.setActionCommand("Cancel");
				panelBotones.add(btCancelar);
			}
		}
		
	}

	

}
