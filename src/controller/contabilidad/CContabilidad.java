package controller.contabilidad;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Egreso;
import dal.TRecibo;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.contabilidad.MEgreso;

public class CContabilidad extends CPadre implements Initializable{
	
	
	//ingreso boleta
	private BorderPane borderPaneIngresoRecibo=null;
	@FXML private Tab tabIngresos;
	//

	@FXML private DatePicker datePickerEgresoFechaFin;
	@FXML private DatePicker datePickerEgresoFechaInicio;
	@FXML private Button buttonBuscar;
	@FXML private Label labelTotalEgresado;
	
	
	
	@FXML private JFXButton jfxButtonNuevoEgreso;
	@FXML private TextField textFieldBuscarEgreso;
	@FXML private TableView<Egreso> tableViewEgreso;
	@FXML private TableColumn<Egreso, Integer> tableColumnEId;
	@FXML private TableColumn<Egreso, String> tableColumnNombreRazon;
	@FXML private TableColumn<Egreso, Integer> tableColumnETipoDocumento;
	@FXML private TableColumn<Egreso, String> tableColumnESerie;
	@FXML private TableColumn<Egreso, String> tableColumnENro;
	@FXML private TableColumn<Egreso, String> tableColumnERuc;
	@FXML private TableColumn<Egreso, String> tableColumnEDescripcion;
	@FXML private TableColumn<Egreso, Date> tableColumnEFechaEmision;
	@FXML private TableColumn<Egreso, Double> tableColumnMonto;
	@FXML private TableColumn<Egreso, String> tableColumnEComentarios;
	@FXML private TableColumn<Egreso, String> tableColumnEOpciones;
	@FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    
    private MenuItem menuItemEditarEgreso =new MenuItem("Editar Egreso");
    private MenuItem menuItemEliminarEgreso =new MenuItem("Eliminar Egreso");
  
    private int id = -1;
    
    MEgreso mEgreso = new MEgreso();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePickerEgresoFechaFin.setValue(LocalDate.now());
		datePickerEgresoFechaInicio.setValue(LocalDate.of(2019, 01, 01));
		cargarInterfazReciboIngreso();
		inicializarTableViewEgreso();
		//recargarTabla();
		buscarEgreso();
		
		jfxButtonNuevoEgreso.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/contabilidad/AddEgreso.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
								
