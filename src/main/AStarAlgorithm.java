package main;

import entities.GameObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStarAlgorithm {

    public List<Node> astar(Node start, Node goal, Handler handler) {
        // Set up the open and closed sets
        List<Node> openSet = new ArrayList<>();
        openSet.add(start);

        Map<Node, Node> cameFrom = new HashMap<>();

        Map<Node, Double> gScore = new HashMap<>();
        gScore.put(start, 0.0);

        Map<Node, Double> fScore = new HashMap<>();
        fScore.put(start, heuristicCostEstimate(start, goal));

        ArrayList<GameObject> objects = handler.getObjectArray();


        for (GameObject obj : objects) {
            Node node = new Node((int)obj.getX(), (int)obj.getY());
            // add the node to the open set
            openSet.add(node);
        }

        // While there are still nodes in the open set
        while (!openSet.isEmpty()) {
            Node current = getNodeWithLowestFScore(openSet, fScore);

            // If we've reached the goal, reconstruct the path and return it
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);

            // For each of the current node's neighbors
            for (Node neighbor : current.getNeighbors()) {
                double tentativeGScore = gScore.get(current) + distanceBetween(current, neighbor);

                // If this path to the neighbor is shorter than any path we've seen before
                if (!gScore.containsKey(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, gScore.get(neighbor) + heuristicCostEstimate(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // If we get here, we didn't find a path
        System.out.println("No path found");
        return null;
    }

    private Node getNodeWithLowestFScore(List<Node> openSet, Map<Node, Double> fScore) {
        Node nodeWithLowestFScore = openSet.get(0);
        double lowestFScore = fScore.get(nodeWithLowestFScore);

        for (Node node : openSet) {
            double fScoreForNode = fScore.getOrDefault(node, Double.MAX_VALUE);

            if (fScoreForNode < lowestFScore) {
                nodeWithLowestFScore = node;
                lowestFScore = fScoreForNode;
            }
        }

        return nodeWithLowestFScore;
    }

    private List<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
        List<Node> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }

    private double heuristicCostEstimate(Node node, Node goal) {
        // Use the Manhattan distance heuristic
        return Math.abs(node.getX() - goal.getX()) + Math.abs(node.getY() - goal.getY());
    }

    private double distanceBetween(Node node1, Node node2) {
        // Assume all edges have a cost of 1
        return 1.0;
    }
}