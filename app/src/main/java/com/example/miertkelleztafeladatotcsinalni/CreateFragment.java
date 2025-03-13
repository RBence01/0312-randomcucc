package com.example.miertkelleztafeladatotcsinalni;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateFragment extends Fragment {
    private EditText titleInput;
    private EditText authorInput;
    private EditText pageInput;
    private EditText releaseInput;
    private Button createButton;

    public CreateFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        titleInput = view.findViewById(R.id.titleInput);
        authorInput = view.findViewById(R.id.authorInput);
        pageInput = view.findViewById(R.id.pageInput);
        releaseInput = view.findViewById(R.id.releaseInput);
        createButton = view.findViewById(R.id.creatButton);

        createButton.setOnClickListener(v -> {
            try {
                String title = titleInput.getText().toString();
                String author = authorInput.getText().toString();
                int page = Integer.parseInt(pageInput.getText().toString());
                int release = Integer.parseInt(releaseInput.getText().toString());
                Book book = new Book(0, title, author, page, release);
                MainActivity.getApi().create(book).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Book successfully created", Toast.LENGTH_SHORT).show();
                            MainActivity.getInstance().navigateTo(R.id.nav_listing);
                        } else new AlertDialog.Builder(getContext())
                                .setTitle("Failed to create book")
                                .setMessage("" + response.code())
                                .show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Failed to create book")
                                .setMessage(throwable.getMessage())
                                .show();
                    }
                });
            } catch (Exception e) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Incorrect input format")
                        .show();
            }
        });
        return view;
    }
}