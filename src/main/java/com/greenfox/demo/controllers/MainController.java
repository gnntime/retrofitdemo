package com.greenfox.demo.controllers;

import com.greenfox.demo.modells.MovieData;
import com.greenfox.demo.services.ApiInterface;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
public class MainController {
  public static String BASE_URL = "https://api.themoviedb.org";
  private String LANGUAGE = "en-US";
  private String API_KEY = "9818383aba797e6caf0bb0c1dbe33f52";
  private int PAGE = 1;

  @GetMapping("/getmovie")
  public ResponseEntity<?> getMovie() throws IOException {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    Call<MovieData> call = apiInterface.getMovie(API_KEY, LANGUAGE, PAGE);
    Response<MovieData> response = call.execute();
    return ResponseEntity.ok(response.body());
  }
}
