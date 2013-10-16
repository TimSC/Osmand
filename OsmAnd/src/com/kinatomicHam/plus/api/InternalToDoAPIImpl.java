package com.kinatomicHam.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicHam.binary.BinaryMapIndexReader;
import com.kinatomicHam.map.ITileSource;
import com.kinatomicHam.map.TileSourceManager.TileSourceTemplate;
import com.kinatomicHam.plus.OsmandApplication;
import com.kinatomicHam.plus.SQLiteTileSource;

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
