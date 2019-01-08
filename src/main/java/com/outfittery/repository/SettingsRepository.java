package com.outfittery.repository;

import com.outfittery.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import com.outfittery.entity.User;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {
  
    public Settings findByName(String name);
}
