package com.kinatomicHamp.plus.api;

import java.io.File;
import java.util.List;

import com.kinatomicHamp.binary.BinaryMapIndexReader;
import com.kinatomicHamp.map.ITileSource;
import com.kinatomicHamp.map.TileSourceManager.TileSourceTemplate;

public interface InternalToDoAPI {

	public BinaryMapIndexReader[] getRoutingMapFiles();
	
	public ITileSource newSqliteTileSource(File dir, List<TileSourceTemplate> knownTemplates);


}
