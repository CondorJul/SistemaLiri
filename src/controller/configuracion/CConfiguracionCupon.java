package controller.configuracion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Cupon;
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
import model.configuracion.MConfiguracion;
import model.recomendacion.MRecomendacion;
import validacion.Validacion;

public class CConfiguracionCupon extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private JFXCheckBox jfxCheckBoxEditarPorcentaje;
    @FXML private TextField textFieldPorcentajeCupon;
    @FXML private Label labelCreadoPor;
    @FXML private Label labelFcreacion;
    @FXML private Label labelModificadoPor;
    @FXML private Label labelFModificacion;
    
    MConfiguracion mConfiguracion = new MConfiguracion();
    Cupon cupon=  new Cupon();
        
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
		
		jfxCheckBoxEditarPorcentaje.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (jfxCheckBoxEditarPorcentaje.isSelected()) {
					textFieldPorcentajeCupon.setDisable(false);
					jfxButtonAgregar.setDisable(false);
				} else {
					textFieldPorcentajeCupon.setDisable(true);
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
		mConfiguracion.iniciarConexionServidor();
		mConfiguracion.actualizarCupon(this.cupon);
		mConfiguracion.cerrarConexionServidor();
		return true;
	}
		
	public void seleccionarCupon(int idCupon){
		mConfiguracion.iniciarConexionServidor();
		cupon = mConfiguracion.seleccionarCupon(idCupon);
		mConfiguracion.cerrarConexionServidor();
		
		textFieldPorcentajeCupon.setText(String.valueOf(cupon.getConfig_cupon()));	
	}
	
	private boolean validar(){
		this.cupon.setConfig_cupon(Double.parseDouble(textFieldPorcentajeCupon.getText()));		
		return false;
	}
	
	public void agregarRectricciones(){
		textFieldPorcentajeCupon.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(3));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.UPDATE==tipoModal) {
			seleccionarCupon((int) objeto);
		}
		
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
