package play;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.*;

/**
 * Created by mz on 4/23/15.
 */
public class StatisticsSource implements Source {
    private Source source = null;
    private List data = null;

    public StatisticsSource(Source source) {
        this.source = source;
    }

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
        System.out.println("Start StatisticsSource.processData ...");
        List analysisResult = source.getData();
        data = new ArrayList();

        //todo: analysis string by counting unique tokens
        //at first copy analysisResult in order to avoid concurrent access errors
        List subject = new ArrayList();
        for( Object obj: analysisResult ) {
            subject.add(obj);
        }

        int i = 0;
        for ( Object obj : analysisResult ) {
            i++;

            List results = new ArrayList();
            data.add(results);

            Map map = (Map) obj;
            int j = 0;
            for ( Object sub : subject ) {
                j++;
                if ( j < i ) {
                    continue;
                }
                Map map2 = (Map) sub;
                // do linear model between map and map2
                SimpleRegression reg = linearRegression(map, map2);
                results.add(reg);

                //System.out.println(map.size() + " " + map2.size());
                //System.out.println("RSquare: " + reg.getRSquare() + ", P: " + reg.getSignificance());

            }
        }



        System.out.println("finish StatisticsSource.processData!");

    }

    private SimpleRegression linearRegression(Map map1, Map map2) {
        SimpleRegression regression = new SimpleRegression(true);
        Map usedTokens = new HashMap();

        Map mapx = map1;
        Map mapy = map2;

        if ( map2.size() < map1.size() ) {
            mapx = map2;
            mapy = map1;
        }

        // at first iterate map1 against map2
        Set keySetx = mapx.keySet();
        Iterator iterx = keySetx.iterator();
        while ( iterx.hasNext() ) {
            String token = (String) iterx.next();
            Integer countx = (Integer) mapx.get(token);

            Integer county = (Integer) mapy.get(token);
            if ( county == null ) {
                county = new Integer(0);
            }

            usedTokens.put(token, "");
            regression.addData((double) countx, (double) county);
        }

/*
        // then iterate map2 against map1
        Set keySet2 = map2.keySet();
        Iterator iter2 = keySet2.iterator();
        while ( iter2.hasNext() ) {
            String token = (String) iter2.next();
            if ( usedTokens.get(token) != null ) {
                continue;
            }

            Integer count2 = (Integer) map2.get(token);
            Integer count1 = (Integer) map1.get(token);
            if ( count1 == null ) {
                count1 = new Integer(0);
            }

            regression.addData((double) count1, (double) count2);
        }
*/
        return regression;
    }
}
