package pl.edu.pw.elka.gis.LGraph.core.action;

import java.io.File;

/**
 * Created by mmajewski on 2016-12-17.
 */
public abstract class FileAction<T extends ActionListener> implements Action<T> {
    private File file;

    public FileAction(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
