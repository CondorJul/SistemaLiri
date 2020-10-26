package controller.recomendacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Cliente;
import dal.Recomendacion;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.cliente.MCliente;
import model.recomendacion.MRecomendacion;
import sesion.Permisos;

public class CRecomendacion extends CPadre implements Initializable {
	
	@FXML private JFXButton jfxButtonConfiguracion;
    @FXML private TextField textFieldBuscarCliente;
    @FXML private TableView<Cliente> tableViewCliente;
    @FXML private TableColumn<Cliente, Integer> tableColumnId;
    @FXML private TableColumn<Cliente, String> tableColumnCodigo;
    @FXML private TableColumn<Cliente, String> tableColumnDni;
    @FXML private TableColumn<Cliente, String> tableColumnApellNom;
    
    @FXML private TextField textFieldBuscarCliente1;
    @FXML private TableView<Recomendacion> tableViewClienteRecom;
    @FXML private TableColumn<Recomendacion, Integer> tableColumnIdRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnCodigoRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnDniRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnApellNomRecom;
    @FXML private TableColumn<Recomendacion, Integer> tableColumnIdBoleta;
    @FXML private TableColumn<Recomendacion, String> tableColumnBoletaRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnFechaRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnImporteRecom;
    @FXML private TableColumn<Recomendacion, String> tableColumnPagoRecom;
    @FXML private Label labelMontoTotal;
    @FXML private JFXButton jFXButtonPago;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    
    private MenuItem menuItemProximosPagos = new MenuItem("Proximos a Pagar");
    private MenuItem menuItemHistorialPagado = new MenuItem("Historial de Pagos");
    private MenuItem menuItemListaRecomendados = new MenuItem("Historial Completa");
    private MenuItem menuItemPagosFacturados = new MenuItem("Pagos Facturados");
        
    MCliente mCliente = new MCliente();
    MRecomendacion mRecomendacion = new MRecomendacion();
    
    private int id = -1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desabilitarBotonPago();
		
		inicializarTableViewCliente();
		cargarTablaCliente();
		
		jfxButtonConfiguracion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/recomendacion/ConfiguracionRecomendacion.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, 1, CPadre.UPDATE);
				
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
			}
		});
		    	
		textFieldBuscarCliente.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				buscarCliente();
			}
		});
		
		tableViewCliente.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>() { 
	         @Override 
	         public void handle(javafx.scene.input.MouseEvent e) { 
	            if (e.getClickCount()==1) {
	            	id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
	            	inicializarTableViewClienteRecomendado();
	            	cargarTablaRecomendado();
	            	desabilitarBotonPago();
	            	traerSumaTotalRecomendado();
				}             
	         } 
	    });
		
		jFXButtonPago.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				String url = "/view/recomendacion/SeeBoletaPago.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.INSERT);
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
			}
		});
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cargarTablaCliente();
			}
		});
		
		menuItemProximosPagos.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
            	
            	String url = "/view/recomendacion/ProximosPagos.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		menuItemHistorialPagado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
            	
            	String url = "/view/recomendacion/HistorialPagos.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		menuItemPagosFacturados.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
				
            	String url = "/view/recomendacion/PagosFacturados.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		menuItemListaRecomendados.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
            	
            	String url = "/view/recomendacion/ListaRecomendaciones.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, -1, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemProximosPagos, menuItemHistorialPagado, menuItemPagosFacturados, menuItemListaRecomendados);
	
		jfxButtonConfiguracion.setVisible(Permisos.CONFIG_POR_RECOMENDACION);
	}
	
	public void inicializarTableViewCliente(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("cli_id"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cli_codigo"));
		tableColumnDni.setCellValueFactory(new PropertyValueFactory<>("cli_dni"));
		tableColumnApellNom.setCellValueFactory(new PropertyValueFactory<>("cli_apellNom"));
	}
	
	public void cargarTablaCliente(){
		mCliente.iniciarConexionServidor();
		tableViewCliente.setItems(mCliente.seleccionarColeccionCliente());
		mCliente.cerrarConexionServidor();
		tableViewCliente.refresh();
	}
		
	public void buscarCliente(){
		mCliente.iniciarConexionServidor();
		tableViewCliente.setItems(mCliente.BuscarBDCliente(textFieldBuscarCliente.getText().trim()));
		mCliente.cerrarConexionServidor();
		tableViewCliente.refresh();
	}

	public void inicializarTableViewClienteRecomendado(){
		tableColumnIdRecom.setCellValueFactory(new PropertyValueFactory<>("recom_idCli"));
		tableColumnCodigoRecom.setCellValueFactory(new PropertyValueFactory<>("recom_codCli"));
		tableColumnDniRecom.setCellValueFactory(new PropertyValueFactory<>("recom_dniCli"));
		tableColumnApellNomRecom.setCellValueFactory(new PropertyValueFactory<>("recom_apellNomCli"));
		tableColumnIdBoleta.setCellValueFactory(new PropertyValueFactory<>("recom_bolId"));
		tableColumnBoletaRecom.setCellValueFactory(new PropertyValueFactory<>("recom_bolSerNro"));
		tableColumnFechaRecom.setCellValueFactory(new PropertyValueFactory<>("recom_bolFech"));
		tableColumnImporteRecom.setCellValueFactory(new PropertyValueFactory<>("recom_bolImporte"));
		tableColumnPagoRecom.setCellValueFactory(new PropertyValueFactory<>("recom_bolPago"));
	}
	
	public void cargarTablaRecomendado(){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.seleccionarColeccionRecomendacion(id, 0));
		mRecomendacion.cerrarConexionServidor();
	}
	
	
	public void traerSumaTotalRecomendado(){
		mRecomendacion.iniciarConexionServidor();
		labelMontoTotal.setText(mRecomendacion.montoPagarRecomendado(id, 0));		
		mRecomendacion.cerrarConexionServidor();
		
	}
		
	public void desabilitarBotonPago(){
		if(!traerDiaPago().equals("Saturday") || tableViewClienteRecom.getItems().isEmpty()){
			jFXButtonPago.setDisable(true);
		}else{
			jFXButtonPago.setDisable(false);
		}
	}
	
	public String traerDiaPago(){
		mRecomendacion.iniciarConexionServidor();
		String dia = mRecomendacion.seleccionarDiaPago();
		mRecomendacion.cerrarConexionServidor();
		return dia;
	}
		
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}

}
