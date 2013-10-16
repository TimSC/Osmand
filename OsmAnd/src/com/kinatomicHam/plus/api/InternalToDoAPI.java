package com.kinatomicHam.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicHam.binary.BinaryMapIndexReader;
import com.kinatomicHam.map.ITileSource;
import com.kinatomicHam.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
