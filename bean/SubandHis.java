package bean;

public class SubandHis {
    private int id_user;
    private String id_share;
    private int type; //1为订阅，2为历史记录

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getId_share() {
        return id_share;
    }

    public void setId_share(String id_share) {
        this.id_share = id_share;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
