package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import entidad.Cursos;
import entidad.Profesor;
import model.CursosModel;
import model.ProfesorModel;
import util.Excel;
import util.Libreria;
import util.Validaciones;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Frm_Profesor extends JInternalFrame implements ActionListener, Runnable, KeyListener, MouseListener {
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTable table;
	private JButton btnLimpiar;
	@SuppressWarnings("rawtypes")
	private JComboBox cboCodigoCurso;
	private JButton btnInsertar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnExportarExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Profesor frame = new Frm_Profesor();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Frm_Profesor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 893, 498);
		getContentPane().setLayout(null);
		
		JLabel lblProfesor = new JLabel("PROFESOR");
		lblProfesor.setFont(new Font("Algerian", Font.ITALIC, 24));
		lblProfesor.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfesor.setBounds(10, 11, 857, 29);
		getContentPane().add(lblProfesor);
		
		JLabel lblCdigoProfesor = new JLabel("C\u00F3digo Profesor :");
		lblCdigoProfesor.setBounds(10, 51, 159, 29);
		lblCdigoProfesor.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblCdigoProfesor);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(10, 91, 130, 22);
		lblNombre.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setBounds(10, 126, 130, 22);
		lblApellidos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblApellidos);
		
		JLabel lblDni = new JLabel("DNI :");
		lblDni.setBounds(474, 51, 128, 29);
		lblDni.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblDni);
		
		JLabel lblCdigoCurso = new JLabel("C\u00F3digo Curso :");
		lblCdigoCurso.setBounds(474, 91, 128, 22);
		lblCdigoCurso.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblCdigoCurso);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(179, 57, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(179, 94, 231, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(this);
		txtApellidos.setBounds(179, 129, 231, 20);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setBounds(662, 57, 100, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		cboCodigoCurso = new JComboBox();
		cboCodigoCurso.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE"}));
		cboCodigoCurso.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		cboCodigoCurso.setBackground(new Color(192, 192, 192));
		cboCodigoCurso.setBounds(474, 128, 393, 20);
		getContentPane().add(cboCodigoCurso);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(this);
		btnInsertar.setBounds(27, 176, 89, 23);
		btnInsertar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnInsertar.setBackground(new Color(147, 112, 219));
		getContentPane().add(btnInsertar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(243, 176, 117, 23);
		btnActualizar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnActualizar.setBackground(new Color(147, 112, 219));
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(513, 176, 89, 23);
		btnEliminar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnEliminar.setBackground(new Color(147, 112, 219));
		getContentPane().add(btnEliminar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLimpiar.setBackground(new Color(147, 112, 219));
		btnLimpiar.setBounds(704, 176, 89, 23);
		getContentPane().add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 213, 851, 196);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Profesor", "Nombre", "Apellidos", "DNI", "Codigo Curso", "Curso"
			}
		));
		/*ARREGLOS DE TABLA*/
		//ENCABEZADO NO EDITABLE
		JTableHeader encabezado=new JTableHeader();
		encabezado.setReorderingAllowed(false);
		encabezado.setResizingAllowed(false);
		
		table.setDefaultEditor(Object.class, null);
		
		//TAMAÑO DE LAS COLUMNAS
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(10);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(10);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		
		//CENTRAR
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		
		scrollPane.setViewportView(table);
		
		JLabel lblHora = new JLabel("Hora :");
		lblHora.setBounds(10, 418, 68, 25);
		lblHora.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblHora);
		
		lblLabel = new JLabel("label");
		lblLabel.setBounds(88, 418, 114, 25);
		lblLabel.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblLabel);
		
		JLabel lblCantdadDeProfesores = new JLabel("Cantidad de Profesores :");
		lblCantdadDeProfesores.setBounds(287, 418, 198, 25);
		lblCantdadDeProfesores.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblCantdadDeProfesores);
		
		lblCanti = new JLabel("Canti");
		lblCanti.setBounds(534, 418, 46, 25);
		lblCanti.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		getContentPane().add(lblCanti);
		
		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.addActionListener(this);
		btnExportarExcel.setBounds(718, 420, 125, 23);
		btnExportarExcel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExportarExcel.setBackground(new Color(147, 112, 219));
		getContentPane().add(btnExportarExcel);
		
		listar();
		
		contarFilas();
		
		generarCodigo();
		
		agregarCodCurso();
		
		hilo=new Thread(this);
		hilo.start();
		run();
	}
	String proSelec="";
	String dniSelecc="";
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportarExcel) {
			do_btnExportarExcel_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnInsertar) {
			do_btnInsertar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(arg0);
		}
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent arg0) {
		listar();
		limpiar();
	}
	protected void do_btnInsertar_actionPerformed(ActionEvent arg0) {
		insertar();
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
		actualizar();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		eliminar();
	}
	protected void do_btnExportarExcel_actionPerformed(ActionEvent arg0) {
		if(table.getRowCount()!=0) {
		try {
			Excel.exportar(table);
		} catch (IOException e) {
			e.printStackTrace();
		}
		}else {
			mensaje("No existe tabla a exportar");
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			do_table_mouseClicked(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void do_table_mouseClicked(MouseEvent arg0) {
		int fila=table.getSelectedRow();
		String codPro=(String) table.getValueAt(fila, 0);
		String nom=(String) table.getValueAt(fila, 1);
		String ape=(String)table.getValueAt(fila, 2);
		String dni=(String)table.getValueAt(fila, 3);
		String codCur=(String)table.getValueAt(fila, 4);
		
		proSelec=codPro;
		dniSelecc=dni;
		
		txtCodigo.setText(codPro);
		txtNombre.setText(nom);
		txtApellidos.setText(ape);
		txtDNI.setText(dni);
		cboCodigoCurso.setSelectedItem(codCur);
	}
	
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtDNI) {
			do_txtDNI_keyTyped(arg0);
		}
		if (arg0.getSource() == txtApellidos) {
			do_txtApellidos_keyTyped(arg0);
		}
		if (arg0.getSource() == txtNombre) {
			do_txtNombre_keyTyped(arg0);
		}
	}
	protected void do_txtNombre_keyTyped(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		if(Libreria.soloLetras(c)=='S'||txtNombre.getText().length()>49) {
			arg0.consume();
			getToolkit();
		}
	}
	protected void do_txtApellidos_keyTyped(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		if(Libreria.soloLetras(c)=='S'||txtApellidos.getText().length()>69) {
			arg0.consume();
			getToolkit();
		}
	}
	protected void do_txtDNI_keyTyped(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		if(Libreria.soloNumeros(c)=='S'||txtDNI.getText().length()>7) {
			arg0.consume();
			getToolkit();
		}
	}
	
	
	
	private void listar() {
		ProfesorModel model=new ProfesorModel();
		List<Profesor> lista=model.listaProfesor();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for (Profesor x : lista) {
			Object [] f= {
				x.getIdProfesor(),x.getNombre(),x.getApellidos(),x.getDNI(),x.getIdCrso(),x.getCurso()	
			};
			
			dtm.addRow(f);
		}
	}
	
	private void insertar() {
		String codPro=txtCodigo.getText().trim();
		String nom=txtNombre.getText().trim();
		String ape=txtApellidos.getText().trim();
		String dni=txtDNI.getText().trim();
			
		if(!(nom.matches(Validaciones.nombre))) {
			mensaje("Nombre es de 2 a 50 caracteres");
			return;
		}
		if(!(ape.matches(Validaciones.apellidos))) {
			mensaje("Apellidos es de 2 a 70 caracteres");
			return;
		}
		String separarApe=Libreria.reemplazarEspaciosPorGuiones(ape);
		if(separarApe.indexOf("-")==-1) {
			mensaje("Los apellidos deben ser 2 a más palabras");
			return;
		}
		if(!(dni.matches(Validaciones.DNI))) {
			mensaje("DNI es de 8 digitos");
			return;
		}
		
		if(cboCodigoCurso.getSelectedIndex()==0) {
			mensaje("Seleccione Código de Curso");
			return;
		}
		if(cboCodigoCurso.getSelectedItem()=="Sin Cursos") {
			mensaje("Registre Cursos primero");
			return;
		}
		String codCur=(String) cboCodigoCurso.getSelectedItem();
		codCur=Libreria.extraerPalabraDeCadena(codCur, "(");
		
		ProfesorModel model=new ProfesorModel();
		if(model.buscaDNI(dni)!="") {
			mensaje("El DNI"+dni+" ya existe");
			return;
		}
		
		nom=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(nom)));
		ape=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(ape)));
		
		Profesor p=new Profesor();
		p.setIdProfesor(codPro);
		p.setNombre(nom);
		p.setApellidos(ape);
		p.setDNI(dni);
		p.setIdCrso(codCur);
		
		int s=model.insertarProfesor(p);
		if(s>0) {
			mensaje("Registro Exitoso");
			listar();
			limpiar();
		}else {
			mensaje("Registro Erróneo");
			return;
		}
		
		
	}
	
	private void actualizar() {
		if(proSelec!="") {
			String codPro=txtCodigo.getText().trim();
			String nom=txtNombre.getText().trim();
			String ape=txtApellidos.getText().trim();
			String dni=txtDNI.getText().trim();
						
			if(!(nom.matches(Validaciones.nombre))) {
				mensaje("Nombre es de 2 a 50 caracteres");
				return;
			}
			if(!(ape.matches(Validaciones.apellidos))) {
				mensaje("Apellidos es de 2 a 70 caracteres");
				return;
			}
			String separarApe=Libreria.reemplazarEspaciosPorGuiones(ape);
			if(separarApe.indexOf("-")==-1) {
				mensaje("Los apellidos deben ser 2 a más palabras");
				return;
			}
			if(Libreria.primeraPalabra(ape).equalsIgnoreCase(Libreria.ultimaPalabra(ape))) {
				mensaje("Los apellidos deben ser 2 a más palabras");
				return;
			}
			if(!(dni.matches(Validaciones.DNI))) {
				mensaje("DNI es de 8 digitos");
				return;
			}
			
			if(cboCodigoCurso.getSelectedIndex()==0) {
				mensaje("Seleccione Código de Curso");
				return;
			}
			if(cboCodigoCurso.getSelectedItem()=="Sin Cursos") {
				mensaje("Registre Cursos primero");
				return;
			}
			String codCur=(String) cboCodigoCurso.getSelectedItem();
			
			ProfesorModel model=new ProfesorModel();
			
			if(!(dni.equals(dniSelecc))) {
				if(model.buscaDNI(dni)!="") {
					mensaje("El DNI"+dni+ " ya existe");
					return;
				}
			}
			nom=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(nom)));
			ape=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(ape)));
			
			Profesor p=new Profesor();
			p.setIdProfesor(codPro);
			p.setNombre(nom);
			p.setApellidos(ape);
			p.setDNI(dni);
			p.setIdCrso(codCur);
			
			int con=Confirmacion("Actualización", "Confirmación de Actualización");
			if(con==0) {
			
			int s=model.actualizarProfesor(p);
			if(s>0) {
				mensaje("Actualización exitosa");
				listar();
				limpiar();
				
			}else {
				mensaje("Actualización errónea");
				return;
			}
			}
			
		}else {
			mensaje("Seleccione Profesor en la tabla");
		}
	}
	
	private void eliminar() {
		if(proSelec!="") {
			ProfesorModel model=new ProfesorModel();
			int con=Confirmacion("Eliminar", "Confirmación de eliminación");
			if(con==0) {
			int s=model.eliminarProfesor(proSelec);
			if(s>0) {
				mensaje("Eliminación exitosa");
				listar();
				limpiar();
			}else {
				mensaje("Eliminación errónea");
				return;
			}
			}
			
		}else {
			mensaje("Seleccione Profesor en la tabla");
			return;
		}
	}
	
	private int Confirmacion(String titulo,String msj) {
		int confi=JOptionPane.showConfirmDialog(null, msj,titulo,JOptionPane.YES_NO_OPTION);
		return confi;
	}
	
	private void mensaje(String x) {
		JOptionPane.showMessageDialog(null, x);
	}
	
	
	private void contarFilas() {
		int filas=table.getRowCount();
		if(filas<0) {
			lblCanti.setText("0");
		}
		else {
			lblCanti.setText(Integer.toString(filas));
		}
	}
	
	private void generarCodigo() {
		ProfesorModel model=new ProfesorModel();
		String codigo=model.ultmoCodigo();
		if(codigo!="") {
			int numero=Integer.parseInt(Libreria.ultimaPalabra(Libreria.separarLetrasYNumeros(codigo, '0')));
			if(numero>=1 && numero<=9) {
				codigo="PRO000"+(numero+1);
			}
			if(numero>9 && numero<=99) {
				codigo="PRO00"+(numero+1);
			}
			if(numero>99 && numero<=999) {
				codigo="PRO0"+(numero+1);
			}
			if(numero>999 && numero<=9999) {
				codigo="PRO"+(numero+1);
			}
			txtCodigo.setText(codigo);
		}else {
			codigo="PRO0001";
			txtCodigo.setText(codigo);
		}
		
		
	}
	
	private void limpiar() {
	contarFilas();
	generarCodigo();
	txtNombre.setText("");
	txtApellidos.setText("");
	txtDNI.setText("");
	cboCodigoCurso.setSelectedIndex(0);
	proSelec="";
	dniSelecc="";
	}
	
	@SuppressWarnings("unchecked")
	private void agregarCodCurso() {
		CursosModel model=new CursosModel();
		List<Cursos> codigo=model.listarCodCursos();
		if(!(codigo.isEmpty())) {
		for (Cursos x : codigo) {
			cboCodigoCurso.addItem(x.getIdCurso()+" ("+x.getCurso()+")");
		}
		}else {
			cboCodigoCurso.addItem("Sin Cursos");
		}
	}
	
	/*HORA EN TIEMPO REAL*/
	int hora,minutos,segundos;
	Calendar calendario;
	Thread hilo;
	private JLabel lblLabel;
	private JLabel lblCanti;
	
	private void generarHora() {
		calendario=new GregorianCalendar();
		hora=calendario.get(Calendar.HOUR_OF_DAY);
		minutos=calendario.get(Calendar.MINUTE);
		segundos=calendario.get(Calendar.SECOND);
	}
	
	
	@SuppressWarnings("static-access")
	public void run() {
		Thread current=Thread.currentThread();
		while(current==hilo) {
			generarHora();
			String hor=Integer.toString(hora),min=Integer.toString(minutos),sec=Integer.toString(segundos);
			if(hora>=0 && hora<=9) {
				hor="0"+hor;
			}
			if(minutos>=0 && minutos<=9) {
				min="0"+min;
			}
			if(segundos>=0 && segundos<=9) {
				sec="0"+sec;
			}
			lblLabel.setText(hor+":"+min+":"+sec);
			
			try {
				hilo.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
