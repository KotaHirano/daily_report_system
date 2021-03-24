package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllFavorites",
        query = "SELECT f FROM Favorite AS f ORDER BY f.id DESC"
    ),
    @NamedQuery(name = "getFvoritesCount", query = "SELECT COUNT(f) FROM Report AS f"),
    @NamedQuery(
            name = "checkfavorite",
            query = "SELECT e FROM Favorite AS e WHERE e.employee = :employee_id AND e.report = :report_id"),


})
@Table(name = "favorite")
public class Favorite {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee", nullable = false)
    private Integer employee;

    @Column(name = "report", nullable = false)
    private Integer report;

    @Column(name = "madeday", nullable = false)
    private Timestamp madeday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmoloyee() {
        return getEmoloyee();
    }

    public void setEmoloyee(Integer emoloyee) {
        this.employee = emoloyee;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public Timestamp getMadeday() {
        return madeday;
    }

    public void setMadeday(Timestamp madeday) {
        this.madeday = madeday;
    }
}
