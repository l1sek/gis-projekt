package pl.edu.pw.elka.gis.LGraph.core.action;

import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;

/**
 * Created by mmajewski on 2016-12-17.
 */
public abstract class EdgeAction<T extends ActionListener> implements Action<T> {

    private GraphEdge graphEdge;

    public EdgeAction(GraphEdge graphEdge) {
        this.graphEdge = graphEdge;
    }

    public GraphEdge getGraphEdge() {
        return graphEdge;
    }

}
