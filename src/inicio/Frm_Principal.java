package inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import entidad.Opcion;
import gui.Frm_Alumno;
import gui.Frm_ConsultaAlumno;
import gui.Frm_ConsultaCursos;
import gui.Frm_ConsultaProfesor;
import gui.Frm_ConsultaRegistro;
import gui.Frm_Cursos;
import gui.Frm_Profesor;
import gui.Frm_Registro;
import model.UsuarioModel;
import util.Global;

@SuppressWarnings("serial")
public class Frm_Principal extends JFrame implements WindowListener, ActionListener, MouseListener {
	
	private JDesktopPane desktop = new JDesktopPane();
	
	// Paso 1: Agregar el formulario

	// Adminsitrativa
	private List<JMenuItem> listaItemMenus=new ArrayList<JMenuItem>();
	private List<JMenu> listaMenus=new ArrayList<JMenu>();
	private UsuarioModel model=new UsuarioModel();
	//PRINCIPAL
	private JMenuItem mntAlumno;
	private JMenuItem mntCursos;
	private JMenuItem mntProfesor;
	private JMenuItem mntRegistrado;
	
	//Consulta
	private JMenuItem mntConsultaAlumno;
	private JMenuItem mntConsultaCursos;
	private JMenuItem mntConsultaProfesor;
	private JMenuItem mntConsultaRegistro;
	
	//Reporte
	private JMenuItem mntReporteAlumno;
	private JMenuItem mntReporteCursos;
	private JMenuItem mntReporteProfesor;
	private JMenuItem mntReporteRegistro;
	
	// Formularios
	//PRINCIPAL
	public Frm_Alumno frmAlumno=new Frm_Alumno();
	public Frm_Cursos frmCursos=new Frm_Cursos();
	public Frm_Profesor frmProfesor=new Frm_Profesor();
	public Frm_Registro frmReistro=new Frm_Registro();
	
	//CONSULTA
	public Frm_ConsultaAlumno frmConsultaAlumno=new Frm_ConsultaAlumno();
	public Frm_ConsultaCursos frmConsultaCursos=new Frm_ConsultaCursos();
	public Frm_ConsultaProfesor frmConsultaProfesor=new Frm_ConsultaProfesor();
	public Frm_ConsultaRegistro frmConsultaRegistro=new Frm_ConsultaRegistro();
	
	
	public Frm_Principal(String cad,int x,int y) {
		super(cad);
		this.setLocation(0,0);
		this.setSize(1960,1080);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);
		
		desktop.setSize(1000,500);
		desktop.setBackground(new Color(192, 192, 192));
		
		getContentPane().add(desktop,BorderLayout.CENTER);
		
		addWindowListener(this);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnPrincipal=new JMenu("PRINCIPALES");
		mnPrincipal.setVisible(true);
		menuBar.add(mnPrincipal);
		
		JMenu mnConsulta=new JMenu("CONSULTAS");
		mnConsulta.setVisible(true);
		menuBar.add(mnConsulta);
		
		//PRINCIPAL
		mntAlumno=new JMenuItem("Alumno");
		mntAlumno.setVisible(true);
		mntAlumno.addActionListener(this);
		mnPrincipal.add(mntAlumno);
		
		mntCursos=new JMenuItem("Cursos");
		mntCursos.setVisible(true);
		mntCursos.addActionListener(this);
		mnPrincipal.add(mntCursos);
		
		mntProfesor=new JMenuItem("Profesor");
		mntProfesor.setVisible(true);
		mntProfesor.addActionListener(this);
		mnPrincipal.add(mntProfesor);
		
		mntRegistrado=new JMenuItem("Registro");
		mntRegistrado.setVisible(true);
		mntRegistrado.addActionListener(this);
		mnPrincipal.add(mntRegistrado);
		
		//CONSULTA
		mntConsultaAlumno=new JMenuItem("Consulta Alumno");
		mntConsultaAlumno.setVisible(true);
		mntConsultaAlumno.addActionListener(this);
		mnConsulta.add(mntConsultaAlumno);
		
		mntConsultaCursos=new JMenuItem("Consulta Cursos");
		mntConsultaCursos.setVisible(true);
		mntConsultaCursos.addActionListener(this);
		mnConsulta.add(mntConsultaCursos);
		
		mntConsultaProfesor=new JMenuItem("Consulta Profesor");
		mntConsultaProfesor.setVisible(true);
		mntConsultaProfesor.addActionListener(this);
		mnConsulta.add(mntConsultaProfesor);
		
