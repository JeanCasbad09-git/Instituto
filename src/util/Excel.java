package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Excel {

	public static void exportar(JTable table) throws IOException{
		JFileChooser eleccionDeArchivo=new JFileChooser();
		FileNameExtensionFilter extension=new FileNameExtensionFilter("Archivos de Excel", "xls");
		eleccionDeArchivo.setFileFilter(extension);
		eleccionDeArchivo.setDialogTitle("Guardar Archvo");
		eleccionDeArchivo.setAcceptAllFileFilterUsed(false);
		if(eleccionDeArchivo.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
			String ruta=eleccionDeArchivo.getSelectedFile().toString().concat(".xls");
			try {
				File archivoXLSX=new File(ruta);
				if(archivoXLSX.exists()) {
					archivoXLSX.delete();
					}
				archivoXLSX.createNewFile();
				Workbook libro=new HSSFWorkbook();
				FileOutputStream archivo=new FileOutputStream(archivoXLSX);
				Sheet hoja=libro.createSheet("Mi hoja de trabajo");
				hoja.setDisplayGridlines(false);
				for(int i=0;i<table.getRowCount();i++) {
					Row fila=hoja.createRow(i);
					for(int f=0;f<table.getColumnCount();f++) {
						Cell celda=fila.createCell(f);
						if(i==0) {
							celda.setCellValue(table.getColumnName(f));
						}
					}
				}
				int filaInicio=1;
				for(int i=0;i<table.getRowCount();i++) {
					Row fila=hoja.createRow(filaInicio);
					filaInicio++;
					for(int f=0;f<table.getColumnCount();f++) {
						Cell celda=fila.createCell(f);
						if(table.getValueAt(i, f) instanceof Double) {
							celda.setCellValue(Double.parseDouble(table.getValueAt(i, f).toString()));
						}else if(table.getValueAt(i, f) instanceof Float) {
							celda.setCellValue(Float.parseFloat((String) table.getValueAt(i, f).toString()));
						}else {
							celda.setCellValue(String.valueOf(table.getValueAt(i, f)));
						}
					}
				}
				libro.write(archivo);
				archivo.close();
				int decision=JOptionPane.showConfirmDialog(null, "¿Desea abrir el archivo?","ABRIR ARCHIVO",JOptionPane.YES_NO_OPTION);
				if(decision==0) {
				Desktop.getDesktop().open(archivoXLSX);
				}
			}
			catch(IOException | NumberFormatException e) {
				throw e;
			}
		}
	}
}
