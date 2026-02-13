package com.sabre.renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static int targetFPS;
	
	public enum Key
	{
	    A, B, C, D, E, F, G, H, I, J,
	    K, L, M, N, O, P, Q, R, S, T,
	    U, V, W, X, Y, Z,

	    DIGIT_0,
	    DIGIT_1,
	    DIGIT_2,
	    DIGIT_3,
	    DIGIT_4,
	    DIGIT_5,
	    DIGIT_6,
	    DIGIT_7,
	    DIGIT_8,
	    DIGIT_9,
	    
	    RIGHT_ARROW,
	    LEFT_ARROW,
	    UP_ARROW,
	    DOWN_ARROW,

	    LEFT_SHIFT,
	    RIGHT_SHIFT,
	    LEFT_CONTROL,
	    RIGHT_CONTROL,

	    SPACE
	}
	
	public enum MouseButton
	{
	    LEFT,
	    RIGHT,
	    MIDDLE
	}

	public static void createDisplay(String title, int width, int height, boolean resizable, int fps) {
		
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(resizable);
			Display.create(new PixelFormat(), attribs);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, width, height);
		
		targetFPS = fps;
		
	}
	
	public static void updateDisplay() {
		
		GL11.glViewport(0, 0, getWidth(), getHeight());
		Display.sync(targetFPS);
		Display.update();
		
	}
	
	public static void closeDisplay() {
		
		Display.destroy();
		
	}
	
	public static boolean isCloseRequested() {
		
		return Display.isCloseRequested();
		
	}
	
	public static boolean isKeyDown(Key key) {
		
		return Keyboard.isKeyDown(mapKey(key));
		
	}
	
	public static boolean isMouseButtonDown(MouseButton button) {
		
		return Mouse.isButtonDown(mapMouseButton(button));
		
	}
	
	public static int getWidth() {
		
		return Display.getWidth();
		
	}
	
	public static int getHeight() {
		
		return Display.getHeight();
		
	}
	
	private static int mapMouseButton(MouseButton button)
	{
	    switch (button)
	    {
	        case LEFT:   return 0;
	        case RIGHT:  return 1;
	        case MIDDLE: return 2;
	    }

	    throw new IllegalArgumentException("Unknown mouse button: " + button);
	}

	private static int mapKey(Key key)
	{
	    switch (key)
	    {
	        case A: return Keyboard.KEY_A;
	        case B: return Keyboard.KEY_B;
	        case C: return Keyboard.KEY_C;
	        case D: return Keyboard.KEY_D;
	        case E: return Keyboard.KEY_E;
	        case F: return Keyboard.KEY_F;
	        case G: return Keyboard.KEY_G;
	        case H: return Keyboard.KEY_H;
	        case I: return Keyboard.KEY_I;
	        case J: return Keyboard.KEY_J;
	        case K: return Keyboard.KEY_K;
	        case L: return Keyboard.KEY_L;
	        case M: return Keyboard.KEY_M;
	        case N: return Keyboard.KEY_N;
	        case O: return Keyboard.KEY_O;
	        case P: return Keyboard.KEY_P;
	        case Q: return Keyboard.KEY_Q;
	        case R: return Keyboard.KEY_R;
	        case S: return Keyboard.KEY_S;
	        case T: return Keyboard.KEY_T;
	        case U: return Keyboard.KEY_U;
	        case V: return Keyboard.KEY_V;
	        case W: return Keyboard.KEY_W;
	        case X: return Keyboard.KEY_X;
	        case Y: return Keyboard.KEY_Y;
	        case Z: return Keyboard.KEY_Z;
	        
	        case UP_ARROW: return Keyboard.KEY_UP;
	        case DOWN_ARROW: return Keyboard.KEY_DOWN;
	        case LEFT_ARROW: return Keyboard.KEY_LEFT;
	        case RIGHT_ARROW: return Keyboard.KEY_RIGHT;

	        case DIGIT_0: return Keyboard.KEY_0;
	        case DIGIT_1: return Keyboard.KEY_1;
	        case DIGIT_2: return Keyboard.KEY_2;
	        case DIGIT_3: return Keyboard.KEY_3;
	        case DIGIT_4: return Keyboard.KEY_4;
	        case DIGIT_5: return Keyboard.KEY_5;
	        case DIGIT_6: return Keyboard.KEY_6;
	        case DIGIT_7: return Keyboard.KEY_7;
	        case DIGIT_8: return Keyboard.KEY_8;
	        case DIGIT_9: return Keyboard.KEY_9;

	        case LEFT_SHIFT: return Keyboard.KEY_LSHIFT;
	        case RIGHT_SHIFT: return Keyboard.KEY_RSHIFT;
	        case LEFT_CONTROL: return Keyboard.KEY_LCONTROL;
	        case RIGHT_CONTROL: return Keyboard.KEY_RCONTROL;

	        case SPACE: return Keyboard.KEY_SPACE;
	    }

	    throw new IllegalArgumentException("Unknown key: " + key);
	}
	
}
