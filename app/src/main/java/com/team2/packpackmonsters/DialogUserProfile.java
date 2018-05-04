package com.team2.packpackmonsters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.team2.packpackmonsters.data.UserProfileDbHelper;

/**
 * Created by Owner on 4/21/2018.
 */

public class DialogUserProfile extends AppCompatDialogFragment {
    private EditText editTextUsername;
    private DialogUserProfileListener listener;
    private UserProfileDbHelper MDbHelper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_userprofile, null);

        builder.setView(view)
                .setTitle("Welcome")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name1 = "Player 1";
                        String username = name1.toString();
                        editTextUsername.setText(username);
                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String username = editTextUsername.getText().toString();
                        listener.applyTexts(username);
                    }
                });

        editTextUsername = view.findViewById(R.id.edit_username);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (DialogUserProfileListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "Must implement DialogUserProfleListener");
        }

    }

    public interface DialogUserProfileListener {
        void applyTexts(String username);
    }

}
