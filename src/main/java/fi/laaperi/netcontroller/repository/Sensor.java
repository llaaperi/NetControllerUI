package fi.laaperi.netcontroller.repository;

public class Sensor {
	
	private long id;
	private String name;
	private float value;
	
	public Sensor(){}
	
	public Sensor(long id, String name, float value){
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String toString(){
		return "{id=" + id + ", name=" + name + ", value=" + value + "}";
	}
}
