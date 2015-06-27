package com.lod.horaceayala.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class FixtureFragment extends Fragment {


    public FixtureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View rootView = inflater.inflate(R.layout.fragment_fixture, container, false);

        WebView webview = (WebView)rootView.findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setPluginState(WebSettings.PluginState.ON);
        webview.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
//        webview.loadData("<h1 style=\"color:blue\">This is a Blue Heading</h1><iframe src=\"https://vine.co/v/bnrtW52x1uJ/card?mute=1\"\n" +
//                "width=\"100%\" height=\"100%\" frameborder=\"0\"></iframe><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1><h1 style=\"color:blue\">This is a Blue Heading</h1>", "text/html", "UTF-8");
        webview.loadUrl("http://scores.infobae.com/mundial/index.html?channel=deportes.futbol.primeraa&lang=es_LA");

        return rootView;
    }


}
