package controller.recomendacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Recomendacion;
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
import model.recomendacion.MRecomendacion;
import validacion.Validacion;

public class CConfiguracionRecomendacion extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private JFXCheckBox jfxCheckBoxEditarPorcentaje;
    @FXML private TextField textFieldPorcentajePago;
    @FXML private Label labelCreadoPor;
    @FXML private Label labelFcreacion;
    @FXML private Label labelModificadoPor;
    @FXML private Label labelFModificacion;
    
    MRecomendacion mRecomendacion = new MRecomendacion();
    Recomendacion recomendacion=  new Recomendacion();
        
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
				mostrarNotificacion(mRecomendacion.getMensaje(), mRecomendacion.getNoError());
			}	
		});
		
		jfxCheckBoxEditarPorcentaje.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (jfxCheckBoxEditarPorcentaje.isSelected()) {
					textFieldPorcentajePago.setDisable(false);
					jfxButtonAgregar.setDisable(false);
				} else {
					textFieldPorcentajePago.setDisable(true);
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
		mRecomendacion.iniciarConexionServidor();
		mRecomendacion.actualizarRecomendacion(this.recomendacion);
		mRecomendacion.cerrarConexionServidor();
		return true;
	}
		
	public void seleccionarRecomendacion(int idRecomendacion){
		mRecomendacion.iniciarConexionServidor();
		recomendacion = mRecomendacion.seleccionarRecomendacion(idRecomendacion);
		mRecomendacion.cerrarConexionServidor();
		
		textFieldPorcentajePago.setText(String.valueOf(recomendacion.getRecom_porcentajePago()));
		
		if(recomendacion.getCreadoPor()!=null){
			labelCreadoPor.setText(recomendacion.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(recomendacion.getfCreacion()!=null){
			labelFcreacion.setText(recomendacion.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(recomendacion.getModificadoPor()!=null){
			labelModificadoPor.setText(recomendacion.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(recomendacion.getfModificacion()!=null){
			labelFModificacion.setText(recomendacion.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
	
	}
	
	private boolean validar(){
		this.recomendacion.setRecom_porcentajePago(Integer.parseInt(textFieldPorcentajePago.getText()));		
		return false;
	}
	
	public void agregarRectricciones(){
		textFieldPorcentajePago.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(3));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.UPDATE==tipoModal) {
			seleccionarRecomendacion((int) objeto);
		}
		
	}


	@Override
	public Object getObjeto() {
		
		return null;
	}

}
