package com.sloot.shoppinglist;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sloot.shoppingbll.ItemShopService;
import com.sloot.shoppingdal.ItemShop;

public class ShoppingListAdapter extends BaseAdapter { // Адаптер. Штука которая управляет списком
    private LayoutInflater lInflater;
    private ItemShopService itemShopService;

    ShoppingListAdapter(Context context, ItemShopService itemShopService) {
        this.itemShopService = itemShopService;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return itemShopService.getItems().size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return itemShopService.getItems().get(position);
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

        ItemShop item = getItemShop(position); // получаем текущий элемент данных списка

        initTextView(view, position, item);

        initCheckbox(view, position, item);

        initButtonDelete(view, position);

        return view;
    }

    private void initTextView(View view, int position, ItemShop item) {
        final TextView nameText = view.findViewById(R.id.name); // возьмем текстовое представление
        nameText.setText(item.getName()); // установим текст
        nameText.setTag(position);
        nameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemShopService.toggleBuyItem((int) v.getTag()); // изменим данные (тип купили кароч)
                notifyDataSetChanged(); // скажем что данные изменились, чтобы список перерисовался
            }
        });

        if(item.isBuy())
            nameText.setPaintFlags(nameText.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG); // если отмечено, то зачеркнем
        else
            nameText.setPaintFlags(nameText.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG)); // если не отмечено, то уберем зачеркивание
    }

    private void initCheckbox(View view, int position, ItemShop item) {
        CheckBox cbBuy = view.findViewById(R.id.checkBuy); // возьмем чекбокс
        cbBuy.setChecked(item.isBuy());
        cbBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemShopService.toggleBuyItem((int) v.getTag()); // изменим данные (тип купили кароч)
                notifyDataSetChanged(); // скажем что данные изменились, чтобы список перерисовался
            }
        });

        cbBuy.setTag(position); // запомним позицию (чтобы потом можно было понять какой по счету элемент списка)
    }

    private void initButtonDelete(View view, int position) {
        Button btnDelete = view.findViewById(R.id.delete); // кнопка удаления
        btnDelete.setTag(position);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                itemShopService.removeItem(itemShopService.getItem(position)); // удалим элемент из списка
                notifyDataSetChanged(); // скажем что данные изменились, чтобы список перерисовался
            }
        });
    }

    // товар по позиции
    private ItemShop getItemShop(int position) {
        return ((ItemShop) getItem(position));
    }

}
