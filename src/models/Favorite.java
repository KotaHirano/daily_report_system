package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllFavorites",
        query = "SELECT f FROM Favorite AS f WHERE f.employee = :employee_id ORDER BY f.id DESC"
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

    @ManyToOne
    @JoinColumn(name = "employee", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report", nullable = false)
    private Report report;

    @Column(name = "madeday", nullable = false)
    private Timestamp madeday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Timestamp getMadeday() {
        return madeday;
    }

    public void setMadeday(Timestamp madeday) {
        this.madeday = madeday;
    }


}
