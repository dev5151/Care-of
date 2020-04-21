package com.dev5151.careof.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev5151.careof.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class OnboardAdapter extends SliderViewAdapter<OnboardAdapter.ViewHolder> {
    private Context context;

    public OnboardAdapter(Context context) {
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout, null);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        switch (position) {
            case 0:
                Glide.with(viewHolder.itemView)
                        .load(getImage("pic_1"))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            case 1:
                Glide.with(viewHolder.itemView)
                        .load(getImage("pic_2"))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);

                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(getImage("pic_3"))
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    class ViewHolder extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;


        public ViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;

        }
    }

    public int getImage(String imageName) {

        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        return drawableResourceId;
    }
}
