package fi.laaperi.netcontroller.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sensor {
	
	@Id
	private long id;
	
	private String name;
	private float value;
	private float min;
	private float max;
	
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
	
	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}
	
	public void resetMin(){
		this.min = this.value;
	}
	
	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}
	
	public void resetMax(){
		this.max = this.value;
	}
	
	public void resetMinMax(){
		resetMin();
		resetMax();
	}
	
	public String toString(){
		return "{id=" + id + ", name=" + name + ", value=" + value + ", min=" + min + ", max=" + max + "}";
	}
}
