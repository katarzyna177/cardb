package pl.kate.cardb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.kate.cardb.dao.CarDaoImpl;
import pl.kate.cardb.model.Car;

import java.util.Optional;

@Controller
public class CarController {
    private CarDaoImpl carDaoImpl;
    private int fromYear = 0;
    private int toYear = 0;

    public CarController(CarDaoImpl carDaoImpl) {
        this.carDaoImpl = carDaoImpl;
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        model.addAttribute("cars", carDaoImpl.findAll());
        model.addAttribute("newCar", new Car());
        model.addAttribute("fromYear", fromYear);
        model.addAttribute("toYear", toYear);
        return "cars";
    }

    @PostMapping("/cars")
    public String getCarsFromTo(@RequestParam int fromYear, @RequestParam int toYear, Model model){
        model.addAttribute("cars", carDaoImpl.findCarsBetweenYears(fromYear, toYear));
        model.addAttribute("from", fromYear);
        model.addAttribute("to", toYear);
        return "filterCars";
    }

    @GetMapping("/cars/{id}")
    public String getCarById(@PathVariable long id, Model model) {
        Optional<Car> carById = Optional.ofNullable(carDaoImpl.getOne(id));
        if (carById.isPresent()) {
            model.addAttribute("car", carById.get());
            return "car";
        }
        return "redirect:/error";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car newCar) {
        carDaoImpl.saveCar(newCar.getId(), newCar.getMark(), newCar.getModel(), newCar.getColor(), newCar.getProductionYear());
        return "redirect:/cars";
    }


    @GetMapping("/delete-car/{id}")
    public String deleteCar(@PathVariable long id) {
        carDaoImpl.deleteCar(id);
        return "redirect:/cars";
    }

    @GetMapping("/edit-car/{id}")
    public String postModifyCar(Model model, @PathVariable long id) {
        Optional<Car> carById = Optional.ofNullable(carDaoImpl.getOne(id));
        Car myCar = carById.orElseGet(Car::new);
        model.addAttribute("newCar", new Car());
        model.addAttribute("car", myCar);
        return "edit-car";
    }

    @PostMapping("/edit-car")
    public String modifyCar(@ModelAttribute Car car) {
        carDaoImpl.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

}

