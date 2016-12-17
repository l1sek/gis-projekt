package pl.edu.pw.elka.gis.LGraph.core.model;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class GraphNode implements Comparable<GraphNode> {
    private String name;

    public GraphNode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(GraphNode o) {
        return name.compareTo(o.name);
    }
}
