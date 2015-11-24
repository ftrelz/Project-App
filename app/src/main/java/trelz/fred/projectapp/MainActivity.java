package trelz.fred.projectapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFloatinActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFloatinActionButton = (FloatingActionButton) findViewById(R.id.new_project);
        mFloatinActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = TaskActivity.newIntent(MainActivity.this);
                startActivity(i);
            }
        });
    }

    /*private void initProjectNameStrings() {
        for (int i = 0; i < projectArrayList.size(); i++) {
            
        }
    }*/

    ArrayList<Project> projectArrayList;

}
