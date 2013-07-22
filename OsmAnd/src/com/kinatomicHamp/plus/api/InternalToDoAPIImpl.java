package com.kinatomicHamp.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicHamp.binary.BinaryMapIndexReader;
import com.kinatomicHamp.map.ITileSource;
import com.kinatomicHamp.map.TileSourceManager.TileSourceTemplate;
import com.kinatomicHamp.plus.OsmandApplication;
import com.kinatomicHamp.plus.SQLiteTileSource;

public class InternalToDoAPIImpl implements InternalToDoAPI {

	private OsmandApplication app;

	public InternalToDoAPIImpl(OsmandApplication app) {
		this.app = app;
	}

	@Override
	public BinaryMapIndexReader[] getRoutingMapFiles() {
		return app.getResourceManager().getRoutingMapFiles();
	}

	@Override
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates) {
		return new SQLiteTileSource(app, dir, knownTemplates);
	}

}
