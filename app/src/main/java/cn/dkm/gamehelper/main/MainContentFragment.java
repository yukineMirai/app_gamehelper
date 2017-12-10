package cn.dkm.gamehelper.main;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cn.dkm.gamehelper.R;
import cn.dkm.gamehelper.global.Constant;
import cn.dkm.gamehelper.global.MyApplication;

/**
 * Created by Administrator on 2017/12/9 0009.
 *
 */

public class MainContentFragment extends Fragment {


    /*private MyApplication application;*/
    private Activity mActivity = null;
    // 主界面
    private WebView mWebView = null;
    private ProgressBar mProgressBar = null;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mActivity = this.getActivity();
       /* application = (MyApplication) mActivity.getApplication();*/

        View view = inflater.inflate(R.layout.web, null);
        mWebView =  view.findViewById(R.id.webView);
        mProgressBar =  view.findViewById(R.id.progress_bar);
        // 设置支持JavaScript脚本
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置默认缩放方式尺寸是far
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(20);

        // 访问assets目录下的文件
       /* String url = Constant.BASEURL+"m";*/
        String url = "http://m.xzgjf.com/h5/index.html";
        mWebView.loadUrl(url);

        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient() {
            // url拦截
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 使用自己的WebView组件来响应Url加载事件，而不是使用默认浏览器器加载页面
                view.loadUrl(url);
                // 相应完成返回true

                Log.d("++++","相应完成返回true" +url);
                return true;
                // return super.shouldOverrideUrlLoading(view, url);
            }

            // 页面开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                Log.d("++++","VISIBLE");
                super.onPageStarted(view, url, favicon);
            }

            // 页面加载完成
            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                Log.d("++++","GONE" + url +"------");
                super.onPageFinished(view, url);
            }

            // WebView加载的所有资源url
            @Override
            public void onLoadResource(WebView view, String url) {
                Log.d("++++","onLoadResource "+url);
                super.onLoadResource(view, url);

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                Log.d("++++","onReceivedError "+errorCode+ "--"+description);
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

        });

        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            // 处理javascript中的alert
            public boolean onJsAlert(WebView view, String url, String message,
                                     final JsResult result) {
                Log.d("++++","onJsAlert "+url+ "--"+result);
                return super.onJsAlert(view, url, message, result);
            };

            @Override
            // 处理javascript中的confirm
            public boolean onJsConfirm(WebView view, String url,
                                       String message, final JsResult result) {
                Log.d("++++","onJsConfirm "+url+ "--"+result);
                return super.onJsConfirm(view, url, message, result);
            };

            @Override
            // 处理javascript中的prompt
            public boolean onJsPrompt(WebView view, String url, String message,
                                      String defaultValue, final JsPromptResult result) {
                Log.d("++++","onJsPrompt "+url+ "--"+result);
                return super.onJsPrompt(view, url, message, defaultValue,
                        result);
            };

            // 设置网页加载的进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                Log.d("++++","onProgressChanged ");
                super.onProgressChanged(view, newProgress);
            }

            // 设置程序的Title
            @Override
            public void onReceivedTitle(WebView view, String title) {

                Log.d("++++","onReceivedTitle " +title);
                super.onReceivedTitle(view, title);
            }
        });



        return view;
    }

    /**
     *
     * 描述：能退出吗
     * @return
     * @throws
     * @date：2013-12-13 上午11:06:58
     * @version v1.0
     */
    public boolean canBack(){
        if(mWebView.canGoBack()){
            mWebView.goBack();
            return false;
        }
        return true;
    }
}
