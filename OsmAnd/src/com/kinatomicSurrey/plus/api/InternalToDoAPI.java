package com.kinatomicSurrey.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicSurrey.binary.BinaryMapIndexReader;
import com.kinatomicSurrey.map.ITileSource;
import com.kinatomicSurrey.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
