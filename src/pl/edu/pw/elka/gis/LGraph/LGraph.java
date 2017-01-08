package pl.edu.pw.elka.gis.LGraph;

import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.process.GraphActionListenerImpl;
import pl.edu.pw.elka.gis.LGraph.persistence.FileActionListener;
import pl.edu.pw.elka.gis.LGraph.view.MainWindow;
import pl.edu.pw.elka.gis.LGraph.view.addons.GraphDrawing;

/**
 * Main class which initialise all application.
 */
public class LGraph {
    public static void main(String[] args) {
        GraphActionListener graphView = new GraphDrawing();
        GraphActionListener lineGraphView = new GraphDrawing();

        /* This listener accepts following actions initiated by User:
         * -  pl.edu.pw.elka.gis.LGraph.core.process.action.AddEdgeAction
         * -  pl.edu.pw.elka.gis.LGraph.core.process.action.AddNodeAction
         * -  pl.edu.pw.elka.gis.LGraph.core.process.action.ClearAction
         * -  pl.edu.pw.elka.gis.LGraph.core.process.action.RemoveEdgeAction
         * -  pl.edu.pw.elka.gis.LGraph.core.process.action.RemoveNodeAction
         * example usage:
         * userGraphActionListener.registerAction(new AddNodeAction(new GraphNode("a")));
         * userGraphActionListener.registerAction(new ClearAction());
         * userGraphActionListener.registerAction(new RemoveEdgeAction(new GraphEdge(new GraphNode("a"), new GraphNode("b"))));
         */
        GraphActionListenerImpl userGraphActionListener = new GraphActionListenerImpl(graphView, lineGraphView);

        /* This listener accepts following actions initiated by User:
         * - pl.edu.pw.elka.gis.LGraph.persistence.action.LoadGraphAction
         * - pl.edu.pw.elka.gis.LGraph.persistence.action.SaveGraphAction
         * example usage:
         * userFileActionListener.registerAction(new SaveGraphAction(new File("to_save.graph"), userGraphActionListener.getGraph()));
         * userFileActionListener.registerAction(new SaveGraphAction(new File("to_save.lgraph"), userGraphActionListener.getLineGraph()));
         * userFileActionListener.registerAction(new LoadGraphAction(new File("to_load.graph"), userGraphActionListener));
         */
        FileActionListener userFileActionListener = new FileActionListener();

        /* Spinning up background tasks.
         * Should be killed/interrupted when MainWindow gets closed.
         */
        Thread graphModificationThread = new Thread(userGraphActionListener);
        Thread persistenceThread = new Thread(userFileActionListener);
        graphModificationThread.start();
        persistenceThread.start();

        new MainWindow(userGraphActionListener, userFileActionListener);
    }
}
