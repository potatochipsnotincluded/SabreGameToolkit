package com.sabre.tester;

import org.lwjgl.util.vector.Vector3f;

import com.sabre.entities.Camera;
import com.sabre.entities.Entity;
import com.sabre.entities.Light;
import com.sabre.models.RawModel;
import com.sabre.models.TexturedModel;
import com.sabre.renderEngine.DisplayManager;
import com.sabre.renderEngine.DisplayManager.Key;
import com.sabre.renderEngine.Loader;
import com.sabre.renderEngine.OBJLoader;
import com.sabre.renderEngine.Renderer;
import com.sabre.shaders.StaticShader;
import com.sabre.textures.ModelTexture;
import com.sabre.toolbox.Maths;

public class Application {
	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay("Sabre Game Toolkit Test Window", 1260, 720, true, 120);
		
		Loader loader = new Loader();
		StaticShader staticShader = new StaticShader();
		Renderer renderer = new Renderer(staticShader, 70, 0.1f, 1000.0f); 
		
		RawModel model = OBJLoader.loadOBJModel("res/monkey.obj").toRawModel(loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("res/gold.png"));
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		TexturedModel staticModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(staticModel, new Vector3f(0, 0,-5f), new Vector3f(0,0,0), new Vector3f(1,1,1));
		
		Light light = new Light(new Vector3f(0,10,0), new Vector3f(1,1,1), 0.1f);
		
		Camera camera = new Camera(new Vector3f(0,0,0), new Vector3f(0,0,0));
		
		while (!DisplayManager.isCloseRequested()) {
			
			//game logic			
			if (DisplayManager.isKeyDown(Key.D)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(0.1f, 0.0f, 0.0f)));
			}
			if (DisplayManager.isKeyDown(Key.A)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(-0.1f, 0.0f, 0.0f)));
			}
			if (DisplayManager.isKeyDown(Key.W)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(0.0f, 0.0f, -0.1f)));
			}
			if (DisplayManager.isKeyDown(Key.S)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(0.0f, 0.0f, 0.1f)));
			}
			if (DisplayManager.isKeyDown(Key.Q)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(0.0f, 0.1f, 0.0f)));
			}
			if (DisplayManager.isKeyDown(Key.E)) {
				camera.setPosition(Maths.addVectors(camera.getPosition(), new Vector3f(0.0f, -0.1f, 0.0f)));
			}
			
			//render
			renderer.prepare(0.05f, 0.15f, 0.2f);
			staticShader.start();
			staticShader.loadLight(light);
			staticShader.loadViewMatrix(camera);
			
			renderer.render(entity, staticShader);
			
			staticShader.stop();
			
			DisplayManager.updateDisplay();
			
		}
		
		loader.cleanUp();
		
		DisplayManager.closeDisplay();
		
	}
	
}
