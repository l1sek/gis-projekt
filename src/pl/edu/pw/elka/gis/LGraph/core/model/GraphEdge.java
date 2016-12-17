package pl.edu.pw.elka.gis.LGraph.core.model;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class GraphEdge {
    private GraphNode first;
    private GraphNode second;

    public GraphEdge(GraphNode first, GraphNode second) {
        this.first = first;
        this.second = second;
    }

    public GraphNode getFirst() {
        return first;
    }

    public void setFirst(GraphNode first) {
        this.first = first;
    }

    public GraphNode getSecond() {
        return second;
    }

    public void setSecond(GraphNode second) {
        this.second = second;
    }

    public boolean hasNode(GraphNode graphNode){
        return first.equals(graphNode) || second.equals(graphNode);
    }
}
