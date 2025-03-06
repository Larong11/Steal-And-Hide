package com.stealer;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL40.*;
import static org.lwjgl.system.MemoryUtil.*;

import com.stealer.core.*;

// import static com.stealer.variables.Color.*;


public class App {

private long window;
private int WIDTH, HEIGHT;
private double RATIO; 
private SceneManager scene;
private MouseInput mouse;
private KeyboardInput keyboard;

    private void start() {
        System.out.println("Started APP");

        init(); // Initialise window \\
        loop(); // Start loop \\

        // 
        glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
		glfwTerminate();
		glfwSetErrorCallback(null).free();
    }

    private void windowResizeCallback(long window, int WIDTH, int HEIGHT) {
        glViewport(0, 0, WIDTH, HEIGHT);
        this.HEIGHT = HEIGHT;
        this.WIDTH = WIDTH;
    }

    
    private void init() {
        GLFWErrorCallback.createPrint(System.err).set(); //All messages about errors with GLFW goes into console \\
        if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
            
        
        

        // Setting standart variables \\
        WIDTH = 800;
        HEIGHT = 600;
        RATIO = (float)WIDTH / (float)HEIGHT;
        window = glfwCreateWindow(this.WIDTH, this.HEIGHT, "Steal And Hide", NULL, NULL);
        mouse = new MouseInput(window);
        keyboard = new KeyboardInput(window);
        scene = new SceneManager(window);
        // -------------------------- \\

        glfwSetWindowAspectRatio(window, 4, 3);
        
        glfwSetFramebufferSizeCallback(window, this::windowResizeCallback);
        glfwMakeContextCurrent(window);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 2);
        GL.createCapabilities();
        glMatrixMode(GL_PROJECTION);
        glOrtho(-RATIO, RATIO, -1, 1, -1, 1);
        
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        
		glfwSwapInterval(1);
		glfwShowWindow(window);
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        scene.render();
        scene.update(keyboard, mouse);
        keyboard.deleteFrame();
        mouse.deleteFrame();
    }

    private void loop() {
        glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        while ( !glfwWindowShouldClose(window) ) {
            render();
            glfwPollEvents();
            glfwSwapBuffers(window);
        }
    }

    public static void main(String[] args) {
        new App().start();
    }
}