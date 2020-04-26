
package flyfishinggame.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tamsi
 */
public class FishTest {
    
   Fish fish;
   
   @Test
   public void constructorCreatesFish() {
       fish = new Fish(1,1,1);
       assertEquals(new Fish(1,1,1).getClass(), fish.getClass());
   }
   
   @Test
   public void settingAndGettingHookedWorks() {
       fish = new Fish(1,1,1);
       fish.hooked(true);
       assertEquals(true, fish.isHooked());
       fish.hooked(false);
       assertEquals(false, fish.isHooked());
   }
}
