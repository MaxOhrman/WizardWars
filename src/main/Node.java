package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private int x;
    private int y;
    private List<Node> neighbors;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbors = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node otherNode)) {
            return false;
        }

        return x == otherNode.x && y == otherNode.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}