package com.sloot.shoppingdal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    private Cursor getAllEntries(){
        String[] columns = new String[] {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_BUY};
        return  database.query(DatabaseHelper.TABLE, columns, null, null, null, null, null);
    }

    public ArrayList<ItemShop> getItems(){
        ArrayList<ItemShop> items = new ArrayList<>();
        Cursor cursor = getAllEntries();
        if(cursor.moveToFirst()){
            do{
                long id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_BUY));
                items.add(new ItemShop(id, name, year > 0));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return  items;
    }

    public long getCount(){
        return DatabaseUtils.queryNumEntries(database, DatabaseHelper.TABLE);
    }

    public ItemShop getItem(int id){
        ItemShop item = new ItemShop(0, "", false);
        String query = String.format("SELECT * FROM %s WHERE %s=? ORDER BY %s",DatabaseHelper.TABLE, DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_ID);
        Cursor cursor = database.rawQuery(query, new String[]{ String.valueOf(id)});
        if(cursor.moveToFirst()){
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            int year = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_BUY));
            item = new ItemShop(id, name, year > 0);
        }
        cursor.close();
        return  item;
    }

    public long insert(ItemShop item){
        ContentValues cv = new ContentValues();

        cv.put(DatabaseHelper.COLUMN_NAME, item.name);
        cv.put(DatabaseHelper.COLUMN_BUY, item.buy ? 1 : 0);

        return  database.insert(DatabaseHelper.TABLE, null, cv);
    }

    public long delete(long id){

        String whereClause = DatabaseHelper.COLUMN_ID+" = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return database.delete(DatabaseHelper.TABLE, whereClause, whereArgs);
    }

    public long update(ItemShop item){

        String whereClause = DatabaseHelper.COLUMN_ID + "=" + item.id;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_BUY, item.buy ? 1 : 0);
        return database.update(DatabaseHelper.TABLE, cv, whereClause, null);
    }
}