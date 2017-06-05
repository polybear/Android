package com.study.myadmob2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends Activity {

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // 초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(getApplicationContext(), bannerid);

        // 테스트 광고 부르기
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice("C4FBF89FCB7FA1BF007BC94FC854E6B2").build();
        mAdView.loadAd(adRequest);
    }

    public void hideAd(View v) {
        if (mAdView.isEnabled()) {
            mAdView.setEnabled(false);
        }
        if (mAdView.getVisibility() != View.INVISIBLE) {
            mAdView.setVisibility(View.INVISIBLE);
        }
    }

    public void showAd(View v) {
        if (!mAdView.isEnabled()) {
            mAdView.setEnabled(true);
        }
        if (mAdView.getVisibility() != View.VISIBLE) {
            mAdView.setVisibility(View.VISIBLE);
        }
    }
}