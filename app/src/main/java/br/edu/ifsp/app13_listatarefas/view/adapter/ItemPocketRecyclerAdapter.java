package br.edu.ifsp.app13_listatarefas.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.ifsp.app13_listatarefas.R;
import br.edu.ifsp.app13_listatarefas.model.entities.Task;
import br.edu.ifsp.app13_listatarefas.mvp.MainMVP;
import br.edu.ifsp.app13_listatarefas.view.RecyclerViewItemClickListener;

public class ItemPocketRecyclerAdapter extends RecyclerView.Adapter<ItemPocketRecyclerAdapter.ViewHolder> {
    private Context context;
    private MainMVP.Presenter presenter;
    private List<Task> data;
    private static RecyclerViewItemClickListener clickListener;

    public ItemPocketRecyclerAdapter(Context context, List<Task> data, MainMVP.Presenter presenter){
        this.context = context;
        this.presenter = presenter;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycledview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = data.get(position);

        holder.titleTextView.setText(task.getTitle());

        if(task.isImportant()){
            holder.starImageView.setColorFilter(context.getColor(R.color.RED));
        }else{
            holder.starImageView.setColorFilter(context.getColor(R.color.GRAY));
        }

        holder.starImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starClick(task);
            }
        });

        holder.pencilImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pencilClick(task);
            }
        });

        holder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void starClick(Task task){
        presenter.favoriteTask(task);
        notifyDataSetChanged();
    }

    private void pencilClick(Task task){
        presenter.openDetails(task);
        notifyDataSetChanged();
    }

    private void deleteClick(Task task){
        presenter.deleteTask(task);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titleTextView;
        public ImageView starImageView;
        public  ImageView pencilImageView;
        public ImageView deleteImageView;

        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            starImageView = itemView.findViewById(R.id.ivStar);
            pencilImageView = itemView.findViewById(R.id.ivPencil);
            deleteImageView = itemView.findViewById(R.id.ivDelete);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(clickListener != null){
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
