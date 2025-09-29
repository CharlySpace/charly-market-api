package com.charly.market.file.repository;

import com.charly.market.file.model.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FileRepository extends JpaRepository<File,Long> {
}
