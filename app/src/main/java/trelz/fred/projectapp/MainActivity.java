package trelz.fred.projectapp;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initProjectNameStrings() {
        for (int i = 0; i < projectArrayList.size(); i++) {
            
        }
    }

    ArrayList<Project> projectArrayList;

}
