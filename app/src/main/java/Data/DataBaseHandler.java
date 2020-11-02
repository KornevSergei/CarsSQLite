package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Car;
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

    //делаём возможность для функционала таблицы

    //метод для добавления в таблицу
    public void addCar(Car car) {
        //Получаем обьект базы данных, читаем его
        SQLiteDatabase db = this.getReadableDatabase();
        //взаимодействуем с базой данных черз ключ значение
        //айди добавляються автоматически
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());
        //вставляем в базу данных
        db.insert(Util.TABLE_NAME, null, contentValues);
        //акрываем соединения с базой данных
        db.close();
    }


    //метод для извлечения записи по айди
    public Car getCar(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //извлекаем записи из всех столбцов, извлекаем только по айди, и пишем нулл для других параметров сортировки
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE},
                Util.KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null, null);

        //проверяем что поиск не пустой, если ок - то запускаем
        if (cursor != null) {
            cursor.moveToFirst();
        }
        //возвращаем обьект с извлеченными данными из курсора, передаем индексы столбцов
        Car car = new Car(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

        return car;
    }


    //метод который получает все записи из таблицы
    public List<Car> getAllCars() {
        //считываем записи
        SQLiteDatabase db = this.getReadableDatabase();
        //создаём список всех записей
        List<Car> carsList = new ArrayList<>();
        //строка для СКЛ кода
        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAllCars, null);
        //проверяем на содержания хоть каких то записей
        if (cursor.moveToFirst()) {
            //выполняем цикл пока цикл содержит следующий элемент создаём обьект и устанавливаем данные по колонкам
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));

                //добавляем обьект с параметрами в лист
                carsList.add(car);
            } while (cursor.moveToNext());
        }
        //возвращаем заполненный лист
        return carsList;
    }
}
