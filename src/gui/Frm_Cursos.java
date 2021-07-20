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
import model.CursosModel;
import util.Excel;
import util.Libreria;
import util.Validaciones;

@SuppressWarnings("serial")
public class Frm_Cursos extends JInternalFrame implements ActionListener, MouseListener, KeyListener, Runnable  {
	private JLabel lblCrditos;
	private JLabel lblCurso;
	private JLabel lblCdigoCurso;
	private JLabel lblCiclo;
	private JTextField txtCodigo;
	private JTextField txtCurso;
	private JLabel lblCursos;
	@SuppressWarnings("rawtypes")
	private JComboBox cboCiclo;
	@SuppressWarnings("rawtypes")
	private JComboBox cboCreditos;
	private JTable table;
	private JLabel lblLabel;
	private JLabel lblHora;
	private JLabel lblCantidadCursos;
	private JLabel lblCantdad;
	private JButton btnExportarExcel;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnLimpiar;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Cursos frame = new Frm_Cursos();
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
	public Frm_Cursos() {
		setBounds(100, 100, 635, 439);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		lblCursos = new JLabel("Cursos");
		lblCursos.setFont(new Font("Algerian", Font.ITALIC, 24));
		lblCursos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCursos.setBounds(10, 11, 599, 23);
		getContentPane().add(lblCursos);
		
		lblCdigoCurso = new JLabel("C\u00F3digo Curso :");
		lblCdigoCurso.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCdigoCurso.setBounds(10, 51, 122, 23);
		getContentPane().add(lblCdigoCurso);
		
		lblCurso = new JLabel("Curso :");
		lblCurso.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCurso.setBounds(10, 92, 122, 23);
		getContentPane().add(lblCurso);
		
		lblCrditos = new JLabel("Cr\u00E9ditos :");
		lblCrditos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCrditos.setBounds(358, 92, 101, 23);
		getContentPane().add(lblCrditos);
		
		lblCiclo = new JLabel("Ciclo :");
		lblCiclo.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCiclo.setBounds(358, 51, 101, 23);
		getContentPane().add(lblCiclo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setBounds(137, 52, 89, 20);
		getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtCurso = new JTextField();
		txtCurso.addKeyListener(this);
		txtCurso.setBounds(137, 93, 157, 20);
		getContentPane().add(txtCurso);
		txtCurso.setColumns(10);
		
		cboCiclo = new JComboBox();
		cboCiclo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "1", "2", "3", "4", "5", "6"}));
		cboCiclo.setBounds(489, 52, 101, 20);
		getContentPane().add(cboCiclo);
		
		cboCreditos = new JComboBox();
		cboCreditos.setModel(new DefaultComboBoxModel(new String[] {"Seleccione", "1", "2", "3", "4"}));
		cboCreditos.setBounds(489, 93, 101, 20);
		getContentPane().add(cboCreditos);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnIngresar.setBackground(new Color(147, 112, 219));
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(29, 138, 89, 23);
		getContentPane().add(btnIngresar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnActualizar.setBackground(new Color(147, 112, 219));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(164, 138, 111, 23);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnEliminar.setBackground(new Color(147, 112, 219));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(343, 138, 89, 23);
		getContentPane().add(btnEliminar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLimpiar.setBackground(new Color(147, 112, 219));
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(489, 138, 89, 23);
		getContentPane().add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 178, 599, 165);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo Curso", "Curso", "Ciclo", "Cr\u00E9ditos"
			}
		));
		/*MEJORAS DE LA TABLA*/
		//PARA QUE LOS ENCABEZADOS NO SEAN EDITABLES
		JTableHeader encabezado=new JTableHeader();
		encabezado.setReorderingAllowed(false);
		encabezado.setResizingAllowed(false);
		
		//PARA QUE LOS CAMPOS NO SEAN EDITABLES
		table.setDefaultEditor(Object.class, null);
		
		//MODIFICAR EL TAMAÑO DE CADA COLUMNA
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(5);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		
		//CENTRAR EL TEXTO DE LOS CAMPOS
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(3).setCellRenderer(dtcr);

		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		
		
		
		lblHora = new JLabel("Hora :");
		lblHora.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblHora.setBounds(10, 369, 66, 29);
		getContentPane().add(lblHora);
		
		lblLabel = new JLabel("label");
		lblLabel.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblLabel.setBounds(86, 369, 111, 29);
		getContentPane().add(lblLabel);
		
		lblCantidadCursos = new JLabel("Cantidad Cursos :");
		lblCantidadCursos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidadCursos.setBounds(207, 369, 161, 29);
		getContentPane().add(lblCantidadCursos);
		
		lblCantdad = new JLabel("Cantdad");
		lblCantdad.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantdad.setBounds(378, 369, 54, 29);
		getContentPane().add(lblCantdad);
		
		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExportarExcel.setBackground(new Color(147, 112, 219));
		btnExportarExcel.addActionListener(this);
		btnExportarExcel.setBounds(472, 372, 118, 23);
		getContentPane().add(btnExportarExcel);
		
		
		generarCodigo();
		listar();
		contarFilas();
		
		hilo=new Thread(this);
		hilo.start();
		run();
	}
	
	String codigoCurso="";
	String nomCurso="";
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExportarExcel) {
			do_btnExportarExcel_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnIngresar) {
			do_btnIngresar_actionPerformed(arg0);
		}
	}
	protected void do_btnIngresar_actionPerformed(ActionEvent arg0) {
		insertar();
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent arg0) {
		actualizar();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent arg0) {
		eliminar();
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent arg0) {
		limpiar();
		listar();
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
		//String cod=(String) table.getValueAt(fila, 0);
		codigoCurso=(String) table.getValueAt(fila, 0);
		nomCurso=(String) table.getValueAt(fila, 1);
		String cic=Integer.toString((int) table.getValueAt(fila, 2));
		String cre=Integer.toString((int) table.getValueAt(fila, 3));
		
		txtCodigo.setText(codigoCurso);
		txtCurso.setText(nomCurso);
		cboCiclo.setSelectedItem(cic);
		cboCreditos.setSelectedItem(cre);
	}
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent arg0) {
	}
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getSource() == txtCurso) {
			do_txtCurso_keyTyped(arg0);
		}
	}
	protected void do_txtCurso_keyTyped(KeyEvent arg0) {
		char caracter=arg0.getKeyChar();
		if(Libreria.soloLetras(caracter)=='S'||txtCurso.getText().length()>69) {
			arg0.consume();
			getToolkit();
		}
	}
	
	private void generarCodigo() {
		CursosModel model=new CursosModel();
		String codigo=model.buscarUltimoCodigo();
		if(codigo!="") {
		codigo=Libreria.separarLetrasYNumeros(codigo, 'R');
		int num=Integer.parseInt(Libreria.ultimaPalabra(codigo));
		if(num>=1 && num<=9) {
			codigo="CUR000"+Integer.toString(num+1);
		}
		if(num>=10 && num<=99) {
			codigo="CUR00"+Integer.toString(num+1);
		}
		if(num>=100 && num<=999) {
			codigo="CUR0"+Integer.toString(num+1);
		}
		if(num>=1000 && num<=9999) {
			codigo="CUR"+Integer.toString(num+1);
		}
		txtCodigo.setText(codigo);
		}else {
			txtCodigo.setText("CUR0001");
		}
	}
	private void listar() {
		CursosModel model=new CursosModel();
		List<Cursos> lista=model.listaCursos();
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Cursos x : lista) {
			Object [] f= {
				x.getIdCurso(),x.getCurso(),x.getCiclo(),x.getCreditos()	
			};
			dtm.addRow(f);
		}
	}
	private void insertar() {
		String cod=txtCodigo.getText().trim();
		String cur=txtCurso.getText().trim();
		
		if(!(cod.matches("CUR[0-9]{4}"))) {
			mensaje("Formato de código : CUR[0-9][0-9][0-9][0-9]");
			return;
		}
		if(!(cur.matches(Validaciones.apellidos))) {
			mensaje("Curso es de 2 a 70 caracteres");
			return;
		}
		if(cboCiclo.getSelectedIndex()==0) {
			mensaje("Seleccione Ciclo");
			return;
		}
		int cic=Integer.parseInt((String) cboCiclo.getSelectedItem());
		if(cboCreditos.getSelectedIndex()==0) {
			mensaje("Seleccione Créditos");
			return;
		}
		int cre=Integer.parseInt((String) cboCreditos.getSelectedItem());
		
		
		cur=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(cur)));
		
		
		CursosModel model=new CursosModel();
		if(model.buscarCurso(cur).isEmpty()) {
		String ulti=Libreria.ultimaPalabra(Libreria.enMinusculas(cur));
			
		if(ulti.equals("i")||ulti.equals("ii")||ulti.equals("iii")) {
		cur=Libreria.sinUltimaPalabra(cur);
		ulti=Libreria.enMayusculas(ulti);
		cur=cur+" "+ulti;
		}
		Cursos obj=new Cursos();
		obj.setIdCurso(cod);
		obj.setCurso(cur);
		obj.setCiclo(cic);
		obj.setCreditos(cre);
		
		
		int s=model.insertarCursos(obj);
		if(s>0) {
			mensaje("Registro Exitoso");
			listar();
			limpiar();
		}
		else {
			mensaje("Registro Erróneo");
			return;
		}
		}else {
			mensaje("Nombre del Curso ya existe");
			return;
		}
		
	}
	private void actualizar() {
		if(codigoCurso=="") {
			mensaje("Seleccione Curso en la tabla");
			return;
		}
		else {
			String cod=codigoCurso;
			String cu=txtCurso.getText().trim();
			if(!(cod.matches("CUR[0-9]{4}"))) {
				mensaje("Formato de código: [0-9][0-9][0-9][0-9]");
				return;
			}
			if(!(cu.matches(Validaciones.apellidos))) {
				mensaje("El nombre del curso es de 2 a 70 caracteres");
				return;
			}
			if(cboCiclo.getSelectedIndex()==0) {
				mensaje("Seleccione Ciclo");
				return;
			}
			int cic=Integer.parseInt((String) cboCiclo.getSelectedItem());
			if(cboCreditos.getSelectedIndex()==0) {
				mensaje("Seleccione Créditos");
				return;
			}
			int cre=Integer.parseInt((String) cboCreditos.getSelectedItem());
			
			cu=Libreria.primeraLetraEnMayusculas(Libreria.enMinusculas(Libreria.soloUnEspacioEntrePalabras(cu)));
			
			CursosModel model=new CursosModel();
			
			if(!(cu.equalsIgnoreCase(nomCurso))) {
				if(!(model.buscarCurso(cu).isEmpty())) {
					mensaje("El nombre del Curso :"+cu+" ya ha sido ingresado");
					return;
				}
			}
			String ulti=Libreria.ultimaPalabra(Libreria.enMinusculas(cu));
			
			if(ulti.equals("i")||ulti.equals("ii")||ulti.equals("iii")) {
				cu=Libreria.sinUltimaPalabra(cu);
				ulti=Libreria.enMayusculas(ulti);
				cu=cu+" "+ulti;
			}
			Cursos c=new Cursos();
			c.setIdCurso(cod);
			c.setCurso(cu);
			c.setCiclo(cic);
			c.setCreditos(cre);
			
			int confi=confirmacion("Confirmacion de Actualización", "Actualización");
			if(confi==0) {
				int s=model.actualizarCursos(c);
				if(s>0) {
					mensaje("Actualización exitosa");
					limpiar();
					listar();
				}
				else {
					mensaje("Actualización errónea");
					return;
				}
			}
		}
	}
	
	private void eliminar() {
		if(codigoCurso=="") {
			mensaje("Seleccione Curso en la tabla");
			return;
		}else {
			
			if(!(codigoCurso.matches("CUR[0-9]{4}"))) {
				mensaje("El formato del código es :CUR[0-9][0-9][0-9][0-9]");
				return;
			}
			CursosModel model=new CursosModel();
			int confi=confirmacion("Confirmación de Eliminación", "Eliminar");
			if(confi==0) {
				int s=model.eliminarCurso(codigoCurso);
				if(s>0) {
					mensaje("Eliminación exitosa");
					limpiar();
					listar();
				}
				else {
					mensaje("Eliminación Errónea");
					return;
				}
			}
		}
	}
	private void limpiar() {
		generarCodigo();
		txtCurso.setText("");
		cboCiclo.setSelectedIndex(0);
		cboCreditos.setSelectedIndex(0);
		codigoCurso="";
		nomCurso="";
		contarFilas();
	}
	private void contarFilas() {
		int cantidad=table.getRowCount();
		lblCantdad.setText(Integer.toString(cantidad));
	}
	private void mensaje(String x) {
		JOptionPane.showMessageDialog(null, x);
	}
	private int confirmacion(String msj,String tit) {
		int valor=JOptionPane.showConfirmDialog(null, msj,tit,JOptionPane.YES_NO_OPTION);
		return valor;
	}
	
	/*GENERAR HORA EN TIEMO REAL*/
	int hora,minutos,segundos;
	Thread hilo;
	Calendar calendario;
	
	public void obtenerHora() {
		calendario=new GregorianCalendar();
		hora=calendario.get(Calendar.HOUR_OF_DAY);
		minutos=calendario.get(Calendar.MINUTE);
		segundos=calendario.get(Calendar.SECOND);
	}
	public void run() {
		Thread current=Thread.currentThread();
		while(current==hilo) {
		obtenerHora();
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
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		}
	}
	
}
