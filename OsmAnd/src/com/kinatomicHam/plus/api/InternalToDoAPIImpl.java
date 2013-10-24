package com.kinatomicWsus.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicWsus.binary.BinaryMapIndexReader;
import com.kinatomicWsus.map.ITileSource;
import com.kinatomicWsus.map.TileSourceManager.TileSourceTemplate;
import com.kinatomicWsus.plus.OsmandApplication;
import com.kinatomicWsus.plus.SQLiteTileSource;

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
