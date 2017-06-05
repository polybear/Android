package com.study.myadmob1;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // 초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(getApplicationContext(), bannerid);

        // 테스트 광고 부르기
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice("C4FBF89FCB7FA1BF007BC94FC854E6B2").
                build();
        mAdView.loadAd(adRequest);
    }
}
