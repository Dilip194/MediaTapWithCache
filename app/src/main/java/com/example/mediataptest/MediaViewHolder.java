package com.example.mediataptest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mediataptest.databinding.AdapterContentBinding;
import com.example.mediataptest.mediaModel.MediaModel;

class MediaViewHolder extends RecyclerView.ViewHolder {

    MediaModel mediaModel;
    AdapterContentBinding contentBinding;

    public MediaViewHolder(View itemView, MediaModel mediaModel,AdapterContentBinding contentBinding) {

        super(itemView);
        this.mediaModel = mediaModel;
        this.contentBinding = contentBinding;
    }

    public void bindView(int position){
        contentBinding.setPage(mediaModel.query.pages.get(position));
    }
}
