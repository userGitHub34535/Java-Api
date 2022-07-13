package com.subbiah.app.FetchApi.Repo;

import com.subbiah.app.FetchApi.Models.PointsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepo extends JpaRepository<PointsEntity, Long>{

}
