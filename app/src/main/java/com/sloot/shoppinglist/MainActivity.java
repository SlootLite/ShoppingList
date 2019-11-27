package com.sloot.shoppinglist;

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

import com.sloot.shoppingbll.ItemShopService;
import com.sloot.shoppingdal.ItemShop;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ItemShopService itemShopService = new ItemShopService();
    ShoppingListAdapter shoppingAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

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

            itemShopService.addItem(new ItemShop(shoppingAdapter.getCount(), text.trim(), checkBuy));

            shoppingAdapter.notifyDataSetChanged();
        }
    }
}
