package controller.estadistica;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Articulo;
import dal.Cliente;
import dal.Estadistica;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.estadistica.MEstadisticaAnual;
import model.estadistica.MEstadisticaConcepto;
import model.estadistica.MEstadisticaMensual;
import model.estadistica.MEstadisticaRankingAC;
import model.estadistica.MEstadisticaSemanal;

public class CEstadistica extends CPadre implements Initializable {
	
	@FXML private TitledPane titlePaneTotalIngresoConcepto;
    @FXML private TableView<Estadistica> tableViewIngresoConcepto;
    @FXML private TableColumn<Estadistica, String> tableColumnIConcepto;
    @FXML private TableColumn<Estadistica, String> tableColumnICTotal;
    
    @FXML private TitledPane titlePaneTotalEgresoConcepto;
    @FXML private TableView<Estadistica> tableViewEgresoConcepto;
    @FXML private TableColumn<Estadistica, String> tableColumnEConcepto;
    @FXML private TableColumn<Estadistica, String> tableColumnECTotal;
    
    @FXML private TitledPane titlePaneTotalBalanceConcepto;
    @FXML private BarChart<String, Number> barCharConcepto;
    @FXML private Label labelInformacionConcepto;
    
    @FXML private TitledPane titlePaneTotalIngresoSemanal;
    @FXML private TableView<Estadistica> tableViewIngresoSemanal;
    @FXML private TableColumn<Estadistica, String> tableColumnISemanal;
    @FXML private TableColumn<Estadistica, String> tableColumnISTotal;
    
    @FXML private TitledPane titlePaneTotalEgresoSemanal;
    @FXML private TableView<Estadistica> tableViewEgresoSemanal;
    @FXML private TableColumn<Estadistica, String> tableColumnESemanal;
    @FXML private TableColumn<Estadistica, String> tableColumnESTotal;
    
    @FXML private TitledPane titlePaneTotalBalanceSemanal;
    @FXML private BarChart<String, Number> barCharSemanal;
    @FXML private Label labelInformacionSemanal;
    
    @FXML private TitledPane titlePaneTotalIngresoMensual;
    @FXML private TableView<Estadistica> tableViewIngresoMensual;
    @FXML private TableColumn<Estadistica, String> tableColumnIMensual;
    @FXML private TableColumn<Estadistica, String> tableColumnIMTotal;
    
    @FXML private TitledPane titlePaneTotalEgresoMensual;
    @FXML private TableView<Estadistica> tableViewEgresoMensual;
    @FXML private TableColumn<Estadistica, String> tableColumnEMensual;
    @FXML private TableColumn<Estadistica, String> tableColumnEMTotal;
    
    @FXML private TitledPane titlePaneTotalBalanceMensual;
    @FXML private BarChart<String, Number> barCharMensual;
    @FXML private Label labelInformacionMensual;
    
    @FXML private TitledPane titlePaneTotalIngresoAnual;
    @FXML private TableView<Estadistica> tableViewIngresoAnual;
    @FXML private TableColumn<Estadistica, String> tableColumnIAnual;
    @FXML private TableColumn<Estadistica, String> tableColumnIATotal;
    
    @FXML private TitledPane titlePaneTotalEgresoAnual;
    @FXML private TableView<Estadistica> tableViewEgresoAnual;
    @FXML private TableColumn<Estadistica, String> tableColumnEAnual;
    @FXML private TableColumn<Estadistica, String> tableColumnEATotal;
    
    @FXML private TitledPane titlePaneTotalBalanceAnual;
    @FXML private BarChart<String, Number> barCharAnual;
    @FXML private Label labelInformacionAnual;
    
    @FXML private TableView<Articulo> tableViewArticulos;
    @FXML private TableColumn<Articulo, String> tableColumnCodigoArt;
    @FXML private TableColumn<Articulo, String> tableColumnArticulo;
    @FXML private TableColumn<Articulo, Integer> tableColumnACantidad;
    
    @FXML private BarChart<String, Number> barCharArticulos;
    @FXML private Label labelInformacionArticulos;
    
