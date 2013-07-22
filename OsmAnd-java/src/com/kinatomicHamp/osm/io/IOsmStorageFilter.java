package com.kinatomicHamp.osm.io;

import com.kinatomicHamp.osm.edit.Entity;
import com.kinatomicHamp.osm.edit.Entity.EntityId;

public interface IOsmStorageFilter {
	
	public boolean acceptEntityToLoad(OsmBaseStorage storage, EntityId entityId, Entity entity);

}
