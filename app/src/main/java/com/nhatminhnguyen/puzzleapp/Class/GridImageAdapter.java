package com.nhatminhnguyen.puzzleapp.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhatminhnguyen.puzzleapp.R;

import java.util.ArrayList;

public class GridImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> textList = new ArrayList<>();
    ArrayList<Integer> imageList = new ArrayList<>();

    public GridImageAdapter(Context context, ArrayList<String> textList, ArrayList<Integer> imageList) {
        this.context = context;
        this.textList = textList;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return textList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_images,parent,false);

        ImageView imageView = view.findViewById(R.id.imageGrid);
        TextView textView = view.findViewById(R.id.textGrid);

        textView.setText(textList.get(i));
        imageView.setImageResource(imageList.get(i));

        return view;
    }
}
