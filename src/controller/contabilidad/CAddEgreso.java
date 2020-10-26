package controller.contabilidad;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import controller.CPadre;
import dal.Concepto;
import dal.Egreso;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.contabilidad.MEgreso;
import validacion.Validacion;

public class CAddEgreso  extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private Label labelVerificacion;
    @FXML private JFXRadioButton radioButtonBoleta;
    @FXML private ToggleGroup tipoDocumento;
    @FXML private JFXRadioButton radioButtonTicket;
    @FXML private JFXRadioButton radioButtonFactura;
    @FXML private TextField textFieldSerie;
    @FXML private TextField textFieldIdConcepto;
    @FXML private TextArea textAreaDescripcion;
    @FXML private Button buttonAgregarConcepto;
    @FXML private DatePicker datePickerFechaEmision;
    @FXML private TextField textFieldNumero;
    @FXML private TextField textFieldRuc;
    @FXML private TextField textFieldMonto;
    @FXML private TextField textFieldNombreRazonSocial;
    @FXML private TextArea textAreaComentario;
    
    
    MEgreso mEgreso= new MEgreso();
    Egreso egreso = new Egreso();
    Concepto concepto=new Concepto();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		agregarRetricciones();
				
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
		
		buttonAgregarConcepto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {				
				String url = "/view/contabilidad/AddConcepto.fxml";
				String css = "/estilocss/EstiloModal.css";
					
				CPadre Rconcepto = getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				concepto = (Concepto)Rconcepto.getObjeto();
				egreso.setEgreIdConcepto(concepto.getConId());
				textAreaDescripcion.setText(concepto.getConConcepto());
				textFieldMonto.setText(String.valueOf(concepto.getConMonto()));
			}
		});
		
		radioButtonBoleta.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				textFieldRuc.setDisable(true);
				textFieldSerie.setDisable(false);
				textFieldNumero.setDisable(false);
			}
		});
		
		radioButtonFactura.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				textFieldSerie.setDisable(false);
				textFieldRuc.setDisable(false);
				textFieldNumero.setDisable(false);
			}
		});
		
		radioButtonTicket.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				textFieldSerie.setDisable(true);
				textFieldRuc.setDisable(true);
				textFieldNumero.setDisable(false);
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
		mEgreso.iniciarConexionServidor();
		mEgreso.insertarEgreso(this.egreso);
		mEgreso.cerrarConexionServidor();
		if(mEgreso.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Egreso "+mEgreso.getMensaje(), mEgreso.getNoError());
			return true;
		}
		return false;
	}
	
	public boolean actualizar(Event event){
		mEgreso.iniciarConexionServidor();
		mEgreso.actualizarEgreso(this.egreso);;
		mEgreso.cerrarConexionServidor();
		if(mEgreso.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			mostrarNotificacion("Datos de Egreso "+mEgreso.getMensaje(), mEgreso.getNoError());
			return true;
		}
		return false;
	}
	
	public boolean validar(){		
		if(!radioButtonBoleta.isSelected() && !radioButtonFactura.isSelected() && !radioButtonTicket.isSelected()){
			mostrarInformacion(labelVerificacion, "Seleccione Tipo de Documento Campo Obligatorio.", 0);
			radioButtonBoleta.requestFocus();
			return false;
		}
		if (textFieldNombreRazonSocial.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Nombre o Razón Social esta vacío Campo Obligatorio.", 0);
			textFieldNombreRazonSocial.requestFocus();
			return false;
		}
		if (radioButtonBoleta.isSelected()) {
			if(textFieldSerie.getText().isEmpty()){
				mostrarInformacion(labelVerificacion, "Serie esta vacío Campo Obligatorio.", 0);
				textFieldSerie.requestFocus();
				return false;
			}else if (textFieldNumero.getText().isEmpty()) {
				mostrarInformacion(labelVerificacion, "Número esta vacío Campo Obligatorio.", 0);
				textFieldNumero.requestFocus();
				return false;
			}
			this.egreso.setEgreTipoDoc(1);
			this.egreso.setEgreSerieDoc(textFieldSerie.getText());
			this.egreso.setEgreNroDoc(textFieldNumero.getText());
			this.egreso.setEgreRuc(null);
			
		} else if (radioButtonFactura.isSelected()) {
			if(textFieldSerie.getText().isEmpty()){
				mostrarInformacion(labelVerificacion, "Serie esta vacío Campo Obligatorio.", 0);
				textFieldSerie.requestFocus();
				return false;
			}else if (textFieldNumero.getText().isEmpty()) {
				mostrarInformacion(labelVerificacion, "Número esta vacío Campo Obligatorio.", 0);
				textFieldNumero.requestFocus();
				return false;
			}else if (textFieldRuc.getText().isEmpty()) {
				mostrarInformacion(labelVerificacion, "Número R.U.C. esta vacío Campo Obligatorio.", 0);
				textFieldRuc.requestFocus();
				return false;
			}else if(textFieldRuc.getText().length()<11 || textFieldRuc.getText().length()>11){
				mostrarInformacion(labelVerificacion, "Ingrese Número de R.U.C válido (11 Digitos)", 0);
				textFieldRuc.requestFocus();
				return false;
			}
			this.egreso.setEgreTipoDoc(2);
			this.egreso.setEgreSerieDoc(textFieldSerie.getText());
			this.egreso.setEgreNroDoc(textFieldNumero.getText());
			this.egreso.setEgreRuc(textFieldRuc.getText());
		} else if(radioButtonTicket.isSelected()){
			if (textFieldNumero.getText().isEmpty()) {
				mostrarInformacion(labelVerificacion, "Número esta vacío Campo Obligatorio.", 0);
				textFieldNumero.requestFocus();
				return false;
			}
			this.egreso.setEgreTipoDoc(3);
			this.egreso.setEgreSerieDoc(null);
			this.egreso.setEgreNroDoc(textFieldNumero.getText());
			
		}
		this.egreso.setEgreNombreRazon(textFieldNombreRazonSocial.getText());
		
		if (datePickerFechaEmision.getValue()==null) {
			this.egreso.setEgreFechaEmision(null);
		}else{
			this.egreso.setEgreFechaEmision(java.sql.Date.valueOf(datePickerFechaEmision.getValue()));
		}
		if (textAreaDescripcion.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Agrega un Concepto Campo Obligatorio.", 0);
			buttonAgregarConcepto.requestFocus();
			return false;
		}
		if (textFieldMonto.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Monto del Concepto esta vacío Campo Obligatorio.", 0);
			textFieldMonto.requestFocus();
			return false;
		}
		this.egreso.setEgreMonto(textFieldMonto.getText());
		this.egreso.setEgreComentarios(textAreaComentario.getText());
		if (egreso.getEgreIdConcepto()==1 && getAccion()==CPadre.INSERT) {
			mostrarInformacion(labelVerificacion, "El concepto Pago por Recomendacion no esta permitido Agregar.", 0);
			buttonAgregarConcepto.requestFocus();
			return false;
		}
		return true;
	}
	
	public void seleccionarEgreso(int idEgreso){
		mEgreso.iniciarConexionServidor();
		egreso=mEgreso.seleccionarEgreso(idEgreso);
		mEgreso.cerrarConexionServidor();
		
		if (egreso.getEgreIdConcepto()!=1) {
			if(egreso.getEgreTipoDoc()==1){
				radioButtonBoleta.setSelected(true);
				textFieldSerie.setText(egreso.getEgreSerieDoc());
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldRuc.setDisable(true);
			}else if(egreso.getEgreTipoDoc()==2){
				radioButtonFactura.setSelected(true);
				textFieldSerie.setText(egreso.getEgreSerieDoc());
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldRuc.setText(egreso.getEgreRuc());
			}else if(egreso.getEgreTipoDoc()==3){
				radioButtonTicket.setSelected(true);
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldSerie.setDisable(true);
				textFieldRuc.setDisable(true);
			}
			textFieldNombreRazonSocial.setText(egreso.getEgreNombreRazon());
			if (egreso.getEgreFechaEmision()!=null) {
				datePickerFechaEmision.setValue(java.sql.Date.valueOf(egreso.getEgreFechaEmision().toString()).toLocalDate());
			}else{
				datePickerFechaEmision.setValue(null);
			}
			
			textAreaDescripcion.setText(egreso.getEgreDescripcion());
			textFieldMonto.setText(String.valueOf(egreso.getEgreMonto()));
			textAreaComentario.setText(egreso.getEgreComentarios());
			egreso.setEgreIdConcepto(egreso.getEgreIdConcepto());
		}else{
			textFieldNombreRazonSocial.setEditable(false);
			textFieldNumero.setEditable(false);
			datePickerFechaEmision.setEditable(false);
			buttonAgregarConcepto.setDisable(true);
			textFieldMonto.setEditable(false);
			radioButtonBoleta.setDisable(true);
			radioButtonFactura.setDisable(true);
			radioButtonTicket.setDisable(true);
			if(egreso.getEgreTipoDoc()==1){
				radioButtonBoleta.setSelected(true);
				textFieldSerie.setText(egreso.getEgreSerieDoc());
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldRuc.setDisable(true);
			}else if(egreso.getEgreTipoDoc()==2){
				radioButtonFactura.setSelected(true);
				textFieldSerie.setText(egreso.getEgreSerieDoc());
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldRuc.setText(egreso.getEgreRuc());
			}else if(egreso.getEgreTipoDoc()==3){
				radioButtonTicket.setSelected(true);
				textFieldNumero.setText(egreso.getEgreNroDoc());
				textFieldSerie.setDisable(true);
				textFieldRuc.setDisable(true);
			}
			textFieldNombreRazonSocial.setText(egreso.getEgreNombreRazon());
			if (egreso.getEgreFechaEmision()!=null) {
				datePickerFechaEmision.setValue(java.sql.Date.valueOf(egreso.getEgreFechaEmision().toString()).toLocalDate());
			}else{
				datePickerFechaEmision.setValue(null);
			}
			
			textAreaDescripcion.setText(egreso.getEgreDescripcion());
			textFieldMonto.setText(String.valueOf(egreso.getEgreMonto()));
			textAreaComentario.setText(egreso.getEgreComentarios());
			egreso.setEgreIdConcepto(egreso.getEgreIdConcepto());
		}
		
		if(egreso.getCreadoPor()!=null){
			labelCreadoPor.setText(egreso.getCreadoPor());
		}else{
			labelCreadoPor.setText("-");			
		}
		if(egreso.getfCreacion()!=null){
			labelFcreacion.setText(egreso.getfCreacion().toString());
		}else{
			labelFcreacion.setText("-");
		}
		if(egreso.getModificadoPor()!=null){
			labelModificadoPor.setText(egreso.getModificadoPor());
		}else{
			labelModificadoPor.setText("-");
		}
		if(egreso.getfModificacion()!=null){
			labelFModificacion.setText(egreso.getfModificacion().toString());
		}else{
			labelFModificacion.setText("-");
		}
	}
	
	private void agregarRetricciones(){
		textFieldSerie.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarSerie(4));
		textFieldNumero.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(8));
		textFieldRuc.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarNumero(11));
		textFieldMonto.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarPrecio(2));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if (CPadre.INSERT==tipoModal) {
			
		}else if (CPadre.UPDATE==tipoModal) {
			seleccionarEgreso((int) objeto);
		}
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
}
