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

public class CTransaccionesExternasP extends CPadre implements Initializable {


	   @FXML
	    private Button buttonCerrar2;

	    @FXML
	    private TextField textFieldArticuloBuscarPlanchado;

	    @FXML
	    private Button buttonArticuloBuscar;

	    @FXML
	    private TableView<Articulo> tableViewArticuloPlanchado1;

	    @FXML
	    private TableColumn<Articulo, String> tableColumnId21;

	    @FXML
	    private TableColumn<Articulo, String> tableColumnArticuloCodigo21;

	    @FXML
	    private TableColumn<Articulo, String> tableColumnArticuloConcepto21;
	    
	    @FXML
	    private TableColumn<Articulo, String> tableColumnArticuloUbicación;

	    @FXML
	    private TableColumn<Articulo, String> tableColumnArticuloCantidad21;

	    @FXML
	    private ContextMenu contextMenuArticuloOpciones1;

	    @FXML
	    private TextField textFieldBuscarPiezaPlanchado;

	    @FXML
	    private TableView<Pieza> tableViewPiezaPlanchado;

	    @FXML
	    private TableColumn<Pieza, Pieza> tableColumnId2;

	    @FXML
	    private TableColumn<Pieza, Pieza> tableColumnCodigo2;

	    @FXML
	    private TableColumn<Pieza, Pieza> tableColumnConcepto2;

	    @FXML
	    private TableColumn<Pieza, Pieza> tableColumnCantidad2;

	    @FXML
	    private ContextMenu contextMenuOpciones;

	
	
	
	
