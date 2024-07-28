package com.example.ebook_assignment1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "EbookAssignment.db", null, 1);
    }
        @Override
        public void onCreate (SQLiteDatabase DataBase){
            DataBase.execSQL("create Table BookInfo(BookName TEXT primary key , BookTitle TEXT,BookCategory TEXT)");
        }
    @Override
    public void onUpgrade(SQLiteDatabase DataBase, int oldVersion, int newVersion) {
        DataBase.execSQL("drop Table if exists BookInfo");
    }
    public Boolean insert(String name,String Title,String Category){
        SQLiteDatabase DataBase=this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("BookName",name);
        contentValues.put("BookTitle",Title);
        contentValues.put("BookCategory",Category);
        long result=DataBase.insert("BookInfo",null,contentValues);
        if(result==-1){
            return false;

        }else {
            return true;
        }
    }
    public Cursor getData (){
        SQLiteDatabase DataBase=this.getWritableDatabase();
        Cursor cursor=DataBase.rawQuery("Select * from BookInfo ", null);
        return cursor;
    }
}







