package pl.edu.pw.elka.gis.LGraph.persistance;

import pl.edu.pw.elka.gis.LGraph.core.action.ActionListener;
import pl.edu.pw.elka.gis.LGraph.core.action.GraphActionListener;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphEdge;
import pl.edu.pw.elka.gis.LGraph.core.model.GraphNode;
import pl.edu.pw.elka.gis.LGraph.core.process.RunnableActionListener;
import pl.edu.pw.elka.gis.LGraph.core.process.action.AddEdgeAction;
import pl.edu.pw.elka.gis.LGraph.core.process.action.AddNodeAction;
import pl.edu.pw.elka.gis.LGraph.persistance.exception.InvalidFileFormatException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class FileActionListener extends RunnableActionListener<FileActionListener> {
    private FileStreamFactory fileStreamFactory;
    private boolean saveNodeNames = true;

    public boolean isSaveNodeNames() {
        return saveNodeNames;
    }

    public void setSaveNodeNames(boolean saveNodeNames) {
        this.saveNodeNames = saveNodeNames;
    }

    public FileActionListener() {
        this(new FileStreamFactoryImpl());
    }

    public FileActionListener(FileStreamFactory fileStreamFactory) {
        this.fileStreamFactory = fileStreamFactory;
    }

    public void saveGraph(Graph graph, File file) throws IOException {
        BufferedWriter fileWriter = new BufferedWriter(fileStreamFactory.fileWriterStream(file));
        List<GraphNode> sortedNodes = new ArrayList<>(graph.getNodeSet());
        Collections.sort(sortedNodes);
        for(GraphNode currentNode : sortedNodes){
            Set<GraphNode> neighbours = graph.findNeighbours(currentNode);
            int written = 0;
            for(GraphNode potentialNeighbour : sortedNodes){
                if(neighbours.contains(potentialNeighbour)){
                    fileWriter.append("1");
                }else{
                    fileWriter.append("0");
                }
                ++written;
                if(written < sortedNodes.size()){
                    fileWriter.append(", ");
                }else{
                    fileWriter.append(System.lineSeparator());
                }
            }
        }
        fileWriter.append(System.lineSeparator());
        if(saveNodeNames) {
            for (GraphNode currentNode : sortedNodes) {
                fileWriter.append("# ").append(currentNode.getName()).append(System.lineSeparator());
            }
            fileWriter.append(System.lineSeparator());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void loadGraph(File file, ActionListener<GraphActionListener> actionListener) throws IOException, InvalidFileFormatException {
        Map<Integer, List<Integer>> nodes = new HashMap<>();
        Map<Integer, String> names = new HashMap<>();

        BufferedReader fileReader = new BufferedReader(fileStreamFactory.fileReaderStream(file));
        String line;
        int lineCount = 0;
        int rowCount = 0;
        int expectedNodes = 0;

        //parse adjacency matrix
        while(true){
            line = fileReader.readLine();
            if(line == null) {
                throw new InvalidFileFormatException("File ended unexpectedly");
            }
            line = line.replaceAll("\\s|,", "");

            ++lineCount;
            if(line.matches(".*[^01].*")){
                throw new InvalidFileFormatException("Error at line "+lineCount);
            }
            if(line.isEmpty()){
                continue;
            }

            String[] nodeNeighbours = line.split("");
            if(rowCount == 0){
                expectedNodes = nodeNeighbours.length;
                for (int i = 0; i < expectedNodes; i++) {
                    nodes.put(i, new ArrayList<Integer>());
                    names.put(i, Integer.toString(i));
                }
            }else if(nodeNeighbours.length != expectedNodes){
                throw new InvalidFileFormatException("Error at line "+lineCount);
            }
            for (int i = 0; i < expectedNodes; ++i) {
                if(nodeNeighbours[i].equals("1")){
                    nodes.get(rowCount).add(i);
                }
            }
            ++rowCount;
            if(rowCount >= expectedNodes){
                break;
            }
        }

        //parse node names
        rowCount = 0;
        while(true){
            line = fileReader.readLine();
            if(line == null) {
                break;
            }
            line = line.trim();

            ++lineCount;
            if(line.matches("^[^#].*")){
                throw new InvalidFileFormatException("Error at line "+lineCount);
            }
            if(line.isEmpty()){
                continue;
            }

            names.put(rowCount, line.substring(1).trim());

            ++rowCount;
        }

        fileReader.close();

        //construct graph
        Map<Integer, GraphNode> graphNodeMap = new HashMap<>();
        for(Map.Entry<Integer, String> nodeName : names.entrySet()){
            GraphNode newGraphNode = new GraphNode(nodeName.getValue());
            graphNodeMap.put(nodeName.getKey(), newGraphNode);
            actionListener.registerAction(new AddNodeAction(newGraphNode));
        }
        Set<GraphEdge> graphEdgeSet = new HashSet<>();
        for(Integer nodeNum : nodes.keySet()){
            GraphNode node = graphNodeMap.get(nodeNum);
            for(Integer neigh : nodes.get(nodeNum)){
                GraphNode neighbour = graphNodeMap.get(neigh);
                graphEdgeSet.add(new GraphEdge(node, neighbour));
            }
        }
        for(GraphEdge graphEdge : graphEdgeSet){
            actionListener.registerAction(new AddEdgeAction(graphEdge));
        }
    }

}
