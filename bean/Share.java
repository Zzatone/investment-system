package bean;

public class Share {
    private String id;
    private String sname;
    private double nprice;//今收
    private double oprice;//昨收
    private double turnover;//成交量
    private double tprice;//成交额
    private double pluse;//振幅
    private double P;//分类
    private double cprice;//涨跌额
    private double creng;//涨跌幅

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public double getNprice() {
        return nprice;
    }

    public void setNprice(double nprice) {
        this.nprice = nprice;
    }

    public double getOprice() {
        return oprice;
    }

    public void setOprice(double oprice) {
        this.oprice = oprice;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    public double getTprice() {
        return tprice;
    }

    public void setTprice(double tprice) {
        this.tprice = tprice;
    }

    public double getPluse() {
        return pluse;
    }

    public void setPluse(double pluse) {
        this.pluse = pluse;
    }

    public double getP() {
        return P;
    }

    public void setP(double p) {
        P = p;
    }

    public double getCprice() {
        return cprice;
    }

    public void setCprice() {
        this.cprice = nprice-oprice;
    }

    public double getCreng() {
        return creng;
    }

    public void setCreng() {
        this.creng = (nprice-oprice)/nprice;
    }
}
