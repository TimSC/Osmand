package com.kinatomicEsus.plus.osmedit;

import com.kinatomicEsus.data.Amenity;
import com.kinatomicEsus.osm.edit.EntityInfo;
import com.kinatomicEsus.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
