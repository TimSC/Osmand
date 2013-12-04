package com.kinatomicSurrey.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicSurrey.binary.BinaryMapIndexReader;
import com.kinatomicSurrey.map.ITileSource;
import com.kinatomicSurrey.map.TileSourceManager.TileSourceTemplate;
import com.kinatomicSurrey.plus.OsmandApplication;
import com.kinatomicSurrey.plus.SQLiteTileSource;

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
