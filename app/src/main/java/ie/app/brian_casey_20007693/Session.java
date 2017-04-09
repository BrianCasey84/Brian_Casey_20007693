package ie.app.brian_casey_20007693;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Brian Casey 2017
 */
public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    //set context
    public Session(Context ctx){
        this.ctx = ctx;
        //Set prefrence name myapp and set mode to private
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }
    // When user logged in set to true
    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode",logggedin);
        //commit to prefrences
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }
}