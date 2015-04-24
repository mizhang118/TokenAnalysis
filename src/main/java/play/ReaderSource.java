package play;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.List;

/**
 * Created by mz on 4/23/15.
 */
public class ReaderSource extends AbstractSource {
    private Source source = null;

    public ReaderSource(Source source) {
        this.source = source;
    }

    @Override
    public Source getSource() {
        System.out.println(this.source.getData().size() + " files have been received by ReaderSource");
        return this.source;
    }

    /**
     * Read an input File into and return a string
     *
     * @param obj
     * @return
     */
    @Override
    public Object process(Object obj) {
        if ( obj == null ) {
            System.err.println("Process a null object in ReaderSource.process()");
            return null;
        }

        if ( !(obj instanceof File) ) {
            System.err.println("Object is not as expected type of File. Its actual type is " + obj.getClass().getName());
            return null;
        }

        File file = (File) obj;
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ( (line = reader.readLine()) != null ) {
                builder.append(line.trim().toLowerCase());
            }

            reader.close();
        }
        catch (Exception e) {
            System.err.println("Error in ReaderSource to read a file of " + file.getName());
            e.printStackTrace(System.err);
        }

        return builder.toString();
    }
}
