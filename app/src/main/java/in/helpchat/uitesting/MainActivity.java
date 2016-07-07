package in.helpchat.uitesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.optimizely.CodeBlocks.CodeBranch;
import com.optimizely.CodeBlocks.DefaultCodeBranch;
import com.optimizely.CodeBlocks.OptimizelyCodeBlock;
import com.optimizely.Optimizely;
import com.optimizely.Variable.LiveVariable;
import com.segment.analytics.Analytics;
import com.segment.analytics.Options;
import com.segment.analytics.Properties;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Optimizely.startOptimizelyWithAPIToken("AANRy1kB5zaF9eCn-czsmZYIDepziZ-A~6314051602", getApplication());

        button = (Button) findViewById(R.id.click_me);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Hello Hell", Toast.LENGTH_LONG).show();
            }
        });

        Properties properties = new Properties();
        properties.put("Event", "create");
        Analytics.with(this).track("OnCreate", properties, new Options().setIntegration("Optimizely", true).setIntegration("Helpchat", true));

        checkout();
    }

    private static OptimizelyCodeBlock checkoutFlow = Optimizely.codeBlock("CheckoutFlow").withBranchNames("shortCheckout", "longCheckout");
    private static OptimizelyCodeBlock normalFlow = Optimizely.codeBlock("NormalFlow").withBranchNames("shortNormal", "longNormal");

    private static LiveVariable<String> chutiyapa = Optimizely.stringForKey("chutiya", "defult-chutiya");

    private void checkout() {
        // This line defines Code Blocks "shortCheckout", "longCheckout", and a
        // default block that is executed in the case that the experiment is
        // not activated.

        Log.d(TAG, "Block Name:  " + checkoutFlow.getBlockName());
        Log.d(TAG, "Branch Name checkout flow:  " + checkoutFlow.getDefaultBranchName());


        checkoutFlow.execute(new DefaultCodeBranch() {
            @Override
            public void execute() {
                // This block is executed by default
                Log.d(TAG, "Default checkout ");
                Properties properties = new Properties();
                properties.put("Event", "execute");
                Analytics.with(MainActivity.this).track("checkout-execute", properties, new Options().setIntegration("Optimizely", true));

            }
        }, new CodeBranch() {
            @Override
            public void execute() {
                // This block is executed when myCheckoutTest -> shortCheckout
                Log.d(TAG,  "checkout - 1 ");
                Properties properties = new Properties();
                properties.put("Event", "execute");
                Analytics.with(MainActivity.this).track("checkout-execute", properties, new Options().setIntegration("Optimizely", true));

            }
        }, new CodeBranch() {
            @Override
            public void execute() {
                // This block is executed when myCheckoutTest -> longCheckout
                Log.d(TAG,  "checkout - 2 ");
                Properties properties = new Properties();
                properties.put("Event", "execute");
                Analytics.with(MainActivity.this).track("checkout-execute", properties, new Options().setIntegration("Optimizely", true));

            }
        });

        Log.d(TAG, "Block Name Normal flow:  " + normalFlow.getBlockName());
        Log.d(TAG, "Branch Name Normal flow:  " + normalFlow.getDefaultBranchName());

        normalFlow.execute(new DefaultCodeBranch() {
            @Override
            public void execute() {
                Log.d(TAG,  "Normal - default ");
                Properties properties = new Properties();
                properties.put("Event", "Normal - execute");
                Analytics.with(MainActivity.this).track("normal-execute", properties, new Options().setIntegration("Optimizely", true));
            }
        }, new CodeBranch() {
            @Override
            public void execute() {
                Log.d(TAG,  "Normal - 1 ");
                Properties properties = new Properties();
                properties.put("Event", "Normal - execute");
                Analytics.with(MainActivity.this).track("normal-execute", properties, new Options().setIntegration("Optimizely", true));
            }
        }, new CodeBranch() {
            @Override
            public void execute() {
                Log.d(TAG,  "Normal - 2 ");
                Properties properties = new Properties();
                properties.put("Event", "Normal - execute");
                Analytics.with(MainActivity.this).track("normal-execute", properties, new Options().setIntegration("Optimizely", true));
            }
        });

        Log.d(TAG, "Chutiyapa : " + chutiyapa.get());
    }

}
