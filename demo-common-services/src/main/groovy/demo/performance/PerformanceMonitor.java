package demo.performance;

import org.apache.commons.lang3.time.StopWatch;

/**
 * Created by vtajzich
 */
public interface PerformanceMonitor {

    StopWatch createStopWatch();
}
