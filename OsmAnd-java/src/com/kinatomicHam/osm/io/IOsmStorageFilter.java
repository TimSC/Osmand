package com.kinatomicWsus.osm.io;

import com.kinatomicWsus.osm.edit.Entity;
import com.kinatomicWsus.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
