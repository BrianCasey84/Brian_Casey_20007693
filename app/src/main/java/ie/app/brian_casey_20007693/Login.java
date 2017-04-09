package ie.app.brian_casey_20007693;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Brian Casey 2017
 */

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button login, register;
    private EditText etEmail, etPass;
    private DbHelper db;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //initialise buttons
        db = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        // If user already logged in start Main Activity
        if(session.loggedin()){
            startActivity(new Intent(Login.this,MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //Login user when clicked
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:// When clicked bring user to register
                startActivity(new Intent(Login.this,Register.class));
                break;
            default:

        }
    }

    private void login(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        // if email and password found start MainActivity
        if(db.getUser(email,pass)){
            session.setLoggedin(true);
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }else{ //If not found print out wrong email/password
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }
}