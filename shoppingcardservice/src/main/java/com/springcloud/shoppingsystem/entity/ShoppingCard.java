package com.springcloud.shoppingsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
public class ShoppingCard implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String shoppingCardName;

    @ManyToMany(targetEntity = Product.class,cascade={CascadeType.ALL})
    @JoinTable(
            name="shoppingCardProduct", //ilişkinin yönetileceği tablo adı
            joinColumns = @JoinColumn(name = "shoppingcard_id"),
            inverseJoinColumns = @JoinColumn(name="product_id"))
    private Set<Product> products;
}
