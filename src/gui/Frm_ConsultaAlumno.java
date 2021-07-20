package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import entidad.Alumno;
import model.AlumnoModel;
import util.Excel;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class Frm_ConsultaAlumno extends JInternalFrame implements ActionListener, Runnable, MouseListener	{
	private JLabel lblAlumno;
	private JLabel lblSeleccione;
	private JTable table;
	private JDateChooser txtDesde;
	private JLabel lblLabel;
	private JButton btnExportarExcel;
	private JLabel lblGuion;
	private JLabel lblIngreseRangoDe;
	@SuppressWarnings("rawtypes")
	private JComboBox cboDatos;
	private JLabel lblConsultaPor;
	@SuppressWarnings("rawtypes")
	private JComboBox cboConsultaPor;
	private JDateChooser txtHasta;
	private JButton btnLimpiar;
	private JButton btnConsultar;
	private JLabel lblCantidad;
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Frm_ConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setClosable(true);
		getContentPane().setBackground(new Color(176, 224, 230));
		setBounds(100, 100, 723, 489);
		getContentPane().setLayout(null);
		
		lblAlumno = new JLabel("CONSULTA ALUMNO");
		lblAlumno.setFont(new Font("Algerian", Font.ITALIC, 24));
		lblAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlumno.setBounds(10, 11, 686, 24);
		getContentPane().add(lblAlumno);
		
		lblSeleccione = new JLabel("Seleccione :");
		lblSeleccione.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblSeleccione.setBounds(10, 46, 339, 24);
		getContentPane().add(lblSeleccione);
		
		lblConsultaPor = new JLabel("Consultar por :");
		lblConsultaPor.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblConsultaPor.setBounds(393, 46, 164, 24);
		getContentPane().add(lblConsultaPor);
		
		txtDesde = new JDateChooser();
		txtDesde.setDateFormatString("yyyy-MMM-dd");
		txtDesde.setBounds(10, 142, 116, 20);
		getContentPane().add(txtDesde);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 686, 227);
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
		lblHora.setBounds(20, 411, 62, 24);
		getContentPane().add(lblHora);
		
		lblLabel = new JLabel("label");
		lblLabel.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblLabel.setBounds(109, 411, 86, 24);
		getContentPane().add(lblLabel);
		
		JLabel lblCantidadDeAlumnos = new JLabel("Cantidad Consultada :");
		lblCantidadDeAlumnos.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidadDeAlumnos.setBounds(238, 411, 186, 24);
		getContentPane().add(lblCantidadDeAlumnos);
		
		lblCantidad = new JLabel("label 2");
		lblCantidad.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblCantidad.setBounds(434, 411, 69, 24);
		getContentPane().add(lblCantidad);
		
		btnExportarExcel = new JButton("Exportar Excel");
		btnExportarExcel.addActionListener(this);
		btnExportarExcel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnExportarExcel.setBackground(new Color(147, 112, 219));
		btnExportarExcel.setBounds(533, 413, 138, 23);
		getContentPane().add(btnExportarExcel);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnConsultar.setBackground(new Color(147, 112, 219));
		btnConsultar.addActionListener(this);
		btnConsultar.setBounds(393, 115, 110, 23);
		getContentPane().add(btnConsultar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		btnLimpiar.setBackground(new Color(147, 112, 219));
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(564, 115, 97, 23);
		getContentPane().add(btnLimpiar);
		
		cboConsultaPor = new JComboBox();
		cboConsultaPor.addActionListener(this);
		cboConsultaPor.setModel(new DefaultComboBoxModel(new String[] {"SELECCIONE TIPO DE FILTRO", "C\u00F3digo Alumno", "Nombre", "Apellidos", "DNI", "Fecha Nacimiento", "Fecha Registro"}));
		cboConsultaPor.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		cboConsultaPor.setBackground(new Color(192, 192, 192));
		cboConsultaPor.setBounds(393, 81, 303, 20);
		getContentPane().add(cboConsultaPor);
		
		cboDatos = new JComboBox();
		cboDatos.addMouseListener(this);
		cboDatos.addActionListener(this);
		cboDatos.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 15));
		cboDatos.setBackground(new Color(192, 192, 192));
		cboDatos.setBounds(10, 81, 339, 20);
		getContentPane().add(cboDatos);
		
		txtHasta = new JDateChooser();
		txtHasta.setDateFormatString("yyyy-MMM-dd");
		txtHasta.setBounds(233, 142, 116, 20);
		getContentPane().add(txtHasta);
		
		lblIngreseRangoDe = new JLabel("Ingrese Rango de Fechas :");
		lblIngreseRangoDe.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblIngreseRangoDe.setBounds(10, 112, 339, 24);
		getContentPane().add(lblIngreseRangoDe);
		
		lblGuion = new JLabel("-");
		lblGuion.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuion.setFont(new Font("Times New Roman", Font.ITALIC, 41));
		lblGuion.setBounds(136, 138, 55, 24);
		getContentPane().add(lblGuion);
		
		/*PARA QUE EL TXTFECNAC Y TXTFECREG NO SEA EDITABLE*/
		JTextFieldDateEditor editor=(JTextFieldDateEditor) txtDesde.getDateEditor();
		editor.setEditable(false);
		
		JTextFieldDateEditor editor1=(JTextFieldDateEditor) txtHasta.getDateEditor();
		editor1.setEditable(false);
		
		limpiar();
		
		/*INICIALIZAMOS EL RELOJ*/
		hilo=new Thread(this);
		hilo.start();
		run();
	}
	/*GENERACIÓN DE FECHA ACTUAL*/
	Date date=new Date();
	LocalDate fecZonHor=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	int anno=fecZonHor.getYear();
	int mes=fecZonHor.getMonthValue();
	int dia=fecZonHor.getDayOfMonth();
	
	//acciones de los botones
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cboConsultaPor) {
			do_cboConsultaPor_actionPerformed(e);
		}
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnConsultar) {
			do_btnConsultar_actionPerformed(e);
		}
		if (e.getSource() == btnExportarExcel) {
			do_btnExportarExcel_actionPerformed(e);
		}
	}
	
	protected void do_btnConsultar_actionPerformed(ActionEvent e) {
		
		AlumnoModel model=new AlumnoModel();
		List<Alumno> lista=null;
		DefaultTableModel dtm=(DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		
		if(cboConsultaPor.getSelectedIndex()>=0 && cboConsultaPor.getSelectedIndex()<=4) {
			if(cboDatos.getSelectedItem()=="SELECCIONE") {
				mensaje("Seleccione Dato");
				listar();
				return;
			}
			String dato=(String) cboDatos.getSelectedItem();
			if(cboConsultaPor.getSelectedIndex()==1) {
				lista=model.listarCodigosAlumnosCBO(dato);
			}
			if(cboConsultaPor.getSelectedIndex()==2) {
				lista=model.listarNombreAlumnosCBO(dato);
			}
			if(cboConsultaPor.getSelectedIndex()==3) {
				lista=model.listarApellidosAlumnosCBO(dato);
			}
			if(cboConsultaPor.getSelectedIndex()==4) {
				lista=model.listarDNIAlumnosCBO(dato);
			}
		}
		if(cboConsultaPor.getSelectedIndex()==5 || cboConsultaPor.getSelectedIndex()==6) {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
			try {
				Date desde=txtDesde.getDate();
				try {
					Date hasta=txtHasta.getDate();
					if(cboConsultaPor.getSelectedIndex()==5) {
						lista=model.listarFecNacAlumnosCBO(sdf.format(desde), sdf.format(hasta));
					}
					if(cboConsultaPor.getSelectedIndex()==6) {
						lista=model.listarFecRegAlumnosCBO(sdf.format(desde), sdf.format(hasta));
					}
				}catch(Exception a) {
					mensaje("ERROR");
				}
			}catch(Exception f) {
				mensaje("ERROR");
			}
		}
		
		if(lista.isEmpty()) {
			mensaje("No se encontró Alumno(s) con los datos ingresados");
			listar();
			return;
		}
		for (Alumno x : lista) {
			Object [] f= {
				x.getIdAlumno(),x.getNombre(),x.getApellidos(),x.getDNI(),x.getFecNac(),x.getFecReg()	
			};
			dtm.addRow(f);
		}
	}
	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		listar();
		limpiar();
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
	
	protected void do_cboConsultaPor_actionPerformed(ActionEvent e) {
		agregarDatosACBODATOS();
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
		
		
	}
	
	/*MÉTODOS*/
	private void mensaje(String x) {
		JOptionPane.showMessageDialog(null, x);
	}
	
	@SuppressWarnings("unchecked")
	private void limpiar() {
		cantiadFilas();
		cboDatos.removeAllItems();		
		cboDatos.addItem("SELECCIONE");
		cboDatos.setEnabled(true);
		cboConsultaPor.setSelectedIndex(0);
		cboDatos.setSelectedIndex(0);
		txtDesde.setEnabled(false);
		txtHasta.setEnabled(false);
		fecReg();
		listar();
	}
	
	@SuppressWarnings({ "unchecked" })
	private void agregarDatosACBODATOS() {
		AlumnoModel model=new AlumnoModel();
		List<Alumno> lista=null;
		switch (cboConsultaPor.getSelectedIndex()) {
		case 0: limpiar();
		cboConsultaPor.setSelectedIndex(0);
			cboDatos.setEnabled(true);break;
		case 1: limpiar();
			cboConsultaPor.setSelectedIndex(1);
				lista=model.listarCodigosAlumnosCBO();
				for (Alumno alumno : lista) {
					cboDatos.addItem(alumno.getIdAlumno());
				};break;
		case 2:limpiar();
		cboConsultaPor.setSelectedIndex(2);
			lista=model.listarNombreAlumnosCBO();
				for (Alumno alumno : lista) {
					cboDatos.addItem(alumno.getNombre());
				};break;
		case 3:limpiar();
		cboConsultaPor.setSelectedIndex(3);
			lista=model.listarApellidosAlumnosCBO();
			for (Alumno alumno : lista) {
				cboDatos.addItem(alumno.getApellidos());
			};break;
		case 4:limpiar();
		cboConsultaPor.setSelectedIndex(4);
			lista=model.listarDNIAlumnosCBO();
		for (Alumno alumno : lista) {
			cboDatos.addItem(alumno.getDNI());
		};break;
		case 5:limpiar();
		cboConsultaPor.setSelectedIndex(5);
			cboDatos.setEnabled(false);
			txtDesde.setEnabled(true);
			txtHasta.setEnabled(true);break;
		case 6:limpiar();
		cboConsultaPor.setSelectedIndex(6);
			cboDatos.setEnabled(false);
		txtDesde.setEnabled(true);
		txtHasta.setEnabled(true);break;
		}
		
			
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
		txtDesde.setDate(fechaParseada);
		txtHasta.setDate(fechaParseada);
	}
	

	
	/*GENERAR HORA EN TIEMPO REAL*/
	int hora,minutos,segundos;
	Calendar calendario;
	Thread hilo;
	
	
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
	

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
