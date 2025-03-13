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

public class ModifyFragment extends Fragment {
    private Book book;
    private EditText titleInput;
    private EditText authorInput;
    private EditText pageInput;
    private EditText releaseInput;
    private Button createButton;
    public ModifyFragment(Book book) {
        this.book = book;
    }

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
        createButton.setText(R.string.modify_text);

        titleInput.setText(book.Title);
        authorInput.setText(book.Author);
        pageInput.setText(String.valueOf(book.Page_count));
        releaseInput.setText(String.valueOf(book.Release_year));

        createButton.setOnClickListener(v -> {
            try {
                String title = titleInput.getText().toString();
                String author = authorInput.getText().toString();
                int page = Integer.parseInt(pageInput.getText().toString());
                int release = Integer.parseInt(releaseInput.getText().toString());
                Book book = new Book(this.book.id, title, author, page, release);
                MainActivity.getApi().change(this.book.id, book).enqueue(new Callback<Book>() {
                    @Override
                    public void onResponse(Call<Book> call, Response<Book> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getContext(), "Book successfully modified", Toast.LENGTH_SHORT).show();
                            MainActivity.getInstance().navigateTo(R.id.nav_listing);
                        } else new AlertDialog.Builder(getContext())
                                .setTitle("Failed to modify book")
                                .setMessage("" + response.code())
                                .show();
                    }

                    @Override
                    public void onFailure(Call<Book> call, Throwable throwable) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Failed to modify book")
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