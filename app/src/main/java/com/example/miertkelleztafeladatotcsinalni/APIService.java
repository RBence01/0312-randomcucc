package com.example.miertkelleztafeladatotcsinalni;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {
    @GET(" ")
    Call<List<Book>> getAll();

    @POST(" ")
    Call<Void> create(@Body() Book book);

    @PATCH("{id}")
    Call<Book> change(@Path("id") int id, @Body() Book body);

    @DELETE("{id}")
    Call<Void> delete(@Path("id") int id);
}
