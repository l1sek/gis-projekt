package pl.edu.pw.elka.gis.LGraph.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.pw.elka.gis.LGraph.core.process.GraphActionListenerImpl;
import pl.edu.pw.elka.gis.LGraph.persistence.exception.InvalidFileFormatException;
import pl.edu.pw.elka.gis.LGraph.persistence.exception.InvalidGraphStructureException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by mmajewski on 2016-12-17.
 */
@RunWith(Parameterized.class)
public class FormatFailFileActionListenerTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<? extends Object> data() throws URISyntaxException {
        File[] testFiles = new File(FormatFailFileActionListenerTest.class.getClassLoader().getResource("resources/format_fail").toURI()).listFiles();
        List<String> testCases = new ArrayList<>();
        for(File file : testFiles){
            if(file.getName().endsWith("_ffail.graph")){
                testCases.add("format_fail/"+file.getName());
            }
        }
        return testCases;
    }

    @Parameterized.Parameter
    public String testCase;

    @Test(expected = InvalidFileFormatException.class)
    public void successfulLoadSaveGraphTest() throws IOException, InvalidFileFormatException, URISyntaxException, InvalidGraphStructureException {
        //given
        InputStream inputStream = FormatFailFileActionListenerTest.class.getClassLoader().getResourceAsStream(testCase);
        TestStreamFactoryImpl testStreamFactory = new TestStreamFactoryImpl(inputStream, 2);
        FileActionListener fileActionListener = new FileActionListener(testStreamFactory);
        GraphActionListenerImpl graphActionListener = new GraphActionListenerImpl();

        //when
        fileActionListener.loadGraph(null, graphActionListener);
        graphActionListener.runInForeground();

        //then
        //exception is thrown
    }
}