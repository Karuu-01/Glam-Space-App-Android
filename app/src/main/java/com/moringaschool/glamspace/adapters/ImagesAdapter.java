package com.moringaschool.glamspace.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.glamspace.R;
import com.moringaschool.glamspace.models.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImageAdapterViewHolder> {
    private List<Result> searchResults;
    private Context mContext;
    public ImagesAdapter( Context context, List<Result> searchResults){
        this.mContext= context;
        this.searchResults= searchResults;

    }
    @NonNull
    @Override
    public ImageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ImageAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterViewHolder holder, int position) {
      holder.bindImages(searchResults.get(position));
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    public class ImageAdapterViewHolder extends RecyclerView.ViewHolder {
        @SuppressLint("NonConstantResourceId")
        @BindView(R.id.imImageView) ImageView imageView;
        private Context context;
        public ImageAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            context = itemView.getContext();

        }

        public void bindImages(Result result) {
            Picasso.get().load(result.getUrls().getRegular()).into(imageView);
        }
    }
}
