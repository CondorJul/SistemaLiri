package cargando;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Cargando {

	
	public static Stage cargando(Stage stagePadre){
		Stage stageCargando = new Stage(StageStyle.UNDECORATED);
		stageCargando.initStyle(StageStyle.TRANSPARENT);
		Scene scene=new Scene(new CargandoControlerAnchorPane());
		scene.setFill(Color.TRANSPARENT);
		stageCargando.setScene(scene);
		double x=stagePadre.getWidth()/2+(stagePadre).getX()-(350/2);
		
		double y=stagePadre.getHeight()/2+stagePadre.getY()-(350/2);
		stageCargando.setX(x);
		stageCargando.setY(y);
		//stageCargando.show();
		return stageCargando;
	}
	public static Stage cargando(){
		Stage stageCargando = new Stage(StageStyle.UNDECORATED);
		stageCargando.initStyle(StageStyle.TRANSPARENT);
		Scene scene=new Scene(new CargandoControlerAnchorPane());
		scene.setFill(Color.TRANSPARENT);
		stageCargando.setScene(scene);
		//stageCargando.show();
		stageCargando.centerOnScreen();
		return stageCargando;
	}
}
