package com.optifolio.repositories;

import com.optifolio.models.CapitalTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CapitalTrackRepository extends JpaRepository<CapitalTrack,String> {
}
