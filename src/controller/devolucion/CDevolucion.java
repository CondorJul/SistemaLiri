package controller.devolucion;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.Boleta;
import dal.TDevolucion;
import dal.TRecibo;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.alquiler.MReservacion;
import model.devolucion.MDevolucion;
import sesion.Permisos;

public class CDevolucion extends CPadre implements Initializable{
		
		
		private MDevolucion mDevolucion=new MDevolucion();
		private TDevolucion tDevolucion=new TDevolucion();
		
		@FXML
		private Button buttonBuscarResevacion; 
	 @FXML
	    private JFXCheckBox jFXCheckBoxBuscarHoy;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarSemana;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarMes;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarDesdeCreacion;

	    @FXML
	    private TextField textFieldBuscarReserva;

	    @FXML
	    private TableView<TDevolucion> tableViewDevolucion;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnNro;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnFecha;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnSerie;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnNumero;
	    
	    @FXML 
	    private TableColumn<TDevolucion, String> tableColumn1erArticuloPieza;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnCodigoCliente;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnDNI;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnApellNom;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnSubtotal;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnDesCupones;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnDesAdicional;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnImporteTotal;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnFechEntrega;

	    @FXML
	    private TableColumn<TDevolucion,String> tableColumnFechDevolucion;
	    
	    @FXML 
	    private TableColumn<TDevolucion,String> tableColumnTiempoRestante;
	    
	    
	    
	    ///inico devuelto
	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarHoyDevuelto;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarSemanaDevuelto;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarMesDevuelto;

	    @FXML
	    private JFXCheckBox jFXCheckBoxBuscarDesdeCreacionDevuelto;

	    @FXML
	    private TextField textFieldBuscarReservaDevuelto;

	    @FXML
	    private Button buttonBuscarDevuelto;

	    @FXML
	    private TableView<TDevolucion> tableViewDevolucionDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnNroDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnFechaDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnSerieDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnNumeroDevuelto;
	    
	    @FXML
	    private TableColumn<TDevolucion, String> tableColumn1erArticuloPiezaDevuelto;
 
	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnCodigoClienteDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnDNIDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnApellNomDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnSubtotalDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnDesCuponesDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnDesAdicionalDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnImporteTotalDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnFechEntregaDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnFechDevolucionDevuelto;

	    @FXML
	    private TableColumn<TDevolucion, String> tableColumnTFechaDevuelltoDevuelto;

	    @FXML
	    private ContextMenu contextMenuOpcDevolucionesDevuelto;
	    
	    
	    
	    
	    ///fin devuelto

	    @FXML
	    private ContextMenu contextMenuOpcDevoluciones;
	    
	    private MenuItem menuItemDevolver=new MenuItem("Devolver");
	    private MenuItem menuItemRefrescarTodo=new MenuItem("Refrescar Todo");
	    private MenuItem menuItemRefrescarBusqueda=new MenuItem("Refrescar Búsqueda");
	    private MenuItem menuItemVerEnDetalle=new MenuItem("Ver en detalle");
	    
	    private String paramTiemSelecDevol=MDevolucion.INICIO_CREACION;
	    
	    
	    private MenuItem menuItemVerEnDetalleDevuelto=new MenuItem("Ver en detalle");
	    private MenuItem menuItemRefrescarTodoDevuelto=new MenuItem("Refrescar Todo");
	    private MenuItem menuItemRefrescarBusquedaDevuelto=new MenuItem("Refrescar Búsqueda");
	    
	    private String paramTiemSelecDevuelto=MDevolucion.INICIO_CREACION;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarCamposAlquilerHoy();
		inicializarCamposDevueltos();
		
		
		seleccionarDevolucion("",paramTiemSelecDevol);
		seleccionarDevueltos("", paramTiemSelecDevuelto);
		actualizar_cliente_reputacion();
		
		//devolucion
		jFXCheckBoxBuscarHoy.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoy.setSelected(true);
				jFXCheckBoxBuscarSemana.setSelected(false);
				jFXCheckBoxBuscarMes.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacion.setSelected(false);
				
