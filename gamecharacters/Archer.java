package gamecharacters;

public class Archer implements Character<Archer> {
    String name, armor, weapon;
    int health, attack_power;

    public Archer(String name, int health, int attack_power, String weapon, String armor) {
        this.name = name;
        this.weapon = weapon;
        this.armor = armor;
        this.health = health;
        this.attack_power = attack_power;
    }

    public Archer(Archer other) {
        this.name = other.name;
        this.weapon = other.weapon;
        this.armor = other.armor;
        this.health = other.health;
        this.attack_power = other.attack_power;
    }

    @Override
    public Archer clone() {
        return new Archer(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public String getArmor() {
        return armor;
    }

    public void setArmor(String armor) {
        this.armor = armor;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attack_power;
    }

    public void setAttackPower(int attack_power) {
        this.attack_power = attack_power;
    }
}
