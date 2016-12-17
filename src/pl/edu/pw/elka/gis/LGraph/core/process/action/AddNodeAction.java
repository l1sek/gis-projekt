package pl.edu.pw.elka.gis.LGraph.core.process.action;

import pl.edu.pw.elka.gis.LGraph.core.action.NodeAction;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;
import pl.edu.pw.elka.gis.LGraph.core.process.GraphActionListener;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class AddNodeAction extends NodeAction<GraphActionListener>{
    public AddNodeAction(GraphNode graphNode) {
        super(graphNode);
    }

    @Override
    public void apply(GraphActionListener actionListener) {
        actionListener.addNode(this.getGraphNode());
    }
}
