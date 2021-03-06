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

public class CancionRendimientoTest {

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
     * Obtener titulo test.
     */
    @Test
    public final void obtenerTituloRendimientoTest() {
        List<Cancion> testElements = new ArrayList<Cancion>();
        for (int i = 0; i < itNumber; i++) {
            String nombre = "cancionOpera";
            Integer duracion = 10;
            Cancion cancion = new Cancion(nombre, duracion);

            testElements.add(cancion);
        }

        EtmPoint point = etmMonitor
                .createPoint("CancionRendimiento:obtenerTitulo");

        for (Cancion c : testElements) {
            c.obtenerTitulo();
        }

        point.collect();
    }

    /**
     * Obtener duracion test.
     */
    @Test
    public final void obtenerDuracionRendimientoTest() {
        List<Cancion> testElements = new ArrayList<Cancion>();
        for (int i = 0; i < itNumber; i++) {
            String nombre = "cancionOpera";
            Integer duracion = 10;
            Cancion cancion = new Cancion(nombre, duracion);

            testElements.add(cancion);
        }

        EtmPoint point = etmMonitor
                .createPoint("CancionRendimiento:obtenerDuracion");

        for (Cancion c : testElements) {
            c.obtenerDuracion();
        }

        point.collect();
    }

    /**
     * Obtener lista reproduccion test.
     */
    @Test
    public final void obtenerListaReproduccionRendimientoTest() {
        List<Cancion> testElements = new ArrayList<Cancion>();
        for (int i = 0; i < itNumber; i++) {
            String nombre = "cancionOpera";
            Integer duracion = 10;
            Cancion cancion = new Cancion(nombre, duracion);

            testElements.add(cancion);
        }

        EtmPoint point = etmMonitor
                .createPoint("CancionRendimiento:obtenerListaReproduccion");

        for (Cancion c : testElements) {
            c.obtenerListaReproduccion();
        }

        point.collect();
    }

    /**
     * Buscar test.
     */
    @Test
    public final void buscarRendimientoTest() {
        ArrayList<Cancion> testElements = new ArrayList<Cancion>();
        String nombreBusqueda = "";
        for (int i = 0; i < itNumber; i++) {
            String nombre = "cancionOpera";
            Integer duracion = 10;
            Cancion cancion = new Cancion(nombre, duracion);

            testElements.add(cancion);
            nombreBusqueda = nombre;
        }

        EtmPoint point = etmMonitor.createPoint("CancionRendimiento:buscar");

        for (Cancion c : testElements) {
            c.buscar(nombreBusqueda);
        }

        point.collect();
    }

}
