package com.dev5151.careof.Adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestBuilder;
import com.dev5151.careof.Interfaces.OnClickInterface;
import com.dev5151.careof.Models.OptionsModel;
import com.dev5151.careof.R;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;

import java.util.List;


public class LifeStyleAdapter extends RecyclerView.Adapter<LifeStyleAdapter.LifeStyleViewHolder> {

    private final int QUES_TYPE_CARD = 0;
    private final int QUES_TYPE_RADIO_BTN = 1;
    private List<OptionsModel> optionsModelList;
    private Context context;
    private String viewType;
    private OnClickInterface onClickInterface;
    private Integer flag = 0;

    public LifeStyleAdapter(List<OptionsModel> optionsModelList, Context context, String viewType, OnClickInterface onClickInterface) {
        this.optionsModelList = optionsModelList;
        this.context = context;
        this.viewType = viewType;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public LifeStyleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == QUES_TYPE_CARD) {
            View view = LayoutInflater.from(context).inflate(R.layout.option_cards, parent, false);
            return new LifeStyleAdapter.LifeStyleViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.radio_layout, parent, false);
            return new LifeStyleAdapter.LifeStyleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final LifeStyleViewHolder holder, int position) {
        final OptionsModel optionsModel = optionsModelList.get(position);
        switch (holder.getItemViewType()) {
            case QUES_TYPE_CARD:
                holder.title.setText(optionsModel.getOption());
                holder.subText.setText(optionsModel.getSubTitle());
                GlideToVectorYou
                        .init()
                        .with(context)
                        .withListener(new GlideToVectorYouListener() {
                            @Override
                            public void onLoadFailed() {
                                holder.progressBar.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onResourceReady() {
                                holder.progressBar.setVisibility(View.INVISIBLE);


                            }
                        })
                        .load(Uri.parse(optionsModel.getImageUrl()), holder.img);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.cardView.setBackground(ContextCompat.getDrawable(context, R.drawable.card_border));

                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                onClickInterface.onLifeStyleClick(optionsModel);
                            }
                        };
                        holder.itemView.postDelayed(runnable, 1000);

                    }
                });
                break;
            case QUES_TYPE_RADIO_BTN:
                holder.tvRound.setText(optionsModel.getOption());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.tvRound.setBackground(ContextCompat.getDrawable(context,R.drawable.card_border));
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                onClickInterface.onLifeStyleClick(optionsModel);
                            }
                        };
                        holder.itemView.postDelayed(runnable, 1000);
                    }
                });
                break;
        }


    }

    @Override
    public int getItemCount() {
        return optionsModelList.size();
    }

    public class LifeStyleViewHolder extends RecyclerView.ViewHolder {

        private TextView title, subText;
        private ImageView img;
        private CardView cardView;
        private ProgressBar progressBar;
        private TextView tvRound;

        public LifeStyleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            tvRound = itemView.findViewById(R.id.tv_round);
            subText = itemView.findViewById(R.id.sub_text);
            img = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.card_view);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (viewType.equals("card")) {
            return QUES_TYPE_CARD;

        } else {
            return QUES_TYPE_RADIO_BTN;
        }
    }


}
