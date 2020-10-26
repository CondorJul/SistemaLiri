package controller;

import funciones.Funciones;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public abstract class CPadre  extends Funciones{
	public static final int NULO=-1;
	public static final int INSERT=1;
	public static final int UPDATE=2;
	
	
	
	
	@FXML public Label labelCreadoPor;
    @FXML public Label labelFcreacion;
    @FXML public Label labelModificadoPor;
    @FXML public Label labelFModificacion; 
    
	private int accion=-1;
	public int getAccion() {
		return accion;
	}


	public void setAccion(int accion) {
		this.accion = accion;
	}


	public abstract void ejecutarAcciones(Object objeto, int tipoModal);
	
	
	public abstract Object getObjeto();
	
	public void mostrarAlerta(String title, String header, String contenido, Alert.AlertType tipoAlerta){
		Alert error_alert = new Alert(tipoAlerta);
        error_alert.setTitle(title);
        error_alert.setHeaderText(header);
        error_alert.setContentText(contenido);
        error_alert.initStyle(StageStyle.DECORATED);
        error_alert.showAndWait();      
	}
	
	public boolean consultarAlerta(String mensaje){
		Alert alert = new Alert(AlertType.CONFIRMATION, mensaje, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}
	
	public static  boolean ventanaEliminar(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea Eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}
}