				buscarEgreso();
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
			}
		});
		
		textFieldBuscarEgreso.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarEgreso();
			}
		});
		
		
		buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				buscarEgreso();
			}
		});
		
		
		tableViewEgreso.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

			@Override
			public void handle(javafx.scene.input.MouseEvent e) {
				if (e.getClickCount()==2) {
					id = tableViewEgreso.getSelectionModel().getSelectedItem().getEgreId();
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/contabilidad/AddEgreso.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);

				}
			}
		});
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				buscarEgreso();
			}
		});
		
		menuItemEditarEgreso.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewEgreso.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewEgreso.getSelectionModel().getSelectedItem().getEgreId();
            	
            	String url = "/view/contabilidad/AddEgreso.fxml";
				String css = "/estilocss/EstiloModal.css";
		
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		menuItemEliminarEgreso.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				id = tableViewEgreso.getSelectionModel().getSelectedItem().getEgreId();
				if (ventanaEliminar()) {
					eliminar(id);
					mostrarNotificacion(mEgreso.getMensaje(), mEgreso.getNoError());
				}
			}
		});
		
		contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemEditarEgreso, menuItemEliminarEgreso);
		
	}
	
	public void inicializarTableViewEgreso(){
		tableColumnEId.setCellValueFactory(new PropertyValueFactory<>("egreId"));
		tableColumnETipoDocumento.setCellValueFactory(new PropertyValueFactory<>("egreDocumento"));
		tableColumnNombreRazon.setCellValueFactory(new PropertyValueFactory<>("egreNombreRazon"));
		tableColumnESerie.setCellValueFactory(new PropertyValueFactory<>("egreSerieDoc"));
		tableColumnENro.setCellValueFactory(new PropertyValueFactory<>("egreNroDoc"));
		tableColumnERuc.setCellValueFactory(new PropertyValueFactory<>("egreRuc"));
		tableColumnEDescripcion.setCellValueFactory(new PropertyValueFactory<>("egreDescripcion"));
		tableColumnEFechaEmision.setCellValueFactory(new PropertyValueFactory<>("egreFechaEmision"));
		tableColumnMonto.setCellValueFactory(new PropertyValueFactory<>("egreMonto"));
		tableColumnEComentarios.setCellValueFactory(new PropertyValueFactory<>("egreComentarios"));
		tableColumnEOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
	
	public void recargarTablas(){
		
		ObservableList<Egreso> observableListEgreso = null; 
		mEgreso.iniciarConexionServidor();
		observableListEgreso = mEgreso.seleccionarColeccionEgreso();
		tableViewEgreso.setItems(observableListEgreso);
		mEgreso.cerrarConexionServidor();
		
		for (Egreso egreso : observableListEgreso) {
			egreso.getOpciones().getjFXButtonVer().setVisible(false);
			
			egreso.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = egreso.getEgreId();
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/contabilidad/AddEgreso.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				}
			});
			
			egreso.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = egreso.getEgreId();
					if (ventanaEliminar()) {
						eliminar(id);
						mostrarNotificacion("Egreso "+mEgreso.getMensaje(), mEgreso.getNoError());
					}
				}
			});
			
			egreso.getOpciones().getjFXButtonAgregar().setVisible(false);
		}
		tableViewEgreso.refresh();
		
		/*Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
				
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();*/
	}
	
	public void buscarEgreso(){
		
		LocalDate fi = java.sql.Date.valueOf(datePickerEgresoFechaInicio.getValue()).toLocalDate();
		LocalDate ff = java.sql.Date.valueOf(datePickerEgresoFechaFin.getValue()).toLocalDate();
		
		
		ObservableList<Egreso> observableListEgreso1 = null;
		mEgreso.iniciarConexionServidor();
		observableListEgreso1 = mEgreso.BuscarBDEgreso(textFieldBuscarEgreso.getText().trim(),fi,ff );
		tableViewEgreso.setItems(observableListEgreso1);
		mEgreso.cerrarConexionServidor();
		
		for (Egreso egreso : observableListEgreso1) {
			egreso.getOpciones().getjFXButtonVer().setVisible(false);
			
			egreso.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = egreso.getEgreId();
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/contabilidad/AddEgreso.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				}
			});
			
			egreso.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = egreso.getEgreId();
					if (ventanaEliminar()) {
						eliminar(id);
						mostrarNotificacion("Egreso "+mEgreso.getMensaje(), mEgreso.getNoError());
					}
				}
			});
			
			egreso.getOpciones().getjFXButtonAgregar().setVisible(false);
		}
		tableViewEgreso.refresh();
		
		//Thread.sleep(61700);
		
		
		/*Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();*/
		
		double totalHoy=0;
		for (Egreso trecibo : observableListEgreso1) {
			totalHoy=totalHoy+Double.parseDouble(trecibo.getEgreMonto());
		}
		

		 DecimalFormat df = new DecimalFormat("#0.00");
		 String resultado = df.format(totalHoy);
		 labelTotalEgresado.setText(resultado);
		 
		 
		 
	}
	
	public boolean eliminar(int idEliminar){
		mEgreso.iniciarConexionServidor();
		mEgreso.eliminarEgreso(idEliminar);
		mEgreso.cerrarConexionServidor();
		return true;
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
	
	//metodo para agregar el recibos a ingresos
	public void cargarInterfazReciboIngreso(){
		try {
			borderPaneIngresoRecibo=obtenerBorderPaneInterna("/view/recibo/InterfazRecibo.fxml", "");
			tabIngresos.setContent(borderPaneIngresoRecibo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
