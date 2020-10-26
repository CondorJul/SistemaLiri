package controller.contabilidad;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Concepto;
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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.contabilidad.MConcepto;
import validacion.Validacion;

public class CAddConcepto  extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
    @FXML private TextField textFieldConcepto;
    @FXML private TextField textFieldMonto;
    @FXML private JFXButton jfxButtonAgregar;
    @FXML private JFXButton jfxButtonLimpiar;
    @FXML private TextField textFieldBuscarConcepto;
    @FXML private TableView<Concepto> tableViewConcepto;
    @FXML private TableColumn<Concepto, Integer> tableColumnId;
    @FXML private TableColumn<Concepto, String> tableColumnConcepto;
    @FXML private TableColumn<Concepto, Double> tableColumnMonto;
    @FXML private TableColumn<Concepto, String> tableColumnOpcion;
    @FXML private Label labelVerificacion;
    @FXML private ContextMenu contextMenuOpcionesTabla;
    @FXML private MenuItem menuItemRefrescar;
    
    private MenuItem menuItemEditarConcepto =new MenuItem("Editar Concepto");
    private MenuItem menuItemEliminarConcepto =new MenuItem("Eliminar Concepto");
    private MenuItem menuItemAgregarConcepto =new MenuItem("Agregar Concepto");
    
    MConcepto mConcepto= new MConcepto();
    Concepto concepto = new Concepto();
	private int id=-1;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewConcepto();
		recargarTabla();
		
		agregarRetricciones();
		
		jfxButtonAgregar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if (getAccion()==CPadre.INSERT) {
					if(validar()){
						agregar();
					}
				} else if (getAccion()==CPadre.UPDATE) {
					if(validar()){;
						actualizar();
					}
				}
			}
			
		});
		
		textFieldBuscarConcepto.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				buscarConcepto();
			}
		});
		
		tableViewConcepto.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {

			@Override
			public void handle(javafx.scene.input.MouseEvent e) {
				/*if (e.getClickCount()==1) {
					id = tableViewConcepto.getSelectionModel().getSelectedItem().getConId();	            
					seleccionarConcepto(id);
					setAccion(2);
				}*/
				if (e.getClickCount()==2) {
					concepto = tableViewConcepto.getSelectionModel().getSelectedItem();
					((Stage)((Node)e.getSource()).getScene().getWindow()).close();
				}
			}
		});
		
		jfxButtonLimpiar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				limpiar();
				labelVerificacion.setText("");
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				recargarTabla();
			}
		});
		
		menuItemEditarConcepto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				labelVerificacion.setText("");
				id = tableViewConcepto.getSelectionModel().getSelectedItem().getConId();	            
				seleccionarConcepto(id);
				setAccion(2);	
			}
		});
		
		menuItemEliminarConcepto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				labelVerificacion.setText("");
				id = tableViewConcepto.getSelectionModel().getSelectedItem().getConId();
				if (ventanaEliminar()) {
					eliminar(id);
					mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
				}
			}
		});
		
		menuItemAgregarConcepto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				concepto = tableViewConcepto.getSelectionModel().getSelectedItem();
				((Stage)tableViewConcepto.getScene().getWindow()).close();
			}
		});
		
		contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(), menuItemEditarConcepto, menuItemEliminarConcepto, menuItemAgregarConcepto);
	}
	
	public boolean agregar(){
		mConcepto.iniciarConexionServidor();
		mConcepto.insertarConcepto(this.concepto);
		mConcepto.cerrarConexionServidor();
		if(mConcepto.getNoError()==CPadre.CORRECTO){
			mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
			limpiar();
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
		}
		return false;
	}
	
	public boolean actualizar(){
		mConcepto.iniciarConexionServidor();
		mConcepto.actualizarConcepto(this.concepto);;
		mConcepto.cerrarConexionServidor();
		if(mConcepto.getNoError()==CPadre.CORRECTO){
			mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
			limpiar();
			return true;
		}else{
			mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
		}
		return false;
	}
	
	public boolean eliminar(int idEliminar){
		mConcepto.iniciarConexionServidor();
		mConcepto.eliminarConcepto(idEliminar);
		mConcepto.cerrarConexionServidor();
		return true;
	}
	
	public boolean limpiar(){
		textFieldConcepto.setText("");
		textFieldMonto.setText("");
		setAccion(1);
		return false;
	}
	
	public boolean validar(){
		if (textFieldConcepto.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Concepto esta Vacío Campo Obligatorio.", 0);
			textFieldConcepto.requestFocus();
			return false;
		}
		this.concepto.setConConcepto(textFieldConcepto.getText());
		if (textFieldMonto.getText().isEmpty()) {
			mostrarInformacion(labelVerificacion, "Monto esta Vacío Campo Obligatorio.", 0);
			textFieldMonto.requestFocus();
			return false;
		}
		this.concepto.setConMonto(Double.parseDouble(textFieldMonto.getText()));		
		return true;
	}
	
	public void seleccionarConcepto(int idConcepto){
		mConcepto.iniciarConexionServidor();
		concepto=mConcepto.seleccionarConcepto(idConcepto);
		mConcepto.cerrarConexionServidor();
		
		textFieldConcepto.setText(concepto.getConConcepto());
		textFieldMonto.setText(String.valueOf(concepto.getConMonto()));
		
	}
	
	public void inicializarTableViewConcepto(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("conId"));
		tableColumnConcepto.setCellValueFactory(new PropertyValueFactory<>("conConcepto"));
		tableColumnMonto.setCellValueFactory(new PropertyValueFactory<>("conMonto"));
		tableColumnOpcion.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	}
	
	public void recargarTabla(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					ObservableList<Concepto> arrayListConcepto = null;
					mConcepto.iniciarConexionServidor();
					arrayListConcepto = mConcepto.seleccionarColeccionConcepto();
					tableViewConcepto.setItems(arrayListConcepto);
					mConcepto.cerrarConexionServidor();
					
					for (Concepto concepto : arrayListConcepto) {
						
						concepto.getOpciones().getjFXButtonVer().setVisible(false);
						
						concepto.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								labelVerificacion.setText("");
								id = concepto.getConId();
								seleccionarConcepto(id);
								setAccion(2);
							}
						});
						
						concepto.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								labelVerificacion.setText("");
								id = concepto.getConId();
								if (ventanaEliminar()) {
									eliminar(id);
									mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
								}
							}
						});
						
						concepto.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewConcepto.refresh();
					Thread.sleep(299999);
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	
	public void buscarConcepto(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					ObservableList<Concepto> arrayListConcepto1 = null;
					mConcepto.iniciarConexionServidor();
					arrayListConcepto1 = mConcepto.BuscarBDConcepto(textFieldBuscarConcepto.getText().trim());
					tableViewConcepto.setItems(arrayListConcepto1);
					mConcepto.cerrarConexionServidor();
					
					for (Concepto concepto : arrayListConcepto1) {
						
						concepto.getOpciones().getjFXButtonVer().setVisible(false);
						
						concepto.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								labelVerificacion.setText("");
								id = concepto.getConId();
								seleccionarConcepto(id);
								setAccion(2);
							}
						});
						
						concepto.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								labelVerificacion.setText("");
								id = concepto.getConId();
								if (ventanaEliminar()) {
									eliminar(id);
									mostrarInformacion(labelVerificacion, mConcepto.getMensaje(), mConcepto.getNoError());
								}
							}
						});
						
						concepto.getOpciones().getjFXButtonAgregar().setVisible(false);
					}
					tableViewConcepto.refresh();
					Thread.sleep(61700);
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
	}
	
	private void agregarRetricciones(){
		textFieldMonto.addEventFilter(KeyEvent.KEY_TYPED, Validacion.validarPrecio(2));
	}
	
	@Override
	public Object getObjeto() {
		return concepto;
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
	}
	
}
