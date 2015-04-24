package play;

import java.io.File;

/**
 * Created by mz on 4/23/15.
 *
 * ******************************************************************
 *                          README
 *
 * Entry point to start the project as standalone application
 *
 * This is a source/sink dataflow with filter design pattern
 *
 * Source interface defines API for data exchange between different
 * sources or between source/sink
 *
 * Dataflow: TokenAnalysis(kickoff point) -> FileSource -> ReaderSource
 *           -> AnalysisSource -> StatisticsSource -> ReporterSink
 *
 * *******************************************************************
 *
 */

public class TokenAnalysis {
    public static int tokenSize = 3;
    public static String sourceDir = ".";

    public static void main(String[] args) {
        if ( args == null || args.length < 2 ) {
            System.err.println("Put token size (int) and a directory (full path) as parameters");
            return;
        }

        String tokenSizeStr = args[0];
        sourceDir = args[1];

        try { tokenSize = Integer.parseInt(tokenSizeStr); } catch (Exception e) {}

        System.out.println("Input processing has been done: token size: " + tokenSize + ", source directory: " + sourceDir);
        System.out.println("Start data analysis ...");

        kickoff();

        System.out.println("All jobs have been done!");
    }

    /**
     * We should have a assembly class that manages the relations between sources or source/sink.
     * Here we just assemble all classes in the kickoff() method in order to
     * simplify the design
     */
    public static void kickoff() {
        Source firstStep = new FileSource(sourceDir);
        Source secondStep = new ReaderSource(firstStep);
        Source thirdStep = new AnalysisSource(secondStep, tokenSize);
        Source fourthStep = new StatisticsSource(thirdStep);

        ReporterSink finalStep = new ReporterSink(fourthStep, firstStep);
        finalStep.report();
    }
}
