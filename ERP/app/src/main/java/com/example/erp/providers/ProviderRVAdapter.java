package com.example.erp.providers;

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

public class ProviderRVAdapter extends RecyclerView.Adapter<ProviderRVAdapter.ViewHolder> {


    private final ArrayList<Provider> providerArrayList;
    private final Context context;

    //Constructor:
    public ProviderRVAdapter(ArrayList<Provider> providerArrayList, Context context) {
        this.providerArrayList = providerArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider, parent, false);
        return new ProviderRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderRVAdapter.ViewHolder holder, int position) {

        Provider provider = providerArrayList.get(position);
        holder.nameProviderTV.setText(provider.getName());
        holder.addressProviderTV.setText(provider.getAddress());
        holder.emailProviderTV.setText(provider.getEmail());
        holder.sectorProviderTV.setText(provider.getSector());

        holder.itemView.setOnClickListener(v -> {

            // On below line we are calling an intent.
            Intent i = new Intent(context, UpdateProviderActivity.class);

            // Below we are passing all our values.
            i.putExtra("provider_name", provider.getName());
            i.putExtra("provider_address", provider.getAddress());
            i.putExtra("provider_email", provider.getEmail());
            i.putExtra("provider_sector", provider.getSector());

            // Starting our activity.
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return providerArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView nameProviderTV;
        private final TextView addressProviderTV;
        private final TextView emailProviderTV;
        private final TextView sectorProviderTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            nameProviderTV = itemView.findViewById(R.id.nameTV);
            addressProviderTV = itemView.findViewById(R.id.addressTV);
            emailProviderTV = itemView.findViewById(R.id.emailTV);
            sectorProviderTV = itemView.findViewById(R.id.sectorTV);
        }
    }

}
