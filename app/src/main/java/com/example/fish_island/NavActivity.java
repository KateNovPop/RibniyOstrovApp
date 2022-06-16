package com.example.fish_island;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fish_island.model.AuthToken;
import com.example.fish_island.model.BagItem;

import java.util.ArrayList;
import java.util.List;

public class NavActivity extends AppCompatActivity {

    Intent intent;
    TextView regView;
    ImageView regImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        //ненужно
        regView = (TextView) findViewById(R.id.regView);
        regImage = (ImageView) findViewById(R.id.regImage);
        if(AuthToken.Token.isEmpty()){
            regView.setVisibility(View.VISIBLE);
            regImage.setVisibility(View.VISIBLE);
        }
        else{
            regView.setVisibility(View.GONE);
            regImage.setVisibility(View.GONE);
        }
    }

    //Акции
    public void openOfferClick(View view){
        if (view.getId() == R.id.offerView) {
            intent = new Intent("fishIsland.intent.action.plug");
            startActivity(intent);
        }
    }

    //Помощь
    public void openNoteClick(View view){
        if (view.getId() == R.id.noteView) {
            intent = new Intent("fishIsland.intent.action.plug");
            startActivity(intent);
        }
    }

    //Регистрация
    public void openRegClick(View view){
        if (view.getId() == R.id.regView) {
            intent = new Intent("fishisland.intent.action.auth");
            startActivity(intent);
        }
    }

    //Профиль
    public void openProfileClick(View view){
        if (view.getId() == R.id.profileView) {
            if(AuthToken.Token.isEmpty()){
                intent = new Intent("fishisland.intent.action.auth");
            }
            else {
                intent = new Intent("fishIsland.intent.action.profile");
            }
            startActivity(intent);
        }
    }

    //Корзина
    public void openBagClick(View view){
        if (view.getId() == R.id.bagView) {
                if(BagItem.item_id.isEmpty()){
                    intent = new Intent("fishIsland.intent.action.emptyBag");
                }
                else {
                    intent = new Intent("fishIsland.intent.action.bag");
                }
                startActivity(intent);
            }
    }


}