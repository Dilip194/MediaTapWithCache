package com.example.mediataptest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mediataptest.databinding.AdapterContentBinding;
import com.example.mediataptest.mediaModel.MediaModel;
import com.example.mediataptest.mediaPresenterListener.RecycleViewClickListener;

class MediaViewHolder extends RecyclerView.ViewHolder {

    MediaModel mediaModel;
    AdapterContentBinding contentBinding;
    RecycleViewClickListener onClickedListener;

    public MediaViewHolder(View itemView, MediaModel mediaModel, AdapterContentBinding contentBinding, AppCompatActivity activity) {

        super(itemView);
        this.mediaModel = mediaModel;
        this.contentBinding = contentBinding;
        this.onClickedListener = (RecycleViewClickListener) activity;
    }

    public void bindView(int position){
            try {
                contentBinding.setPage(mediaModel.query.pages.get(position));
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

        itemView.setOnClickListener(view ->{
            onClickedListener.onRecyclerViewClickedListener(mediaModel.query.pages.get(position).getTitle());
                });
    }
}
