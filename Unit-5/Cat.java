import java.awt.Color;

class Cat {
    public int catCount;
    // Instance variables - All cat objects have
    private String name;
    private int age;
    private Color color;

    // Constructors
    public Cat() {
        name = "Default";
        age = 0;
        color = Color.black;
    }

    public Cat(String name) {
        this.name = name;
        this.age = 0;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public Cat(String name, int age, Color color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    // Instance Methods - All cat objects will do
    public void meow() {
        System.out.println(name + " Meows");
    }

    // Getters and Setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return this.color.toString();
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
