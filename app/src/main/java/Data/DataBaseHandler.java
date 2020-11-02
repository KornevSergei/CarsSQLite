package Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import Utils.Util;

//класс для управления базой данных и наследуемся

public class DataBaseHandler extends SQLiteOpenHelper {

    //передаём параметры базы данных строк
    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }


    //метод для создания базы данных
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Использем язык SQL, язык струкутированых запросов
        //создаём таблицу таблицу, задаём название и заполняем колонки
        //для айди уникальный ключ, к которму обращаемся
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";


        db.execSQL(CREATE_CARS_TABLE);
    }

    //метод для обновления базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //удаляем старую таблицу
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        //создаём новую вызывая метод
        onCreate(db);

    }
}
