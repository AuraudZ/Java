import java.awt.Color;

public class CatRunner {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.setName("Fluffy");
        cat.setAge(2);
        cat.setColor(Color.WHITE);
        Cat cat2 = new Cat("S", 2, Color.BLACK);
        System.out.println(cat.getName());
        cat2.meow();
    }
}
