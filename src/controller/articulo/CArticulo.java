package controller.articulo;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.channels.SelectableChannel;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.hyperic.sigar.Mem;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import controller.proveedor.CAddPoveedor;
import ctreetablewiew.Unidad;
import dal.DatoAtomico;
import funciones.Servidor;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.articulo.MArticulo;
import model.articulo.MPieza;
import model.datoAtomico.MDatoAtomico;
import sesion.Permisos;

public class CArticulo extends CPadre implements Initializable{
	
    @FXML
    private ContextMenu contextMenuOpcionesTabla;

    @FXML
    private ComboBox<DatoAtomico> comboBoxFamiliaArticulosPiezas;
    
    @FXML
    private ComboBox<String> comboBoxTallaArticulosPiezas;
    
    @FXML
    private ComboBox<String> comboBoxGeneroArticuloPieza;
	@FXML
    private JFXButton jfxButtonNuevoArticulo;

    @FXML
    private TextField textFieldBuscarArticulo;

    @FXML
    private TreeTableView<Unidad> treeTableViewArticulo;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnCodigo;
     
    @FXML
    private TreeTableColumn<Unidad, String> tableColumnNombre;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnTalla;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnGenero;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnUbicacion;
    
    @FXML
    private TreeTableColumn<Unidad, String> tableColumnFamilia;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnNroPiezas;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnStock;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnPreCompra;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnPreAlquiler;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnPreVenta;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnReqPlanchar;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnTipoMant;
    
    @FXML
    private TreeTableColumn<Unidad, String> tableColumnLavanderiaCant;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnReparacionCant;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnPlanchadoCant;

    @FXML
    private TreeTableColumn<Unidad, String> tableColumnEsperaCant;
    
    @FXML
    private MenuItem menuItemActualizarArticuloPieza;

    private MenuItem menuItemAgregarPieza =new MenuItem("Agregar Pieza");
    
    private MenuItem menuItemVerImgenArticulo=new MenuItem("Ver Imagen de Artículo");
    private MenuItem menuItemRefrescarTodo=new MenuItem("Refrescar Todo");
    private MenuItem menuitemRefrescarBusqueda=new MenuItem("Refrescar Busqueda");
    private MenuItem menuItemAgregarStockEn=new MenuItem("Agregar Stock En");
    
    private MenuItem menuItemVerRentabilidad=new MenuItem("Ver Rentabilidad");
    
    private MenuItem menuItemVerDetalleSalida=new MenuItem("Detalle Salida (Venta/Alquiler)");
    
    private TreeItem<Unidad> dummyRoot;
    
	    
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTallaArticulosPiezas.getItems().addAll("Todos","0", "2","4","6","8","10","12","14","16","S","M", "L", "XL","XXL","Pequeño","Mediano","Grande");
		comboBoxGeneroArticuloPieza.getItems().addAll("Todos","Femenino","Masculino","Femenino/Masculino");
		cargarComboBoxFamilia();
		
