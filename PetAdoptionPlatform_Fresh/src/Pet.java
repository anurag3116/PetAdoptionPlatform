public class Pet {
    private int id;
    private String name;
    private String breed;
    private int age;
    private String status;

    public Pet(int id, String name, String breed, int age, String status) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.status = status;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public int getAge() { return age; }
    public String getStatus() { return status; }
}
