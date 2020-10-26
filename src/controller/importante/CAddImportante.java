package controller.importante;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Importante;
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
import model.importante.MImportante;
import validacion.Validacion;

public class CAddImportante  extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private TextField textFieldCodigo;
    @FXML private TextArea textAreaDescripcion;
    @FXML private DatePicker datePickerFechaInicio;
    @FXML private DatePicker datePickerFechaFin;
    @FXML private TextField textFieldCantidad;
    @FXML private ComboBox<String> comboBoxTalla;
    @FXML private TextArea textAreaComentario;
    
    MImportante mImportante = new MImportante();
    Importante importante = new Importante();

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		agregarRectricciones();
		comboBoxTalla.getItems().addAll("Todos","0", "2","4","6","8","10","12","14","16","S","M", "L", "XL","XXL");
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
		mImportante.iniciarConexionServidor();
		mImportante.insertarImportante(this.importante);
		mImportante.cerrarConexionServidor();
		if(mImportante.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Importante "+mImportante.getMensaje(), mImportante.getNoError());
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mImportante.getMensaje(), mImportante.getNoError());
		}
		return false;
	}
	
	public boolean actualizar(Event event){
		mImportante.iniciarConexionServidor();
		mImportante.actualizarImportante(this.importante);;
		mImportante.cerrarConexionServidor();
		if(mImportante.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Importante "+mImportante.getMensaje(), mImportante.getNoError());
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mImportante.getMensaje(), mImportante.getNoError());
		}
		return false;
	}
	
	public boolean validar(){
		this.importante.setImpCodigo(textFieldCodigo.getText());
		if (textAreaDescripcion.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Descripción esta Vacío Campo Obligatorio.", 0);
			textAreaDescripcion.requestFocus();
			return false;
		}
		this.importante.setImpDescripcion(textAreaDescripcion.getText());
		if (datePickerFechaInicio.getValue()!=null) {
			this.importante.setImpFechaInicio(java.sql.Date.valueOf(datePickerFechaInicio.getValue()));
		}else{
			this.importante.setImpFechaInicio(null);
		}
		if (datePickerFechaFin.getValue()!=null) {
			this.importante.setImpFechaFin(java.sql.Date.valueOf(datePickerFechaFin.getValue()));
		}else{
			this.importante.setImpFechaFin(null);
		}
		this.importante.setImpTalla(comboBoxTalla.getSelectionModel().getSelectedItem());
		if (!textFieldCantidad.getText().isEmpty()) {
			this.importante.setImpCantidad(Integer.parseInt(textFieldCantidad.getText()));
		}else{
			this.importante.setImpCantidad(-1);
		}
		this.importante.setImpDemanda(1);
		this.importante.setImpComentario(textAreaComentario.getText());
		
		return true;
	}
	
	public void seleccionarImportante(int idImportante){
		mImportante.iniciarConexionServidor();
		importante=mImportante.seleccionarImportante(idImportante);
		mImportante.cerrarConexionServidor();
		
		textFieldCodigo.setText(importante.getImpCodigo());
		textAreaDescripcion.setText(importante.getImpDescripcion());
		if (importante.getImpFechaInicio()!=null) {
			datePickerFechaInicio.setValue(java.sql.Date.valueOf(importante.getImpFechaInicio().toString()).toLocalDate());
		}else{
			datePickerFechaInicio.setValue(null);
		}
		if (importante.getImpFechaFin()!=null) {
			datePickerFechaFin.setValue(java.sql.Date.valueOf(importante.getImpFechaFin().toString()).toLocalDate());
		}else{
			datePickerFechaFin.setValue(null);
		}
		
		comboBoxTalla.getSelectionModel().select(importante.getImpTalla());
		textFieldCantidad.setText(String.valueOf(importante.getImpCantidad()));
		textAreaComentario.setText(importante.getImpComentario());
		
		if(importante.getCreadoPor()!=null){
			labelCreadoPor.setText(importante.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(importante.getfCreacion()!=null){
			labelFcreacion.setText(importante.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(importante.getModificadoPor()!=null){
			labelModificadoPor.setText(importante.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(importante.getfModificacion()!=null){
			labelFModificacion.setText(importante.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
	}
	
	public void actualizarCodigoImportante(){
		new Thread(new Runnable() {
		    public void run() {
		        try {
		            while(true){
		               mImportante.iniciarConexionServidor();
		               textFieldCodigo.setText(mImportante.traerCodigoImportante());
		               datePickerFechaInicio.setValue(LocalDate.now());
		               mImportante.cerrarConexionServidor();
		               Thread.sleep(10000);
		            }

		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		}).start();
	}
	
	public void agregarRectricciones(){
		textFieldCantidad.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(12));
	}
	

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.INSERT==tipoModal) {
			actualizarCodigoImportante();
		}else if (CPadre.UPDATE==tipoModal) {
			seleccionarImportante((int) objeto);
		}
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
}
