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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by mmajewski on 2016-12-17.
 */
@RunWith(Parameterized.class)
public class StructureFailFileActionListenerTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<? extends Object> data() throws URISyntaxException {
        File[] testFiles = new File(StructureFailFileActionListenerTest.class.getClassLoader().getResource("resources/structure_fail").toURI()).listFiles();
        List<String> testCases = new ArrayList<>();
        for(File file : testFiles){
            if(file.getName().endsWith("_sfail.graph")){
                testCases.add("structure_fail/"+file.getName());
            }
        }
        return testCases;
    }

    @Parameterized.Parameter
    public String testCase;

    @Test(expected = InvalidGraphStructureException.class)
    public void successfulLoadSaveGraphTest() throws IOException, InvalidFileFormatException, URISyntaxException, InvalidGraphStructureException {
        //given
        InputStream inputStream = StructureFailFileActionListenerTest.class.getClassLoader().getResourceAsStream(testCase);
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