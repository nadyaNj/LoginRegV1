package am.home.LoginRegApp;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mUsername;
    private TextView mSessionKey;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = (TextView)findViewById(R.id.username);
        mSessionKey = (TextView)findViewById(R.id.session_key);

        Button getSessionBtn = (Button)findViewById(R.id.getSession);
        getSessionBtn.setOnClickListener(mOnGetSessionKey);

        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccountsByType("fm.last.android.account");
        if(accounts.length > 0) {
            mUsername.setText("Last.fm account: " + accounts[0].name);
        } else {
            mUsername.setText("No Last.fm account configured!");
        }
    }

    private View.OnClickListener mOnGetSessionKey = new View.OnClickListener() {
        public void onClick(View v) {
            AccountManager am = AccountManager.get(MainActivity.this);
            Account[] accounts = am.getAccountsByType("fm.last.android.account");
            if(accounts.length > 0) {
                Bundle options = new Bundle();
                options.putString("api_key", "4a63a45f148bf80885675f7facce7841");
                options.putString("api_secret", "14124caf3e1854c5e283f5bce7d93f1e");
                am.getAuthToken(accounts[0], "", options, MainActivity.this, new AccountManagerCallback<Bundle>() {
                    public void run(AccountManagerFuture<Bundle> arg0) {
                        try {
                            String key = arg0.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                            mSessionKey.setText("Session key: " + key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
            }
        }
    };
}
