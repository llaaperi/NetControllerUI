package fi.laaperi.netcontroller.repository;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Relay {
	
	@Id
	private long id;
	
	private String name;
	private boolean state;	//true = open, false = closed
	
	public Relay(){}
	
	public Relay(long id, String name, boolean state){
		this.id = id;
		this.name = name;
		this.state = state;
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
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isOpen(){
		return state;
	}
	
	public String toString(){
		return "{id=" + id + ", name=" + name + ", state=" + state + "}";
	}
	
}
