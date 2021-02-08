package com.example.taskreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    ArrayList<Task> taskArrayList;
    OnTaskClickListner mOnTaskClickListner;

    public TaskAdapter(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.taski_tem,null);

        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

        Task task = taskArrayList.get(position);
        holder.imageView.setImageResource(task.getImageId());
        holder.titleTv.setText(task.TaskName);
        holder.desTv.setText(task.TaskDescription);

    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView titleTv,desTv;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTv = itemView.findViewById(R.id.textViewTitle);
            desTv = itemView.findViewById(R.id.textViewDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnTaskClickListner !=null){
                        mOnTaskClickListner.onTaskClicked(TaskAdapter.this,taskArrayList.get(getAdapterPosition()),getAdapterPosition(),itemView);
                    }
                }
            });
        }
    }

    public interface OnTaskClickListner{
        public void onTaskClicked(TaskAdapter taskAdapter, Task task, int position, View view);
    }

    public void setOnTaskClickListner(OnTaskClickListner onTaskClickListner){
        this.mOnTaskClickListner = onTaskClickListner;
    }

}
