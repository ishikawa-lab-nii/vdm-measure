/**
 * 
 */
package space.fyufyu.vdm.nodecount;

/**
 * @author f-ishikawa Interface to define a function to map a detailed AST node
 *         name to a more meaningful name
 *
 */
public abstract class NodeGrouper {
	
	public abstract String map(String nodeName);

	public MapCounter apply(MapCounter counter) {
		MapCounter ret = new MapCounter();
		for (String key : counter.keySet()) {
			ret.count(map(key), counter.getValue(key));
		}
		return ret;
	}

}
