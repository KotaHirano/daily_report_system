package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@NamedQueries({
    @NamedQuery(
        name = "getAllfavorite",
        query = "SELECT f FROM Favorite AS f ORDER BY f.id DESC"
    )})
@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee")
    private Integer emoloyee;

    @Column(name = "report")
    private Integer report;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmoloyee() {
        return emoloyee;
    }

    public void setEmoloyee(Integer emoloyee) {
        this.emoloyee = emoloyee;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

}
