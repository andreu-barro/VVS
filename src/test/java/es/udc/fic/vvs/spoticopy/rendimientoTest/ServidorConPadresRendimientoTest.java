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
import es.udc.fic.vvs.spoticopy.servidor.ServidorConPadres;
import es.udc.fic.vvs.spoticopy.servidor.ServidorNormal;
import es.udc.fic.vvs.spoticopy.token.Token;
import etm.core.configuration.BasicEtmConfigurator;
import etm.core.configuration.EtmManager;
import etm.core.monitor.EtmMonitor;
import etm.core.monitor.EtmPoint;

public class ServidorConPadresRendimientoTest {
	
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
    

	@Test
	public final void buscarRendimientoTest(){
		List<ServidorConPadres> testElementos = new ArrayList<ServidorConPadres>();
		List<Contenido> contenido = new ArrayList<Contenido>();
		List<String> tokenValido2 = new ArrayList<String>();
		for (int i = 0; i < itNumber; i++) {
			String nombre ="serv";
			Token tokenValido = new Token("admin");

			Servidor servidorPadre = new ServidorNormal(nombre,contenido, tokenValido);
			//ServidorConPadres(String nombre, Servidor padre, List<Contenido> contenidos, Token token)
			ServidorConPadres s1 = new ServidorConPadres(nombre,servidorPadre, contenido,tokenValido);

			String titulo = "titulo1";
			String titulo1 = "titulo1";
			String titulo2 = "titulo3";

			Integer duracion = 2;
			Integer duracion1 = 3;
			Integer duracion2 = 4;

			Contenido cancion = new Cancion(titulo, duracion);
			Contenido cancion1 = new Cancion(titulo1, duracion1);
			Contenido cancion2 = new Cancion(titulo2, duracion2);
			tokenValido2.add(s1.alta());
			servidorPadre.agregar(cancion, "admin");
			servidorPadre.agregar(cancion1, "admin");
			servidorPadre.agregar(cancion2, "admin");
			servidorPadre.agregar(cancion2, "admin");

			testElementos.add(s1);
		}

		EtmPoint point = etmMonitor.createPoint("ServidorConPadres:buscar");
		int i = 0;
		for (ServidorConPadres s : testElementos) {
			s.buscar("titulo1", "admin");
			i++;
		}

		point.collect();
	}
}
