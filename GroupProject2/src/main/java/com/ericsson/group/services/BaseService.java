package com.ericsson.group.services;

import java.sql.Date;
import java.util.Collection;

import com.ericsson.group.entities.BaseData;

public interface BaseService {
	public Collection<BaseData> getAllBaseData();
	public Collection<?> getEventIdCauseCode(Long imsi);
	public Collection<?> getCallFailures(Long imsi, Date date);
}
