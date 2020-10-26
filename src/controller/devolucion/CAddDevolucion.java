package controller.devolucion;

import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import controller.CPadre;
import controller.alquiler.CSeeBoleta;
import controller.reputacion.CProgress;
import dal.Boleta;
import dal.Cliente;
import dal.DetalleBoleta;
import dal.TBoletaAvalada;
import dal.TDetalleBoletaDevolucion;
import dal.TDetallePorPiezasDevolucion;
import dal.TDevolucion;
import javafx.beans.InvalidationListener;
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
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.alquiler.MBoleta;
import model.alquiler.MDetalleBoleta;
import model.cliente.MCliente;
import model.devolucion.MDevolucion;
import model.devolucion.MTBoletaAvalada;
import model.devolucion.MTDetalleBoletaDevolucion;
import model.devolucion.MTDetallePorPiezasDevolucion;
import sesion.Sesion;
import validacion.Validacion;

public  class CAddDevolucion extends CPadre implements Initializable{


	@FXML 
	private Tab tabGarantia;
	
	@FXML 
	private Tab tabVentaDanio;

    @FXML
    private TableView<TBoletaAvalada> tableViewBoletasAvaladas;

    @FXML
    private TableColumn<TBoletaAvalada,String> tableColumnIDBoletasAvaladas;

    @FXML
    private TableColumn<TBoletaAvalada,String> tablecColumnSerieBoletasAvaladas;

    @FXML
    private TableColumn<TBoletaAvalada,String> tableColumnNumeroBoletasAvaladas;

    @FXML
    private TableColumn<TBoletaAvalada,String> tableColumnEstadoBoletasAvaladas;
    
    @FXML
    private JFXCheckBox jFXTCheckBoxBoletaAlqDevolucion;

  
    @FXML
    private Label labelBoletaAvaladasRojo;

    @FXML
    private JFXCheckBox jFXCheckBoxDniGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxLicenciaGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDineroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniMenorGarantia;

    @FXML
    private TextField textFieldNroDniGarantia;

    @FXML
    private TextField textFieldNroMenorGarantia;

    @FXML
    private TextField textFieldNroLicenciaGarantia;

    @FXML
    private TextField textFieldDineroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxOtroGarantia;

    @FXML
    private TextArea textAreaOtroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniDevolucion;

    @FXML
    private JFXCheckBox jFXCheckBoxOtroDevolucion;

    @FXML
    private JFXCheckBox jFXCheckBoxDineroDevolucion;

    @FXML
    private JFXCheckBox jFXCheckBoxLicenciaDevolucion;

    @FXML
    private JFXCheckBox jFXCheckBoxDniMenorDevolucion;

    @FXML
    private TextField textFieldNroBoletaGarantia;

    @FXML
    private JFXCheckBox jFXTCheckBoxBoletaAlqGarantia;

    @FXML
    private TextField textFieldSerieBoletaGarantia;

    @FXML
    private Button buttonGuardarIrBoletaDevolucion;

    @FXML
    private JFXButton jFXButtonGuadarGarantiaDevolucion;

    @FXML
    private BorderPane borderPaneReputacion;


	@FXML
	private Label labelSerie;
	
	@FXML
	private Label labelNumero;
	
	@FXML
	private Label labelSubTotal;
	
	@FXML
	private Label labelDesCupones;
	
	@FXML
	private Label labelDesAdicional;
	
	@FXML
	private Label labelDesImporteTotal;
	
    @FXML
    private Button buttonCerrar;

    @FXML
    private JFXButton jfxButtonCerrar;

    @FXML
    private JFXButton jfxButtonValidar;

    @FXML
    private Label labelVerificacion;
    
    @FXML
    private JFXButton jFXButtonGuadarEnBloque;

    @FXML
    private TableView<TDetallePorPiezasDevolucion> tableViewDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnNroDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnCodigoDevolucion;
    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnPiezaDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnPendienteDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnDevueltoDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnAlmacenDevolucion;
    
    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnEsperaDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnLavanderiaDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnReparacionDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnPlanchadoDevolucion;
    
    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnVentaDDevolucion;

    @FXML
    private TableColumn<TDetallePorPiezasDevolucion,String> tableColumnVenderDevolucion;

    @FXML
    private Label labelCodigoCliente;

    @FXML
    private Label lableDNICliente;

