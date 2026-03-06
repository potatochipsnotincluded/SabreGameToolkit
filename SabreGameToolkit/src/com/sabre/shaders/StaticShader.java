package com.sabre.shaders;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.sabre.entities.Camera;
import com.sabre.entities.Light;
import com.sabre.toolbox.Maths;

public class StaticShader extends ShaderProgramme {
	
	private static final String VERTEX_FILE = "src/com/sabre/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/com/sabre/shaders/fragmentShader.txt";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_metallic;
	private int location_smoothness;
	private int location_ambient;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}
	
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_metallic = super.getUniformLocation("metallic");
		location_smoothness = super.getUniformLocation("smoothness");
		location_ambient = super.getUniformLocation("ambient");
	}
	
	public void loadMaterialVariables(float metallic, float smoothness) {
		super.loadFloat(location_metallic, metallic);
		super.loadFloat(location_smoothness, smoothness);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Maths.createViewMatrix(camera);
		
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
	
	public void loadLight(Light light) {
		super.loadVector(location_lightPosition, light.getPosition());
		super.loadVector(location_lightColour, light.getColour());
		super.loadFloat(location_ambient, light.getAmbient());
	}
	
}
