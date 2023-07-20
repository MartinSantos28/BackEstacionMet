package com.crzindustries.estacionmetereologica.repositories;

import com.crzindustries.estacionmetereologica.entities.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDataRepository extends JpaRepository<Data, Long> {

    @Query(value = "SELECT * FROM data WHERE sensor_type = ?1", nativeQuery = true)
    List<Data> findAllBySensorType(String sensorType);
}
