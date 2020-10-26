

package validacion;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Validacion {

	
    private static Pattern validDoubleText = Pattern.compile("-?((\\d*)|(\\d+\\.\\d*))");
    private static Pattern validIntText = Pattern.compile("(\\d*)");
    
    public static String validarNumero = "\\d";
    public static String validarLetras = "([a-zA-Z,])|(\\s)";
    public static String validarFecha = "";
    public static String validarPrecio = "([0-9.])";
    public static String validarSerie = "([0-9])|([BE0-9])|([EB0-9])|([F0-9])";
       
    public static TextFormatter<Double> doubleFormater(){
    	TextFormatter<Double> textFormatter = new TextFormatter<Double>(new DoubleStringConverter(), 0.00, 
                change -> {
                    String newText = change.getControlNewText() ;
                    if (validDoubleText.matcher(newText).matches()) {
                        return change ;
                    } else return null ;
                });
    	return textFormatter;
    }
    
    
    public static TextFormatter<Integer> intFormater(){
    	TextFormatter<Integer> textFormatter = new TextFormatter<Integer>(new IntegerStringConverter(), 0, 
                change -> {
                    String newText = change.getControlNewText() ;
                    if (validIntText.matcher(newText).matches()) {
                        return change ;
                    } else return null ;
                });
    	return textFormatter;
    }
        
    
    public static EventHandler<KeyEvent> validarNumero(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
            	
            	 TextField txt_TextField = (TextField) e.getSource();
                 if(txt_TextField.getText()==null){
                 	txt_TextField.setText("");
                 }
            	
                if (txt_TextField.getText().length() >= max_Lengh) {                    
                    e.consume();
                }
                if(e.getCharacter().matches(validarNumero)){ 
                    if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                        e.consume();
                    }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                        e.consume(); 
                    }
                }else{
                    e.consume();
                }
            }
        };
    }
    
   /* public static EventHandler<KeyEvent> validarLetras(){
		return new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent event) {
				if(event.getCharacter().matches(validarLetras)){
					
				}else{
					event.consume();
				}
			}
			
		};
    	
    }*/
    
    public static EventHandler<KeyEvent> validarFecha(){
    	return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
	                if(e.getCharacter().matches(validarFecha)){ 
	                    
	                }else{
	                    e.consume();
	                }
			}
		};
    }
    
    public static EventHandler<KeyEvent> validarPrecio(final Integer max){
    	return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				TextField tx = (TextField)e.getSource();
				if(e.getCharacter().matches(validarPrecio)){
					if(tx.getText().contains(".") && e.getCharacter().equals(".")){
						e.consume();
					}
					if(tx.getText().contains(".") && tx.getText().length()-tx.getText().indexOf(".")==max+1){
						e.consume();
					}else{
						//e.consume();
					}
				}else{
					e.consume();
				}
			}
			
    	};
    }
    
    public static EventHandler<KeyEvent> validarSerie(final Integer max){
    	return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max) {                    
                    e.consume();
                }
				if(e.getCharacter().matches(validarSerie)){ 
                    
                }else{
                    e.consume();
                }				
			}
		};
    }
    
    public static String doubleAStringMoneda(double decimal){
    	DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
    	simbolos.setDecimalSeparator('.');
		DecimalFormat dc = new DecimalFormat("#0.00", simbolos);
		return  dc.format(decimal);
	}
    
}
