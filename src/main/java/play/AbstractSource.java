package play;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mz on 4/23/15.
 */
public abstract class AbstractSource implements Source {

    private List data = null;

    @Override
    public List getData() {
        if ( data != null ) {
            return data;
        }

        processData();
        return data;
    }

    @Override
    public void processData() {
        Source source = getSource();
        if ( source == null ) {
            return;
        }

        System.out.println("Start " + source.getClass().getName() + " ...");
        List input = source.getData();
        if ( input == null ) {
            return;
        }

        data = new ArrayList();
        for( Object obj : input ) {
            data.add(process(obj));
        }

        System.out.println("Finish " + source.getClass().getName() + "!");
    }


    public abstract Source getSource();

    public abstract Object process(Object input);
}
