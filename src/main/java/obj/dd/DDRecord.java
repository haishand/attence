package obj.dd;

import java.util.Date;

public class DDRecord {
    String name;
    String id;
    String dep;
    Date ptime;
    String site;
    String address;

    @Override
    public String toString() {
        return "DDRecord{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", dep='" + dep + '\'' +
                ", ptime=" + ptime +
                ", site='" + site + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAvail(String site, String addr) {
        if(site.contains("青岛软件园") || addr.contains("宁夏路") || addr.contains("金门路")) {
            return true;
        }
    }
}
