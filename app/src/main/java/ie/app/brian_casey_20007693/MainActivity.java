package ie.app.brian_casey_20007693;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import java.util.HashSet;
import java.util.Set;
import android.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;
import android.content.Context;
import android.content.SharedPreferences;
import android.app.Activity;



public class MainActivity extends AppCompatActivity {

    ArrayList<String> myList = null;
    // Adapter to show array list in the ListView
    ArrayAdapter<String> adapter = null;
    ListView lv = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myList = new ArrayList<>();
        // Constructor for ArrayAdaptor to add single item to ArrayList
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myList);
        lv = (ListView) findViewById(R.id.listView);
        // Method to show ArrayList in the ListView
        lv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.clear);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    myList.clear();
                    lv.setAdapter(adapter);

                    Snackbar.make(view, "Items Cleared", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_shop) {
            // Launch Activity
            startActivity (new Intent(this, Shop.class));
            return true;
        }
        if (id == R.id.action_report) {
            Bundle b=new Bundle();
//            String[] stringArray = myList.toArray(new String[0]);
            b.putStringArray("array1", myList.toArray(new String[0]));
            Intent i=new Intent(this, Report.class);
            i.putExtras(b);

            startActivity (i);
            return true;
        }
        if (id == R.id.action_add)
        {   // Create AlertDialog form
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add Item");
            //EditText allows a text entry
            final EditText input = new EditText(this);
            builder.setView(input);
            //Set positive button to confirm input
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Text item added to the array
                    myList.add((input.getText().toString()));
                   // Collections.sort(shoppingList);
                    lv.setAdapter(adapter);
                }
            });//Set neggative button to cancel input
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
            return true;
        }

        return super.onOptionsItemSelected(item);


    }


}