    @FXML private TableView<Cliente> tableViewClientes;
    @FXML private TableColumn<Cliente, String> tableColumnCodigoCli;
    @FXML private TableColumn<Cliente, String> tableColumnCliente;
    @FXML private TableColumn<Cliente, Integer> tableColumnCCantidad;
    
    @FXML private BarChart<String, Number> barCharClientes;
    @FXML private Label labelInformacionCliente;
    
    @FXML private JFXButton jfxButtonRefrescar;
    @FXML private JFXButton jfxButtonRefrescar2;
    @FXML private JFXButton jfxButtonRefrescar3;
    @FXML private JFXButton jfxButtonRefrescar4;
    @FXML private JFXButton jfxButtonRefrescar5;
    @FXML private JFXButton jfxButtonRefrescar6;
    
    
    Estadistica estadistica = new Estadistica();
    MEstadisticaConcepto mEstadisticaConcepto = new MEstadisticaConcepto();
    MEstadisticaSemanal mEstadisticaSemanal = new MEstadisticaSemanal();
    MEstadisticaMensual mEstadisticaMensual = new MEstadisticaMensual();
    MEstadisticaAnual mEstadisticaAnual = new MEstadisticaAnual();
    MEstadisticaRankingAC mEstadisticaRankingAC = new MEstadisticaRankingAC();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		cargarEstadisticaConcepto();
		balanceConcepto();
		
		cargarEstadisticaSemanal();
		balanceSemanal();
		
		cargarEstadisticaMensual();
		balanceMensual();
		
		cargarEstadisticaAnual();
		balanceAnual();
		
		cargarEstadisticaRankingArticulosClientes();
			
		inicializarTableViewIngresoConcepto();
		inicializarTableViewEgresoConcepto();
		cargarTablaIngresoEgresoConcepto();
		
		inicializarTableViewIngresoSemanal();
		inicializarTableViewEgresoSemanal();
		cargarTablaIngresoEgresoSemanal();
			
		inicializarTableViewIngresoMensual();		
		inicializarTableViewEgresoMensual();
		cargarTablaIngresoEgresoMensual();
		
		inicializarTableViewIngresoAnual();		
		inicializarTableViewEgresoAnual();
		cargarTablaIngresoEgresoAnual();
		
		inicializarTableViewRankingArticulosAlquilados();		
		inicializarTableViewClientesRecurrentes();
		cargarTablaRankingArticulosClientes();
		
		jfxButtonRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
		jfxButtonRefrescar2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
		jfxButtonRefrescar3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
		jfxButtonRefrescar4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
		jfxButtonRefrescar5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
		jfxButtonRefrescar6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				refresh();
			}
		});
		
	}
	
	public void refresh(){
		cargarTablaIngresoEgresoAnual();
		cargarTablaIngresoEgresoConcepto();
		cargarTablaIngresoEgresoMensual();
		cargarTablaIngresoEgresoSemanal();
		cargarTablaRankingArticulosClientes();
		balanceAnual();
		balanceConcepto();
		balanceMensual();
		balanceSemanal();
	}
	
	public void inicializarTableViewIngresoConcepto(){
		tableColumnIConcepto.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnICTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewEgresoConcepto(){
		tableColumnEConcepto.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnECTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewIngresoSemanal(){
		tableColumnISemanal.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnISTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewEgresoSemanal(){
		tableColumnESemanal.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnESTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewIngresoMensual(){
		tableColumnIMensual.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnIMTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewEgresoMensual(){
		tableColumnEMensual.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnEMTotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewIngresoAnual(){
		tableColumnIAnual.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnIATotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewEgresoAnual(){
		tableColumnEAnual.setCellValueFactory(new PropertyValueFactory<>("estad_conceptoMes"));
		tableColumnEATotal.setCellValueFactory(new PropertyValueFactory<>("estad_totalConceptoMes"));
	}
	
	public void inicializarTableViewRankingArticulosAlquilados(){
		tableColumnCodigoArt.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnArticulo.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnACantidad.setCellValueFactory(new PropertyValueFactory<>("cantAlquilado"));
	}
	
	public void inicializarTableViewClientesRecurrentes(){
		tableColumnCodigoCli.setCellValueFactory(new PropertyValueFactory<>("cli_codigo"));
		tableColumnCliente.setCellValueFactory(new PropertyValueFactory<>("cli_apellNom"));
		tableColumnCCantidad.setCellValueFactory(new PropertyValueFactory<>("cli_cantRecurrencia"));
	}
	
	/*controller de Modelo Estadistica Concepto*/
	public void cargarTablaIngresoEgresoConcepto(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

			
					mEstadisticaConcepto.iniciarConexionServidor();
					tableViewIngresoConcepto.setItems(mEstadisticaConcepto.seleccionarColeccionIC());
					tableViewEgresoConcepto.setItems(mEstadisticaConcepto.seleccionarColeccionEC());
					mEstadisticaConcepto.cerrarConexionServidor();
					tableViewIngresoConcepto.refresh();
					tableViewEgresoConcepto.refresh();

					return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void balanceConcepto(){
		mEstadisticaConcepto.iniciarConexionServidor();
		estadistica = mEstadisticaConcepto.balanceConcepto();
		mEstadisticaConcepto.cerrarConexionServidor();
		
		titlePaneTotalIngresoConcepto.setText(String.valueOf(estadistica.getEstad_ingreso()));
		titlePaneTotalEgresoConcepto.setText(String.valueOf(estadistica.getEstad_egreso()));
		titlePaneTotalBalanceConcepto.setText(String.valueOf(estadistica.getEstad_balance()));
	}
	
	private void cargarEstadisticaConcepto(){
		ObservableList<XYChart.Series<String, Number>> arrayConcepto=null;
		XYChart.Series<String, Number> concepto=null;
		
		mEstadisticaConcepto.iniciarConexionServidor();
		arrayConcepto=mEstadisticaConcepto.estadisticaIngresoConcepto();
		barCharConcepto.getData().setAll(arrayConcepto);
		mEstadisticaConcepto.cerrarConexionServidor();
		
		concepto=arrayConcepto.get(0);
		concepto.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionConcepto.setVisible(true);
				labelInformacionConcepto.setTranslateX(e.getSceneX()-220);
				labelInformacionConcepto.setTranslateY(e.getSceneY()-350);
				labelInformacionConcepto.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		concepto=arrayConcepto.get(1);
		concepto.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionConcepto.setVisible(true);
				labelInformacionConcepto.setTranslateX(e.getSceneX()-220);
				labelInformacionConcepto.setTranslateY(e.getSceneY()-350);
				labelInformacionConcepto.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		concepto=arrayConcepto.get(2);
		concepto.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionConcepto.setVisible(true);
				labelInformacionConcepto.setTranslateX(e.getSceneX()-220);
				labelInformacionConcepto.setTranslateY(e.getSceneY()-350);
				labelInformacionConcepto.setText(String.valueOf(data.getYValue()));  
			});
		});
	}
	/*Termina controller de Modelo Estadistica Concepto*/
	
	/*Controller de Modelo Estadistica Semanal*/
	public void cargarTablaIngresoEgresoSemanal(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				
					mEstadisticaSemanal.iniciarConexionServidor();
					tableViewIngresoSemanal.setItems(mEstadisticaSemanal.seleccionarColeccionIS());
					tableViewEgresoSemanal.setItems(mEstadisticaSemanal.seleccionarColeccionES());
					mEstadisticaSemanal.cerrarConexionServidor();
					tableViewIngresoSemanal.refresh();
					tableViewEgresoSemanal.refresh();
					Thread.sleep(30273);
				
					return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void balanceSemanal(){
		mEstadisticaSemanal.iniciarConexionServidor();
		estadistica = mEstadisticaSemanal.balanceSemanal();
		mEstadisticaSemanal.cerrarConexionServidor();
		
		titlePaneTotalIngresoSemanal.setText(String.valueOf(estadistica.getEstad_ingreso()));
		titlePaneTotalEgresoSemanal.setText(String.valueOf(estadistica.getEstad_egreso()));
		titlePaneTotalBalanceSemanal.setText(String.valueOf(estadistica.getEstad_balance()));
	}
	
	private void cargarEstadisticaSemanal(){
		ObservableList<XYChart.Series<String, Number>> arraySemanal=null;
		XYChart.Series<String, Number> semanal=null;
		
		mEstadisticaSemanal.iniciarConexionServidor();
		arraySemanal=mEstadisticaSemanal.estadisticaIESemanal();
		barCharSemanal.getData().setAll(arraySemanal);
		mEstadisticaSemanal.cerrarConexionServidor();
		
		semanal=arraySemanal.get(0);
		semanal.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionSemanal.setVisible(true);
				labelInformacionSemanal.setTranslateX(e.getSceneX()-800);
				labelInformacionSemanal.setTranslateY(e.getSceneY()-350);
				labelInformacionSemanal.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		semanal=arraySemanal.get(1);
		semanal.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionSemanal.setVisible(true);
				labelInformacionSemanal.setTranslateX(e.getSceneX()-800);
				labelInformacionSemanal.setTranslateY(e.getSceneY()-350);
				labelInformacionSemanal.setText(String.valueOf(data.getYValue()));  
			});
		});
	}
	/*Termina controller de Modelo Estadistica Semanal*/
	
	/*Controller de Modelo Estadistica Mensual*/
	public void cargarTablaIngresoEgresoMensual(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

			
					mEstadisticaMensual.iniciarConexionServidor();
					tableViewIngresoMensual.setItems(mEstadisticaMensual.seleccionarColeccionIM());
					tableViewEgresoMensual.setItems(mEstadisticaMensual.seleccionarColeccionEM());
					mEstadisticaMensual.cerrarConexionServidor();
					tableViewIngresoMensual.refresh();
					tableViewEgresoMensual.refresh();
					Thread.sleep(30273);
				
					return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void balanceMensual(){
		mEstadisticaMensual.iniciarConexionServidor();
		estadistica = mEstadisticaMensual.balanceMensual();
		mEstadisticaMensual.cerrarConexionServidor();
		
		titlePaneTotalIngresoMensual.setText(String.valueOf(estadistica.getEstad_ingreso()));
		titlePaneTotalEgresoMensual.setText(String.valueOf(estadistica.getEstad_egreso()));
		titlePaneTotalBalanceMensual.setText(String.valueOf(estadistica.getEstad_balance()));
	}
	
	private void cargarEstadisticaMensual(){		
		ObservableList<XYChart.Series<String, Number>> arrayMensual=null;
		XYChart.Series<String, Number> mensual=null;
		
		mEstadisticaMensual.iniciarConexionServidor();
		arrayMensual=mEstadisticaMensual.estadisticaIEMensual();
		barCharMensual.getData().setAll(arrayMensual);
		mEstadisticaMensual.cerrarConexionServidor();
		
		mensual=arrayMensual.get(0);
		mensual.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionMensual.setVisible(true);
				labelInformacionMensual.setTranslateX(e.getSceneX()-220);
				labelInformacionMensual.setTranslateY(e.getSceneY()-350);
				labelInformacionMensual.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		mensual=arrayMensual.get(1);
		mensual.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionMensual.setVisible(true);
				labelInformacionMensual.setTranslateX(e.getSceneX()-220);
				labelInformacionMensual.setTranslateY(e.getSceneY()-350);
				labelInformacionMensual.setText(String.valueOf(data.getYValue()));  
			});
		});
	}
	/*Termina controller de Modelo Estadistica Mensual*/
	
	/*Controller de Modelo Estadistica Anual*/
	public void cargarTablaIngresoEgresoAnual(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

					mEstadisticaAnual.iniciarConexionServidor();
					tableViewIngresoAnual.setItems(mEstadisticaAnual.seleccionarColeccionIA());
					tableViewEgresoAnual.setItems(mEstadisticaAnual.seleccionarColeccionEA());
					mEstadisticaAnual.cerrarConexionServidor();
					tableViewIngresoAnual.refresh();
					tableViewEgresoAnual.refresh();
					Thread.sleep(30273);
					
					return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void balanceAnual(){
		mEstadisticaAnual.iniciarConexionServidor();
		estadistica = mEstadisticaAnual.balanceAnual();
		mEstadisticaAnual.cerrarConexionServidor();
		
		titlePaneTotalIngresoAnual.setText(String.valueOf(estadistica.getEstad_ingreso()));
		titlePaneTotalEgresoAnual.setText(String.valueOf(estadistica.getEstad_egreso()));
		titlePaneTotalBalanceAnual.setText(String.valueOf(estadistica.getEstad_balance()));
	}
	
	private void cargarEstadisticaAnual(){
		ObservableList<XYChart.Series<String, Number>> arrayAnual=null;
		XYChart.Series<String, Number> anual=null;
		
		mEstadisticaAnual.iniciarConexionServidor();
		arrayAnual=mEstadisticaAnual.estadisticaIEAnual();
		barCharAnual.getData().setAll(arrayAnual);
		mEstadisticaAnual.cerrarConexionServidor();
		
		anual=arrayAnual.get(0);
		anual.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionAnual.setVisible(true);
				labelInformacionAnual.setTranslateX(e.getSceneX()-800);
				labelInformacionAnual.setTranslateY(e.getSceneY()-350);
				labelInformacionAnual.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		anual=arrayAnual.get(1);
		anual.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionAnual.setVisible(true);
				labelInformacionAnual.setTranslateX(e.getSceneX()-800);
				labelInformacionAnual.setTranslateY(e.getSceneY()-350);
				labelInformacionAnual.setText(String.valueOf(data.getYValue()));  
			});
		});
	}
	/*Termina controller de Modelo Estadistica Anual*/
	
	/*Controller de Modelo Estadistica Ranking y Recurrencia*/
	public void cargarTablaRankingArticulosClientes(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				
					mEstadisticaRankingAC.iniciarConexionServidor();
					tableViewArticulos.setItems(mEstadisticaRankingAC.seleccionarColeccionRAArticulo());
					tableViewClientes.setItems(mEstadisticaRankingAC.seleccionarColeccionRRCliente());
					mEstadisticaRankingAC.cerrarConexionServidor();
					tableViewArticulos.refresh();
					tableViewClientes.refresh();
					Thread.sleep(30000);
				
					return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
		
	private void cargarEstadisticaRankingArticulosClientes(){
		ObservableList<XYChart.Series<String, Number>> arrayArticulos=null;
		ObservableList<XYChart.Series<String, Number>> arrayClientes=null;
		XYChart.Series<String, Number> articulos=null;
		XYChart.Series<String, Number> clientes=null;
		mEstadisticaRankingAC.iniciarConexionServidor();
		
		arrayArticulos=mEstadisticaRankingAC.estadisticaRankingArticulosAlquilados();
		barCharArticulos.getData().setAll(arrayArticulos);
		
		arrayClientes=mEstadisticaRankingAC.estadisticaClientesRecurrentes();
		barCharClientes.getData().setAll(arrayClientes);
		mEstadisticaRankingAC.cerrarConexionServidor();
	
		articulos=arrayArticulos.get(0);
		articulos.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionArticulos.setVisible(true);
				labelInformacionArticulos.setTranslateX(e.getSceneX()-220);
				labelInformacionArticulos.setTranslateY(e.getSceneY()-300);
				labelInformacionArticulos.setText(String.valueOf(data.getYValue()));  
			});
		});
		
		clientes=arrayClientes.get(0);
		clientes.getData().stream().forEach(data1->{
			data1.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionCliente.setVisible(true);
				labelInformacionCliente.setTranslateX(e.getSceneX()-800);
				labelInformacionCliente.setTranslateY(e.getSceneY()-300);
				labelInformacionCliente.setText(String.valueOf(data1.getYValue()));  
			});
		});
		
		
	}
	/*Termina controller de Modelo Estadistica Ranking y Recurrencia*/
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {

	}

	@Override
	public Object getObjeto() {
		return null;
	}

}
