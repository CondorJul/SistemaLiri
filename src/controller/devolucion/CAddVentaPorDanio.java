package controller.devolucion;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import controller.CPadre;
import controller.alquiler.CSeeBoleta;
import controller.busqueda.CBusqueda;
import controller.cliente.CAddCliente;
import controller.reputacion.CProgress;
import ctreetablewiew.Unidad;
import dal.Boleta;
import dal.Busqueda;
import dal.Cliente;
import dal.DatoAtomico;
import dal.DetalleBoleta;
import dal.Reservacion;
import dal.TAlquilerHoy;
import dal.TBoletaAvalada;
import dal.TDetalleBoletaDevolucion;
import dal.TDetallePorPiezasDevolucion;
import dal.TDevolucion;
import funciones.Servidor;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.MPadre;
import model.alquiler.MBoleta;
import model.alquiler.MDetalleBoleta;
import model.alquiler.MReservacion;
import model.alquiler.MTAlquilerHoy;
import model.busqueda.MBusqueda;
import model.cliente.MCliente;
import model.datoAtomico.MDatoAtomico;
import model.devolucion.MDevolucion;
import model.devolucion.MTBoletaAvalada;
import model.devolucion.MTDetalleBoletaDevolucion;
import model.devolucion.MTDetallePorPiezasDevolucion;
import validacion.Validacion;

public  class CAddVentaPorDanio extends CPadre implements Initializable{
	private int intReputacionCliente=0;
	
	private MBoleta mBoleta=new MBoleta();
	private Boleta boleta=new Boleta();
	private Cliente cliente=new Cliente();
	
	private MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
	private DetalleBoleta detalleBoleta=new DetalleBoleta();
				
	private CProgress cProgressCliente=new CProgress();
   
    private ObservableList<DetalleBoleta> arrayDetalleBoletas=FXCollections.observableArrayList();

    
    @FXML
    private JFXTextField jFXTextFieldCodigo;

    @FXML
    private JFXTextField jFXTextFieldApellidosNombres;

    @FXML
    private JFXTextField jFXTextFieldDireccion;

    @FXML
    private JFXTextField jFXTextFieldReferencia;

    @FXML
    private JFXTextField jFXTextFieldDNI;

    @FXML
    private JFXTextField jFXTextFieldCorreo;

    @FXML
    private JFXTextField jFXTextFieldTelefono;

    @FXML
    private BorderPane borderPaneReputacion;

    @FXML
    private Label labelMensajeCliente;

    @FXML
    private TableView<DetalleBoleta> tableViewArticulos1;

    @FXML
    private TableColumn<DetalleBoleta,String> tableColumnCantArticulos1;

    @FXML
    private TableColumn<DetalleBoleta,String> tableColumnCodigoArticulos1;

    @FXML
    private TableColumn<DetalleBoleta,String> tableColumnDescripcionArticulos1;

    @FXML
    private TableColumn<DetalleBoleta,String> tableColumnPrecioArticulos1;

    @FXML
    private TableColumn<DetalleBoleta,String> tableColumnImporteArticulos1;

    @FXML
    private TextField textFieldSubTotal;

    @FXML
    private TextField textFieldDesCupones;

    @FXML
    private TextField textFieldDesAdicional;

    @FXML
    private TextField textFieldTotalPagar;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon1;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon2;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon3;

    @FXML
    private JFXRadioButton jFXRadioButtonDesNinguno;

    @FXML
    private ToggleGroup groupDesAdic;

    @FXML
    private JFXRadioButton jFXRadioButtonDesSoles;

    @FXML
    private JFXRadioButton jFXRadioButtonDesPor;

    @FXML
    private Label labelDescuentoEn;

    @FXML
    private Label labelDescuentoEnNota;

    @FXML
    private TextField textFiedlDesAdicMonPor;

    @FXML
    private JFXButton jFXButtonReservar;

