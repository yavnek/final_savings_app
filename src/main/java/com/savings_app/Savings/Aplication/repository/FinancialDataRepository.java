package com.savings_app.Savings.Aplication.repository;

import com.savings_app.Savings.Aplication.entity.FinancialData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialDataRepository extends JpaRepository<FinancialData, Long> {

}
