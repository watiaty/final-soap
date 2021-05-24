package by.epam.shop.soap.bean;

import javax.persistence.*;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "currency")
    private String currency;
    @Column(name = "value")
    private Integer value;
    @Column(name = "id_product")
    private Integer productId;

    public Price(int id, String currency, int value, int productId) {
        this.id = id;
        this.currency = currency;
        this.value = value;
        this.productId = productId;
    }

    public Price(String currency, Integer value, Integer productId) {
        this.currency = currency;
        this.value = value;
        this.productId = productId;
    }

    public Price() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Price{");
        sb.append("id=").append(id);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
