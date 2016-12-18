package pl.edu.pw.elka.gis.LGraph.persistence.action;

import pl.edu.pw.elka.gis.LGraph.core.action.FileAction;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.persistence.FileActionListener;

import java.io.File;
import java.io.IOException;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class SaveGraphAction extends FileAction<FileActionListener> {
    private Graph graph;

    public SaveGraphAction(File file, Graph graph) {
        super(file);
        this.graph = graph;
    }

    @Override
    public void apply(FileActionListener actionListener) {
        try {
            actionListener.saveGraph(graph, getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