				paramTiemSelecDevol=MDevolucion.HOY;
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
			}
		});;
		
		
		jFXCheckBoxBuscarSemana.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoy.setSelected(false);
				jFXCheckBoxBuscarSemana.setSelected(true);
				jFXCheckBoxBuscarMes.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacion.setSelected(false);
				
				paramTiemSelecDevol=MDevolucion.SEMANA;
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
			}
		});;
		
		jFXCheckBoxBuscarMes.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoy.setSelected(false);
				jFXCheckBoxBuscarSemana.setSelected(false);
				jFXCheckBoxBuscarMes.setSelected(true);
				jFXCheckBoxBuscarDesdeCreacion.setSelected(false);
				
				paramTiemSelecDevol=MDevolucion.MES;
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
			}
		});;
		
		jFXCheckBoxBuscarDesdeCreacion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoy.setSelected(false);
				jFXCheckBoxBuscarSemana.setSelected(false);
				jFXCheckBoxBuscarMes.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacion.setSelected(true);
				
				paramTiemSelecDevol=MDevolucion.INICIO_CREACION;
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
			}
		});;
		

		textFieldBuscarReserva.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarDevolucion(textFieldBuscarReserva.getText(), paramTiemSelecDevol);
			}
		});
		
		buttonBuscarResevacion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarDevolucion(textFieldBuscarReserva.getText(), paramTiemSelecDevol);
 
			}
		});
		
		
		menuItemDevolver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				/*MenuItem menuItem=(MenuItem)event.getSource();
				ContextMenu contextMenu=menuItem.getParentPopup();
				*/
				StackPane sp=((StackPane)((Stage)tableViewDevolucion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				String url = "/view/devolucion/AddDevolucion.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				
				TDevolucion tDevolucion=tableViewDevolucion.getSelectionModel().getSelectedItem();
				if(tDevolucion!=null){
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, tDevolucion, CPadre.INSERT);	
					seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
				}
				sp.setVisible(false);
				
			}
		});
		
		menuItemRefrescarBusqueda.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
				
			}
		});
		menuItemRefrescarTodo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				textFieldBuscarReserva.setText("");
				seleccionarDevolucion(textFieldBuscarReserva.getText(),paramTiemSelecDevol);
				
			}
		});
		menuItemVerEnDetalle.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewDevolucion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				TDevolucion tDevolucion=tableViewDevolucion.getSelectionModel().getSelectedItem();
				
				if(tDevolucion !=null){
					Boleta boleta=new Boleta();
					boleta.setId(tDevolucion.getId());
					String url = "/view/recibo/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					

				}
				
				sp.setVisible(false);
						
			}
		});

		
		
		//devuelto
		
		jFXCheckBoxBuscarHoyDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoyDevuelto.setSelected(true);
				jFXCheckBoxBuscarSemanaDevuelto.setSelected(false);
				jFXCheckBoxBuscarMesDevuelto.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacionDevuelto.setSelected(false);
				
				paramTiemSelecDevuelto=MDevolucion.HOY_D;
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
			}
		});;
		
		
		jFXCheckBoxBuscarSemanaDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoyDevuelto.setSelected(false);
				jFXCheckBoxBuscarSemanaDevuelto.setSelected(true);
				jFXCheckBoxBuscarMesDevuelto.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacionDevuelto.setSelected(false);
				
				paramTiemSelecDevuelto=MDevolucion.SEMANA_D;
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
			}
		});;
		
		jFXCheckBoxBuscarMesDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoyDevuelto.setSelected(false);
				jFXCheckBoxBuscarSemanaDevuelto.setSelected(false);
				jFXCheckBoxBuscarMesDevuelto.setSelected(true);
				jFXCheckBoxBuscarDesdeCreacionDevuelto.setSelected(false);
				
				paramTiemSelecDevuelto=MDevolucion.MES_D;
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
			}
		});;
		
		jFXCheckBoxBuscarDesdeCreacionDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				jFXCheckBoxBuscarHoyDevuelto.setSelected(false);
				jFXCheckBoxBuscarSemanaDevuelto.setSelected(false);
				jFXCheckBoxBuscarMesDevuelto.setSelected(false);
				jFXCheckBoxBuscarDesdeCreacionDevuelto.setSelected(true);
				
				paramTiemSelecDevuelto=MDevolucion.INICIO_CREACION_D;
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
			}
		});;
		

		textFieldBuscarReservaDevuelto.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(), paramTiemSelecDevuelto);
			}
		});
		
		buttonBuscarDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(), paramTiemSelecDevuelto);
 
			}
		});
		
		
	
		
		menuItemRefrescarBusquedaDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
				
			}
		});
		menuItemRefrescarTodoDevuelto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				textFieldBuscarReservaDevuelto.setText("");
				seleccionarDevueltos(textFieldBuscarReservaDevuelto.getText(),paramTiemSelecDevuelto);
				
			}
		});
		menuItemVerEnDetalleDevuelto.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)tableViewDevolucion.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				
				TDevolucion tDevolucion=tableViewDevolucionDevuelto.getSelectionModel().getSelectedItem();
				
				if(tDevolucion !=null){
					
					Boleta boleta=new Boleta();
					boleta.setId(tDevolucion.getId());
					String url = "/view/recibo/SeeBoleta.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					//int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, boleta, CPadre.INSERT);					
	
				}
				
				sp.setVisible(false);

						
			}
		});
		//fin devuelto
		
		contextMenuOpcDevoluciones.getItems().add(menuItemRefrescarBusqueda);
		contextMenuOpcDevoluciones.getItems().add(menuItemRefrescarTodo);
		contextMenuOpcDevoluciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpcDevoluciones.getItems().add(menuItemDevolver);
		contextMenuOpcDevoluciones.getItems().add(menuItemVerEnDetalle);

		
		contextMenuOpcDevolucionesDevuelto.getItems().add(menuItemRefrescarBusquedaDevuelto);
		contextMenuOpcDevolucionesDevuelto.getItems().add(menuItemRefrescarTodoDevuelto);
		contextMenuOpcDevolucionesDevuelto.getItems().add(new SeparatorMenuItem());
		contextMenuOpcDevolucionesDevuelto.getItems().add(menuItemVerEnDetalleDevuelto);
		
		menuItemDevolver.setVisible(Permisos.DEVOL_DEVOLUCION);
		
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}

	
	public void inicializarCamposAlquilerHoy(){
		tableColumnNro.setCellValueFactory(new PropertyValueFactory<>("nroEnum"));
		tableColumnFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		tableColumnSerie.setCellValueFactory(new PropertyValueFactory<>("serieBoleta"));
		tableColumnNumero.setCellValueFactory(new PropertyValueFactory<>("numeroBoleta"));
		tableColumn1erArticuloPieza.setCellValueFactory(new PropertyValueFactory<>("primerArticuloPieza"));
		tableColumnCodigoCliente.setCellValueFactory(new PropertyValueFactory<>("codigoCliente"));
		tableColumnDNI.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
		tableColumnApellNom.setCellValueFactory(new PropertyValueFactory<>("apellNom"));
		tableColumnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		tableColumnDesCupones.setCellValueFactory(new PropertyValueFactory<>("desCupones"));
		tableColumnDesAdicional.setCellValueFactory(new PropertyValueFactory<>("desAdic"));
		tableColumnImporteTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
		tableColumnFechEntrega.setCellValueFactory(new PropertyValueFactory<>("fEntrega"));
		tableColumnFechDevolucion.setCellValueFactory(new PropertyValueFactory<>("fDevolucion"));
		tableColumnTiempoRestante.setCellValueFactory(new PropertyValueFactory<>("tiempoRetante"));
		
	}
	
	public void seleccionarDevolucion(String buscar,String tiempo){
		
		mDevolucion.iniciarConexionServidor();
		tableViewDevolucion.setItems(mDevolucion.seleccionarTDevolucion(buscar, tiempo));
		tableViewDevolucion.refresh();
		mDevolucion.cerrarConexionServidor();
		
	}
	public void actualizar_cliente_reputacion(){
		MDevolucion mDevolucion=new MDevolucion();
		mDevolucion.iniciarConexionServidor();
		mDevolucion.actualizar_cliente_reputacion();
		mDevolucion.cerrarConexionServidor();
		
	}
	
	
	//devuelto
	public void inicializarCamposDevueltos(){
		tableColumnNroDevuelto.setCellValueFactory(new PropertyValueFactory<>("nroEnum"));
		tableColumnFechaDevuelto.setCellValueFactory(new PropertyValueFactory<>("fecha"));
		tableColumnSerieDevuelto.setCellValueFactory(new PropertyValueFactory<>("serieBoleta"));
		tableColumnNumeroDevuelto.setCellValueFactory(new PropertyValueFactory<>("numeroBoleta"));
		tableColumn1erArticuloPiezaDevuelto.setCellValueFactory(new PropertyValueFactory<>("primerArticuloPieza"));
		tableColumnCodigoClienteDevuelto.setCellValueFactory(new PropertyValueFactory<>("codigoCliente"));
		tableColumnDNIDevuelto.setCellValueFactory(new PropertyValueFactory<>("dniCliente"));
		tableColumnApellNomDevuelto.setCellValueFactory(new PropertyValueFactory<>("apellNom"));
		tableColumnSubtotalDevuelto.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
		tableColumnDesCuponesDevuelto.setCellValueFactory(new PropertyValueFactory<>("desCupones"));
		tableColumnDesAdicionalDevuelto.setCellValueFactory(new PropertyValueFactory<>("desAdic"));
		tableColumnImporteTotalDevuelto.setCellValueFactory(new PropertyValueFactory<>("total"));
		tableColumnFechEntregaDevuelto.setCellValueFactory(new PropertyValueFactory<>("fEntrega"));
		tableColumnFechDevolucionDevuelto.setCellValueFactory(new PropertyValueFactory<>("fDevolucion"));
		tableColumnFechaDevuelto.setCellValueFactory(new PropertyValueFactory<>("fechaDevuelto"));
		
	}
	
	public void seleccionarDevueltos(String buscar,String tiempo){
		
		mDevolucion.iniciarConexionServidor();
		tableViewDevolucionDevuelto.setItems(mDevolucion.seleccionarDevueltos(buscar, tiempo));
		tableViewDevolucionDevuelto.refresh();
		mDevolucion.cerrarConexionServidor();
		
	}


	
}
