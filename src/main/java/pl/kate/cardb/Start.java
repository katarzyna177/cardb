package pl.kate.cardb;

import org.springframework.stereotype.Component;
import pl.kate.cardb.dao.CarDao;

@Component
public class Start {

    private CarDao carDao;

    public Start(CarDao carDao) {
        this.carDao = carDao;
        /*carDao.saveCar(1L, "Fiat", "126p", "RED", 2009);
        carDao.saveCar(2L, "Polonez", "Caro Plus", "BLACK", 2013);
        carDao.saveCar(3L, "BMW", "X4", "GREEN", 2014);*/
//        carDao.updateCar(new Car(3L, "Skoda", "X4", "GREEN", 2012));
//        carDao.deleteCar(2L);
//        System.out.println(carDao.getOne(3L));
        //carDao.findAll().forEach(System.out::println);
    }
}