package pl.edu.pw.elka.gis.LGraph.core.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class Graph {
    private Set<GraphNode> nodeSet = new HashSet<>();
    private Set<GraphEdge> edgeSet = new HashSet<>();

    public void addNode(GraphNode graphNode){
        nodeSet.add(graphNode);
    }

    public void addEdge(GraphEdge graphEdge){
        nodeSet.add(graphEdge.getFirst());
        nodeSet.add(graphEdge.getFirst());
        edgeSet.add(graphEdge);
    }

    public void removeNode(GraphNode graphNode){
        nodeSet.remove(graphNode);
        edgeSet.removeIf(e -> e.getFirst().equals(graphNode) || e.getSecond().equals(graphNode));
    }

    public void removeEdge(GraphEdge graphEdge){
        edgeSet.remove(graphEdge);
    }

    public Set<GraphNode> getNodeSet() {
        return nodeSet;
    }

    public Set<GraphEdge> getEdgeSet() {
        return edgeSet;
    }

    public Set<GraphNode> getNeighbours(GraphNode graphNode){
        return edgeSet.parallelStream()
                .filter(e -> e.hasNode(graphNode))
                .map(e -> {
                    if(e.getFirst().equals(graphNode)){
                        return e.getSecond();
                    }
                    return e.getFirst();
                }).collect(Collectors.toSet());
    }

    public GraphNode findNode(String name){
        GraphNode graphNode = new GraphNode(name);
        if(nodeSet.contains(graphNode)) return graphNode;
        return null;
    }

    public Set<GraphEdge> findEdgesToNode(GraphNode graphNode){
        return edgeSet.parallelStream()
                .filter(e -> e.hasNode(graphNode))
                .collect(Collectors.toSet());
    }
}
