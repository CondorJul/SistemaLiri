package controller.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.MPadre;
import model.usuario.MUsuario;
import validacion.Validacion;

public class CAddUsuario extends CPadre implements Initializable{
	MUsuario mUsuario=new MUsuario();
	Usuario usuario=null;
	
	@FXML
    private Button buttonCerrar;

    @FXML
    private JFXButton jfxButtonCancelar;

    @FXML
    private JFXButton jfxButtonAgregar;

    @FXML
    private Label labelVerificacion;

    @FXML
    private TextField textFieldClave;

    @FXML
    private JFXCheckBox jFXCheckBoxActivo;

    @FXML
    private JFXCheckBox jFXCheckBoxInactivo;

    @FXML
    private TextArea textAreaComentario;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldNomApell;

    @FXML
    private TextField textFieldDireccion;

    @FXML
    private TextField textFieldEmail;
    
    @FXML
    private ImageView imageViewFoto;

    @FXML
    private TextField textFieldTelefono;

    @FXML
    private TextField textFieldCargo;

    @FXML
    private TextField textFieldSalario;
    
    @FXML
    private Hyperlink hyperlinkSubirFoto;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		agregarRestricciones();
		
		jFXCheckBoxActivo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxActivo.setSelected(true);
				jFXCheckBoxInactivo.setSelected(false);
				
			}
		});
		
		jFXCheckBoxInactivo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jFXCheckBoxInactivo.setSelected(true);
				jFXCheckBoxActivo.setSelected(false);
				
			}
		});
		
		hyperlinkSubirFoto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Image image=seleccionarImage();
				if(image!=null){
					imageViewFoto.setImage(image);
				}
			

			}
		});
		
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(getAccion()==CPadre.INSERT){
					insertarUsuario();
					((Stage)((Node)event.getSource()).getScene().getWindow()).close();
					mostrarNotificacion(mUsuario.getMensaje(), mUsuario.getNoError());
				}else if(getAccion()==CPadre.UPDATE){
					actualizarUsuario();
					((Stage)((Node)event.getSource()).getScene().getWindow()).close();
					mostrarNotificacion(mUsuario.getMensaje(), mUsuario.getNoError());
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

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		setAccion(tipoModal);
		if(tipoModal==CPadre.UPDATE){
			String dni=(String)objeto;
			enableFieldsUPD();
			mostrarUsuario(seleccionarUsuario(dni));
			
		}
		
		//if()
		//System.out.println((String)objeto);
		
	}
	
	
	
	
	
	
	private void agregarRestricciones(){
		textFieldDNI.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(8));
		//	textFieldNomApell.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarLetras());
		textFieldTelefono.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(9));
	}
	
	public boolean insertarUsuario(){
		this.usuario=new Usuario(textFieldDNI.getText(), textFieldNomApell.getText(),
				textFieldDireccion.getText(), textFieldEmail.getText(), textFieldTelefono.getText(),
				textFieldCargo.getText(), encriptar(textFieldClave.getText()),
				textAreaComentario.getText(), Double.parseDouble((textFieldSalario.getText()!=null && !textFieldSalario.getText().trim().isEmpty())?textFieldSalario.getText():"-1.0"), imageViewFoto.getImage(), (jFXCheckBoxActivo.isSelected())?1:(jFXCheckBoxInactivo.isSelected())?0:-1);
		mUsuario.iniciarConexionServidor();
		mUsuario.insertarUsuario(this.usuario);
		mUsuario.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, mUsuario.getMensaje(), mUsuario.getNoError());
		return true;
	} 
	
	public boolean actualizarUsuario(){
		
		this.usuario=new Usuario(textFieldDNI.getText(), textFieldNomApell.getText(),
				textFieldDireccion.getText(), textFieldEmail.getText(), textFieldTelefono.getText(),
				textFieldCargo.getText(), encriptar(textFieldClave.getText()),
				textAreaComentario.getText(), Double.parseDouble((textFieldSalario.getText()!=null && !textFieldSalario.getText().trim().isEmpty())?textFieldSalario.getText():"-1.0"), imageViewFoto.getImage(), (jFXCheckBoxActivo.isSelected())?1:(jFXCheckBoxInactivo.isSelected())?0:-1);
		mUsuario.iniciarConexionServidor();
		mUsuario.actualizarUsuario(this.usuario);
		mUsuario.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, mUsuario.getMensaje(), mUsuario.getNoError());
		return true;
	}

	@Override
	public Object getObjeto() {
		
		return usuario;
	}
	
	public void enableFieldsUPD(){
		textFieldDNI.setDisable(true);
		textFieldClave.setDisable(true);
	}
	public void mostrarUsuario(Usuario usuario){
		textFieldDNI.setText(usuario.getDni());
		textFieldNomApell.setText(usuario.getNomApell());
		textFieldDireccion.setText(usuario.getDireccion());
		textFieldEmail.setText(usuario.getEmail());
		textFieldTelefono.setText(usuario.getTelefono());
		textFieldCargo.setText(usuario.getCargo());
		textFieldSalario.setText(String.valueOf(usuario.getSalario()));
		textFieldClave.setText("****");
		if(usuario.getEstado()==1){
			jFXCheckBoxActivo.setSelected(true);
			jFXCheckBoxInactivo.setSelected(false);
		}else if(usuario.getEstado()==0){
			jFXCheckBoxActivo.setSelected(false);
			jFXCheckBoxInactivo.setSelected(true);
			
		}
		
		textAreaComentario.setText(usuario.getComentarios());
		imageViewFoto.setImage(usuario.getFoto());
	}
	
	
	
	public Usuario seleccionarUsuario(String dni){
		MUsuario mUsuario=new MUsuario();
		Usuario usuario=null;
		mUsuario.iniciarConexionServidor();
		usuario=mUsuario.seleccionarUsuario(dni);
		mUsuario.cerrarConexionServidor();
		return usuario;	
	}
	
	public boolean actualizarUsuario(Usuario usuario){
		boolean estado=false;
		MUsuario mUsuario=new MUsuario();
		mUsuario.iniciarConexionServidor();
		mUsuario.actualizarUsuario(usuario);
		if(mUsuario.getNoError()==MPadre.CORRECTO){
			estado=true;
		}
		return estado;
	}
	

}
