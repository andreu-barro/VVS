package es.udc.fic.vvs.spoticopy.rendimientoTest;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.jetm.reporting.BindingMeasurementRenderer;
import com.google.code.jetm.reporting.xml.XmlAggregateBinder;

import es.udc.fic.vvs.spoticopy.contenido.Cancion;
import es.udc.fic.vvs.spoticopy.contenido.Contenido;
import es.udc.fic.vvs.spoticopy.servidor.Servidor;
import es.udc.fic.vvs.spoticopy.servidor.ServidorGenerico;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;
import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

public class ServidorNormalRendimientoTest {

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
    
    /**
     * Number of iteratios to check performance.
     */
    private final Integer itNumber = 10000;
    
	/**
	 * Obtener nombre test.
	 */
	@Test
	public final void obtenerNombreRendimientoTest() {
		List<Contenido> testContenido = new ArrayList<Contenido>();
		List<Servidor> testElemento = new ArrayList<Servidor>();
		for (int i = 0; i < itNumber; i++) {
			String nombre = "serv";
			Token token = new Token ("admin");
			Servidor s = new ServidorNormal(nombre, testContenido, token);
			testElemento.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorNormal:obtenerTitulo");

		for (Servidor s : testElemento) {
			s.obtenerNombre();
		}

		point.collect();
	}
	
	@Test
	public final void altaRendimientoTest(){
		Token token = new Token ("admin");
		List<Contenido> lista = new ArrayList<Contenido>();
		Servidor s = new ServidorNormal("1",lista, token);
		
		EtmPoint point = etmMonitor.createPoint("ServidorNormal:alta");
		for (int i = 0; i < itNumber; i++){
			s.alta();
		}
		point.collect();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public final void agregarRendimientoTest(){

		List<Cancion> testElementos = new ArrayList<Cancion>();
		List<Contenido> contenido = new ArrayList<Contenido>();
		Token token = new Token("admin");
		Servidor serv = new ServidorNormal("serv", contenido, token);

		for (int i = 0; i < itNumber; i++) {

			String nombreCancion = "can";
			Integer duracionCancion = 5;
			Cancion cancion = new Cancion(nombreCancion, duracionCancion);

			testElementos.add(cancion);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorNormal:agregar");

		for (Cancion c : testElementos) {
			serv.agregar(c,"admin");
		}

		point.collect();
	}
	
	@Test
	public void bajaRendimientoTest(){

		List<String> testElementos = new ArrayList<String>();
		String nombre = "serv";
		Token token = new Token("admin");
		List<Contenido> lista = new ArrayList<Contenido>();
		Servidor servidor = new ServidorNormal(nombre, lista, token);
		for (int i = 0; i < itNumber; i++) {
			
			testElementos.add(servidor.alta());
		}

		EtmPoint point = etmMonitor.createPoint("ServidorNormal:baja");

		for (String tokens : testElementos) {
			servidor.baja(tokens);
		}

		point.collect();
	
	}
	
	@Test
	public final void eliminarRendimientoTest(){
		List<Servidor> testElementos = new ArrayList<Servidor>();
		List<Contenido> contenido = new ArrayList<Contenido>();
		final Token token = new Token("admin");

		String titulo = "can";
		Integer duracion = 2;
		Cancion cancion = new Cancion(titulo, duracion);
		for (int i = 0; i < itNumber; i++) {
			String nombre = "serv";
			Servidor s = new ServidorNormal(nombre,contenido, token);
			s.agregar((Contenido)cancion, "admin");
			testElementos.add(s);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorNormal:eliminar");

		for (Servidor s : testElementos) {
			s.eliminar(cancion, "admin");
		}

		point.collect();
	}
	
}
