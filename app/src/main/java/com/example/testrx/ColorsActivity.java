package com.example.testrx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ColorsActivity extends AppCompatActivity {

    RecyclerView colorListView;
    SimpleStringAdapter simpleStringAdapter;
    Disposable colorDisposable;
    private RestClient restClient; //new

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        restClient = new RestClient(this); //new
        configureLayout();
        createObservable();
    }

    private void createObservable() {
        Observable<List<String>> colorObservable = Observable.just(restClient.getFavoriteColors());
        colorDisposable = colorObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(colors -> simpleStringAdapter.setStrings(colors));
    }

    private void configureLayout() {
        setContentView(R.layout.activity_colors);
        colorListView = (RecyclerView) findViewById(R.id.color_list);
        colorListView.setLayoutManager(new LinearLayoutManager(this));
        simpleStringAdapter = new SimpleStringAdapter(this);
        colorListView.setAdapter(simpleStringAdapter);
    }

    // see RestClient
    /*private static List<String> getColorList() {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("red");
        colors.add("green");
        colors.add("blue");
        colors.add("pink");
        colors.add("brown");
        return colors;
    }*/

    @Override
    protected void onStop() {
        super.onStop();
        if (colorDisposable != null && !colorDisposable.isDisposed()) {
            colorDisposable.dispose();
        }
    }
}
