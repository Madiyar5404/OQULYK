package com.example.oqulyk;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.oqulyk.R;

public class MyDialog extends DialogFragment {
    public static final String OQULYK_ADD_DIALOG="addOqulyk";
    public static final String OQULYK_UPDATE_DIALOG = "updateOqulyk";
    private OnClickListener listener;
    private int roll;
    private String name;

    public MyDialog(int roll, String name) {
        this.roll=roll;
        this.name=name;
    }

    public MyDialog() {

    }

    public interface OnClickListener{
        void onClick(String text1,String text2);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog=null;
        if (getTag().equals(OQULYK_ADD_DIALOG))dialog=getAddOqulykDialog();
        if (getTag().equals(OQULYK_UPDATE_DIALOG))dialog=getUpdateOqulykDialog();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
      }

    private Dialog getUpdateOqulykDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Oqulyqty özgertu");

        EditText sany_edt=view.findViewById(R.id.est01);
        EditText name_edt=view.findViewById(R.id.edt02);

        sany_edt.setHint("Kıtap sanyn jazyñyz");
        name_edt.setHint("Kıtap aty");


        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);
        add.setText("BASU");
        sany_edt.setText(roll+"");
        sany_edt.setEnabled(false);
        name_edt.setText(name);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String sany=sany_edt.getText().toString();
            String name=name_edt.getText().toString();
            listener.onClick(sany,name);
            dismiss();
        });
        return builder.create();

    }

    private Dialog getAddOqulykDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        View view= LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
        builder.setView(view);

        TextView title=view.findViewById(R.id.titleDialog);
        title.setText("Oqulyğymdy engızu");

        EditText sany_edt=view.findViewById(R.id.est01);
        EditText name_edt=view.findViewById(R.id.edt02);

        sany_edt.setHint("Kıtap sanyn jazyñyz");
        name_edt.setHint("Kıtap aty");

        Button cancel=view.findViewById(R.id.cancel_btn);
        Button add=view.findViewById(R.id.add_btn);

        cancel.setOnClickListener(v -> dismiss());
        add.setOnClickListener(v -> {
            String sany=sany_edt.getText().toString();
            String name=name_edt.getText().toString();
            sany_edt.setText(String.valueOf(Integer.parseInt(sany)+1));
            name_edt.setText("");
            listener.onClick(sany,name);
        });
        return builder.create();
    }

    }
