package controller.perfil;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Usuario;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.usuario.MUsuario;
import sesion.Sesion;

public class CPerfil extends CPadre implements Initializable{
	
	@FXML private JFXCheckBox jfxCheckBox;
    @FXML private TextField textFieldDni;
    @FXML private TextField textFieldApellidosNombres;
    @FXML private TextField textFieldDireccion;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldCorreo;
    @FXML private TextField textFieldCargo;
    @FXML private TextField textFieldSalario;
    @FXML private ImageView imageViewFotoPerfil;
    @FXML private Hyperlink hyperlinkSubirFoto;
    @FXML private Label labelEstado;
    @FXML private Hyperlink hyperlinkCambiarContraseña;
    @FXML private JFXButton jfxButtonGuardar;

    MUsuario mUsuario = new MUsuario();
    Usuario usuario = new Usuario();
    
	@Override
	public void initialize (URL arg0, ResourceBundle arg1) {
		
		mostrarDatosPerfil();
		
		jfxButtonGuardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				validar();
				actualizarDatosPerfil();
				mostrarNotificacion(mUsuario.getMensaje(), mUsuario.getNoError());
			}
		});
		
		hyperlinkCambiarContraseña.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				String url = "/view/perfil/CambiarContrasena.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.UPDATE);
				
				
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
			
			}
		});
		
		hyperlinkSubirFoto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Image image=seleccionarImage();
				if(image!=null){
					imageViewFotoPerfil.setImage(image);
				}
			}
		});
		
		jfxCheckBox.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				if(jfxCheckBox.isSelected()){
					textFieldApellidosNombres.setDisable(false);
					textFieldDireccion.setDisable(false);
					textFieldTelefono.setDisable(false);
					textFieldCorreo.setDisable(false);
					hyperlinkSubirFoto.setDisable(false);
					hyperlinkCambiarContraseña.setDisable(false);
					jfxButtonGuardar.setDisable(false);
				}else{
					textFieldApellidosNombres.setDisable(true);
					textFieldDireccion.setDisable(true);
					textFieldTelefono.setDisable(true);
					textFieldCorreo.setDisable(true);
					hyperlinkSubirFoto.setDisable(true);
					hyperlinkCambiarContraseña.setDisable(true);
					jfxButtonGuardar.setDisable(true);
				}
			}
		});

	}
	
	public boolean actualizarDatosPerfil(){
		mUsuario.iniciarConexionServidor();
		mUsuario.actualizarUsuario(this.usuario);
		mUsuario.cerrarConexionServidor();
		return true;
	}
	
	public boolean validar(){
		this.usuario.setNomApell(textFieldApellidosNombres.getText());
		this.usuario.setDireccion(textFieldDireccion.getText());
		this.usuario.setTelefono(textFieldTelefono.getText());
		this.usuario.setEmail(textFieldCorreo.getText());
		this.usuario.setFoto(imageViewFotoPerfil.getImage());
		
		return false;
	}

	public void mostrarDatosPerfil(){
		
		mUsuario.iniciarConexionServidor();
		usuario=mUsuario.seleccionarUsuario(Sesion.DNI);
		mUsuario.cerrarConexionServidor();
		
		textFieldDni.setText(usuario.getDni());
	    textFieldApellidosNombres.setText(usuario.getNomApell());
	    textFieldDireccion.setText(usuario.getDireccion());
	    textFieldTelefono.setText(usuario.getTelefono());
	    textFieldCorreo.setText(usuario.getEmail());
	    textFieldCargo.setText(usuario.getCargo());
	    textFieldSalario.setText(String.valueOf(usuario.getSalario()));
		imageViewFotoPerfil.setImage(usuario.getFoto());
		labelEstado.setText(usuario.getEstado_s());
		
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
}
