package pl.edu.pw.elka.gis.LGraph.persistence;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class FileStreamFactoryImpl implements FileStreamFactory {
    @Override
    public OutputStreamWriter fileWriterStream(File file) throws FileNotFoundException {
        return new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8);
    }

    @Override
    public InputStreamReader fileReaderStream(File file) throws FileNotFoundException {
        return new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    }
}
