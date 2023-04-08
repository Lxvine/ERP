package com.example.erp.accounting;

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

public class AccountingRVAdapter extends RecyclerView.Adapter<AccountingRVAdapter.ViewHolder> {


    private final ArrayList<Accounting> accountingArrayList;
    private final Context context;

    //Constructor:
    public AccountingRVAdapter(ArrayList<Accounting> accountingArrayList, Context context) {
        this.accountingArrayList = accountingArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.accounting, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.erp.accounting.AccountingRVAdapter.ViewHolder holder, int position) {

        Accounting accounting = accountingArrayList.get(position);
        holder.codeTV.setText(accounting.getCode());
        holder.descriptionTV.setText(accounting.getDescription());
        holder.debitTV.setText(accounting.getDebit());
        holder.creditTV.setText(accounting.getCredit());

        holder.itemView.setOnClickListener(v -> {

            // On below line we are calling an intent.
            Intent i = new Intent(context, UpdateAccountingActivity.class);

            // Below we are passing all our values.
            i.putExtra("accounting_code", accounting.getCode());
            i.putExtra("accounting_description", accounting.getDescription());
            i.putExtra("accounting_debit", accounting.getDebit());
            i.putExtra("accounting_credit", accounting.getCredit());

            // Starting our activity.
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return accountingArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private final TextView codeTV;
        private final TextView descriptionTV;
        private final TextView debitTV;
        private final TextView creditTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            codeTV = itemView.findViewById(R.id.codeTV);
            descriptionTV = itemView.findViewById(R.id.descriptionTV);
            debitTV = itemView.findViewById(R.id.debitTV);
            creditTV = itemView.findViewById(R.id.creditTV);
        }
    }
}
