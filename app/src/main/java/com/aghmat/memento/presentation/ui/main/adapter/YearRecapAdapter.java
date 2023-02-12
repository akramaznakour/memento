package com.aghmat.memento.presentation.ui.main.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aghmat.memento.databinding.ItemYearRecapBinding;
import com.aghmat.memento.domain.enums.AgeType;
import com.aghmat.memento.domain.model.YearRecap;

import java.util.List;
import java.util.function.Consumer;

public class YearRecapAdapter extends RecyclerView.Adapter<YearRecapAdapter.CustomViewHolder> {

    private List<YearRecap> yearRecaps;
    private final Consumer<YearRecap> clickListener;

    public YearRecapAdapter(Consumer<YearRecap> clickListener) {
        this.clickListener = clickListener;
    }

    public void setYearRecaps(List<YearRecap> yearRecaps) {
        this.yearRecaps = yearRecaps;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemYearRecapBinding binding = ItemYearRecapBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CustomViewHolder(binding.getRoot(), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(yearRecaps.get(position));
    }

    @Override
    public int getItemCount() {
        return yearRecaps.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private final ItemYearRecapBinding binding;
        private final Consumer<YearRecap> clickListener;

        CustomViewHolder(@NonNull View itemView, Consumer<YearRecap> clickListener) {
            super(itemView);
            binding = ItemYearRecapBinding.bind(itemView);
            this.clickListener = clickListener;
        }

        public void bind(YearRecap yearRecap) {
            binding.textView.setText(String.valueOf(yearRecap.getAge()));
            binding.container.setOnClickListener(v -> clickListener.accept(yearRecap));
            binding.getRoot().setAlpha(AgeType.FUTURE.equals(yearRecap.getYearType()) ? 0.5F : 1F);
        }
    }
}
