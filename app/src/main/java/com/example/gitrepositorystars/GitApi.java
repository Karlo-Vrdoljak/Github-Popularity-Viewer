package com.example.gitrepositorystars;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitApi {

    @GET("/search/repositories")
    Call<GitRepoWrapper> getRepos(
            @Query(value = "q", encoded = false) String query,
            @Query(value = "sort", encoded = false) String sort,
            @Query(value = "order", encoded = false) String order
    );
}
