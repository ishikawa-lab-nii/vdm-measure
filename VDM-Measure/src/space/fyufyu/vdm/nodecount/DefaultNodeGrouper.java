package space.fyufyu.vdm.nodecount;

public class DefaultNodeGrouper implements NodeGrouper {

	@Override
	public String map(String nodeName) {
		return nodeName;
	}

}
