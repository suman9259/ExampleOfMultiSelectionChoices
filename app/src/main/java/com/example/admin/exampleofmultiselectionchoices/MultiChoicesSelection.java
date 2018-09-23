package com.example.admin.exampleofmultiselectionchoices;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MultiChoicesSelection extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    String [] listItems;
    boolean [] checkedItem;
    ArrayList<Integer> userList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choices_selection);
        isInitView();
        textView.setOnClickListener(this);
    }
    public void isInitView(){
        textView = (TextView)findViewById(R.id.textView);
        textView.setText("click here");
        //get a list of names from string.xml
        listItems = getResources().getStringArray(R.array.item_list);
        //set number of checkBox
        checkedItem= new boolean[listItems.length];
    }

    @Override
    public void onClick(View view) {
        textView.setVisibility(View.GONE);
        //create a dialog for show the list on click of textView.
        final AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(MultiChoicesSelection.this);
        //set title of dialog
        alertDialogBuilder.setTitle("Name of groups");
        //set MultiChoiceClickListener on checkBox
        alertDialogBuilder.setMultiChoiceItems(listItems, checkedItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
             if (isChecked){
                 userList.add(position);
             }else {
                 userList.remove(Integer.valueOf(position));
             }
            }
        });

        alertDialogBuilder.setCancelable(false);
        //on click Ok Button
        alertDialogBuilder.setPositiveButton("add", new DialogInterface.OnClickListener() {
            String item = "";
            @Override
            public void onClick(DialogInterface dialogInterface, int whichItems) {
                for (int i=0; i<userList.size(); i++){
                    item= item + listItems[userList.get(i)];

                    //for "," separated
                    if (i!= userList.size()-1){
                        item = item + ",";
                    }
                }
                textView.setText(item);
                textView.setVisibility(View.VISIBLE);
            }
        });

        //onclick cancel button.
        alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                textView.setVisibility(View.VISIBLE);
                dialogInterface.dismiss();
            }
        });

        alertDialogBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for (int i=0; i<checkedItem.length;i++){
                    checkedItem[i]=false;
                    userList.clear();
                    textView.setText("Click Here");
                    textView.setVisibility(View.VISIBLE);
                }

            }
        });

        AlertDialog alertDialog= alertDialogBuilder.create();
        alertDialog.show();
    }
}
