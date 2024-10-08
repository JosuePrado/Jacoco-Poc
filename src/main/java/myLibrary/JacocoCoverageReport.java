package myLibrary;

import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.IMethodCoverage;
import org.jacoco.core.tools.ExecFileLoader;

import java.io.File;
import java.io.IOException;

public class JacocoCoverageReport {

    public static void main(String[] args) throws IOException {
        File execFile = new File("build/jacoco/test.exec");
        if (!execFile.exists()) {
            throw new IOException("El archivo de cobertura no se encuentra en: " + execFile.getAbsolutePath());
        }

        ExecFileLoader loader = new ExecFileLoader();
        loader.load(execFile);

        CoverageBuilder coverageBuilder = new CoverageBuilder();
        Analyzer analyzer = new Analyzer(loader.getExecutionDataStore(), coverageBuilder);

        analyzer.analyzeAll(new File("build/classes/java/main/myLibrary"));

        for (IClassCoverage classCoverage : coverageBuilder.getClasses()) {
            System.out.println("===========================================");
            System.out.println("Clase: " + classCoverage.getName());
            System.out.println("Total de líneas: " + classCoverage.getLineCounter().getTotalCount());
            System.out.println("Líneas cubiertas: " + classCoverage.getLineCounter().getCoveredCount());
            System.out.println("Líneas no cubiertas: " + classCoverage.getLineCounter().getMissedCount());
            System.out.printf("Cobertura de líneas: %.2f%%\n", 100.0 * classCoverage.getLineCounter().getCoveredRatio());

            System.out.println("Métodos:");
            for (IMethodCoverage methodCoverage : classCoverage.getMethods()) {
                System.out.println("  - " + methodCoverage.getName());
                System.out.println("    Total de líneas: " + methodCoverage.getLineCounter().getTotalCount());
                System.out.println("    Líneas cubiertas: " + methodCoverage.getLineCounter().getCoveredCount());
                System.out.println("    Líneas no cubiertas: " + methodCoverage.getLineCounter().getMissedCount());
                System.out.printf("    Cobertura de líneas: %.2f%%\n", 100.0 * methodCoverage.getLineCounter().getCoveredRatio());
            }
            System.out.println("===========================================");
        }
    }
}