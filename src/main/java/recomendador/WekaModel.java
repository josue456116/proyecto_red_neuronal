package recomendador;



import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.DenseInstance;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class WekaModel {

    private Classifier clasificador;
    private Instances estructuraDatos;

    public WekaModel(String rutaModelo, String rutaArff) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaModelo))) {
            clasificador = (Classifier) ois.readObject();
        }

        DataSource source = new DataSource(rutaArff);
        estructuraDatos = source.getDataSet();
        if (estructuraDatos.classIndex() == -1) {
            estructuraDatos.setClassIndex(estructuraDatos.numAttributes() - 1);
        }
    }

    public String recomendarCurso(double[] notas) throws Exception {
        if (notas.length < 3) {
            throw new IllegalArgumentException("Se requieren 3 notas");
        }

        Instance nuevaInstancia = new DenseInstance(estructuraDatos.numAttributes());
        nuevaInstancia.setDataset(estructuraDatos);

        for (int i = 0; i < 3; i++) {
            nuevaInstancia.setValue(i, notas[i]);
        }

        // Obtiene la distribución de probabilidad para cada clase
        double[] dist = clasificador.distributionForInstance(nuevaInstancia);

        // Imprime la distribución para depurar
        System.out.println("Distribución de clases:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(estructuraDatos.classAttribute().value(i) + ": " + dist[i]);
        }

        // Predicción (clase con mayor probabilidad)
        double resultado = clasificador.classifyInstance(nuevaInstancia);
        int claseIndex = (int) resultado;

        if (claseIndex < 0 || claseIndex >= estructuraDatos.classAttribute().numValues()) {
            throw new Exception("Predicción fuera de rango");
        }

        System.out.println("Clase predicha: " + estructuraDatos.classAttribute().value(claseIndex));

        return estructuraDatos.classAttribute().value(claseIndex);
    }
}
