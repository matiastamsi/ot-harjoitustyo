
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
    public void thereIsZeroRocksAtTheBeginning() {
        r = new Rapids();
        //In the beginning there is zero rocks.
        assertEquals(0, r.rocks.size());
    }
}
