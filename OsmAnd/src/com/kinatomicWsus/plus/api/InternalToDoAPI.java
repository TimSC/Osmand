package com.kinatomicWsus.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicWsus.binary.BinaryMapIndexReader;
import com.kinatomicWsus.map.ITileSource;
import com.kinatomicWsus.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
