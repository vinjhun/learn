package com.example.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "A_ENTITLEMENT_DETAILS")
public class EntitlementDetailsEntity
{
    private String id;
    private BigDecimal customerRate;
    private BigDecimal profitRate;
    private BigDecimal productRate;
    private String specialRateFlag;

    @Id
    @Column(name="ID",nullable=false,length=30)
    @SequenceGenerator(name="ID",initialValue=8)
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Column(name="CUSTOMER_RATE",precision=18)
    public BigDecimal getCustomerRate()
    {
        return customerRate;
    }

    public void setCustomerRate(BigDecimal customerRate)
    {
        this.customerRate = customerRate;
    }

    @Column(name="PROFIT_RATE",precision=18)
    public BigDecimal getProfitRate()
    {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate)
    {
        this.profitRate = profitRate;
    }

    @Column(name="PRODUCT_RATE",precision=18)
    public BigDecimal getProductRate()
    {
        return productRate;
    }

    public void setProductRate(BigDecimal productRate)
    {
        this.productRate = productRate;
    }

    @Column(name="SPECIAL_RATE_FLAG",length=100)
    public String getSpecialRateFlag()
    {
        return specialRateFlag;
    }

    public void setSpecialRateFlag(String specialRateFlag)
    {
        this.specialRateFlag = specialRateFlag;
    }

}