    @FXML
    private JFXButton jFXButtonEfectuarOperacion;

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	
	}

	@Override
	public Object getObjeto() {
		return null;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarCamposTableDetalleBoleta();
		
		//eventos
		jFXRadioButtonDesNinguno.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				boleta.setDesAdicionalAc(0);
				textFiedlDesAdicMonPor.setText("0");
				textFiedlDesAdicMonPor.setDisable(true);
				labelDescuentoEn.setText("");
				labelDescuentoEnNota.setText("");
				
				if(actualizarBoletaDescuentos()){
					cargarPreciosCalculados();
				}
				
				
			}
		});
		
		jFXRadioButtonDesSoles.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boleta.setDesAdicionalAc(1);
				labelDescuentoEn.setText("Descuento en (S/)");
				textFiedlDesAdicMonPor.setDisable(false);
				labelDescuentoEnNota.setText("");
				boleta.setDesAdiSoles(Double.parseDouble(textFiedlDesAdicMonPor.getText()));
				if(actualizarBoletaDescuentos()){
					cargarPreciosCalculados();
				}
			}
		});
		
		jFXRadioButtonDesPor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boleta.setDesAdicionalAc(2);
				labelDescuentoEn.setText("Descuento en (%)");
				textFiedlDesAdicMonPor.setDisable(false);
				labelDescuentoEnNota.setText("Descuento % (1-100)");
				boleta.setDesAdiPor(Double.parseDouble(textFiedlDesAdicMonPor.getText()));
				if(actualizarBoletaDescuentos()){
					cargarPreciosCalculados();
				}
			}
		});
		
		textFiedlDesAdicMonPor.setOnKeyTyped(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				if(!Character.isDigit(arg0.getCharacter().charAt(0))){
					arg0.consume();
				}
			}
			
		});
		textFiedlDesAdicMonPor.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				
				if(event.getCode()==KeyCode.ENTER){
					
					actualizarDescuento();	
				}
			}
		});
		
		textFiedlDesAdicMonPor.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue){
					actualizarDescuento();
					
				}
			}
		});
		
		
		
	
		
	}

	
	private void actualizarDescuento(){
		if(jFXRadioButtonDesPor.isSelected()){
			boleta.setDesAdiPor(Double.parseDouble(textFiedlDesAdicMonPor.getText()));;
		}else if(jFXRadioButtonDesSoles.isSelected()){
			boleta.setDesAdiSoles(Double.parseDouble(textFiedlDesAdicMonPor.getText()));
		}
		if(actualizarBoletaDescuentos()){
			cargarPreciosCalculados();
		}
	}
	
	private void inicializarCamposTableDetalleBoleta(){
		tableColumnCantArticulos1.setCellValueFactory(new PropertyValueFactory<>("cantEntero"));
		tableColumnCodigoArticulos1.setCellValueFactory(new PropertyValueFactory<>("codigo"));	
		tableColumnDescripcionArticulos1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnPrecioArticulos1.setCellValueFactory(new PropertyValueFactory<>("precioUnit"));
		tableColumnImporteArticulos1.setCellValueFactory(new PropertyValueFactory<>("importe"));
		
	}
	
	public void extraerDatosDeBusquedaCliente(int id){
		 MCliente mCliente=new MCliente();
		 mCliente.iniciarConexionServidor();
		 cliente=mCliente.seleccionarCliente(id);
		 mCliente.cerrarConexionServidor();
		if(cliente!=null){
			 boleta.setIdCliente(cliente.getCli_id());
			 jFXTextFieldCodigo.setText(cliente.getCli_codigo());
			 jFXTextFieldDNI.setText(cliente.getCli_dni());
			 jFXTextFieldApellidosNombres.setText(cliente.getCli_apellNom());
			 jFXTextFieldDireccion.setText(cliente.getCli_direccion());
			 jFXTextFieldReferencia.setText(cliente.getCli_referencia());
			 jFXTextFieldCorreo.setText(cliente.getCli_email());
			 jFXTextFieldTelefono.setText(cliente.getCli_telefono());
			 cProgressCliente.modelarProgress(cliente.getCli_reputacion());
			 intReputacionCliente=cliente.getCli_reputacion();

			 //
			 if(cliente.getCli_reputacion()<=CProgress.VMROJO){
				 labelMensajeCliente.setTextFill(Color.web(CProgress.COLOR_ROJO));
				 labelMensajeCliente.setText("El cliente seleccionado tiene una reputación baja. ");
				 
			 }else{
				 labelMensajeCliente.setTextFill(Color.web("#000"));
				 labelMensajeCliente.setText("Cliente apto para esta transacción.");
			 }
		}
	}
	public void extraerDatosBoletaPendienteCarritoAlquilerCostos(){
		jFXCheckBoxCupon1.setSelected(boleta.isCupon1());
		jFXCheckBoxCupon2.setSelected(boleta.isCupon2());
		jFXCheckBoxCupon3.setSelected(boleta.isCupon3());
		
		
	}
	
	private void cargarPreciosCalculados(){
		Boleta boleta=new Boleta();
		mBoleta.iniciarConexionServidor();
		boleta=mBoleta.selecccionarPreciosCalculados(this.boleta.getId());
		mBoleta.cerrarConexionServidor();
  
		textFieldSubTotal.setText(boleta.getSubTotal());
		textFieldDesCupones.setText(boleta.getDesCupones());
		textFieldDesAdicional.setText(boleta.getDesAdicional());
		textFieldTotalPagar.setText(boleta.getTotalPagar());
		
	}
	
	public boolean actualizarBoletaDescuentos(){
		mBoleta.iniciarConexionServidor();
		mBoleta.actualizarBoletaDescuentos(this.boleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==CPadre.CORRECTO){
			return true;
		}
		
		return false;
	}
	
	public void cargarDetallePendiente(int idBoleta){
		MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
		mDetalleBoleta.iniciarConexionServidor();
		ObservableList<DetalleBoleta> arrayDetalleBoleta=mDetalleBoleta.seleccionarDetalleBoletaPorBoleta(idBoleta);
		mDetalleBoleta.cerrarConexionServidor();
		tableViewArticulos1.setItems(arrayDetalleBoleta);
		tableViewArticulos1.refresh();
	}
	
}
