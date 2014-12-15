package tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.example.ramap.R;

/**
 * Created by Joe's Desktop on 12/14/2014.
 */
public class TwitterFragment extends Fragment{
    private WebView webView;
    private Bundle webViewBundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout twitterLayout = (LinearLayout) inflater.inflate(R.layout.fragment_twitter,
                container, false);

        webView = (WebView) twitterLayout.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        if (webViewBundle == null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://mobile.twitter.com/fordhamnotes");
        } else {
            webView.restoreState(webViewBundle);
        }

        return twitterLayout;

    }

    @Override
    public void onPause() {
        super.onPause();

        webViewBundle = new Bundle();
        webView.saveState(webViewBundle);
    }
}
