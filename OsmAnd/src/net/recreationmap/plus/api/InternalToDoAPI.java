package net.recreationmap.plus.api;

import java.io.File;
import java.util.List;

import net.recreationmap.binary.BinaryMapIndexReader;
import net.recreationmap.map.ITileSource;
import net.recreationmap.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
