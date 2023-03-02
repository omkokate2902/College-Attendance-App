package com.omkokate.attendance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    LayoutInflater inflater;
    List<Attendance> attend;

    public Adapter(Context ctx, List<Attendance> attend){
        this.inflater = LayoutInflater.from(ctx);
        this.attend = attend;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_attendance_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.subname.setText(attend.get(position).getSubname());
        holder.avg.setText(attend.get(position).getAvg());
        holder.avgth.setText(attend.get(position).getAvgth());
        holder.avgpr.setText(attend.get(position).getAvgpr());
        holder.avg3d.setText(attend.get(position).getAvg3d());
        holder.avg7d.setText(attend.get(position).getAvg7d());
        holder.avgmonth.setText(attend.get(position).getAvgmonth());

        holder.avgPB.setProgress(attend.get(position).getAvgPB());
        holder.avgthPB.setProgress(attend.get(position).getAvgthPB());
        holder.avgprPB.setProgress(attend.get(position).getAvgprPB());
        holder.avg3dPB.setProgress(attend.get(position).getAvg3dPB());
        holder.avg7dPB.setProgress(attend.get(position).getAvg7dPB());
        holder.avgmonthPB.setProgress(attend.get(position).getAvg7dPB());
    }

    @Override
    public int getItemCount() {
        return attend.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView subname,avg,avgth,avgpr,avg3d,avg7d,avgmonth;
        ProgressBar avgPB,avgthPB,avgprPB,avg3dPB,avg7dPB,avgmonthPB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subname = itemView.findViewById(R.id.subname);
            avg = itemView.findViewById(R.id.avg);
            avgth = itemView.findViewById(R.id.avgth);
            avgpr = itemView.findViewById(R.id.avgpr);
            avg3d = itemView.findViewById(R.id.avg3d);
            avg7d = itemView.findViewById(R.id.avg7d);
            avgmonth = itemView.findViewById(R.id.avgmonth);

            avgPB = itemView.findViewById(R.id.avgPB);
            avgthPB = itemView.findViewById(R.id.avgthPB);
            avgprPB = itemView.findViewById(R.id.avgprPB);
            avg3dPB = itemView.findViewById(R.id.avg3dPB);
            avg7dPB = itemView.findViewById(R.id.avg7dPB);
            avgmonthPB = itemView.findViewById(R.id.avgmonthPB);
        }
    }
}
