package play;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz on 4/23/15.
 */
public class ReporterSink {
    private Source input = null;
    private Source fileSource = null;

    private File outDir = new File("/Users/mz/test/token/out");

    public ReporterSink(Source input, Source fs) {
        this.input = input;
        this.fileSource = fs;
    }

    public void report() {
        System.out.println("Start reporter sink ....");
        List statResults = input.getData();
        List files = (ArrayList)fileSource.getData();

        System.out.println("Statistic results: " + statResults.size() + ", Input file number: " + files.size());

        //todo: data report
        File statsReport = new File(outDir, "stats.txt");
        try {
            FileWriter writer = new FileWriter(statsReport);
            //print header
            writer.write("Doc1\tDoc2\tRSquare\tLogRSquare\tPValue\tLogPValue\n");
            int i = 0;
            for (Object obj : statResults) {
                List list = (List) obj;
                int j = i;
                for (Object stat : list) {
                    SimpleRegression reg = (SimpleRegression) stat;
                    double r2 = reg.getRSquare();
                    double logr2 = -20d;
                    if ( r2 > 0 ) {
                        logr2 = Math.log10(r2);
                    }
                    double p = reg.getSignificance();
                    double logp = 20d;
                    if ( p > 0 ) {
                        logp = 0 - Math.log10(p);
                    }

                    writer.write( ((File)files.get(i)).getName() + "\t" + ((File)files.get(j)).getName() + "\t" + r2 + "\t" + logr2 + "\t" + p + "\t" + logp + "\n");

                    j++;
                }

                i++;
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace(System.err);
        }




        System.out.println("finish reporter sink!");
    }
}
