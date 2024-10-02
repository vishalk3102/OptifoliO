package com.optifolio.repositories;

import com.optifolio.models.Portfolio;
import com.optifolio.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PositionRepository extends JpaRepository<Position,String> {
    Optional<Position> findByTradingSymbol(String tradingSymbol);
}
