package com.kinatomicHam.plus.osmedit;

import com.kinatomicHam.data.Amenity;
import com.kinatomicHam.osm.edit.EntityInfo;
import com.kinatomicHam.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
