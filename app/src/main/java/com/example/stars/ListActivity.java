package com.example.stars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.stars.adapter.StarAdapter;
import com.example.stars.beans.Star;
import com.example.stars.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private List<Star> stars;
    private StarService service;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        //insérer le code
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void init() {
        service.create(new Star("Tom Hardy", "https://static.wikia.nocookie.net/peaky-blinders/images/e/e6/Alfiepeakyblinders.jpg/revision/latest?cb=20171102190226", 3.5f));
        service.create(new Star("Al Pacino", "https://media.vanityfair.fr/photos/60d3407906b78c0fb40a797d/1:1/w_630,h_630,c_limit/slider_al_pacino_7733.jpeg", 3));
        service.create(new Star("Travis Fimmel", "https://elcomercio.pe/resizer/sXMjr2JQcqUG7wiHwFeAWcIr8F4=/1200x1200/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/HLGLGG7URZHENDLOHYHKS5XQ44.jpg", 5));
        service.create(new Star("katheryn winnick", "https://i.pinimg.com/originals/6d/58/50/6d5850fa8038e29501436f3c23a95b22.jpg", 1));
        service.create(new Star("Lena headey", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRK-kciD25G9-WGhQnduCFHSHsbPDhDBCwUDg&usqp=CAU", 5));
        service.create(new Star("Adrien brody", "https://fr.web.img4.acsta.net/pictures/21/07/13/14/43/5734555.jpg", 1));
    }
    private static final String TAG = "MyActivity";
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(query);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d(TAG, newText);
                return true;
            }
        });
        return true;
    }


}
