package controller.alquiler;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import controller.CPadre;
import controller.busqueda.CBusqueda;
import ctreetablewiew.Unidad;
import dal.AlquilerBArticulos;
import dal.Boleta;
import dal.Busqueda;
import dal.Cliente;
import dal.DetalleBoleta;
import dal.Pruebaa;
import funciones.Servidor;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import model.MPadre;
import model.alquiler.MBoleta;
import model.alquiler.MDetalleBoleta;
import model.busqueda.MBusqueda;
import model.cliente.MCliente;


	


public class CAlquiler2 extends CPadre implements Initializable{

	private MBoleta mBoleta=new MBoleta();
	private Boleta boleta=new Boleta();
	private Cliente cliente=new Cliente();
	
	private MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();
	private DetalleBoleta detalleBoleta=new DetalleBoleta();
	
    @FXML
    private TextField textFieldCodigoCliente;

    @FXML
    private Button buttonBuscarCliente;

    @FXML
    private Button buttonEliminarCliente;

    @FXML
    private JFXTextField jFXTextFieldCodigo;

    @FXML
    private JFXTextField jFXTextFieldApellidosNombres;

    @FXML
    private JFXTextField jFXTextFieldDireccion;

    @FXML
    private JFXTextField jFXTextFieldReferencia;

    @FXML
    private JFXTextField jFXTextFieldDNI;

    @FXML
    private JFXTextField jFXTextFieldCorreo;

    @FXML
    private JFXTextField jFXTextFieldTelefono;

    @FXML
    private TableView<Pruebaa> tableViewArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnCodigoArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnCantArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnDescripcionArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnPrecioArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnImporteArticulos;

    @FXML
    private TableColumn<Pruebaa, String> tableColumnEliminarArticulos;

    @FXML
    private TextField textFieldSubTotal;

    @FXML
    private TextField textFieldDesCupones;

    @FXML
    private TextField textFieldDesAdicional;

    @FXML
    private TextField textFieldTotalPagar;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon1;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon2;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon3;

    @FXML
    private JFXRadioButton jFXRadioButtonDesNinguno;

    @FXML
    private ToggleGroup groupDesAdic;

    @FXML
    private JFXRadioButton jFXRadioButtonDesSoles;

    @FXML
    private JFXRadioButton jFXRadioButtonDesPor;

    @FXML
    private Label labelDescuentoEn;

    @FXML
    private Spinner<?> spinnerDesMonPor;

    @FXML
    private TableView<?> tableViewPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnSeleccionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnNroPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnCantPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnImportePiezas;

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
    private JFXCheckBox jFXTextFieldOtroGarantia;

    @FXML
    private TextArea textAreaOtroGarantia;

    @FXML
    private Button buttonEnlazarBoleta;

    @FXML
    private Button buttonEliminarEnlazarBoleta;

    @FXML
    private TextField textFieldNroBoletaGarantia;

    @FXML
    private JFXCheckBox jFXTextFieldBoletaAlqGarantia;

    @FXML
    private TextField textFieldSerieBoletaGarantia;

    @FXML
    private BorderPane boderPaneBodyRecomendacion;

    @FXML
    private TextField textFieldCodigoClienteRecome;

    @FXML
    private Button buttonBuscarClienteRecome;

    @FXML
    private Button buttonEliminarClienteRecomen;

    @FXML
    private BorderPane boderPaneBodyDevolucion;

    @FXML
    private DatePicker datePickerFechaEntrega;

    @FXML
    private DatePicker datePickerFechaDevolucion;

    @FXML
    private JFXButton jFXButtonReservar;

    @FXML
    private JFXButton jFXButtonEfectuarOperacion;

    @FXML 
    private MenuItem menuItemNuevoAlquiler;
    
    @FXML 
    private MenuItem menuItemNuevoVenta;

    @FXML
    private TableColumn<?, ?> tableColumnNro;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionArticulo;

    @FXML
    private TableColumn<?, ?> tableColumnTallla;

    @FXML
    private TableColumn<?, ?> tableColumnGenero;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioCompra;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioAlquiler;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioVenta;

    

