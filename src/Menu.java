import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Menu {
    private Farm farm;
    private Scanner scanner;
    private File saved;

    public Menu(){
        farm = new Farm();
        scanner = new Scanner(System.in);
        saved = new File ("C:\\Users\\Przemek\\Desktop\\Farm\\saved.txt");
        if(!saved.exists()){
            try {
                saved.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            load();
        }
    }

    public void showMenu(){
        String input = "default";
        do {
            System.out.println(
                    "////YOUR FARM\\\\\\\\ \n" +
                    "1. All animals.     \n" +
                    "2. Animals with vaccination.  \n" +
                    "3. Most numeros animal type.  \n" +
                    "4. Youngest animals. \n" +
                    "5. Oldest animals.  \n" +
                    "6. Barn with biggest animals number. \n" +
                    "7. Farm manager. \n" +
                    "0. EXIT");

            input = scanner.nextLine();
            standardMenuSwitch(input);
        } while (!input.equals("0"));
    }

    public void showAdvancedMenu(){
        String input = "default";
        do {
            System.out.println(
                    "////FARM MANAGER\\\\\\\\ \n" +
                    "1. Add animal. \n" +
                    "2. Remove animal.  \n" +
                    "3. Add barn. \n" +
                    "4. Remove barn.  \n" +
                    "5. Set vaccination.  \n" +
                    "6. Save.        \n" +
                    "0. Back."          );

            input = scanner.nextLine();
            advancedMenuSwitch(input);
        } while (!input.equals("0"));
    }

    public void standardMenuSwitch(String input){
        switch (input){
            case "1":
                showAllAnimals();
                break;
            case "2":
                showVaccinated();
                break;
            case "3":
                showMostNumerosType();
                break;
            case "4":
                showOldestAnimals();
                break;
            case "5":
                showYoungestAnimals();
                break;
            case "6":
                showMostNumerosBarn();
                break;
            case "7":
                showAdvancedMenu();
                break;
            case "0":
                save();
                break;
            default:
                System.out.println("Wrong choice. Try again!");
        }
    }

    public void advancedMenuSwitch(String input) {
        switch (input) {
            case "1":
                addAnimal();
                break;
            case "2":
                removeAnimal();
                break;
            case "3":
                addBarn();
                break;
            case "4":
                removeBarn();
                break;
            case "5":
                setVaccination();
                break;
            case "6":
                save();
                break;
            default:
                System.out.println("Wrong choice. Try again.\n\n");
                break;
            case "0":
                save();
        }
    }

    public void showAllAnimals(){
        if(farm.isThereAnyAnimal()){
            for (Animal animal : farm.getAllAnimals()) {
                System.out.println(animalToString(animal));
            }
        }
    }

    public void showVaccinated(){
        if (farm.isThereAnyAnimal()) {
            if(!farm.animalsWithVaccination().isEmpty()){
                for (Animal animal : farm.animalsWithVaccination()) {
                    System.out.println(animalToString(animal));
                }
            }else{
                System.out.println("There's no animals with vaccination.");
            }
        }
    }

    public void showMostNumerosType(){
        if(farm.isThereAnyAnimal()){
            System.out.println(farm.mostNumerosType());
        }
    }

    public void showOldestAnimals(){
        if(farm.isThereAnyAnimal()) {
            for (Animal animal : farm.getOldestAnimals()) {
                System.out.println(animalToString(animal));
            }
        }
    }

    public void showYoungestAnimals(){
        if(farm.isThereAnyAnimal()) {
            for (Animal animal : farm.getYoungestAnimals()) {
                System.out.println(animalToString(animal));
            }
        }
    }

    public void showMostNumerosBarn(){
        if(farm.isThereAnyBarn()) {
            if(farm.isThereAnyAnimal()) {
                System.out.println(farm.mostNumerosBarn().getID());
            }
        }
    }

    public void addAnimal() {
        if (farm.isThereAnyBarn()) {
            System.out.println("Which barn you want it in? Put barn ID: ");
            String barnID = scanner.nextLine();
            boolean check = false;
            for (Barn barn : farm.getBarns()) {
                if (barn.getID().equals(barnID)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                System.out.println("This barn doesn't exist! Try with another one!");
            }else {
                System.out.println("Animal type: ");
                String stringType = scanner.nextLine();
                AnimalType type = stringToAnimalType(stringType);
                if(type!=null) {
                    check = false;
                    System.out.println("Animal age: ");
                    int age = 0;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                        check = true;
                    } catch (NumberFormatException e) {
                        System.out.println("You need to put number here!");
                    }
                    if (check) {
                        System.out.println("Was it vaccinated? If yes type \"yes\".");
                        boolean vaccination = false;
                        if (scanner.nextLine().toLowerCase().equals("yes")) {
                            vaccination = true;
                        }
                        farm.addAnimal(age, type, vaccination, barnID, farm.createAnimalID());
                    }
                }
            }
        }
    }

    public void removeAnimal(){
        if(farm.isThereAnyAnimal()) {
            System.out.println("Type ID of animal to remove: ");
            String ID = scanner.nextLine();
            boolean check = false;
            for (Animal animal : farm.getAllAnimals()) {
                if (animal.getID().equals(ID)) {
                    check = true;
                }
            }
            if (check) {
                farm.removeAnimal(ID);
            } else {
                System.out.println("This animal doesn't exist!");
            }
        }
    }

    public void addBarn(){
        System.out.println("Type barn name: ");
        String name = scanner.nextLine();
        System.out.println("Type barn ID: ");
        String ID = scanner.nextLine();
        farm.addBarn(name, ID);
    }

    public void removeBarn(){
        if(farm.isThereAnyBarn()) {
            System.out.println("Type ID of barn to remove: ");
            String ID = scanner.nextLine();
            boolean check = false;
            for (Barn barn : farm.getBarns()) {
                if (barn.getID().equals(ID)) {
                    check = true;
                }
            }
            if (check) {
                farm.removeBarn(ID);
            } else {
                System.out.println("This barn doesn't exist!");
            }
        }
    }

    public void setVaccination(){
        System.out.println("ID of animal to set vaccination: ");
        String ID = scanner.nextLine();
        farm.setVaccination(ID);
    }

    public void save(){
        if(farm.isThereAnyBarn()) {
            if (farm.isThereAnyAnimal()) {
                String toSave = "";
                for (Barn barn : farm.getBarns()) {
                    toSave += barn.getID() + ":" + barn.getName() + "\n";
                    for (Animal animal : barn.getAnimals()) {
                        toSave += animal.getID() + ":" + animal.getType() + ":" + animal.getAge() + ":" + animal.getVaccination() + "\n";
                    }
                }
                try {
                    Files.write(saved.toPath(), toSave.getBytes(), StandardOpenOption.CREATE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            System.out.println("There's nothing to save!");
        }
    }

    public void load(){
        try {
            String barnID="";
            for (String s : Files.readAllLines(saved.toPath())) {
                String[] splitted = s.split(":");
                if (splitted.length == 2){
                    barnID = splitted[0];
                    farm.addBarn(splitted[1], barnID);
                }else{
                    String animalID = splitted[0];
                    AnimalType animalType = stringToAnimalType(splitted[1]);
                    int animalAge = Integer.parseInt(splitted[2]);
                    boolean vaccination;
                    if(splitted[3].equals("true")) {
                        vaccination = true;
                    }else {
                        vaccination = false;
                    }
                    farm.addAnimal(animalAge,animalType,vaccination,barnID, animalID);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String animalToString(Animal animal){
        return animal.getID()+"-"+animal.getType()+"-"+animal.getAge()+animal.getVaccination();
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
                System.out.println("There's no such animal type in base!");
                return null;
        }
    }
}