package pl.edu.pw.elka.gis.LGraph.persistance;

import java.io.*;

/**
 * Created by mmajewski on 2016-12-17.
 */
public interface FileStreamFactory {
    OutputStreamWriter fileWriterStream(File file) throws FileNotFoundException;
    InputStreamReader fileReaderStream(File file) throws FileNotFoundException;
}
