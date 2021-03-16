package huglife;

import java.util.*;

/**
 * @author Josh Hug
 * A class that represents living creatures. You should
 * extend this class to populate your world.
 */
public abstract class Creature extends Occupant {
    /**
     * energy for this creature.
     */
    protected double energy;

    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     */
    public Creature(String n) {
        super(n);
    }

    /**
     * Called when this creature moves.
     */
    public abstract void move();

    /**
     * Called when this creature attacks C.
     */
    public abstract void attack(Creature c);

    /**
     * Called when this creature chooses replicate.
     * Must return a creature of the same type.
     */
    public abstract Creature replicate();

    /**
     * Called when this creature chooses stay.
     */
    public abstract void stay();

    /**
     * Returns an action. The creature is provided information about its
     * immediate NEIGHBORS with which to make a decision.
     */
    public abstract Action chooseAction(Map<Direction, Occupant> neighbors);

    /**
     * Returns the current energy.
     */
    public double energy() {
        return energy;
    }

    /**
     * Returns a random object of @deque.
     */
    protected Direction chooseRandomDirection(Deque<Direction> deque) {
        Random rd = new Random();
        int randomIndex = rd.nextInt(deque.size());
        Direction res = null;
        for (int i = 0; i <= randomIndex; i++) {
            res = deque.removeFirst();
        }
        return res;
    }
}
