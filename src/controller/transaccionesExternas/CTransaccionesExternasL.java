package controller.transaccionesExternas;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.CPadre;
import dal.Articulo;
import dal.Pieza;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.transaccionesExternas.MTransaccionesExternas;

public class CTransaccionesExternasL extends CPadre implements Initializable {
	
	
	@FXML private Button buttonCerrar;
    @FXML private TextField textFieldBuscarPiezaLavanderia;
    @FXML private TableView<Pieza> tableViewPiezaLavanderia;
    @FXML private TableColumn<Pieza, Integer> tableColumnId;
    @FXML private TableColumn<Pieza, String> tableColumnCodigo;
    @FXML private TableColumn<Pieza, String> tableColumnConcepto;
    @FXML private TableColumn<Pieza, String> tableColumnCantidad;
       
   /*agregamos nuevos menus*/
    private MenuItem menuItemPiezaActualizar =new MenuItem("Actualizar Busqueda");
    private MenuT menuTPiezaAlmancen=new MenuT("Almacen (Stock)", MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
    //private MenuT menuTPiezaLavanderia=new MenuT("Lavanderia",MTransaccionesExternas.A_UBICACION_LAVANDERIA);
    private MenuT menuTPiezaEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
    private MenuT menuTPiezaPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
    private MenuT menuTPiezaReparacion=new MenuT("Reparaci�n", MTransaccionesExternas.A_UBICACION_REPARACION);
    
    private MenuItem menuItemArticuloActualizar =new MenuItem("Actualizar Busqueda");
    private MenuT menuTArtiuloAlmancen=new MenuT("Almacen (Stock)",MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
    //private MenuT menuTArtiuloLavanderia=new MenuT("Lavanderia", MTransaccionesExternas.A_UBICACION_LAVANDERIA);
    private MenuT menuTArtiuloEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
    private MenuT menuTArtiuloPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
    private MenuT menuTArtiuloReparacion=new MenuT("Reparaci�n", MTransaccionesExternas.A_UBICACION_REPARACION);
    
    /*fin nuevos menu*/
    
    @FXML
    private ContextMenu contextMenuOpciones;
    /*Modificaciones en los menuItem*/
    
    
    /*Fin de modificaciones en menuitem*/
    private MTransaccionesExternas mTransacciones = new MTransaccionesExternas();


   
    
    //definimos los tres eventos 
   private EventHandler<ActionEvent> eventHandlerPiezaMoverUno= new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
		
			System.out.println(menuT.getAUbicacion()+"--1-pieza");
			
			Pieza pieza=tableViewPiezaLavanderia.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), 1, MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
			}
			buscarPiezaLavanderia();
			
			
		}
	};
	
	
	private EventHandler<ActionEvent> eventHandlerPiezamoverRegistroCompleto=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
			
			System.out.println(menuT.getAUbicacion()+"--compelto-pieza");
			Pieza pieza=tableViewPiezaLavanderia.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), pieza.getCantTransacExt(), MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
			}
			buscarPiezaLavanderia();
			
		}
	};
	
	private EventHandler<ActionEvent> eventHandlerPiezaMoverOtraCantidad=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
			System.out.println(menuT.getAUbicacion()+"--otra cantidad-pieza");
			Pieza pieza=tableViewPiezaLavanderia.getSelectionModel().getSelectedItem();
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
					mostrarAlerta(menuT.getNameUbicacion(), "", "Ingrese un n�mero v�lido", AlertType.WARNING);
					return;
				}
			}
			
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
			}
			buscarPiezaLavanderia();
		}
	};
	
	
	   private EventHandler<ActionEvent> eventHandlerArticuloMoverUno= new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--1-articulo");
	
				
				Articulo articulo=tableViewArticuloLavanderia.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), 1, MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
				}
				buscarArticuloLavanderia();
			}
		};
		
		
		private EventHandler<ActionEvent> eventHandlerArticulomoverRegistroCompleto=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--completo-articulo");
				
				Articulo articulo=tableViewArticuloLavanderia.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), articulo.getLavanderiaCant(), MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
				}
				buscarArticuloLavanderia();
			}
		};
		
		private EventHandler<ActionEvent> eventHandlerArticuloMoverOtraCantidad=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--otra cantidad--articulo");
				Articulo articulo=tableViewArticuloLavanderia.getSelectionModel().getSelectedItem();
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
						mostrarAlerta(menuT.getNameUbicacion(), "", "Ingrese un n�mero v�lido", AlertType.WARNING);
						return;
					}
				}
				
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_LAVANDERIA, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n se realiz� correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operaci�n no se complet�.", AlertType.ERROR);
				}
				buscarArticuloLavanderia();
			}
		};
		
		/*eventos para poder agregar este */
		
		/**/
    /*Modificaciones*/
    @FXML
    private TextField textFieldArticuloBuscarLavanderia;

    @FXML
    private Button buttonArticuloBuscar;

    @FXML
    private TableView<Articulo> tableViewArticuloLavanderia;

    @FXML
    private TableColumn<Articulo,String > tableColumnArticuloId;

    @FXML
    private TableColumn<Articulo,String > tableColumnArticuloCodigo;

    @FXML
    private TableColumn<Articulo,String > tableColumnArticuloConcepto;
    
    @FXML
    private TableColumn<Articulo,String > tableColumnArticuloUbicaci�n;

    @FXML
    private TableColumn<Articulo,String > tableColumnArticuloCantidad;

    @FXML
    private ContextMenu contextMenuArticuloOpciones;


    /*Modificaciones*/
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewPiezaLavanderia();
		
		recargarTabla();
		/*inio de modificaciones*/
		inicializarTableViewArticuloLavanderia();
		recargarTablaArticulo();
		
		/*fin demodificaciones*/
		
		
		textFieldBuscarPiezaLavanderia.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarPiezaLavanderia();
			}
		});
		
		
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();	
			}
		});
		
		menuItemPiezaActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				buscarPiezaLavanderia();

			}
		});
		
		menuItemArticuloActualizar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				buscarArticuloLavanderia();

			}
		});

		
		/*es para pieza*/
		contextMenuOpciones.getItems().add(menuItemPiezaActualizar);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaAlmancen);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaEspera);
		//contextMenuOpciones.getItems().add(menuTArtiuloLavanderia);
		contextMenuOpciones.getItems().add(menuTPiezaPlanchado);
		contextMenuOpciones.getItems().add(menuTPiezaReparacion);
		
		/*Es para articulo*/
		contextMenuArticuloOpciones.getItems().add(menuItemArticuloActualizar);
		contextMenuArticuloOpciones.getItems().add(new SeparatorMenuItem());

		contextMenuArticuloOpciones.getItems().add(menuTArtiuloAlmancen);
		contextMenuArticuloOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuArticuloOpciones.getItems().add(menuTArtiuloEspera);
		//contextMenuArticuloOpciones.getItems().add(menuTArtiuloLavanderia);
		contextMenuArticuloOpciones.getItems().add(menuTArtiuloPlanchado);
		contextMenuArticuloOpciones.getItems().add(menuTArtiuloReparacion);
		
		
		/*menu para menu pieza*/
		menuTPiezaAlmancen.getItems().addAll(
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
		menuTPiezaPlanchado.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerPiezaMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerPiezamoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerPiezaMoverOtraCantidad)
				);
	
		menuTPiezaReparacion.getItems().addAll(
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
		menuTArtiuloEspera.getItems().addAll(
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
		menuTArtiuloReparacion.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);


		
		
		/*inicio de modificaciones*/
		
		textFieldArticuloBuscarLavanderia.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarArticuloLavanderia();
			}
		});
		
		
		
		
		/*fin de modicaciones*/
	}
	//lavanderia
	public void inicializarTableViewPiezaLavanderia(){
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnConcepto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantTransacExt"));
	}
	
	public void recargarTabla(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaLavanderia.setItems(mTransacciones.seleccionarColeccionPiezasLavanderia());
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaLavanderia.refresh();
	}
	
	public void buscarPiezaLavanderia(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaLavanderia.setItems(mTransacciones.BuscarBDPiezaLavanderia(textFieldBuscarPiezaLavanderia.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaLavanderia.refresh();
	}
		
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		
	}

	@Override
	public Object getObjeto() {
		
		return null;
	}
	

	
	
	/*modificaciones con articulo*/
	
	public void inicializarTableViewArticuloLavanderia(){
		tableColumnArticuloId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnArticuloCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnArticuloConcepto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnArticuloUbicaci�n.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
		tableColumnArticuloCantidad.setCellValueFactory(new PropertyValueFactory<>("lavanderiaCant"));
	}
	public void buscarArticuloLavanderia(){
		mTransacciones.iniciarConexionServidor();
		tableViewArticuloLavanderia.setItems(mTransacciones.BuscarBDArticuloLavanderia(textFieldArticuloBuscarLavanderia.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewArticuloLavanderia.refresh();
	}
	
	
	
	public void recargarTablaArticulo(){
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {

				while(true){
					mTransacciones.iniciarConexionServidor();
					tableViewArticuloLavanderia.setItems(mTransacciones.seleccionarColeccionArticuloLavanderia());
					mTransacciones.cerrarConexionServidor();
					tableViewArticuloLavanderia.refresh();
					Thread.sleep(30000);
				}
			}
			
		};
		Thread hilo=new Thread(task);
		hilo.setDaemon(true);
		hilo.start();
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
