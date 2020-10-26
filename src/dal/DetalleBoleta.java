package dal;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.alquiler.MDetalleBoleta;
import validacion.Validacion;


public class DetalleBoleta  extends DALPadre{
	private MDetalleBoleta mDetalleBoleta=new MDetalleBoleta();

	
	public static final int ARTICULO=1;
	public static final int PIEZA=2;
	public static final int OTRO=3;

	private int id=-1;
	
	
	private int idBoleta=-1;
	private int tipoVenta=-1;
	private int tipoPro=-1;
	
	
	private int idPieza=-1;
	private int idArticulo=-1;
	
	//private int cant=-1;
	private TextField cant =new TextField();
	private int cantEntero=0;

	
	
	private String codigo=null;
	private String descripcion=null;
	
	private String precioUnit=null;
	public JFXButton getJfxButtonEliminar() {
		return jfxButtonEliminar;
	}

	public void setJfxButtonEliminar(JFXButton jfxButtonEliminar) {
		this.jfxButtonEliminar = jfxButtonEliminar;
	}

	private String importe=null;

	

	
	
	
    //private SpinnerValueFactory<Integer> valueFactory =  null;
    


	private JFXButton jfxButtonEliminar=null;
	private FontAwesomeIconView font=new FontAwesomeIconView();
	public void cargarButtonAgregar(){
		
		font.setGlyphName("CLOSE");
		font.setSize("20px");
		jfxButtonEliminar=new JFXButton();
		jfxButtonEliminar.setGraphic(font);
		jfxButtonEliminar.setMinWidth(25);
		jfxButtonEliminar.setMinHeight(25);
	}
    
    public DetalleBoleta(){
    	this.cant.setTextFormatter(Validacion.intFormater());
    	this.cant.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(event.getCode()==KeyCode.ENTER){
					TextField textField=(TextField)event.getSource();
					mDetalleBoleta.iniciarConexionServidor();
					mDetalleBoleta.actualizarCantDetalleBoleta(id,Integer.parseInt(textField.getText()));
					DetalleBoleta dbci= mDetalleBoleta.seleccionarDetalleBoletaCantImporte(id);
					textField.setText(dbci.getCant().getText());
					importe=dbci.getImporte();
					//detalleBoleta.setImporte(dbci.getImporte());
					//tableViewArticulos1.ge
					mDetalleBoleta.cerrarConexionServidor();
					
					System.out.println(dbci.getImporte());
					System.out.println(dbci.getId());
					System.out.println(dbci.getCant().getText());
				}
				
			}
		});
    	
    	//valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
    	//cant.setValueFactory(valueFactory);
    }
    
    
    
	public int getIdBoleta() {
		return idBoleta;
	}



	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}



	public int getIdPieza() {
		return idPieza;
	}



	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}



	public int getIdArticulo() {
		return idArticulo;
	}



	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}



	public int getCantEntero() {
		return cantEntero;
	}

	public void setCantEntero(int cantEntero) {
		this.cantEntero = cantEntero;
	}

	public DetalleBoleta(int id, String codigo, int tipoPro, String descripcion, String precioUnit, String  importe,
			int cant, int stock) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.tipoPro = tipoPro;
		this.descripcion = descripcion;
		this.precioUnit = precioUnit;
		this.importe = importe;
		//valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1, stock, cant);
		this.cant.setText(String.valueOf(cant));;
		this.cantEntero=cant;
	}
	


	
	public int getTipoVenta() {
		return tipoVenta;
	}



	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getTipoPro() {
		return tipoPro;
	}
	public void setTipoPro(int tipo) {
		this.tipoPro = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrecioUnit() {
		return precioUnit;
	}
	public void setPrecioUnit(String precioUnit) {
		this.precioUnit = precioUnit;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public TextField getCant() {
		return cant;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleBoleta other = (DetalleBoleta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public DetalleBoleta(int id){
		super();
		this.id=id;
	}
	


    
    
}
