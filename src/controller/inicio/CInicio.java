package controller.inicio;

import java.net.URL;
import java.util.ResourceBundle;

import controller.CPadre;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.inicio.MInicio;

public class CInicio extends CPadre implements Initializable {
	
	@FXML private Label labelCantidadVenta1;
    @FXML private Label labelCantidadVenta2;
    @FXML private Label labelCantidadVenta3;
	@FXML private Label labelCantidadCliente1;
    @FXML private Label labelCantidadCliente2;
    @FXML private Label labelCantidadCliente3;
    @FXML private Label labelCantidadUsuario1;
    @FXML private Label labelCantidadUsuario2;
    @FXML private Label labelCantidadUsuario3;
    @FXML private Label labelCantidadAlquiler1;
    @FXML private Label labelCantidadAlquiler2;
    @FXML private Label labelCantidadAlquiler3;
    @FXML private Label labelCantidadArticulo1;
    @FXML private Label labelCantidadArticulo2;
    @FXML private Label labelCantidadArticulo3;
    @FXML private Label labelCantidadPieza1;
    @FXML private Label labelCantidadPieza2;
    @FXML private Label labelCantidadPieza3;
    @FXML private Label labelCantidadEgreso1;
    @FXML private Label labelCantidadEgreso2;
    @FXML private Label labelCantidadEgreso3;
    @FXML private Label labelCantidadProveedor1;
    @FXML private Label labelCantidadProveedor2;
    @FXML private Label labelCantidadProveedor3;
    @FXML private Label labelCantidadImportante1;
    @FXML private Label labelCantidadImportante2;
    @FXML private Label labelCantidadImportante3;
    
    MInicio mInicio =new  MInicio();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llamar();		
	}
	
	public void llamar(){
		new Thread(new Runnable() {
		    public void run() {
		        //try {
		            //while(true){
		            	mInicio.iniciarConexionServidor();
		            	
		            	labelCantidadAlquiler1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ALQUILER_HOY)));
		        		labelCantidadAlquiler2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ALQUILER_MES)));
		        		labelCantidadAlquiler3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ALQUILER_TOTAL)));
		            	
		            	labelCantidadVenta1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_VENTA_HOY)));
		        		labelCantidadVenta2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_VENTA_MES)));
		        		labelCantidadVenta3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_VENTA_TOTAL)));
		        			            	
		        		labelCantidadArticulo1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ARTICULO_HOY)));
		        		labelCantidadArticulo2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ARTICULO_MES)));
		        		labelCantidadArticulo3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_ARTICULO_TOTAL)));
		        		
		        		labelCantidadPieza1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PIEZA_HOY)));
		        		labelCantidadPieza2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PIEZA_MES)));
		        		labelCantidadPieza3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PIEZA_TOTAL)));
		        		
		        		labelCantidadCliente1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_CLIENTE_HOY)));
		        		labelCantidadCliente2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_CLIENTE_MES)));
		        		labelCantidadCliente3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_CLIENTE_TOTAL)));
		        		
		        		labelCantidadUsuario1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_USUARIO_HOY)));
		        		labelCantidadUsuario2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_USUARIO_MES)));
		        		labelCantidadUsuario3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_USUARIO_TOTAL)));
		        				        		
		        		labelCantidadEgreso1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_EGRESO_HOY)));
		        		labelCantidadEgreso2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_EGRESO_MES)));
		        		labelCantidadEgreso3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_EGRESO_TOTAL)));
		        		
		        		labelCantidadProveedor1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PROVEEDOR_HOY)));
		        		labelCantidadProveedor2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PROVEEDOR_MES)));
		        		labelCantidadProveedor3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_PROVEEDOR_TOTAL)));
		        		
		        		labelCantidadImportante1.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_IMPORTANTE_HOY)));
		        		labelCantidadImportante2.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_IMPORTANTE_MES)));
		        		labelCantidadImportante3.setText(String.valueOf(mInicio.numeroDB(MInicio.NUM_IMPORTANTE_TOTAL)));
		        		
		        		mInicio.cerrarConexionServidor();
		               //Thread.sleep(10000);
		            //}

		        /*} catch (InterruptedException e) {
		            e.printStackTrace();
		        }*/
		    }
		}).start();		
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

}
