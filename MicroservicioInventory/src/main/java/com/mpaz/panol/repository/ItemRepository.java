package com.mpaz.panol.repository;

import com.mpaz.panol.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}