package com.example.fragmentapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_DATA = "extra_data";
    private ImageView adivHeroPhoto;
    private TextView adtvHeroName, adtvHeroDesc;
    private Button adbtnShareHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        adivHeroPhoto   = findViewById(R.id.adiv_hero_image);
        adtvHeroName    = findViewById(R.id.adtv_hero_name);
        adtvHeroDesc    = findViewById(R.id.adtv_hero_desc);
        adbtnShareHero  = findViewById(R.id.adbtn_share_hero);

        ModelHero hero = getIntent().getParcelableExtra(EXTRA_DATA);
        int image       = hero.getHeroImage();
        String name     = hero.getHeroName();
        final String desc     = hero.getHeroDesc();

        if (hero != null) {
            Glide.with(this).load(image).into(adivHeroPhoto);
            adtvHeroName.setText(name);
            adtvHeroDesc.setText(desc);
        }

        adbtnShareHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, desc);
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, null);
                startActivity(shareIntent);
            }
        });
    }
}
