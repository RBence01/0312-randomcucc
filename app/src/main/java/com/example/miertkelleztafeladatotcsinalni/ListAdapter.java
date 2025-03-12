package com.example.miertkelleztafeladatotcsinalni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<Book> items;
    private Context context;

    public ListAdapter(Context context, List<Book> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item, viewGroup, false);

        Book book = items.get(i);
        ((TextView)view.findViewById(R.id.title)).setText(book.Title);
        ((TextView)view.findViewById((R.id.author))).setText(book.Author);
        ((Button)view.findViewById(R.id.delteButton)).setOnClickListener(v -> {
            APIInstance.getInstance().
        });

        return view;
    }
}