    @FXML
    private Label labelApellNombresCliente;

    @FXML 
    private GridPane   gridPaneGarantiaParte1;
    
    @FXML
    private BorderPane borderPaneGarantiaTipoBoleta;
    
    @FXML
    private JFXButton jFXButtonValidarDevolucionPertinencias;
    /*@FXML
    private ProgressBar progressBarReputacionCliente;*/
    private CProgress progressBarReputacionCliente=new CProgress();

    @FXML
    private Label labelDireccionCliente;

    @FXML
    private Label labelFechaEmision;

    @FXML
    private Label labelFechaEntrega;

    @FXML
    private Label labelFechaDevolucion;

    @FXML
    private TableView<TDetalleBoletaDevolucion> tableViewDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoletaDevolucion,String> tableColumnCantDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoletaDevolucion,String> tableColumnCodigoDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoletaDevolucion,String> tableColumnDescripcionDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoletaDevolucion,String> tableColumnEstadoDetalleBoleta;

    @FXML
    private ContextMenu contextMenuOpcDevolucion;

    @FXML 
    private JFXButton jFXButtonValidarDevCompletada;
    private MenuItem menuItemActualizar=new MenuItem("Actualizar");
    
    @FXML
    private Label labelTiempoDevolucion;
    private TDevolucion tDevolucion=null;

	//private ObservableList<TDetallePorPiezasDevolucion> arrayDevolucion=FXCollections.observableArrayList();
	
    
    /*Inicio de variables-Venta por daño*/
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

   // @FXML
   // private BorderPane borderPaneReputacion;

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
    
    @FXML
    private GridPane gridPaneDescuentos;

    /*Fin de variabe de variables-Venta por daño */
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarCamposTDetalleBoleta();
		inicializarCamposTDetallePorPiezasDevolucion();
		inicializarCamposTBoletaAvalada();
		
