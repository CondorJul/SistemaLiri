package dal;

public class Cupon extends DALPadre{
	
	private int config_id = -1;
	private double config_cupon = -1.0;
	
	public int getConfig_id() {
		return config_id;
	}
	public void setConfig_id(int config_id) {
		this.config_id = config_id;
	}
	public double getConfig_cupon() {
		return config_cupon;
	}
	public void setConfig_cupon(double config_cupon) {
		this.config_cupon = config_cupon;
	}
	
	public Cupon(){
		
	}

}
