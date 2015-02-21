package space.fyufyu.vdm.nodecount;

import java.io.FilenameFilter;

/**
 * Command to give configuration for node count
 * @author f-ishikawa
 *
 */
public class NodeCountCommand {
	
	String targetFileName;
	
	String reportFilePrefix;
	
	FilenameFilter filenameFilter;
	
	NodeGrouper grouper;
	
	public NodeCountCommand(String targetFileName, String reportFilePrefix, FilenameFilter filenameFilter){
		this.targetFileName = targetFileName;
		this.reportFilePrefix = reportFilePrefix;
		this.filenameFilter = filenameFilter;
		this.grouper = new DefaultNodeGrouper();
	}
	
	public NodeCountCommand(String targetFileName, String reportFilePrefix, FilenameFilter filenameFilter, NodeGrouper grouper){
		this.targetFileName = targetFileName;
		this.reportFilePrefix = reportFilePrefix;
		this.filenameFilter = filenameFilter;
		this.grouper = grouper;
	}
	

}