		tableViewDevolucion.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				tableViewDevolucion.refresh();
			}
		});
		tableViewDevolucion.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				tableViewDevolucion.refresh();
			}
		});
		
		menuItemActualizar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				tableViewDevolucion.refresh();				
			}
		});
		
		contextMenuOpcDevolucion.getItems().add(menuItemActualizar);
		//tableViewDevolucion.setItems(arrayDevolucion);
		tableViewDetalleBoleta.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getClickCount()==2){
					seleccionarTDetallePorPiezasDevolucion(tableViewDetalleBoleta.getSelectionModel().getSelectedItem().getId());

				}
				
			}
		});
		
		jFXButtonGuadarEnBloque.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				tableViewDevolucion.refresh();
				updTDetallePorPiezasDevolucion();
				seleccionarCantPendientePorBoleta(tDevolucion.getId());
			}
		});
		
		jfxButtonCerrar.setOnAction(new EventHandler<ActionEvent>() {
			
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
		
		jFXButtonGuadarGarantiaDevolucion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(actualizarBoletaGarantiaDevolucion(getFieldsGarantia())){
					mostrarInformacion(labelVerificacion, " Garantia: Actualización Correcta.",CPadre.CORRECTO);
					jFXButtonValidarDevolucionPertinencias.setDisable(!validarEntregaCompGarantias());
				}else{
					mostrarInformacion(labelVerificacion, "Garantia: No se efectuo la actualización .",CPadre.INCORRECTO);
				}
			}
		});
		
		
		jFXButtonValidarDevolucionPertinencias.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(actualizarBoletaDevGarantiaCompletada(tDevolucion.getId())){
					mostrarAlerta("Devolución", "", "Devolución garantia completada completada", AlertType.INFORMATION);					
					bloquearDevGarantiaCompletada();
					
				}
			}
		});
		jFXButtonValidarDevCompletada.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boolean completado=false;
				boolean estado=actualizarBoletaDevCompletada(tDevolucion.getId());
				int aptoCrearNuevaBoleta=verificarAptoCrearNuevaBoleta(tDevolucion.getId());
				int lastIdBoletaPorDanio=-1;
				if(estado){
					if(aptoCrearNuevaBoleta==1){
						lastIdBoletaPorDanio=nuevaBoletaPorDanio(tDevolucion.getId(), Sesion.DNI);
						if(lastIdBoletaPorDanio!=-1){
							bloquearDevCompletada();
							boleta.setId(lastIdBoletaPorDanio);
							cargarBoletaDanio();
							completado=true;						}
					}else{
						bloquearDevCompletada();
						tabGarantia.setDisable(false);

						completado=true;
					}
					/*
					 * if(boleta.getIdBoletaDanio()!=-1){
							cargarBoletaDanio();
						}else{
							tabGarantia.setDisable(false);
						}
					 * */
					//lastIdBoletaPorDanio=nuevaBoletaPorDanio(tDevolucion.getId(), Sesion.DNI);
				}
				
				if(completado){
					mostrarAlerta("Devolución", "", "Devolución completada.", AlertType.INFORMATION);
				}else {
					mostrarAlerta("Devolución", "", "Fallo validar esta devolución.", AlertType.WARNING);
				}
									
			}
		});
		
		
		/*fin de eventos-Venta por daño*/
		inicializarCamposTableDetalleBoleta();
		
		//eventos
		
		jFXCheckBoxCupon1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boleta.setCupon1(jFXCheckBoxCupon1.isSelected());
				if(actualizarBoletaDescuentos()){
					cargarPreciosCalculados();
				}
			}
		});
	
		jFXCheckBoxCupon2.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					boleta.setCupon2(jFXCheckBoxCupon2.isSelected());
					if(actualizarBoletaDescuentos()){
						cargarPreciosCalculados();
					}
				}
			});
	
		jFXCheckBoxCupon3.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boleta.setCupon3(jFXCheckBoxCupon3.isSelected());
				if(actualizarBoletaDescuentos()){
					cargarPreciosCalculados();
				}
			}
			
		});
			
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
		
		jFXButtonEfectuarOperacion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
			
				String url = "/view/alquiler/SeeBoleta.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CSeeBoleta.FACTURAR);					
				boolean estado=(boolean)cpadre.getObjeto();
				if(estado){
					cargarBoletaDanio();
				}
			}
		});
		
		/*inicio de eventos-Vemta por daño*/
		
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		if(tipoModal==CPadre.INSERT){
			 tDevolucion = (TDevolucion)objeto;
			//extraer Datyos del cliente
			Cliente cliente=seleccionarCliente(tDevolucion.getIdCliente());
			 /*Incio modificaciones-03/08/2019*/
			 /*los datos que capturo aqui se guarda en la boleta general de esta clase para poder usar como venta*/
			 Boleta boletaDanio=seleccionarDevArticulosGarantiaVentaDanio(tDevolucion.getId());
			 if(boletaDanio.getIdBoletaDanio()!=-1){
				 this.boleta.setId(boletaDanio.getIdBoletaDanio());
				 this.boleta.setIdCliente(tDevolucion.getIdCliente());
			 }
			 /*fin de modificaicones 03/08/2019*/
			
			labelCodigoCliente.setText(cliente.getCli_codigo());
			lableDNICliente.setText(cliente.getCli_dni());
			labelApellNombresCliente.setText(cliente.getCli_apellNom());
			labelDireccionCliente.setText(cliente.getCli_direccion());
			borderPaneReputacion.setCenter(progressBarReputacionCliente);
			progressBarReputacionCliente.modelarProgress(cliente.getCli_reputacion());
			
			//extraerDatos de la boleta
			labelSerie.setText(tDevolucion.getSerieBoleta());
			labelNumero.setText(tDevolucion.getNumeroBoleta());
			
			labelSubTotal.setText(tDevolucion.getSubTotal());
			labelDesCupones.setText(tDevolucion.getDesCupones());
			labelDesAdicional.setText(tDevolucion.getDesAdic());
			labelDesImporteTotal.setText(tDevolucion.getTotal());			
			
			labelFechaEmision.setText(tDevolucion.getFecha());
			labelFechaEntrega.setText(tDevolucion.getFEntrega());
			labelFechaDevolucion.setText(tDevolucion.getFDevolucion());
			
			if(Integer.parseInt(tDevolucion.getTiempoRetante())<0){
				labelTiempoDevolucion.setStyle("-fx-text-fill: red;");
				labelTiempoDevolucion.setText((Integer.parseInt(tDevolucion.getTiempoRetante())*-1)+" días después");
			}else if(Integer.parseInt(tDevolucion.getTiempoRetante())>0){
				labelTiempoDevolucion.setStyle("-fx-text-fill: blue;");
				labelTiempoDevolucion.setText(tDevolucion.getTiempoRetante()+" días antes");
			}else {
				labelTiempoDevolucion.setStyle("-fx-text-fill: blue;");
				labelTiempoDevolucion.setText("Entrega puntual");
			}
			
			
			
			//
			
			
			if(tDevolucion.getDevCompletada()==1){
				
				bloquearDevCompletada();
				/*verificamos y agregamos un resricciob mas*/
				if(boletaDanio.getIdBoletaDanio()!=-1){
					cargarBoletaDanio();
				}else{
					tabGarantia.setDisable(false);
					tabVentaDanio.setDisable(true);
				}
				
				/*Modificaciones añadidias a este modulo*/
				
			}
			seleccionarCantPendientePorBoleta(tDevolucion.getId());

			
			seleccionarTDetalleBoletaDevolucionPorBoleta(tDevolucion.getId());
			
			if(tDevolucion.getDevGarantiaCompletada()==1){
				bloquearDevGarantiaCompletada();
			}
			
			
			
			seleccionarTBoletaAvalada(tDevolucion.getId());
			
			Boleta boletaGarantia=selecccionarBoletaGarantiaAndDevolucionGarantia(tDevolucion.getId());
			
			jFXCheckBoxDniGarantia.setSelected(boletaGarantia.isGarDni());
			jFXCheckBoxDniGarantia.setDisable(!boletaGarantia.isGarDni());
			textFieldNroDniGarantia.setDisable(!boletaGarantia.isGarDni());
			textFieldNroDniGarantia.setText(boletaGarantia.getGarNroDni());
			jFXCheckBoxDniDevolucion.setDisable(!boletaGarantia.isGarDni());
			jFXCheckBoxDniDevolucion.setSelected(boletaGarantia.isDevDni());
			
			jFXCheckBoxDniMenorGarantia.setSelected(boletaGarantia.isGarDniMenor());
			jFXCheckBoxDniMenorGarantia.setDisable(!boletaGarantia.isGarDniMenor());
			textFieldNroMenorGarantia.setDisable(!boletaGarantia.isGarDniMenor());
			textFieldNroMenorGarantia.setText(boletaGarantia.getGarNroDniMenor());
			jFXCheckBoxDniMenorDevolucion.setDisable(!boletaGarantia.isGarDniMenor());
			jFXCheckBoxDniMenorDevolucion.setSelected(boletaGarantia.isDevDniMenor());
			
			
			jFXCheckBoxLicenciaGarantia.setSelected(boletaGarantia.isGarLicencia());
			jFXCheckBoxLicenciaGarantia.setDisable(!boletaGarantia.isGarLicencia());
			textFieldNroLicenciaGarantia.setDisable(!boletaGarantia.isGarLicencia());
			textFieldNroLicenciaGarantia.setText(boletaGarantia.getGarNroLicencia());
			jFXCheckBoxLicenciaDevolucion.setDisable(!boletaGarantia.isGarLicencia());
			jFXCheckBoxLicenciaDevolucion.setSelected(boletaGarantia.isDevLicencia());
			
			jFXCheckBoxDineroGarantia.setSelected(boletaGarantia.isGarDinero());
			jFXCheckBoxDineroGarantia.setDisable(!boletaGarantia.isGarDinero());
			textFieldDineroGarantia.setDisable(!boletaGarantia.isGarDinero());
			textFieldDineroGarantia.setText(Validacion.doubleAStringMoneda(boletaGarantia.getGarMonto()));
			jFXCheckBoxDineroDevolucion.setDisable(!boletaGarantia.isGarDinero());
			jFXCheckBoxDineroDevolucion.setSelected(boletaGarantia.isDevDinero());
			
			jFXCheckBoxOtroGarantia.setSelected(boletaGarantia.isGarOtro());
			jFXCheckBoxOtroGarantia.setDisable(!boletaGarantia.isGarOtro());
			textAreaOtroGarantia.setDisable(!boletaGarantia.isGarOtro());
			textAreaOtroGarantia.setText(boletaGarantia.getGarOtroEspecifique());
			jFXCheckBoxOtroDevolucion.setDisable(!boletaGarantia.isGarOtro());
			jFXCheckBoxOtroDevolucion.setSelected(boletaGarantia.isDevOtro());
			
			
			jFXTCheckBoxBoletaAlqGarantia.setSelected(boletaGarantia.isGarEnlazarBoleta());
			jFXTCheckBoxBoletaAlqGarantia.setDisable(!boletaGarantia.isGarEnlazarBoleta());
			textFieldSerieBoletaGarantia.setDisable(!boletaGarantia.isGarEnlazarBoleta());
			textFieldNroBoletaGarantia.setDisable(!boletaGarantia.isGarEnlazarBoleta());
			textFieldSerieBoletaGarantia.setText(boletaGarantia.getGarSerieBoleta());
			textFieldNroBoletaGarantia.setText(String.format("%08d", boletaGarantia.getGarNumeroBoleta()) );
			jFXTCheckBoxBoletaAlqDevolucion.setDisable(!boletaGarantia.isGarEnlazarBoleta());
			jFXTCheckBoxBoletaAlqDevolucion.setSelected(boletaGarantia.isDevEnlazarBoleta());
			jFXButtonValidarDevolucionPertinencias.setDisable(!validarEntregaCompGarantias());
			if(setDisableGarTipoEnlace()){
				labelBoletaAvaladasRojo.setVisible(true);
				borderPaneGarantiaTipoBoleta.setDisable(true);
				jFXButtonGuadarGarantiaDevolucion.setDisable(true);
				jFXButtonGuadarGarantiaDevolucion.setDisable(true);
			}
			
		}
		
	}
	

	@Override
	public Object getObjeto() {
		return null;
	}
	
	public void inicializarCamposTDetalleBoleta(){
		tableColumnCantDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("cant"));
		tableColumnCodigoDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnDescripcionDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnEstadoDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("estado"));

	}
	
	public void seleccionarTDetalleBoletaDevolucionPorBoleta(int idBoleta){
		MTDetalleBoletaDevolucion mTDetalleBoletaDevoluion =new MTDetalleBoletaDevolucion();
		mTDetalleBoletaDevoluion.iniciarConexionServidor();
		tableViewDetalleBoleta.setItems(mTDetalleBoletaDevoluion.seleccionarTDetalleBoletaDevolucioonPorBoleta(idBoleta));
		tableViewDetalleBoleta.refresh();
		mTDetalleBoletaDevoluion.cerrarConexionServidor();
	}
	
	
	public Cliente seleccionarCliente(int id){
		MCliente mCliente=new MCliente();
		mCliente.iniciarConexionServidor();
		Cliente cliente=mCliente.seleccionarCliente(id);
		mCliente.cerrarConexionServidor();
		
		return cliente;
	}
	
	public void seleccionarTDetallePorPiezasDevolucion(int idDetalleBoleta){
		
		MTDetallePorPiezasDevolucion mTDetallePorPiezasDevolucion=new MTDetallePorPiezasDevolucion();
		mTDetallePorPiezasDevolucion.iniciarConexionServidor();
		tableViewDevolucion.setItems(mTDetallePorPiezasDevolucion.seleccionarTDetallePorPiezasDevolucion(idDetalleBoleta));;
		tableViewDevolucion.refresh();
		mTDetallePorPiezasDevolucion.cerrarConexionServidor();
	}
	

	public void inicializarCamposTDetallePorPiezasDevolucion(){
		tableColumnNroDevolucion.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableColumnCodigoDevolucion.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnPiezaDevolucion.setCellValueFactory(new PropertyValueFactory<>("pieza"));
		tableColumnPendienteDevolucion.setCellValueFactory(new PropertyValueFactory<>("pendiente"));
		tableColumnDevueltoDevolucion.setCellValueFactory(new PropertyValueFactory<>("devuelto"));
		tableColumnAlmacenDevolucion.setCellValueFactory(new PropertyValueFactory<>("almacen"));
		tableColumnEsperaDevolucion.setCellValueFactory(new PropertyValueFactory<>("espera"));
		tableColumnLavanderiaDevolucion.setCellValueFactory(new PropertyValueFactory<>("lavanderia"));;
		tableColumnReparacionDevolucion.setCellValueFactory(new PropertyValueFactory<>("reparacion"));
		tableColumnPlanchadoDevolucion.setCellValueFactory(new PropertyValueFactory<>("planchado"));
		tableColumnVentaDDevolucion.setCellValueFactory(new PropertyValueFactory<>("vendido"));
		tableColumnVenderDevolucion.setCellValueFactory(new PropertyValueFactory<>("guardar"));

	}


	private boolean updTDetallePorPiezasDevolucion(){
		
		MTDetallePorPiezasDevolucion mtDetallePorPiezasDevolucion=new MTDetallePorPiezasDevolucion();
		mtDetallePorPiezasDevolucion.iniciarConexionServidor();
		mtDetallePorPiezasDevolucion.inicializarCommit();
		for (TDetallePorPiezasDevolucion row : tableViewDevolucion.getItems()) {
			if(!mtDetallePorPiezasDevolucion.actualizarTDetallePorPiezasDevolucion(row)){
				mtDetallePorPiezasDevolucion.cerrarConexionServidor();
				return false;
			}
		}
		mtDetallePorPiezasDevolucion.validarCambiosServidor();
		mtDetallePorPiezasDevolucion.cerrarConexionServidor();
		return true;
	}
	
	
	/*public DetalleBoleta seleccionarDetalleBoleta(int idDetalleBoleta){
		MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
		mDetalleBoleta.iniciarConexionServidor();
		DetalleBoleta detalleBoleta=mDetalleBoleta.seleccionarDetalleBoleta(idDetalleBoleta);
		mDetalleBoleta.cerrarConexionServidor();
	
		return detalleBoleta;
	}*/
	public static void refrescarTableDevolucion(){
		//TableView static table=tableViewDevolucion;
		//if(tableViewDevolucion.getItems()!=null && !tableViewDevolucion.getItems().isEmpty()){
			//tableViewDevolucion.refresh();
		//}
		
	}
	
	public void seleccionarCantPendientePorBoleta(int idBoleta){
		
		MDevolucion mDevolucion=new MDevolucion();
		mDevolucion.iniciarConexionServidor();
		int cant=mDevolucion.contarPiezasPendientesPorBoleta(idBoleta);
		mDevolucion.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, cant+" Piezas pendientes.", (cant==0)?CPadre.CORRECTO:CPadre.INCORRECTO);
		if(cant==0){
			jFXButtonValidarDevCompletada.setDisable(false);
			//tabGarantia.setDisable(false);
		}else{
			jFXButtonValidarDevCompletada.setDisable(true);
			//tabGarantia.setDisable(true);
			
		}
	}
	
	
	public void inicializarCamposTBoletaAvalada(){
		tableColumnIDBoletasAvaladas.setCellValueFactory(new PropertyValueFactory<>("id"));
		tablecColumnSerieBoletasAvaladas.setCellValueFactory(new PropertyValueFactory<>("serieBoleta"));
		tableColumnNumeroBoletasAvaladas.setCellValueFactory(new PropertyValueFactory<>("numeroBoleta"));
		tableColumnEstadoBoletasAvaladas.setCellValueFactory(new PropertyValueFactory<>("estadoDevBoleta"));
	}
	
	
	public void seleccionarTBoletaAvalada(int idGarBoleta){
		MTBoletaAvalada mtBoletaAvalada=new MTBoletaAvalada();
		mtBoletaAvalada.iniciarConexionServidor();
		tableViewBoletasAvaladas.setItems(mtBoletaAvalada.seleccionarBoletaAvalada(idGarBoleta));
		tableViewBoletasAvaladas.refresh();
		mtBoletaAvalada.cerrarConexionServidor();
		

	}
	
	
	public Boleta selecccionarBoletaGarantiaAndDevolucionGarantia(int idBoleta){
		MBoleta mBoleta=new MBoleta();
		Boleta boleta=null;
		mBoleta.iniciarConexionServidor();
		boleta=mBoleta.selecccionarBoletaGarantiaAndDevolucionGarantia(idBoleta);
		mBoleta.cerrarConexionServidor();
		return boleta;
	
	}
	
	public boolean actualizarBoletaGarantiaDevolucion(Boleta boleta){
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		mBoleta.actualizarBoletaGarantiaDevolucion(boleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MBoleta.CORRECTO){
			return true;
		}
		return false;
	}
	
	
	public Boleta getFieldsGarantia(){
		Boleta boleta=new Boleta();
		boleta.setId(tDevolucion.getId());
		boleta.setDevDni(jFXCheckBoxDniDevolucion.isSelected());
		boleta.setDevDniMenor(jFXCheckBoxDniMenorDevolucion.isSelected());
		boleta.setDevLicencia(jFXCheckBoxLicenciaDevolucion.isSelected());
		boleta.setDevDinero(jFXCheckBoxDineroDevolucion.isSelected());
		boleta.setDevOtro(jFXCheckBoxOtroDevolucion.isSelected());
		boleta.setDevEnlazarBoleta(jFXTCheckBoxBoletaAlqDevolucion.isSelected());
		return boleta;
	}
	
