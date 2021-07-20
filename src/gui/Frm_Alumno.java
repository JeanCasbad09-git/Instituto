package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
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

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import entidad.Alumno;
import model.AlumnoModel;
import util.Excel;
import util.Libreria;
import util.Validaciones;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frm_Alumno extends JInternalFrame implements ActionListener, MouseListener, KeyListener, Runnable	{
	private JLabel lblAlumno;
	private JLabel lblCdigoAlumno;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtDNI;
	private JTable table;
	private JDateChooser txtFecNac;
	private JDateChooser txtFecReg;
	private JButton btnInsertar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JLabel lblLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Alumno frame = new Frm_Alumno();
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
	public Frm_Alumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setClosable(true);
		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 723, 561);
		getContentPane().setLayout(null);
		
		lblAlumno = new JLabel("ALUMNO");
		lblAlumno.setFont(new Font("Algerian", Font.ITALIC, 24));
		lblAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumno.setBounds(10, 11, 686, 24);
		getContentPane().add(lblAlumno);
		
		lblCdigoAlumno = new JLabel("C\u00F3digo Alumno :");
		lblCdigoAlumno.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCdigoAlumno.setBounds(10, 46, 138, 24);
		getContentPane().add(lblCdigoAlumno);
		
		JLabel lblNombres = new JLabel("Nombre(s) :");
		lblNombres.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblNombres.setBounds(10, 83, 138, 24);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblApellidos.setBounds(10, 123, 138, 24);
		getContentPane().add(lblApellidos);
		
		JLabel lblDni = new JLabel("DNI :");
		lblDni.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblDni.setBounds(393, 46, 164, 24);
		getContentPane().add(lblDni);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento :");
		lblFechaNacimiento.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblFechaNacimiento.setBounds(393, 83, 164, 24);
		getContentPane().add(lblFechaNacimiento);
		
		JLabel lblFechaRegistro = new JLabel("Fecha Registro :");
		lblFechaRegistro.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblFechaRegistro.setBounds(393, 123, 164, 24);
		getContentPane().add(lblFechaRegistro);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(171, 50, 86, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.addKeyListener(this);
		txtNombre.setBounds(171, 87, 191, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.addKeyListener(this);
		txtApellidos.setBounds(171, 127, 191, 20);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtDNI = new JTextField();
		txtDNI.addKeyListener(this);
		txtDNI.setBounds(569, 46, 116, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);
		
		txtFecNac = new JDateChooser();
		txtFecNac.setDateFormatString("yyyy-MMM-dd");
		txtFecNac.setBounds(569, 87, 116, 20);
		getContentPane().add(txtFecNac);
		
		txtFecReg = new JDateChooser();
		txtFecReg.getCalendarButton().setEnabled(false);
		txtFecReg.getCalendarButton().addActionListener(this);
		txtFecReg.setDateFormatString("yyyy-MMM-dd");
		txtFecReg.setBounds(569, 127, 116, 20);
		getContentPane().add(txtFecReg);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(this);
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLimpiar.setBackground(new Color(147, 112, 219));
		btnLimpiar.setBounds(581, 175, 89, 23);
		getContentPane().add(btnLimpiar);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(this);
		btnInsertar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnInsertar.setBackground(new Color(147, 112, 219));
		btnInsertar.setBounds(21, 175, 89, 23);
		getContentPane().add(btnInsertar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnActualizar.setBackground(new Color(147, 112, 219));
		btnActualizar.setBounds(212, 175, 108, 23);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnEliminar.setBackground(new Color(147, 112, 219));
		btnEliminar.setBounds(393, 175, 89, 23);
		getContentPane().add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 223, 686, 227);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo Alumno", "Nombre(s)", "Apellidos", "DNI", "Fecha Nacimiento", "Fecha Registro"
			}
		));
		scrollPane.setViewportView(table);
		
		/*MEJORAS DE LA TABLA*/
		//PARA QUE LOS ENCABEZADOS NO SEAN EDITABLES
		JTableHeader encabezado=new JTableHeader();
		encabezado.setReorderingAllowed(false);
		encabezado.setResizingAllowed(false);
		
		//PARA QUE LOS CAMPOS NO SEAN EDITABLES
		table.setDefaultEditor(Object.class, null);
		
		//MODIFICAR EL TAMAÑO DE CADA COLUMNA
		table.getColumnModel().getColumn(0).setPreferredWidth(7);
		table.getColumnModel().getColumn(1).setPreferredWidth(35);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(3);
		table.getColumnModel().getColumn(4).setPreferredWidth(16);
		table.getColumnModel().getColumn(5).setPreferredWidth(16);
		
		//CENTRAR EL TEXTO DE LOS CAMPOS
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		
		JLabel lblHora = new JLabel("Hora :");
		lblHora.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblHora.setBounds(21, 471, 62, 24);
		getContentPane().add(lblHora);
		
		lblLabel = new JLabel("label");
		lblLabel.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblLabel.setBounds(110, 471, 86, 24);
		getContentPane().add(lblLabel);
		
		JLabel lblCantidadDeAlumnos = new JLabel("Cantidad de Alumnos :");
		lblCantidadDeAlumnos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidadDeAlumnos.setBounds(239, 471, 186, 24);
		getContentPane().add(lblCantidadDeAlumnos);
		
		lblCantidad = new JLabel("label 2");
		lblCantidad.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidad.setBounds(435, 471, 69, 24);
		getContentPane().add(lblCantidad);
		
		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.addActionListener(this);
		btnExportarExcel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExportarExcel.setBackground(new Color(147, 112, 219));
		btnExportarExcel.setBounds(532, 474, 138, 23);
		getContentPane().add(btnExportarExcel);
		
		/*PARA QUE EL TXTFECNAC Y TXTFECREG NO SEA EDITABLE*/
		JTextFieldDateEditor editor0=(JTextFieldDateEditor) txtFecNac.getDateEditor();
		editor0.setEditable(false);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) txtFecReg.getDateEditor();
		editor.setEditable(false);
		
		fecReg();//ESTABLECE UN VALOR POR EFECTO AL TXTFECREG
		listar();
		
		/*INICIALIZAMOS EL RELOJ*/
		hilo=new Thread(this);
		hilo.start();
		run();
	}
	/*Variable Global al seleccionar alguna fila*/
	String seleccionado="";
	/*GENERACIÓN DE FECHA ACTUAL*/
	Date date=new Date();
	LocalDate fecZonHor=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int anno=fecZonHor.getYear();
	int mes=fecZonHor.getMonthValue();
	int dia=fecZonHor.getDayOfMonth();
	private JLabel lblCantidad;
	
	
	//acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExportarExcel) {
			do_btnExportarExcel_actionPerformed(e);
		}
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnInsertar) {
			do_btnInsertar_actionPerformed(e);
		}
	}
	protected void do_btnInsertar_actionPerformed(ActionEvent e) {
		insertar();
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		actualizar();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		eliminar();
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		limpiar();
		generarCodigo();
	}
	
	protected void do_btnExportarExcel_actionPerformed(ActionEvent e) {
		if(table.getRowCount()!=0) {
			try {
			Excel.exportar(table);
			}catch(IOException a) {
				a.printStackTrace();
			}
		}else {
			mensaje("No existe tabla para exportar");
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
	String dniSeleccionado="";
	//ESTABLECER FUNCIÓN AL HACER CLICK EN ALGUNA FILA DE LA TABLA
	protected void do_table_mouseClicked(MouseEvent arg0) {
		int fila=table.getSelectedRow();
		seleccionado=(String)table.getValueAt(fila, 0);
		String nom=(String)table.getValueAt(fila, 1);
		String ape=(String)table.getValueAt(fila, 2);
		String dni=(String)table.getValueAt(fila, 3);
		dniSeleccionado=(String)table.getValueAt(fila, 3);
		String fecNac=(String)table.getValueAt(fila, 4);
		String fecReg=(String)table.getValueAt(fila, 5);
		
		txtCodigo.setText(seleccionado);
		txtNombre.setText(nom);
		txtApellidos.setText(ape);
		txtDNI.setText(dni);
		
		Date fechaParseada = null;
		Date fechaParseada1 = null;
		try {
			fechaParseada = new SimpleDateFormat("yyyy-MM-dd").parse(fecNac);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			fechaParseada1=new SimpleDateFormat("yyyy-MM-dd").parse(fecReg);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		txtFecNac.setDate(fechaParseada);
		txtFecReg.setDate(fechaParseada1);
	}
	
	
	private void listar() {
		
		AlumnoModel model=new AlumnoModel();		
		List<Alumno> lista=model.listarAlumnos();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for (Alumno x : lista) {
			Object [] f= {
				x.getIdAlumno(),x.getNombre(),x.getApellidos(),x.getDNI(),x.getFecNac(),x.getFecReg()	
			};
			
			dtm.addRow(f);
		}
		generarCodigo();
		cantiadFilas();
		
	}
	
	@SuppressWarnings("static-access")
	private void insertar() {
		String codigo=txtCodigo.getText().trim();
		String nom=txtNombre.getText().trim();
		String ape=txtApellidos.getText().trim();
		String dni=txtDNI.getText().trim();
		try {
		Date fecNac= txtFecNac.getDate();
		
		//GENERACION DE HORA
		Calendar calendar=Calendar.getInstance();
		int hora=calendar.get(calendar.HOUR_OF_DAY);
		int minutos=calendar.get(calendar.MINUTE);
		int segundos=calendar.get(calendar.SECOND);
		/*--------------------------------*/
		/*VALIDACIONES*/
		if(!(codigo.matches("ALU[0-9][0-9][0-9][0-9]"))) {
			mensaje("El formato de código es : ALU[0-9][0-9][0-9][0-9]");
			return;
		}
		if(!(nom.matches(Validaciones.nombre))) {
			mensaje("El nombre es de 2 a 50 caracteres");
			return;
		}
		
		if(!(ape.matches(Validaciones.apellidos))) {
			mensaje("Los apellidos son de 2 a 70 caracteres");
			return;
		}
		String separarApe=Libreria.reemplazarEspaciosPorGuiones(ape);
		if(separarApe.indexOf("-")==-1) {
			mensaje("Los apellidos deben ser 2 a más palabras");
			return;
		}
		if(!(dni.matches(Validaciones.DNI))) {
			mensaje("El DNI es de 8 dígitos");
			return;
		}
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		
		//MODIFICAMOS LOS STRING PARA QUE SE VEA ORDENADO
		nom=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(nom)));
		ape=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(ape)));
				
		
		String anho=Libreria.primeraPalabra(Libreria.reemplazarSlashPorEspacios(sdf.format(fecNac)));
		if((anno-Integer.parseInt(anho))<=16) {
			mensaje("El alumno debe ser mayor a 16 años\nIngrese un año menor desde :"+(anno-16));
			return;
		}
		/*--------------------------------------------------------------------------------------------*/
		AlumnoModel model=new AlumnoModel();
		if(model.buscarCodigoAlumno(codigo).isEmpty()) {
			if(model.buscarDNIAlumno(dni).isEmpty()) {
		Alumno alu=new Alumno();
		alu.setIdAlumno(codigo);
		alu.setNombre(nom);
		alu.setApellidos(ape);
		alu.setDNI(dni);
		alu.setFecNac(sdf.format(fecNac));
		alu.setFecReg(anno+"-"+mes+"-"+dia+" "+hora+":"+minutos+":"+segundos);
		
		int s=model.insertarAlumno(alu);
		if(s>0) {
			mensaje("Registro exitoso");
			listar();
			limpiar();
			generarCodigo();
		}
		else {
			mensaje("Registro erróneo");
			return;
		}}
			else {
				mensaje("El DNI: "+dni+" ya ha sido registrado");
				return;
			}
		}else {
			mensaje("El código ya existe");
			return;
		}}catch(Exception e) {
			mensaje("Ingrese Fecha de Nacimineto correctamente");
		}
	}
	private void actualizar() {
		Alumno a;
		String nom=txtNombre.getText().trim();
		String ape=txtApellidos.getText().trim();
		String dni=txtDNI.getText().trim();
		Date fecNac= txtFecNac.getDate();
		
		//MODIFICAMOS LOS STRING PARA QUE SE VEA ORDENADO
		nom=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(nom)));
		ape=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(ape)));
		
		//VERIFICAMOS SI SE A SELECCIONADO AL ALUMNO EN LA TABLA
		if(seleccionado=="") {
			mensaje("Seleccione Alumno en la tabla");
			return;
		}
		else {
			/*VALIDACIONES*/
			if(!nom.matches(Validaciones.nombre)) {
				mensaje("El nombre es de 2 a 50 caracteres");
				return;
			}
			if(Libreria.primeraPalabra(ape)==Libreria.ultimaPalabra(ape)) {
				mensaje("Los apellidos deben estar conformados por 2 o más palabras");
				return;
			}
			if(!ape.matches(Validaciones.apellidos)) {
				mensaje("Los apellidos son de 2 a 70 caracteres");
				return;
			}
			String separarApe=Libreria.reemplazarEspaciosPorGuiones(ape);
			if(separarApe.indexOf("-")==-1) {
				mensaje("Los apellidos deben ser 2 a más palabras");
				return;
			}
			if(!dni.matches(Validaciones.DNI)) {
				mensaje("El DNI es de 8 dígitos");
				return;
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			String anho=Libreria.primeraPalabra(Libreria.reemplazarSlashPorEspacios(sdf.format(fecNac)));
			
			if((anno-Integer.parseInt(anho))<=16) {
				mensaje("El alumno debe ser mayor a 16 años\nIngrese un año menor desde :"+(anno-16));
				return;
				}
			/*-------------------------------------------------------------------------------------------*/
			
			a=new Alumno();
			
			a.setIdAlumno(seleccionado);
			a.setNombre(nom);
			a.setApellidos(ape);
			a.setDNI(dni);
			a.setFecNac(sdf.format(fecNac));
			a.setFecReg(sdf.format(txtFecReg.getDate()));
			
			AlumnoModel model=new AlumnoModel();
			if(!(dni.equals(dniSeleccionado))) {
				if(model.buscarDNIAlumno(dni).isEmpty()==false) {
					mensaje("El DNI : "+dni+" ya ha sido ingresado");
					return;
				}
				
			}
			//if(model.buscarDNIAlumno(dni).isEmpty()) {
			int opcion=confirmacion("Confirmación de actualización", "Confirmación");
			if(opcion==0) {
				int s=model.actualizarAlumno(a);
				if(s>0) {
					mensaje("Actualización exitosa");
					listar();
					limpiar();
					seleccionado="";
					generarCodigo();
				}
				else {
					mensaje("Actualización errónea");
					return;
				}
			}
			else {
				return;
			}//}else {
				//mensaje("El DNI : "+dni+" ya ha sido registrado");
				//return;
	//		}
			}
	}
	
	private void eliminar() {
		//VERIFICAMOS SI SE A SELECCIONADO AL ALUMNO EN LA TABLA
		if(seleccionado=="") {
			mensaje("Seleccione Alumno");
			return;
		}else {
			AlumnoModel model=new AlumnoModel();
			int valor=confirmacion("Confirmación de eliminación", "ELIMINAR");
			if(valor==0) {
			int s=model.eliminarAlumno(seleccionado);
			
			if(s>0) {
				mensaje("Eliminación exitosa");
				listar();
				limpiar();
				seleccionado="";
				generarCodigo();
			}
			else {
				mensaje("Eliminación errónea");
				return;
			}}
		}
	}
	
	/*MÉTODOS*/
	private void mensaje(String x) {
		JOptionPane.showMessageDialog(null, x);
	}
	private int confirmacion(String mensaje,String Titulo) {
		int valor=JOptionPane.showConfirmDialog(null, mensaje,Titulo,JOptionPane.YES_NO_OPTION);
		return valor;
	}
	private void limpiar() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDNI.setText("");
		txtFecNac.setDate(null);
		generarCodigo();
		cantiadFilas();
	}
	
	private void cantiadFilas() {
		int cantidad=table.getRowCount();
		lblCantidad.setText(Integer.toString(cantidad));
	}
	
	/*Método para establecer el valor de la fecha e registro*/
	private void fecReg() {
		
		Date fechaParseada = null;
		try {
			fechaParseada = new SimpleDateFormat("yyyy/MM/dd").parse(anno+"/"+mes+"/"+dia);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtFecReg.setDate(fechaParseada);
	}
	
	/*MÉTODO PARA GENERAR CÓDIGO DE ALUMNOS ATOMATICAMENTE*/
	private void generarCodigo() {
		txtCodigo.setEditable(false);
		AlumnoModel model=new AlumnoModel();
		String codigo=model.buscarUltimoCodigoAlumno();
		if(codigo!="") {
		String separar=Libreria.separarLetrasYNumeros(codigo, 'U');
		String letras="ALU000";
		int numero=Integer.parseInt(Libreria.ultimaPalabra(separar));
		numero++;
		if(numero>9) letras="ALU00";
		if(numero>99)letras="ALU0";
		if(numero>999)letras="ALU";
		txtCodigo.setText(letras+Integer.toString(numero));
		}
		else {
			txtCodigo.setText("ALU0001");
		}
	}
	
	/*RESTRICCIONES AL PRESIONAR LAS TECLAS*/
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
		char s=arg0.getKeyChar();
		if(Libreria.soloLetras(s)=='S'||txtNombre.getText().length()>49) {
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void do_txtApellidos_keyTyped(KeyEvent arg0) {
		char c=arg0.getKeyChar();
		if(Libreria.soloLetras(c)=='S'||txtApellidos.getText().length()>69) {
			arg0.consume();
			getToolkit().beep();
		}
	}
	protected void do_txtDNI_keyTyped(KeyEvent arg0) {
		char s=arg0.getKeyChar();
		if(Libreria.soloNumeros(s)=='S'||txtDNI.getText().trim().length()>7) {
			arg0.consume();
			getToolkit().beep();
		}
	}
	
	/*GENERAR HORA EN TIEMPO REAL*/
	int hora,minutos,segundos;
	Calendar calendario;
	Thread hilo;
	private JButton btnExportarExcel;
	
	//Obtiene la hora
	public void obtenerHora() {
		calendario=new GregorianCalendar();
		hora=calendario.get(Calendar.HOUR_OF_DAY);
		minutos=calendario.get(Calendar.MINUTE);
		segundos=calendario.get(Calendar.SECOND);
	}
	//establece la hora en el label
	public void run() {//TIENE QUE TENER ESTE NOMBRE PARA QUE SE PUEDA EJECUTAR CORRECTAMEENTE
		
		Thread currentT=Thread.currentThread();
		while(currentT==hilo) {
			obtenerHora();
			String valHora=Integer.toString(hora)
			,valMinutos=Integer.toString(minutos)
			,valSegundos=Integer.toString(segundos);
			if(hora>=0 && hora<=9 ) {
				valHora="0"+valHora;
			}
			if(minutos>=0 && minutos<=9) {
				valMinutos="0"+valMinutos;
			}
			if(segundos>=0 && segundos<=9) {
				valSegundos="0"+valSegundos;
			}
			lblLabel.setText(valHora+":"+valMinutos+":"+valSegundos);
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
