package pl.kate.cardb.dao;

import pl.kate.cardb.model.Car;

import java.util.List;

public interface CarDao {

    void saveCar(long id, String mark, String model, String color, int productionYear);

    List<Car> findAll();

    Car getOne(long id);

    List<Car> findCarsBetweenYears(int fromYear, int toYear);

    void updateCar(Car car);

    void deleteCar(long id);
}
