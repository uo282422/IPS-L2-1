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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import logic.Paciente;
import nexus.GestorCitas;

public class DialogoInfoContacto extends JDialog {

	private final JPanel panelDialogoPrincipal = new JPanel();

//	gP.getContactoPaciente(idPaciente)
	
	
	
//	gP.actualizarContactoPaciente(idPaciente,str);
//	textAreaPrincipal.setText(gP.getContactoPaciente(idPaciente));
	private GestorCitas gC;
	private JTextField tfCorreo;
	private JTextField tfTlf;
	private JTextPane tpOtros;
	/**
	 * Create the dialog.
	 */
	public DialogoInfoContacto(GestorCitas gC, int id) {
		this.gC=gC;
		setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
				JLabel lbIdPaciente = new JLabel(String.valueOf(id));
				panelNorte.add(lbIdPaciente);
			}
		}
		panelDialogoPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelDialogoPrincipal, BorderLayout.CENTER);
		panelDialogoPrincipal.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelNorte = new JPanel();
			FlowLayout fl_panelNorte = (FlowLayout) panelNorte.getLayout();
			fl_panelNorte.setAlignment(FlowLayout.LEFT);
			panelDialogoPrincipal.add(panelNorte, BorderLayout.NORTH);
			{
				JPanel panelTelefono = new JPanel();
				panelNorte.add(panelTelefono);
				{
					JLabel lblTelfono = new JLabel("Teléfono");
					panelTelefono.add(lblTelfono);
				}
				{
					tfTlf = new JTextField();
					tfTlf.setColumns(10);
					tfTlf.setText(gC.getTlfProv()+"");
					panelTelefono.add(tfTlf);
				}
			}
			{
				JPanel panelCorreo = new JPanel();
				panelNorte.add(panelCorreo);
				{
					JLabel lblCorreo = new JLabel("Correo");
					
					panelCorreo.add(lblCorreo);
				}
				{
					tfCorreo = new JTextField();
					tfCorreo.setColumns(10);
					tfCorreo.setText(gC.getCorreoProv());
					panelCorreo.add(tfCorreo);
				}
			}
		}
		{
			JPanel panelOeste = new JPanel();
			panelDialogoPrincipal.add(panelOeste, BorderLayout.WEST);
			panelOeste.setLayout(new BorderLayout(0, 0));
			{
				JLabel lbOtros = new JLabel("Otra información de contacto:");
				panelOeste.add(lbOtros, BorderLayout.NORTH);
			}
			{
				JTextPane tpOtros = new JTextPane();
				this.tpOtros=tpOtros;
				tpOtros.setText(gC.getOtrosProv());
				panelOeste.add(tpOtros);
			}
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
						
						gC.añadirInfoContactoProv(Integer.parseInt(tfTlf.getText()),tfCorreo.getText(),tpOtros.getText());
						dispose();
//						gP.actualizarTelefonoPaciente(idPaciente,Integer.parseInt(tfTlf.getText()));
//						gP.actualizarCorreoPaciente(idPaciente,tfCorreo.getText());
//						gP.actualizarOtrosContactosPaciente(id, tpOtros.getText());

						
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
