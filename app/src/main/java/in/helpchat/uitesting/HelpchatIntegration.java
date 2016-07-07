package in.helpchat.uitesting;

import android.app.Application;
import android.content.Context;

import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.segment.analytics.ValueMap;
import com.segment.analytics.integrations.Integration;
import com.segment.analytics.integrations.Logger;

/**
 * Created by adarshpandey on 6/30/16.
 */
public class HelpchatIntegration extends Integration<Void> implements HelpchatABListener {
    private static final String OPTIMIZELY_KEY = "Optimizely";

    private final Context context;
    private final Logger logger;
    private final Analytics analytics;

    // Optimizely must be initialized immediately on launch. So we require the token when creating the
    // factory, and initialize it in this method.
    public static Factory createFactory(final Application application, String token) {
        return new Factory() {
            @Override public Integration<?> create(ValueMap settings, Analytics analytics) {
                Logger logger = analytics.logger(OPTIMIZELY_KEY);

                HelpchatIntegration integration =
                        new HelpchatIntegration(analytics, application, logger);
                    HelpchatABTesting.addListener(integration);

                HelpchatABTesting.init();
                return integration;
            }

            @Override public String key() {
                return OPTIMIZELY_KEY;
            }
        };
    }

    public HelpchatIntegration(Analytics analytics, Context context, Logger logger) {
        this.analytics = analytics;
        this.context = context;
        this.logger = logger;
    }

    public void log(String eventName) {
        analytics.track(eventName);
    }

    @Override
    public void start() {
        Properties properties = new Properties();
        properties.put("key", "boot");
        analytics.track("HelpchatABBoot", properties);


    }
}
