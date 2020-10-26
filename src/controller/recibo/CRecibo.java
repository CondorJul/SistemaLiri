package controller.recibo;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.CPadre;
import controller.alquiler.CSeeBoleta;
import dal.Boleta;
import dal.TAlquilerHoy;
import dal.TRecibo;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.alquiler.MBoleta;
import model.alquiler.MTAlquilerHoy;
import model.recibo.MTRecibo;

public class CRecibo  extends CPadre implements Initializable {

	    @FXML
	    private TextField textFieldBuscarRecibos;

	    @FXML
	    private TableView<TRecibo> TableViewRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnNroRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnFechaRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnSerieRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnNumeroRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnTipoRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnCodDniRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnApellNomRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnSubTotalRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnDesCuponRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnDesAdicRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnTotalRecibos;

	    @FXML
	    private TableColumn<TRecibo, String> tableColumnEstadoRecibos;

	    @FXML
	    private ContextMenu contextMenuOpcRecibos;
	    
	    
	    @FXML
	    private Label labelTotalRecibo;
	    @FXML 
	    private Button buttonBuscar; 
	    
	    @FXML 
	    private DatePicker datePickerReciboFechaInicio;
	    
	    @FXML 
	    private DatePicker datePickerReciboFechaFin;
	    
	    
	    private MenuItem menuItemVer=new MenuItem("Ver en detalle");
	    private MenuItem menuItemDuplicado=new MenuItem("Duplicado");
	    private MenuItem menuItemAnular=new MenuItem("Anular");
	    private MenuItem menuItemRefrescarBusqueda=new MenuItem("Refrescar Busqueda");
	    private MenuItem menuItemRefrescarTodo=new MenuItem("Refrescar Todo");
	    
	    

	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		//primeros en ser ejecutados 
	    	
	    	datePickerReciboFechaFin.setValue(LocalDate.now());
			datePickerReciboFechaInicio.setValue(LocalDate.of(2019, 01, 01));
			
	    	inicializarCamposTRecibo();

