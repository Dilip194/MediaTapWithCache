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
import com.example.mediataptest.mediaModel.Page;

import java.util.List;

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

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterContentBinding contentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_content,parent,false);
        View view = contentBinding.getRoot();

        return new MediaViewHolder(view,mediaModel,contentBinding,activity);
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

    /** Filter Logic**/
    public void animateTo(MediaModel models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
        notifyDataSetChanged();
    }

    private void applyAndAnimateRemovals(MediaModel newModels) {

        for (int i = newModels.query.pages.size() - 1; i >= 0; i--) {
            final Page model = newModels.query.pages.get(i);
            if (!newModels.query.pages.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(MediaModel newModels) {

        for (int i = 0, count = newModels.query.pages.size(); i < count; i++) {
            final Page model = newModels.query.pages.get(i);
            if (!newModels.query.pages.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(MediaModel newModels) {
        for (int toPosition = newModels.query.pages.size() - 1; toPosition >= 0; toPosition--) {
            final Page model = newModels.query.pages.get(toPosition);
            final int fromPosition = newModels.query.pages.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    private Page removeItem(int position) {
        final Page model = mediaModel.query.pages.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    private void addItem(int position, Page model) {
        mediaModel.query.pages.add(position, model);
        notifyItemInserted(position);
    }

    private void moveItem(int fromPosition, int toPosition) {
        final Page model = mediaModel.query.pages.remove(fromPosition);
        mediaModel.query.pages.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
