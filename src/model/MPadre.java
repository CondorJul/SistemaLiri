package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import funciones.Funciones;
import funciones.Servidor;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class MPadre {
	public static final String MEN_INSERT_CORRECTO="La Inserción se realizo Correctamente";
	public static final String MEN_UPDATE_CORRECTO="La Actualización se realizo correctamente";
	public static final String MEN_DELETE_CORRECTO="Se eliminó correctamente";
	public static final String MEN_INSERT_INCORRECTO="Fallo la insercion de datos";
	public static final String MEN_UPDATE_INCORRECTO="Fallo la actualización de datos";
	public static final String MEN_DELETE_INCORRECTO="Surgio un fallo al eliminar este registro";
	public static final int CORRECTO=1;
	public static final int INCORRECTO=0;
	protected int noError=0;/*0=ERROR y 1 =exito*/
	protected String mensaje=null;
	protected Connection conexionServidor=null;
	
	public boolean iniciarConexionServidor(){
		boolean bool=false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionServidor = DriverManager.getConnection(Servidor.getServidor(), Servidor.USER, Servidor.PASS);
			bool=true;
		} catch (Exception e) {
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al conectar!!!");
            error_alert.setHeaderText("No se pudo conectar con el servidor.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
			cerrarConexionServidor();

		} 
		return bool;
	}
	
	public boolean inicializarCommit(){
		boolean bool=false;
		try {
			conexionServidor.setAutoCommit(false);
			 bool=true;
		} catch (SQLException e) {
			 bool=false;
			cerrarConexionServidor();
			e.printStackTrace();
		}
		return bool;
	}
	
	
	public boolean validarCambiosServidor(){
		boolean bool=false;
		try {
			conexionServidor.commit();
			cerrarConexionServidor();
		} catch (SQLException e) {
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al validar!!!");
            error_alert.setHeaderText("Surgio un error al intentar validar los datos.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
			cerrarConexionServidor();
			
		}
		return bool;
	}
	
	public boolean cerrarConexionServidor(){
		boolean bool=false;
		try {
			if (conexionServidor != null) {
				conexionServidor.close();
			}
			bool=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bool;
	}

	public int getNoError() {
		return noError;
	}

	public void setNoError(int noError) {
		this.noError = noError;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Connection getConexionServidor() {
		return conexionServidor;
	}

	public void setConexionServidor(Connection conexionServidor) {
		this.conexionServidor = conexionServidor;
	}
	
	
	
	public class ExcepcionMPadre extends Exception {
	    public ExcepcionMPadre(String mensaje) {
	        super(mensaje);
	    }
	}
}
