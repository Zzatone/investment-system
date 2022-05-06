package util;

import bean.History;

import java.math.BigDecimal;
import java.util.ArrayList;

public class LR {

    public double coefficients(History his){
        ArrayList<Double> x=new ArrayList<Double>();
        ArrayList<Double> y=new ArrayList<Double>();
        double x_a=0;double y_a=0;
        double em=0;double ez=0;
        double coef=0;
        for (int i=1;i<=5;i++){
            double di=i;
            x.add(Double.valueOf(di));
        }
        y.add(his.getD4());
        y.add(his.getD3());
        y.add(his.getD2());
        y.add(his.getD1());
        y.add(his.getNewday());
        //计算均值
        for (double t_x:x){
            x_a+=t_x;
        }
        for (double t_y:y){
            y_a+=t_y;
        }
        x_a=x_a/5;y_a=y_a/5;
        //计算斜率em分母和ez分子
        for (int i=0;i<x.size();i++){
            em+=((x.get(i)-x_a)*(y.get(i)-y_a));
            ez+=Math.pow((x.get(i)-x_a),2);
        }
        coef=em/ez;
        return coef;
    }

    public Double intercept(History his){
        ArrayList<Double> x=new ArrayList<Double>();
        ArrayList<Double> y=new ArrayList<Double>();
        double x_a=0;double y_a=0;
        double em=0;double ez=0;
        double coef=0;double intercept=0;
        for (int i=1;i<=5;i++){
            double di=i;
            x.add(Double.valueOf(di));
        }
        y.add(his.getD4());
        y.add(his.getD3());
        y.add(his.getD2());
        y.add(his.getD1());
        y.add(his.getNewday());
        //计算均值
        for (double t_x:x){
            x_a+=t_x;
        }
        for (double t_y:y){
            y_a+=t_y;
        }
        x_a=x_a/5;y_a=y_a/5;
        //计算斜率em分母和ez分子
        coef=coefficients(his);
        intercept=y_a-coef*x_a;
        return intercept;
    }

    public double LR_six(History his){
        ArrayList<Double> x=new ArrayList<Double>();
        ArrayList<Double> y=new ArrayList<Double>();
        double x_a=0;double y_a=0;
        double em=0;double ez=0;double ez1=0;double ez2=0;
        double coef=0;double intercept=0;
        double pre=0;
        for (int i=1;i<=5;i++){ double di=i;x.add(Double.valueOf(di)); }
        y.add(his.getD4());y.add(his.getD3());y.add(his.getD2());y.add(his.getD1());y.add(his.getNewday());
        for (double t_x:x){ x_a+=t_x; }
        for (double t_y:y){ y_a+=t_y; }
        x_a=x_a/5;y_a=y_a/5;
        for (int i=0;i<x.size();i++){
            em+=((x.get(i)-x_a)*(y.get(i)-y_a));
            ez+=Math.pow((x.get(i)-x_a),2); }
        coef=em/ez;
        intercept=y_a-coef*x_a;
        pre=coef*6+intercept;
        double mse=LR_mse(y,coef,intercept);
        BigDecimal coef_b=new BigDecimal(coef);
        coef=coef_b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal pre_b=new BigDecimal(pre);
        pre=pre_b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        BigDecimal intercept_b=new BigDecimal(intercept);
        intercept=intercept_b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("id_share:"+his.getIdshare());
        System.out.println("coefficients:"+coef);
        System.out.println("intercept:"+intercept);
        System.out.println("predict:"+pre);
        System.out.println("MSE:"+mse);
        return pre;
    }

    public double LR_mse(ArrayList<Double> y,double coef,double intercept){
        double y1=Math.pow(y.get(0)-(coef*1+intercept),2);
        double y2=Math.pow(y.get(1)-(coef*2+intercept),2);
        double y3=Math.pow(y.get(2)-(coef*3+intercept),2);
        double y4=Math.pow(y.get(3)-(coef*4+intercept),2);
        double y5=Math.pow(y.get(4)-(coef*5+intercept),2);
        double mse=(y1+y2+y3+y4+y5)/5;
        BigDecimal mse_f=new BigDecimal(mse);
        mse=mse_f.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return mse;
    }

}
