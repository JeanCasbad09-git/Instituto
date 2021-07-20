package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import entidad.Alumno;
import entidad.Cursos;
import entidad.Registro;
import model.AlumnoModel;
import model.CursosModel;
import model.RegistroModel;
import util.Excel;
import util.Libreria;

@SuppressWarnings("serial")
public class Frm_Registro extends JInternalFrame implements ActionListener, MouseListener, Runnable {
	private JTable table;
	private JLabel lblCdigoAlumno;
	private JLabel lblCdigoCurso;
	@SuppressWarnings("rawtypes")
	private JComboBox cboAlumno;
	@SuppressWarnings("rawtypes")
	private JComboBox cboCurso;
	private JButton btnInsertar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnLimpiar;
	private JLabel lblHora;
	private JLabel lblLabel;
	private JLabel lblCantidadDeRegistrados;
	private JLabel lblCanti;
	private JButton btnExportarExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frm_Registro frame = new Frm_Registro();
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
	public Frm_Registro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 651, 499);
		getContentPane().setLayout(null);
		
		JLabel lblRegistro = new JLabel("REGISTRO");
		lblRegistro.setFont(new Font("Algerian", Font.ITALIC, 24));
		lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistro.setBounds(10, 11, 575, 24);
		getContentPane().add(lblRegistro);
		
		lblCdigoAlumno = new JLabel("C\u00F3digo Alumno :");
		lblCdigoAlumno.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCdigoAlumno.setBounds(26, 104, 129, 24);
		getContentPane().add(lblCdigoAlumno);
		
		lblCdigoCurso = new JLabel("C\u00F3digo Curso :");
		lblCdigoCurso.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCdigoCurso.setBounds(26, 150, 129, 24);
		getContentPane().add(lblCdigoCurso);
		
		cboAlumno = new JComboBox();
		cboAlumno.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		cboAlumno.setBackground(new Color(192, 192, 192));
		cboAlumno.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		cboAlumno.setBounds(209, 107, 364, 20);
		getContentPane().add(cboAlumno);
		
		cboCurso = new JComboBox();
		cboCurso.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		cboCurso.setBackground(new Color(192, 192, 192));
		cboCurso.setModel(new DefaultComboBoxModel(new String[] {"Seleccione"}));
		cboCurso.setBounds(209, 153, 364, 20);
		getContentPane().add(cboCurso);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 615, 168);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id Registro", "Id Alumno", "Alumno", "Id Curso", "Curso"
			}
		));
		
		table.addMouseListener(this);
		/*ARREGLOS TABA*/
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(5);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);
		//ENCABEZADO
		JTableHeader encabezado=new JTableHeader();
		encabezado.setReorderingAllowed(false);
		encabezado.setResizingAllowed(false);
		
		//CAMPOS NO EDTABLES
		table.setDefaultEditor(Object.class, null);
		
		//CENTRAR
		DefaultTableCellRenderer dtcr=new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(1).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(2).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(3).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		
		scrollPane.setViewportView(table);
		
		btnInsertar = new JButton("Insertar");
		btnInsertar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnInsertar.setBackground(new Color(147, 112, 219));
		btnInsertar.addActionListener(this);
		btnInsertar.setBounds(26, 199, 89, 23);
		getContentPane().add(btnInsertar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnActualizar.setBackground(new Color(147, 112, 219));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(157, 199, 111, 23);
		getContentPane().add(btnActualizar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLimpiar.setBackground(new Color(147, 112, 219));
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(496, 199, 89, 23);
		getContentPane().add(btnLimpiar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnEliminar.setBackground(new Color(147, 112, 219));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(348, 199, 89, 23);
		getContentPane().add(btnEliminar);
		
		lblHora = new JLabel("Hora :");
		lblHora.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblHora.setBounds(10, 434, 68, 24);
		getContentPane().add(lblHora);
		
		lblLabel = new JLabel("Label");
		lblLabel.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblLabel.setBounds(88, 434, 111, 24);
		getContentPane().add(lblLabel);
		
		lblCantidadDeRegistrados = new JLabel("Cantidad de Registrados :");
		lblCantidadDeRegistrados.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidadDeRegistrados.setBounds(188, 434, 193, 24);
		getContentPane().add(lblCantidadDeRegistrados);
		
		lblCanti = new JLabel("Canti");
		lblCanti.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCanti.setBounds(391, 434, 46, 24);
		getContentPane().add(lblCanti);
		
		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExportarExcel.setBackground(new Color(147, 112, 219));
		btnExportarExcel.addActionListener(this);
		btnExportarExcel.setBounds(496, 435, 129, 23);
		getContentPane().add(btnExportarExcel);
		
		lblCdigoDeRegistro = new JLabel("C\u00F3digo de Registro :");
		lblCdigoDeRegistro.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCdigoDeRegistro.setBounds(26, 57, 173, 24);
		getContentPane().add(lblCdigoDeRegistro);
		
		txtRegistro = new JTextField();
		txtRegistro.setBounds(209, 59, 144, 20);
		txtRegistro.setEditable(false);
		getContentPane().add(txtRegistro);
		txtRegistro.setColumns(10);
		
		listar();
		
		limpiar();
		
		hilo=new Thread(this);
		hilo.start();
		run();
	}
	String codigoRegistro="";
	String codigoCurso="";
	
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
		if (arg0.getSource() == btnInsertar) {
			do_btnInsertar_actionPerformed(arg0);
		}
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
	protected void do_btnLimpiar_actionPerformed(ActionEvent arg0) {
		listar();
		limpiar();
		habiDesabiBotones(true);
	}
	protected void do_btnExportarExcel_actionPerformed(ActionEvent arg0) {
		if(table.getRowCount()>0) {
			try {
				Excel.exportar(table);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			mensaje("No existe tabla a exportar");
			return;
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
		String codR=(String) table.getValueAt(fila, 0);
		String codA=(String) table.getValueAt(fila, 1);
		String nomA=(String) table.getValueAt(fila, 2);
		String codC=(String) table.getValueAt(fila, 3);
		String nomC=(String) table.getValueAt(fila, 4);
		
		codigoRegistro=codR;
		codigoCurso=codC;
		txtRegistro.setText(codR);
		cboAlumno.setSelectedItem(codA+" ("+nomA+")");
		cboCurso.setSelectedItem(codC+" ("+nomC+")");
		habiDesabiBotones(false);
	}
	
	private void insertar() {
		if(cboAlumno.getSelectedIndex()==0) {
			mensaje("Seleccione Código de Alumno");
			return;
		}
		if(cboCurso.getSelectedIndex()==0) {
			mensaje("Seleccione Código del Curso");
			return;
		}
		if(cboAlumno.getSelectedItem()=="No existen Alumnos") {
			mensaje("Registre Alumnos primero");
			return;
		}
		if(cboCurso.getSelectedItem()=="No existen Cursos") {
			mensaje("Registre Cursos primero");
			return;
		}
		
		String codR=txtRegistro.getText().trim();
		String codA=(String) cboAlumno.getSelectedItem();
		String codC=(String) cboCurso.getSelectedItem();
		
		codA=Libreria.extraerPalabraDeCadena(codA, "(");
		codC=Libreria.extraerPalabraDeCadena(codC, "(");
		
		RegistroModel model=new RegistroModel();
		if(model.buscarAlumnoYCurso(codC).equalsIgnoreCase(codA)) {
			mensaje("El Alumno ya está registrado en el Curso:\n"+cboCurso.getSelectedItem());
			return;
		}
		Registro r=new Registro();
		r.setIdREGISTRO(codR);
		r.setIdAlumno(codA);
		r.setIdCurso(codC);
		int s=model.insertar(r);
		if(s>0) {
			mensaje("Registro exitoso");
			listar();
			limpiar();
		}else {
			mensaje("Registro erróneo");
			return;
		}
	}
	
	private void actualizar() {
		if(codigoRegistro!="") {
			if(cboAlumno.getSelectedIndex()==0) {
				mensaje("Seleccione Código de Alumno");
				return;
			}
			if(cboCurso.getSelectedIndex()==0) {
				mensaje("Seleccione Código del Curso");
				return;
			}
			String codR=codigoRegistro;
			String codA=(String) cboAlumno.getSelectedItem();
			String codC=(String) cboCurso.getSelectedItem();
			
			codA=Libreria.extraerPalabraDeCadena(codA, "(");
			codC=Libreria.extraerPalabraDeCadena(codC, "(");
			
			RegistroModel model=new RegistroModel();
			if(!(codC.equalsIgnoreCase(codigoCurso))) {
				if(model.buscarAlumnoYCurso(codC).equalsIgnoreCase(codA)) {
					mensaje("El Alumno ya se registró en el Curso:\n"+cboCurso.getSelectedItem());
					return;
				}
			}
			int con=confirmacion("Actualización", "Confirmación de Actualización");
			if(con==0) {
			Registro r=new Registro();
			r.setIdREGISTRO(codR);
			r.setIdAlumno(codA);
			r.setIdCurso(codC);
			int s=model.actualizar(r);
			if(s>0) {
				mensaje("Actualización Exitosa");
				listar();
				limpiar();
				codigoRegistro="";
			}else {
				mensaje("Actualización Errónea");
				return;
			}}
		}else {
			mensaje("Seleccione Registro en la tabla");
		}
	}
	
	private void eliminar() {
		if(codigoRegistro!="") {
			
			RegistroModel model=new RegistroModel();
			int con=confirmacion("Eliminación", "Confirmación de Eliminación");
			if(con==0) {
			int s=model.eliminar(codigoRegistro);
			if(s>0) {
				mensaje("Eliminación Exitosa");
				listar();
				limpiar();
				codigoRegistro="";
			}else {
				mensaje("Eliminación Errónea");
				return;
			}}
		}else {
			mensaje("Seleccione Registro en la tabla");
		}
	}
	
	private void listar() {
		RegistroModel model=new RegistroModel();
		List<Registro> lista=model.listaRegistrado();
		DefaultTableModel dtm=(DefaultTableModel)table.getModel();
		dtm.setRowCount(0);
		for (Registro x : lista) {
			Object [] f= {
					x.getIdREGISTRO(),x.getIdAlumno(),x.getNombreCom(),x.getIdCurso(),x.getCurso()
			};
			dtm.addRow(f);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void limpiar() {
		cboAlumno.removeAllItems();
		cboAlumno.addItem("Seleccione");
		cboCurso.removeAllItems();
		cboCurso.addItem("Seleccione");
		aagregarTextoCBO();
		generarCodigo();
		cboAlumno.setSelectedIndex(0);
		cboCurso.setSelectedIndex(0);
		codigoRegistro="";
		contarFilas();
		habiDesabiBotones(true);
	}
	
	private void habiDesabiBotones(boolean j) {
		cboAlumno.setEnabled(j);
		btnInsertar.setEnabled(j);
		btnExportarExcel.setEnabled(j);
	}
	
	private void mensaje(String s) {
		JOptionPane.showMessageDialog(null, s);
	}
	
	private int confirmacion(String tit,String msj) {
		int con=JOptionPane.showConfirmDialog(null, msj,tit,JOptionPane.YES_NO_OPTION);
		return con;
	}
	
	private void contarFilas() {
		int fila=table.getRowCount();
		if(fila>0) {
			lblCanti.setText(Integer.toString(fila));
		}else {
			lblCanti.setText("0");
		}
	}
	
	@SuppressWarnings("unchecked")
	private void aagregarTextoCBO() {
		AlumnoModel modelA=new AlumnoModel();
		CursosModel modelC=new CursosModel();
		List<Alumno> listaA=modelA.listarCodigosAlumnos();
		List<Cursos> listaC=modelC.listarCodCursos();
		
		if(!(listaA.isEmpty() && listaC.isEmpty())) {
			//EVITAR VALORES REPETIDOS
			/*HashMap<String, Alumno> map= new HashMap<String,Alumno>();
			for(Alumno alumno:listaA) {
				map.put(alumno.getIdAlumno(), alumno);
			}
			Set<Entry<String, Alumno>> set=map.entrySet();
			for (Entry<String, Alumno> entry : set) {
				cboAlumno.addItem(entry.getValue().getIdAlumno()+" ("+entry.getValue().getNombre()+")");
			}*/
			for (Alumno x : listaA) {
				cboAlumno.addItem(x.getIdAlumno()+" ("+x.getNombre()+")");
			}
		}else {
			cboAlumno.addItem("No existen Alumnos");
		}
		if(!(listaC.isEmpty())) {
			/*HashMap<String, Cursos> map= new HashMap<String,Cursos>();
			for(Cursos curso:listaC) {
				map.put(curso.getIdCurso(), curso);
			}
			Set<Entry<String, Cursos>> set=map.entrySet();
			for (Entry<String, Cursos> entry : set) {
				cboCurso.addItem(entry.getValue().getIdCurso()+" ("+entry.getValue().getCurso()+")");
			}*/
			for (Cursos x : listaC) {
				cboCurso.addItem(x.getIdCurso()+" ("+x.getCurso()+")");
			}
		}
		else {
			cboCurso.addItem("No existen Cursos");
		}
	}
	
	private void generarCodigo() {
		RegistroModel model=new RegistroModel();
		String cod=model.ultimoCodigoRegistro();
		if(cod!="") {
			int num=Integer.parseInt(Libreria.ultimaPalabra(Libreria.separarLetrasYNumeros(cod, 'G')));
			cod=Libreria.primeraPalabra(Libreria.separarLetrasYNumeros(cod, '0'));
			if(num>=1 && num<=9) {
				cod=cod+"000"+Integer.toString(num+1);
			}
			if(num>=10 && num<=99) {
				cod=cod+"00"+Integer.toString(num+1);
			}if(num>=100 && num<=999) {
				cod=cod+"0"+Integer.toString(num+1);
			}if(num>=1000 && num<=9999) {
				cod=cod+Integer.toString(num+1);
			}
			txtRegistro.setText(cod);
		}else {
			txtRegistro.setText("REG0001");
		}
	}
	
	/*HORA EN TIEMPO REAL*/
	int horas,minutos,segundos;
	Calendar calendario;
	Thread hilo;
	private JTextField txtRegistro;
	private JLabel lblCdigoDeRegistro;
	private void generarHora() {
		calendario=new GregorianCalendar();
		horas=calendario.get(Calendar.HOUR_OF_DAY);
		minutos=calendario.get(Calendar.MINUTE);
		segundos=calendario.get(Calendar.SECOND);
	}
	
	@SuppressWarnings("static-access")
	public void run() {
		Thread current=Thread.currentThread();
		while(current==hilo) {
			generarHora();
			String hor=Integer.toString(horas),min=Integer.toString(minutos),sec=Integer.toString(segundos);
			if(horas>=0&&horas<=9) {
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
				current.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
