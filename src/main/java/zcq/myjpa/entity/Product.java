package zcq.myjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Entity
@Table(name = "mj_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mj_code")
    private String code;
    @Column(name = "mj_type")
    private String type;
    @Column(name = "mj_name")
    private String name;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "mj_bill_id")
    @JsonIgnoreProperties(value = {"product"},ignoreUnknown = true)
    private Bill bill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(){}
}
