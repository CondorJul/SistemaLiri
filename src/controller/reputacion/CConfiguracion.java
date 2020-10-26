package controller.reputacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Reputacion;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.reputacion.MReputacion;
import validacion.Validacion;

public class CConfiguracion extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private TextField textFieldVMaxRojo;
    @FXML private TextField textFieldVMaxAmbar;
    @FXML private TextField textFieldVMaxVerde;
    @FXML private JFXCheckBox jfxCheckBoxEditarIntervalo;
    
    MReputacion mReputacion = new MReputacion();
    Reputacion reputacion=  new Reputacion();
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		agregarRectricciones();
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (getAccion()==CPadre.UPDATE) {
					validar();
					actualizar();
				}
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}	
		});
		
		jfxCheckBoxEditarIntervalo.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (jfxCheckBoxEditarIntervalo.isSelected()) {
					textFieldVMaxRojo.setDisable(false);
					textFieldVMaxAmbar.setDisable(false);
					textFieldVMaxVerde.setDisable(false);
					jfxButtonAgregar.setDisable(false);
				} else {
					textFieldVMaxRojo.setDisable(true);
					textFieldVMaxAmbar.setDisable(true);
					textFieldVMaxVerde.setDisable(true);
					jfxButtonAgregar.setDisable(true);
				}
				
			}
		});
		
		jfxButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});
	
	}
	
	public boolean actualizar(){
		mReputacion.iniciarConexionServidor();
		mReputacion.actualizarReputacion(this.reputacion);
		mReputacion.cerrarConexionServidor();
		return true;
	}
		
	public void seleccionarReputacion(int idReputacion){
		mReputacion.iniciarConexionServidor();
		reputacion=mReputacion.seleccionarReputacion(idReputacion);
		mReputacion.cerrarConexionServidor();
		
		textFieldVMaxRojo.setText(String.valueOf(reputacion.getRepVMRojo()));
		textFieldVMaxAmbar.setText(String.valueOf(reputacion.getRepVMAmmbar()));
		textFieldVMaxVerde.setText(String.valueOf(reputacion.getRepVMVerde()));
		
		if(reputacion.getCreadoPor()!=null){
			labelCreadoPor.setText(reputacion.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(reputacion.getfCreacion()!=null){
			labelFcreacion.setText(reputacion.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(reputacion.getModificadoPor()!=null){
			labelModificadoPor.setText(reputacion.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(reputacion.getfModificacion()!=null){
			labelFModificacion.setText(reputacion.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
	
	}
	
	private boolean validar(){
		this.reputacion.setRepVMRojo(Integer.parseInt(textFieldVMaxRojo.getText()));
		this.reputacion.setRepVMAmmbar(Integer.parseInt(textFieldVMaxAmbar.getText()));
		this.reputacion.setRepVMVerde(Integer.parseInt(textFieldVMaxVerde.getText()));
		
		return false;
	}
	
	public void agregarRectricciones(){
		textFieldVMaxAmbar.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(2));
		textFieldVMaxRojo.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(2));
		textFieldVMaxVerde.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(2));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.UPDATE==tipoModal) {
			seleccionarReputacion((int) objeto);
		}
		
	}


	@Override
	public Object getObjeto() {
		
		return null;
	}

}
