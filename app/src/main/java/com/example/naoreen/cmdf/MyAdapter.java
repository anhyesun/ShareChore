package com.example.naoreen.cmdf;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private String[] mDataset;
    //private Context context;

    @BindView(R.id.task_description) TextView tv;


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        MyViewHolder(LinearLayout v){
            super(v);
            textView = v.findViewById(R.id.task_description);
        }
    }

    public MyAdapter(String[] myDataset) {
        //context = ctx;
        mDataset = myDataset;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout lin = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_cell, parent, false);

        MyViewHolder vh = new MyViewHolder(lin);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.setText(mDataset[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
