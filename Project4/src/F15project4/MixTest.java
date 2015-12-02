/**
 * 
 */
package F15project4;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author xxevanxx007
 *
 */
public class MixTest {
    @Test 
    public void testProcessCommand1() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);	
    }

	@Test 
	public void testProcessCommand() {
	    Mix message = new Mix();
	    message.setInitialMessage ("This is a secret message");
	    String userMessage = message.processCommand("b a 24");
	    assertEquals("This is a secret messagea", userMessage);	}
	
	}
}