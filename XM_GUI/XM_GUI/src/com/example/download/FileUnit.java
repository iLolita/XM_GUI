package com.example.download;

public class FileUnit {
	private String name=null;
	private String type=null;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString(){
		return this.name+"----"+type;
	}
	
}
