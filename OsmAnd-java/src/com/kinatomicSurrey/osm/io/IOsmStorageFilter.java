package com.kinatomicSurrey.osm.io;

import com.kinatomicSurrey.osm.edit.Entity;
import com.kinatomicSurrey.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
