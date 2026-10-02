package gamecharacters;

public class Client {
    public static void fillregistry(CharacterRegistry characterRegistry) {
        Warrior warrior = new Warrior("Ninja-125", 90, 85, "sword", "Heavy");
        characterRegistry.addCharacter("warrior", warrior);
        Mage mage = new Mage("Mage", 70, 80, "Magic Staff", "Robe");
        characterRegistry.addCharacter("mage", mage);
        Archer archer = new Archer("Archer", 80, 60, "Bow", "Light Armor");
        characterRegistry.addCharacter("archer", archer);
    }

    public static void main(String[] args) {
        CharacterRegistry characterregistry = new CharacterRegistry();
        fillregistry(characterregistry);
        Warrior warrior = new Warrior("Ninja-127", 150, 85, "sword", "Heavy");
        characterregistry.addCharacter("warrior2", warrior);
        Warrior warriorplayer = (Warrior) characterregistry.getCharacter("warrior").clone();
        System.out.println(warriorplayer.getAttackPower());
        System.out.println(warriorplayer != warrior);

    }
}
