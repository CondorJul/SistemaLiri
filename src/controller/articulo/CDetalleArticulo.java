package controller.articulo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Articulo;
import dal.DetalleArticulo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.articulo.MArticulo;
import model.transaccionesExternas.MTransaccionesExternas;

public class CDetalleArticulo extends CPadre implements Initializable {
	
	
	MArticulo mArticulo=new MArticulo();
	Articulo articulo= new Articulo();

    @FXML
    private Button buttonCerrar;

    @FXML
    private TextField textFieldArticuloBuscar;

    @FXML
    private Button buttonArticuloBuscar;

    @FXML
    private TableView<DetalleArticulo> tableViewDetalle;

    @FXML
    private TableColumn<DetalleArticulo, Integer> tableColumnDetalleNro;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleCodArtPieza;

    @FXML
    private TableColumn<DetalleArticulo, Integer> tableColumnDetalleCant;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleDescripcion;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetallePUnit;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleImporte;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleImporteDesc;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleCodCliente;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleCliente;

    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnDetalleBoleta;
    
    @FXML
    private TableColumn<DetalleArticulo, String> tableColumnFecha;

    @FXML
    private ContextMenu contextMenuDetalle;

    @FXML
    private Label labelCostoTotal;

    @FXML
    private Label labelCostoTotalDesc;

    @FXML
    private Label  labelCostoTotalDiferencia;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarTableView();
		
		buttonArticuloBuscar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				seleccionarArticuloDetalleVenta(articulo.getId(), textFieldArticuloBuscar.getText());
				
			}
		});
		textFieldArticuloBuscar.setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				
				seleccionarArticuloDetalleVenta(articulo.getId(), textFieldArticuloBuscar.getText());

				
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		if(objeto instanceof Integer){
			articulo.setId((int)objeto);

			seleccionarArticuloDetalleVenta((int)objeto, "");
		}
		
	}

	@Override
	public Object getObjeto() {
		
		
		
		return null;
	}
	
	
	public void inicializarTableView(){
		tableColumnDetalleNro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableColumnDetalleCodArtPieza.setCellValueFactory(new PropertyValueFactory<>("codArtiPieza"));
		tableColumnDetalleCant.setCellValueFactory(new PropertyValueFactory<>("cant"));
		tableColumnDetalleDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnDetallePUnit.setCellValueFactory(new PropertyValueFactory<>("precioUnit"));
		tableColumnDetalleImporte.setCellValueFactory(new PropertyValueFactory<>("importe"));
		tableColumnDetalleImporteDesc.setCellValueFactory(new PropertyValueFactory<>("importeDesc"));
		tableColumnDetalleCodCliente.setCellValueFactory(new PropertyValueFactory<>("codCliente"));
		tableColumnDetalleCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
		tableColumnDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("boleta"));
		tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

	}
	
	public void seleccionarArticuloDetalleVenta(int artId ,String buscar ){
		mArticulo.iniciarConexionServidor();

		ObservableList<DetalleArticulo> array=mArticulo.seleccionarArticuloDetalleVenta(artId, buscar);		
		Double importeTotal=0.0;
		Double importeTotalConDes=0.0;
		
		tableViewDetalle.setItems(array);
		mArticulo.cerrarConexionServidor();
		tableViewDetalle.refresh();
		
		for (DetalleArticulo detalleArticulo : array) {
			importeTotal=importeTotal+Double.parseDouble(detalleArticulo.getImporte());
			importeTotalConDes=importeTotalConDes+Double.parseDouble(detalleArticulo.getImporteDesc());
		}
		
		labelCostoTotal.setText(importeTotal+"");
		labelCostoTotalDesc.setText(importeTotalConDes+"");
		labelCostoTotalDiferencia.setText((importeTotal-importeTotalConDes)+"");;
	}
	
	
	
	
	

		

}
