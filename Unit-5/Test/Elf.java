/*
 * The constructor takes a single parameter: String name. The constructor should create a
 * FantasyCharacter with 100 energy, 90 attackAccuracy, and 90 healingSkill.
 * 
 * The specialMove method for the Elf is to heal 3 times.
 */
public class Elf extends FantasyCharacter {

    public Elf(String name) {
        super(name, 100, 90, 90);
    }

    @Override
    public void specialMove(FantasyCharacter c) {
        for (int i = 0; i < 3; i++) {
            heal();
        }
    }

}
