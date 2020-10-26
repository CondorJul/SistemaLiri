package controller.busqueda;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Busqueda;
import dal.Proveedor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.busqueda.MBusqueda;
import model.proveedor.MProveedor;

public class CBusqueda extends CPadre implements Initializable{
		
	
		MBusqueda mBusqueda=new MBusqueda();
		Busqueda busqueda=new Busqueda();
	
    	@FXML
    	private Label labelVerificacion;
		
    	@FXML
	    private JFXButton jfxButtonCerrar;

	    @FXML
	    private JFXButton jfxButtonValidar;

	    @FXML
	    private TextField textFieldBusqueda;

	    @FXML
	    private TableView<Busqueda> tableViewBusqueda;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo1;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo2;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo3;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo4;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo5;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo6;

	    @FXML
	    private TableColumn<Busqueda,String> tableColumnCampo7;
	    
	    @FXML 
	    private Button buttonCerrar;


	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		int entidad=(int)objeto;
		mBusqueda.iniciarConexionServidor();
		mBusqueda.setEntidad(entidad);
		inicializarTableViewBusqueda();

	}

	@Override
	public Object getObjeto() {
		return busqueda;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		textFieldBusqueda.setOnKeyReleased(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				tableViewBusqueda.getItems().clear();
				tableViewBusqueda.setItems(mBusqueda.seleccionarBusqueda(textFieldBusqueda.getText()));
				tableViewBusqueda.refresh();
			}
		});
		
		jfxButtonValidar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				 cargarDatoADevolver(event);                 

				
			}
		});
		
		tableViewBusqueda.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				 if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
					 	cargarDatoADevolver(event);                 
			        }
			}

		
		});
		jfxButtonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				busqueda=null;
				mBusqueda.cerrarConexionServidor();
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				busqueda=null;
				mBusqueda.cerrarConexionServidor();
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		

	}

	private void cargarDatoADevolver(Event event){
		mBusqueda.cerrarConexionServidor();
		busqueda=tableViewBusqueda.getSelectionModel().getSelectedItem();				
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	}
	
	public void inicializarTableViewBusqueda(){
		
		tableColumnCampo1.setCellValueFactory(new PropertyValueFactory<>("campo1"));
		tableColumnCampo2.setCellValueFactory(new PropertyValueFactory<>("campo2"));
		tableColumnCampo3.setCellValueFactory(new PropertyValueFactory<>("campo3"));
		tableColumnCampo4.setCellValueFactory(new PropertyValueFactory<>("campo4"));
		tableColumnCampo5.setCellValueFactory(new PropertyValueFactory<>("campo5"));
		tableColumnCampo6.setCellValueFactory(new PropertyValueFactory<>("campo6"));
		
		tableColumnCampo1.setText(mBusqueda.getNombresCabecera().get(0));
		tableColumnCampo2.setText(mBusqueda.getNombresCabecera().get(1));
		tableColumnCampo3.setText(mBusqueda.getNombresCabecera().get(2));
		tableColumnCampo4.setText(mBusqueda.getNombresCabecera().get(3));
		tableColumnCampo5.setText(mBusqueda.getNombresCabecera().get(4));
		tableColumnCampo6.setText(mBusqueda.getNombresCabecera().get(5));
		tableColumnCampo7.setVisible(false);
		tableColumnCampo7.setPrefWidth(1);
		tableViewBusqueda.setItems(mBusqueda.seleccionarBusqueda(textFieldBusqueda.getText()));

	}
}
