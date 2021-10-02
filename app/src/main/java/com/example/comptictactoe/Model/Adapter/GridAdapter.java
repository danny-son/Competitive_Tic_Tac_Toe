package com.example.comptictactoe.Model.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.comptictactoe.Model.Animation.AnimationController;
import com.example.comptictactoe.Model.Game.GridSize;
import com.example.comptictactoe.R;
import com.example.comptictactoe.Model.Animation.AnimationType;

import java.util.HashMap;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private int[] drawableList;
    //map containing the position and the animationType
    HashMap<Integer, AnimationType> animationMap = new HashMap<>();
    private GridSize gridSize;
    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private final int imageSize = Math.min(((screenHeight + screenWidth) / 10), 350);
    private final AnimationController animationController = new AnimationController();

    LayoutInflater inflater;

    public GridAdapter(Context context, int[] drawableList) {
        this.context = context;
        this.drawableList = drawableList;
        this.gridSize = GridSize.THREE_BY_THREE;
        setUpAnimationMap();
    }

    @Override
    public int getCount() {
        return drawableList.length;
    }

    @Override
    public Object getItem(int i) {
        return drawableList[i];
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

        setUpImages(view, i);
        return view;
    }

    /**
     * sets up our animation Map with empty animation States
     */
    private void setUpAnimationMap() {
        for (int i = 0; i < this.drawableList.length; i++) {
            animationMap.put(i, AnimationType.EMPTY);
        }
    }

    /**
     * defines the parameters for our images (width and height), based on our current gridSize
     * @param view ImageView that our adapter is setting up
     * @param i displays the position where that image is in our adapter
     */
    private void setUpImages(View view, int i) {
        ImageView imageView = view.findViewById(R.id.grid_image);
        Log.i("Dimension", String.valueOf(Resources.getSystem().getDisplayMetrics().widthPixels));
        Log.i("Dimension", String.valueOf(Resources.getSystem().getDisplayMetrics().heightPixels));
        int size;
        switch (gridSize) {
            case FIVE_BY_FIVE:
                size = imageSize - 125;
                break;
            case SEVEN_BY_SEVEN:
                size = imageSize - 175;
                break;
            default:
                size = imageSize;
                break;
        }
        imageView.setLayoutParams(new LinearLayout.LayoutParams(size,size));
        imageView.setImageResource(drawableList[i]);
        if (animationMap.get(i) == AnimationType.PLACE) {
            Log.i("Animation", "Animation Should Play!");
            animationController.animatePlace(imageView);
            animationMap.put(i, AnimationType.EMPTY);
        }
    }

    /**
     * Updates an image in our adapter
     *
     * @param id the id that contains our drawable
     * @param position index in our image list
     */
    public void placeImage(int id, int position) {
        this.drawableList[position] = id;
        this.animationMap.put(position, AnimationType.PLACE);
        this.notifyDataSetChanged();
    }

    public void removeImage(int position) {
        this.drawableList[position] = 0;
        this.notifyDataSetChanged();
    }

    /**
     * updates the whole list of our images
     *
     * @param drawableList -> an array of image ids
     */
    public void updateImageList(int[] drawableList) {
        this.drawableList = drawableList;
        this.notifyDataSetChanged();
    }

    public void updateGridSizeIncrease(GridSize newGridSize) {
        int size = 0;
        if (newGridSize == GridSize.FIVE_BY_FIVE) {
            this.gridSize = GridSize.FIVE_BY_FIVE;
            size = 5;
        } else if (newGridSize == GridSize.SEVEN_BY_SEVEN) {
            this.gridSize = GridSize.SEVEN_BY_SEVEN;
            size = 7;
        }
        int[] newImageList = new int[size * size];
        int prevIndex = 0;
        for (int row = 1; row < size - 1; row++) {
            for (int col = 1; col < size - 1; col++) {
                newImageList[(row * size) + col] = drawableList[prevIndex];
                prevIndex++;
            }
        }
        updateImageList(newImageList);
    }

}
