package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {

    public Clorus() {
        super("clorus");
    }

    public Clorus(double e) {
        super("clorus");
        this.energy = e;
    }

    @Override
    public void move() {
        this.energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        this.energy += c.energy();
    }

    @Override
    public Creature replicate() {
        this.energy /= 2;
        return new Clorus(this.energy());
    }

    @Override
    public void stay() {
        this.energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> neighboringPlips = new ArrayDeque<>();

        for (Direction direction : neighbors.keySet()) {
            if (neighbors.get(direction).name().equals("empty")) {
                emptyNeighbors.add(direction);
            } else if (neighbors.get(direction).name().equals("plip")) {
                neighboringPlips.add(direction);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        if (!neighboringPlips.isEmpty()) {
            Direction direction = chooseRandomDirection(neighboringPlips);
            return new Action(Action.ActionType.ATTACK, direction);
        }

        if (this.energy() >= 1d) {
            Direction direction = chooseRandomDirection(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, direction);
        }

        Direction randomDirection = chooseRandomDirection(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, randomDirection);

    }

    @Override
    public Color color() {
        return new Color(34, 0, 231);
    }
}
