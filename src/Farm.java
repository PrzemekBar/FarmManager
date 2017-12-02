import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Farm {
    private List<Barn> barns;
    private List<Animal> allAnimals;
    private int animalCounter;
    private int actualMonth;
    private int actualYear;

    public Farm(){
        barns = new ArrayList<>();
        allAnimals = new ArrayList<>();
        animalCounter = 0;
        actualMonth = LocalDate.now().getMonthValue();
    }

    public List<Animal> getAllAnimals() {
        return allAnimals;
    }

    public List<Barn> getBarns() {
        return barns;
    }

    public void addBarn(String name, String ID){
        Barn barn = new Barn(name, ID);
        barns.add(barn);
    }

    public void removeBarn(String ID){
        for (Barn barn : barns) {
            for (Animal animal : barn.getAnimals()) {
                for (Animal allAnimal : allAnimals) {
                    if(animal.getID().equals(allAnimal.getID())){
                        allAnimals.remove(allAnimal);
                    }
                }
            }
            if(barn.getID().equals(ID)){
                barns.remove(barn);
            }
        }
    }

    public void addAnimal(int animalAge, AnimalType animalType, boolean vaccination, String barnID, String animalID){
        if(animalType != null){
            Animal animal = new Animal(animalType,animalAge,vaccination);
            animal.setID(createAnimalID());
            allAnimals.add(animal);
            for (Barn barn : barns) {
                if(barn.getID().equals(barnID)){
                    barn.addAnimal(animal);
                }
            }
        }else
            System.out.println("No such an animal type in base.");
    }

    public void removeAnimal(String animalID){
        for (Animal animal : allAnimals) {
            if(animal.getID().equals(animalID)){
                allAnimals.remove(animal);
            }
        }
        for (Barn barn : barns) {
            for (Animal animal : barn.getAnimals()) {
                if(animal.getID().equals(animalID)){
                    barn.getAnimals().remove(animal);
                }
            }
        }
    }

    public List<Animal> animalsWithVaccination() {
        List<Animal> animalsWithVaccination = new ArrayList<>();
        for (Animal animal : allAnimals) {
            if (animal.getVaccination()) {
                animalsWithVaccination.add(animal);
            }
        }

        return animalsWithVaccination;
    }

    public AnimalType mostNumerosType(){
        int mostNumber = 1;
        AnimalType mostNumerosType = null;
        Map<AnimalType, Integer> map = new HashMap<>();

        for (Animal animal : allAnimals) {
            AnimalType type = animal.getType();
            if (map.containsKey(type)) {
                map.replace(type, map.get(type) + 1);
                if (map.get(type) > mostNumber) {
                    mostNumerosType = type;
                }
            } else {
                map.put(type, 1);
            }
        }
        return mostNumerosType;
    }

    public Barn mostNumerosBarn(){
        int counter = 1;
        Barn mostNumerosBarn = null;
        for (Barn barn : barns) {
            int localCounter = 0;
            for (Animal animal : barn.getAnimals()) {
                localCounter++;
            }
            if(localCounter>counter){
                counter=localCounter;
                mostNumerosBarn = barn;
            }
        }
        return mostNumerosBarn;
    }

    public List<Animal> getOldestAnimals(){
        allAnimals.sort(Animal::compareTo);
        List<Animal> oldestAnimals = new ArrayList<>();
        oldestAnimals = allAnimals.subList(0,4);
        return oldestAnimals;
    }

    public List<Animal> getYoungestAnimals(){
        allAnimals.sort(Animal::compareTo);
        List<Animal> youngest = new ArrayList<>();
        youngest = allAnimals.subList(allAnimals.size()-5,allAnimals.size());
        return youngest;
    }

    public void setVaccination(String ID){
        boolean check = false;
        for (Animal animal : allAnimals) {
            if (animal.getID().equals(ID)) {
                animal.setVaccination(true);
                check = true;
            }
        }
        if (!check){
            System.out.println("This animal doesn't exist!");
        }
    }

    public boolean isThereAnyAnimal(){
        if(allAnimals.isEmpty()){
            System.out.println("There's no animals!");
            return false;
        }else{
            return true;
        }
    }

    public boolean isThereAnyBarn(){
        if(barns.isEmpty()){
            System.out.println("There's no barns!");
            return false;
        }else{
            return true;
        }
    }

    public String createAnimalID(){
        int month = LocalDate.now().getMonthValue();
        int year = LocalDate.now().getYear();
        if(actualMonth<month||actualYear<year){
            animalCounter=0;
            actualMonth=month;
            actualYear=year;
        }
        animalCounter++;
        String animalID = ""+ actualMonth + actualYear + animalCounter;
        return animalID;
    }
}