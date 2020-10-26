package controller.cliente;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.cliente.MCliente;
import validacion.Validacion;

public class CAddCliente extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private TextField textFieldCodigo;
    @FXML private ComboBox<String> comboBoxTipoCliente;
    @FXML private TextField textFieldDniRuc;
    @FXML private TextField textFieldApellNom;
    @FXML private DatePicker datePickerFechaNac;
    @FXML private TextField textFieldTelefono;
    @FXML private TextField textFieldDireccion;
    @FXML private TextField textFieldReferencia;
    @FXML private TextField textFieldCorreo;
    @FXML private TextArea textAreaComentario;
    
    MCliente mCliente = new MCliente();
    Cliente cliente =  new Cliente();
           
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		agregarRestricciones();
		comboBoxTipoCliente.getItems().addAll("Cliente Natural", "Cliente Jurídica");
		
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (getAccion()==CPadre.INSERT) {
					if(validar()){
						agregar(event);
					}
				}else if (getAccion()==CPadre.UPDATE) {
					if(validar()){
						actualizar(event);
					}
				}
			}
		});
		
		comboBoxTipoCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (comboBoxTipoCliente.getSelectionModel().getSelectedItem().toString().equals("Cliente Natural")) {
					datePickerFechaNac.setDisable(false);
				} else {
					datePickerFechaNac.setDisable(true);
				}
			}
		});
				
		jfxButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cliente=null;
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cliente=null;
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});		
	
	}
	
	
	
	private boolean agregar(Event event){
		mCliente.iniciarConexionServidor();
		mCliente.agregarCliente(this.cliente);
		mCliente.cerrarConexionServidor();
		if(mCliente.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Cliente "+mCliente.getMensaje(), mCliente.getNoError());
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mCliente.getMensaje(), mCliente.getNoError());
		}
		return false;
	}
	
	public boolean actualizar(Event event){
		mCliente.iniciarConexionServidor();
		mCliente.actualizarCliente(this.cliente);
		mCliente.cerrarConexionServidor();
		if(mCliente.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Cliente "+mCliente.getMensaje(), mCliente.getNoError());
			return true;
		}
		return false;
	}
		
	public void actualizarCodigoCliente(){
		new Thread(new Runnable() {
		    public void run() {
		        try {
		            while(true){
		               mCliente.iniciarConexionServidor();
		               textFieldCodigo.setText(mCliente.traerCodigoCliente());
		               mCliente.cerrarConexionServidor();
		               Thread.sleep(10000);
		            }

		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		}).start();
	}
	
	public boolean traerReputacion(){
		mCliente.iniciarConexionServidor();
		this.cliente.setCli_reputacion(mCliente.traerReputacion());
		mCliente.cerrarConexionServidor();
		return false;
	}
	
	public void seleccionarCliente(int idCliente){
		mCliente.iniciarConexionServidor();
		cliente=mCliente.seleccionarCliente(idCliente);
		mCliente.cerrarConexionServidor();
		
		if (cliente.getCli_tipoCliente().equals("Cliente Natural")) {
			textFieldDniRuc.setText(cliente.getCli_dni());
		} else {
			textFieldDniRuc.setText(cliente.getCli_dni());
			datePickerFechaNac.setDisable(true);
		}
		comboBoxTipoCliente.getSelectionModel().select(cliente.getCli_tipoCliente());
		textFieldCodigo.setText(cliente.getCli_codigo());
		textFieldApellNom.setText(cliente.getCli_apellNom());
		if (cliente.getCli_fechNac()!=null) {
			datePickerFechaNac.setValue(java.sql.Date.valueOf(cliente.getCli_fechNac().toString()).toLocalDate());
		}else{
			datePickerFechaNac.setValue(null);
		}
		textFieldTelefono.setText(cliente.getCli_telefono());	
		textFieldDireccion.setText(cliente.getCli_direccion());
		textFieldReferencia.setText(cliente.getCli_referencia());
		textFieldCorreo.setText(cliente.getCli_email());
		textAreaComentario.setText(cliente.getCli_comentarios());
		
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
		this.cliente.setCli_codigo(textFieldCodigo.getText());
		if (comboBoxTipoCliente.getSelectionModel().getSelectedItem()==null) {
			mostrarInformacion(labelVerificacion, "Tipo de Cliente esta Vacío Campo Obligatorio.", 0);
			comboBoxTipoCliente.requestFocus();
			return false;
		}
		this.cliente.setCli_tipoCliente(comboBoxTipoCliente.getSelectionModel().getSelectedItem());
		if (textFieldDniRuc.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Número de DNI/R.U.C. esta Vacío Campo Obligatorio.", 0);
			textFieldDniRuc.requestFocus();
			return false;
		}else if (comboBoxTipoCliente.getSelectionModel().getSelectedItem().equals("Cliente Natural")) {
			if (textFieldDniRuc.getText().length() < 8 || textFieldDniRuc.getText().length() > 8) {
				mostrarInformacion(labelVerificacion, "Ingrese Número de DNI válido (8 Digitos)", 0);
				textFieldDniRuc.requestFocus();
				return false;
			}
		}else if (comboBoxTipoCliente.getSelectionModel().getSelectedItem().equals("Cliente Jurídica") && textFieldDniRuc.getText().length() < 11) {
			if (textFieldDniRuc.getText().length() < 11 || textFieldDniRuc.getText().length() > 11) {
				mostrarInformacion(labelVerificacion, "Ingrese Número de R.U.C válido (11 Digitos)", 0);
				textFieldDniRuc.requestFocus();
				return false;
			}
		}
		this.cliente.setCli_dni(textFieldDniRuc.getText());
		if (textFieldApellNom.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Apellidos y Nombres esta Vacío Campo Obligatorio.", 0);
			textFieldApellNom.requestFocus();
			return false;
		}
		this.cliente.setCli_apellNom(textFieldApellNom.getText());
		if (datePickerFechaNac.isDisable() || datePickerFechaNac.getValue()==null) {
			this.cliente.setCli_fechNac(null);
		}else{
			this.cliente.setCli_fechNac(java.sql.Date.valueOf(datePickerFechaNac.getValue()));
		}
		this.cliente.setCli_telefono(textFieldTelefono.getText());
		this.cliente.setCli_direccion(textFieldDireccion.getText());
		this.cliente.setCli_referencia(textFieldReferencia.getText());
		if (textFieldReferencia.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Ingrese Referencia.", 0);
			textFieldReferencia.requestFocus();
			return false;
		}
		this.cliente.setCli_email(textFieldCorreo.getText());
		this.cliente.setCli_comentarios(textAreaComentario.getText());
		return true;
	}
	
	
	private void agregarRestricciones(){
		textFieldDniRuc.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(11));
		//textFieldApellNom.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarLetras());
		textFieldTelefono.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(9));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.INSERT==tipoModal) {
			actualizarCodigoCliente();
			traerReputacion();
		} else if(CPadre.UPDATE==tipoModal) {
			seleccionarCliente((int)objeto);
		}
				
	}

	@Override
	public Object getObjeto() {
		
		return cliente;
	}

}
