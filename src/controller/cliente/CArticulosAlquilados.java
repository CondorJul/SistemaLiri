package controller.cliente;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ResourceBundle;

import controller.CPadre;
import dal.ArticuloAlquilado;
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

public class CArticulosAlquilados extends CPadre implements Initializable {
	
	@FXML private Button buttonCerrar;
    @FXML private TextField textFieldBuscarArticulosAlquilados;
    @FXML private TableView<ArticuloAlquilado> tableViewClienteArticulosAlquilados;
    @FXML private TableColumn<ArticuloAlquilado, Integer> tableColumnIdBoleta;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnSerieNroBoleta;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnFecha;
    @FXML private TableColumn<ArticuloAlquilado, Integer> tableColumnCantidad;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnCodigo;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnDescripcion;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnPrecioUnit;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnImporte;
    @FXML private TableColumn<ArticuloAlquilado, String> tableColumnImpDesct;
    @FXML private Label labelMontoTotalSinDesc;
    @FXML private Label labelMontoTotalConDesc;
    @FXML private Label labelMontoTotalDiferencia;
    
    public int id=-1;
    
    MCliente mCliente = new MCliente();
    ArticuloAlquilado aa = new ArticuloAlquilado();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewArticuloAlquilado();
		
		textFieldBuscarArticulosAlquilados.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarArticulosAlquilados();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});
	
	}
	
	public void inicializarTableViewArticuloAlquilado(){
		tableColumnIdBoleta.setCellValueFactory(new PropertyValueFactory<>("aaIdBoleta"));
		tableColumnSerieNroBoleta.setCellValueFactory(new PropertyValueFactory<>("aaSerNroBoleta"));
		tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("aaFecha"));
		tableColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("aaCant"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("aaCodigo"));
		tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("aaDescripcion"));
		tableColumnPrecioUnit.setCellValueFactory(new PropertyValueFactory<>("aaPrecioUnit"));
		tableColumnImporte.setCellValueFactory(new PropertyValueFactory<>("aaImporte"));
		tableColumnImpDesct.setCellValueFactory(new PropertyValueFactory<>("aaImpDesc"));
	}
	
	public void cargarTablaArticuloAlquilado(int idCliente){
		mCliente.iniciarConexionServidor();
		tableViewClienteArticulosAlquilados.setItems(mCliente.seleccionarColeccionArticulosAlquilados(idCliente));
		mCliente.cerrarConexionServidor();
	}
	
	public void buscarArticulosAlquilados(){
		mCliente.iniciarConexionServidor();
		tableViewClienteArticulosAlquilados.setItems(mCliente.BuscarArticulosAlquilados(textFieldBuscarArticulosAlquilados.getText().trim(), id));
		mCliente.cerrarConexionServidor();
					
	}
	
	public void traerSumaImportes(int idCli){
		mCliente.iniciarConexionServidor();
		aa =  mCliente.importesFacturados(idCli);		
		mCliente.cerrarConexionServidor();
		
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
		simbolos.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat("#0.00", simbolos);
		
		labelMontoTotalSinDesc.setText(String.valueOf(aa.getAaImpSinDesc()));
		labelMontoTotalConDesc.setText(String.valueOf(aa.getAaImpConDesc()));
		labelMontoTotalDiferencia.setText(String.valueOf(df.format(Double.parseDouble(aa.getAaImpSinDesc())-Double.parseDouble(aa.getAaImpConDesc()))));
	}
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		cargarTablaArticuloAlquilado((int)objeto);
		id=(int)objeto;
		traerSumaImportes((int)objeto);
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

}
