package controller.recibo;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import controller.reputacion.CProgress;
import dal.Boleta;
import dal.Cliente;
import dal.TDetalleBoleta;
import dal.TDetallePorPiezasDevolucion;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.alquiler.MBoleta;
import model.alquiler.MTDetalleBoleta;
import model.cliente.MCliente;
import model.devolucion.MDevolucion;
import model.devolucion.MTDetalleBoletaDevolucion;
import model.devolucion.MTDetallePorPiezasDevolucion;
import validacion.Validacion;

public class CSeeBoleta extends CPadre implements Initializable{

	@FXML
    private Button buttonCerrar;

    @FXML
    private JFXButton jfxButtonCerrar;

    @FXML
    private JFXButton jfxButtonValidar;

    @FXML
    private Label labelVerificacion;

    @FXML
    private Label labelCodigoCliente;

    @FXML
    private Label lableDNICliente;

    @FXML
    private Label labelApellNombresCliente;
    @FXML 
    private Label labelEstadoDevolucion;



    @FXML
    private Label labelDireccionCliente;

    @FXML
    private Label labelFechaEmision;

    @FXML
    private Label labelSerie;

    @FXML
    private Label labelNumero;

    @FXML
    private Label labelSubTotal;

    @FXML
    private Label labelDesCupones;

    @FXML
    private Label labelDesAdicional;

    @FXML
    private Label labelDesImporteTotal;

    @FXML
    private Label labelTipoBoleta;

    @FXML
    private TableView<TDetalleBoleta> tableViewDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoleta,String> tableColumnCantDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoleta,String> tableColumnCodigoDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoleta,String> tableColumnDescripcionDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoleta,String> tableColumnPUnitDetalleBoleta;

    @FXML
    private TableColumn<TDetalleBoleta,String> tableColumnImporteBoleta1;

    @FXML
    private BorderPane boderPaneBodyGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxLicenciaGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDineroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniMenorGarantia;

    @FXML
    private TextField textFieldNroDniGarantia;

    @FXML
    private TextField textFieldNroMenorGarantia;

    @FXML
    private TextField textFieldNroLicenciaGarantia;

    @FXML
    private TextField textFieldDineroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxOtroGarantia;

    @FXML
    private TextArea textAreaOtroGarantia;

    @FXML
    private TextField textFieldNroBoletaGarantia;

    @FXML
    private JFXCheckBox jFXTCheckBoxBoletaAlqGarantia;

    @FXML
    private TextField textFieldSerieBoletaGarantia;

    @FXML
    private Label labelApellNomBoletaGarantia;

    @FXML
    private Label labelCodigoRecom;

    @FXML
    private Label lableDNIRecom;

    @FXML
    private Label labelApellNombresRecom;

    @FXML
    private Label labelPorDelTotalRecom;

    @FXML
    private Label labelMontoTotalRecom;

    @FXML
    private Label labelEstadoPagoRecom;

    @FXML
    private Label labelBoletaEgresoRecom;

    @FXML
    private Label labelACuenta;

    @FXML
    private Label labelSaldo;

    @FXML
    private Label labelFRecojo;

    @FXML
    private Label labelFechaEntrega;

    @FXML
    private Label labelFechaDevolucion;

    @FXML
    private BorderPane labelEstdoDevolucion;

    @FXML
    private Label labelTiempoDevolucion;

    @FXML
    private Label labelEstado;

    @FXML
    private Label labelAnulado;

    @FXML
    private Label labelCreadoPor;

    @FXML
    private Label labelFcreacion;

    @FXML
    private Label labelModificadoPor;

    @FXML
    private Label labelFModificacion;
    
    @FXML
    private BorderPane borderPaneReputacionCliente;
    
    private CProgress progressBarReputacionCliente=new CProgress();
    
    @FXML 
    private GridPane gridPaneReserva;
    
