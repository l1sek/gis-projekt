package pl.edu.pw.elka.gis.LGraph.core.action;

import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public interface ViewActionListener extends ActionListener {
    void addNode(GraphNode graphNode);
    void addEdge(GraphEdge graphEdge);
    void removeNode(GraphNode graphNode);
    void removeEdge(GraphEdge graphEdge);
    void refresh(Graph graph);
}
