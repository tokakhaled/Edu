package com.example.edu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.sentenceViewHolder> {

ArrayList<String>sen;
OnRecyclerViewClickListener lis;

    public RecyclerViewAdapter(ArrayList<String> sen ,OnRecyclerViewClickListener lis ) {

        this.sen = sen;
        this.lis=lis;
    }

    @NonNull
    @Override
    public sentenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=  LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item ,
                parent,false);
        sentenceViewHolder viewHolder =new sentenceViewHolder(v);
        return viewHolder;

    }
    @NonNull
    @Override
    public void onBindViewHolder(@NonNull sentenceViewHolder holder, int position) {
        String s =sen.get(position);
        holder.tv_text.setText(s);

    }

    @Override
    public int getItemCount() { return sen.size(); }

   class sentenceViewHolder extends RecyclerView.ViewHolder{
      TextView tv_text;
       public sentenceViewHolder(@NonNull View itemView) {
           super(itemView);
          tv_text=itemView.findViewById(R.id.tv_text);
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  lis.OnItemClick(tv_text.getText().toString(),getAdapterPosition());
              }
          });
       }
   }
}
