package com.msc.mobileapps.mwanabiashara.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.msc.mobileapps.mwanabiashara.R;
import com.msc.mobileapps.mwanabiashara.db.Expense;
import com.msc.mobileapps.mwanabiashara.db.Sale;
import com.msc.mobileapps.mwanabiashara.db.viewmodel.DatabaseViewModel;
import com.msc.mobileapps.mwanabiashara.depi.Injectable;

import java.util.List;

import javax.inject.Inject;


public class TransactionListFragment extends Fragment implements Injectable {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private DatabaseViewModel databaseViewModel;
    private TransactionListFIL listFIL;
    private RecyclerView recyclerView;

    public TransactionListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseViewModel = ViewModelProviders.of(this, viewModelFactory).get(DatabaseViewModel.class);
        return inflater.inflate(R.layout.fragment_transaction_list, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView = getActivity().findViewById(R.id.recyclerView);

        if(TransactionsActivity.mode == "SALES"){
            LiveData<List<Sale>> orderItems = databaseViewModel.getSales();
            orderItems.observe(getViewLifecycleOwner(), x -> recyclerView.setAdapter(new SalesRecyclerViewAdapter(x)));
            listFIL.changeTitle("SALES");
        } else if(TransactionsActivity.mode == "DIRECT"){
            LiveData<List<Expense>> orderItems = databaseViewModel.getExpenses("DIRECT");
            orderItems.observe(getViewLifecycleOwner(), x -> recyclerView.setAdapter(new ExpensesRecyclerViewAdapter(x)));
            listFIL.changeTitle("DIRECT EXPENSES");
        } else if(TransactionsActivity.mode == "INDIRECT"){
            LiveData<List<Expense>> orderItems = databaseViewModel.getExpenses("INDIRECT");
            orderItems.observe(getViewLifecycleOwner(), x -> recyclerView.setAdapter(new ExpensesRecyclerViewAdapter(x)));
            listFIL.changeTitle("INDIRECT EXPENSES");
        }


        FloatingActionButton fab = getActivity().findViewById(R.id.add);
        fab.setOnClickListener(view -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_transactionListFragment_to_newTransactionFragment));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TransactionListFIL) {
            listFIL = (TransactionListFIL) context;
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