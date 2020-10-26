package ctreetablewiew;

import com.jfoenix.controls.JFXButton;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Paint;
import opciones.COpcion;

//EmploymentUnit<T extends EmploymentUnit<?>>
public class Unidad{
	public final static int ARTICULO=1;
	public final static int PIEZA=2;
	public final static int OTRO=3;
	private int tipoClase=-1;
	private String cod=null;
	
	private int idArticulo=-1;
	private int nroPiezasArticulo=-1;
	
	public int getNroPiezasArticulo() {
		return nroPiezasArticulo;
	}

	public void setNroPiezasArticulo(int nroPiezasArticulo) {
		this.nroPiezasArticulo = nroPiezasArticulo;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	private SimpleIntegerProperty id;
	private SimpleStringProperty codigo;
	private SimpleStringProperty descripcion;
	private SimpleStringProperty talla;
	private SimpleStringProperty ubicacion;
	private SimpleStringProperty genero;
	private SimpleStringProperty familia;
	private SimpleIntegerProperty nro_piezas;
	private SimpleIntegerProperty stock;
	private SimpleDoubleProperty precioCompra;
	private SimpleDoubleProperty precioAlquiler;
	private SimpleDoubleProperty precioVenta;
	
	
	private SimpleStringProperty requierePlanchar;
	private SimpleStringProperty tipoMantenimiento;
	
	private SimpleIntegerProperty lavanderiaCant;
	private SimpleIntegerProperty reparacionCant;
	private SimpleIntegerProperty planchadoCant;
	private SimpleIntegerProperty esperaCant;
	
	
	private COpcion opciones= new COpcion();
	
	private JFXButton jfxButtonAgregar=null;
	private FontAwesomeIconView font=new FontAwesomeIconView();
	public void cargarButtonAgregar(int tam){
		
		font.setGlyphName("PLUS");
		font.setSize(tam+"px");
		jfxButtonAgregar=new JFXButton();
		jfxButtonAgregar.setGraphic(font);
		jfxButtonAgregar.setMinWidth(tam+3);
		jfxButtonAgregar.setMinHeight(tam+3);
	}
	public JFXButton getJfxButtonAgregar() {
		return jfxButtonAgregar;
	}

	public void setJfxButtonAgregar(JFXButton jfxButtonAgregar) {
		this.jfxButtonAgregar = jfxButtonAgregar;
	}

	public COpcion getOpciones() {
		return opciones;
	}

	public void setOpciones(COpcion opciones) {
		this.opciones = opciones;
	}

	public Unidad(int tipoClase,int id, String codigo, String descripcion, 
			String talla, String ubicacion, String genero, String familia,
			int nro_piezas, int stock, double precioCompra,
			double precioAlquiler, double precioVenta, 
			String requierePlanchar, String tipoMantenimiento, 
			
			int lavanderiaCant,
			int reparacionCant,
			int planchadoCant,
			int esperaCant
			
			) {
	  this.tipoClase=tipoClase;
	  this.id=new SimpleIntegerProperty(id);
      this.codigo = new SimpleStringProperty(codigo);
      this.cod=codigo;
      this.descripcion = new SimpleStringProperty(descripcion);
      this.talla=new SimpleStringProperty(talla);
      this.ubicacion=new SimpleStringProperty(ubicacion);
      this.genero=new SimpleStringProperty(genero);
      this.familia=new SimpleStringProperty(familia);
      this.nro_piezas=new SimpleIntegerProperty(nro_piezas);
      this.stock=new SimpleIntegerProperty(stock);
      this.precioCompra=new SimpleDoubleProperty(precioCompra);
      this.precioAlquiler=new SimpleDoubleProperty(precioAlquiler);
      this.precioVenta=new SimpleDoubleProperty(precioVenta);
      this.requierePlanchar=new SimpleStringProperty(requierePlanchar);
      this.tipoMantenimiento=new SimpleStringProperty(tipoMantenimiento);
      this.lavanderiaCant=new SimpleIntegerProperty(lavanderiaCant);
      this.reparacionCant=new SimpleIntegerProperty(reparacionCant);
      this.planchadoCant=new SimpleIntegerProperty(planchadoCant);
      this.esperaCant=new SimpleIntegerProperty(esperaCant);
      
    }
	
	public SimpleIntegerProperty idProperty(){
		if(id==null){
			id=new SimpleIntegerProperty(this,"id");
		}
		return id;
	}
	
	public SimpleStringProperty codigoProperty(){
		if(codigo==null){
			codigo=new SimpleStringProperty(this,"codigo");
		}
		return codigo;
	}
	
	public SimpleStringProperty descripcionProperty(){
		if(descripcion==null){
			descripcion=new SimpleStringProperty(this,"descripcion");
		}
		return descripcion;
	}
	
	public SimpleStringProperty tallaProperty(){
		if(talla==null){
			talla=new SimpleStringProperty(this,"talla");
		}
		return talla;
	}
	
	public SimpleStringProperty ubicacionProperty(){
		if(ubicacion==null){
			ubicacion=new SimpleStringProperty(this,"ubicacion");
		}
		return ubicacion;
	}	
	
	
	public SimpleStringProperty generoProperty(){
		if(genero==null){
			genero=new SimpleStringProperty(this,"genero");
		}
		return genero;
	}
	
	public SimpleStringProperty familiaProperty(){
		if(familia==null){
			familia=new SimpleStringProperty(this,"familia");
		}
		return familia;
	}
	
	public SimpleIntegerProperty nro_piezasProperty(){
		if(nro_piezas==null){
			nro_piezas=new SimpleIntegerProperty();
		}
		return nro_piezas;
	}
	
	public SimpleIntegerProperty stockProperty(){
		if(stock==null){
			stock=new SimpleIntegerProperty(this,"stock");
		}
		return stock;
	}
	
	public SimpleDoubleProperty precioCompraProperty(){
		if(precioCompra==null){
			precioCompra=new SimpleDoubleProperty(this,"precioCompra");
		}
		return precioCompra;
	}
	
	public SimpleDoubleProperty precioAlquilerProperty(){
		if(precioAlquiler==null){
			precioAlquiler=new SimpleDoubleProperty(this,"precioAlquiler");
		}
		return precioAlquiler;
	}
	
	
	public SimpleDoubleProperty precioVentaProperty(){
		if(precioVenta==null){
			precioVenta=new SimpleDoubleProperty(this,"precioVenta");
		}
		return precioVenta;
	}
	
	
	public SimpleStringProperty requierePlancharProperty(){
		if(requierePlanchar==null){
			requierePlanchar=new SimpleStringProperty(this,"requierePlanchar");
		}
		return requierePlanchar;
	}
	
	public SimpleStringProperty tipoMantenimientoProperty(){
		if(tipoMantenimiento==null){
			tipoMantenimiento=new SimpleStringProperty(this,"tipoMantenimiento");
		}
		return tipoMantenimiento;
	}
	
	
	
	public SimpleIntegerProperty lavanderiaCantProperty(){
		if(lavanderiaCant==null){
			lavanderiaCant=new SimpleIntegerProperty(this,"lavanderiaCant");
		}
		return lavanderiaCant;
	}
	
	public SimpleIntegerProperty reparacionCantProperty(){
		if(reparacionCant==null){
			reparacionCant=new SimpleIntegerProperty(this,"reparacionCant");
		}
		return reparacionCant;
	}
	
	public SimpleIntegerProperty planchadoCantProperty(){
		if(planchadoCant==null){
			planchadoCant=new SimpleIntegerProperty(this,"planchadoCant");
		}
		return planchadoCant;
	}
	public SimpleIntegerProperty esperaCantProperty(){
		if(esperaCant==null){
			esperaCant=new SimpleIntegerProperty(this,"esperaCant");
		}
		return esperaCant;
	}
	
	public int getTipoClase() {
	      return this.tipoClase;
	}
	
	public int getId() {
	      return id.get();
	}

	public String getCodigo(){
		return codigo.get();
	}

	
	public String getDescripcion() {
	      return descripcion.get();
	}
	
	public String getTalla(){
		return talla.get();
	}
	
	public String getUbicacion() {
		return ubicacion.get();
	}

	

	public String getGenero(){
		return genero.get();
	}
	
	public String getFamilia(){
		return familia.get();
	}
	
	public int getNro_piezas(){
		return nro_piezas.get();
	}
	
	public int getStock(){
		return stock.get();
	}
	
	public Double getPrecioCompra(){
		return precioCompra.get();
	}
	
	public Double getPrecioAlquiler(){
		return precioAlquiler.get();
	}
	
	public Double getPrecioVenta(){
		return precioVenta.get();
	}
	
	public String getRequierePlanchar(){
		return requierePlanchar.get();
	}
	
	public String getTipoMantenimiento(){
		return tipoMantenimiento.get();
	}
	
	public int getLavanderiaCant() {
	      return lavanderiaCant.get();
	}
	
	public int getReparacionCant() {
	      return reparacionCant.get();
	}
	
	public int getPlanchadoCant() {
	      return planchadoCant.get();
	}
	
	public int getEsperaCant() {
	      return esperaCant.get();
	}
	
	
	
	
	public void setId(int id){
		this.id.set(id);
	}

	public void setCodigo(String codigo){
		this.codigo.set(codigo);
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion.set(descripcion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cod == null) ? 0 : cod.hashCode());
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
		Unidad other = (Unidad) obj;
		if (cod == null) {
			if (other.cod != null)
				return false;
		} else if (!cod.equals(other.cod))
			return false;
		return true;
	}
	
	
	
	
	
}
