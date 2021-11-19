/*
 * The constructor takes a single parameter: String name. The constructor should create a
 * FantasyCharacter with 80 energy, 70 attackAccuracy, and 60 healingSkill.
 * 
 * In the specialMove method, the Wizard attacks 3 times, but also attacks himself 1 time.
 */

public class Wizard extends FantasyCharacter {

    public Wizard(String name) {
        super(name, 80, 70, 60);
    }

    @Override
    public void specialMove(FantasyCharacter c) {
        for (int i = 0; i < 3; i++) {
            attack(c);
        }
        attack(this);
    }


}
