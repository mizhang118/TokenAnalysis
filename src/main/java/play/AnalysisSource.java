package play;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mz on 4/23/15.
 */
public class AnalysisSource extends AbstractSource {
    private Source source = null;
    private int tokenSize = 3;

    public AnalysisSource(Source source, int tokenSize) {
        this.source = source;
        this.tokenSize = tokenSize;
    }

    @Override
    public Source getSource() {
        System.out.println(this.source.getData().size() + " strings have been received by AnalysisSource");
        return this.source;
    }

    @Override
    public Object process(Object obj) {

        if ( obj == null ) {
            System.err.println("Process a null object in AnalysisSource.process()");
            return null;
        }

        if ( !(obj instanceof String) ) {
            System.err.println("Object is not as expected type of String. Its actual type is " + obj.getClass().getName());
            return null;
        }

        String text = (String) obj;
        Map<String, Integer> map = new HashMap<String, Integer>(10000);

        //System.out.println(obj.toString());
        int len = text.length();
        int start = 0;
        int end = start + tokenSize + 1;
        while ( end <= len ) {
            String token = text.substring(start, end);
            Integer count = map.get(token);
            if ( count == null ) {
                map.put(token, new Integer(1));
            }
            else {
                map.put(token, (count + 1));
            }

            start++;
            end++;
        }

//        System.out.println("The Map size is " + map.size());

        return map;
    }
}
