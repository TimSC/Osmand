package com.kinatomicSurrey.plus.osmedit;

import com.kinatomicSurrey.data.Amenity;
import com.kinatomicSurrey.osm.edit.EntityInfo;
import com.kinatomicSurrey.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
