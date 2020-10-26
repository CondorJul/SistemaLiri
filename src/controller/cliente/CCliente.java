package controller.cliente;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Cliente;
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
import sesion.Permisos;

public class CCliente extends CPadre implements Initializable {
	
	@FXML private JFXButton jfxButtonNuevoCliente;
    @FXML private TextField textFieldBuscarCliente;
    @FXML private TableView<Cliente> tableViewCliente;
    @FXML private TableColumn<Cliente, Integer> tableColumnId;
    @FXML private TableColumn<Cliente, String> tableColumnCodigo;
    @FXML private TableColumn<Cliente, String> tableColumnTipoCliente;
    @FXML private TableColumn<Cliente, String> tableColumnDni;
    @FXML private TableColumn<Cliente, String> tableColumnApellNom;
    @FXML private TableColumn<Cliente, Date> tableColumnFechaNacimiento;
    @FXML private TableColumn<Cliente, String> tableColumnDireccion;
    @FXML private TableColumn<Cliente, String> tableColumnReferencia;
    @FXML private TableColumn<Cliente, String> tableColumnCelular;
    @FXML private TableColumn<Cliente, String> tableColumnOpciones;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescarTabla;
    
    private MenuItem menuItemEditarCliente =new MenuItem("Editar Cliente");
    private MenuItem menuItemEliminarCliente =new MenuItem("Eliminar Cliente");
    private MenuItem menuItemArticulosAlquilados =new MenuItem("Arículos Alquilados");
	
    private int id = -1;
    MCliente mCliente = new MCliente();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewCliente();
		recargarTabla();
				
		jfxButtonNuevoCliente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/cliente/AddCliente.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				buscarCliente();
			}
		});
		
		jfxButtonNuevoCliente.setVisible(Permisos.ADD_CLIENTES);
		
		textFieldBuscarCliente.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				buscarCliente();
			}
		});
		
		tableViewCliente.setOnMouseClicked (new EventHandler<javafx.scene.input.MouseEvent>() { 
	         @Override 
	         public void handle(javafx.scene.input.MouseEvent e) { 
	            if (e.getClickCount()==2) {
	            	
	            	id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/cliente/AddCliente.fxml";
					String css = "/estilocss/EstiloModal.css";
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
					((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
	
				}             
	         } 
	      }); 
		
		menuItemRefrescarTabla.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				recargarTabla();
			}
		});
		
		menuItemEditarCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
            	
            	String url = "/view/cliente/AddCliente.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
				buscarCliente();
				
			}
		});
		
		menuItemEliminarCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
				if (ventanaEliminar()) {
					eliminar(id);
					mostrarNotificacion("Cliente "+mCliente.getMensaje(), mCliente.getNoError());
				}
			}
		});
		
		menuItemArticulosAlquilados.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)tableViewCliente.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewCliente.getSelectionModel().getSelectedItem().getCli_id();
            	
            	String url = "/view/cliente/ArticulosAlquilados.fxml";
				String css = "/estilocss/EstiloModal.css";
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		
		/*privilegios*/
		contextMenuOpcionesTabla.getItems().add(new SeparatorMenuItem());
		
		if(Permisos.UPD_CLIENTES){
			contextMenuOpcionesTabla.getItems().add(menuItemEditarCliente);
		}
		
		if(Permisos.DEL_CLIENTES){
			contextMenuOpcionesTabla.getItems().add(menuItemEliminarCliente);
		}
		contextMenuOpcionesTabla.getItems().add(menuItemArticulosAlquilados);
		//contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), , menuItemEliminarCliente);
		
	}

	public void inicializarTableViewCliente(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("cli_id"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("cli_codigo"));
		tableColumnTipoCliente.setCellValueFactory(new PropertyValueFactory<>("cli_tipoCliente"));
		tableColumnDni.setCellValueFactory(new PropertyValueFactory<>("cli_dni"));
		tableColumnApellNom.setCellValueFactory(new PropertyValueFactory<>("cli_apellNom"));
		tableColumnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("cli_fechNac"));
		tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("cli_direccion"));
		tableColumnReferencia.setCellValueFactory(new PropertyValueFactory<>("cli_referencia"));
		tableColumnCelular.setCellValueFactory(new PropertyValueFactory<>("cli_telefono"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
		
	public void recargarTabla(){
		ObservableList<Cliente> arrayListCliente=null;
		mCliente.iniciarConexionServidor();
		arrayListCliente = mCliente.seleccionarColeccionCliente();
		tableViewCliente.setItems(arrayListCliente);
		mCliente.cerrarConexionServidor();
		
		for (Cliente cliente : arrayListCliente) {
			
			cliente.getOpciones().getjFXButtonVer().setVisible(false);
			
			
			cliente.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = cliente.getCli_id();
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/cliente/AddCliente.fxml";
					String css = "/estilocss/EstiloModal.css";
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
	
				}
			});
			
			cliente.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					id = cliente.getCli_id();
					if (ventanaEliminar()) {
						eliminar(id);
						recargarTabla();
						mostrarNotificacion("Cliente "+mCliente.getMensaje(), mCliente.getNoError());
					}
				}
			});
			
			cliente.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_CLIENTES);			

			cliente.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_CLIENTES);

			cliente.getOpciones().getjFXButtonAgregar().setVisible(false);
		}
		tableViewCliente.refresh();
	}
	
	public void buscarCliente(){
		ObservableList<Cliente> arrayListCliente1 = null;
		mCliente.iniciarConexionServidor();
		arrayListCliente1 = mCliente.BuscarBDCliente(textFieldBuscarCliente.getText().trim());
		tableViewCliente.setItems(arrayListCliente1);
		mCliente.cerrarConexionServidor();
		
		for (Cliente cliente : arrayListCliente1) {
			
			cliente.getOpciones().getjFXButtonVer().setVisible(false);
			
			cliente.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_CLIENTES);			
			
			cliente.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = cliente.getCli_id();
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/cliente/AddCliente.fxml";
					String css = "/estilocss/EstiloModal.css";
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
	
				}
			});
			
			cliente.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_CLIENTES);
			
			
			cliente.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					id = cliente.getCli_id();
					if (ventanaEliminar()) {
						eliminar(id);
						recargarTabla();
						mostrarNotificacion("Cliente "+mCliente.getMensaje(), mCliente.getNoError());
					}
				}
			});
			
			cliente.getOpciones().getjFXButtonAgregar().setVisible(false);
			
		}
		tableViewCliente.refresh();
	}
	
	public boolean eliminar(int idEliminar){
		mCliente.iniciarConexionServidor();
		mCliente.eliminarCliente(idEliminar);
		mCliente.cerrarConexionServidor();
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
