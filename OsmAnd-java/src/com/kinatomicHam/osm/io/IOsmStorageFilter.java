package com.kinatomicHam.osm.io;

import com.kinatomicHam.osm.edit.Entity;
import com.kinatomicHam.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
