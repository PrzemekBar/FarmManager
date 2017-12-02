public class Animal implements Comparable<Animal>{
    private AnimalType type;
    private int age;
    private boolean vaccination;
    private String ID;

    public Animal(AnimalType type, int age, boolean vaccination){
        this.type = type;
        this.age = age;
        this.vaccination = vaccination;
    }

    public int getAge() {
        return age;
    }

    public AnimalType getType() {
        return type;
    }

    public boolean getVaccination() {
        return vaccination;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public int compareTo(Animal o) {
        return this.age - o.getAge();
    }

    public void setVaccination(boolean vaccination) {
        this.vaccination = vaccination;
    }

}