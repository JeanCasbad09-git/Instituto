package inicio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidad.Usuario;
import model.UsuarioModel;
import util.Global;

@SuppressWarnings("serial")
public class Login extends JDialog implements ActionListener{

	public JTextField txtLogin;
	public JPasswordField txtPassword;
	private JLabel lblLogin;
	public JLabel lblPassword;
	public JButton btnEnviar;
	public JButton btnLimpiar;
	private UsuarioModel model=new UsuarioModel();
	public Frm_Principal frm;

	/*public static void main(String[] args) {
		try {
			Login dialog = new Login();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	
	public Login(Frm_Principal frm) {
		/*setTitle("LOGIN");
		setBounds(100, 100, 393, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);*/
		
		this.frm = frm;
		this.setSize(340, 180);
		this.setLocationRelativeTo(null);
	    this.setTitle("Sistema de gestión de Intituto");
	    getContentPane().setBackground(new Color(224, 255, 255));
		getContentPane().setLayout(null);
		
		lblLogin = new JLabel("Login :");
		lblLogin.setForeground(new Color(72, 61, 139));
		lblLogin.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblLogin.setBounds(24, 11, 88, 28);
		getContentPane().add(lblLogin);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(72, 61, 139));
		lblPassword.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblPassword.setBounds(24, 50, 88, 25);
		getContentPane().add(lblPassword);
		
		txtLogin = new JTextField("Luffy");
		txtLogin.setBounds(122, 17, 142, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JPasswordField("Mugiwara");
		txtPassword.setBounds(122, 50, 142, 20);
		getContentPane().add(txtPassword);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBackground(new Color(144, 238, 144));
		btnEnviar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 19));
		btnEnviar.setBounds(34, 86, 89, 34);
		btnEnviar.addActionListener( this);
		getContentPane().add(btnEnviar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(144, 238, 144));
		btnLimpiar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 19));
		btnLimpiar.setBounds(180, 86, 106, 34);
		btnLimpiar.addActionListener( this);
		getContentPane().add(btnLimpiar);
		
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnEnviar||e.getSource()==txtLogin||e.getSource()==txtPassword) {
			String login=txtLogin.getText().trim();
			String contra=new String(txtPassword.getPassword());
			Usuario bean=model.valida(login, contra);
			if(bean!=null) {
				Global.IdUsuario=bean.getIdUsuario();
				frm.setVisible(true);
				this.setVisible(false);
				frm.muestraOpciones();
			}else {
				JOptionPane.showMessageDialog(this, "Usuario no válido");
			}
			
			
		}
		if(e.getSource()==btnLimpiar) {
			txtLogin.setText("");
			txtPassword.setText("");
			txtLogin.requestFocus();
		}
	}
	
	public void windowDeactivated(WindowEvent e) {}
}
