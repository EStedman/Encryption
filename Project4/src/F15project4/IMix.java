/**
 * 
 */
package F15project4;

/**
 * Created with IntelliJ IDEA.
 * User: fergusor
 * Date: 
 * Time:
 * To change this template use File | Settings | File Templates.
 */
public interface IMix {

	   /** processes the given mix command */
	   String processCommand(String command);
		
	   /** set the original message */
	   void setInitialMessage(String message);

	}
