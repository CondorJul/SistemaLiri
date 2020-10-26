package controller.recomendacion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;

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

public class CListaRecomendados extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
	@FXML private TextField textFieldBuscar;
	@FXML private JFXCheckBox jfxCheckBoxPagados;
	@FXML private JFXCheckBox jfxCheckBoxPorPagar;
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
    @FXML private Label labelTotal;
    
    public int id=-1;
    
    MCliente mCliente = new MCliente();
    MRecomendacion mRecomendacion = new MRecomendacion();
        
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewClienteRecomendado();
		
		textFieldBuscar.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarProximosPagos();
			}
		});
		
		jfxCheckBoxPagados.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				jfxCheckBoxPorPagar.setSelected(false);
				cargarFiltroPagados(1);
				traerSumaTotalPagarYPagado(1);
				labelTotal.setText("");
				labelTotal.setText("Total Pagado  S/");
			}
		});
		
		jfxCheckBoxPorPagar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				jfxCheckBoxPagados.setSelected(false);
				cargarFiltroPagados(0);
				traerSumaTotalPagarYPagado(0);
				labelTotal.setText("");
				labelTotal.setText("Total Por Pagar S/");
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
		tableViewClienteRecom.setItems(mRecomendacion.seleccionarColeccionRecomendacion(IdRecom, 2));
		mRecomendacion.cerrarConexionServidor();
	}
	
	public void buscarProximosPagos(){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.BuscarBDProximosPagos(textFieldBuscar.getText().trim(), id, 2));
		mRecomendacion.cerrarConexionServidor();	
	}
	
	public void traerSumaTotalPagarYPagado(int estado){
		mRecomendacion.iniciarConexionServidor();
		labelMontoTotal.setText(mRecomendacion.montoTotalPagarPagado(estado));		
		mRecomendacion.cerrarConexionServidor();
	}
	
	public void cargarFiltroPagados(int estado){
		mRecomendacion.iniciarConexionServidor();
		tableViewClienteRecom.setItems(mRecomendacion.seleccionarFiltroPagos(estado));
		mRecomendacion.cerrarConexionServidor();
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		cargarTablaRecomendado((int)objeto);
		id=(int)objeto;
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
