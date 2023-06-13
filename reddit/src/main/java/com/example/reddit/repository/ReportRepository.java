package com.example.reddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.reddit.model.Report;

public interface ReportRepository extends JpaRepository<Report, Long>  {

}
