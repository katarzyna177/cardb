package pl.kate.cardb.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pl.kate.cardb.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(long id, String mark, String model, String color, int production_year) {
        Car car = new Car(id, mark, model, color, production_year);
        String sql = "INSERT INTO cars VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, car.getId(), car.getMark(), car.getModel(), car.getColor(), car.getProductionYear());
    }

    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return findAllCarList(carList, maps);
    }

    private List<Car> findAllCarList(List<Car> carList, List<Map<String, Object>> maps) {
        maps.forEach(element -> carList.add(new Car(
                Long.parseLong(String.valueOf(element.get("car_id"))),
                String.valueOf(element.get("mark")),
                String.valueOf(element.get("model")),
                String.valueOf(element.get("color")),
                Integer.parseInt(String.valueOf(element.get("production_year"))
                ))));
        return carList;
    }

    @Override
    public List<Car> findCarsBetweenYears(int fromYear, int toYear) {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE production_year BETWEEN ? AND ?";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, fromYear, toYear);
        return findAllCarList(carList, maps);
    }

    @Override
    public Car getOne(long id) {
        String sql = "SELECT  * FROM cars WHERE car_id=?";
        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> new Car(resultSet.getLong(1), resultSet.getString("mark"), resultSet.getString("model"), resultSet.getString("color"), resultSet.getInt("production_year")), id);
    }

    @Override
    public void updateCar(Car newCar) {
        String sql = "UPDATE cars SET cars.mark=?, cars.model=?, cars.color=?, cars.production_year=? WHERE cars.car_id=?";
        jdbcTemplate.update(sql, newCar.getMark(), newCar.getModel(), newCar.getColor(), newCar.getProductionYear(), newCar.getId());
    }

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM cars WHERE car_id=?";
        jdbcTemplate.update(sql, id);
    }
}
