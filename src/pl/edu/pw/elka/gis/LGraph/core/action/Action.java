package pl.edu.pw.elka.gis.LGraph.core.action;

/**
 * Created by mmajewski on 2016-12-17.
 */
public interface Action<T extends ActionListener> {
    void apply(T actionListener);
}
