package pl.edu.pw.elka.gis.LGraph.core.process.action;

import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.action.NodeAction;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class RemoveNodeAction extends NodeAction {
    public RemoveNodeAction(GraphNode graphNode) {
        super(graphNode);
    }

    @Override
    public void apply(GraphActionListener actionListener) {
        actionListener.removeNode(this.getGraphNode());
    }
}
