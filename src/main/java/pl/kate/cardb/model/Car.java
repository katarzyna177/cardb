package pl.kate.cardb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private long id;
    private String mark;
    private String model;
    private String color;
    private int productionYear;

}
