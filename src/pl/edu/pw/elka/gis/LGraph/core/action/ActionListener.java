package pl.edu.pw.elka.gis.LGraph.core.action;

/**
 * Created by mmajewski on 2016-12-17.
 */
public interface ActionListener<T extends ActionListener> {
    void registerAction(Action<T> action);
}
