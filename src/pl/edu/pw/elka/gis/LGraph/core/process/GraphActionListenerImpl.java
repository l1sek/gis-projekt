package pl.edu.pw.elka.gis.LGraph.core.process;

import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class GraphActionListenerImpl extends RunnableActionListener<GraphActionListener> implements GraphActionListener {

    private Graph graph = new Graph();
    private Graph lineGraph = new Graph();
    private GraphActionListener graphView;
    private GraphActionListener lineView;

    public GraphActionListenerImpl() {
        this.graphView = new DummyViewActionListener();
        this.lineView = new DummyViewActionListener();
    }

    public GraphActionListenerImpl(GraphActionListener graphView, GraphActionListener lineView) {
        this.graphView = graphView;
        this.lineView = lineView;
    }

    public Graph getGraph() {
        return graph;
    }

    public Graph getLineGraph() {
        return lineGraph;
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

    public void addNode(GraphNode graphNode) {
        if(graph.findNode(graphNode.getName()) == null) {
            graph.addNode(graphNode);
            graphView.addNode(graphNode);
        }
    }

    public void addEdge(GraphEdge graphEdge) {
        if (graph.findEdge(graphEdge) == null) {
            graph.addEdge(graphEdge);
            graphView.addEdge(graphEdge);

            GraphNode lineNode = new GraphNode(lineNodeName(graphEdge));
            lineGraph.addNode(lineNode);
            lineView.addNode(lineNode);

            for (GraphNode neighbour : graph.findNeighbours(graphEdge.getFirst())) {
                if (!neighbour.equals(graphEdge.getSecond())) {
                    GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getFirst(), neighbour));
                    GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                    lineGraph.addEdge(lineEdge);
                    lineView.addEdge(lineEdge);
                }
            }

            for (GraphNode neighbour : graph.findNeighbours(graphEdge.getSecond())) {
                if (!neighbour.equals(graphEdge.getFirst())) {
                    GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getSecond(), neighbour));
                    GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                    lineGraph.addEdge(lineEdge);
                    lineView.addEdge(lineEdge);
                }
            }
        }
    }

    public void removeEdge(GraphEdge graphEdge) {
        if(graph.findEdge(graphEdge) != null) {
            graph.removeEdge(graphEdge);
            graphView.removeEdge(graphEdge);

            GraphNode lineNode = new GraphNode(lineNodeName(graphEdge));

            for (GraphNode neighbour : graph.findNeighbours(graphEdge.getFirst())) {
                if (!neighbour.equals(graphEdge.getSecond())) {
                    GraphNode lineNeighbour = new GraphNode(lineNodeName(graphEdge.getFirst(), neighbour));
                    GraphEdge lineEdge = new GraphEdge(lineNeighbour, lineNode);
                    lineGraph.removeEdge(lineEdge);
                    lineView.removeEdge(lineEdge);
                }
            }

            for (GraphNode neighbour : graph.findNeighbours(graphEdge.getSecond())) {
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
    }

    public void removeNode(GraphNode graphNode) {
        if(graph.findNode(graphNode.getName()) != null) {
            for(GraphEdge edge : graph.findEdgesToNode(graphNode)){
                this.removeEdge(edge);
            }
            graph.removeNode(graphNode);
            graphView.removeNode(graphNode);
        }
    }
}
