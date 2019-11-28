package com.sloot.shoppinglist;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.sloot.shoppingbll.ItemShopService;
import com.sloot.shoppingdal.ItemShop;

public class MainActivity extends AppCompatActivity {
    ItemShopService itemShopService;
    ShoppingListAdapter shoppingAdapter;
    private AdView mAdView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        itemShopService = new ItemShopService(this);
        shoppingAdapter = new ShoppingListAdapter(this, itemShopService);

        final ListView items = findViewById(R.id.items);
        items.setAdapter(shoppingAdapter);


        final EditText name = findViewById(R.id.name);
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    addNewProduct(name.getText().toString(), false);
                    return true;
                }
                return false;
            }
        });

        final Button add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProduct(name.getText().toString(), false);
            }
        });
    }


    private void addNewProduct(String text, boolean checkBuy) {
        if(text.length() > 0) {
            ((EditText)findViewById(R.id.name)).setText("");

            itemShopService.addItem(new ItemShop(0, text.trim(), checkBuy));

            shoppingAdapter.notifyDataSetChanged();
        }
    }
}
