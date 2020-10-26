package controller.proveedor;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import controller.CPadre;
import dal.Proveedor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.proveedor.MProveedor;
import validacion.Validacion;


public class CAddPoveedor extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
	@FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
  	@FXML private Label labelVerificacion;
    @FXML private TextField textFieldCodigo;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldArticulo;
    @FXML private TextField textFieldDireccion;
    @FXML private TextField textFieldCorreoElec;
	@FXML private TextArea textAreaComentario;
	
	MProveedor mProveedor = new MProveedor();
	Proveedor proveedor = new Proveedor();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		agregarRestricciones();
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (getAccion()==CPadre.INSERT) {
					if(validar()){
						agregar(event);
					}
				} else if (getAccion()==CPadre.UPDATE) {
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
	
	public boolean agregar(Event event){
		mProveedor.iniciarConexionServidor();
		mProveedor.insertarProveedor(this.proveedor);
		mProveedor.cerrarConexionServidor();
		if(mProveedor.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Proveedor "+mProveedor.getMensaje(), mProveedor.getNoError());
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mProveedor.getMensaje(), mProveedor.getNoError());
		}
		return false;
	}
	
	public boolean actualizar(Event event){
		mProveedor.iniciarConexionServidor();
		mProveedor.actualizarProveedor(this.proveedor);
		mProveedor.cerrarConexionServidor();
		if(mProveedor.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Proveedor "+mProveedor.getMensaje(), mProveedor.getNoError());
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mProveedor.getMensaje(), mProveedor.getNoError());
		}
		return false;
	}
	
	public boolean validar(){
		this.proveedor.setCodigo(textFieldCodigo.getText());
		this.proveedor.setTelefono(textFieldTelefono.getText());
		if (textFieldNombre.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Nombre / Razón Social esta Vacío Campo Obligatorio.", 0);
			textFieldNombre.requestFocus();
			return false;
		}
		this.proveedor.setNombre(textFieldNombre.getText());
		if (textFieldArticulo.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Descripcion de Artículo esta Vacío Campo Obligatorio.", 0);
			textFieldArticulo.requestFocus();
			return false;
		}
		this.proveedor.setArticulo(textFieldArticulo.getText());
		this.proveedor.setDireccion(textFieldDireccion.getText());
		this.proveedor.setEmail(textFieldCorreoElec.getText());
		this.proveedor.setComentario(textAreaComentario.getText());
		
		return true;
	}
	
	public void seleccionarProveedor(int idProveedor){
		mProveedor.iniciarConexionServidor();
		proveedor=mProveedor.seleccionarProveedor(idProveedor);
		mProveedor.cerrarConexionServidor();
		
		textFieldCodigo.setText(proveedor.getCodigo());
		textFieldTelefono.setText(proveedor.getTelefono());
		textFieldNombre.setText(proveedor.getNombre());
		textFieldArticulo.setText(proveedor.getArticulo());
		textFieldDireccion.setText(proveedor.getDireccion());
		textFieldCorreoElec.setText(proveedor.getEmail());
		textAreaComentario.setText(proveedor.getComentario());
		
		if(proveedor.getCreadoPor()!=null){
			labelCreadoPor.setText(proveedor.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(proveedor.getfCreacion()!=null){
			labelFcreacion.setText(proveedor.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(proveedor.getModificadoPor()!=null){
			labelModificadoPor.setText(proveedor.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(proveedor.getfModificacion()!=null){
			labelFModificacion.setText(proveedor.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
		
	}
	
	public void actualizarCodigoProveedor(){
		new Thread(new Runnable() {
		    public void run() {
		        try {
		            while(true){
		               mProveedor.iniciarConexionServidor();
		               textFieldCodigo.setText(mProveedor.traerCodigoProveedor());
		               mProveedor.cerrarConexionServidor();
		               Thread.sleep(10000);
		            }

		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		}).start();
	}
	
	public void agregarRestricciones(){
		textFieldTelefono.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(9));
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.INSERT==tipoModal) {
			actualizarCodigoProveedor();
		} else if(CPadre.UPDATE==tipoModal){
			seleccionarProveedor((int)objeto);
		}
	}

	@Override
	public Object getObjeto() {
		return null;
	}


}
