package com.kinatomicEsus.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicEsus.binary.BinaryMapIndexReader;
import com.kinatomicEsus.map.ITileSource;
import com.kinatomicEsus.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
