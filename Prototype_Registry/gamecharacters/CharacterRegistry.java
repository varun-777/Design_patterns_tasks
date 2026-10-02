package gamecharacters;

import java.util.HashMap;
import java.util.Map;

public class CharacterRegistry {

    private Map<String, Character> characterRegister;

    public CharacterRegistry() {
        this.characterRegister = new HashMap<>();
    }

    public void addCharacter(String key, Character character) {
        characterRegister.put(key, character);
    }

    public Character getCharacter(String key) {
        return characterRegister.get(key);
    }
}