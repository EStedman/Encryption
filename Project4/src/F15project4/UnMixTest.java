/**
 * 
 */
package F15project4;

/**
 * @author xxevanxx007
 *
 */
public class UnMixTest {
    @Test 
    public void testUndoProcessCommand() {
        	// Creates the file for testing, 
    		// this code could be removed if the file already exist
    		Mix message = new Mix();
        	message.setInitialMessage ("This is a secret message");
        	String userMessage = message.processCommand("b a 0");
        	message.processCommand("s testIt");
       
        	UnMix unMessage = new UnMix();
        	String original = unMessage.UnMixUsingFile ("testIt", userMessage);
        	assertEquals(original, "This is a secret message");
}
