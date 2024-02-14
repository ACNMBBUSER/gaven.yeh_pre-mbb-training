package com.gaven.prembbtraining;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

@Slf4j
public class ParallelStreamsTest {

    @Test
    public void parallelStreamsTest() {

        var intStream1 = IntStream.rangeClosed(0, 10000000);
        var intStream2 = IntStream.rangeClosed(0, 10000000);


        long streamStartTime = System.nanoTime();
        intStream1.map(integer -> integer + 1);
        long streamEndTime = System.nanoTime();

        long streamDuration = streamEndTime - streamStartTime;  //divide by 1000000 to get milliseconds.


        long parallelStreamStartTime = System.nanoTime();
        intStream2.parallel().map(integer -> integer + 1);
        long parallelStreamEndTime = System.nanoTime();

        long parallelStreamDuration = parallelStreamEndTime - parallelStreamStartTime;  //divide by 1000000 to get milliseconds.

        log.info("streamDuration: {}", streamDuration);
        log.info("parallelStreamDuration: {}", parallelStreamDuration);

        Assertions.assertTrue(streamDuration > parallelStreamDuration);
    }

}
