package com.example.erp.client;

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

public class ClientRVAdapter extends RecyclerView.Adapter<ClientRVAdapter.ViewHolder> {

    private final ArrayList<Client> clientArrayList;
    private final Context context;

    //Constructor:
    public ClientRVAdapter(ArrayList<Client> clientArrayList, Context context) {
        this.clientArrayList = clientArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientRVAdapter.ViewHolder holder, int position) {

        Client client = clientArrayList.get(position);
        holder.nameTV.setText(client.getName());
        holder.addressTV.setText(client.getAddress());
        holder.emailTV.setText(client.getEmail());
        holder.sectorTV.setText(client.getSector());

        holder.itemView.setOnClickListener(v -> {

            // On below line we are calling an intent.
            Intent i = new Intent(context, UpdateClientActivity.class);

            // Below we are passing all our values.
            i.putExtra("client_name", client.getName());
            i.putExtra("client_address", client.getAddress());
            i.putExtra("client_email", client.getEmail());
            i.putExtra("client_sector", client.getSector());

            // Starting our activity.
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return clientArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView nameTV;
        private final TextView addressTV;
        private final TextView emailTV;
        private final TextView sectorTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            nameTV = itemView.findViewById(R.id.nameTV);
            addressTV = itemView.findViewById(R.id.addressTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            sectorTV = itemView.findViewById(R.id.sectorTV);
        }
    }
}