	    	seleccionarTRecibo("", java.sql.Date.valueOf(datePickerReciboFechaInicio.getValue()).toLocalDate(),java.sql.Date.valueOf(datePickerReciboFechaFin.getValue()).toLocalDate() );
	    	sumarTotalRecibo();
	    	
	    	
	    	buttonBuscar.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					seleccionarTRecibo(textFieldBuscarRecibos.getText().trim(), java.sql.Date.valueOf(datePickerReciboFechaInicio.getValue()).toLocalDate(),java.sql.Date.valueOf(datePickerReciboFechaFin.getValue()).toLocalDate());	
					sumarTotalRecibo();					
				}
			});
	    	
	    	textFieldBuscarRecibos.setOnKeyReleased(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					seleccionarTRecibo(textFieldBuscarRecibos.getText().trim(), java.sql.Date.valueOf(datePickerReciboFechaInicio.getValue()).toLocalDate(),java.sql.Date.valueOf(datePickerReciboFechaFin.getValue()).toLocalDate());	
					sumarTotalRecibo();
				}
			});
	    	
	    	
	    	menuItemVer.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					StackPane sp=((StackPane)((Stage)TableViewRecibos.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					TRecibo tRecibo=TableViewRecibos.getSelectionModel().getSelectedItem();
					if(tRecibo !=null){
						Boleta boleta=new Boleta();
						boleta.setId(tRecibo.getId());
						String url = "/view/recibo/SeeBoleta.fxml";
						String css = "/estilocss/EstiloModal.css";
						
						//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
						
						CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					
							
						
					}
					
					sp.setVisible(false);
		
				}
			});

	    	
	    	menuItemRefrescarBusqueda.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					seleccionarTRecibo(textFieldBuscarRecibos.getText().trim(), java.sql.Date.valueOf(datePickerReciboFechaInicio.getValue()).toLocalDate(),java.sql.Date.valueOf(datePickerReciboFechaFin.getValue()).toLocalDate());
					sumarTotalRecibo();
					
				}
			});
	    	
	    	menuItemRefrescarTodo.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					textFieldBuscarRecibos.setText("");
					seleccionarTRecibo(textFieldBuscarRecibos.getText().trim(), java.sql.Date.valueOf(datePickerReciboFechaInicio.getValue()).toLocalDate(),java.sql.Date.valueOf(datePickerReciboFechaFin.getValue()).toLocalDate());
					sumarTotalRecibo();
				}
			});
	    	
	    	menuItemDuplicado.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					
					
					TRecibo tRecibo=TableViewRecibos.getSelectionModel().getSelectedItem();
					
					if(tRecibo==null){
						return ;
					}
					if(tRecibo.getEstadoAccion()==1){
						mostrarAlerta("Anulado", "", "No se permite duplicado de boletas anuladas.", AlertType.INFORMATION);
						return;
					}
					if(tRecibo.getTipoInt()==3){
						mostrarAlerta("Duplicado", "", "Solo se permite duplicado de boleta por venta o alquiler.", AlertType.INFORMATION);
						return;
					}
					
					Boleta boleta=agregarBoletaDuplicado(tRecibo.getIdCliente(), tRecibo.getId());
					
					
					if(boleta!=null){
					StackPane sp=((StackPane)((Stage)TableViewRecibos.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
						String url = "/view/alquiler/SeeBoleta.fxml";
						String css = "/estilocss/EstiloModal.css";
						
						//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
						
						CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CSeeBoleta.FACTURAR);		
					
					sp.setVisible(false);
					}else{
						mostrarAlerta("Duplicado", "", "Surgio un error al intentar duplicar el error.", AlertType.ERROR);
					}
					
				}
			});
	    	
	    	menuItemAnular.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					TRecibo tRecibo=TableViewRecibos.getSelectionModel().getSelectedItem();
					if(tRecibo !=null){
						
						if(tRecibo.getEstadoAccion()!=1){
							if(consultarAlerta("¿Está seguro que desea anular?"))
								if(anularReciboBoleta(tRecibo.getId())){
									mostrarAlerta("Anular", "", "Boleta anulada.", AlertType.INFORMATION);
									TableViewRecibos.getSelectionModel().getSelectedItem().setEstado("Anulado");
									TableViewRecibos.getSelectionModel().getSelectedItem().setEstadoAccion(1);
									TableViewRecibos.refresh();
								}
						}else{
							mostrarAlerta("Anular", "", "Esta boleta ya ha sido anulada.", AlertType.INFORMATION);
						}
					}
				}
			});
	    	// añadir añadir menuItemas
	    	contextMenuOpcRecibos.getItems().add(menuItemRefrescarBusqueda);
	    	contextMenuOpcRecibos.getItems().add(menuItemRefrescarTodo);
	    	contextMenuOpcRecibos.getItems().add(menuItemVer);
	    	contextMenuOpcRecibos.getItems().add(menuItemDuplicado);
	    	contextMenuOpcRecibos.getItems().add(new SeparatorMenuItem());
	    	contextMenuOpcRecibos.getItems().add(menuItemAnular);
	    	
		}

	    
	    public void inicializarCamposTRecibo(){
			tableColumnNroRecibos.setCellValueFactory(new PropertyValueFactory<>("nro"));
			tableColumnFechaRecibos.setCellValueFactory(new PropertyValueFactory<>("fEntrega"));
			tableColumnSerieRecibos.setCellValueFactory(new PropertyValueFactory<>("serie"));
			tableColumnNumeroRecibos.setCellValueFactory(new PropertyValueFactory<>("numero"));
			tableColumnTipoRecibos.setCellValueFactory(new PropertyValueFactory<>("tipo"));
			tableColumnCodDniRecibos.setCellValueFactory(new PropertyValueFactory<>("codDni"));
			tableColumnApellNomRecibos.setCellValueFactory(new PropertyValueFactory<>("apellNom"));
			tableColumnSubTotalRecibos.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
			tableColumnDesCuponRecibos.setCellValueFactory(new PropertyValueFactory<>("desCupones"));
			tableColumnDesAdicRecibos.setCellValueFactory(new PropertyValueFactory<>("desAdic"));
			tableColumnTotalRecibos.setCellValueFactory(new PropertyValueFactory<>("total"));
			tableColumnEstadoRecibos.setCellValueFactory(new PropertyValueFactory<>("estado"));
		}

		
		private MTRecibo mTRecibo=new MTRecibo();
		public void seleccionarTRecibo(String buscar, LocalDate fechaInicio, LocalDate fechaFin){
			mTRecibo.iniciarConexionServidor();
			TableViewRecibos.setItems(mTRecibo.seleccionarTRecibo(buscar,fechaInicio,fechaFin));
			TableViewRecibos.refresh();
			mTRecibo.cerrarConexionServidor();
			System.out.println("Trecibo");
			
		}
		
		public void sumarTotalRecibo(){
			double totalHoy=0;
			for (TRecibo trecibo : TableViewRecibos.getItems()) {
				if(trecibo.getEstadoAccion()!=1){
					totalHoy=totalHoy+Double.parseDouble(trecibo.getTotal());
				}
			}
			

			 DecimalFormat df = new DecimalFormat("#0.00");
			 String resultado = df.format(totalHoy);
			 labelTotalRecibo.setText(resultado);
		}
		
		
		private boolean anularReciboBoleta(int idBoleta){
			MBoleta mBoleta=new MBoleta();
			Boleta boleta=new Boleta();
			boleta.setId(idBoleta);
			
			boolean estado=false;
			
			
			boleta.setEstadoEliminado(Boleta.ESTADO_ELIMINADO_TRUE);
			mBoleta.iniciarConexionServidor();
			mBoleta.actualizarBoletaAnular(boleta);
			mBoleta.cerrarConexionServidor();
			if(mBoleta.getNoError()==MBoleta.CORRECTO){
				estado=true;
			}
		return estado;	
		}


		@Override
		public void ejecutarAcciones(Object objeto, int tipoModal) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public Object getObjeto() {
			// TODO Auto-generated method stub
			return null;
		}
		
	
		private Boleta agregarBoletaDuplicado(int idCliente, int  idBoleta){
			MBoleta mBoleta=new MBoleta();
			Boleta boleta=new Boleta();
			boleta.setIdBoletaDuplicado(idBoleta);
			boleta.setIdCliente(idCliente);
			
			
			
			mBoleta.iniciarConexionServidor();
			int idBoletaReturn=mBoleta.agregarBoletaDuplicado(boleta);
			boleta.setId(idBoletaReturn);
			mBoleta.cerrarConexionServidor();
			if(mBoleta.getNoError()!=MBoleta.CORRECTO){
				boleta=null;
			}
			
			return boleta;
		}
		
		

}