    @FXML 
    private GridPane gridPaneDevolucion;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarCamposTDetalleBoleta();
		jfxButtonCerrar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
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
		if(objeto instanceof Boleta){
			//casteamos el objetos que obtenemos 
			Boleta boleta=(Boleta)objeto;
			boleta=seleccionarBoleta(boleta.getId());
			//mostramos datos de cliente
			Cliente cliente=seleccionarCliente(boleta.getIdCliente());
			labelCodigoCliente.setText(cliente.getCli_codigo());
			lableDNICliente.setText(cliente.getCli_dni());
			labelApellNombresCliente.setText(cliente.getCli_apellNom());
			labelDireccionCliente.setText(cliente.getCli_dni());
			borderPaneReputacionCliente.setCenter(progressBarReputacionCliente);
			progressBarReputacionCliente.modelarProgress(cliente.getCli_reputacion());
			
			
			//mostramos detalle de la boleta
			labelTipoBoleta.setText((boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER)?"Alquiler":(boleta.getTipo()==Boleta.TIPO_VENTA_VENTA)?"Venta":"Duplicado");
			labelSerie.setText(boleta.getSerie());
			labelNumero.setText(String.format("%08d", boleta.getNumero()));
			labelFechaEmision.setText((boleta.getFecha()!=null)?boleta.getFecha().toString():"");
			labelSubTotal.setText(boleta.getSubTotal());
			labelDesCupones.setText(boleta.getDesCupones());
			labelDesAdicional.setText(boleta.getDesAdicional());
			labelDesImporteTotal.setText(boleta.getTotalPagar());
			
			if(boleta.getSepFechaRecojo()!=null){
				labelFRecojo.setText(boleta.getSepFechaRecojo().toString());
				labelACuenta.setText(Validacion.doubleAStringMoneda(boleta.getSepACuenta()));
				labelSaldo.setText(Validacion.doubleAStringMoneda(boleta.getSepSaldo()));
			}else{
				gridPaneReserva.setDisable(true);
			}
			
			if(boleta.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
				labelFechaEntrega.setText((boleta.getDevFechaEntrega()!=null)?boleta.getDevFechaEntrega().toString():"");
				labelFechaDevolucion.setText((boleta.getDevFechaDevolucion()!=null)?boleta.getDevFechaDevolucion().toString():"");
				
				
				//falta una fecha en la base de datos que diga que devolvio xD
			}else{
				gridPaneDevolucion.setDisable(true);
			}
			
			jFXCheckBoxDniGarantia.setSelected(boleta.isGarDni());
			textFieldNroDniGarantia.setDisable(!boleta.isGarDni());
			textFieldNroDniGarantia.setText(boleta.getGarNroDni());
			
			jFXCheckBoxDniMenorGarantia.setSelected(boleta.isGarDniMenor());
			textFieldNroMenorGarantia.setDisable(!boleta.isGarDniMenor());
			textFieldNroMenorGarantia.setText(boleta.getGarNroDniMenor());
			
			jFXCheckBoxLicenciaGarantia.setSelected(boleta.isGarLicencia());
			textFieldNroLicenciaGarantia.setDisable(!boleta.isGarLicencia());
			textFieldNroLicenciaGarantia.setText(boleta.getGarNroLicencia());
			
			jFXCheckBoxDineroGarantia.setSelected(boleta.isGarDinero());
			textFieldDineroGarantia.setDisable(!boleta.isGarDinero());
			textFieldDineroGarantia.setText((boleta.isGarDinero())?validacion.Validacion.doubleAStringMoneda(boleta.getGarMonto()):"");
			
			jFXCheckBoxOtroGarantia.setSelected(boleta.isGarOtro());
			textAreaOtroGarantia.setDisable(!boleta.isGarOtro());
			textAreaOtroGarantia.setText(boleta.getGarOtroEspecifique());
			
			
			jFXTCheckBoxBoletaAlqGarantia.setSelected(boleta.isGarEnlazarBoleta());
			textFieldSerieBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
			textFieldNroBoletaGarantia.setDisable(!boleta.isGarEnlazarBoleta());
			textFieldSerieBoletaGarantia.setText(boleta.getGarSerieBoleta());
			textFieldNroBoletaGarantia.setText(String.format("%08d", boleta.getGarNumeroBoleta()));
			
			labelEstado.setText((boleta.getEstadoAccion()==Boleta.EST_PENDIENTE)?"Pendiente":(boleta.getEstadoAccion()==Boleta.EST_RESERVADO)?"Reservado":(boleta.getEstadoAccion()==Boleta.EST_FACTURADO)?"Facturado":"");
			labelAnulado.setText((boleta.getEstadoEliminado()==Boleta.ESTADO_ELIMINADO_TRUE)?"Si":"No");
			labelCreadoPor.setText(boleta.getCreadoPor());
			labelFcreacion.setText(boleta.getfCreacion().toString());
			labelModificadoPor.setText(boleta.getModificadoPor());
			labelFModificacion.setText(boleta.getfModificacion().toString());
			
			
			labelEstadoDevolucion.setText((boleta.getDevCompletada()==1)?"Articulos Devueltos":"Devolución Pendiente.");
			//labelTiempoDevolucion.setText();
			labelPorDelTotalRecom.setText(Validacion.doubleAStringMoneda(boleta.getPorcentajePorPagoRec()));
			labelMontoTotalRecom.setText(Validacion.doubleAStringMoneda(boleta.getPorcentajePago()));
			labelEstadoPagoRecom.setText((boleta.getPagoRecom()==1)?"Pagado":"No pagado");
			labelBoletaEgresoRecom.setText((boleta.getIdEgreso()!=-1)?String.valueOf(boleta.getIdEgreso()):"");
			
			//mostramos el detalle de la boleta 
			tableViewDetalleBoleta.setItems(seleccionarDetalleBoleta(boleta.getId()));
			
			//mostramos datos de recomendacion
			Cliente clienteRecomendador=seleccionarCliente(boleta.getRecIdCliente());
			if(clienteRecomendador!=null){
				labelCodigoRecom.setText(clienteRecomendador.getCli_codigo());
				lableDNIRecom.setText(clienteRecomendador.getCli_dni());
				labelApellNombresRecom.setText(clienteRecomendador.getCli_apellNom());
					
			}
			
			

			
		}
	
		
	} 

	@Override
	public Object getObjeto() {
		return null;
	}
	


	
	public Cliente seleccionarCliente(int id){
		MCliente mCliente=new MCliente();
		mCliente.iniciarConexionServidor();
		Cliente cliente=mCliente.seleccionarCliente(id);
		mCliente.cerrarConexionServidor();
		
		return cliente;
	}
	

	public Boleta seleccionarBoleta(int id){
		MBoleta mBoleta=new MBoleta();
		mBoleta.iniciarConexionServidor();
		Boleta boleta=mBoleta.selecccionarBoleta(id);
		mBoleta.cerrarConexionServidor();
		return boleta;
	}
	

	public void seleccionarCantPendientePorBoleta(int idBoleta){
		
		MDevolucion mDevolucion=new MDevolucion();
		mDevolucion.iniciarConexionServidor();
		int cant=mDevolucion.contarPiezasPendientesPorBoleta(idBoleta);
		mDevolucion.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, cant+" Piezas pendientes.", (cant==0)?CPadre.CORRECTO:CPadre.INCORRECTO);
		if(cant==0){
			jfxButtonValidar.setDisable(false);
		}else{
			jfxButtonValidar.setDisable(true);
		}
	}
	
	public void inicializarCamposTDetalleBoleta(){
		tableColumnCantDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("cant"));
		tableColumnCodigoDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnDescripcionDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnPUnitDetalleBoleta.setCellValueFactory(new PropertyValueFactory<>("precioUnit"));
		tableColumnImporteBoleta1.setCellValueFactory(new PropertyValueFactory<>("importe"));

	}
	
	  private ObservableList<TDetalleBoleta> seleccionarDetalleBoleta(int idBoleta){
	    	 MTDetalleBoleta mTDetalleBoleta=new MTDetalleBoleta();
	    	 mTDetalleBoleta.iniciarConexionServidor();
	    	 ObservableList<TDetalleBoleta> array=mTDetalleBoleta.seleccionarDetalleBoletaPorBoleta(idBoleta);
	    	 mTDetalleBoleta.cerrarConexionServidor();
	    	 
	    	 return array;
	  }
}
