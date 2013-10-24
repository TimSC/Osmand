package com.kinatomicWsus.plus.osmedit;

import com.kinatomicWsus.data.Amenity;
import com.kinatomicWsus.osm.edit.EntityInfo;
import com.kinatomicWsus.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
