
package controller.alquiler;

import java.awt.RenderingHints.Key;
import java.awt.print.PrinterException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import controller.CPadre;
import controller.busqueda.CBusqueda;
import controller.cliente.CAddCliente;
import controller.cliente.CCliente;
import controller.reputacion.CProgress;
import ctreetablewiew.Unidad;
import dal.AlquilerBArticulos;
import dal.Boleta;
import dal.Busqueda;
import dal.CFamilia;
import dal.Cliente;
import dal.DatoAtomico;
import dal.DetalleBoleta;
import dal.Pruebaa;
import dal.Reservacion;
import dal.TAlquilerHoy;
import dal.TRecibo;
import funciones.Servidor;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
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
import model.articulo.MArticulo;
import model.busqueda.MBusqueda;
import model.cliente.MCliente;
import model.datoAtomico.MDatoAtomico;
import validacion.Validacion;


	


public class CAlquiler extends CPadre implements Initializable{
	private int intReputacionCliente=0;
	
	public static int ULTIMO_ID_BOLETA=-1;
	
	/*1=EFECTUADO Y 0=NO EFECTUADO*/
	public static int BOL_VEN_ALQ_EFEC;
	
	//Tipo de venta
	private int tipo_venta=-1;
	//

	private MBoleta mBoleta=new MBoleta();
	private Boleta boleta=new Boleta();
	private Cliente cliente=new Cliente();
	
	private MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
	private DetalleBoleta detalleBoleta=new DetalleBoleta();


	//curosos
	@FXML 
	private ComboBox<String>comboBoxTallaArticulosPiezas;
	@FXML 
	private ComboBox<DatoAtomico>comboBoxFamiliaArticulosPiezas;
	//Rerservacion
	
		@FXML 
		private ContextMenu contextMenuOpcArticulosPiezas;
		private MenuItem menuItemRefrescarTodoArtPiezas=new MenuItem("Refrescar Todo");
		private MenuItem menuItemRefrescarBusquedaArtPiezas=new MenuItem("Refrescar Búsqueda");
		private MenuItem menuItemAgregar=new MenuItem("Agregar");
		
		@FXML
		private Label labelTotalHoy;
		
		
		@FXML 
		private ContextMenu contextMenuOpcAlqVentasHoy;
		
		@FXML 
		private ContextMenu contextMenuOpcReservacion;
		
		@FXML 
		private ContextMenu contextMenuArticulos;
		
		private MenuItem menuItemReserEditar=new MenuItem("Facturar");
		private MenuItem menuItemReserEliminar=new MenuItem("Eliminar");
		private MenuItem menuItemReserRefrescar=new MenuItem("Refrescar");
		private MenuItem menuItemFacturar=new MenuItem("Facturar");
	
		@FXML 
		private TextField textFieldBuscarReservacion;
	
	   @FXML
	    private TableView<Reservacion> tableViewResevacion;
	   
	   @FXML
	    private TableColumn<Reservacion, String> tableColumnIdReser;
	   
	   @FXML
	   private TableColumn<Reservacion, String>  tableColumnArticuloPiezaReser;
	   
	    @FXML
	    private TableColumn<Reservacion, String> tableColumnFechaReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnTipoReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnCodCliReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnDNIReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnApellNomReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnTotalAPagarReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnACuentaReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnSaldoReser;

	    @FXML
	    private TableColumn<Reservacion, String> tableColumnfRecojoReser;
	    
	    //
	    @FXML 
	    private Label labelMensajeCliente;
		
	    @FXML 
	    private BorderPane borderPaneReputacion;
		
		@FXML
		private HBox hBoxReputacion;
		
		private CProgress cProgressCliente=new CProgress();
		private CProgress cProgressRecomendacion = new CProgress();
		
		private MenuItem menuItemVerEnDetalle=new MenuItem("Ver en detalle");
		
		private MenuItem menuItemVerEnDetalleHoy=new MenuItem("Ver en detalle");
		private MenuItem menuItemRefrescarTodoHoy=new MenuItem("Refrescar Todo");
	    private MenuItem menuitemRefrescarBusquedaHoy=new MenuItem("Refrescar Busqueda");
	
	
	@FXML 
	private ProgressBar progressBarReputacionCliente;
	
	@FXML
	private ProgressBar progressBarReputacionRec;
	
	@FXML
	private JFXToggleButton jFXToggleButtonSeparado;
	
	@FXML 
	private Label labelApellNomBoletaGarantia;
	
	@FXML
	private Label labelTipoTransaccion;
	
    @FXML
    private TextField textFieldCodigoCliente;

    @FXML
    private Button buttonBuscarCliente;

    @FXML
    private Button buttonEliminarCliente;

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
    private Label labelDescuentoEnNota;
    
    @FXML
    private TableView<DetalleBoleta> tableViewArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnCodigoArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnCantArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnDescripcionArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnPrecioArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnImporteArticulos1;

    @FXML
    private TableColumn<DetalleBoleta, String> tableColumnEliminarArticulos1;

    
    
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
    private DatePicker datePickerFechaRecojo;
    
    @FXML
    private Label labelDescuentoEn;

    //@FXML
   // private Spinner<Integer> spinnerDesMonPor;
    @FXML 
    private TextField textFiedlDesAdicMonPor;
    
    
    @FXML
    private TextField textFieldSepACuenta;
	
    @FXML 
	private TextField textFieldSepSaldo;
    
    @FXML
    private TableView<?> tableViewPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnSeleccionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnNroPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnCantPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnImportePiezas;

    @FXML
    private BorderPane boderPaneBodyGarantia;

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
    private Button buttonEnlazarBoleta;

    @FXML
    private Button buttonEliminarEnlazarBoleta;

    @FXML
    private TextField textFieldNroBoletaGarantia;

    @FXML
    private JFXCheckBox jFXTCheckBoxBoletaAlqGarantia;

    @FXML
    private TextField textFieldSerieBoletaGarantia;

    @FXML
    private BorderPane boderPaneBodyRecomendacion;

    @FXML
    private TextField textFieldCodigoClienteRecome;

    @FXML
    private Button buttonBuscarClienteRecome;

    @FXML
    private Button buttonEliminarClienteRecomen;

    @FXML
    private BorderPane boderPaneBodyDevolucion;
    
    @FXML 
    private BorderPane borderPaneBodySeparado;

    @FXML
    private DatePicker datePickerFechaEntrega;

    @FXML
    private DatePicker datePickerFechaDevolucion;

    @FXML 
    private Label labelNomApellRec;
    
    @FXML
    private JFXButton jFXButtonReservar;

    @FXML
    private JFXButton jFXButtonEfectuarOperacion;

    @FXML 
    private MenuItem menuItemNuevoAlquiler;
    
    @FXML 
    private MenuItem menuItemNuevoVenta;

 
    @FXML
    private TextField textFieldBuscarProductos;

    @FXML
    private TextField textFieldBuscarAlquiler;
    
    @FXML
    private TreeTableView<Unidad> treeTableViewArticulos;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnCodigo;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnDescripcion;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnTalla;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnGenero;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPCompra;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPAlquiler;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPVenta;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnStock;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnAgregar;
    
    private TreeItem<Unidad> dummyRoot;
    
    @FXML
    private TableView<TAlquilerHoy> TableViewListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnNroListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnSerieListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnNumeroListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnTipoListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnCodDniListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnApellNomListAlqHoy;
    
    @FXML 
    private TableColumn<TAlquilerHoy, String> tableColumnSubTotalListAlqHoy;
    
    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnDesCuponListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnDesAdicListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnTotalListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnFEntregaListAlqHoy;

    @FXML
    private TableColumn<TAlquilerHoy, String> tableColumnFDevolucionListAlqHoy;

