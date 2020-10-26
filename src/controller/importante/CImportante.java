package controller.importante;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Importante;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.importante.MImportante;
import sesion.Permisos;

public class CImportante extends CPadre implements Initializable{

	@FXML private JFXButton jfxButtonNuevoRecordar;
    @FXML private TextField textFieldBuscarImportante;
    @FXML private TableView<Importante> tableViewImportante;
    @FXML private TableColumn<Importante, Integer> tableColumnId;
    @FXML private TableColumn<Importante, String> tableColumnCodigo;
    @FXML private TableColumn<Importante, String> tableColumnDescripcion;
    @FXML private TableColumn<Importante, Date> tableColumnFechaInicio;
    @FXML private TableColumn<Importante, Date> tableColumnFechaFin;
    @FXML private TableColumn<Importante, Integer> tableColumnTalla;
    @FXML private TableColumn<Importante, Integer> tableColumnCantidad;
    @FXML private TableColumn<Importante, Integer> tableColumnDemanda;
    @FXML private TableColumn<Importante, String> tableColumnComentario;
    @FXML private TableColumn<Importante, String> tableColumnOpciones;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    @FXML private DatePicker datePickerFechaInicio;
    @FXML private DatePicker datePickerFechaFin;
    @FXML private Button buttonBuscar;
    
    private MenuItem menuItemEditarImportante =new MenuItem("Editar Importante");
    private MenuItem menuItemEliminarImportante =new MenuItem("Eliminar Importante");
    
    private int id = -1;
    MImportante mImportante = new MImportante();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePickerFechaFin.setValue(LocalDate.now());
		datePickerFechaInicio.setValue(LocalDate.of(2019, 01, 01));
		
		inicializarTableViewImportante();
		recargarTabla();

