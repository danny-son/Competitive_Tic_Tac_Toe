package com.example.comptictactoe.Model.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.comptictactoe.Model.GridSize;
import com.example.comptictactoe.R;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private int[] imageList;

    LayoutInflater inflater;

    public GridAdapter(Context context, int[] imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
        return imageList.length;
    }

    @Override
    public Object getItem(int i) {
        return imageList[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null) {
            view = inflater.inflate(R.layout.grid_item, null);
        }

        //TODO set image size based on current grid size and phone dimensions
        ImageView imageView = view.findViewById(R.id.grid_image);
        imageView.setImageResource(imageList[i]);

        return view;
    }


    /**
     * Updates an image in our adapter
     *
     * @param id       the id that contains our drawable
     * @param position index in our image list
     */
    public void updateImage(int id, int position) {
        this.imageList[position] = id;
        this.notifyDataSetChanged();
    }

    public void removeImage(int position) {
        this.imageList[position] = 0;
        this.notifyDataSetChanged();
    }

    /**
     * updates the whole list of our images
     *
     * @param imageList -> an array of image ids
     */
    public void updateImageList(int[] imageList) {
        this.imageList = imageList;
        this.notifyDataSetChanged();
    }

    public void updateGridSizeIncrease(GridSize newGridSize) {
        int size = 0;
        if (newGridSize == GridSize.FIVE_BY_FIVE) {
            size = 5;
        } else if (newGridSize == GridSize.SEVEN_BY_SEVEN) {
            size = 7;
        }
        int[] newImageList = new int[size * size];
        int prevIndex = 0;
        for (int row = 1; row < size - 1; row++) {
            for (int col = 1; col < size - 1; col++) {
                newImageList[(row * size) + col] = imageList[prevIndex];
                prevIndex++;
            }
        }
        updateImageList(newImageList);
    }
}
