package com.springcloud.shoppingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product implements Serializable{
    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private Integer count;
    @JsonIgnore
    @ManyToMany(targetEntity = ShoppingCard.class,mappedBy = "products",cascade = CascadeType.ALL) //İlişkiyi yöneten taraf ShoppingCard sınıfında ki "products" property'sidir.
    private Set<ShoppingCard> shoppingCards;

}
