package com.cvcompany.vo.myapplication.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cvcompany.vo.myapplication.Module.TiengAnh;
import com.cvcompany.vo.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vinh on 9/1/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<TiengAnh> list;
    private Context context;

    public MyAdapter(Context context, List<TiengAnh> list){
       this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TiengAnh tiengAnh = list.get(position);
        holder.txt.setText(""+tiengAnh.getTen());
        Picasso.with(context).load(tiengAnh.getHinhAnh()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.img)
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
