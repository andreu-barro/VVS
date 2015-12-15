package es.udc.fic.vvs.spoticopy.rendimientoTest;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;
import es.udc.fic.vvs.spoticopy.contenido.Cancion;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;
import es.udc.fic.vvs.spoticopy.token.Token;

public class TokenRendimientoTest {

    private static EtmMonitor etmMonitor;

    /**
     * Configure JETM
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        BasicEtmConfigurator.configure();

        etmMonitor = EtmManager.getEtmMonitor();
        etmMonitor.start();
    }

    /**
     * Write out the results of all of the test runs. This writes out the
     * collected point data to an XML file located in target/jetm beneath the
     * working directory.
     *
     * @throws Exception If any errors occur during the write-out.
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        if (etmMonitor != null) {
            etmMonitor.stop();

            final File timingDirectory = new File("target/jetm");
            FileUtils.forceMkdir(timingDirectory);

            final File timingFile = new File(timingDirectory, "Timing" + ".xml");
            final FileWriter writer = new FileWriter(timingFile);
            try {
                etmMonitor.render(new BindingMeasurementRenderer(new XmlAggregateBinder(), writer));
            } finally {
                writer.close();
            }
        }
    }

	// Performance Variables
    /**
     * Number of iteratios to check performance.
     */
    private final Integer itNumber = 10000;

    /**
     * Alta test.
     */
    @Test
    public final void altaRendimientoTest() {
        List<Token> testElements = new ArrayList<Token>();
        for (int i = 0; i < itNumber; i++) {
            String strToken = "ADMIN";
            Token token = new Token(strToken);

            testElements.add(token);
        }

        EtmPoint point = etmMonitor
                .createPoint("TokenRendimiento:alta");

        for (Token t : testElements) {
            t.alta();
        }

        point.collect();
    }

        /**
     * Baja test.
     */
    @Test
    public final void bajaRendimientoTest() {
        List<Token> testElements = new ArrayList<Token>();
        for (int i = 0; i < itNumber; i++) {
            String strToken = "ADMIN";
            Token token = new Token(strToken);

            testElements.add(token);
        }

        EtmPoint point = etmMonitor
                .createPoint("TokenRendimiento:baja");

        for (Token t : testElements) {
            String strToken = "ADMIN";
            String resultado2 = t.alta();
            t.baja(resultado2);
        }

        point.collect();
    }

}
