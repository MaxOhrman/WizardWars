package main;

/**
 * The ID Enum will list all game objects
 * of our game.
 * <p>
 * Whenever we create object
 * we will also give it an appropriate id
 * from enum ID. This is needed to identify
 * gameObjects from one another
 */

public enum ID {
    Player(),
    Block(),
    Projectile(),

    //Monster
    Slime(),

    //Grass
    Grass_0(),
    Grass_1(),
    Grass_2(),

    //Dirt
    Dirt_0,
    Dirt_1,
    Dirt_2,

    //Dirt-Grass borders
    Dirt_N,
    Dirt_E,
    Dirt_W,
    Dirt_S,

    Dirt_NE,
    Dirt_NW,
    Dirt_SW,
    Dirt_SE,

    Dirt_Inner_NE,
    Dirt_Inner_NW,
    Dirt_Inner_SE,
    Dirt_Inner_SW,

    BigTree_NE,
    BigTree_NW,
    BigTree_SE,
    BigTree_SW


}
