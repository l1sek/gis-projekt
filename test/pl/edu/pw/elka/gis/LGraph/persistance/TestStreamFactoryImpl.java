package pl.edu.pw.elka.gis.LGraph.persistance;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by mmajewski on 2016-12-17.
 */
public class TestStreamFactoryImpl implements FileStreamFactory {
    private ByteArrayOutputStream[] outputStreamArray;
    private int currentOutStream = 0;
    private InputStream inputStream = null;

    public TestStreamFactoryImpl(InputStream inputStream, int outputStreams) throws FileNotFoundException {
        this.inputStream = inputStream;
        this.outputStreamArray = new ByteArrayOutputStream[outputStreams];
        for (int i = 0; i < outputStreams; i++) {
            outputStreamArray[i] = new ByteArrayOutputStream();
        }
    }

    public String getOutput(int streamNum) throws UnsupportedEncodingException {
        return outputStreamArray[streamNum].toString(StandardCharsets.UTF_8.name());
    }

    @Override
    public OutputStreamWriter fileWriterStream(File file) throws FileNotFoundException {
        return new OutputStreamWriter(outputStreamArray[currentOutStream++], StandardCharsets.UTF_8);
    }

    @Override
    public InputStreamReader fileReaderStream(File file) throws FileNotFoundException {
        return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
}
