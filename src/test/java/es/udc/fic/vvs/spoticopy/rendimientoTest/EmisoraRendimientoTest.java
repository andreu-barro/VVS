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
import es.udc.fic.vvs.spoticopy.contenido.Anuncio;
import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.contenido.Emisora;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

public class EmisoraRendimientoTest {

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
     * Write out the results of all of the test runs. This writes out 
     * the collected point data to an XML file located in target/jetm
     * beneath the working directory.
     * 
     * @throws Exception
     *             If any errors occur during the write-out.
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

	/** Number of iteratios to check performance. */
	private final Integer itNumber = 10000;
	
	/**
	 * Obtener titulo test.
	 */
        @Test
	public final void obtenerTituloRendimientoTest() {
		List<Emisora> testElements = new ArrayList<Emisora>();
		for (int i = 0; i < itNumber; i++) {
			Emisora emisora = new Emisora("titulo",null);

			testElements.add(emisora);
		}

		EtmPoint point = etmMonitor
				.createPoint("EmisoraRendimiento:obtenerTitulo");

		for (Emisora e : testElements) {
			e.obtenerTitulo();
		}

		point.collect();
	}}
//
//	/**
//	 * Obtener duracion test.
//	 */
//	@Test
//	public final void obtenerDuracionRendimientoTest() {
//		List<Emisora> testElements = new ArrayList<Emisora>();
//		for (int i = 0; i < itNumber; i++) {
//			Emisora emisora = new Emisora("titulo",null);
//
//			String nombreCancion = "Cancioon";
//			Integer duracionCancion = 2;
//			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
//
//			emisora.agregar(cancion, emisora);
//
//			testElements.add(emisora);
//		}
//
//		EtmPoint point = etmMonitor
//				.createPoint("EmisoraRendimiento:obtenerDuracion");
//
//		for (Emisora e : testElements) {
//			e.obtenerDuracion();
//		}
//
//		point.collect();
//	}
//
//	/**
//	 * Obtener lista reproduccion test.
//	 */
//	@Test
//	public final void obtenerListaReproduccionRendimientoTest() {
//		List<Emisora> testElements = new ArrayList<Emisora>();
//		for (int i = 0; i < itNumber; i++) {
//			Emisora emisora = new Emisora("titulo",null);
//
//			String nombreCancion = "Canciooon";
//			Integer duracionCancion = 10;
//			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
//
//			emisora.agregar(cancion, emisora);
//			testElements.add(emisora);
//		}
//
//		EtmPoint point = etmMonitor
//				.createPoint("EmisoraRendimiento:obtenerListaReproduccion");
//
//		for (Emisora e : testElements) {
//			e.obtenerListaReproduccion();
//		}
//
//		point.collect();
//	}
//
//	/**
//	 * Buscar test.
//	 */
//	@Test
//	public final void buscarRendimientoTest() {
//		List<Emisora> testElements = new ArrayList<Emisora>();
//		for (int i = 0; i < itNumber; i++) {
//			Emisora emisora = new Emisora("titulo",null);
//
//			Anuncio anuncio = new Anuncio();
//			emisora.agregar(anuncio, null);
//			
//			List<Contenido> listaReproduccion = new ArrayList<Contenido>();
//			listaReproduccion.add(emisora);
//
//			testElements.add(emisora);
//		}
//
//		EtmPoint point = etmMonitor.createPoint("EmisoraRendimiento:buscar");
//
//		for (Emisora e : testElements) {
//			e.buscar("titulo");
//		}
//
//		point.collect();
//
//	}
//	/**
//	 * Agregar test.
//	 */
//	@Test
//	public final void agregarRendimientoTest() {
//		List<Emisora> testElements = new ArrayList<Emisora>();
//		List<Cancion> testSongs = new ArrayList<Cancion>();
//
//		for (int i = 0; i < itNumber; i++) {
//			Emisora emisora = new Emisora("titulo",null);
//
//			String nombreCancion = "cancioon";
//			Integer duracionCancion = 2;
//			Cancion cancion = new Cancion(nombreCancion, duracionCancion);
//			testSongs.add(cancion);
//			testElements.add(emisora);
//		}
//
//		EtmPoint point = etmMonitor.createPoint("EmisoraRendimiento:agregar");
//
//		for (Emisora e : testElements) {
//			e.agregar(testSongs.get(0),null);
//		}
//
//		point.collect();
//	}
//
//	/**
//	 * Eliminar test.
//	 */
//	@Test
//	public final void eliminarRendimientoTest() {
//		List<Emisora> testElements = new ArrayList<Emisora>();
//
//		String nombreCancion = "caa";
//		Integer duracionCancion = 10;
//		Cancion cancion = new Cancion(nombreCancion, duracionCancion);
//		for (int i = 0; i < itNumber; i++) {
//			Emisora emisora = new Emisora("titulo",null);
//
//			emisora.agregar(cancion, emisora);
//
//			testElements.add(emisora);
//		}
//
//		EtmPoint point = etmMonitor.createPoint("EmisoraRendimiento:eliminar");
//
//		for (Emisora e : testElements) {
//			e.eliminar(cancion);
//		}
//
//		point.collect();
//
//	}
//
//
//}
