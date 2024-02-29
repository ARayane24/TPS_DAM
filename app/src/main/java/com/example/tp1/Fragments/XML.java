package com.example.tp1.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.tp1.R;

public class XML extends Fragment {

    private String CodeUrl;
    WebView webView;

    public XML(){}
    public XML(String codeUrl){
        CodeUrl = codeUrl;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webView = view.findViewById(R.id.web);
        webView.getSettings().setAllowFileAccess(true);
        webView.loadUrl("file:///android_asset/"+CodeUrl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xml_solution, container, false);
    }
}