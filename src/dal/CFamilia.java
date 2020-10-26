package dal;

public class CFamilia extends DALPadre{
 private int id=-1;
 private String nombre=null;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}

@Override
public String toString() {
	return nombre;
}


 


 
 
}
