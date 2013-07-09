package net.recreationmap.osm.io;

import net.recreationmap.osm.edit.Entity;
import net.recreationmap.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