    @FXML
    private TableColumn<?, ?> tableColumnAgregar;

    @FXML
    private TextField textFieldBuscarProductos;

    @FXML
    private TextField textFieldBuscarAlquiler;
    
    
    @FXML
    private TreeTableView<Unidad> treeTableViewArticulos;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnCodigo;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnDescripcion;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnTalla;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnGenero;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPCompra;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPAlquiler;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnPVenta;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnStock;

    @FXML
    private TreeTableColumn<Unidad, String> treeTableColumnAgregar;
    
    private TreeItem<Unidad> dummyRoot;
 /*   @FXML
    private TreeTableView<?> treeTableViewAlquiler;

    @FXML
    private TreeTableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TreeTableColumn<?, ?> tableColumnNombre;

    @FXML
    private TreeTableColumn<?, ?> tableColumnFamilia;
    
    @FXML
    private TreeTableColumn<?, ?> tableColumnStock;*/

	private ObservableList<Pruebaa> arrayDetalleBoleta=FXCollections.observableArrayList(
			new Pruebaa("campo1", "campo2", "campo3", "campo4", "campo5", "campo6", "campo7", "campo8"));
	
	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		menuItemNuevoAlquiler.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				
			}
		});
		
		buttonBuscarCliente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				String url = "/view/busqueda/Busqueda.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, MBusqueda.CLIENTE, CPadre.NULO);
				CBusqueda cBusqueda = (CBusqueda)cpadre;
				if(cBusqueda!=null){
					String dni=((Busqueda)cBusqueda.getObjeto()).getCampo2();
					textFieldCodigoCliente.setText(dni);
					extraerDatosDeBusqueda(dni);					
				}
			}
		});
		
		textFieldBuscarProductos.setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				seleccionarArticulos(textFieldBuscarProductos.getText());
			}
		});
		
		inicializarCamposTableDetalleBoleta();
		tableViewArticulos.setItems(arrayDetalleBoleta);
		/*	inicializarCamposTreeTableArticulos();
		seleccionarArticulos("");
		if(!cargarBoletaPendiente()){
			agregarBoleta();
		}*/
		
		
		
	//	tableViewArticulos.getItems().addAll( );
		tableViewArticulos.refresh();
	}
	
	private boolean cargarBoletaPendiente(){
		mBoleta.iniciarConexionServidor();
		Boleta boleta=mBoleta.selecccionarBoletaPendiente();
		if(boleta!=null){
			this.boleta=boleta;
			return true;
		}
		return false;
	}
	
	
	private void habilitarCamposVenta(){
		
		
	}
	
	private void habilitaComposAlquiler(){
		
	}
	
	private void extraerDatosBoletaPendiente(){
		
	}
	
	public void extraerDatosDeBusqueda(String codDni){
		 MCliente mCliente=new MCliente();
		 mCliente.iniciarConexionServidor();
		 cliente=mCliente.seleccionarCliente(codDni);
		 mCliente.cerrarConexionServidor();
		 boleta.setIdCliente(cliente.getCli_id());
		 jFXTextFieldCodigo.setText(cliente.getCli_codigo());
		 jFXTextFieldDNI.setText(cliente.getCli_dni());
		 jFXTextFieldApellidosNombres.setText(cliente.getCli_apellNom());
		 jFXTextFieldDireccion.setText(cliente.getCli_direccion());
		 jFXTextFieldReferencia.setText(cliente.getCli_referencia());
		 jFXTextFieldCorreo.setText(cliente.getCli_email());
		 jFXTextFieldTelefono.setText(cliente.getCli_telefono());
	}

	public void inicializarCamposTreeTableArticulos(){
	 	treeTableColumnCodigo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad,String> param)-> new ReadOnlyStringWrapper(
			 param.getValue().getValue().getCodigo()
	 	));
	 	
	 	treeTableColumnDescripcion.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	    	param.getValue().getValue().getDescripcion()
	    ));
	 	
	 	treeTableColumnTalla.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
		    	param.getValue().getValue().getTalla()
		    	
		));
	 	
	 	treeTableColumnGenero.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			param.getValue().getValue().getGenero()
	 	));
	 	
	 
	 	
	 	
	 	treeTableColumnStock.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getStock())
	 	));
	 	
	 	treeTableColumnPCompra.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioCompra())
	 	));
	 	
	 	
	 	treeTableColumnPAlquiler.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioAlquiler())
	 	));
	 	
	 	treeTableColumnPVenta.setCellValueFactory((TreeTableColumn.CellDataFeatures<Unidad, String> param)->new ReadOnlyStringWrapper(
	 			String.valueOf(param.getValue().getValue().getPrecioVenta())
	 	));
	 	
	 	treeTableColumnAgregar.setCellValueFactory(new TreeItemPropertyValueFactory<Unidad, String>("jfxButtonAgregar"));
	    
	}
	
	

	public void seleccionarArticulos(String strBuscar){
		strBuscar=(strBuscar!=null && !strBuscar.trim().isEmpty()?"%"+strBuscar+"%":"%");

		dummyRoot=new  TreeItem<>();

		Connection conexionServidor=null;
		PreparedStatement pst=null;
		PreparedStatement pstPiezas=null;
		ResultSet rs=null;
		ResultSet rsPiezas=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexionServidor = DriverManager.getConnection(Servidor.getServidor(), Servidor.USER, Servidor.PASS);
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Talla, Stock, IdFamilia,(select nombre from familia where id=IdFamilia) as Familia, Rentabilidad, PrecioCompra, PrecioAlquiler, PrecioVenta, Genero, Ubicacion, NroPiezas, ProveedorId from articulo where Codigo like ? OR Descripcion like ?;");
			pst.setString(1, strBuscar);
			pst.setString(2, strBuscar);
			rs=pst.executeQuery();
			while(rs.next()){
				Unidad unidadRoot=new Unidad(Unidad.ARTICULO,rs.getInt("Id"),rs.getString("Codigo"),rs.getString("Descripcion"),
						  rs.getString("Talla"), rs.getString("Ubicacion"),rs.getString("Genero"), rs.getString("Familia"), rs.getInt("NroPiezas"), rs.getInt("Stock"), rs.getDouble("PrecioCompra"), rs.getDouble("PrecioAlquiler"), rs.getDouble("PrecioVenta"), null, null, 
						  
						  0, 0, 0, 0

						);
				unidadRoot.cargarButtonAgregar(21);
				unidadRoot.setIdArticulo(rs.getInt("Id"));
				unidadRoot.setNroPiezasArticulo(rs.getInt("NroPiezas"));  
				unidadRoot.getJfxButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						DetalleBoleta detalleBoleta=agregarDetalleBoleta(unidadRoot);
						System.out.println(detalleBoleta.getId());
						if(detalleBoleta!=null){
							System.out.println(detalleBoleta.getId());
							System.out.println(detalleBoleta.getImporte());
							//tableViewArticulos.getItems().add
							//tableViewArticulos.getItems().add(detalleBoleta);
							tableViewArticulos.refresh();
						}
					}
				});;
				final TreeItem<Unidad> root = 
						  
						  new TreeItem<>();
				  root.setValue(unidadRoot);
				  
				  pstPiezas=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, (select nombre from tipo_mantenimiento where Id=Pieza.TMant) as mantenimiento, PrecioCompra, PrecioAlquiler, PrecioVenta, if(ReqPlanchar=1,'Si','No') as reqPlanchars , Comentarios, Estado, Stock, ArticuloId from pieza where ArticuloId=?;");
				  pstPiezas.setInt(1, rs.getInt("Id"));
				  rsPiezas=pstPiezas.executeQuery();
				  ObservableList<Unidad> unidades=FXCollections.observableArrayList();
				  
				  //List<Unidad> unidades = Arrays.<Unidad> asList();
				  while(rsPiezas.next()){
					  Unidad unidad=new Unidad(Unidad.PIEZA,rsPiezas.getInt("Id"),rsPiezas.getString("Codigo"),rsPiezas.getString("Descripcion"),
							  null,null,null, null, 0, rsPiezas.getInt("Stock"), rsPiezas.getDouble("PrecioCompra"),
							  rsPiezas.getDouble("PrecioAlquiler"), rsPiezas.getDouble("PrecioVenta"), rsPiezas.getString("reqPlanchars"), rsPiezas.getString("mantenimiento"), 
							  
							  0, 0, 0, 0

							  );
					  unidad.cargarButtonAgregar(19);
					  unidad.getJfxButtonAgregar().setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							DetalleBoleta detalleBoleta=agregarDetalleBoleta(unidad);
							if(detalleBoleta!=null){
							//	tableViewArticulos.getItems().add(detalleBoleta);
							}
						}
					});
					 
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
		
		treeTableViewArticulos.setRoot(dummyRoot);
	    treeTableViewArticulos.setShowRoot(false);
	    treeTableViewArticulos.refresh();
	}
	
	
	
	
	private void inicializarCamposTableDetalleBoleta(){
		/*tableColumnCantArticulos.setCellValueFactory(new PropertyValueFactory<>("cant"));
		tableColumnCodigoArticulos.setCellValueFactory(new PropertyValueFactory<>("codigo"));	
		tableColumnDescripcionArticulos.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableColumnPrecioArticulos.setCellValueFactory(new PropertyValueFactory<>("precioUnit"));
		tableColumnImporteArticulos.setCellValueFactory(new PropertyValueFactory<>("importe"));
		tableColumnEliminarArticulos.setCellValueFactory(new PropertyValueFactory<>("jfxButtonEliminar"));
*/
		System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		tableColumnCantArticulos.setCellValueFactory(new PropertyValueFactory<>("campo1"));
		tableColumnCodigoArticulos.setCellValueFactory(new PropertyValueFactory<>("campo2"));	
		tableColumnDescripcionArticulos.setCellValueFactory(new PropertyValueFactory<>("campo3"));
		tableColumnPrecioArticulos.setCellValueFactory(new PropertyValueFactory<>("campo4"));
		tableColumnImporteArticulos.setCellValueFactory(new PropertyValueFactory<>("campo5"));
		tableColumnEliminarArticulos.setCellValueFactory(new PropertyValueFactory<>("campo7"));

	}
	
	
	private void agregarBoleta(){
		mBoleta.iniciarConexionServidor();
		int idBoleta=mBoleta.agregarBoleta(boleta);
		mBoleta.cerrarConexionServidor();
		if(mBoleta.getNoError()==MPadre.CORRECTO){
			boleta.setId(idBoleta);
		}
		
	}
	
	private DetalleBoleta agregarDetalleBoleta(Unidad unidad){
		DetalleBoleta detalleBoleta=new DetalleBoleta();
		detalleBoleta.setIdBoleta(boleta.getId());
		detalleBoleta.setTipoVenta(boleta.getTipo());
		detalleBoleta.setTipoPro(unidad.getTipoClase());
		if(unidad.getTipoClase()==Unidad.ARTICULO){
			detalleBoleta.setIdArticulo(unidad.getId());
		}if(unidad.getTipoClase()==Unidad.PIEZA){
			detalleBoleta.setIdPieza(unidad.getId());
		}
		//detalleBoleta.getCant().getValueFactory().setValue(1);
		
		mDetalleBoleta.iniciarConexionServidor();
		
		int idUltimo=mDetalleBoleta.agregarDetalleBoleta(detalleBoleta);
		System.out.println(idUltimo);
		
		if(mDetalleBoleta.getNoError()==MPadre.CORRECTO){
			detalleBoleta=mDetalleBoleta.seleccionarDetalleBoleta(idUltimo);
		}else{

			detalleBoleta=null;
		}
		mDetalleBoleta.cerrarConexionServidor();
		//tableViewArticulos.getItems().add(detalleBoleta);
		return detalleBoleta;
	}
	
	private void actualizarBoleta(){
		
	}
	
	private void obtenerProductoBaseDatos (int tipo, String codigo){
		 
	}	
}
	


