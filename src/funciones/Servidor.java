package funciones;

public class Servidor {
	
	/*
	 * uBICACION EN ALQUILERES
	 * 
	 * */
	
	public static  String URL_INICIO ="jdbc:mysql://";
	//public static final String HOST_NAME="169.254.40.203";
	public static  String HOST_NAME="localhost";

	public static  String BASE_DATOS="sistemaliri";
	public static  String UNICODE="?useUnicode=yes&characterEncoding=UTF-8";
	
	//public static final String USER="SistemaLiri";
	public static  String USER="root";

	//public static final String PASS="$@s1st3m4l1r12oi9$@";
	public static  String PASS="1234";

	//private static  String SERVIDOR = URL_INICIO+HOST_NAME+"/"+BASE_DATOS + UNICODE;
	public static  String BASE_DATOS_IMAGEN="sistemaLiriImagenes";
	
	//private static  String SERVIDOR_FOTO_ARTI = URL_INICIO+HOST_NAME+"/"+BASE_DATOS_IMAGEN + UNICODE;

	public static String getServidor(){
		return URL_INICIO+HOST_NAME+"/"+BASE_DATOS + UNICODE;
	}
	
	public static String getServidorFotoArticulo(){
		return  URL_INICIO+HOST_NAME+"/"+BASE_DATOS_IMAGEN + UNICODE;
	}
	

}
