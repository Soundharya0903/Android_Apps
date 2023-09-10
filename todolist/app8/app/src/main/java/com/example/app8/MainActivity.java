package com.example.app8;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    ListView l;
    TextView t1;
    EditText e;
    String res="";
    ArrayList<String> a=new ArrayList<>();
    boolean[] checkedItems;
    String[] b;
    ArrayList<Integer> muser=new ArrayList<>();
    String s="";
    ArrayAdapter<String> arr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        a.add("Wake up early");
        a.add("Exercise");
        a.add("Brushing");
        l = findViewById(R.id.list);

        arr
                = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                a);
        l.setAdapter(arr);
        b=getResources().getStringArray(R.array.todo);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                s=(String)adapterView.getItemAtPosition(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        if (item.getTitle().equals("Add")) {
            AlertDialog.Builder m=new AlertDialog.Builder(MainActivity.this);
            e= new EditText(MainActivity.this);
            m.setTitle("Choose items to delete");
            m.setMessage("Enter the option");
            m.setView(e);
            m.setPositiveButton("Yes Option", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    //What ever you want to do with the value
                    res+= e.getText().toString();
                    a.add(res);
                    arr.notifyDataSetChanged();
                }
            });
            m.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            m.show();

        } else if (item.getTitle().equals( "Delete")) {
            AlertDialog.Builder m=new AlertDialog.Builder(MainActivity.this);
            m.setTitle("Choose items to delete");
            m.setMultiChoiceItems(b, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean boo) {
                    if(boo){
                        if(!muser.contains(i)){
                            muser.add(i);
                        }else{
                            muser.remove(i);
                        }
                    }
                }
            });
            m.setCancelable(false);
            m.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                String c="";
                @Override

                public void onClick(DialogInterface dialogInterface, int i) {
                    for(int j=0;j<muser.size();j++){
                        c=a.get(muser.get(j));
                        a.remove(c);
                        arr.notifyDataSetChanged();
                    }
                }
            });
            m.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // what ever you want to do with No option.
                }
            });

            m.show();
        }
        return true;
    }
}