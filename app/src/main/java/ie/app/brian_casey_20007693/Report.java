package ie.app.brian_casey_20007693;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Report extends AppCompatActivity {

//    Bundle b=this.getIntent().getExtras();
//    String[] itemList=b.getStringArray("itemList");
    private ArrayList itemList;
    // Adapter to show array list in the ListView
    private ArrayAdapter<String> adapter;
    private ListView lv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get a reference to the intent used to launch this Activity
        Intent intent = getIntent();
        // Extract the ArrayList from the intent
        itemList = intent.getStringArrayListExtra("itemList");
        // Constructor for ArrayAdaptor to add single item to ArrayList
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList);
        lv = (ListView) findViewById(R.id.LVreport);
        // Method to show ArrayList in the ListView
        lv.setAdapter(adapter);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list) {
            // Launch Activity
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
