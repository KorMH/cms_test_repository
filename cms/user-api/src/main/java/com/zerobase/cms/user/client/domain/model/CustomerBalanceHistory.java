package com.zerobase.cms.user.client.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerBalanceHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
    private Customer customer;
    //변경된 돈
    private Integer changeMoney;
    //해당 시점 잔액
    private Integer currentMoney;
    private String fromMessage;
    private String description;
}