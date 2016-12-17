package pl.edu.pw.elka.gis.LGraph.core.action;

import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public interface GraphActionListener extends ActionListener<GraphActionListener> {
    void addNode(GraphNode graphNode);
    void addEdge(GraphEdge graphEdge);
    void removeNode(GraphNode graphNode);
    void removeEdge(GraphEdge graphEdge);
}
