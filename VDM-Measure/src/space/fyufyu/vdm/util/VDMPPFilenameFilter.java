package space.fyufyu.vdm.util;

import java.io.File;
import java.io.FilenameFilter;

public class VDMPPFilenameFilter implements FilenameFilter {

	private static VDMPPFilenameFilter singleton = new VDMPPFilenameFilter();
	
	protected VDMPPFilenameFilter() {
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(".vpp") || name.endsWith(".vdmpp");
	}

	public static VDMPPFilenameFilter getInstance(){
		return singleton;
	}
	
	public static boolean isVDMPPFile(File dir, String name){
		return singleton.accept(dir, name);
	}
	
}
