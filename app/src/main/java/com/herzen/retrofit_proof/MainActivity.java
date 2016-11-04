package com.herzen.retrofit_proof;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
// import android.widget.RelativeLayout;
import android.widget.TextView;

import com.herzen.retrofit_proof.api.GithubClient;
import com.herzen.retrofit_proof.api.GithubService;
import com.herzen.retrofit_proof.api.Repository;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.buttonFetch)
    Button buttonFetch;
    @Bind(R.id.txtResult)
    TextView txtResult;

    GithubClient githubClient = new GithubClient();
    GithubService githubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        githubService = githubClient.getGithubService();
    }

    @OnClick(R.id.buttonFetch)
    public void handleFetchClick(){
        Call<List<Repository>> call = githubService.listRepo("OscarGovea");

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                txtResult.setText(response.body().toString());
            }
            @Override
            public void onFailure(Call<List<Repository>> call, Throwable throwable) {
                txtResult.setText("Something went wrong: " + throwable.getMessage());
            }
        });
    }
}
