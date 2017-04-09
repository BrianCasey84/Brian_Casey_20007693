package ie.app.brian_casey_20007693;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import android.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;

import android.widget.TextView;


/**
 * Brian Casey 2017
 */

public class MainActivity extends AppCompatActivity {

   // private Button btnLogout;
    private Session session;
    ArrayAdapter<String> mAdapter;
    DbListHelper dbListHelper;
    ListView lstTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbListHelper = new DbListHelper(this);

        lstTask = (ListView)findViewById(R.id.lstTask);

        loadTaskList();

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }

    }

    private void loadTaskList() {

        ArrayList<String> taskList = dbListHelper.getTaskList();


        if(mAdapter==null){

            mAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.task_title,taskList);

            lstTask.setAdapter(mAdapter);

        }

        else{

            mAdapter.clear();

            mAdapter.addAll(taskList);

            mAdapter.notifyDataSetChanged();

        }

    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActivity.this,Login.class));
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


        if (id == R.id.action_shop) {
            // Launch Activity
            startActivity (new Intent(this, Shop.class));
            return true;
        }

        if (id == R.id.action_report) {
            // Launch Activity
            startActivity (new Intent(this, Report.class));
            return true;
        }

        if (id == R.id.action_logout) {
            // Launch Activity
            logout();
        }

        switch (item.getItemId()){

            case R.id.action_add_task:

                final EditText taskEditText = new EditText(this);

                AlertDialog dialog = new AlertDialog.Builder(this)

                        .setTitle("Add New Item")

                        .setMessage("What do you want to add next?")

                        .setView(taskEditText)

                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                            @Override

                            public void onClick(DialogInterface dialog, int which) {

                                String task = String.valueOf(taskEditText.getText());

                                dbListHelper.insertNewTask(task);

                                loadTaskList();

                            }

                        })

                        .setNegativeButton("Cancel",null)

                        .create();

                dialog.show();

                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbListHelper.deleteTask(task);
        loadTaskList();
    }







}
