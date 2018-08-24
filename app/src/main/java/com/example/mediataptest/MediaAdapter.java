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

import java.util.ArrayList;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaViewHolder> {

    AppCompatActivity activity;
    private List<Page> list;

    public MediaAdapter(AppCompatActivity activity, MediaModel mediaModel) {
        this.activity = activity;
        list = new ArrayList<>();
        if (mediaModel != null){
            for (Page item: mediaModel.query.pages){
                list.add(item);
            }
        }

    }

    @NonNull
    @Override
    public MediaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterContentBinding contentBinding = DataBindingUtil.inflate(layoutInflater,R.layout.adapter_content,parent,false);
        View view = contentBinding.getRoot();

        return new MediaViewHolder(view,contentBinding,activity);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaViewHolder holder, int position) {
        holder.bindView(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    /** Filter Logic**/
    public void animateTo(List<Page> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);

    }

    private void applyAndAnimateRemovals(List<Page> newModels) {


        for (int i = list.size() - 1; i >= 0; i--) {
            final Page model = list.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Page> newModels) {

        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Page model = newModels.get(i);
            if (!list.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Page> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Page model = newModels.get(toPosition);
            final int fromPosition = list.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public Page removeItem(int position) {
        final Page model = list.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Page model) {
        list.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Page model = list.remove(fromPosition);
        list.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
}
