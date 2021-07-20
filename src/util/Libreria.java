package util;

public class Libreria {
	
	private static int longitud(String s) {
		return s.length();
	}
	
	private static char ultimoCaracter(String a) {
		return a.charAt(longitud(a)-1);
	}
	
	public static String enMayusculas(String s) {
		return s.toUpperCase();
	}
	
	public static String enMinusculas(String s) {
		return s.toLowerCase();
	}
	
	public static String soloUnEspacioEntrePalabras(String s) {
		String texto="";
		char caracter;
		for(int i=0;i<longitud(s);i++) {
			caracter=s.charAt(i);
			if(!(caracter==' ' && ultimoCaracter(texto)==' ')) {
				texto+=caracter;
			}
		}
		return texto;
	}
	
	public static String primeraLetraEnMayusculas(String s) {
		char [] caracteres=s.toCharArray();
		caracteres[0]=Character.toUpperCase(caracteres[0]);
		for(int i=0;i<longitud(s)-2;i++) {//EL -2 ES PARA EVITAR UNA EXCEPTION DEL ARREGLO
			if(caracteres[i]==' ' ||caracteres[i]==','||caracteres[i]=='.') {
				caracteres[i+1]=Character.toUpperCase(caracteres[i+1]);
			}
			
		}
		return new String(caracteres);
	}
	
	public static String reemplazarSlashPorEspacios(String x) {
		return x.replace('/', ' ');
	}
	public static String reemplazarEspaciosPorGuiones(String x) {
		return x.replace(' ', '-');
	}
	private static int posPrimerEspacio(String s) {
		return s.indexOf(' ');
	}
	public static String separarLetrasYNumeros(String s,char x) {
		return s.replace(x, ' ');
	}
	public static String primeraPalabra(String s) {
		int pos=posPrimerEspacio(s);
		if(pos==-1) 
			return s;
		else 
			return s.substring(0,pos);
		
	}
	
	private static int posUltimoEspacio(String s) {
		return s.lastIndexOf(' ');
	}
	public static String ultimaPalabra(String s) {
		int pos=posUltimoEspacio(s);
		if(pos==-1) {
			return s;
		}
		else {
			return s.substring(pos+1);
		}
	}
	public static String sinUltimaPalabra(String s) {
		int pos=posUltimoEspacio(s);
		if(pos==-1) {
			return s;
		}else {
			return s.substring(0,pos);
		}
	}
	
	public static String extraerPalabraDeCadena(String texto,String hasta) {
		int pos=texto.indexOf(hasta);
		if(pos==-1) {
			return texto;
		}
		else {
			return texto.substring(0, pos-1);
		}
	}
	
	public static char soloLetras(char f) {
		char error='N';
		if(Character.isDigit(f)||f=='.'||f==','||f==';'||f==':'||f=='-'||f=='_'||f=='{'||f=='['||f=='}'||f==']'
		||f=='¨'||f=='+'||f=='*'||f=='¿'||f=='¡'||f=='?'||f=='='||f==')'||f=='('||f=='/'||f=='&'||f=='%'||f=='$'
		||f=='#'||f=='"'||f=='@'||f=='!'||f=='|'||f=='°'||f=='¬'||f=='<'||f=='>') {
			error='S';
		}
		return error;
	}
	public static char soloNumeros(char c) {
		char error='N';
		if(!(Character.isDigit(c))) {
			error='S';
		}
		return error;
	}
}
