package controller.perfil;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import funciones.Funciones;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.usuario.MUsuario;
import sesion.Sesion;

public class CCambiarContrasena extends CPadre implements Initializable {
	
    @FXML private PasswordField passwordFieldActualPass;
    @FXML private PasswordField passwordFieldNuevaPass;
    @FXML private PasswordField passwordFieldRepetirNuevaPass;
    @FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonModificar;
    @FXML private Label labelVerificacion;
    
    MUsuario mUsuario = new MUsuario();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		jfxButtonModificar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mUsuario.iniciarConexionServidor();
				String mensaje="";
				if (passwordFieldActualPass.getText().trim().isEmpty() || passwordFieldNuevaPass.getText().trim().isEmpty() || passwordFieldRepetirNuevaPass.getText().trim().isEmpty()) {
					mensaje="Rellene los campos requeridos";
				}else{
					if (mUsuario.seleccionarContrasena().equals(encriptar(passwordFieldActualPass.getText()))) {
						if (passwordFieldNuevaPass.getText().equals(passwordFieldRepetirNuevaPass.getText())) {
							
							mUsuario.actualizarDatosContrasena(encriptar(passwordFieldNuevaPass.getText()));
							//Sesion.CONTRASENA=encriptar(passwordFieldNuevaPass.getText());
							mensaje="Cambio exitoso";
							mostrarInformacion(labelVerificacion, mensaje, Funciones.CORRECTO);
							((Stage)((Node)event.getSource()).getScene().getWindow()).close();
							mostrarNotificacion(mUsuario.getMensaje(), mUsuario.getNoError());
							return;
						}else{
							mensaje="Las contraseñas no coinciden.";
						}
					}else{
						mensaje="La contraseña actual es incorrecta.";
					}
				}
				mostrarInformacion(labelVerificacion, mensaje, Funciones.INCORRECTO);
				
				mUsuario.cerrarConexionServidor();
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
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}
}
