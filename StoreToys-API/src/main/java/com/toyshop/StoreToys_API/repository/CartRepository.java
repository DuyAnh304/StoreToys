package com.toyshop.StoreToys_API.repository;

import com.toyshop.StoreToys_API.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.userId = :id")
    Optional<List<Cart>> findByUserId(@Param("id") int id);

    @Modifying // tra ve so ban ghi bi anh huong
    @Query("UPDATE Cart c SET c.quantity = :quantity " +
            "WHERE c.id = :cartId AND c.userId = :userid AND c.product.productId = :productId")
    int updateQuantity(@Param("quantity") int quantity, @Param("cartId") int cartId,
                              @Param("userid") int userId, @Param("productId") int productId);

    void deleteByUserId(int id);
}