		mntConsultaRegistro=new JMenuItem("Consulta Registro");
		mntConsultaRegistro.setVisible(true);
		mntConsultaRegistro.addActionListener(this);
		mnConsulta.add(mntConsultaRegistro);
		
		
		listaMenus.add(mnPrincipal);
		listaMenus.add(mnConsulta);
		
		//PRINCIPAL
		listaItemMenus.add(mntAlumno);
		listaItemMenus.add(mntCursos);
		listaItemMenus.add(mntProfesor);
		listaItemMenus.add(mntRegistrado);
		
		//CONSULTA
		listaItemMenus.add(mntConsultaAlumno);
		listaItemMenus.add(mntConsultaCursos);
		listaItemMenus.add(mntConsultaProfesor);
		listaItemMenus.add(mntConsultaRegistro);
		
		//REPORTE
		listaItemMenus.add(mntReporteAlumno);
		listaItemMenus.add(mntReporteCursos);
		listaItemMenus.add(mntReporteProfesor);
		listaItemMenus.add(mntReporteRegistro);
		
		// Paso 4: Se los formularios al contenedor
		//PRINCIPAL
		desktop.add(frmAlumno);
		desktop.add(frmCursos);
		desktop.add(frmProfesor);
		desktop.add(frmReistro);
		
		//CONSULTA
		desktop.add(frmConsultaAlumno);
		desktop.add(frmConsultaCursos);
		desktop.add(frmConsultaProfesor);
		desktop.add(frmConsultaRegistro);
		
		//label.setIcon(new ImageIcon(Frm_Principal.class.getResource(arg0)));
		//label.setBounds(0,0,1975,1088);
		//desktop.add(label);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			
			Frm_Principal jf=new Frm_Principal("Sistema de gestión de INSTITUTO", 900, 600);
			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			jf.setVisible(false);
			
			Login frm = new Login(jf);
			frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frm.setVisible(true);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	*/
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==mntAlumno) {
			centrar(frmAlumno);
			frmAlumno.setVisible(true);
		}
		if(arg0.getSource()==mntCursos) {
			centrar(frmCursos);
			frmCursos.setVisible(true);
		}
		if(arg0.getSource()==mntProfesor) {
			centrar(frmProfesor);
			frmProfesor.setVisible(true);
		}
		if(arg0.getSource()==mntRegistrado) {
			centrar(frmReistro);
			frmReistro.setVisible(true);
		}
		
		if(arg0.getSource()==mntConsultaAlumno) {
			centrar(frmConsultaAlumno);
			frmConsultaAlumno.setVisible(true);
		}
		if(arg0.getSource()==mntConsultaCursos) {
			centrar(frmConsultaCursos);
			frmConsultaCursos.setVisible(true);
		}
		if(arg0.getSource()==mntConsultaProfesor) {
			centrar(frmConsultaProfesor);
			frmConsultaProfesor.setVisible(true);
		}
		if(arg0.getSource()==mntConsultaRegistro) {
			centrar(frmConsultaRegistro);
			frmConsultaRegistro.setVisible(true);
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		int n=JOptionPane.showConfirmDialog(e.getWindow(), "¿Desea cerrar la aplicación?","Confirmación",JOptionPane.YES_NO_OPTION);
		if(n==JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
			
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/*public void muestraOpciones() {
		//MOSTRAR ITEMS
		List<opcion>
	}*/
	public void muestraOpciones() {
		List<Opcion> data=model.obtieneOpciones(Global.IdUsuario);
		for (Opcion aux : data) {
			for (JMenuItem aux2 : listaItemMenus) {
				if(aux.getNombre().equalsIgnoreCase(aux2.getText())) {
					aux2.setVisible(true);
					break;
				}
			}
		
		}
		for (JMenu aux : listaMenus) {
			verificaMenu(aux);
			
		}
	}
	
	public void verificaMenu(JMenu aux) {
		for(int i=0;i<aux.getItemCount();i++) {
			if(aux.getItem(i).isVisible()) {
				aux.setVisible(true);
				break;
			}
		}
	}
	
	public void centrar(JInternalFrame frm) {
		//DIMENSIONES DE LA PANTALLA
		Dimension pantalla=Toolkit.getDefaultToolkit().getScreenSize();
		//DIMENSIONES DEL FORMULARIO
		Dimension ventana=frm.getSize();
		
		int posX=(int) (pantalla.getWidth()-ventana.getWidth())/2;
		frm.setLocation(posX, 80);
	}
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
}