		jfxButtonNuevoRecordar.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent arg0) {
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/importante/AddImportante.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
								
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				buscarImportante();
			}
		});
		
		jfxButtonNuevoRecordar.setVisible(Permisos.ADD_IMPORTANTE);
		
		textFieldBuscarImportante.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarImportante();
			}
		});
		
		tableViewImportante.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

			@Override
			public void handle(javafx.scene.input.MouseEvent e) {
				if (e.getClickCount()==2) {
					id = tableViewImportante.getSelectionModel().getSelectedItem().getImpId();
	            	((StackPane)((Stage)((Node)e.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/importante/AddImportante.fxml";
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
		
		menuItemEditarImportante.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewImportante.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				id = tableViewImportante.getSelectionModel().getSelectedItem().getImpId();
            	
            	String url = "/view/importante/AddImportante.fxml";
				String css = "/estilocss/EstiloModal.css";
		
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
				sp.setVisible(false);
			}
		});
		
		menuItemEliminarImportante.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				id = tableViewImportante.getSelectionModel().getSelectedItem().getImpId();
				if (ventanaEliminar()) {
					eliminar(id);
					recargarTabla();
					mostrarNotificacion(mImportante.getMensaje(), mImportante.getNoError());
				}
			}
		});
		
		buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				LocalDate fi = java.sql.Date.valueOf(datePickerFechaInicio.getValue()).toLocalDate();
				LocalDate ff = java.sql.Date.valueOf(datePickerFechaFin.getValue()).toLocalDate();

				cargarTablaIntervalo(fi, ff);
				
			}
		});
		
		contextMenuOpcionesTabla.getItems().add(new SeparatorMenuItem());
		if(Permisos.UPD_IMPORTANTE){
			contextMenuOpcionesTabla.getItems().add(menuItemEditarImportante);
		}
		if(Permisos.DEL_IMPORTANTE){

			
			contextMenuOpcionesTabla.getItems().add(menuItemEliminarImportante);

		}
		//contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemEditarImportante, menuItemEliminarImportante);
		
	}
	
	public void inicializarTableViewImportante(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("impId"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("impCodigo"));
		tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("impDescripcion"));
		tableColumnFechaInicio.setCellValueFactory(new PropertyValueFactory<>("impFechaInicio"));
		tableColumnFechaFin.setCellValueFactory(new PropertyValueFactory<>("impFechaFin"));
		tableColumnTalla.setCellValueFactory(new PropertyValueFactory<>("impTalla"));
		tableColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("impCantidad"));
		tableColumnDemanda.setCellValueFactory(new PropertyValueFactory<>("impDemanda"));
		tableColumnComentario.setCellValueFactory(new PropertyValueFactory<>("impComentario"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
	
	public void recargarTabla(){
		ObservableList<Importante> observableListImportante = null;
		mImportante.iniciarConexionServidor();
		observableListImportante = mImportante.seleccionarColeccionImportante();
		tableViewImportante.setItems(observableListImportante);
		mImportante.cerrarConexionServidor();
		
		for (Importante importante : observableListImportante) {
			importante.getOpciones().getjFXButtonVer().setVisible(false);
			
			importante.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_IMPORTANTE);
			importante.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/importante/AddImportante.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
				}
			});
			
			importante.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_IMPORTANTE);
			importante.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					if (ventanaEliminar()) {
						eliminar(id);
						recargarTabla();
						mostrarNotificacion("Importante "+mImportante.getMensaje(), mImportante.getNoError());
					}
				}
			});
			
			importante.getOpciones().getjFXButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					contador(id);
					recargarTabla();
					
				}
			});
		}
		tableViewImportante.refresh();
	}
	
	public void buscarImportante(){
		ObservableList<Importante> observableListImportante1 = null;
		mImportante.iniciarConexionServidor();
		observableListImportante1 = mImportante.BuscarBDImportante(textFieldBuscarImportante.getText().trim());
		tableViewImportante.setItems(observableListImportante1);
		mImportante.cerrarConexionServidor();
		
		for (Importante importante : observableListImportante1) {
			importante.getOpciones().getjFXButtonVer().setVisible(false);
			
			importante.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_IMPORTANTE);
			importante.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/importante/AddImportante.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				}
			});
			importante.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_IMPORTANTE);
			importante.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					if (ventanaEliminar()) {
						eliminar(id);
						recargarTabla();
						mostrarNotificacion("Importante "+mImportante.getMensaje(), mImportante.getNoError());
					}
				}
			});
			
			importante.getOpciones().getjFXButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					contador(id);
					recargarTabla();
					
				}
			});
		}
		tableViewImportante.refresh();
	}
	
	public void cargarTablaIntervalo(LocalDate fi, LocalDate ff){
		ObservableList<Importante> observableListImportante = null;
		mImportante.iniciarConexionServidor();
		observableListImportante = mImportante.seleccionarIntervaloFechasImportante(fi, ff);
		tableViewImportante.setItems(observableListImportante);
		mImportante.cerrarConexionServidor();
		
		for (Importante importante : observableListImportante) {
			importante.getOpciones().getjFXButtonVer().setVisible(false);
			
			importante.getOpciones().getjFXButtonEditar().setVisible(Permisos.UPD_IMPORTANTE);
			importante.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
	            	
	            	String url = "/view/importante/AddImportante.fxml";
					String css = "/estilocss/EstiloModal.css";
			
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.UPDATE);
					
	            	((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
				}
			});
			
			importante.getOpciones().getjFXButtonEliminar().setVisible(Permisos.DEL_IMPORTANTE);
			importante.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					if (ventanaEliminar()) {
						eliminar(id);
						recargarTabla();
						mostrarNotificacion("Importante "+mImportante.getMensaje(), mImportante.getNoError());
					}
				}
			});
			
			importante.getOpciones().getjFXButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					id = importante.getImpId();
					contador(id);
					recargarTabla();
					
				}
			});
		}
		tableViewImportante.refresh();
	}
		
	public boolean eliminar(int IdEliminar){
		mImportante.iniciarConexionServidor();
		mImportante.eliminarImportante(IdEliminar);
		mImportante.cerrarConexionServidor();
		return true;
	}
	
	public void contador(int idContador){
		mImportante.iniciarConexionServidor();
		mImportante.contarVecesBusqueda(idContador);
		mImportante.cerrarConexionServidor();
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}

}
