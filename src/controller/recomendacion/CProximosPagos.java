package controller.recomendacion;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CPadre;
import dal.Recomendacion;
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
import javafx.stage.Stage;
import model.cliente.MCliente;
import model.recomendacion.MRecomendacion;

public class CProximosPagos extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private TextField textFieldBuscarProximosPagos;
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
    
    public int id=-1;
    
    MCliente mCliente = new MCliente();
    MRecomendacion mRecomendacion = new MRecomendacion();
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewClienteRecomendado();
		
		textFieldBuscarProximosPagos.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarProximosPagos();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});
	
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
	
	public void cargarTablaRecomendado(int IdRecom){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.seleccionarColeccionRecomendacion(IdRecom, 0));
		mRecomendacion.cerrarConexionServidor();
	}
	
	public void buscarProximosPagos(){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.BuscarBDProximosPagos(textFieldBuscarProximosPagos.getText().trim(), id, 0));
		mRecomendacion.cerrarConexionServidor();
					
	}
	
	public void traerSumaTotalRecomendado(){
		mRecomendacion.iniciarConexionServidor();
		labelMontoTotal.setText(mRecomendacion.montoPagarRecomendado(id, 0));		
		mRecomendacion.cerrarConexionServidor();
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		cargarTablaRecomendado((int)objeto);
		id=(int)objeto;
		traerSumaTotalRecomendado();
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
