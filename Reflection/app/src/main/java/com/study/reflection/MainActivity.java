package com.study.reflection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.BaseGameUtils;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Button btnShowLeaderboard;
    private Button btnShowAchievement;
    private EditText editPoint;
    private EditText editTime;
    private int nStep = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Play Games services에 접근할 구글 Api 클라이언트 생성
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                // add other APIs and scopes here as needed
                .build();

        editPoint = (EditText) findViewById(R.id.edit_point);
        Button btnPoint = (Button) findViewById(R.id.btn_point);
        btnPoint.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mGoogleApiClient.isConnected())
                {
                    // 최고 점수 등록
                    Games.Leaderboards.submitScore(
                            mGoogleApiClient,
                            getString(R.string.leaderboard_point),
                            Integer.parseInt(editPoint.getText().toString()));
                }
            }
        });

        editTime = (EditText) findViewById(R.id.edit_time);
        Button btnTime = (Button) findViewById(R.id.btn_time);
        btnTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mGoogleApiClient.isConnected())
                {
                    // 소요 시간 등록
                    Games.Leaderboards.submitScore(
                            mGoogleApiClient,
                            getString(R.string.leaderboard_time),
                            Integer.parseInt(editTime.getText().toString()));
                }
            }
        });

        Button btnAchievement1 = (Button) findViewById(R.id.btn_achievement1);
        btnAchievement1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 일회성업적 달성
                if (mGoogleApiClient.isConnected())
                {
                    Games.Achievements.unlock(
                            mGoogleApiClient,
                            getString(R.string.achievement_one));
                }
            }
        });

        Button btnAchievement2 = (Button) findViewById(R.id.btn_achievement2);
        btnAchievement2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 단계별업적 달성
                if (mGoogleApiClient.isConnected())
                {
                    Games.Achievements.increment(
                            mGoogleApiClient,
                            getString(R.string.achievement_step),
                            nStep);
                    nStep += 1;
                }
            }
        });

        btnShowLeaderboard = (Button) findViewById(R.id.btn_show_leaderboard);
        btnShowLeaderboard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 리더보드 확인
//          if (isSignedIn())
//          {
//             startActivityForResult(
//                   Games.Leaderboards.getLeaderboardIntent(
//                         getApiClient(),
//                         getString(R.string.leaderboard_point)),
//                   1001);
//          }
                if (mGoogleApiClient.isConnected())
                {
                    startActivityForResult(
                            Games.Leaderboards.getAllLeaderboardsIntent(mGoogleApiClient),
                            1001);
                }

            }
        });

        btnShowAchievement = (Button) findViewById(R.id.btn_show_achievement);
        btnShowAchievement.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // 업적 확인
                startActivityForResult(
                        Games.Achievements.getAchievementsIntent(mGoogleApiClient),
                        1002);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // auto sign in
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        btnShowAchievement.setEnabled(true);
        btnShowLeaderboard.setEnabled(true);
    }

    private static int RC_SIGN_IN = 9001;

    private boolean mResolvingConnectionFailure = false;
    private boolean mAutoStartSignInFlow = true;
    private boolean mSignInClicked = false;

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mResolvingConnectionFailure) {
            // already resolving
            return;
        }

        // if the sign-in button was clicked or if auto sign-in is enabled,
        // launch the sign-in flow
        if (mSignInClicked || mAutoStartSignInFlow) {
            mAutoStartSignInFlow = false;
            mSignInClicked = false;
            mResolvingConnectionFailure = true;

            // Attempt to resolve the connection failure using BaseGameUtils.
            // The R.string.signin_other_error value should reference a generic
            // error string in your strings.xml file, such as "There was
            // an issue with sign-in, please try again later."
            if (!BaseGameUtils.resolveConnectionFailure(this,
                    mGoogleApiClient, connectionResult,
                    RC_SIGN_IN, R.string.sign_in_other_error)) {
                mResolvingConnectionFailure = false;
            }
        }

        // Put code here to display the sign-in button
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Attempt to reconnect
        mGoogleApiClient.connect();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            mSignInClicked = false;
            mResolvingConnectionFailure = false;
            if (resultCode == RESULT_OK) {
                mGoogleApiClient.connect();
            } else {
                // Bring up an error dialog to alert the user that sign-in
                // failed. The R.string.signin_failure should reference an error
                // string in your strings.xml file that tells the user they
                // could not be signed in, such as "Unable to sign in."
                BaseGameUtils.showActivityResultError(this,
                        requestCode, resultCode, R.string.sign_in_failed);
            }
        }
    }

}
