package pl.edu.pw.elka.gis.LGraph.persistance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.edu.pw.elka.gis.LGraph.core.process.GraphActionListenerImpl;
import pl.edu.pw.elka.gis.LGraph.persistance.exception.InvalidFileFormatException;
import pl.edu.pw.elka.gis.LGraph.persistance.exception.InvalidGraphStructureException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by mmajewski on 2016-12-17.
 */
@RunWith(Parameterized.class)
public class SuccessfulFileActionListenerTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<? extends Object> data() throws URISyntaxException {
        File[] testFiles = new File(SuccessfulFileActionListenerTest.class.getClassLoader().getResource("resources/success").toURI()).listFiles();
        List<String> testCases = new ArrayList<>();
        for(File file : testFiles){
            if(file.getName().endsWith("_input.graph")){
                testCases.add("success/"+file.getName().replace("_input.graph", ""));
            }
        }
        return testCases;
    }

    @Parameterized.Parameter
    public String testCase;

    @Test
    public void successfulLoadSaveGraphTest() throws IOException, InvalidFileFormatException, URISyntaxException, InvalidGraphStructureException {
        //given
        InputStream inputStream = SuccessfulFileActionListenerTest.class.getClassLoader().getResourceAsStream(testCase+"_input.graph");
        String expectedGraph = new String(Files.readAllBytes(Paths.get(SuccessfulFileActionListenerTest.class.getClassLoader().getResource(testCase+"_output.graph").toURI())));
        String expectedLineGraph = new String(Files.readAllBytes(Paths.get(SuccessfulFileActionListenerTest.class.getClassLoader().getResource(testCase+"_output.lgraph").toURI())));
        TestStreamFactoryImpl testStreamFactory = new TestStreamFactoryImpl(inputStream, 2);
        FileActionListener fileActionListener = new FileActionListener(testStreamFactory);
        GraphActionListenerImpl graphActionListener = new GraphActionListenerImpl();

        //when
        fileActionListener.loadGraph(null, graphActionListener);
        graphActionListener.runInForeground();
        fileActionListener.saveGraph(graphActionListener.getGraph(), null);//written to output stream 0
        fileActionListener.saveGraph(graphActionListener.getLineGraph(), null);//written to output stream 1

        //then
        assertEquals(expectedGraph, testStreamFactory.getOutput(0));
        assertEquals(expectedLineGraph, testStreamFactory.getOutput(1));

    }
}