package net.abcdroid.kurmi.a;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AA extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_a, menu);
        return true;
    }
}
