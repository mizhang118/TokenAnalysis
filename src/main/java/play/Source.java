package play;

import java.util.List;

/**
 * Created by mz on 4/23/15.
 *
 * This is a source/sink dataflow with filter design pattern
 *
 * Source interface defines API for data exchange between different
 * sources or between source/sink
 *
 * Dataflow: TokenAnalysis(kickoff point) -> FileSource -> ReaderSource
 *           -> AnalysisSource -> ReporterSink
 */
public interface Source {
    public List getData();
    public void processData();
}
