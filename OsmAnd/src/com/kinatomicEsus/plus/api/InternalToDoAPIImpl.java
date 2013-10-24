package com.kinatomicEsus.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicEsus.binary.BinaryMapIndexReader;
import com.kinatomicEsus.map.ITileSource;
import com.kinatomicEsus.map.TileSourceManager.TileSourceTemplate;
import com.kinatomicEsus.plus.OsmandApplication;
import com.kinatomicEsus.plus.SQLiteTileSource;

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
