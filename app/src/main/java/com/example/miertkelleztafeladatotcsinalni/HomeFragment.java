package com.example.miertkelleztafeladatotcsinalni;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private List<Book> items;
    private ListAdapter adapter;
    private RecyclerView list;
    private boolean modify;

    public HomeFragment(boolean modify) {
        this.modify = modify;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        list = view.findViewById(R.id.list);
        items = new ArrayList<>();
        adapter = new ListAdapter(items, modify);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        load();

        return view;
    }

    public void load() {
        MainActivity.getApi().getAll().enqueue(new Callback<List<Book>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    items.clear();
                    items.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else new AlertDialog.Builder(getContext())
                        .setTitle("Error loading books")
                        .show();
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable throwable) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Error loading books")
                        .setMessage(throwable.getMessage())
                        .show();
            }
        });
    }
}