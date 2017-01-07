package pl.edu.pw.elka.gis.LGraph.core.process.action;

import pl.edu.pw.elka.gis.LGraph.core.action.Action;
import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;

/**
 * Created by mmajewski on 2016-12-18.
 */
public class ClearAction implements Action<GraphActionListener> {

    @Override
    public void apply(GraphActionListener actionListener) {
        actionListener.clear();
    }
}
