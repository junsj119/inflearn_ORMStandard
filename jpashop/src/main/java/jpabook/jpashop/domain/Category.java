package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="PAREAMT_ID")
    private Category parent;

    @OneToMany
    private List<Category> child = new ArrayList<>();

    // joinColumns -> 내가 조인하는 것, inverseJoinColumns-> 반대쪽이 조인하는 것
    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM",
            joinColumns = @JoinColumn(name="CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<Item>items = new ArrayList<>();
}
