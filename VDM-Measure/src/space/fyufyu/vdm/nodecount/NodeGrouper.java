/**
 * 
 */
package space.fyufyu.vdm.nodecount;

/**
 * @author f-ishikawa Interface to define a function to map a detailed AST node
 *         name to a more meaningful name
 *
 */
public interface NodeGrouper {
	
	public String map(String nodeName);

}
