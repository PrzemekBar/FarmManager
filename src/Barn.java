import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Barn{

    private String name;
    private String ID;
    private List<Animal> animals;

    public Barn(String name, String ID){
        this.name = name;
        this.ID = ID;
        this.animals = new ArrayList<Animal>();
    }

    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    }
    public List<Animal> getAnimals() {
        return animals;
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public AnimalType stringToAnimalType(String animalType){
        switch (animalType){
            case "cow":
                return AnimalType.cow;
            case "pig":
                return AnimalType.pig;
            case "horse":
                return AnimalType.horse;
            case "bull":
                return AnimalType.bull;
            case "chicken":
                return AnimalType.chicken;
            case "ducks":
                return AnimalType.ducks;
            case "goose":
                return AnimalType.goose;
            case "sheep":
                return AnimalType.sheep;
            default:
                return null;
        }
    }
}