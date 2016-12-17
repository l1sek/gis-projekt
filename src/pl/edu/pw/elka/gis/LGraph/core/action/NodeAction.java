package pl.edu.pw.elka.gis.LGraph.core.action;

import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public abstract class NodeAction implements Action<GraphActionListener> {
    private GraphNode graphNode;

    public NodeAction(GraphNode graphNode) {
        this.graphNode = graphNode;
    }

    public GraphNode getGraphNode() {
        return graphNode;
    }
}