/**
 * 
 */
package F15project4;

/**
 * @author xxevanxx007
 *
 */
public class MixTest {
    @Test 
    public void testProcessCommand() {
        Mix message = new Mix();
        message.setInitialMessage ("This is a secret message");
        String userMessage = message.processCommand("b a 0");
        assertEquals("aThis is a secret message", userMessage);	}
}
