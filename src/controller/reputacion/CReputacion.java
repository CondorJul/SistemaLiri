package controller.reputacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Cliente;
import dal.Reputacion;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.cliente.MCliente;
import model.reputacion.MReputacion;
import sesion.Permisos;

public class CReputacion extends CPadre implements Initializable {
	
	@FXML private JFXButton jfxButtonConfiguracion;
    @FXML private TextField textFieldBuscarCliente;
    @FXML private TableView<Cliente> tableViewReputacion;
    @FXML private TableColumn<Cliente, Integer> tableColumnId;
    @FXML private TableColumn<Cliente, String> tableColumnCodigo;
    @FXML private TableColumn<Cliente, String> tableColumnDni;
    @FXML private TableColumn<Cliente, String> tableColumnApellNom;
    @FXML private TableColumn<Cliente, Integer> tableColumnReputacion;
    @FXML private TableColumn<Cliente, String> tableColumnNivel;
    @FXML private TableColumn<Cliente, String> tableColumnOpciones;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    
    private MenuItem menuItemEditarReputacion =new MenuItem("Editar Reputación");
    private int id = -1;
    MCliente mCliente = new MCliente();
    MReputacion mReputacion = new MReputacion();
    Reputacion reputacion = null;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewReputacion();
		recargarTabla();
				
		jfxButtonConfiguracion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/reputacion/ConfiguracionReputacion.fxml";
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
		
		tableViewReputacion.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>() { 
	         @Override 
	         public void handle(javafx.scene.input.MouseEvent e) { 
	            if (e.getClickCount()==2) {
	            	
	            	id = tableViewReputacion.getSelectionModel().getSelectedItem().getCli_id();
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/reputacion/UdpReputacion.fxml";
					String css = "/estilocss/EstiloModal.css";
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
					((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
	
				}             
	         } 
	    });
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				recargarTabla();
			}
		});
		
		menuItemEditarReputacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewReputacion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewReputacion.getSelectionModel().getSelectedItem().getCli_id();
            	
            	String url = "/view/reputacion/UdpReputacion.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemEditarReputacion);
		
		jfxButtonConfiguracion.setVisible(Permisos.CONFIG_INTER_REPUTACION);
		menuItemEditarReputacion.setVisible(Permisos.UPD_REPUTACION);
	}

	public void inicializarTableViewReputacion(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("cli_id"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cli_codigo"));
		tableColumnDni.setCellValueFactory(new PropertyValueFactory<>("cli_dni"));
		tableColumnApellNom.setCellValueFactory(new PropertyValueFactory<>("cli_apellNom"));
		tableColumnReputacion.setCellValueFactory(new PropertyValueFactory<>("cli_reputacion"));
		tableColumnNivel.setCellValueFactory(new PropertyValueFactory<>("nivel"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
	
	public void recargarTabla(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					ObservableList<Cliente> observableListCliente = null;
					mReputacion.iniciarConexionServidor();
					reputacion=mReputacion.seleccionarReputacion(1);
					CProgress.VMROJO=reputacion.getRepVMRojo();
					CProgress.VMAMBAR=reputacion.getRepVMAmmbar();
					CProgress.VMVERDE=reputacion.getRepVMVerde();
					mCliente.setConexionServidor(mReputacion.getConexionServidor());
					
					observableListCliente = mCliente.seleccionarColeccionCliente();
					tableViewReputacion.setItems(observableListCliente);
					mCliente.cerrarConexionServidor();
					
					for (Cliente cliente : observableListCliente) {
						cliente.getOpciones().getjFXButtonVer().setVisible(false);
						
						cliente.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_REPUTACION);
						cliente.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = cliente.getCli_id();
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				            	
				            	String url = "/view/reputacion/UdpReputacion.fxml";
								String css = "/estilocss/EstiloModal.css";
								CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
								
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
							}
						});
						
						cliente.getOpciones().getjFXButtonEliminar().setVisible(false);
						
						cliente.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewReputacion.refresh();
					Thread.sleep(29999);
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void buscarCliente(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					ObservableList<Cliente> observableListCliente1 = null;
					mCliente.iniciarConexionServidor();
					observableListCliente1 = mCliente.BuscarBDCliente(textFieldBuscarCliente.getText().trim());
					tableViewReputacion.setItems(observableListCliente1);
					mCliente.cerrarConexionServidor();
					
					for (Cliente cliente : observableListCliente1) {
						cliente.getOpciones().getjFXButtonVer().setVisible(false);
						
						cliente.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = cliente.getCli_id();
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				            	
				            	String url = "/view/reputacion/UdpReputacion.fxml";
								String css = "/estilocss/EstiloModal.css";
								CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
								
								((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
							}
						});
						
						cliente.getOpciones().getjFXButtonEliminar().setVisible(false);
						
						cliente.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewReputacion.refresh();
					Thread.sleep(61700);
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
}
