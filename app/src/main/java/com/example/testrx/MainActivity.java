package com.example.testrx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent i = null;
        switch (view.getId()) {
            case R.id.first:
                i = new Intent(this, RxJavaSimpleActivity.class);
                break;
            case R.id.colors:
                i = new Intent(this, ColorsActivity.class);
                break;
            case R.id.books:
                i = new Intent(this, BooksActivity.class);
                break;
            case R.id.sheduler:
                i = new Intent(this, SchedulerActivity.class);
                break;
        }
        startActivity(i);
    }
}

