package com.msc.mobileapps.mwanabiashara.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.msc.mobileapps.mwanabiashara.R;
import com.msc.mobileapps.mwanabiashara.db.Expense;
import com.msc.mobileapps.mwanabiashara.db.Sale;
import com.msc.mobileapps.mwanabiashara.db.viewmodel.DatabaseViewModel;
import com.msc.mobileapps.mwanabiashara.depi.Injectable;

import java.util.Date;

import javax.inject.Inject;


public class NewTransactionFragment extends Fragment implements Injectable {
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DatabaseViewModel databaseViewModel;
    private TransactionListFragment.TransactionListFIL listFIL;
    private EditText title;
    private EditText qty;
    private EditText cost;
    private EditText comments;

    FloatingActionButton check;

    public NewTransactionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseViewModel = ViewModelProviders.of(this, viewModelFactory).get(DatabaseViewModel.class);
        return inflater.inflate(R.layout.fragment_new_transaction, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        check = getActivity().findViewById(R.id.check);

        title = getActivity().findViewById(R.id.title);
        qty = getActivity().findViewById(R.id.quantity);
        cost = getActivity().findViewById(R.id.cost);
        comments = getActivity().findViewById(R.id.comments);

        listFIL.changeTitle("New transaction");
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        String vTitle = title.getText().toString();
        String vCost = cost.getText().toString();
        String vComments = comments.getText().toString();
        String vQty = qty.getText().toString();

        if (vTitle.isEmpty() || vCost.isEmpty() || vQty.isEmpty()) {
            Toast.makeText(getContext(), "Invalid values", Toast.LENGTH_LONG).show();
        } else {
            if(TransactionsActivity.mode == "SALES"){
                Sale sale = new Sale();
                sale.title = vTitle;
                sale.amount = Float.parseFloat(vCost);
                sale.comments = vComments;
                sale.createdAt = new Date();
                sale.quantity = vQty;

                databaseViewModel.insertSale(sale);
                getActivity().onBackPressed();
            } else if(TransactionsActivity.mode == "DIRECT"){
                Expense sale = new Expense();
                sale.title = vTitle;
                sale.expenseType = "DIRECT";
                sale.amount = Float.parseFloat(vCost);
                sale.comments = vComments;
                sale.createdAt = new Date();
                sale.quantity = vQty;

                databaseViewModel.insertExpense(sale);
                getActivity().onBackPressed();
            } else if(TransactionsActivity.mode == "INDIRECT"){
                Expense sale = new Expense();
                sale.title = vTitle;
                sale.expenseType = "INDIRECT";
                sale.amount = Float.parseFloat(vCost);
                sale.comments = vComments;
                sale.createdAt = new Date();
                sale.quantity = vQty;

                databaseViewModel.insertExpense(sale);
                getActivity().onBackPressed();
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TransactionListFragment.TransactionListFIL) {
            listFIL = (TransactionListFragment.TransactionListFIL) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement TransactionListFIL");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listFIL = null;
    }

    public interface TransactionListFIL {
        void changeTitle(String title);
    }

}