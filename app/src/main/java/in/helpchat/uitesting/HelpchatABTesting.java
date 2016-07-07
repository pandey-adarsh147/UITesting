package in.helpchat.uitesting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarshpandey on 7/1/16.
 */
public class HelpchatABTesting {

    private static List<HelpchatABListener> listeners = new ArrayList<>();

    public static void addListener(HelpchatABListener helpchatABTesting) {
        listeners.add(helpchatABTesting);
    }

    public static void init() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (HelpchatABListener testing: listeners) {
                    testing.start();
                }
            }
        });

        thread.start();
    }

}
