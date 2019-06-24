package com.bettercode.connect.repository;

import com.bettercode.connect.entity.HolidayAppoval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHolidayApprovalRepository extends JpaRepository<HolidayAppoval, Long> {
}
