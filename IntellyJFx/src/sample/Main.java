package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


import java.awt.*;
import java.awt.event.InputEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    static long lastTime = System.currentTimeMillis();
    static long donguSayisi = 0;
    static long time = 0;
    static long deltaTime = 0;
    static final int HEDEFFPS = 60;
    static boolean fareOynasin = false;

    static double x = 900;
    static double y = 500;

    public static void main(String[] args) throws AWTException {


        jnativeHook();


        for (String text : args) {
            System.out.println(text);
        }


        Robot robot = new Robot();

        int yon = 0;
        double i = 300;

        robot.mouseMove(300, 300);

        int yariCap = 10;


        while (true) {
            donguBasi();

            if (i == 900)
                yon = -1;

            else if (i == 300) {
                yon = 1;
            }
            i += yon;

            if (fareOynasin) {
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.mouseRelease(InputEvent.BUTTON1_MASK);
                robot.mouseMove((int) (x + Math.cos(i) * yariCap), (int) (y + Math.sin(i) * yariCap));

            }
            donguSonu();
        }

//        launch(args);
    }

    private static void jnativeHook() {
            try {
                GlobalScreen.registerNativeHook();
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
            }
            // Get the logger for "org.jnativehook" and set the level to off.
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);

    // Don't forget to disable the parent handlers.
            logger.setUseParentHandlers(false);




        NativeKeyListener list = new NativeKeyListener() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {



            }

            @Override
            public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
                System.out.println("basıldı - " + nativeKeyEvent.getKeyChar());
                fareOynasin = !fareOynasin;
            }

            @Override
            public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

            }
        };
            GlobalScreen.addNativeKeyListener(list);


        NativeMouseInputListener mouseList = new NativeMouseInputListener() {
            @Override
            public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
                x = nativeMouseEvent.getX();
                y = nativeMouseEvent.getY();

                System.out.println(x+ "-" + y);
            }

            @Override
            public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {

            }

            @Override
            public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {

            }

            @Override
            public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {

            }

            @Override
            public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {

            }
        };
        GlobalScreen.addNativeMouseListener(mouseList);






    }


    private static void donguSonu() {
        deltaTime = System.currentTimeMillis() - time;

        long delay = (1000 / HEDEFFPS) - (System.currentTimeMillis() - time);
        if (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void donguBasi() {
        time = System.currentTimeMillis();
        donguSayisi++;
        if (time - lastTime > 1000) {
            lastTime = time;  // we're too far behind, catch up
            donguSayisi = 0;
            saniyedeBirYapilcaklar();

        }
    }

    private static void saniyedeBirYapilcaklar() {


    }
}
