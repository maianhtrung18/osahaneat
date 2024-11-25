package com.cybersoft.osahaneat.repository;

import com.cybersoft.osahaneat.entity.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
//    @Query(value = "SELECT * FROM Restaurant ORDER BY is_freeship DESC LIMIT :limit", nativeQuery = true)
//    List<Restaurant> findTopRestaurant(@Param("limit") int limit);
//    Page<Restaurant> findAllRestaurant(Pageable pageable);
Page<Restaurant> findAllByOrderByIsFreeShipDesc(Pageable pageable);
}
