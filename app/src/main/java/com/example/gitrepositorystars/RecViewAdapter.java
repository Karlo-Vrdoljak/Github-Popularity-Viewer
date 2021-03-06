package com.example.gitrepositorystars;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.ViewHolder> {
    private List<Note> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    RecViewAdapter(Context context,List<Note> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note data = mData.get(position);
        holder.starLabel.setText(String.valueOf(data.getCreatedAt()));
        holder.textView.setText(String.valueOf(data.getName()));
        /*
        holder.textView.setText(String.valueOf(data.getStargazersCount()));
        new DownloadImageTask((ImageView) holder.itemView.findViewById(R.id.gitRepoImg))
                .execute(data.getGitUser().getAvatarUrl());
        holder.repoName.setText(String.valueOf(data.getFullName()));
        */
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView starLabel;
        TextView repoName;
        ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.git_repo_txt);
            this.starLabel = itemView.findViewById(R.id.label_star);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(mClickListener != null) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    Note getItem(int id) {
        return mData.get(id);
    }
    void setOnclickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

