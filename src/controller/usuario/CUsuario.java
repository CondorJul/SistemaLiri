package controller.usuario;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Usuario;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.usuario.MUsuario;
import sesion.Permisos;

public class CUsuario extends CPadre implements Initializable{
		MUsuario mUsuario=new MUsuario();
	
		@FXML private JFXButton jfxButtonNuevoUsuario;
	    @FXML private TextField textFieldBuscarUsuario;
	    @FXML private TableView<Usuario> tableViewUsuario;
	    @FXML private TableColumn<Usuario, String> tableColumnDni;
	    @FXML private TableColumn<Usuario, String> tableColumnApeNom;
	    @FXML private TableColumn<Usuario, String> tableColumnDireccion;
	    @FXML private TableColumn<Usuario, String> tableColumnCorreoElectronico;
	    @FXML private TableColumn<Usuario, String> tableColumnTelefono;
	    @FXML private TableColumn<Usuario, String> tableColumnCargo;
	    @FXML private TableColumn<Usuario, Double> tableColumnSalario;
	    @FXML private TableColumn<Usuario, String> tableColumnComentario;
	    @FXML private TableColumn<Usuario, String> tableColumnEstado;
	    @FXML private TableColumn<Usuario, String> tableColumnOpciones;    
	    @FXML private ContextMenu contextMenu;
    
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewUsuarios();
		recargarTabla();
		
		jfxButtonNuevoUsuario.setVisible(Permisos.ADD_USUARIOS);
		jfxButtonNuevoUsuario.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				String urlFxml="/view/usuario/AddUsuario.fxml";
				String css="/estilocss/EstiloModal.css";
				CPadre cpadreAddUsuario=getControlerMostrarInterfazModalShowAndWait(urlFxml, css,null,CPadre.INSERT);
				
				String urlFxmlViewPermisos="/view/usuario/ViewPermisos.fxml";
				if(cpadreAddUsuario.getObjeto()!=null){
					getControlerMostrarInterfazModalShowAndWait(urlFxmlViewPermisos, css, cpadreAddUsuario.getObjeto(),CPadre.INSERT);
				}
				
				((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
				
			}
		});
		
		textFieldBuscarUsuario.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				buscarUsuario();
			}
		});
		
		
		MenuItem menuItemRefrescar=new MenuItem("Refrescar");
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				recargarTabla();
			}
		});
		contextMenu.getItems().add(menuItemRefrescar);
		
		if(Permisos.UPD_USUARIOS){
			
			MenuItem menuItemActualizar=new MenuItem("Editar");
			menuItemActualizar.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					StackPane sp=((StackPane)((Stage)tableViewUsuario.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					Usuario usuario=tableViewUsuario.getSelectionModel().getSelectedItem();
					if(usuario!=null){
						String dni=usuario.getDni();

						String urlFxml="/view/usuario/AddUsuario.fxml";
						String css="/estilocss/EstiloModal.css";
						CPadre cpadreAddUsuario=getControlerMostrarInterfazModalShowAndWait(urlFxml, css,dni,CPadre.UPDATE);
					}
					sp.setVisible(false);
										
				}
			});
		
			contextMenu.getItems().add(menuItemActualizar);

		}
		
		
		if(Permisos.STATE_PASS_USUARIOS){
			MenuItem menuItemPermisos=new MenuItem("Permisos");
			menuItemPermisos.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					StackPane sp=((StackPane)((Stage)jfxButtonNuevoUsuario.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					String urlFxmlViewPermisos="/view/usuario/ViewPermisos.fxml";
					String css="/estilocss/EstiloModal.css";

					String dniUsuario=tableViewUsuario.getSelectionModel().getSelectedItem().getDni();
					getControlerMostrarInterfazModalShowAndWait(urlFxmlViewPermisos, css, dniUsuario,CPadre.UPDATE);
					sp.setVisible(false);

				}
			});
			contextMenu.getItems().add(menuItemPermisos);
		}
		
		if(Permisos.STATE_PASS_USUARIOS){
			MenuItem menuItemRestClave=new MenuItem("Restablecer contraseña");
			menuItemRestClave.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					StackPane sp=((StackPane)((Stage)jfxButtonNuevoUsuario.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					
					/*restablecer contraseña*/
					Usuario usuario=tableViewUsuario.getSelectionModel().getSelectedItem();
					if(usuario!=null){
						TextInputDialog texInputDialogIp=new TextInputDialog("");
						texInputDialogIp.setTitle("Restablecer contraseña-"+usuario.getNomApell());
						texInputDialogIp.setHeaderText("");
						texInputDialogIp.setContentText("Nueva contraseña: ");

						Optional<String> result = texInputDialogIp.showAndWait();
						
						if (result.isPresent()){
							
							try {
								if(result.get().toString().trim().equals("")){
									mostrarAlerta("Restablecer contraseña-"+usuario.getNomApell(), "", "Nueva contraseña vacío.", AlertType.WARNING);
								}else {
									MUsuario mUsuario=new MUsuario();
									mUsuario.iniciarConexionServidor();
									mUsuario.actualizarDatosContrasena(encriptar(result.get().toString()),usuario.getDni());
									mUsuario.cerrarConexionServidor();
									mostrarNotificacion(mUsuario.getMensaje(),mUsuario.getNoError() );
								}	
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					
					sp.setVisible(false);
				}
			});
			contextMenu.getItems().add(menuItemRestClave);
		}
		
		if(Permisos.STATE_PASS_USUARIOS){
			MenuItem menuItemCambiarEstado=new MenuItem("Permisos");
			menuItemCambiarEstado.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					Usuario usuario=tableViewUsuario.getSelectionModel().getSelectedItem();
					usuario.setEstado((usuario.getEstado()==1)?0:1);
					mUsuario.iniciarConexionServidor();
					mUsuario.actualizarEstadoUsuario(usuario);
					mUsuario.cerrarConexionServidor();
					tableViewUsuario.refresh();
					
				}
			});
			contextMenu.getItems().add(menuItemCambiarEstado);
		
			tableViewUsuario.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event arg0) {
					int estado=tableViewUsuario.getSelectionModel().getSelectedItem().getEstado();
					menuItemCambiarEstado.setText((estado==1)?"Bloquear Usuario":"Activar Usuario");
					
				}
			});
		}
		
		
		
		
		
		
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inicializarTableViewUsuarios(){
		tableColumnDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		tableColumnApeNom.setCellValueFactory(new PropertyValueFactory<>("nomApell"));
		tableColumnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		tableColumnCorreoElectronico.setCellValueFactory(new PropertyValueFactory<>("email"));
		tableColumnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		tableColumnCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
		tableColumnComentario.setCellValueFactory(new PropertyValueFactory<>("comentarios"));
		tableColumnSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
		tableColumnEstado.setCellValueFactory(new PropertyValueFactory<>("estado_s"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
		
	}
	public void recargarTabla(){
		mUsuario.iniciarConexionServidor();
		tableViewUsuario.setItems(mUsuario.seleccionarColeccionUsuario());
		mUsuario.cerrarConexionServidor();
		tableViewUsuario.refresh();
	}
	
	public void buscarUsuario(){
		mUsuario.iniciarConexionServidor();
		tableViewUsuario.setItems(mUsuario.BuscarBDUsuario(textFieldBuscarUsuario.getText().trim()));
		mUsuario.cerrarConexionServidor();
		tableViewUsuario.refresh();
	}
}
