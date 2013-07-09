package net.recreationmap.plus.osmedit;

import net.recreationmap.data.Amenity;
import net.recreationmap.osm.edit.EntityInfo;
import net.recreationmap.osm.edit.Node;

public interface OpenstreetmapUtil {
	
	public EntityInfo getEntityInfo();
	
	public Node commitNodeImpl(OsmPoint.Action action, Node n, EntityInfo info, String comment, boolean closeChangeSet);
	
	public void closeChangeSet();
	
	public Node loadNode(Amenity n);
	
}
