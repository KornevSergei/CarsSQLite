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

//        //добавляем записи в базу данных с параметрами из конструктора
//        dataBaseHandler.addCar(new Car("Тайота", "30 000"));
//        dataBaseHandler.addCar(new Car("Хонда", "20 000"));
//        dataBaseHandler.addCar(new Car("БМВ", "50 000"));
//        dataBaseHandler.addCar(new Car("КИЯ", "15 000"));

        //создаём лист из обектов вызывая метод
        List <Car> carList = dataBaseHandler.getAllCars();

//        //смотрим в лог через цикл
//        for (Car car: carList ){
//            Log.d("Car Info: ", "ID " + car.getId() + ", name "+ car.getName() + ", price " + car.getPrice());
//        }





//        //получаем информацию об одной машине по айди и выводим в лог
//        Car car = dataBaseHandler.getCar(1);
////        Log.d("Car Info: ", "ID " + car.getId() + ", name "+ car.getName() + ", price " + car.getPrice());
//
//        //устанавливаем новые поля для обьекта и вызываем метод обновления
//        car.setName("Тесла");
//        car.setPrice("100 000");
//
//        //вызываем метод обновления
//        dataBaseHandler.updateCar(car);
//
//        Log.d("Car Info: ", "ID " + car.getId() + ", name "+ car.getName() + ", price " + car.getPrice());





//        //извлекаем элемент под айди
//        Car deletingCar = dataBaseHandler.getCar(7);
//        //удаляем элемент вызывая метод
//        dataBaseHandler.deleteCar(deletingCar);
        //смотрим в лог
        for (Car car: carList ){
            Log.d("Car Info: ", "ID " + car.getId() + ", name "+ car.getName() + ", price " + car.getPrice());
        }

        //узнаем колличество вызывая метод
        Log.d("CarCount: ", String.valueOf(dataBaseHandler.getCarsCount()));
    }
}