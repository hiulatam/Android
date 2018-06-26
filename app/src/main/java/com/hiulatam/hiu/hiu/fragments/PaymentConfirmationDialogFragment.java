package com.hiulatam.hiu.hiu.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.hiulatam.hiu.hiu.R;

/**
 * Created by:  Shiny solutions
 * Created on:  5/12/18.
 */

public class PaymentConfirmationDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.todoDialogLight);
        builder.setMessage(String.format(getString(R.string.payment_confirmation), "$55"));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }
}
