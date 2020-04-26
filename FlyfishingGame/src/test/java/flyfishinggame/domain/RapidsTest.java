package flyfishinggame.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tamsi
 */
public class RapidsTest {

    Rapids r;

    @Test
    public void thereIsZeroObjectsAtTheBeginning() {
        r = new Rapids();
        //Check rocks.
        assertEquals(0, r.rocks.size());
        //Check buffers.
        assertEquals(0, r.buffers.size());
        //Check taken area.
        assertEquals(0, r.takenAreas.size());
        //Check bubbles.
        assertEquals(200, r.bubbles.length);
        //Check leaves.
        assertEquals(3, r.leaves.length);
    }
}
