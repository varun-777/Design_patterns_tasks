```markdown
# Character Game - Prototype & Registry Pattern

## Problem Statement

You are developing a simple character system for a game.

The game contains different types of characters such as:

- Warrior
- Mage
- Archer

Each character has common properties:

- Name
- Health
- Attack Power
- Weapon
- Armor

Creating every character from scratch can be repetitive. Instead, the game should maintain a set of pre-configured character objects and create new characters by cloning them.

To achieve this, implement the **Prototype Design Pattern** along with a **Registry**.

---

## Requirements

### 1. Character Prototype

Create a generic `Character<T>` interface with a `clone()` method.

The `clone()` method should return a copy of the current character.

Example:

```java
public interface Character<T> {
    T clone();
}
```

---

### 2. Character Types

Create the following character classes:

- `Warrior`
- `Mage`
- `Archer`

Each character should contain:

```text
name
health
attack_power
weapon
armor
```

Each character must:

1. Implement the `Character<T>` interface.
2. Have a constructor to initialize its properties.
3. Have a copy constructor.
4. Implement the `clone()` method.
5. Provide getters and setters for its properties.

Example:

```java
Warrior warrior = new Warrior(
    "Ninja-125",
    90,
    85,
    "Sword",
    "Heavy"
);
```

---

## 3. Character Registry

Create a `CharacterRegistry` class that stores character prototypes.

The registry should use a `Map` where:

```text
key → character prototype
```

For example:

```text
"warrior" → Warrior prototype
"mage"    → Mage prototype
"archer"  → Archer prototype
```

The registry should provide methods to:

```java
addCharacter(String key, Character character)
```

and

```java
getCharacter(String key)
```

---

## 4. Client

Create a `Client` class.

The client should:

1. Create a `CharacterRegistry`.
2. Create one prototype for each character type.
3. Add the prototypes to the registry.
4. Retrieve a prototype from the registry.
5. Clone the prototype to create a new character.
6. Verify that the cloned character contains the same data as the prototype.
7. Verify that the clone is a different object.

Example:

```java
CharacterRegistry registry = new CharacterRegistry();

fillRegistry(registry);

Warrior warriorPlayer =
    (Warrior) registry
        .getCharacter("warrior")
        .clone();
```

---

## Expected Behavior

If the registered Warrior prototype contains:

```text
Name: Ninja-125
Health: 90
Attack Power: 85
Weapon: Sword
Armor: Heavy
```

then cloning it should create another Warrior with the same values.

However:

```java
warriorPlayer != warriorPrototype
```

should be:

```text
true
```

because the clone must be a **new object**.

---

## Design Pattern Responsibilities

### Prototype

The Prototype Pattern is responsible for:

> Creating a new object by copying an existing object.

For example:

```java
Warrior clone() {
    return new Warrior(this);
}
```

### Registry

The Registry is responsible for:

> Storing and retrieving predefined prototypes using keys.

For example:

```text
"warrior" → Warrior prototype
"mage"    → Mage prototype
"archer"  → Archer prototype
```

### Overall Flow

```text
                Character Registry
                       |
          ┌────────────┼────────────┐
          ↓            ↓            ↓
      "warrior"      "mage"      "archer"
          ↓            ↓            ↓
      Warrior        Mage        Archer
     Prototype     Prototype    Prototype
          |
          | clone()
          ↓
     New Warrior
```

---

## Goal of the Assignment

The goal is to understand how the **Prototype Design Pattern** and **Registry** work together.

The important idea is:

```text
Registry
   ↓
Find prototype
   ↓
Clone prototype
   ↓
Create new object
```
