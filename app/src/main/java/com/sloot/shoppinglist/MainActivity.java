package com.sloot.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static class Item {
        String name;
        boolean buy;
        Item(String name, boolean buy) {
            this.name = name;
            this.buy = buy;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final EditText name = findViewById(R.id.name);
        final Button add = findViewById(R.id.add);
        final ListView items = findViewById(R.id.items);
        final ItemsAdapter adapter = new ItemsAdapter();
        items.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.add(new Item(name.getText().toString(), false));
            }
        });
    }

    private class ItemsAdapter extends ArrayAdapter<Item> {

        ItemsAdapter() {
            super(MainActivity.this, R.layout.item);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final View view = getLayoutInflater().inflate(R.layout.item, null);
            final Item item = getItem(position);
            final CheckBox checkBuy = view.findViewById(R.id.checkBuy);
            checkBuy.setTag(position);

            ((TextView) view.findViewById(R.id.name)).setText(item.name);
            checkBuy.setChecked(item.buy);
            return view;
        }
    }
}
