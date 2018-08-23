package com.example.mediataptest;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mediataptest.databinding.AdapterContentBinding;
import com.example.mediataptest.mediaModel.MediaModel;

public class MediaAdapter extends RecyclerView.Adapter<MediaViewHolder> {

    AppCompatActivity activity;
    MediaModel mediaModel;

    public MediaAdapter(AppCompatActivity activity, MediaModel mediaModel) {
        this.activity = activity;
        this.mediaModel = mediaModel;
    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());/*.inflate(R.layout.adapter_content,parent,false);*/
        AdapterContentBinding contentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_content,parent,false);
        View view = contentBinding.getRoot();

        return new MediaViewHolder(view,mediaModel,contentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mediaModel != null ? mediaModel.query.pages.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void notifyChange(MediaModel mediaModel){
        this.mediaModel = mediaModel;
        notifyDataSetChanged();
    }
}
