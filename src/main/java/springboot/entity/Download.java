package springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 11/04/2017 20:18
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Entity
public class Download {

    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String name;

    @Column
    public String exp;

    @Column
    public String pict;

    @Column
    public String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getPict() {
        return pict;
    }

    public void setPict(String pict) {
        this.pict = pict;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
