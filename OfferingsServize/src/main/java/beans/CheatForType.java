package beans;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class CheatForType extends PanacheEntity {
    private String offeringType;
    private int cheatPercent;

    public CheatForType() {
        this.offeringType = "Nothing";
        this.cheatPercent = 0;
    }

    public CheatForType(String offeringType, int cheatPercent) {
        this.offeringType = offeringType;
        this.cheatPercent = cheatPercent;
    }

    public String getOfferingType() {
        return offeringType;
    }

    public void setOfferingType(String offeringType) {
        this.offeringType = offeringType;
    }

    public int getCheatPercent() {
        return cheatPercent;
    }

    public void setCheatPercent(int cheatPercent) {
        this.cheatPercent = cheatPercent;
    }
}
