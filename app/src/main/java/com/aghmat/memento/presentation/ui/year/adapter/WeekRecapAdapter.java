package com.aghmat.memento.presentation.ui.year.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aghmat.memento.databinding.ItemWeekRecapBinding;
import com.aghmat.memento.domain.enums.WeekType;
import com.aghmat.memento.domain.model.WeekRecap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class WeekRecapAdapter extends RecyclerView.Adapter<WeekRecapAdapter.CustomViewHolder> {

    private final List<WeekRecap> weekRecaps = new ArrayList<>();
    private final Consumer<WeekRecap> onClickListener;

    public WeekRecapAdapter(Consumer<WeekRecap> onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setWeekRecaps(List<WeekRecap> weekRecaps) {
        this.weekRecaps.clear();
        this.weekRecaps.addAll(weekRecaps);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeekRecapBinding binding = ItemWeekRecapBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CustomViewHolder(binding.getRoot(), onClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.bind(weekRecaps.get(position));
    }

    @Override
    public int getItemCount() {
        return weekRecaps.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {

        private final ItemWeekRecapBinding binding;
        private Consumer<WeekRecap> onClickListener;

        CustomViewHolder(@NonNull View itemView, Consumer<WeekRecap> onClickListener) {
            super(itemView);
            binding = ItemWeekRecapBinding.bind(itemView);
            this.onClickListener = onClickListener;
        }

        public void bind(WeekRecap weekRecap) {
            binding.textView.setText(weekRecap.getWeekNumber() + "\n" + weekRecap.getStartDate() + " \n " + weekRecap.getEndDate());
            binding.getRoot().setAlpha(WeekType.FUTURE.equals(weekRecap.getWeekType()) ? 0.5F : 1F);
            binding.getRoot().setOnClickListener(v -> onClickListener.accept(weekRecap));
        }
    }
}
