package com.example.fragmentapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewHero;
    private ArrayList<ModelHero> heroes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewHero = findViewById(R.id.recycle_hero);
        recyclerViewHero.setHasFixedSize(true);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        heroes.addAll(DataHero.getListDetail());
        showRecycler();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_details:
                            selectedFragment = new DetailsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    private void showRecycler() {
        recyclerViewHero.setLayoutManager(new LinearLayoutManager(this));
        AdapterHero adapterHero = new AdapterHero(heroes);
        adapterHero.setListHero(heroes);
        recyclerViewHero.setAdapter(adapterHero);

        adapterHero.setOnItemClickCallback(new AdapterHero.OnItemClickCallback() {
            @Override
            public void onItemClicked(ModelHero hero) {
                Toast.makeText(MainActivity.this, "Memilih " + hero.getHeroName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_DATA, (Parcelable) hero);
                startActivity(intent);
            }
        });
    }
}
