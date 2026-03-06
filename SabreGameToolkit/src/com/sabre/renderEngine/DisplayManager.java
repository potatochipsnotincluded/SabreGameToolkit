package com.sabre.renderEngine;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class DisplayManager {

    private static long window;
    private static int targetFPS;

    public enum Key {
        A, B, C, D, E, F, G, H, I, J,
        K, L, M, N, O, P, Q, R, S, T,
        U, V, W, X, Y, Z,

        DIGIT_0, DIGIT_1, DIGIT_2, DIGIT_3, DIGIT_4,
        DIGIT_5, DIGIT_6, DIGIT_7, DIGIT_8, DIGIT_9,

        RIGHT_ARROW, LEFT_ARROW, UP_ARROW, DOWN_ARROW,

        LEFT_SHIFT, RIGHT_SHIFT, LEFT_CONTROL, RIGHT_CONTROL,

        SPACE
    }

    public enum MouseButton {
        LEFT, RIGHT, MIDDLE
    }

    public static void createDisplay(String title, int width, int height, boolean resizable, int fps) {
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0) {
            throw new RuntimeException("Failed to create GLFW window");
        }

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1); // vsync (optional)
        glfwShowWindow(window);

        glViewport(0, 0, width, height);
        targetFPS = fps;
    }

    public static void updateDisplay() {
        glfwPollEvents();

        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            glfwGetFramebufferSize(window, width, height);
            glViewport(0, 0, width.get(0), height.get(0));
        }

        glfwSwapBuffers(window);

        // Manual FPS sync (approx)
        if (targetFPS > 0) {
            try {
                Thread.sleep(1000 / targetFPS);
            } catch (InterruptedException ignored) {}
        }
    }

    public static void closeDisplay() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static boolean isCloseRequested() {
        return glfwWindowShouldClose(window);
    }

    public static boolean isKeyDown(Key key) {
        return glfwGetKey(window, mapKey(key)) == GLFW_PRESS;
    }

    public static boolean isMouseButtonDown(MouseButton button) {
        return glfwGetMouseButton(window, mapMouseButton(button)) == GLFW_PRESS;
    }

    public static int getWidth() {
        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            glfwGetFramebufferSize(window, width, height);
            return width.get(0);
        }
    }

    public static int getHeight() {
        try (MemoryStack stack = stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            glfwGetFramebufferSize(window, width, height);
            return height.get(0);
        }
    }

    private static int mapMouseButton(MouseButton button) {
        switch (button) {
            case LEFT: return GLFW_MOUSE_BUTTON_1;
            case RIGHT: return GLFW_MOUSE_BUTTON_2;
            case MIDDLE: return GLFW_MOUSE_BUTTON_3;
        }
        throw new IllegalArgumentException("Unknown mouse button: " + button);
    }

    private static int mapKey(Key key) {
        switch (key) {
            case A: return GLFW_KEY_A;
            case B: return GLFW_KEY_B;
            case C: return GLFW_KEY_C;
            case D: return GLFW_KEY_D;
            case E: return GLFW_KEY_E;
            case F: return GLFW_KEY_F;
            case G: return GLFW_KEY_G;
            case H: return GLFW_KEY_H;
            case I: return GLFW_KEY_I;
            case J: return GLFW_KEY_J;
            case K: return GLFW_KEY_K;
            case L: return GLFW_KEY_L;
            case M: return GLFW_KEY_M;
            case N: return GLFW_KEY_N;
            case O: return GLFW_KEY_O;
            case P: return GLFW_KEY_P;
            case Q: return GLFW_KEY_Q;
            case R: return GLFW_KEY_R;
            case S: return GLFW_KEY_S;
            case T: return GLFW_KEY_T;
            case U: return GLFW_KEY_U;
            case V: return GLFW_KEY_V;
            case W: return GLFW_KEY_W;
            case X: return GLFW_KEY_X;
            case Y: return GLFW_KEY_Y;
            case Z: return GLFW_KEY_Z;

            case UP_ARROW: return GLFW_KEY_UP;
            case DOWN_ARROW: return GLFW_KEY_DOWN;
            case LEFT_ARROW: return GLFW_KEY_LEFT;
            case RIGHT_ARROW: return GLFW_KEY_RIGHT;

            case DIGIT_0: return GLFW_KEY_0;
            case DIGIT_1: return GLFW_KEY_1;
            case DIGIT_2: return GLFW_KEY_2;
            case DIGIT_3: return GLFW_KEY_3;
            case DIGIT_4: return GLFW_KEY_4;
            case DIGIT_5: return GLFW_KEY_5;
            case DIGIT_6: return GLFW_KEY_6;
            case DIGIT_7: return GLFW_KEY_7;
            case DIGIT_8: return GLFW_KEY_8;
            case DIGIT_9: return GLFW_KEY_9;

            case LEFT_SHIFT: return GLFW_KEY_LEFT_SHIFT;
            case RIGHT_SHIFT: return GLFW_KEY_RIGHT_SHIFT;
            case LEFT_CONTROL: return GLFW_KEY_LEFT_CONTROL;
            case RIGHT_CONTROL: return GLFW_KEY_RIGHT_CONTROL;

            case SPACE: return GLFW_KEY_SPACE;
        }
        throw new IllegalArgumentException("Unknown key: " + key);
    }
}