package com.ericsson.group.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BaseDataList implements Serializable{

	private Collection<BaseData> baseDataList;

	public Collection<BaseData> getBaseDataList() {
		return baseDataList;
	}

	public void setBaseDataList(Collection<BaseData> baseDataList) {
		this.baseDataList = baseDataList;
	}
	
	
}
