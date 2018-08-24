package com.example.mediataptest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mediataptest.databinding.AdapterContentBinding;
import com.example.mediataptest.mediaModel.MediaModel;
import com.example.mediataptest.mediaModel.Page;
import com.example.mediataptest.mediaPresenterListener.RecycleViewClickListener;

import java.util.List;

class MediaViewHolder extends RecyclerView.ViewHolder {

    AdapterContentBinding contentBinding;
    RecycleViewClickListener onClickedListener;

    public MediaViewHolder(View itemView, AdapterContentBinding contentBinding, AppCompatActivity activity) {

        super(itemView);
        this.contentBinding = contentBinding;
        this.onClickedListener = (RecycleViewClickListener) activity;
    }

    public void bindView(int position, Page page){
            try {
                contentBinding.setPage(page);
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

        itemView.setOnClickListener(view ->{
            onClickedListener.onRecyclerViewClickedListener(page.getTitle());
                });
    }
}
