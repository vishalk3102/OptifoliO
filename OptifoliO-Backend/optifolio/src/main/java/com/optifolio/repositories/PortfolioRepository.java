package com.optifolio.repositories;

import com.optifolio.models.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio,String> {

    Optional<Portfolio> findByTradingSymbol(String tradingSymbol);
}
