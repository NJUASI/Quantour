package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Byron Dong on 2017/4/17.
 */
public class DetectorTest {
    @Test
    public void passwordDetector() throws Exception {
    }

    @Test
    public void infoDetector() throws Exception {
    }

    @Test
    public void cycleDetector() throws Exception {
        Detector detector = new Detector();
        assertEquals(true,detector.cycleDetector("500","2"));
        assertEquals(true,detector.cycleDetector("11","1"));
    }

}