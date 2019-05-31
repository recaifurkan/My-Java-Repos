

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;


import java.awt.*;


import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    static long lastTime = System.currentTimeMillis();
    static long donguSayisi = 0;
    static long time = 0;
    static long deltaTime = 0;
    static final int HEDEFFPS = 2;
    static boolean fareOynasin = false;

    static double x = 0;
    static double y = 0;

    public static void main(String[] args) throws AWTException {


        


        for (String text : args) {
            System.out.println(text);
        }


        Robot robot = new Robot();


        double i = 0;
        int yariCap = 50;


        while (true) {
            donguBasi();

            i++;

            if (fareOynasin) {

                robot.mouseMove((int) (x + Math.cos(i) * yariCap), (int) (y + Math.sin(i) * yariCap));
//                robot.mouseMove((int)x,(int)y);

            }
            donguSonu();
        }


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

        logger.setUseParentHandlers(false);


        // Don't forget to disable the parent handlers.
        NativeKeyListener list = new NativeKeyListener() {
            @Override
            public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {


            }

            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                System.out.println("basıldı - " + NativeKeyEvent.getKeyText(e.getKeyCode()));
                fareOynasin = !fareOynasin;
                if ("Escape".equals(NativeKeyEvent.getKeyText(e.getKeyCode()))) {
                    System.exit(15);
                }

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

                System.out.println(x + "-" + y);
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
