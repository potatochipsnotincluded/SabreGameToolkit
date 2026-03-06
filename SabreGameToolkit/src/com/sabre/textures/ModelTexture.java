package com.sabre.textures;

public class ModelTexture {
	
	private int textureID;
	
	private float metallic = 1;
	private float smoothness = 0;
	
	public ModelTexture(int id) {
		this.textureID = id;
	}
	
	public int getID() {
		return this.textureID;
	}

	public float getMetallic() {
		return metallic;
	}

	public void setMetallic(float metallic) {
		this.metallic = metallic;
	}

	public float getSmoothness() {
		return smoothness;
	}

	public void setSmoothness(float smoothness) {
		this.smoothness = smoothness;
	}
	
}
