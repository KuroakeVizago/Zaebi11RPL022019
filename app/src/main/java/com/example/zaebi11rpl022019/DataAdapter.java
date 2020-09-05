package com.example.zaebi11rpl022019;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {

    public List<Model> dataList;
    public List<Model> dataListFull;
    OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DataAdapter(List<Model> dataList, OnItemClickListener onItemClickListener) {
        this.dataList = dataList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_data_adapter, parent, false);
        return new UserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, final int position) {
        holder.tv_listTitle.setText(dataList.get(position).getOriginal_title());
        holder.tv_listDescription.setText(dataList.get(position).getOverview());
        holder.img_listImage.setImageResource(R.drawable.ic_launcher_foreground);
        Picasso.get().load(dataList.get(position).getPoster_path())
        .placeholder(R.mipmap.icon)
        .into(holder.img_listImage);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_listTitle;
        private TextView tv_listDescription;
        private ImageView img_listImage;

        public UserViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            tv_listTitle = itemView.findViewById(R.id.tvname);
            tv_listDescription = itemView.findViewById(R.id.tvdesc);
            img_listImage = itemView.findViewById(R.id.ivprofile);
        }
    }
}