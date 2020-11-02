package com.example.carssqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Data.DataBaseHandler;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //создаём базу при запуске приложения
        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        //добавляем записи в базу данных с параметрами из конструктора
        dataBaseHandler.addCar(new Car("Тайота", "30 000"));
        dataBaseHandler.addCar(new Car("Хонда", "20 000"));
        dataBaseHandler.addCar(new Car("БМВ", "50 000"));
        dataBaseHandler.addCar(new Car("КИЯ", "15 000"));

        //создаём лист из обектов вызывая метод
        List <Car> carList = dataBaseHandler.getAllCars();

        //смотрим в лог через цикл
        for (Car car: carList ){
            Log.i("Car Info: ", "ID " + car.getId() + ", name "+ car.getName() + ", price " + car.getPrice());
        }
    }
}