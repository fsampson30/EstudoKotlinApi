package com.sampson.terceirokotlin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewEditFragmentAdapter extends RecyclerView.Adapter<ViewHolderEditFragment> {
    @NonNull
    @Override
    public ViewHolderEditFragment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent,false);
        return new ViewHolderEditFragment(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEditFragment holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
