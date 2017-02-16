package com.ericsson.group.dao;

import java.sql.Date;
import java.util.Collection;

import javax.ejb.Local;

import com.ericsson.group.entities.BaseData;

@Local
public interface BaseDAO {
	public Collection<BaseData> getBaseData();
	public Collection<?> getEventIdCauseCode(Long imsi);
	public Collection<?> getCallFailures(Long imsi, Date date);
}
