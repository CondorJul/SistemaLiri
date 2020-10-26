package controller.reputacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Cliente;
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
import model.cliente.MCliente;
import validacion.Validacion;

public class CUdpReputacion extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private TextField textFieldCodigo;
    @FXML private TextField textFieldDni;
    @FXML private TextField textFieldApellNom;
    @FXML private TextField textFieldReputacion;
    
    MCliente mCliente = new MCliente();
    Cliente cliente=  new Cliente();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		agregarRestricciones();
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (getAccion()==CPadre.UPDATE) {
					if(validar()){
						actualizar(event);
					}
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
	
	
	public boolean actualizar(Event event){
		mCliente.iniciarConexionServidor();
		mCliente.actualizarReputacionCliente(this.cliente);
		mCliente.cerrarConexionServidor();
		
		if(mCliente.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Reputacion de Cliente "+mCliente.getMensaje(), mCliente.getNoError());
			return true;
		}
		return false;
	}
			
	public void seleccionarCliente(int idCliente){
		mCliente.iniciarConexionServidor();
		cliente=mCliente.seleccionarCliente(idCliente);
		mCliente.cerrarConexionServidor();

		textFieldCodigo.setText(cliente.getCli_codigo());
		textFieldDni.setText(cliente.getCli_dni());
		textFieldApellNom.setText(cliente.getCli_apellNom());
		textFieldReputacion.setText(String.valueOf(cliente.getCli_reputacion()));
		
		if(cliente.getCreadoPor()!=null){
			labelCreadoPor.setText(cliente.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(cliente.getfCreacion()!=null){
			labelFcreacion.setText(cliente.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(cliente.getModificadoPor()!=null){
			labelModificadoPor.setText(cliente.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(cliente.getfModificacion()!=null){
			labelFModificacion.setText(cliente.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
		
	
	}
	
	private boolean validar(){
		if (textFieldReputacion.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Reputación esta vacío Campo Obligatorio.", 0);
			textFieldReputacion.requestFocus();
			return false;
		}
		this.cliente.setCli_reputacion(Integer.parseInt(textFieldReputacion.getText()));
		return true;
	}
	
	public void agregarRestricciones(){
		textFieldReputacion.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(2));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.UPDATE==tipoModal) {
			seleccionarCliente((int)objeto);
		}
		
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}
}