    private ObservableList<DetalleBoleta> arrayDetalleBoletas=FXCollections.observableArrayList();
	
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	
	}

	@Override
	public Object getObjeto() {
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTallaArticulosPiezas.getItems().addAll("Todos","0", "2","4","6","8","10","12","14","16","S","M", "L", "XL","XXL");
		cargarComboBoxFamilia();
		hBoxReputacion.getChildren().add(cProgressRecomendacion);
		cProgressRecomendacion.setPrefWidth(268);
		cProgressCliente.setMinWidth(288);
		borderPaneReputacion.setCenter(cProgressCliente);
		textFiedlDesAdicMonPor.setTextFormatter(Validacion.doubleFormater());
		
		inicializarCamposTreeTableArticulos();
		inicializarCamposTableDetalleBoleta();
		
		seleccionarArticulos("", comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());
		
		tableViewArticulos1.setItems(arrayDetalleBoletas);
		tableViewArticulos1.refresh();
		
		
		agregarEventosGarantiajFXCheckBox();
		inicializarCamposTableReservacion();
		seleccionarReservacion("");
		
		inicializarCamposAlquilerHoy();
		seleccionarTAlquilerHoy("");
		calcularTotalHoy();
		
		
		//if(!cargarBoletaPendiente()){
			
			//inicar boleta de alquiler
			boleta.setTipo(Boleta.TIPO_VENTA_ALQUILER);
			agregarBoleta();
		//}/*else{
			
			extraerDatosDeBusquedaCliente(boleta.getIdCliente());
			cargarDetallePendiente(boleta.getId());
			//extraerDatos
		//}*/
		
		
		if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
			habilitaComposAlquiler();
			radioButtonNinguno();
			deshabilitarCamposGarantia(false);
			deshabilitarCamposSeparado(true);
			datePickerFechaEntrega.setValue(LocalDate.now());

			//deshabilitarCamposDevolucion(false, boleta.getTipo());
	
		}else if(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA){
			habilitarCamposVenta();
			deshabilitarCamposDevolucion(true, boleta.getTipo());
			deshabilitarCamposGarantia(true);
			deshabilitarCamposSeparado(true);
			
		}
		
		
		
		
		//Cargar 
	
		
		//JEditorPane textarea = new JEditorPane("text/html", "");
		//textarea.setText("Here is some <b>bold text</b>\nSegunda line supuestaramente <b>bold text</b><br>Terceralinea<h1>Ricardo</h1>");
		
		//JTextArea textare=new JTextArea("HOLAAAAAAAjfdalksdjflaskdjflñ\nhola\nhola\n\t\thola hola\n <h3>Ricardo</h3> ");
		/*try {
			textarea.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//textare.
		
/*setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0));*/
		
		menuItemNuevoAlquiler.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				nuevoAlquiler();
				
				
				
				
				
				
				//eliminar ultimo id para poder crear una nueva boleta
				/*if(boleta.getEstadoAccion()==1){
					eliminarBoletAYDetalle();
				}
				eliminarLimpiarCamposCliente();
				TableViewListAlqHoy.getItems().clear();
				
				boleta.setTipo(Boleta.TIPO_VENTA_ALQUILER);
				agregarBoleta();
				
				habilitaComposAlquiler();
				if(boleta.getId()!=1){
					cargarPreciosCalculados();
					deshabilitarCamposDevolucion(false, boleta.getTipo());
					deshabilitarCamposGarantia(false);
				}*/

			}
		});
		
		menuItemNuevoVenta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0){
				
				//metodos de pendiente al ser una boleta limpia no va traer nada de la base de datos
				if(boleta.getEstadoAccion()==1){
					eliminarBoletAYDetalle();
				}
				
				boleta=new Boleta();

				tableViewArticulos1.getItems().clear();
				tableViewArticulos1.refresh();
				//TableViewListAlqHoy.getItems().clear();
				//TableViewListAlqHoy.refresh();
				boleta.setTipo(Boleta.TIPO_VENTA_VENTA);
				agregarBoleta();
				extraerDatosBoleta();
				//cargarDetallePendiente(boleta.getId());

				if(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA){
					habilitarCamposVenta();
					deshabilitarCamposDevolucion(true, boleta.getTipo());
					deshabilitarCamposGarantia(true);
					deshabilitarCamposSeparado(true);
					datePickerFechaEntrega.setValue(null);


				}else if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
					habilitaComposAlquiler();
					deshabilitarCamposDevolucion(false, boleta.getTipo());
					deshabilitarCamposGarantia(false);
					deshabilitarCamposSeparado(true);
					datePickerFechaEntrega.setValue(LocalDate.now());

				}	
				cargarPreciosCalculados();
				
				//fin del clases necesarias
				
				
				
				
	
				//eliminar ultimo id para poder crear una nueva boleta
				/**if(boleta.getEstadoAccion()==1){
					eliminarBoletAYDetalle();
				}
				eliminarLimpiarCamposCliente();
				TableViewListAlqHoy.getItems().clear();
				
				boleta.setTipo(Boleta.TIPO_VENTA_VENTA);
				
				agregarBoleta();
				
				habilitarCamposVenta();
				if(boleta.getId()!=-1){
					cargarPreciosCalculados();
					deshabilitarCamposDevolucion(true, boleta.getTipo());
					deshabilitarCamposGarantia(true);
				}*/
				
			}
		});
		
		jFXToggleButtonSeparado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				deshabilitarCamposSeparado(!jFXToggleButtonSeparado.isSelected());
				//deshabilitarCamposDevolucion(jFXToggleButtonSeparado.isSelected(), boleta.getTipo());
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
		
		
		
	
		
		textFieldCodigoCliente.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
			
				if(event.getCode()==KeyCode.ENTER){
					String codCliente=textFieldCodigoCliente.getText().trim();
					if(codCliente!=null && !codCliente.isEmpty()){
						if(!extraerDatosDeBusquedaCliente(codCliente)){
							Alert alert=new Alert(AlertType.CONFIRMATION, "¿Agregar nuevo cliente?", ButtonType.NEXT, ButtonType.CANCEL);
							alert.showAndWait();
							if(alert.getResult() == ButtonType.NEXT){
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
								
								String url = "/view/cliente/AddCliente.fxml";
								String css = "/estilocss/EstiloModal.css";
											
								CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
								
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);

								CAddCliente cCliente=(CAddCliente)cpadre;
								Cliente cliente=(Cliente)cCliente.getObjeto();
								
								extraerDatosDeBusquedaCliente(cliente.getCli_dni());					

							}
						}
					}
				}
				
				
			}
		});
		
		buttonBuscarCliente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);

				String url = "/view/busqueda/Busqueda.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, MBusqueda.CLIENTE, CPadre.NULO);
			
				CBusqueda cBusqueda = (CBusqueda)cpadre;
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				Busqueda busqueda=(Busqueda)cBusqueda.getObjeto();
				if(busqueda!=null){
					String dni=busqueda.getCampo2();
					textFieldCodigoCliente.setText(dni);
					extraerDatosDeBusquedaCliente(dni);					
				}
			}
		});
		
		buttonEliminarCliente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(boleta.getIdCliente()!=-1){
					if(EliminarDatos()){
						eliminarLimpiarCamposCliente();
					}
				}
				

			}
		});
		
		
		buttonEnlazarBoleta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);

				String url = "/view/busqueda/Busqueda.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, MBusqueda.BOLETA, CPadre.NULO);
			
				CBusqueda cBusqueda = (CBusqueda)cpadre;
				Busqueda busqueda=(Busqueda)cBusqueda.getObjeto();
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				if(busqueda!=null){
					String[] s_n=busqueda.getCodigo().split("-");
					
					extraerDatosDeBusquedaGarantia(s_n[0], Integer.parseInt(s_n[1]));
					
					
				}
			}
		});
		
		buttonEliminarEnlazarBoleta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				eliminarDatosDeBuquedaGarantia();
				
			}
		});
		
		buttonBuscarClienteRecome.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);

				String url = "/view/busqueda/Busqueda.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, MBusqueda.CLIENTE, CPadre.NULO);
			
				CBusqueda cBusqueda = (CBusqueda)cpadre;
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
				if(cBusqueda!=null){
					Busqueda busqueda=((Busqueda)cBusqueda.getObjeto());
					if(busqueda!=null){
						extraerDatosDeBusquedaRecomendacion(busqueda.getId());
					}
					//String dni=((Busqueda)cBusqueda.getObjeto()).getCampo2();
					//textFieldCodigoCliente.setText(dni);
					//extraerDatosDeBusquedaCliente(dni);					
				}
			}
		});
		
		
		textFieldBuscarProductos.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarArticulos(textFieldBuscarProductos.getText(), comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());			}
		});
		
		textFieldBuscarReservacion.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				seleccionarReservacion(textFieldBuscarReservacion.getText());
			}
		});
		
		textFieldBuscarAlquiler.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarTAlquilerHoy(textFieldBuscarAlquiler.getText());
				calcularTotalHoy();
				
			}
		});
		/*check button para actualizar descuentos y costos*/
		
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
	
		
		
		
		
		//textFieldSerieBoletaGarantia.setOnDragExited(value);;
		textFieldSerieBoletaGarantia.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

			String serieBolGar=textFieldSerieBoletaGarantia.getText().trim();
			String nroBolGar=textFieldNroBoletaGarantia.getText().trim();
			if(!newValue && serieBolGar!=null && !serieBolGar.isEmpty() && nroBolGar!=null && !nroBolGar.isEmpty()){
				extraerDatosDeBusquedaGarantia(serieBolGar, Integer.parseInt(nroBolGar));     
			  }
				
			}
		});
		
		textFieldNroBoletaGarantia.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String serieBolGar=textFieldSerieBoletaGarantia.getText().trim();
				String nroBolGar=textFieldNroBoletaGarantia.getText().trim();
				if(!newValue && serieBolGar!=null && !serieBolGar.isEmpty() && nroBolGar!=null && !nroBolGar.isEmpty()){
					extraerDatosDeBusquedaGarantia(serieBolGar, Integer.parseInt(nroBolGar));     
				  }				
			}
		});
		
		textFieldNroBoletaGarantia.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event){
				if(event.getCode()==KeyCode.ENTER){
					String serieBolGar=textFieldSerieBoletaGarantia.getText().trim();
					String nroBolGar=textFieldNroBoletaGarantia.getText().trim();
					if( serieBolGar!=null && !serieBolGar.isEmpty() && nroBolGar!=null && !nroBolGar.isEmpty()){
						extraerDatosDeBusquedaGarantia(serieBolGar, Integer.parseInt(nroBolGar));     
					  }	
				}
				
			}
		});
		
		comboBoxFamiliaArticulosPiezas.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarArticulos(textFieldBuscarProductos.getText(), comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());
				
			}
		});
		
		comboBoxTallaArticulosPiezas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seleccionarArticulos(textFieldBuscarProductos.getText(), comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());				
			}
		});
		
		textFieldCodigoClienteRecome.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					String codClienteRec=textFieldCodigoClienteRecome.getText().trim();
					if(codClienteRec!=null && !codClienteRec.isEmpty()){
						extraerDatosDeBusquedaRecomendacion(codClienteRec);
					}
				}
			}
		});
		buttonEliminarClienteRecomen.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				eliminarLimpiarCamposRecomendacion();
				
			}
		});
		
		textFieldCodigoClienteRecome.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				String codClienteRec=textFieldCodigoClienteRecome.getText().trim();
				if(!newValue && codClienteRec!=null && !codClienteRec.isEmpty()){
					extraerDatosDeBusquedaRecomendacion(codClienteRec);
				}
			}
		});
		
		
		/*Reservacion*/
		textFieldSepACuenta.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					
					//calcularSepSaldo();
					
				}
				
			}
		});
		textFieldSepACuenta.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue){
					calcularSepSaldo();
				}
			}
		});
		
		jFXButtonReservar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				boleta.setEstadoAccion(2);
				capturarDatosBoleta();
				
				if(!verificarCamposVaciosReserva())
				{
					return;
				}
				boolean uEstado=actualizarBoletaClienteTipo();
				
				if(uEstado && boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
					uEstado=actualizarBoletaGarantia();
					
				}
				
				if(uEstado){
					uEstado=actualizarBoletaRecomendacion();
				} 
				
				if(boleta.isSepSeparar() && uEstado){
					uEstado=actualizarBoletaSeparacion();
					
				}
				
				if(uEstado && boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER ){
					uEstado=actualizarBoletaDevolucion();
				}
				

				if(uEstado){
					uEstado=actualizarBoletaReservado();
				}
				
				
				if(uEstado){
					
					StackPane sp=((StackPane)((Stage)tableViewResevacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					ULTIMO_ID_BOLETA=-1;
					String url = "/view/alquiler/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					//mostrarAlerta("Reservar", "", "Se realizo correctamente la reserva", AlertType.CONFIRMATION);
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CSeeBoleta.RESERVA);					
					boolean estado=(boolean)cpadre.getObjeto();
					if(estado){
						nuevoAlquiler();
					}
					sp.setVisible(false);
					
					
					
					System.out.println("Se realizao correctament la reserva");
				}else{
					mostrarAlerta("Reservar", "", "Fallo al realizarse la reserva.\nPor favor verifque bien los datos\ne intente de nuevo.", AlertType.WARNING);
					System.out.println("No se realizo correctamente la reseva");
				}

				
			}
		});
		
		jFXButtonEfectuarOperacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				boleta.setEstadoAccion(2);

				capturarDatosBoleta();
				if(!verificarCamposVacios()){
					return;
				}
				
				boolean uEstado=actualizarBoletaClienteTipo();
				
				if(uEstado && boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
					uEstado=actualizarBoletaGarantia();
					
				}
				if(uEstado){
					uEstado=actualizarBoletaRecomendacion();
				} 
				
				if(boleta.isSepSeparar() && uEstado){
					uEstado=actualizarBoletaSeparacion();
					
				}
				
				if(uEstado){
					uEstado=actualizarBoletaDevolucion();
				}
				
				if(uEstado){
					uEstado=actualizarBoletaReservado() ;
				}
				
				
				if(uEstado){
					
					
					StackPane sp=((StackPane)((Stage)tableViewResevacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					ULTIMO_ID_BOLETA=-1;
					String url = "/view/alquiler/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CSeeBoleta.FACTURAR);					
					boolean estado=(boolean)cpadre.getObjeto();
					if(estado){
						nuevoAlquiler();
					}
					sp.setVisible(false);
					
				}else{
					System.out.println("No se realizo correctamente la reseva");
				}


			}
		});

		
		
		
		
		menuItemReserEditar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ULTIMO_ID_BOLETA=-1;

				if(cargarBoletaReserva(tableViewResevacion.getSelectionModel().getSelectedItem().getId())){
					tableViewArticulos1.getItems().clear();
					tableViewArticulos1.refresh();
					extraerDatosBoleta();
					cargarDetallePendiente(boleta.getId());

					if(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA){
						habilitarCamposVenta();
						deshabilitarCamposDevolucion(true, boleta.getTipo());
						deshabilitarCamposGarantia(true);
						deshabilitarCamposSeparado(true);


					}else if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
						habilitaComposAlquiler();
						deshabilitarCamposDevolucion(false, boleta.getTipo());
						deshabilitarCamposGarantia(false);
						deshabilitarCamposSeparado(true);

					}	
					cargarPreciosCalculados();
				}
				
			}
		});
		
		menuItemReserEliminar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Reservacion reservacion=tableViewResevacion.getSelectionModel().getSelectedItem();
				if(reservacion!=null){
					mostrarAlerta("Reservación", "", "Antes de eliminar esta reservación.\nReembolse la suma de S/."+reservacion.getACuenta()+"\nque dejo a cuenta el cliente.", AlertType.INFORMATION);				}
					if(consultarAlerta("¿Esta seguro que desea continuar?")){
						eliminarBoletAYDetalleReserva(reservacion.getId());
						seleccionarReservacion(textFieldBuscarReservacion.getText().trim());
					}
					
			}
		});
		menuItemReserRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				seleccionarReservacion(textFieldBuscarReservacion.getText().trim());
			}
		});
		
		menuItemVerEnDetalle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				//TRecibo tRecibo=TableViewRecibos.getSelectionModel().getSelectedItem();
				Reservacion reservacion=tableViewResevacion.getSelectionModel().getSelectedItem();
				if(reservacion !=null){
					StackPane sp=((StackPane)((Stage)tableViewResevacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					Boleta boleta=new Boleta();
					boleta.setId(reservacion.getId());
					String url = "/view/recibo/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					
						
					sp.setVisible(false);

				}
				
			}
		});
		
		
		menuItemRefrescarTodoHoy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seleccionarTAlquilerHoy("");
				calcularTotalHoy();
				
			}
		});
		menuitemRefrescarBusquedaHoy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seleccionarTAlquilerHoy(textFieldBuscarAlquiler.getText());
				calcularTotalHoy();				
			}
		});
		
		
		menuItemVerEnDetalleHoy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Reservacion reservacion=tableViewResevacion.getSelectionModel().getSelectedItem();
				TAlquilerHoy tAlquilerHoy=TableViewListAlqHoy.getSelectionModel().getSelectedItem();
				if(tAlquilerHoy !=null){
					StackPane sp=((StackPane)((Stage)tableViewResevacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					Boleta boleta=new Boleta();
					boleta.setId(tAlquilerHoy.getId());
					String url = "/view/recibo/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					
						
					sp.setVisible(false);
				}				
			}
		});
		
		menuItemFacturar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				StackPane sp=((StackPane)((Stage)tableViewResevacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
			
				String url = "/view/alquiler/SeeBoleta.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				Boleta boleta=new Boleta();
				//boleta.set
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					
				sp.setVisible(false);

			}
		});
		
		
		menuItemAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				TreeItem<Unidad> treItemUnidad=treeTableViewArticulos.getSelectionModel().getSelectedItem();
				if(treItemUnidad!=null){
					Unidad unidad=treItemUnidad.getValue();
					agregarDetalleBoletaUnidad(unidad);	
				}
				
				
				
			}
		});
		menuItemRefrescarBusquedaArtPiezas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				seleccionarArticulos(textFieldBuscarProductos.getText(),comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());
			}
		});
		menuItemRefrescarTodoArtPiezas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				textFieldBuscarProductos.setText("");
				seleccionarArticulos(textFieldBuscarProductos.getText(), comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem(), comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem());
			}
		});
		
		
		//productos
		contextMenuArticulos.getItems().add(menuItemAgregar);
		contextMenuArticulos.getItems().add(new SeparatorMenuItem());
		contextMenuArticulos.getItems().add(menuItemRefrescarBusquedaArtPiezas);
		contextMenuArticulos.getItems().add(menuItemRefrescarTodoArtPiezas);
		
		
		
		///añadiendo 
		contextMenuOpcReservacion.getItems().add(menuItemReserRefrescar);	
		contextMenuOpcReservacion.getItems().add(menuItemReserEditar);
		contextMenuOpcReservacion.getItems().add(menuItemVerEnDetalle);
		contextMenuOpcReservacion.getItems().add(new SeparatorMenuItem());
		contextMenuOpcReservacion.getItems().add(menuItemReserEliminar);
	
		
		
		contextMenuOpcAlqVentasHoy.getItems().add(menuItemRefrescarTodoHoy);
		contextMenuOpcAlqVentasHoy.getItems().add(menuitemRefrescarBusquedaHoy);
		contextMenuOpcAlqVentasHoy.getItems().add(menuItemVerEnDetalleHoy);
		
	}
	
	
	//buton
	private void radioButtonNinguno(){
		boleta.setDesAdicionalAc(0);
		textFiedlDesAdicMonPor.setText("0");
		textFiedlDesAdicMonPor.setDisable(true);
		labelDescuentoEn.setText("");
		labelDescuentoEnNota.setText("");
		
	}
	
	///metodos para actualizar para boelta de reservar o pago
	private boolean actualizarBoletaGarantia(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaGarantia(this.boleta)){
			bool=true;
		}
		mBoleta.cerrarConexionServidor();
		return  bool;
	}
	
	public boolean actualizarBoletaRecomendacion(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaRecomendacion(boleta)){
			bool=true;
		}
		return bool;
	}
	
	private boolean actualizarBoletaSeparacion(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaSeparacion(boleta)){
			bool=true;
		}
		mBoleta.cerrarConexionServidor();
		return bool;
	}
	
	private boolean actualizarBoletaDevolucion(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaDevolucion(boleta)){
			bool=true;
		}
		return bool;
	}
	
	private boolean actualizarBoletaClienteTipo(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaClienteTipo(boleta)){
			bool=true;
		}
		return bool;
	}
	
	private boolean actualizarBoletaReservado(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaReservado(boleta)){
			bool=true;
		};
		return bool;
	}
	
	private boolean actualizarBoletaFacturado(){
		boolean bool=false;
		mBoleta.iniciarConexionServidor();
		if(mBoleta.actualizarBoletaFacturado(boleta)){
			bool=true;
		}
		return bool;
	}
	
	
	//
	private boolean cargarBoletaPendiente(){
		mBoleta.iniciarConexionServidor();
		Boleta boleta=mBoleta.selecccionarBoletaPendiente();
		if(boleta!=null){
			this.boleta=boleta;
			return true;
		}
		return false;
	} 
	
	private boolean cargarBoletaReserva(int idBoleta){
		mBoleta.iniciarConexionServidor();
		Boleta boleta=mBoleta.selecccionarBoletaReserva(idBoleta);
		if(boleta!=null){
			this.boleta=boleta;
			return true;
		}
		return false;
	}
	
	
	private void habilitarCamposVenta(){
		labelTipoTransaccion.setText("Venta");
		jFXCheckBoxCupon1.setDisable(true);
		jFXCheckBoxCupon2.setDisable(true);
		jFXCheckBoxCupon3.setDisable(true);
		
		//poner en false todo los check 
		
		jFXCheckBoxCupon1.setSelected(false);
		jFXCheckBoxCupon2.setSelected(false);
		jFXCheckBoxCupon3.setSelected(false);
	}
	
	private void habilitaComposAlquiler(){
		labelTipoTransaccion.setText("Alquiler");
		jFXCheckBoxCupon1.setDisable(false);
		jFXCheckBoxCupon2.setDisable(false);
		jFXCheckBoxCupon3.setDisable(false);
	}
	
	private void  deshabilitarCamposGarantia(boolean bool){
		/*verdadero*/
		if(bool){
			boderPaneBodyGarantia.setDisable(bool);
			jFXCheckBoxDniGarantia.setSelected(false);
			jFXCheckBoxDniMenorGarantia.setSelected(false);
			jFXCheckBoxLicenciaGarantia.setSelected(false);
			jFXCheckBoxDineroGarantia.setSelected(false);
			jFXCheckBoxOtroGarantia.setSelected(false);
			jFXTCheckBoxBoletaAlqGarantia.setSelected(false);
			
			/*TextField limpiar disable*/
			textFieldNroDniGarantia.setText("");
			textFieldNroMenorGarantia.setText("");
			textFieldNroLicenciaGarantia.setText("");
			textFieldDineroGarantia.setText("");
			textAreaOtroGarantia.setText("");
			textFieldSerieBoletaGarantia.setText("");
			textFieldNroBoletaGarantia.setText("");
			boleta.setGarIdBoleta(-1);
			
		}/*falso*/else{
			boderPaneBodyGarantia.setDisable(bool);
			
		}
		
		
	}
	
	private void deshabilitarCamposSeparado(boolean bool){
		borderPaneBodySeparado.setDisable(bool);
		jFXButtonReservar.setDisable(bool);
		jFXButtonEfectuarOperacion.setDisable(!bool);
		if(bool){
			//datePickerFechaRecojo.setValue(null);
			//textFieldSepACuenta.setText("");
			//textFieldSepSaldo.setText("");
		}
	}
	
	public void deshabilitarCamposDevolucion(boolean bool,int tipoVenta){
		if(tipoVenta==Boleta.TIPO_VENTA_ALQUILER){
			boderPaneBodyDevolucion.setDisable(bool);
			datePickerFechaEntrega.setDisable(bool);
			datePickerFechaDevolucion.setDisable(bool);
		}else if(tipoVenta==Boleta.TIPO_VENTA_VENTA){
			boderPaneBodyDevolucion.setDisable(true);
			datePickerFechaEntrega.setDisable(true);
			datePickerFechaDevolucion.setDisable(true);
		}
			
		
	}
	
	private void extraerDatosBoleta(){
		extraerDatosBoletaPendienteCliente();
		extraerDatosBoletaPendienteCarritoAlquilerCostos();
		extraerDatosBoletaPendienteGarantia();
		extraerDatosBoletaPendienteRecomendacion();
		extraerDatosBoletaPendienteSeparado();
		extraerDatosBoletaPendienteDevolucion();
	}
	
	private void extraerDatosBoletaPendienteCliente(){
		
		//if(boleta.getIdCliente()!=-1){
			extraerDatosDeBusquedaCliente(boleta.getIdCliente());
		
		//}
		
	}
	public void extraerDatosBoletaPendienteCarritoAlquilerCostos(){
		jFXCheckBoxCupon1.setSelected(boleta.isCupon1());
		jFXCheckBoxCupon2.setSelected(boleta.isCupon2());
		jFXCheckBoxCupon3.setSelected(boleta.isCupon3());
		
		
	}
	public void extraerDatosBoletaPendienteGarantia(){
		
		//Dni
		jFXCheckBoxDniGarantia.setSelected(boleta.isGarDni());
		if(boleta.isGarDni()){
			textFieldNroDniGarantia.setDisable(!boleta.isGarDni());
			textFieldNroDniGarantia.setText(boleta.getGarNroDni());
		}else{
			textFieldNroDniGarantia.setDisable(!boleta.isGarDni());
			textFieldNroDniGarantia.setText("");
		}
		
		//dni menor		
		jFXCheckBoxDniMenorGarantia.setSelected(boleta.isGarDniMenor());
		if(boleta.isGarDniMenor()){
			textFieldNroMenorGarantia.setDisable(!boleta.isGarDniMenor());
			textFieldNroMenorGarantia.setText(boleta.getGarNroDniMenor());
		}else{
			textFieldNroMenorGarantia.setDisable(!boleta.isGarDniMenor());
			textFieldNroMenorGarantia.setText("");
		}
		
		
		//licencia 
		jFXCheckBoxLicenciaGarantia.setSelected(boleta.isGarLicencia());
		if(boleta.isGarLicencia()){
			textFieldNroLicenciaGarantia.setDisable(!boleta.isGarLicencia());
			textFieldNroLicenciaGarantia.setText(boleta.getGarNroLicencia());
		}else{
			textFieldNroLicenciaGarantia.setDisable(!boleta.isGarLicencia());
			textFieldNroLicenciaGarantia.setText("");
		}
		
		//dinero
		jFXCheckBoxDineroGarantia.setSelected(boleta.isGarDinero());
		if(boleta.isGarDinero()){
			textFieldDineroGarantia.setDisable(!boleta.isGarDinero());
			textFieldDineroGarantia.setText(String.valueOf(boleta.getGarMonto()));
		}else{
			textFieldDineroGarantia.setDisable(!boleta.isGarDinero());
			textFieldDineroGarantia.setText(String.valueOf(0.0));
		}
		
		//otro
		jFXCheckBoxOtroGarantia.setSelected(boleta.isGarOtro());
		if(boleta.isGarOtro()){
			textAreaOtroGarantia.setDisable(!boleta.isGarOtro());
			textAreaOtroGarantia.setText(boleta.getGarOtroEspecifique());
		}else{
			textAreaOtroGarantia.setDisable(!boleta.isGarOtro());
			textAreaOtroGarantia.setText("");			
		}		
		
		
		//enlazar boleta
		jFXTCheckBoxBoletaAlqGarantia.setSelected(boleta.isGarEnlazarBoleta());
		if(boleta.isGarEnlazarBoleta()){
			
			textFieldSerieBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
			textFieldNroBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
			
			textFieldSerieBoletaGarantia.setText(boleta.getGarSerieBoleta());
			textFieldNroBoletaGarantia.setText(String.valueOf(((boleta.getGarNumeroBoleta()!=-1)?boleta.getGarNumeroBoleta():"")));
			buttonEnlazarBoleta.setDisable(!boleta.isGarEnlazarBoleta());
			buttonEliminarEnlazarBoleta.setDisable(!boleta.isGarEnlazarBoleta());
		}else{
			textFieldSerieBoletaGarantia.setText("");
			textFieldNroBoletaGarantia.setText("");
			buttonEnlazarBoleta.setDisable(!boleta.isGarEnlazarBoleta());
			buttonEliminarEnlazarBoleta.setDisable(!boleta.isGarEnlazarBoleta());

			textFieldSerieBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
			textFieldNroBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
		}
		
		

		
		
	}
	
	public void extraerDatosBoletaPendienteRecomendacion(){
		extraerDatosDeBusquedaRecomendacion(boleta.getRecIdCliente());
	}

	public void extraerDatosBoletaPendienteSeparado(){
		//jFXToggleButtonSeparado.setSelected(boleta.isSepSeparar());
		jFXToggleButtonSeparado.setSelected(false);
		//borderPaneBodySeparado.setDisable(!boleta.isSepSeparar());
		borderPaneBodySeparado.setDisable(true);
		
		if(boleta.getSepFechaRecojo()!=null){
		
			LocalDate localDateSepFechaRecojo = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(boleta.getSepFechaRecojo()));
			datePickerFechaRecojo.setValue(localDateSepFechaRecojo);			
		}else{
			datePickerFechaRecojo.setValue(null);			
		}
		
		textFieldSepACuenta.setText((boleta.getSepACuenta()!=-1.0)?String.valueOf(boleta.getSepACuenta()):"");
		textFieldSepSaldo.setText((boleta.getSepSaldo()!=-1.0)?String.valueOf(boleta.getSepSaldo()):"");
	}
	
	//The variable REPOSITORI not found.
	
	public void extraerDatosBoletaPendienteDevolucion(){
		//boderPaneBodyDevolucion.setDisable(!boleta.isSepSeparar());
		//butt
		if(boleta.getDevFechaEntrega()!=null){
			LocalDate localDateFechaEntrega = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(boleta.getDevFechaEntrega()));
			datePickerFechaEntrega.setValue(localDateFechaEntrega);
		}else{
			datePickerFechaEntrega.setValue(null);			
		}
		
		if(boleta.getDevFechaDevolucion()!=null){
			LocalDate localDateDevolucion=LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(boleta.getDevFechaDevolucion()));
			datePickerFechaDevolucion.setValue(localDateDevolucion);			
		}else{
			datePickerFechaDevolucion.setValue(null);						
		}
		
	}
	
	
	
	
	public void extraerDatosDeBusquedaRecomendacion(String codDni){
		 MCliente mCliente=new MCliente();
		 mCliente.iniciarConexionServidor();
		 cliente=mCliente.seleccionarCliente(codDni);
		 mCliente.cerrarConexionServidor();
		 if(cliente!=null){
			 boleta.setRecIdCliente(cliente.getCli_id());
			 labelNomApellRec.setText(cliente.getCli_apellNom());
			 cProgressRecomendacion.modelarProgress(cliente.getCli_reputacion());

		 }else{
			 eliminarLimpiarCamposRecomendacion();
		 }
	}
	
	public void extraerDatosDeBusquedaRecomendacion(Busqueda busqueda){
		if(busqueda!=null){
			
			textFieldCodigoClienteRecome.setText(busqueda.getCodigo());
			labelNomApellRec.setText(busqueda.getCampo3());
			boleta.setRecIdCliente(busqueda.getId());
		}else{
			eliminarLimpiarCamposRecomendacion();
		}
		
	}
	
	public void extraerDatosDeBusquedaRecomendacion(int  idRecomCliente){
		
		
		 MCliente mCliente=new MCliente();
		 mCliente.iniciarConexionServidor();
		 cliente=mCliente.seleccionarCliente(idRecomCliente);
		 mCliente.cerrarConexionServidor();
		if(cliente!=null){
			textFieldCodigoClienteRecome.setText(cliente.getCli_codigo());
			labelNomApellRec.setText(cliente.getCli_apellNom());
			boleta.setRecIdCliente(cliente.getCli_id());
			 cProgressRecomendacion.modelarProgress(cliente.getCli_reputacion());
		}else {
			
			eliminarLimpiarCamposRecomendacion();

		}
			

	}
	
	
	private void eliminarLimpiarCamposRecomendacion(){
		textFieldCodigoClienteRecome.setText("");
		labelNomApellRec.setText("");
		boleta.setRecIdCliente(-1);
		 cProgressRecomendacion.modelarProgress(0);
	}

	public void extraerDatosDeBusquedaGarantia(Busqueda busqueda){
		if(busqueda!=null){
			
			String[] s_n=busqueda.getCodigo().split("-");
			textFieldSerieBoletaGarantia.setText(s_n[0]);
			textFieldNroBoletaGarantia.setText(s_n[1]);
			labelApellNomBoletaGarantia.setText(busqueda.getCampo4());
			 
			boleta.setGarIdBoleta(busqueda.getId());
			boleta.setGarSerieBoleta(s_n[0]);
			boleta.setGarNumeroBoleta(Integer.parseInt(s_n[1]));
		}		
	}
	
	public void extraerDatosDeBusquedaGarantia(String serie, int numero){
		mBoleta.iniciarConexionServidor();
		Object[] objetos=mBoleta.selecccionarBoletaGarantia(serie, numero);
		mBoleta.cerrarConexionServidor();
		if(objetos!=null){
			labelApellNomBoletaGarantia.setStyle("-fx-text-fill: #008df5;");
			boleta.setGarIdBoleta((int)objetos[0]);
			labelApellNomBoletaGarantia.setText((String)objetos[1]);
			boleta.setGarSerieBoleta(serie);
			boleta.setGarNumeroBoleta(numero);
			
			textFieldSerieBoletaGarantia.setText(boleta.getGarSerieBoleta());
			textFieldNroBoletaGarantia.setText(String.format("%08d", boleta.getGarNumeroBoleta()));
		
		}else{
			labelApellNomBoletaGarantia.setStyle("-fx-text-fill: red;");
			boleta.setGarIdBoleta(-1);
			labelApellNomBoletaGarantia.setText("Boleta no encontrada o \nBoleta no apta para esta acción.");
			boleta.setGarSerieBoleta(serie);
			boleta.setGarNumeroBoleta(numero);
		}
		
	}
	
	public void eliminarDatosDeBuquedaGarantia(){
		boleta.setGarIdBoleta(-1);
		textFieldSerieBoletaGarantia.setText("");
		textFieldNroBoletaGarantia.setText("");
		labelApellNomBoletaGarantia.setText("");
	}
	
	public boolean extraerDatosDeBusquedaCliente(String codDni){
		 MCliente mCliente=new MCliente();
		 mCliente.iniciarConexionServidor();
		 cliente=mCliente.seleccionarCliente(codDni);
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
				 labelMensajeCliente.setText("El cliente seleccionado no cumple con la reputación minima para realizar esta transacción. ");
				 
			 }else{
				 labelMensajeCliente.setTextFill(Color.web("#000"));
				 labelMensajeCliente.setText("Cliente apto para esta transacción.");
			 }
			 //
			 return true;
		 }else{
				eliminarLimpiarCamposCliente();
				return false;
		 }
		
		 
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
				 labelMensajeCliente.setText("El cliente seleccionado no cumple con la reputación minima para realizar esta transacción. ");
				 
			 }else{
				 labelMensajeCliente.setTextFill(Color.web("#000"));
				 labelMensajeCliente.setText("Cliente apto para esta transacción.");
			 }
		}else {
			eliminarLimpiarCamposCliente();
		}
	}
	
	public void eliminarLimpiarCamposCliente(){
		boleta.setIdCliente(-1);
		textFieldCodigoCliente.setText("");
		 jFXTextFieldCodigo.setText("");
		 jFXTextFieldDNI.setText("");
		 jFXTextFieldApellidosNombres.setText("");
		 jFXTextFieldDireccion.setText("");
		 jFXTextFieldReferencia.setText("");
		 jFXTextFieldCorreo.setText("");
		 jFXTextFieldTelefono.setText("");
		 cProgressCliente.modelarProgress(0);
		 intReputacionCliente=0;

		 //
		 labelMensajeCliente.setTextFill(Color.web("#000"));
		 labelMensajeCliente.setText("Busque o seleccione un cliente.");
			 
		 
	}
	


	public void inicializarCamposTreeTableArticulos(){
	 	treeTableColumnCodigo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad,String> param)-> new ReadOnlyStringWrapper(
			 param.getValue().getValue().getCodigo()
	 	));
	 	
	 	treeTableColumnDescripcion.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	    	param.getValue().getValue().getDescripcion()
	    ));
	 	
	 	treeTableColumnTalla.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		    	param.getValue().getValue().getTalla()
		    	
		));
	 	
	 	treeTableColumnGenero.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			param.getValue().getValue().getGenero()
	 	));

	 	treeTableColumnStock.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getStock())
	 	));
	 	
	 	treeTableColumnPCompra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioCompra())
	 	));
	 	
	 	
	 	treeTableColumnPAlquiler.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioAlquiler())
	 	));
	 	
	 	treeTableColumnPVenta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioVenta())
	 	));
	 	
	 	treeTableColumnAgregar.setCellValueFactory(new TreeItemPropertyValueFactory<Unidad, String>("jfxButtonAgregar"));
	 	
	    
	}
	
	

	public void seleccionarArticulos(String strBuscar, DatoAtomico datoAtomico, String talla){
		strBuscar=(strBuscar!=null && !strBuscar.trim().isEmpty()?"%"+strBuscar+"%":"%");
		String sqlFamilia="";
		if(datoAtomico!=null){
			if(datoAtomico.getId()!=0){
				sqlFamilia=" and IdFamilia="+datoAtomico.getId()+"";
			}
		}
		
		String sqlTalla=(talla!=null &&!talla.equals("Todos"))?" and Talla='"+talla+"' ":" ";
		
		dummyRoot=new  TreeItem<>();

		Connection conexionServidor=null;
		PreparedStatement pst=null;
		PreparedStatement pstPiezas=null;
		ResultSet rs=null;
		ResultSet rsPiezas=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionServidor = DriverManager.getConnection(Servidor.getServidor(), Servidor.USER, Servidor.PASS);
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Talla, Stock, IdFamilia,(select nombre from familia where id=IdFamilia) as Familia, Rentabilidad, PrecioCompra, PrecioAlquiler, PrecioVenta, Genero, Ubicacion, NroPiezas, ProveedorId from articulo where (Codigo like ? OR Descripcion like ? or Comentarios like ?)  "+sqlTalla+" "+sqlFamilia+";");
			pst.setString(1, strBuscar);
			pst.setString(2, strBuscar);
			pst.setString(3, strBuscar);
			rs=pst.executeQuery();
			while(rs.next()){
				Unidad unidadRoot=new Unidad(Unidad.ARTICULO,rs.getInt("Id"),rs.getString("Codigo"),rs.getString("Descripcion"),
						  rs.getString("Talla"), rs.getString("Ubicacion"),rs.getString("Genero"), rs.getString("Familia"), rs.getInt("NroPiezas"), rs.getInt("Stock"), rs.getDouble("PrecioCompra"), rs.getDouble("PrecioAlquiler"), rs.getDouble("PrecioVenta"), null, null, 
						  
						0, 0, 0, 0
						);
				unidadRoot.cargarButtonAgregar(21);
				unidadRoot.setIdArticulo(rs.getInt("Id"));
				unidadRoot.setNroPiezasArticulo(rs.getInt("NroPiezas"));  
				unidadRoot.getJfxButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						agregarDetalleBoletaUnidad(unidadRoot);
					}
				});;
				final TreeItem<Unidad> root = 
						  
						  new TreeItem<>();
				  root.setValue(unidadRoot);
				  
				  pstPiezas=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, (select nombre from tipo_mantenimiento where Id=Pieza.TMant) as mantenimiento, PrecioCompra, PrecioAlquiler, PrecioVenta, if(ReqPlanchar=1,'Si','No') as reqPlanchars , Comentarios, Estado, Stock, ArticuloId from pieza where ArticuloId=?;");
				  pstPiezas.setInt(1, rs.getInt("Id"));
				  rsPiezas=pstPiezas.executeQuery();
				  ObservableList<Unidad> unidades=FXCollections.observableArrayList();
				  
				  //List<Unidad> unidades = Arrays.<Unidad> asList();
				  while(rsPiezas.next()){
					  Unidad unidad=new Unidad(Unidad.PIEZA,rsPiezas.getInt("Id"),rsPiezas.getString("Codigo"),rsPiezas.getString("Descripcion"),
							  null,null,null, null, 0, rsPiezas.getInt("Stock"), rsPiezas.getDouble("PrecioCompra"),
							  rsPiezas.getDouble("PrecioAlquiler"), rsPiezas.getDouble("PrecioVenta"), rsPiezas.getString("reqPlanchars"), rsPiezas.getString("mantenimiento"), 
									  
									  0, 0, 0, 0
									  );
					  unidad.cargarButtonAgregar(19);
					  unidad.getJfxButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							agregarDetalleBoletaUnidad(unidad);
						}
					});
					 
					  unidades.add(unidad);
					  
					  
					  

				  }
				  rsPiezas.close();
				  pstPiezas.close();
				  unidades.stream().forEach((unidad) -> {
					  root.getChildren().add(new TreeItem<>(unidad));
				    });
				  dummyRoot.getChildren().add(root);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al conectar!!!");
            error_alert.setHeaderText("No se pudo conectar con el servidor.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
			
		} finally{
			try {
				if (conexionServidor != null) {
					conexionServidor.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		treeTableViewArticulos.setRoot(dummyRoot);
	    treeTableViewArticulos.setShowRoot(false);
	    treeTableViewArticulos.refresh();
	}

	private void inicializarCamposTableDetalleBoleta(){
		tableColumnCantArticulos1.setCellValueFactory(new PropertyValueFactory<>("cant"));
		tableColumnCodigoArticulos1.setCellValueFactory(new PropertyValueFactory<>("codigo"));	
		tableColumnDescripcionArticulos1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnPrecioArticulos1.setCellValueFactory(new PropertyValueFactory<>("precioUnit"));
		tableColumnImporteArticulos1.setCellValueFactory(new PropertyValueFactory<>("importe"));
		tableColumnEliminarArticulos1.setCellValueFactory(new PropertyValueFactory<>("jfxButtonEliminar"));

	}
	
	
	private void agregarBoleta(){
		mBoleta.iniciarConexionServidor();
		int idBoleta=mBoleta.agregarBoleta(boleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MPadre.CORRECTO){
			boleta.setId(idBoleta);
			ULTIMO_ID_BOLETA=idBoleta;
			
		}
		
	}
	
	private DetalleBoleta agregarDetalleBoleta(Unidad unidad){
		DetalleBoleta detalleBoleta=new DetalleBoleta();
		detalleBoleta.setIdBoleta(boleta.getId());
		detalleBoleta.setTipoVenta(boleta.getTipo());
		detalleBoleta.setTipoPro(unidad.getTipoClase());
		if(unidad.getTipoClase()==Unidad.ARTICULO){
			detalleBoleta.setIdArticulo(unidad.getId());
		}if(unidad.getTipoClase()==Unidad.PIEZA){
			detalleBoleta.setIdPieza(unidad.getId());
		}
		detalleBoleta.getCant().setText("1");
		
		//campo redundante para guardar la cantidad vieja y no alterar la boleta
		
		
		mDetalleBoleta.iniciarConexionServidor();
		
		int idUltimo=mDetalleBoleta.agregarDetalleBoleta(detalleBoleta);
		
		if(mDetalleBoleta.getNoError()==MPadre.CORRECTO){
			detalleBoleta=mDetalleBoleta.seleccionarDetalleBoleta(idUltimo);
			detalleBoleta.cargarButtonAgregar();
			detalleBoleta.getJfxButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
				MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
				@Override
				public void handle(ActionEvent event) {
					
					mDetalleBoleta.iniciarConexionServidor();
					mDetalleBoleta.eliminarDetalleBoleta(idUltimo);
					if(mDetalleBoleta.getNoError()==CPadre.CORRECTO){
						tableViewArticulos1.getItems().remove(new DetalleBoleta(idUltimo));
						cargarPreciosCalculados();
					}
					mDetalleBoleta.cerrarConexionServidor();
					
					
					
				}
			});
			detalleBoleta.getCant().setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode()==KeyCode.ENTER){
						tableViewArticulos1.refresh(); 	
						cargarPreciosCalculados();

					}
					
				}
			});

			
		}else{

			detalleBoleta=null;
		}
		mDetalleBoleta.cerrarConexionServidor();
		return detalleBoleta;
	}
	
	public void cargarDetallePendiente(int idBoleta){
		MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
		mDetalleBoleta.iniciarConexionServidor();
		ObservableList<DetalleBoleta> arrayDetalleBoleta=mDetalleBoleta.seleccionarDetalleBoletaPorBoleta(idBoleta);
		mDetalleBoleta.cerrarConexionServidor();
		for (DetalleBoleta detalleBoleta : arrayDetalleBoleta) {
			detalleBoleta.cargarButtonAgregar();
			detalleBoleta.getJfxButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
				MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
				@Override
				public void handle(ActionEvent event) {
					
					mDetalleBoleta.iniciarConexionServidor();
					mDetalleBoleta.eliminarDetalleBoleta(detalleBoleta.getId());
					if(mDetalleBoleta.getNoError()==CPadre.CORRECTO){
						tableViewArticulos1.getItems().remove(new DetalleBoleta(detalleBoleta.getId()));
					}
					mDetalleBoleta.cerrarConexionServidor();
					
					
					
				}
			});
			detalleBoleta.getCant().setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode()==KeyCode.ENTER){
						tableViewArticulos1.refresh(); 	
						cargarPreciosCalculados();

					}
					
				}
			});
			tableViewArticulos1.getItems().add(detalleBoleta);
		}
		//tableViewArticulos1.setItems(arrayDetalleBoleta);
		tableViewArticulos1.refresh();
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
	
	private void actualizarBoleta(){
		
		
	}
	
	private void obtenerProductoBaseDatos (int tipo, String codigo){
		 
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
	
	
	public void agregarEventosGarantiajFXCheckBox(){
		jFXCheckBoxDniGarantia.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!jFXCheckBoxDniGarantia.isSelected()){
					textFieldNroDniGarantia.setDisable(true);
					textFieldNroDniGarantia.setText("");
				}else{
					textFieldNroDniGarantia.setDisable(false);
				}
				
			}
			
		});
		
		
		jFXCheckBoxDniMenorGarantia.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!jFXCheckBoxDniMenorGarantia.isSelected()){
					textFieldNroMenorGarantia.setDisable(true);
					textFieldNroMenorGarantia.setText("");
				}else{
					textFieldNroMenorGarantia.setDisable(false);
				}
			}
		});
		
		jFXCheckBoxLicenciaGarantia.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				if(!jFXCheckBoxLicenciaGarantia.isSelected()){
					textFieldNroLicenciaGarantia.setText("");
					textFieldNroLicenciaGarantia.setDisable(true);
				}else{
					textFieldNroLicenciaGarantia.setDisable(false);
				}
			}
		});
		
		jFXCheckBoxDineroGarantia.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				if(!jFXCheckBoxDineroGarantia.isSelected()){
					textFieldDineroGarantia.setDisable(true);
					textFieldDineroGarantia.setText("");
				}else{
					textFieldDineroGarantia.setDisable(false);
				}
			}
		});
		
		jFXCheckBoxOtroGarantia.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				if(!jFXCheckBoxOtroGarantia.isSelected()){
					textAreaOtroGarantia.setDisable(true);
					textAreaOtroGarantia.setText("");
				}else{
					textAreaOtroGarantia.setDisable(false);
				}
			}
		});
		
		jFXTCheckBoxBoletaAlqGarantia.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
				if(!jFXTCheckBoxBoletaAlqGarantia.isSelected()){
					buttonEnlazarBoleta.setDisable(true);
					buttonEliminarEnlazarBoleta.setDisable(true);
					textFieldSerieBoletaGarantia.setDisable(true);
					textFieldSerieBoletaGarantia.setText("");
					textFieldNroBoletaGarantia.setDisable(true);
					textFieldNroBoletaGarantia.setText("");
					boleta.setGarIdBoleta(-1);
					labelApellNomBoletaGarantia.setText("");
				}else{
					buttonEnlazarBoleta.setDisable(false);
					buttonEliminarEnlazarBoleta.setDisable(false);
					textFieldSerieBoletaGarantia.setDisable(false);
					textFieldNroBoletaGarantia.setDisable(false);
					labelApellNomBoletaGarantia.setText("");
				}
			}
		});
		
		
	
	}
	
	//p
	private void capturarDatosBoletaGarantia(){
		boleta.setGarDni(jFXCheckBoxDniGarantia.isSelected());
		boleta.setGarNroDni(textFieldNroDniGarantia.getText().trim());
		
		boleta.setGarDniMenor(jFXCheckBoxDniMenorGarantia.isSelected());
		boleta.setGarNroDniMenor(textFieldNroMenorGarantia.getText().trim());
		
		boleta.setGarLicencia(jFXCheckBoxLicenciaGarantia.isSelected());
		boleta.setGarNroLicencia(textFieldNroLicenciaGarantia.getText().trim());
		
		boleta.setGarDinero(jFXCheckBoxDineroGarantia.isSelected());
		boleta.setGarMonto(Double.parseDouble((textFieldDineroGarantia.getText()!=null && 
				!textFieldDineroGarantia.getText().trim().isEmpty())?
						textFieldDineroGarantia.getText().trim():"-0")
					);
		
		boleta.setGarOtro(jFXCheckBoxOtroGarantia.isSelected());
		boleta.setGarOtroEspecifique((textAreaOtroGarantia.getText()!=null && !textAreaOtroGarantia.getText().trim().isEmpty())?textAreaOtroGarantia.getText().trim():null);
		
		boleta.setGarEnlazarBoleta(jFXTCheckBoxBoletaAlqGarantia.isSelected());
		boleta.setGarSerieBoleta(textFieldSerieBoletaGarantia.getText().trim());
		boleta.setGarNumeroBoleta(Integer.parseInt((textFieldNroBoletaGarantia.getText()!=null &&
				!textFieldNroBoletaGarantia.getText().trim().isEmpty())?
						textFieldNroBoletaGarantia.getText().trim():"-1"));
		
		 
	}
	private void capturarDatosBoletaRecomendacion(){
		
	}
	private void capturarDatosBoletaSeparado(){
		boleta.setSepSeparar(jFXToggleButtonSeparado.isSelected());
		Date dateSepFechaRecojo=null;
		if(datePickerFechaRecojo.getValue()!=null){
			dateSepFechaRecojo= Date.from(datePickerFechaRecojo.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		boleta.setSepFechaRecojo(dateSepFechaRecojo);
		
		boleta.setSepACuenta(Double.valueOf((textFieldSepACuenta.getText().trim()!=null && !textFieldSepACuenta.getText().trim().isEmpty())?textFieldSepACuenta.getText().trim():"-1.0"));
		boleta.setSepSaldo(Double.valueOf((textFieldSepSaldo.getText().trim()!=null && !textFieldSepSaldo.getText().trim().isEmpty())?textFieldSepSaldo.getText().trim():"-1.0"));
	}
	
	private void capturarDatosBoletaDevolucion(){
		Date dateDevFechaEngrega=null;
		if(datePickerFechaEntrega.getValue()!=null){
			dateDevFechaEngrega=Date.from(datePickerFechaEntrega.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());		
		}
		boleta.setDevFechaEntrega(dateDevFechaEngrega);
		Date dateDevFechaDevolucion=null;
		if(datePickerFechaDevolucion.getValue()!=null){
			dateDevFechaDevolucion= Date.from(datePickerFechaDevolucion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
		boleta.setDevFechaDevolucion(dateDevFechaDevolucion);
		
	}
	
	private void capturarDatosBoleta(){
		capturarDatosBoletaGarantia();
		capturarDatosBoletaRecomendacion();
		capturarDatosBoletaSeparado();
		capturarDatosBoletaDevolucion();
	}
	//reservaciones

	public void inicializarCamposTableReservacion(){
		tableColumnIdReser.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnArticuloPiezaReser.setCellValueFactory(new PropertyValueFactory<>("articuloPieza"));
	 	tableColumnFechaReser.setCellValueFactory(new PropertyValueFactory<>("fecha"));
	 	tableColumnTipoReser.setCellValueFactory(new PropertyValueFactory<>("tipoFact"));
	 	tableColumnCodCliReser.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
	 	tableColumnDNIReser.setCellValueFactory(new PropertyValueFactory<>("dni"));
	 	tableColumnApellNomReser.setCellValueFactory(new PropertyValueFactory<>("apellNom"));
	 	tableColumnACuentaReser.setCellValueFactory(new PropertyValueFactory<>("aCuenta"));
	 	
	 	tableColumnTotalAPagarReser.setCellValueFactory(new PropertyValueFactory<>("totalAPagar"));
	 	tableColumnSaldoReser.setCellValueFactory(new PropertyValueFactory<>("saldo"));
	 	tableColumnfRecojoReser.setCellValueFactory(new PropertyValueFactory<>("fRecojo"));
	}
	
	private MReservacion mReservacion=new MReservacion();
	public void seleccionarReservacion(String buscar){
		mReservacion.iniciarConexionServidor();
		tableViewResevacion.setItems(mReservacion.seleccionarReservacion(buscar));
		tableViewResevacion.refresh();
		mReservacion.cerrarConexionServidor();
		
	}
	
	
	//alquilados en el dia
	
	public void inicializarCamposAlquilerHoy(){
		tableColumnNroListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableColumnSerieListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("serie"));
		tableColumnNumeroListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("numero"));
		tableColumnTipoListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("tipo"));
		tableColumnCodDniListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("codDni"));
		tableColumnApellNomListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("apellNom"));
		tableColumnSubTotalListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		tableColumnDesCuponListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("desCupones"));
		tableColumnDesAdicListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("desAdic"));
		tableColumnTotalListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("total"));
		tableColumnFEntregaListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("fEntrega"));
		tableColumnFDevolucionListAlqHoy.setCellValueFactory(new PropertyValueFactory<>("fDevolucion"));
		
	}

	
	private MTAlquilerHoy mTAlquilerHoy=new MTAlquilerHoy();
	public void seleccionarTAlquilerHoy(String buscar){
		mTAlquilerHoy.iniciarConexionServidor();
		TableViewListAlqHoy.setItems(mTAlquilerHoy.seleccionarTAlquilerHoy(buscar));
		TableViewListAlqHoy.refresh();
		mTAlquilerHoy.cerrarConexionServidor();
	}
	
	private void calcularTotalHoy(){
		double totalHoy=0;
		for (TAlquilerHoy alquiler : TableViewListAlqHoy.getItems()) {
			totalHoy=totalHoy+Double.parseDouble(alquiler.getTotal());
		}
		

		 DecimalFormat df = new DecimalFormat("#0.00");
		 String resultado = df.format(totalHoy);
		labelTotalHoy.setText(resultado);
	}
	
	
	//extraer Precios de pendientej o otro tipo de venta
	private void extraerPreciosBoleta(){
		jFXCheckBoxCupon1.setSelected(boleta.isCupon1());
		jFXCheckBoxCupon2.setSelected(boleta.isCupon2());
		jFXCheckBoxCupon3.setSelected(boleta.isCupon3());
		cargarPreciosCalculados();
	}
	
	
	//eliminar boleta y detalle de boleta
	
	public static boolean eliminarBoletAYDetalle (){
		
		
		return eliminarBoletAYDetalle(ULTIMO_ID_BOLETA);
	}
	
	public static boolean eliminarBoletAYDetalle (int idBoleta){
		boolean bool=false;
		if(idBoleta!=-1){
			MDetalleBoleta mDetalleBoleta= new MDetalleBoleta();
			mDetalleBoleta.iniciarConexionServidor();
			mDetalleBoleta.inicializarCommit();
			mDetalleBoleta.eliminarDetalleBoletaPorBoleta(idBoleta);
			MBoleta mBoleta=new MBoleta();
			mBoleta.setConexionServidor(mDetalleBoleta.getConexionServidor());
			mBoleta.eliminarBoletaPendientea(idBoleta);
			if(mBoleta.getNoError()==MPadre.CORRECTO){
				mBoleta.validarCambiosServidor();

				bool=true;
			}
				
			mBoleta.cerrarConexionServidor();

		}		
		return bool;
	}
	
	
	public boolean eliminarBoletAYDetalleReserva (int idBoleta){
		boolean bool=false;
		if(idBoleta!=-1){
			MDetalleBoleta mDetalleBoleta= new MDetalleBoleta();
			mDetalleBoleta.iniciarConexionServidor();
			mDetalleBoleta.inicializarCommit();
			mDetalleBoleta.eliminarDetalleBoletaPorBoleta(idBoleta);
			MBoleta mBoleta=new MBoleta();
			mBoleta.setConexionServidor(mDetalleBoleta.getConexionServidor());
			mBoleta.eliminarBoletaReserva(idBoleta);
			if(mBoleta.getNoError()==MPadre.CORRECTO){
				mBoleta.validarCambiosServidor();

				bool=true;
			}
				
			mBoleta.cerrarConexionServidor();

		}		
		return bool;
	}
	
	public boolean verificarCamposVacios(){
		if(boleta.getIdCliente()==-1){
			mostrarAlerta("Cliente", "", "Cliente no seleccionado.\nEste campo es requerido.", AlertType.WARNING);
			textFieldCodigoCliente.requestFocus();
			return false;
		}
		if(intReputacionCliente<=CProgress.VMROJO){
			mostrarAlerta("Cliente", "", "El cliente seleccionado\nno cumple con el nivel de reputación mínimo\nrequerido.", AlertType.WARNING);
			textFieldCodigoCliente.requestFocus();
			return false;
		}
		
		/*if(Double.parseDouble(boleta.getTotalPagar())==0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ 0.00.", AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}
		
		if(boleta.getTotalPagar()==null || boleta.getTotalPagar().isEmpty() || Double.parseDouble(boleta.getTotalPagar())==0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ 0.00.", AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}*/
		
		if(textFieldTotalPagar.getText()==null || textFieldTotalPagar.getText().isEmpty() || Double.parseDouble(textFieldTotalPagar.getText())<=0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ "+textFieldTotalPagar.getText()+".", AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}
		
		
		if(boleta.getRecIdCliente()==boleta.getIdCliente()){
			mostrarAlerta("Recomendación", "El cliente no se puede recomendar a si mismo.", ".", AlertType.WARNING);
			textFieldCodigoClienteRecome.requestFocus();
			return false;
		}
		
		if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
			
			if(!(boleta.isGarDni() || boleta.isGarDniMenor() || boleta.isGarLicencia() || boleta.isGarDinero()
				||	boleta.isGarOtro() || boleta.isGarEnlazarBoleta())){
				mostrarAlerta("Garantia", "", "Seleccione e ingrese como minimo una garantia.\nDato requerido.", AlertType.WARNING);
				jFXCheckBoxDniGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarDni() && isNullOrEmtpy(textFieldNroDniGarantia.getText())){
				mostrarAlerta("Garantia", "", "Ingrese Nro de DNI.\nDato requerido.", AlertType.WARNING);
				textFieldNroDniGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarDniMenor() && isNullOrEmtpy(textFieldNroMenorGarantia.getText())){
				mostrarAlerta("Garantia", "", "Ingrese Nro de DNI Menor .\nDato requerido.", AlertType.WARNING);
				textFieldNroMenorGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarLicencia() && isNullOrEmtpy(textFieldNroLicenciaGarantia.getText())){
				mostrarAlerta("Garantia", "", "Ingrese Nro de  Licencia .\nDato requerido.", AlertType.WARNING);
				textFieldNroLicenciaGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarDinero() && isNullOrEmtpy(textFieldDineroGarantia.getText())){
				mostrarAlerta("Garantia", "", "Ingrese Monto dejado como garantia.\nDato requerido.", AlertType.WARNING);
				textFieldDineroGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarOtro() && isNullOrEmtpy(textAreaOtroGarantia.getText())){
				mostrarAlerta("Garantia", "", "Especifique otro tipo de garantia.\nDato requerido.", AlertType.WARNING);
				textAreaOtroGarantia.requestFocus();
				return false;
			}
			
			if(boleta.isGarEnlazarBoleta() && boleta.getGarIdBoleta()==-1){
				mostrarAlerta("Garantia", "", "Especifique la serie y número de boleta.\nDato requerido.", AlertType.WARNING);
				textFieldSerieBoletaGarantia.requestFocus();
				return false;
			}
			
	
		}else if(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA){
			
		}
		
		if(jFXToggleButtonSeparado.isSelected()){
			if(datePickerFechaRecojo.getValue()==null){
				datePickerFechaRecojo.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese fecha de recojo.", AlertType.WARNING);
				
				return false;
			}
			if(!(textFieldSepACuenta.getText().trim()!=null && !textFieldSepACuenta.getText().trim().isEmpty())){
				textFieldSepACuenta.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese el monto que va dejar a cuenta.", AlertType.WARNING);
				return false;
				
			}
			if(!(textFieldSepSaldo.getText().trim()!=null && !textFieldSepSaldo.getText().trim().isEmpty())){
				textFieldSepSaldo.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese el saldo restando.", AlertType.WARNING);
				return false;
			}
			
		}
		
		if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
			if(datePickerFechaEntrega.getValue()==null){
				datePickerFechaEntrega.requestFocus();

				mostrarAlerta("Devolución", "", "Por favor ingresa la fecha de entrega.", AlertType.WARNING);
				return false;
			}
			
			if(datePickerFechaDevolucion.getValue()==null){
				datePickerFechaDevolucion.requestFocus();

				mostrarAlerta("Devolución", "", "Por favor ingrese la fecha de devolución.", AlertType.WARNING);
				return false;
			}
			
			if(datePickerFechaEntrega.getValue().isAfter(datePickerFechaDevolucion.getValue())){
				datePickerFechaDevolucion.requestFocus();

				mostrarAlerta("Devolución", "", "La fecha de entreega no puede ser despues a la fecha de devolución.", AlertType.WARNING);
				return false;
			}
			
		}
		

		return true;
	}

	public boolean verificarCamposVaciosReserva(){
		if(boleta.getIdCliente()==-1){
			mostrarAlerta("Cliente", "", "Cliente no seleccionado.\nEste campo es requerido.", AlertType.WARNING);
			textFieldCodigoCliente.requestFocus();
			return false;
		}
		if(intReputacionCliente<=CProgress.VMROJO){
			mostrarAlerta("Cliente", "", "El cliente seleccionado\nno cumple con el nivel de reputación mínimo\nrequerido.", AlertType.WARNING);
			textFieldCodigoCliente.requestFocus();
			return false;
		}
		
		/*if(Double.parseDouble(boleta.getTotalPagar())==0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ 0.00.", AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}
		
		if(boleta.getTotalPagar()==null || boleta.getTotalPagar().isEmpty() || Double.parseDouble(boleta.getTotalPagar())==0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ 0.00.", AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}*/
		
		if(textFieldTotalPagar.getText()==null || textFieldTotalPagar.getText().isEmpty() || Double.parseDouble(textFieldTotalPagar.getText())<=0.0){
			mostrarAlerta("Facturación", "", "No se puede facturar por S/ "+textFieldTotalPagar.getText(), AlertType.WARNING);
			textFieldTotalPagar.requestFocus();
			return false;
		}
		
		if(boleta.getRecIdCliente()==boleta.getIdCliente()){
			mostrarAlerta("Recomendación", "El cliente no se puede recomendar a si mismo.", ".", AlertType.WARNING);
			textFieldCodigoClienteRecome.requestFocus();
			return false;
		}
		
		
		if(jFXToggleButtonSeparado.isSelected()){
			if(datePickerFechaRecojo.getValue()==null){
				datePickerFechaRecojo.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese fecha de recojo.", AlertType.WARNING);
				
				return false;
			}
			if(!(textFieldSepACuenta.getText().trim()!=null && !textFieldSepACuenta.getText().trim().isEmpty())){
				textFieldSepACuenta.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese el monto que va dejar a cuenta.", AlertType.WARNING);
				return false;
				
			}
			if(!(textFieldSepSaldo.getText().trim()!=null && !textFieldSepSaldo.getText().trim().isEmpty())){
				textFieldSepSaldo.requestFocus();
				mostrarAlerta("Separar", "", "Por favor ingrese el saldo restando.", AlertType.WARNING);
				return false;
			}
			
		}
		
	
		

		return true;
	}

	
	public boolean calcularSepSaldo(){
		
		double aCuenta=0;
		double totalAPagar=0;
		try{
			textFieldSepACuenta.setText(Validacion.doubleAStringMoneda(Double.parseDouble(textFieldSepACuenta.getText())));
			
			
			aCuenta=Double.parseDouble(textFieldSepACuenta.getText());
			totalAPagar=Double.parseDouble(textFieldTotalPagar.getText());
			
			if(aCuenta>totalAPagar){
				mostrarAlerta("Separación", "", "El monto \"A cuenta\" es mayor que el monto \"Total A Pagar\".", AlertType.WARNING);
				textFieldSepACuenta.requestFocus();
				return false;
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			textFieldSepSaldo.setText(" ");
			mostrarAlerta("Separación", "", "Surgio un error al tratar de comvertir\nel dato del campo \"A Cuenta\"", AlertType.WARNING);
			textFieldSepACuenta.requestFocus();
			return false;
		}
		double sepSaldo=totalAPagar-aCuenta;
		

		textFieldSepSaldo.setText(Validacion.doubleAStringMoneda(sepSaldo));
		
		return true;
		
	}
	
	
	public boolean  calcularSepFecha(){

		//date
		return true;
	}
	
	
	private void nuevoAlquiler(){
		//metodo para cargar una nueva boleta y limpiar campos
		if(boleta.getEstadoAccion()==1){
			eliminarBoletAYDetalle();
		}
		
		
		boleta=new Boleta();
		tableViewArticulos1.getItems().clear();
		tableViewArticulos1.refresh();
		//TableViewListAlqHoy.getItems().clear();
		//TableViewListAlqHoy.refresh();

		boleta.setTipo(Boleta.TIPO_VENTA_ALQUILER);
		agregarBoleta();
		extraerDatosBoleta();
		//cargarDetallePendiente(boleta.getId());

		if(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA){
			habilitarCamposVenta();
			deshabilitarCamposDevolucion(true, boleta.getTipo());
			deshabilitarCamposGarantia(true);
			deshabilitarCamposSeparado(true);
			datePickerFechaEntrega.setValue(null);

		}else if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
			habilitaComposAlquiler();
			deshabilitarCamposDevolucion(false, boleta.getTipo());
			deshabilitarCamposGarantia(false);
			deshabilitarCamposSeparado(true);
			deshabilitarCamposSeparado(true);
			datePickerFechaEntrega.setValue(LocalDate.now());
		}	
		cargarPreciosCalculados();
	}
	
	
	
	public void cargarComboBoxFamilia(){
		MDatoAtomico dDatoAtomico=new MDatoAtomico();
		dDatoAtomico.iniciarConexionServidor();
		DatoAtomico todos=new DatoAtomico();
		todos.setId(0);
		todos.setNombre("Todos");
		comboBoxFamiliaArticulosPiezas.getItems().add(todos);
		comboBoxFamiliaArticulosPiezas.getItems().addAll(dDatoAtomico.seleccionarDatosAtomicos(MDatoAtomico.QUERY_SELECT_FAMILIAS));
		dDatoAtomico.cerrarConexionServidor();
	}

	private void agregarDetalleBoletaUnidad(Unidad unidadRoot){
		DetalleBoleta detalleBoleta=agregarDetalleBoleta(unidadRoot);
		if(detalleBoleta!=null){
			//tableViewArticulos.getItems().add
			tableViewArticulos1.getItems().add(detalleBoleta);
			tableViewArticulos1.refresh();
			if(actualizarBoletaDescuentos()){
				cargarPreciosCalculados();
			}
		}
		/*
		DetalleBoleta detalleBoleta=agregarDetalleBoleta(unidad);
		if(detalleBoleta!=null){
			tableViewArticulos1.getItems().add(detalleBoleta);
			tableViewArticulos1.refresh();
			if(actualizarBoletaDescuentos()){
				cargarPreciosCalculados();
			}
		}*/
	}
}
	


