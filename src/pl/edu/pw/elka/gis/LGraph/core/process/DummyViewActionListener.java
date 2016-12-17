package pl.edu.pw.elka.gis.LGraph.core.process;

import pl.edu.pw.elka.gis.LGraph.core.action.Action;
import pl.edu.pw.elka.gis.LGraph.core.action.ViewActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class DummyViewActionListener implements ViewActionListener{
    @Override
    public void registerAction(Action action) {

    }

    @Override
    public void addNode(GraphNode graphNode) {

    }

    @Override
    public void addEdge(GraphEdge graphEdge) {

    }

    @Override
    public void removeNode(GraphNode graphNode) {

    }

    @Override
    public void removeEdge(GraphEdge graphEdge) {

    }

    @Override
    public void refresh(Graph graph) {

    }
}
