package pl.edu.pw.elka.gis.LGraph.core.process;

import pl.edu.pw.elka.gis.LGraph.core.action.Action;
import pl.edu.pw.elka.gis.LGraph.core.action.ActionListener;
import pl.edu.pw.elka.gis.LGraph.core.action.ViewActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class GraphActionListener implements ActionListener<GraphActionListener>, Runnable {
    private BlockingQueue<Action<GraphActionListener>> actionQueue = new ArrayBlockingQueue<>(100);
    private Graph graph = new Graph();
    private Graph lineGraph = new Graph();
    private ViewActionListener graphView;
    private ViewActionListener lineView;

    public GraphActionListener() {
        this.graphView = new DummyViewActionListener();
        this.lineView = new DummyViewActionListener();
    }

    public GraphActionListener(ViewActionListener graphView, ViewActionListener lineView) {
        this.graphView = graphView;
        this.lineView = lineView;
    }

    private String lineNodeName(GraphNode graphNode1, GraphNode graphNode2) {
        if (graphNode1.getName().compareTo(graphNode2.getName()) < 0) {
            return graphNode1.getName() + ":" + graphNode2.getName();
        }
        return graphNode2.getName() + ":" + graphNode1.getName();
    }

    private String lineNodeName(GraphEdge graphEdge) {
        return lineNodeName(graphEdge.getFirst(), graphEdge.getSecond());
    }

    @Override
    public void registerAction(Action<GraphActionListener> action) {
        actionQueue.add(action);
    }

    public void addNode(GraphNode graphNode) {
        graph.addNode(graphNode);
        graphView.addNode(graphNode);
    }

    public void addEdge(GraphEdge graphEdge) {
        graph.addEdge(graphEdge);
        graphView.addEdge(graphEdge);

        GraphNode lineNode = new GraphNode(lineNodeName(graphEdge));
        lineGraph.addNode(lineNode);
        lineView.addNode(lineNode);

        for (GraphNode neighbour : graph.getNeighbours(graphEdge.getFirst())) {
            if (!neighbour.equals(graphEdge.getSecond())) {
                GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getFirst(), neighbour));
                GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                lineGraph.addEdge(lineEdge);
                lineView.addEdge(lineEdge);
            }
        }

        for (GraphNode neighbour : graph.getNeighbours(graphEdge.getSecond())) {
            if (!neighbour.equals(graphEdge.getFirst())) {
                GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getSecond(), neighbour));
                GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                lineGraph.addEdge(lineEdge);
                lineView.addEdge(lineEdge);
            }
        }
    }

    public void removeEdge(GraphEdge graphEdge) {
        graph.removeEdge(graphEdge);
        graphView.removeEdge(graphEdge);

        GraphNode lineNode = new GraphNode(lineNodeName(graphEdge));

        for (GraphNode neighbour : graph.getNeighbours(graphEdge.getFirst())) {
            if (!neighbour.equals(graphEdge.getSecond())) {
                GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getFirst(), neighbour));
                GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                lineGraph.removeEdge(lineEdge);
                lineView.removeEdge(lineEdge);
            }
        }

        for (GraphNode neighbour : graph.getNeighbours(graphEdge.getSecond())) {
            if (!neighbour.equals(graphEdge.getFirst())) {
                GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getSecond(), neighbour));
                GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                lineGraph.removeEdge(lineEdge);
                lineView.removeEdge(lineEdge);
            }
        }

        lineGraph.removeNode(lineNode);
        lineView.removeNode(lineNode);
    }

    public void removeNode(GraphNode graphNode) {
        graph.findEdgesToNode(graphNode).forEach(this::removeEdge);

        graph.removeNode(graphNode);
        graphView.removeNode(graphNode);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Action<GraphActionListener> action = actionQueue.take();
                action.apply(this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
