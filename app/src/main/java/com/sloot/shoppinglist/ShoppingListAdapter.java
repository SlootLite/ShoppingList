package com.sloot.shoppinglist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.sloot.shoppingdal.ItemShop;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter { // Адаптер. Штука которая управляет списком
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<ItemShop> objects; // массив объектов, которые лежат в списке

    ShoppingListAdapter(Context context, ArrayList<ItemShop> items) {
        ctx = context;
        objects = items;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // вызывается для каждого элемента списка
        View view = convertView;
        if (view == null) view = lInflater.inflate(R.layout.item, parent, false);

        ItemShop p = getItemShop(position); // получаем текущий элемент данных списка

        final TextView nameText = view.findViewById(R.id.name); // возьмем текстовое представление
        nameText.setText(p.name); // установим текст

        CheckBox cbBuy = view.findViewById(R.id.checkBuy); // возьмем чекбокс
        cbBuy.setOnCheckedChangeListener(new OnCheckedChangeListener() { // событие, случится после клика по чекбоксу
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItemShop((Integer) buttonView.getTag()).buy = isChecked; // изменим данные (тип купили кароч)

                if(isChecked)
                    nameText.setPaintFlags(nameText.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG); // если отмечено, то зачеркнем
                else
                    nameText.setPaintFlags(nameText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG)); // если не отмечено, то уберем зачеркивание
            }
        });
        cbBuy.setTag(position); // запомним позицию (чтобы потом можно было понять какой по счету элемент списка)
        cbBuy.setChecked(p.buy);

        Button btnDelete = view.findViewById(R.id.delete); // кнопка удаления
        btnDelete.setTag(position);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                objects.remove(position); // удалим элемент из списка
                notifyDataSetChanged(); // скажем что данные изменились, чтобы список перерисовался
            }
        });

        return view;
    }

    // товар по позиции
    ItemShop getItemShop(int position) {
        return ((ItemShop) getItem(position));
    }

}
