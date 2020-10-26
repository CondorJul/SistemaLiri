package controller.transaccionesExternas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.CPadre;
import dal.Articulo;
import dal.Pieza;
import funciones.Servidor;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.transaccionesExternas.MTransaccionesExternas;

public class CTransaccionesExternasR extends CPadre implements Initializable {
	    

    @FXML
    private Button buttonCerrar1;

    @FXML
    private TextField textFieldBuscarArticuloReparacion;

    @FXML
    private Button buttonArticuloBuscar;

    @FXML
    private TableView<Articulo> tableViewArticuloReparacion;

    @FXML
    private TableColumn<Articulo, String> tableColumnId11;

    @FXML
    private TableColumn<Articulo, String> tableColumnArticuloCodigo;

    @FXML
    private TableColumn<Articulo, String> tableColumnArticuloConcepto;
    @FXML
    private TableColumn<Articulo, String> tableColumnArticuloUbicación;

    @FXML
    private TableColumn<Articulo, String> tableColumnArticuloCantidad;

    @FXML
    private ContextMenu contextMenuArticuloOpciones1;

    @FXML
    private TextField textFieldBuscarPiezaReparacion;

    @FXML
    private TableView<Pieza> tableViewPiezaReparacion;

    @FXML
    private TableColumn<Pieza,String> tableColumnId1;

    @FXML
    private TableColumn<Pieza,String> tableColumnCodigo1;

    @FXML
    private TableColumn<Pieza,String> tableColumnConcepto1;

    @FXML
    private TableColumn<Pieza,String> tableColumnCantidad1;

