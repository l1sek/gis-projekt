package pl.edu.pw.elka.gis.LGraph.persistance.action;

import pl.edu.pw.elka.gis.LGraph.core.action.FileAction;
import pl.edu.pw.elka.gis.LGraph.core.model.Graph;
import pl.edu.pw.elka.gis.LGraph.persistance.FileActionListener;
import pl.edu.pw.elka.gis.LGraph.persistance.exception.InvalidFileFormatException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class LoadGraphAction extends FileAction<FileActionListener> {
    private Graph graph;
    private Lock lock = new ReentrantLock();
    private Exception exception = null;

    public LoadGraphAction(File file) {
        super(file);
        lock.lock();
    }

    @Override
    public void apply(FileActionListener actionListener) {
        try {
            graph = actionListener.loadGraph(getFile());
        } catch (IOException|InvalidFileFormatException e) {
            e.printStackTrace();
            exception = e;
        } finally {
            lock.unlock();
        }
    }

    public Graph getGraph() {
        if(graph == null){
            lock.lock();
            lock.unlock();
        }
        return graph;
    }

    public Exception getException() {
        return exception;
    }
}