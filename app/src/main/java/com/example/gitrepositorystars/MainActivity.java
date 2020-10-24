package com.example.gitrepositorystars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements RecViewAdapter.ItemClickListener {

    RecViewAdapter recViewAdapter;
    private String gitApiURL = "https://api.github.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit ret = new Retrofit.Builder().baseUrl(this.gitApiURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitApi gitApi = ret.create(GitApi.class);

        Call<GitRepoWrapper> listCall = gitApi.getRepos("stars:>=100000", "stars", "desc");
        System.out.println(listCall.request().toString());

        listCall.enqueue(new Callback<GitRepoWrapper>() {
            @Override
            public void onResponse(Call<GitRepoWrapper> call, Response<GitRepoWrapper> response) {
                if(!response.isSuccessful()) {
                    System.err.println("Code" + response.code());
                    return;
                }
                GitRepoWrapper repos = response.body();
                //System.out.println(repos);
                for (GitRepo gr: repos.getItems()) {
                    System.out.println(gr);
                }
                RecyclerView recyclerView = findViewById(R.id.rev_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recViewAdapter = new RecViewAdapter(MainActivity.this, repos.getItems());
                recViewAdapter.setOnclickListener(MainActivity.this::onItemClick);

                recyclerView.setAdapter(recViewAdapter);
            }

            @Override
            public void onFailure(Call<GitRepoWrapper> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        GitRepo item = recViewAdapter.getItem(position);
        Uri uri = Uri.parse(item.getHtmlUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}