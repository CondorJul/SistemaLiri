package controller.recomendacion;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CPadre;
import dal.Egreso;
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
import model.recomendacion.MRecomendacion;

public class CPagosFacturados extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
	@FXML private TextField textFieldBuscarPagosFacturados;
    @FXML private TableView<Egreso> tableViewPagosFacturados;
    @FXML private TableColumn<Egreso, Integer> tableColumnIdEgreso;
    @FXML private TableColumn<Egreso, String> tableColumnTipoDoc;
    @FXML private TableColumn<Egreso, String> tableColumnNombreRazon;
    @FXML private TableColumn<Egreso, String> tableColumnNroDoc;
    @FXML private TableColumn<Egreso, String> tableColumnFechaEmision;
    @FXML private TableColumn<Egreso, String> tableColumnConcepto;
    @FXML private TableColumn<Egreso, String> tableColumnPagoFac;
    @FXML private Label labelMontoTotal;

    
    public int id=-1;
    
    MRecomendacion mRecomendacion = new MRecomendacion();
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewClienteRecomendado();
		
		textFieldBuscarPagosFacturados.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				//buscarProximosPagos();
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
		tableColumnIdEgreso.setCellValueFactory(new PropertyValueFactory<>("egreId"));
		tableColumnTipoDoc.setCellValueFactory(new PropertyValueFactory<>("egreDocumento"));
		tableColumnNombreRazon.setCellValueFactory(new PropertyValueFactory<>("egreNombreRazon"));
		tableColumnNroDoc.setCellValueFactory(new PropertyValueFactory<>("egreNroDoc"));
		tableColumnFechaEmision.setCellValueFactory(new PropertyValueFactory<>("egreFechaEmision"));
		tableColumnConcepto.setCellValueFactory(new PropertyValueFactory<>("egreDescripcion"));
		tableColumnPagoFac.setCellValueFactory(new PropertyValueFactory<>("egreMonto"));
	}
	
	public void cargarTablaRecomendado(int IdCliente){
		mRecomendacion.iniciarConexionServidor();
		tableViewPagosFacturados.setItems(mRecomendacion.seleccionarColeccionPagosFacturados(IdCliente));
		mRecomendacion.cerrarConexionServidor();
	}
	
	/*public void buscarProximosPagos(){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.BuscarBDProximosPagos(textFieldBuscarHistorialPagos.getText().trim(), id, 1));
		mRecomendacion.cerrarConexionServidor();
					
	}*/
	
	public void traerSumaTotalPagosFacturados(int IdCliente){
		mRecomendacion.iniciarConexionServidor();
		labelMontoTotal.setText(mRecomendacion.montoPagarPagosFacturados(IdCliente));		
		mRecomendacion.cerrarConexionServidor();
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		cargarTablaRecomendado((int)objeto);
		id=(int)objeto;
		traerSumaTotalPagosFacturados((int)objeto);
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