		comboBoxTallaArticulosPiezas.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				seleccionarArticulos();
				
			}
		});
		
		comboBoxGeneroArticuloPieza.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				seleccionarArticulos();
				
			}
		});
		
		comboBoxFamiliaArticulosPiezas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				seleccionarArticulos();
			}
		});
		jfxButtonNuevoArticulo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				String url = "/view/articulo/AddArticulo.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				CAddArticulo cArticulo = (CAddArticulo)cpadre;
				seleccionarArticulos();
				
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				
			}
		});	
		
		
		menuItemAgregarPieza.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				//((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(true);
				
				MPieza mPiezas=new MPieza();
				mPiezas.iniciarConexionServidor();
				int nroPiezas=mPiezas.seleccionarNroPiezaArticulo(treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getIdArticulo());
				mPiezas.cerrarConexionServidor();
				if(nroPiezas<treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getNroPiezasArticulo()){
					String url = "/view/articulo/AddPieza.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					int id=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, id, CPadre.INSERT);
					//CAddPieza cPiezas =(CAddPieza)cpadre;
					
					seleccionarArticulos();  
				}else{
					
					mostrarAlerta("Agregar Pieza", "","Usted establecio un limite de piezas.\n"
    						+ "Si desea agregar una pieza más modifique la cantidad de Piezas." , Alert.AlertType.WARNING);
				}
				
				//CAddArticulo cArticulo = (CAddArticulo)cpadre;
				
				//((StackPane)((Stage)((Node)event.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
				sp.setVisible(false);

			}
		});
		
		
		mostrarArticulosPiezas();
		
		
		menuItemActualizarArticuloPieza.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				
				
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				if(unidad!=null){

					int id=unidad.getValue().getTipoClase();
					int idUnidad=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
					
					if(id==Unidad.ARTICULO){
						String url = "/view/articulo/AddArticulo.fxml";
						String css = "/estilocss/EstiloModal.css";
						
						CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, idUnidad, CPadre.UPDATE);
						CAddArticulo cArticulo = (CAddArticulo)cpadre;
					}else if(id==Unidad.PIEZA){
						String url = "/view/articulo/AddPieza.fxml";
						String css = "/estilocss/EstiloModal.css";
						CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, idUnidad, CPadre.UPDATE);
						CAddPieza cPiezas =(CAddPieza)cpadre;
					}
					seleccionarArticulos();
					//treeTableViewArticulo.getRoot().getChildren().get(1).setExpanded(true);
					treeTableViewArticulo.getSelectionModel().select(unidad);
					//unidad.setExpanded(true);
						
				}
				sp.setVisible(false);

			}
		});
		
		menuItemVerImgenArticulo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				int id=unidad.getValue().getTipoClase();
				int idUnidad=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				
				if(id==Unidad.ARTICULO){
					String url = "/view/articulo/SeeImgArticulo.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, idUnidad, CPadre.NULO);
					
				}
				sp.setVisible(false);
	
			}
		});
		menuitemRefrescarBusqueda.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				seleccionarArticulos();
				
			}
		});
		
		menuItemRefrescarTodo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				textFieldBuscarArticulo.setText("");
				seleccionarArticulos();
				
			}
		});
		textFieldBuscarArticulo.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarArticulos();
			}
		});
		contextMenuOpcionesTabla.getItems().addAll(new SeparatorMenuItem(),menuitemRefrescarBusqueda, menuItemRefrescarTodo, new SeparatorMenuItem());
		contextMenuOpcionesTabla.getItems().addAll(menuItemAgregarPieza,menuItemVerImgenArticulo);
		
		contextMenuOpcionesTabla.getItems().add(menuItemVerRentabilidad);
		
		contextMenuOpcionesTabla.getItems().add(new SeparatorMenuItem());
		contextMenuOpcionesTabla.getItems().add(menuItemAgregarStockEn);
		
		contextMenuOpcionesTabla.getItems().add(new SeparatorMenuItem());
		contextMenuOpcionesTabla.getItems().add(menuItemVerDetalleSalida);
		
		
		
		menuItemVerRentabilidad.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				int id=unidad.getValue().getTipoClase();
				int idUnidad=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				
				if(id==Unidad.ARTICULO){
					String url = "/view/articulo/SeeRentabilidadArticulo.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, idUnidad, CPadre.NULO);
					//CAddArticulo cArticulo = (CAddArticulo)cpadre;
				}
				
				sp.setVisible(false);
			}
		});
		
		menuItemVerDetalleSalida.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				int id=unidad.getValue().getTipoClase();
				int idUnidad=treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getId();
				
				if(id==Unidad.ARTICULO){
					String url = "/view/articulo/DetalleArticulo.fxml";
					String css = "/estilocss/EstiloModal.css";
					
					CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, idUnidad, CPadre.NULO);
					//CAddArticulo cArticulo = (CAddArticulo)cpadre;
				}
				
				sp.setVisible(false);
				
			}
		});
		
		
		
		menuItemAgregarStockEn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				
				
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				int tipoClase=unidad.getValue().getTipoClase();
				
				if(tipoClase==Unidad.ARTICULO){
					int id=unidad.getValue().getId();
					
					TextInputDialog dialog = new TextInputDialog("0"); 
					dialog.setTitle("Aumentar Stock de Articulo en:");
					dialog.setHeaderText("");
					dialog.setContentText("Ingrese el Stock entrante:");
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
						int stockEntrante=0;
						try{
							stockEntrante=Integer.parseInt(result.get());
							actualizarStockEn( id, stockEntrante);	
							seleccionarArticulos();
						}catch(Exception e){
							mostrarAlerta("Aumentar Stok de Articulo en:","", "Por favor ingrese un número valido", AlertType.WARNING);
						}
						
						
					}
					
					
				}else{
					int id=unidad.getValue().getId();
					
					TextInputDialog dialog = new TextInputDialog("0"); 
					dialog.setTitle("Aumentar Stock de Pieza en:");
					dialog.setHeaderText("");
					dialog.setContentText("Ingrese el Stock entrante:");
					Optional<String> result = dialog.showAndWait();
					if (result.isPresent()){
						int stockEntrante=0;
						try{
							stockEntrante=Integer.parseInt(result.get());
							actualizarStockEnPieza(id, stockEntrante);	
							seleccionarArticulos();
						}catch(Exception e){
							mostrarAlerta("Aumentar Stok de Pieza en:","", "Por favor ingrese un número valido", AlertType.WARNING);
						}
						
						
					}
					
				}
				sp.setVisible(false);
	
				
			}
		});

		treeTableViewArticulo.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				
				StackPane sp=((StackPane)((Stage)treeTableViewArticulo.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				
				
				TreeItem<Unidad> unidad=treeTableViewArticulo.getSelectionModel().getSelectedItem();
				if(unidad!=null){

					int id=unidad.getValue().getTipoClase();
					
					if(id==Unidad.ARTICULO){
						menuItemAgregarPieza.setDisable(false);
						menuItemVerImgenArticulo.setDisable(false);
						//menuItemAgregarPieza.setDisable(false);
						menuItemActualizarArticuloPieza.setText("Actualizar Artículo");
					}else if(id==Unidad.PIEZA){
						menuItemAgregarPieza.setDisable(true);
						menuItemVerImgenArticulo.setDisable(true);
						//contextMenuOpcionesTabla.getItems().removeAll(menuItemAgregarPieza,menuItemVerImgenArticulo);
						//menuItemAgregarPieza.setDisable(true);
						menuItemActualizarArticuloPieza.setText("Actualizar Pieza");
					}
				}
				//System.out.println(treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getTipoClase()); 

				
				
				/*final TreeItem<Unidad> root = new TreeItem<>(new Unidad(
						   2,"Productommmmms", ""));*/
			/*	  dummyRoot.getChildren().add(root);
				  List<Unidad> unidades = Arrays.<Unidad> asList(
							new Unidad(1,"1246666","asdf"),
							new Unidad(2, "fdahhhhsdf", "sadfasf"));
				  unidades.stream().forEach((unidad) -> {
				      root.getChildren().add(new TreeItem<>(unidad));
				    });

				System.out.println(treeTableViewArticulo.getSelectionModel().getSelectedItem().getValue().getCodigo()); */
				//treeTableViewArticulo.getTreeItem(treeTableViewArticulo.getSelectionModel().getSelected;
				//treeTableViewArticulo.getSelectionModel().getSelectedItem().addEventHandler(, eventHandler);
				
				sp.setVisible(false);
	
			}
		});
		inicializarCamposTreeTableArticulos();
		seleccionarArticulos();
		
		//privilegios 
		jfxButtonNuevoArticulo.setVisible(Permisos.ADD_ARTICULOS);
		menuItemActualizarArticuloPieza.setVisible(Permisos.UPD_ARTICULOS);
		menuItemAgregarPieza.setVisible(Permisos.ADD_ARTICULOS);
		
		
		
		
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
	
	
	public void mostrarArticulosPiezas(){
	/*	List<Unidad> unidades = Arrays.<Unidad> asList(
				new Unidad(1,"124","asdf"),
				new Unidad(2, "fdasdf", "sadfasf")
				);
			  
			  

			  final TreeItem<Unidad> root1 = new TreeItem<>(new Unidad(2,"2","fdas"));
			  
			  final TreeItem<Unidad> root2 = new TreeItem<>(new Unidad(
				   2,"Productos", ""));
		
		
		//TreeItem<Unidad> dummyRoot=treeTableViewArticulo.getRoot();
		 dummyRoot=new  TreeItem<>(); 
		  dummyRoot.getChildren().addAll(root1,root2);
		  root1.setExpanded(true);
		  root2.setExpanded(true);
		  
	    //root.setExpanded(true);
		  unidades.stream().forEach((unidad) -> {
	      root1.getChildren().add(new TreeItem<>(unidad));
	    });
		
		  

			    
			   
			    

			//    treeTableViewArticulo.setTreeColumn();
			
		
			    treeTableViewArticulo.setRoot(dummyRoot);
			    treeTableViewArticulo.setShowRoot(false);
			    treeTableViewArticulo.refresh();
			  //  TreeTableView<Employee> treeTableView = new TreeTableView<>(dummyRoot);
			    //treeTableView.setShowRoot(false);
			    
			    //treeTableView.getColumns().setAll(empColumn, emailColumn);
			    
			    //treeTableView.setTableMenuButtonVisible(true);
			    
			    
			    //sceneRoot.getChildren().add(treeTableView);
			    //stage.setScene(scene);
			    //stage.show();
			     * *
			     */
	}

	
	public void inicializarCamposTreeTableArticulos(){
		 	tableColumnCodigo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad,String> param)-> new ReadOnlyStringWrapper(
				 param.getValue().getValue().getCodigo()
		 	));
		 	
		 	tableColumnNombre.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		    	param.getValue().getValue().getDescripcion()
		    ));
		 	
		 	tableColumnTalla.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
			    	param.getValue().getValue().getTalla()
			    	
			));
		 	
		 	tableColumnUbicacion.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
			    	param.getValue().getValue().getUbicacion()
			    	
			));
		 	
		 	tableColumnGenero.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			param.getValue().getValue().getGenero()
		 	));
		 	
		 	tableColumnFamilia.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			param.getValue().getValue().getFamilia()
		 	));
		 	
		 	tableColumnNroPiezas.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getNro_piezas())
		 	));
		 	
		 	tableColumnStock.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getStock())
		 	));
		 	tableColumnPreCompra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getPrecioCompra())
		 	));
		 	
		 	
		 	tableColumnPreAlquiler.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getPrecioAlquiler())
		 	));
		 	
		 	tableColumnPreVenta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getPrecioVenta())
		 	));
		 	
	
		 	tableColumnReqPlanchar.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad,String> param)->new ReadOnlyStringWrapper(
		 			param.getValue().getValue().getRequierePlanchar()
		 	));
		 	
		 	tableColumnTipoMant.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			param.getValue().getValue().getTipoMantenimiento()
		 	));
		 	
		 	
		 	tableColumnLavanderiaCant.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getLavanderiaCant())
		 	));
		 	
		 	tableColumnReparacionCant.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getReparacionCant())
		 	));
		 	
		 	tableColumnPlanchadoCant.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getPlanchadoCant())
		 	));
		 	
		 	tableColumnEsperaCant.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		 			String.valueOf(param.getValue().getValue().getEsperaCant())
		 	));
		    
	}
	
	
	public void seleccionarArticulos(){
		DatoAtomico datoAtomico=comboBoxFamiliaArticulosPiezas.getSelectionModel().getSelectedItem();
		String talla=comboBoxTallaArticulosPiezas.getSelectionModel().getSelectedItem();
		String genero=comboBoxGeneroArticuloPieza.getSelectionModel().getSelectedItem();
		
		String buscar =(!textFieldBuscarArticulo.getText().trim().isEmpty())?"%"+textFieldBuscarArticulo.getText().trim()+"%":"%";
		String sqlFamilia="";
		if(datoAtomico!=null){
			if(datoAtomico.getId()!=0){
				sqlFamilia=" and IdFamilia="+datoAtomico.getId()+"";
			}
		}
		
		String sqlTalla=(talla!=null &&!talla.equals("Todos"))?" and Talla='"+talla+"' ":" ";
		String sqlGenero=(genero!=null && !genero.equals("Todos"))?" and Genero='"+genero+"' ":" ";
		dummyRoot=new  TreeItem<>();

		Connection conexionServidor=null;
		PreparedStatement pst=null;
		PreparedStatement pstPiezas=null;
		ResultSet rs=null;
		ResultSet rsPiezas=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionServidor = DriverManager.getConnection(Servidor.getServidor(), Servidor.USER, Servidor.PASS);
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Talla, Stock, IdFamilia,(select nombre from familia where id=IdFamilia) as Familia, Rentabilidad, PrecioCompra, PrecioAlquiler, PrecioVenta, Ubicacion, Genero, Ubicacion, NroPiezas, ProveedorId, LavanderiaCant, ReparacionCant, PlanchadoCant, EsperaCant  from articulo where (Codigo like ? or Descripcion like ? or Comentarios like ?) "+sqlFamilia+" "+sqlTalla+" "+sqlGenero);
			pst.setString(1, buscar);
			pst.setString(2, buscar);
			pst.setString(3, buscar);
			rs=pst.executeQuery();
			while(rs.next()){
				Unidad unidadRoot=new Unidad(Unidad.ARTICULO,rs.getInt("Id"), rs.getString("Codigo"),rs.getString("Descripcion"),
						  rs.getString("Talla"), rs.getString("Ubicacion"), rs.getString("Genero"), rs.getString("Familia"), rs.getInt("NroPiezas"), rs.getInt("Stock"), rs.getDouble("PrecioCompra"), rs.getDouble("PrecioAlquiler"), rs.getDouble("PrecioVenta"), null, null,
						  
						rs.getInt("LavanderiaCant"),rs.getInt("ReparacionCant"),rs.getInt("PlanchadoCant"),rs.getInt("EsperaCant")
						
					
						
						);
				unidadRoot.setIdArticulo(rs.getInt("Id"));
				unidadRoot.setNroPiezasArticulo(rs.getInt("NroPiezas"));  
				final TreeItem<Unidad> root = 
						  
						  new TreeItem<>();
				  root.setValue(unidadRoot);
				  
				  pstPiezas=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, (select nombre from tipo_mantenimiento where Id=Pieza.TMant) as mantenimiento, PrecioCompra, PrecioAlquiler, PrecioVenta, if(ReqPlanchar=1,'Si','No') as reqPlanchars , Comentarios, Estado, Stock, ArticuloId, LavanderiaCant, ReparacionCant, PlanchadoCant, EsperaCant  from pieza where ArticuloId=?;");
				  pstPiezas.setInt(1, rs.getInt("Id"));
				  rsPiezas=pstPiezas.executeQuery();
				  ObservableList<Unidad> unidades=FXCollections.observableArrayList();
				  
				  //List<Unidad> unidades = Arrays.<Unidad> asList();
				  while(rsPiezas.next()){
					  Unidad unidad=new Unidad(Unidad.PIEZA,rsPiezas.getInt("Id"),rsPiezas.getString("Codigo"),rsPiezas.getString("Descripcion"),
							  null,null,null, null, 0, rsPiezas.getInt("Stock"), rsPiezas.getDouble("PrecioCompra"),
							  rsPiezas.getDouble("PrecioAlquiler"), rsPiezas.getDouble("PrecioVenta"), rsPiezas.getString("reqPlanchars"), rsPiezas.getString("mantenimiento"), 
							  
								rs.getInt("LavanderiaCant"),rs.getInt("ReparacionCant"),rs.getInt("PlanchadoCant"),rs.getInt("EsperaCant")

							  
							  );
					  unidades.add(unidad);

				  }
				  rsPiezas.close();
				  pstPiezas.close();
				  unidades.stream().forEach((unidad) -> {
					  root.getChildren().add(new TreeItem<>(unidad));
				    });
				  dummyRoot.getChildren().add(root);

			}
		} catch (Exception e) {
			e.printStackTrace();
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al conectar!!!");
            error_alert.setHeaderText("No se pudo conectar con el servidor.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
			
		} finally{
			try {
				if (conexionServidor != null) {
					conexionServidor.close();
				}
				if(pst!=null){
					pst.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		treeTableViewArticulo.setRoot(dummyRoot);
	    treeTableViewArticulo.setShowRoot(false);
	    treeTableViewArticulo.refresh();
	}
	
	private boolean actualizarStockEn(int idArticulo, int stockEn){
		MArticulo mArticulo=new MArticulo();
		mArticulo.iniciarConexionServidor();
		boolean estado=false;
		mArticulo.actualizarStockEn(idArticulo, stockEn);
		mArticulo.cerrarConexionServidor();
		return estado;
	}
	public boolean actualizarStockEnPieza(int idPieza, int stockEn){
		MPieza mPieza=new MPieza();
		mPieza.iniciarConexionServidor();
		boolean estado=false;
		mPieza.actualizarStockEnPieza(idPieza, stockEn);
		mPieza.cerrarConexionServidor();
		return estado;
	}
	
	public void cargarComboBoxFamilia(){
		MDatoAtomico dDatoAtomico=new MDatoAtomico();
		dDatoAtomico.iniciarConexionServidor();
		DatoAtomico todos=new DatoAtomico();
		todos.setId(0);
		todos.setNombre("Todos");
		comboBoxFamiliaArticulosPiezas.getItems().add(todos);
		comboBoxFamiliaArticulosPiezas.getItems().addAll(dDatoAtomico.seleccionarDatosAtomicos(MDatoAtomico.QUERY_SELECT_FAMILIAS));
		dDatoAtomico.cerrarConexionServidor();
	}
	
	
}
