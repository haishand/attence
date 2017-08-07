package obj.pcard;

import java.util.Date;

public class PCardRec {
    boolean state;  // 0: 上班 1: 下班
    String depart;
    String id;
    String name;
    Date punchTime;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    @Override
    public String toString() {
        return "PCardRec{" +
                "state=" + state +
                ", depart='" + depart + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", punchTime=" + punchTime +
                '}';
    }
}