   /*agregamos nuevos menus*/
    private MenuItem menuItemPiezaActualizar =new MenuItem("Actualizar Busqueda");
    private MenuT menuTPiezaAlmancen=new MenuT("Almacen (Stock)", MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
    private MenuT menuTPiezaLavanderia=new MenuT("Lavanderia",MTransaccionesExternas.A_UBICACION_LAVANDERIA);
    private MenuT menuTPiezaEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
    //private MenuT menuTPiezaPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
    private MenuT menuTPiezaReparacion=new MenuT("Reparación", MTransaccionesExternas.A_UBICACION_REPARACION);
    
    private MenuItem menuItemArticuloActualizar =new MenuItem("Actualizar Busqueda");
    private MenuT menuTArtiuloAlmancen=new MenuT("Almacen (Stock)",MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK);
    private MenuT menuTArtiuloLavanderia=new MenuT("Lavanderia", MTransaccionesExternas.A_UBICACION_LAVANDERIA);
    private MenuT menuTArtiuloEspera=new MenuT("Espera", MTransaccionesExternas.A_UBICACION_ESPERA);
    //private MenuT menuTArtiuloPlanchado=new MenuT("Planchado", MTransaccionesExternas.A_UBICACION_PLANCHADO);
    private MenuT menuTArtiuloReparacion=new MenuT("Reparación", MTransaccionesExternas.A_UBICACION_REPARACION);
        
    /*Fin de modificaciones en menuitem*/
    private MTransaccionesExternas mTransacciones = new MTransaccionesExternas();


   
    
    //definimos los tres eventos 
   private EventHandler<ActionEvent> eventHandlerPiezaMoverUno= new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
		
			
			Pieza pieza=tableViewPiezaPlanchado.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), 1, MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
			}
			buscarPiezaLavanderia();
			
			
		}
	};
	
	
	private EventHandler<ActionEvent> eventHandlerPiezamoverRegistroCompleto=new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			MenuItem menuItem=(MenuItem)event.getSource();
			MenuT menuT=(MenuT)menuItem.getParentMenu();
			
			Pieza pieza=tableViewPiezaPlanchado.getSelectionModel().getSelectedItem();
			if(pieza==null){
				mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
				return;
			}
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), pieza.getCantTransacExt(), MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
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
			Pieza pieza=tableViewPiezaPlanchado.getSelectionModel().getSelectedItem();
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
			
			if(moverPiezaArticulo(MTransaccionesExternas.TIPO_PIEZA, pieza.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
				
			}else{
				mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
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
	
				
				Articulo articulo=tableViewArticuloPlanchado1.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), 1, MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloPlanchado();
			}
		};
		
		
		private EventHandler<ActionEvent> eventHandlerArticulomoverRegistroCompleto=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--completo-articulo");
				
				Articulo articulo=tableViewArticuloPlanchado1.getSelectionModel().getSelectedItem();
				if(articulo==null){
					mostrarAlerta(menuT.getNameUbicacion(), "", "No selecciona nada para devolver", AlertType.WARNING);
					return;
				}
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), articulo.getPlanchadoCant(), MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloPlanchado();
			}
		};
		
		private EventHandler<ActionEvent> eventHandlerArticuloMoverOtraCantidad=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				MenuItem menuItem=(MenuItem)event.getSource();
				MenuT menuT=(MenuT)menuItem.getParentMenu();
				System.out.println(menuT.getAUbicacion()+"--otra cantidad--articulo");
				Articulo articulo=tableViewArticuloPlanchado1.getSelectionModel().getSelectedItem();
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
				
				if(moverPiezaArticulo(MTransaccionesExternas.TIPO_ARTICULO, articulo.getId(), otra_cantidad, MTransaccionesExternas.DE_UBICACION_PLANCHADO, menuT.getAUbicacion())){
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación se realizó correctamente.", AlertType.CONFIRMATION);
					
				}else{
					mostrarAlerta(menuT.getNameUbicacion(), "", "La operación no se completó.", AlertType.ERROR);
				}
				buscarArticuloPlanchado();
			}
		};
		
		/*eventos para poder agregar este */
		
		/**/
    /*Modificaciones*/


  


    /*Modificaciones*/
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		inicializarTableViewPiezaLavanderia();
		
		recargarTabla();
		/*inio de modificaciones*/
		inicializarTableViewArticuloLavanderia();
		recargarTablaArticulo();
		
		/*fin demodificaciones*/
		
		
		textFieldBuscarPiezaPlanchado.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarPiezaLavanderia();
			}
		});
		
		
		
		buttonCerrar2.setOnAction(new EventHandler<ActionEvent>() {

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
				buscarArticuloPlanchado();

			}
		});

		
		/*es para pieza*/
		contextMenuOpciones.getItems().add(menuItemPiezaActualizar);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaAlmancen);
		contextMenuOpciones.getItems().add(new SeparatorMenuItem());
		contextMenuOpciones.getItems().add(menuTPiezaEspera);
		contextMenuOpciones.getItems().add(menuTPiezaLavanderia);
		//contextMenuOpciones.getItems().add(menuTPiezaPlanchado);
		contextMenuOpciones.getItems().add(menuTPiezaReparacion);
		
		/*Es para articulo*/
		contextMenuArticuloOpciones1.getItems().add(menuItemArticuloActualizar);
		contextMenuArticuloOpciones1.getItems().add(new SeparatorMenuItem());

		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloAlmancen);
		contextMenuArticuloOpciones1.getItems().add(new SeparatorMenuItem());
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloEspera);
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloLavanderia);
		//contextMenuArticuloOpciones1.getItems().add(menuTArtiuloPlanchado);
		contextMenuArticuloOpciones1.getItems().add(menuTArtiuloReparacion);
		
		
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
		menuTPiezaEspera.getItems().addAll(
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
		menuTArtiuloLavanderia.getItems().addAll(
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
		menuTArtiuloReparacion.getItems().addAll(
				new MenuItemT("Mover 1 ud.", eventHandlerArticuloMoverUno),
			    new MenuItemT("Mover registro completo.", eventHandlerArticulomoverRegistroCompleto),
				new SeparatorMenuItem(),
				new MenuItemT("Mover otra cantidad.", eventHandlerArticuloMoverOtraCantidad)
				);


		
		
		/*inicio de modificaciones*/
		
		textFieldArticuloBuscarPlanchado.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				buscarArticuloPlanchado();
			}
		});
		
		
		
		
		/*fin de modicaciones*/
	}
	//lavanderia
	public void inicializarTableViewPiezaLavanderia(){
		tableColumnId2.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnCodigo2.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnConcepto2.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnCantidad2.setCellValueFactory(new PropertyValueFactory<>("cantTransacExt"));
	}
	
	public void recargarTabla(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaPlanchado.setItems(mTransacciones.seleccionarColeccionPiezasPlanchado());
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaPlanchado.refresh();
	}
	
	public void buscarPiezaLavanderia(){
		mTransacciones.iniciarConexionServidor();
		tableViewPiezaPlanchado.setItems(mTransacciones.BuscarBDPiezaPlanchado(textFieldBuscarPiezaPlanchado.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewPiezaPlanchado.refresh();
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
		tableColumnId21.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnArticuloCodigo21.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		tableColumnArticuloConcepto21.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnArticuloUbicación.setCellValueFactory(new PropertyValueFactory<>("ubicacion"));
		tableColumnArticuloCantidad21.setCellValueFactory(new PropertyValueFactory<>("planchadoCant"));
	}
	public void buscarArticuloPlanchado(){
		mTransacciones.iniciarConexionServidor();
		tableViewArticuloPlanchado1.setItems(mTransacciones.BuscarBDArticuloPlanchado(textFieldArticuloBuscarPlanchado.getText().trim()));
		mTransacciones.cerrarConexionServidor();
		tableViewArticuloPlanchado1.refresh();
	}
	
	
	
	public void recargarTablaArticulo(){
		mTransacciones.iniciarConexionServidor();
		tableViewArticuloPlanchado1.setItems(mTransacciones.seleccionarColeccionArticuloPlanchado());
		mTransacciones.cerrarConexionServidor();
		tableViewArticuloPlanchado1.refresh();
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
