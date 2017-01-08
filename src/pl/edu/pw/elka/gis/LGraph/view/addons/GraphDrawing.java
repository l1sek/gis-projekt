package pl.edu.pw.elka.gis.LGraph.view.addons;

import javax.swing.JPanel;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import pl.edu.pw.elka.gis.LGraph.core.action.Action;
import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * This class keeps JPanel with area for graphs drawing by GraphStream.
 */
public class GraphDrawing implements GraphActionListener {
    
    public GraphDrawing() {
        graph = new SingleGraph("singleGraph");
        
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        drawPanel = viewer.addDefaultView(false);
    }
    
    public JPanel getPanel() {
        return drawPanel;
    }

    @Override
    public void addNode(GraphNode graphNode) {
        String name = graphNode.getName();
        graph.addNode(name);
        graph.getNode(name).addAttribute("ui.label", name);
    }

    @Override
    public void addEdge(GraphEdge graphEdge) {
        String firstName = graphEdge.getFirst().getName();
        String secondName = graphEdge.getSecond().getName();
        if (firstName.compareTo(secondName) < 0) {
            graph.addEdge(firstName + secondName, firstName, secondName);
        } else {
            graph.addEdge(secondName + firstName, secondName, firstName);
        }
    }

    @Override
    public void removeNode(GraphNode graphNode) {
        graph.removeNode(graphNode.getName());
    }

    @Override
    public void removeEdge(GraphEdge graphEdge) {
        String firstName = graphEdge.getFirst().getName();
        String secondName = graphEdge.getSecond().getName();
        graph.removeEdge(firstName, secondName);
    }

    @Override
    public pl.edu.pw.elka.gis.LGraph.core.model.Graph getGraph() {
        return null;
    }

    @Override
    public pl.edu.pw.elka.gis.LGraph.core.model.Graph getLineGraph() {
        return null;
    }

    @Override
    public void clear() {
        graph.clear();
    }

    @Override
    public void registerAction(Action<GraphActionListener> action) {
        
    }
    
    private SingleGraph graph;
    private JPanel drawPanel;
    private Viewer viewer;
}