    @FXML
    private ContextMenu contextMenuOpciones;

	
	
	
	
/*agregamos nuevos menus*/
 private MenuItem menuItemPiezaActualizar =new MenuItem("Actualizar Busqueda");
 private MenuT menuTPiezaAlmancen=new MenuT("Almacen (Stock)", MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
 private MenuT menuTPiezaLavanderia=new MenuT("Lavanderia",MTransaccionesExternas.A_UBICACION_LAVANDERIA);
 private MenuT menuTPiezaEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
 private MenuT menuTPiezaPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
 //private MenuT menuTPiezaReparacion=new MenuT("Reparación", MTransaccionesExternas.A_UBICACION_REPARACION);
 
 private MenuItem menuItemArticuloActualizar =new MenuItem("Actualizar Busqueda");
 private MenuT menuTArtiuloAlmancen=new MenuT("Almacen (Stock)",MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
 private MenuT menuTArtiuloLavanderia=new MenuT("Lavanderia", MTransaccionesExternas.A_UBICACION_LAVANDERIA);
 private MenuT menuTArtiuloEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
 private MenuT menuTArtiuloPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
 //private MenuT menuTArtiuloReparacion=new MenuT("Reparación", MTransaccionesExternas.A_UBICACION_REPARACION);
     
 /*Fin de modificaciones en menuitem*/
 private MTransaccionesExternas mTransacciones = new MTransaccionesExternas();



 
 //definimos los tres eventos 
private EventHandler<ActionEvent> eventHandlerPiezaMoverUno= new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
		
			System.out.println(menuT.getAUbicacion()+"--1-pieza");
			
			Pieza pieza=tableViewPiezaReparacion.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), 1, MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
			}
			buscarPiezaReparacion();
			
			
		}
	};
	
	
	private EventHandler<ActionEvent> eventHandlerPiezamoverRegistroCompleto=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
			
			System.out.println(menuT.getAUbicacion()+"--compelto-pieza");
			Pieza pieza=tableViewPiezaReparacion.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), pieza.getCantTransacExt(), MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
			}
			buscarPiezaReparacion();
			
		}
	};
	
	private EventHandler<ActionEvent> eventHandlerPiezaMoverOtraCantidad=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
			System.out.println(menuT.getAUbicacion()+"--otra cantidad-pieza");
			Pieza pieza=tableViewPiezaReparacion.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver.", AlertType.WARNING);
				return;
			}
			
			TextInputDialog texInputDialogIp=new TextInputDialog("0");
			texInputDialogIp.setTitle(menuT.getNameUbicacion());
			texInputDialogIp.setHeaderText("");
			texInputDialogIp.setContentText("Otra cantidad: ");

			int otra_cantidad=0;
			Optional<String> result = texInputDialogIp.showAndWait();
			
			if (result.isPresent()){
				try {
					otra_cantidad=Integer.parseInt(result.get().toString());
				} catch (Exception e) {
					mostrarAlerta(menuT.getNameUbicacion(), "", "Ingrese un número válido", AlertType.WARNING);
					return;
				}
			}
			
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
			}
			buscarPiezaReparacion();
		}
	};
	
	
	   private EventHandler<ActionEvent> eventHandlerArticuloMoverUno= new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--1-articulo");
	
				
				Articulo articulo=tableViewArticuloReparacion.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), 1, MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloReparacion();
			}
		};
		
		
		private EventHandler<ActionEvent> eventHandlerArticulomoverRegistroCompleto=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--completo-articulo");
				
				Articulo articulo=tableViewArticuloReparacion.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), articulo.getReparacionCant(), MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloReparacion();
			}
		};
		
		private EventHandler<ActionEvent> eventHandlerArticuloMoverOtraCantidad=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--otra cantidad--articulo");
				Articulo articulo=tableViewArticuloReparacion.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver.", AlertType.WARNING);
					return;
				}
				
				TextInputDialog texInputDialogIp=new TextInputDialog("0");
				texInputDialogIp.setTitle(menuT.getNameUbicacion());
				texInputDialogIp.setHeaderText("");
				texInputDialogIp.setContentText("Otra cantidad: ");

				int otra_cantidad=0;
				Optional<String> result = texInputDialogIp.showAndWait();
				
				if (result.isPresent()){
					try {
						otra_cantidad=Integer.parseInt(result.get().toString());
					} catch (Exception e) {
						mostrarAlerta(menuT.getNameUbicacion(), "", "Ingrese un número válido", AlertType.WARNING);
						return;
					}
				}
				
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_REPARACION, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloReparacion();
			}
		};
		
		/*eventos para poder agregar este */
		
		/**/
 /*Modificaciones*/





 /*Modificaciones*/
 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewPiezaReparacion();
		
		recargarTabla();
		/*inio de modificaciones*/
		inicializarTableViewArticuloReparacion();
		recargarTablaArticulo();
		
		/*fin demodificaciones*/
		
		
		textFieldBuscarPiezaReparacion.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarPiezaReparacion();
			}
		});
		
		
		
		buttonCerrar1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});
		
		menuItemPiezaActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				buscarPiezaReparacion();

			}
		});
		
		menuItemArticuloActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				buscarArticuloReparacion();

			}
		});

		
		/*es para pieza*/
		contextMenuOpciones.getItems().add(menuItemPiezaActualizar);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaAlmancen);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaEspera);
		contextMenuOpciones.getItems().add(menuTPiezaLavanderia);
		contextMenuOpciones.getItems().add(menuTPiezaPlanchado);
		//contextMenuOpciones.getItems().add(menuTPiezaReparacion);
		
		/*Es para articulo*/
		contextMenuArticuloOpciones1.getItems().add(menuItemArticuloActualizar);
		contextMenuArticuloOpciones1.getItems().add(new SeparatorMenuItem());

		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloAlmancen);
		contextMenuArticuloOpciones1.getItems().add(new SeparatorMenuItem());
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloEspera);
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloLavanderia);
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloPlanchado);
		//contextMenuArticuloOpciones1.getItems().add(menuTArtiuloReparacion);
		
		
		/*menu para menu pieza*/
		menuTPiezaAlmancen.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerPiezaMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerPiezamoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerPiezaMoverOtraCantidad)
				);
		menuTPiezaLavanderia.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerPiezaMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerPiezamoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerPiezaMoverOtraCantidad)
				);
		menuTPiezaPlanchado.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerPiezaMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerPiezamoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerPiezaMoverOtraCantidad)
				);
	
		menuTPiezaEspera.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerPiezaMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerPiezamoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerPiezaMoverOtraCantidad)
				);
			
		
		
		/*menu para menu articulos*/
		/*add metodos a los menus*/
		menuTArtiuloAlmancen.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);
		menuTArtiuloLavanderia.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);
		menuTArtiuloPlanchado.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);
		menuTArtiuloEspera.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);


		
		
		/*inicio de modificaciones*/
		
		textFieldBuscarArticuloReparacion.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarArticuloReparacion();
			}
		});
		
		
		
		
		/*fin de modicaciones*/
	}
	//lavanderia
	public void inicializarTableViewPiezaReparacion(){
		tableColumnId1.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCodigo1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnConcepto1.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnCantidad1.setCellValueFactory(new PropertyValueFactory<>("cantTransacExt"));
	}
	
	public void recargarTabla(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaReparacion.setItems(mTransacciones.seleccionarColeccionPiezasReparacion());
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaReparacion.refresh();
	}
	
	public void buscarPiezaReparacion(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaReparacion.setItems(mTransacciones.BuscarBDPiezaReparacion(textFieldBuscarPiezaReparacion.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaReparacion.refresh();
	}
		
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}
	

	
	
	/*modificaciones con articulo*/
	
	public void inicializarTableViewArticuloReparacion(){
		tableColumnId11.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnArticuloCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnArticuloConcepto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnArticuloUbicación.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
		tableColumnArticuloCantidad.setCellValueFactory(new PropertyValueFactory<>("reparacionCant"));
	}
	public void buscarArticuloReparacion(){
		mTransacciones.iniciarConexionServidor();
		tableViewArticuloReparacion.setItems(mTransacciones.BuscarBDArticuloReparacion(textFieldBuscarArticuloReparacion.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewArticuloReparacion.refresh();
	}
	
	
	
	public void recargarTablaArticulo(){
		mTransacciones.iniciarConexionServidor();
		tableViewArticuloReparacion.setItems(mTransacciones.seleccionarColeccionArticuloReparacion());
		mTransacciones.cerrarConexionServidor();
		tableViewArticuloReparacion.refresh();
	}
	
	public boolean moverPiezaArticulo(String tipo, int id, int cant, String deUbicacion, String aUbicacion){
		boolean estado=false;
		MTransaccionesExternas mTransaccionesExternas=new MTransaccionesExternas();
		mTransaccionesExternas.iniciarConexionServidor();
		estado=mTransaccionesExternas.moverPiezaArticulo(tipo, id,cant, deUbicacion, aUbicacion);
		mTransaccionesExternas.cerrarConexionServidor();
		return estado;
	}
	
	/*fin modificaciones*/

}
