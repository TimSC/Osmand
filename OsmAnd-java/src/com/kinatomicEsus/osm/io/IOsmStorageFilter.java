package com.kinatomicEsus.osm.io;

import com.kinatomicEsus.osm.edit.Entity;
import com.kinatomicEsus.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
