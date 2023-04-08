package com.example.erp.mail.received;

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

public class ReceivedRVAdapter extends RecyclerView.Adapter<ReceivedRVAdapter.ViewHolder> {

    private final ArrayList<Received> receivedArrayList;
    private final Context context;

    //Constructor:
    public ReceivedRVAdapter(ArrayList<Received> receivedArrayList, Context context) {
        this.receivedArrayList = receivedArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceivedRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received, parent, false);
        return new ReceivedRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceivedRVAdapter.ViewHolder holder, int position) {

        Received received = receivedArrayList.get(position);
        holder.subjectTV.setText(received.getSubject());
        holder.senderTV.setText(received.getSender());
        holder.bodyTV.setText(received.getBody());

        holder.itemView.setOnClickListener(v -> {

            // On below line we are calling an intent.
            Intent i = new Intent(context, UpdateReceivedActivity.class);

            // Below we are passing all our values.
            i.putExtra("received_subject", received.getSubject());
            i.putExtra("received_sender", received.getSender());
            i.putExtra("received_body", received.getBody());

            // Starting our activity.
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return receivedArrayList.size();
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
