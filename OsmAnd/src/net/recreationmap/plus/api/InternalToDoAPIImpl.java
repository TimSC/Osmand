package net.recreationmap.plus.api;

import java.io.File;
import java.util.List;

import net.recreationmap.binary.BinaryMapIndexReader;
import net.recreationmap.map.ITileSource;
import net.recreationmap.map.TileSourceManager.TileSourceTemplate;
import net.recreationmap.plus.OsmandApplication;
import net.recreationmap.plus.SQLiteTileSource;

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
