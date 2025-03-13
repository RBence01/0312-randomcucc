package com.example.miertkelleztafeladatotcsinalni;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Book> items;
    private boolean modify;

    public ListAdapter(List<Book> items, boolean modify) {
        this.items = items;
        this.modify = modify;
    }


    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView author;
        public Button delete;
        private Book item;
        private int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            delete = itemView.findViewById(R.id.delteButton);
            if (modify) {
                delete.setText(R.string.modify_text);
                delete.setOnClickListener(v -> {
                    MainActivity.getInstance().navigateTo(new ModifyFragment(item));
                });
            }
            else delete.setOnClickListener(v -> {
                MainActivity.getApi().delete(item.id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            items.remove(position);
                            notifyItemRemoved(position);
                        } else new AlertDialog.Builder(itemView.getContext())
                                .setTitle("Error deleting book")
                                .show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {
                        new AlertDialog.Builder(itemView.getContext())
                                .setTitle("Error deleting book")
                                .setMessage(throwable.getMessage())
                                .show();
                    }
                });
            });
        }

        public void bind(int position) {
            this.position = position;
            this.item = items.get(position);
            title.setText(item.Title);
            author.setText(item.Author);
        }
    }
}
