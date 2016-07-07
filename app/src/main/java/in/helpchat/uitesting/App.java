package in.helpchat.uitesting;

import android.app.Application;

import com.segment.analytics.Analytics;

/**
 * Created by adarshpandey on 6/28/16.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Analytics analytics = new Analytics.Builder(this, "ffI6hVkFKWBrU4tnStde993YN2qy0Ckg")
                .use(HelpchatIntegration.createFactory(this, "AANRy1kB5zaF9eCn-czsmZYIDepziZ-A~6314051602")).
                        logLevel(Analytics.LogLevel.VERBOSE).build();
        Analytics.setSingletonInstance(analytics);

    }
}