/*	public boolean actualizarBoletaGarantiaDevolucion(int idboleta){
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		mBoleta.actualizarBoletaDevCompletada(idboleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MBoleta.CORRECTO){
			return true;
		}
		return false;
	}*/
	
	public boolean actualizarBoletaDevCompletada(int idBoleta){
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		mBoleta.actualizarBoletaDevCompletada(idBoleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MBoleta.CORRECTO){
			return true;
		}
		return false;
	}
	
	public boolean actualizarBoletaDevGarantiaCompletada(int idBoleta){
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		mBoleta.actualizarBoletaDevGarantiaCompletada(idBoleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MBoleta.CORRECTO){
			return true;
		}
		return false;
	}
	
	
	
	public void bloquearDevCompletada(){
		tableViewDevolucion.setEditable(false);
		
		jFXButtonValidarDevCompletada.setVisible(false);
		tableColumnVenderDevolucion.setMaxWidth(0.0);
		tableColumnVenderDevolucion.setMinWidth(0.0);
		tableColumnVenderDevolucion.setVisible(false);
		jFXButtonGuadarEnBloque.setVisible(false);
	}
	
	
	public boolean validarEntregaCompGarantias (){
		//boolean garDni=!jFXCheckBoxDniGarantia.isDisable();
		if(!jFXCheckBoxDniGarantia.isDisable() && !jFXCheckBoxDniDevolucion.isSelected() ){

			System.out.println("hOLAAAASSSSSS DNI DEVOLUCIÓN");
			return false;
		}
		
		if(!jFXCheckBoxDniMenorGarantia.isDisable() && !jFXCheckBoxDniMenorDevolucion.isSelected()){

			System.out.println("hOLAAAASSSSSS GARANTIA");
			return false;
		}
		
		if(!jFXCheckBoxLicenciaGarantia.isDisable() && !jFXCheckBoxLicenciaDevolucion.isSelected()){
			return false;
		}
		
		if(!jFXCheckBoxDineroGarantia.isDisable() && !jFXCheckBoxDineroDevolucion.isSelected()){
			return false;
		}
		
		if(!jFXCheckBoxOtroGarantia.isDisable() && !jFXCheckBoxOtroDevolucion.isSelected()){
			return false;
		}
		System.out.println("hOLAAAASSSSSS");
		
		
		
		if(!jFXTCheckBoxBoletaAlqGarantia.isDisable() && !jFXTCheckBoxBoletaAlqDevolucion.isSelected()){
			System.out.println("hOLAAAASSSSSS BOLETA GARANTIA");
			return false;
		}
		
		System.out.println("hOLAAAASSSSSS GARANTIA");
		return true;
	}
	
	public void bloquearDevGarantiaCompletada(){
		jFXButtonValidarDevolucionPertinencias.setVisible(false);
		jFXButtonGuadarGarantiaDevolucion.setVisible(false);
	}
	
	
	private boolean setDisableGarTipoEnlace(){
		
		for (TBoletaAvalada dal : tableViewBoletasAvaladas.getItems()) {
			if(!(dal.getDevCompletada()==1 && dal.getDevGarantiaCompletada()==1)){
				return true;
			}
		}
		
		return false;
	}
	
	
	
	/*Incio-funciones para venta por daño*/
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
		jFXCheckBoxCupon1.setSelected(this.boleta.isCupon1());
		jFXCheckBoxCupon2.setSelected(this.boleta.isCupon2());
		jFXCheckBoxCupon3.setSelected(this.boleta.isCupon3());
		
		textFiedlDesAdicMonPor.setText("0");
		textFiedlDesAdicMonPor.setDisable(true);
		labelDescuentoEn.setText("");
		labelDescuentoEnNota.setText("");
		
		if(this.boleta.getDesAdicionalAc()==0){
			textFiedlDesAdicMonPor.setText("0");
			textFiedlDesAdicMonPor.setDisable(true);
			labelDescuentoEn.setText("");
			labelDescuentoEnNota.setText("");
			jFXRadioButtonDesNinguno.setSelected(true);
		}else if(this.boleta.getDesAdicionalAc()==1){
			labelDescuentoEn.setText("Descuento en (S/)");
			textFiedlDesAdicMonPor.setDisable(false);
			textFiedlDesAdicMonPor.setText(String.valueOf(this.boleta.getDesAdiSoles()));
			labelDescuentoEnNota.setText("");
			jFXRadioButtonDesSoles.setSelected(true);
			
		}else if(this.boleta.getDesAdicionalAc()==2){
			labelDescuentoEn.setText("Descuento en (%)");
			textFiedlDesAdicMonPor.setDisable(false);
			labelDescuentoEnNota.setText("Descuento % (1-100)");
			textFiedlDesAdicMonPor.setText(String.valueOf(this.boleta.getDesAdiPor()));
			jFXRadioButtonDesPor.setSelected(true);
		}
			
		
		
		
		
	}
	
	private void cargarDescuentosAdicYCupones(){
		Boleta boleta=new Boleta();
		mBoleta.iniciarConexionServidor();
		boleta=mBoleta.cargarDescuentosAdicYCupones(this.boleta.getId());
		mBoleta.cerrarConexionServidor();
		
		this.boleta.setCupon1(boleta.isCupon1());
		this.boleta.setCupon2(boleta.isCupon2());
		this.boleta.setCupon3(boleta.isCupon3());
		this.boleta.setValorCuponPor(boleta.getValorCuponPor());
		this.boleta.setDesAdicionalAc(boleta.getDesAdicionalAc());
		this.boleta.setDesAdiSoles(boleta.getDesAdiSoles());
		this.boleta.setDesAdiPor(boleta.getDesAdiPor());
		
		
		//mostrar datos los labels
		
		
		
		
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
	
	public int  nuevaBoletaPorDanio(int idBoleta, String dniUserActual){
		int idBoletaNuevaPorDanio=-1;
		MDevolucion mDevolucion=new MDevolucion();
		mDevolucion.iniciarConexionServidor();
		idBoletaNuevaPorDanio=mDevolucion.nuevaBoletaPorDanio(idBoleta, dniUserActual);
		mDevolucion.cerrarConexionServidor();
		return idBoletaNuevaPorDanio;
	}
	
	public int verificarAptoCrearNuevaBoleta(int idBoleta){
		int estado=0;
		MDevolucion mDevolucion=new MDevolucion();
		mDevolucion.iniciarConexionServidor();
		estado=mDevolucion.verificarAptoCrearNuevaBoleta(idBoleta);
		mDevolucion.cerrarConexionServidor();
		return estado;
	}
	
	private Boleta seleccionarDevArticulosGarantiaVentaDanio(int idBoleta){
		Boleta boleta=null;
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		boleta=mBoleta.seleccionarDevArticulosGarantiaVentaDanio(idBoleta);
		mBoleta.cerrarConexionServidor();
		return boleta;
		
	}
	
	private Boleta checkFacturacion(int idBoleta){
		Boleta boleta=null;
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		boleta=mBoleta.checkFacturacion(idBoleta);
		mBoleta.cerrarConexionServidor();
		return boleta;
		
	}
	private void  bloquearFacturacionVentaPorDanio(int estadoAccion){
		
		if(estadoAccion==3){
			jFXButtonEfectuarOperacion.setVisible(false);
			gridPaneDescuentos.setDisable(true);
			tabGarantia.setDisable(false);
			tabVentaDanio.setDisable(false);
			
		}else{
			tabVentaDanio.setDisable(false);
			
			tabGarantia.setDisable(true);
		}
		
	}
	
	private void cargarBoletaDanio(){
		extraerDatosDeBusquedaCliente(tDevolucion.getIdCliente());
		cargarDetallePendiente(this.boleta.getId());
		cargarDescuentosAdicYCupones();
		extraerDatosBoletaPendienteCarritoAlquilerCostos();
		cargarPreciosCalculados();
		int estadoAccion=checkFacturacion(this.boleta.getId()).getEstadoAccion();
		bloquearFacturacionVentaPorDanio(estadoAccion);
	
	}
	
	
	/*Fin-Funciones para venta por daño*/
	
	
}
