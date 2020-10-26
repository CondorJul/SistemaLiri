package controller.proveedor;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Proveedor;
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
import model.proveedor.MProveedor;
import sesion.Permisos;

public class CProveedor extends CPadre implements Initializable {
	
	@FXML private JFXButton jfxButtonNuevoProveedor;
    @FXML private TextField textFieldBuscarProveedor;
    @FXML private TableView<Proveedor> tableViewProveedor;
    @FXML private TableColumn<Proveedor, Integer> tableColumnId;
    @FXML private TableColumn<Proveedor, String> tableColumnCodigo;
    @FXML private TableColumn<Proveedor, String> tableColumnNombre;
    @FXML private TableColumn<Proveedor, String> tableColumnArticulo;
    @FXML private TableColumn<Proveedor, String> tableColumnDireccion;
    @FXML private TableColumn<Proveedor, String> tableColumnTelefono;
    @FXML private TableColumn<Proveedor, String> tableColumnCorreo;
    @FXML private TableColumn<Proveedor, String> tableColumnOpciones;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    
    private MenuItem menuItemEditarProveedor =new MenuItem("Editar Proveedor");
    private MenuItem menuItemEliminarProveedor =new MenuItem("Eliminar Proveedor");
    
    private int id = -1;
    MProveedor mProveedor = new MProveedor();
	    	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewProveedor();
		recargarTabla();

		jfxButtonNuevoProveedor.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/proveedor/AddProveedor.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
								
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
			}
		});
		
		jfxButtonNuevoProveedor.setVisible(Permisos.ADD_PROVEEDOR);
		
		textFieldBuscarProveedor.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarProveedor();
			}
		});
		
		tableViewProveedor.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

			@Override
			public void handle(javafx.scene.input.MouseEvent e) {
				if (e.getClickCount()==2) {
					id = tableViewProveedor.getSelectionModel().getSelectedItem().getId();
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/proveedor/AddProveedor.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
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
		
		menuItemEditarProveedor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewProveedor.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewProveedor.getSelectionModel().getSelectedItem().getId();
            	
            	String url = "/view/proveedor/AddProveedor.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(true);
			}
		});
		
		menuItemEliminarProveedor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				id = tableViewProveedor.getSelectionModel().getSelectedItem().getId();
				if (ventanaEliminar()) {
					eliminar(id);
					mostrarNotificacion(mProveedor.getMensaje(), mProveedor.getNoError());
				}
			}
		});
		contextMenuOpcionesTabla.getItems().add(new SeparatorMenuItem());
		
		if(Permisos.UPD_PROVEEDOR){
			contextMenuOpcionesTabla.getItems().add(menuItemEditarProveedor);
		}
		if(Permisos.DEL_PROVEEDOR){
			contextMenuOpcionesTabla.getItems().add(menuItemEliminarProveedor);
		}
		//contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemEditarProveedor, menuItemEliminarProveedor);
		
	}
	
	public void inicializarTableViewProveedor(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tableColumnArticulo.setCellValueFactory(new PropertyValueFactory<>("articulo"));
		tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		tableColumnCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
	
	public void recargarTabla(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				
					ObservableList<Proveedor> observableListProveeedor = null;
					mProveedor.iniciarConexionServidor();
					observableListProveeedor = mProveedor.seleccionarColeccionProveedor();
					tableViewProveedor.setItems(observableListProveeedor);
					mProveedor.cerrarConexionServidor();
					
					for (Proveedor proveedor : observableListProveeedor) {
						proveedor.getOpciones().getjFXButtonVer().setVisible(false);
						
						proveedor.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_PROVEEDOR);
						proveedor.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = proveedor.getId();
				            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				            	
				            	String url = "/view/proveedor/AddProveedor.fxml";
								String css = "/estilocss/EstiloModal.css";
								
								CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
								
				            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
							}
						});
						proveedor.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_PROVEEDOR);
						proveedor.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = proveedor.getId();
								if (ventanaEliminar()) {
									eliminar(id);
									mostrarNotificacion("Proveedor "+mProveedor.getMensaje(), mProveedor.getNoError());
								}
							}
						});
						
						proveedor.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewProveedor.refresh();
					Thread.sleep(29999);
				return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public void buscarProveedor(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

			
					ObservableList<Proveedor> observableListProveedor1 = null;
					mProveedor.iniciarConexionServidor();
					observableListProveedor1 = mProveedor.BuscarBDProveedor(textFieldBuscarProveedor.getText().trim());
					tableViewProveedor.setItems(observableListProveedor1);
					mProveedor.cerrarConexionServidor();
					
					for (Proveedor proveedor : observableListProveedor1) {
						proveedor.getOpciones().getjFXButtonVer().setVisible(false);
						
						proveedor.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = proveedor.getId();
				            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				            	
				            	String url = "/view/proveedor/AddProveedor.fxml";
								String css = "/estilocss/EstiloModal.css";
								
								CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
								
				            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
							}
						});
						
						proveedor.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								id = proveedor.getId();
								if (ventanaEliminar()) {
									eliminar(id);
									recargarTabla();
									mostrarNotificacion("Proveedor "+mProveedor.getMensaje(), mProveedor.getNoError());
								}
							}
						});
						
						proveedor.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewProveedor.refresh();
					Thread.sleep(61700);
				return null;
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	public boolean eliminar(int idEliminar){
		mProveedor.iniciarConexionServidor();
		mProveedor.eliminarProveedor(idEliminar);
		mProveedor.cerrarConexionServidor();
		return true;
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
	
		
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
