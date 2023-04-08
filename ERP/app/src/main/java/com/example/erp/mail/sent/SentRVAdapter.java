package com.example.erp.mail.sent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.erp.R;

import java.util.ArrayList;

public class SentRVAdapter extends RecyclerView.Adapter<SentRVAdapter.ViewHolder> {

    private final ArrayList<Sent> sentArrayList;
    private final Context context;

    //Constructor:
    public SentRVAdapter(ArrayList<Sent> sentArrayList, Context context) {
        this.sentArrayList = sentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SentRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent, parent, false);
        return new SentRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentRVAdapter.ViewHolder holder, int position) {

        Sent sent = sentArrayList.get(position);
        holder.subjectTV.setText(sent.getSubject());
        holder.senderTV.setText(sent.getReceiver());
        holder.bodyTV.setText(sent.getBody());

        holder.itemView.setOnClickListener(v -> {

            // On below line we are calling an intent.
            Intent i = new Intent(context, UpdateSentActivity.class);

            // Below we are passing all our values.
            i.putExtra("sent_subject", sent.getSubject());
            i.putExtra("sent_receiver", sent.getReceiver());
            i.putExtra("sent_body", sent.getBody());

            // Starting our activity.
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return sentArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView subjectTV;
        private final TextView senderTV;
        private final TextView bodyTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            subjectTV = itemView.findViewById(R.id.subjectTV);
            senderTV = itemView.findViewById(R.id.senderTV);
            bodyTV = itemView.findViewById(R.id.bodyTV);
        }
    }

}
