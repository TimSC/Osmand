package com.kinatomicHamp.plus.osmedit;

import com.kinatomicHamp.data.Amenity;
import com.kinatomicHamp.osm.edit.EntityInfo;
import com.kinatomicHamp.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
