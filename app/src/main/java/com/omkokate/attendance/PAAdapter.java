package com.omkokate.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PAAdapter extends RecyclerView.Adapter<PAAdapter.EventViewHolder> {
    private final List<PA> PAList;

    LayoutInflater layoutInflater;

    public PAAdapter(Context applicationContext, List<PA> PAList) {
        this.layoutInflater=LayoutInflater.from(applicationContext);
        this.PAList = PAList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.custom_pa_layout, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
//        PA event = PAList.get(position);
        holder.time.setText(PAList.get(position).getTime());
        holder.subject.setText(PAList.get(position).getSubject());
        holder.presenty.setText(PAList.get(position).getPresenty());
        holder.priority.setText(PAList.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return PAList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView time, subject, presenty,priority;

        public EventViewHolder(View view) {
            super(view);
            time = view.findViewById(R.id.time);
            subject = view.findViewById(R.id.subject);
            presenty = view.findViewById(R.id.presenty);
            priority = view.findViewById(R.id.priority);
        }
    }
}
