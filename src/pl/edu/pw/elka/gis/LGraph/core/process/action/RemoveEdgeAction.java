package pl.edu.pw.elka.gis.LGraph.core.process.action;

import pl.edu.pw.elka.gis.LGraph.core.action.EdgeAction;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.process.GraphActionListener;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class RemoveEdgeAction extends EdgeAction<GraphActionListener> {
    public RemoveEdgeAction(GraphEdge graphEdge) {
        super(graphEdge);
    }

    @Override
    public void apply(GraphActionListener actionListener) {
        actionListener.removeEdge(this.getGraphEdge());
    }
}
