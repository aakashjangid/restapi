package com.iotapi.restapp.interfaces;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.iotapi.restapp.model.BaseEntity;

@RepositoryRestResource(collectionResourceRel = "values1", path = "values1")
public interface BaseEntityInterface extends PagingAndSortingRepository<BaseEntity, Integer>{

	List<BaseEntity> findByVoltage(@Param("voltage") Double voltage);
}
