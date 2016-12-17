package pl.edu.pw.elka.gis.LGraph.core.model;

import java.util.HashSet;
import java.util.Set;

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
        nodeSet.add(graphEdge.getSecond());
        edgeSet.add(graphEdge);
    }

    public void removeNode(GraphNode graphNode){
        nodeSet.remove(graphNode);
        Set<GraphEdge> toDelete = new HashSet<>();
        for(GraphEdge edge : edgeSet){
            if(edge.hasNode(graphNode)){
                toDelete.add(edge);
            }
        }
        edgeSet.removeAll(toDelete);
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

    public Set<GraphNode> findNeighbours(GraphNode graphNode){
        Set<GraphNode> neighbourSet = new HashSet<>();
        for(GraphEdge edge : edgeSet){
            if(edge.getFirst().equals(graphNode)){
                neighbourSet.add(edge.getSecond());
            }else if(edge.getSecond().equals(graphNode)){
                neighbourSet.add(edge.getFirst());
            }
        }
        return neighbourSet;
    }

    public GraphNode findNode(String name){
        GraphNode graphNode = new GraphNode(name);
        if(nodeSet.contains(graphNode)) return graphNode;
        return null;
    }

    public GraphEdge findEdge(GraphEdge graphEdge){
        return findEdge(graphEdge.getFirst(), graphEdge.getSecond());
    }

    public GraphEdge findEdge(GraphNode node1, GraphNode node2){
        for(GraphEdge edge : edgeSet){
            if(edge.hasNode(node1) && edge.hasNode(node2)){
                return edge;
            }
        }
        return null;
    }

    public Set<GraphEdge> findEdgesToNode(GraphNode graphNode){
        Set<GraphEdge> nodeEdgeSet = new HashSet<>();
        for(GraphEdge edge : edgeSet){
            if(edge.hasNode(graphNode)){
                nodeEdgeSet.add(edge);
            }
        }
        return nodeEdgeSet;
    }
}
