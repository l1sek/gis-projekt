package pl.edu.pw.elka.gis.LGraph.core.process.action;

import pl.edu.pw.elka.gis.LGraph.core.action.EdgeAction;
import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class AddEdgeAction extends EdgeAction {
    public AddEdgeAction(GraphEdge graphEdge) {
        super(graphEdge);
    }

    @Override
    public void apply(GraphActionListener actionListener) {
        actionListener.addEdge(this.getGraphEdge());
    }
}
