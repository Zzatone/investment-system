package util;

import bean.POI;
import bean.Share;
import bean.User;
import dao.POIDao;
import dao.ShareDao;
import dao.UserDao;

import java.text.NumberFormat;
import java.util.ArrayList;

public class UserCF {

    public ArrayList<POI> add_userP(){
        ArrayList<POI> arrayList=new POIDao().selectAllP();
        return arrayList;
    }

    public double CF_like(POI user,POI poi){//计算相似度
        double up1,up2,up3,up4,up5;
        double p1,p2,p3,p4,p5;
        up1=user.getP1();
        up2=user.getP2();
        up3=user.getP3();
        up4=user.getP4();
        up5=user.getP5();
        p1=poi.getP1();
        p2=poi.getP2();
        p3=poi.getP3();
        p4=poi.getP4();
        p5=poi.getP5();
        double up_ave=(up1+up2+up3+up4+up5)/5;
        double p_ave=(p1+p2+p3+p4+p5)/5;
        if (up_ave==0||p_ave==0){
            return 0;
        }
        up1=up1-up_ave;up2=up2-up_ave;up3=up3-up_ave;up4=up4-up_ave;up5=up5-up_ave;
        p1=p1-p_ave;p2=p2-p_ave;p3=p3-p_ave;p4=p4-p_ave;p5=p5-p_ave;
        double like=(up1*p1+up2*p2+up3*p3+up4*p4+up5*p5)/(Math.sqrt(up1*up1+up2*up2+up3*up3+up4*up4+up5*up5)*Math.sqrt(p1*p1+p2*p2+p3*p3+p4*p4+p5*p5));
        return like;
    }



    public int CF_res(POI user){//得到最类似的user_id
        ArrayList<POI> arrayList=add_userP();
        double res=0;
        int id = 0;
        int all_row=new UserDao().selectrow();
        for (int i=0;i<all_row;i++){
            double temp=CF_like(user,arrayList.get(i));
            if (temp>res && user.getId()!=arrayList.get(i).getId()){
                res=temp;
                id=arrayList.get(i).getId();
            }
        }
        return id;
    }

    public int CF_res_second(POI user,int id_rec){//得到第二类似的user_id
        ArrayList<POI> arrayList=add_userP();
        double res=0;
        int id = 0;
        int all_row=new UserDao().selectrow();
        for (int i=0;i<all_row;i++){
            double temp=CF_like(user,arrayList.get(i));
            if (temp>res && user.getId()!=arrayList.get(i).getId()){
                res=temp;
                if (arrayList.get(i).getId()==id_rec){
                    break;
                }
                id=arrayList.get(i).getId();
            }
        }
        return id;
    }

    public ArrayList<Integer> CF_res_fina(POI user,POI p1,POI p2){//比较两个相似，得到预测P
        //double threshold=(user.getP1()+user.getP2()+user.getP3()+user.getP4()+user.getP5())/5;
        double p1_like=CF_like(user,p1);
        double p2_like=CF_like(user,p2);
//        double R1=(p1_like*p1.getP1()+p2_like*p2.getP1())/(p1_like+p2_like);
//        double R2=(p1_like*p1.getP2()+p2_like*p2.getP2())/(p1_like+p2_like);
//        double R3=(p1_like*p1.getP3()+p2_like*p2.getP3())/(p1_like+p2_like);
//        double R4=(p1_like*p1.getP4()+p2_like*p2.getP4())/(p1_like+p2_like);
//        double R5=(p1_like*p1.getP5()+p2_like*p2.getP5())/(p1_like+p2_like);
        double user_ave=(user.getP1()+user.getP2()+user.getP3()+user.getP4()+user.getP5())/5;
        double p1_ave=(p1.getP1()+p1.getP2()+p1.getP3()+p1.getP4()+p1.getP5())/5;
        double p2_ave=(p2.getP1()+p2.getP2()+p2.getP3()+p2.getP4()+p2.getP5())/5;
        double R1=user_ave+(p1_like*(p1.getP1()-p1_ave)+p2_like*(p2.getP1()-p2_ave))/(p1_like+p2_like);
        double R2=user_ave+(p1_like*(p1.getP2()-p1_ave)+p2_like*(p2.getP2()-p2_ave))/(p1_like+p2_like);
        double R3=user_ave+(p1_like*(p1.getP3()-p1_ave)+p2_like*(p2.getP3()-p2_ave))/(p1_like+p2_like);
        double R4=user_ave+(p1_like*(p1.getP4()-p1_ave)+p2_like*(p2.getP4()-p2_ave))/(p1_like+p2_like);
        double R5=user_ave+(p1_like*(p1.getP5()-p1_ave)+p2_like*(p2.getP5()-p2_ave))/(p1_like+p2_like);
//        double threshold=(R1+R2+R3+R4+R5)/5;
        double threshold=(R1+R2+R3+R4+R5)/5;
        ArrayList<Integer> res=new ArrayList<Integer>();
        if (R1>=threshold){
            res.add(1);
        }
        if (R2>=threshold){
            res.add(2);
        }
        if (R3>=threshold){
            res.add(3);
        }
        if (R4>=threshold){
            res.add(4);
        }
        if (R5>=threshold){
            res.add(5);
        }
        if ( R1<threshold && R2<threshold && R3<threshold && R4<threshold && R5<threshold ){
            double temp=R1;int i=1;
            if(R2>temp) {
                temp=R2;
                i=2;
            }
            if(R3>temp) {
                temp=R3;
                i=3;
            }
            if(R4>temp) {
                temp=R4;
                i=4;
            }
            if(R5>temp) {
                i=5;
            }
            res.add(i);
        }
        return res;
    }

    public int CF_highest_P(POI poi){
        double temp=poi.getP1();int i=1;
        if(poi.getP2()>temp) {
            temp=poi.getP2();
            i=2;
        }
        if(poi.getP3()>temp) {
            temp=poi.getP3();
            i=3;
        }
        if(poi.getP4()>temp) {
            temp=poi.getP4();
            i=4;
        }
        if(poi.getP5()>temp) {
            i=5;
        }
        return i;
    }

    public ArrayList<Share> CF_fina(String name,String revenue) {//得到最后的推荐股份
        long startTime = System.currentTimeMillis();    //获取开始时间
        POI user_poi = new POI();
        int rev = Integer.valueOf(revenue);
        rev = rev / 500;
        user_poi = new UserDao().select_userP_name(name);
        int id1 = new UserCF().CF_res(user_poi);
        int id2 = new UserCF().CF_res_second(user_poi, id1);
        String id1_S = Integer.toString(id1);
        String id2_S = Integer.toString(id2);
        POI poi1 = new POIDao().select_POI(id1_S);
        POI poi2 = new POIDao().select_POI(id2_S);
        ArrayList<Integer> res = new UserCF().CF_res_fina(user_poi, poi1, poi2);
        int size = res.size();
        ArrayList<Share> shares;
        switch (size) {
            case 1:
                shares = new ShareDao().Select_CF_1(res.get(0), rev);
                break;
            case 2:
                shares = new ShareDao().Select_CF_2(res.get(0), res.get(1), rev);
                break;
            case 3:
                shares = new ShareDao().Select_CF_3(res.get(0), res.get(1), res.get(2), rev);
                break;
            case 4:
                shares = new ShareDao().Select_CF_4(res.get(0), res.get(1), res.get(2), res.get(3), rev);
                break;
            case 5:
                shares = new ShareDao().Select_CF_5(res.get(0), res.get(1), res.get(2), res.get(3), res.get(4), rev);
                break;
            default:
                shares = new ShareDao().SelectFiveShareByRevenue(rev);
        }
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
        return shares;
    }

    public boolean CF_precise(String name){
        POI poi = new UserDao().select_userP_name(name);
        int id1 = new UserCF().CF_res(poi);
        int id2 = new UserCF().CF_res_second(poi, id1);
        String id1_S = Integer.toString(id1);
        String id2_S = Integer.toString(id2);
        POI poi1 = new POIDao().select_POI(id1_S);
        POI poi2 = new POIDao().select_POI(id2_S);
        ArrayList<Integer> res = new UserCF().CF_res_fina(poi, poi1, poi2);
        int highest_P=CF_highest_P(poi);
        System.out.println("用户点击量：P1:"+poi.getP1()+"    P2:"+poi.getP2()+"    P3:"+poi.getP3()+"    P4:"+poi.getP4()+"    P5:"+poi.getP5());
        System.out.print("推荐分类P：");
        for (Integer i:res){
            System.out.print(i+"    ");
        }
        System.out.println(" ");
        //System.out.println("MAE:"+MAE);
        double MAE=new UserCF().CF_MAE(poi,poi1,poi2);
        return true;
    }

    public double CF_MAE(POI user,POI p1,POI p2){
        double p1_like=CF_like(user,p1);
        double p2_like=CF_like(user,p2);
        double user_ave=(user.getP1()+user.getP2()+user.getP3()+user.getP4()+user.getP5())/5;
        double p1_ave=(p1.getP1()+p1.getP2()+p1.getP3()+p1.getP4()+p1.getP5())/5;
        double p2_ave=(p2.getP1()+p2.getP2()+p2.getP3()+p2.getP4()+p2.getP5())/5;
        double R1=user_ave+(p1_like*(p1.getP1()-p1_ave)+p2_like*(p2.getP1()-p2_ave))/(p1_like+p2_like);
        double R2=user_ave+(p1_like*(p1.getP2()-p1_ave)+p2_like*(p2.getP2()-p2_ave))/(p1_like+p2_like);
        double R3=user_ave+(p1_like*(p1.getP3()-p1_ave)+p2_like*(p2.getP3()-p2_ave))/(p1_like+p2_like);
        double R4=user_ave+(p1_like*(p1.getP4()-p1_ave)+p2_like*(p2.getP4()-p2_ave))/(p1_like+p2_like);
        double R5=user_ave+(p1_like*(p1.getP5()-p1_ave)+p2_like*(p2.getP5()-p2_ave))/(p1_like+p2_like);
        double mae1= Math.abs(R1 - user.getP1());
        double mae2= Math.abs(R2 - user.getP2());
        double mae3= Math.abs(R3 - user.getP3());
        double mae4= Math.abs(R4 - user.getP4());
        double mae5= Math.abs(R5 - user.getP5());
        double MAE=mae1+mae2+mae3+mae4+mae5;
        NumberFormat numberFormat=NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String MAE_String=numberFormat.format(MAE/5);
        System.out.println("MAE:"+MAE_String);
        return MAE;
    }

    public boolean CF_precise_all(){
        ArrayList<POI> pois=add_userP();
        int size=pois.size();
        int i=0;
        for (POI poi:pois){
            int id1 = new UserCF().CF_res(poi);
            int id2 = new UserCF().CF_res_second(poi, id1);
            String id1_S = Integer.toString(id1);
            String id2_S = Integer.toString(id2);
            POI poi1 = new POIDao().select_POI(id1_S);
            POI poi2 = new POIDao().select_POI(id2_S);
            ArrayList<Integer> res = new UserCF().CF_res_fina(poi, poi1, poi2);
            int highest_P=CF_highest_P(poi);
            if (res.contains(highest_P)){
                i++;
            }
        }
        NumberFormat numberFormat=NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String precise=numberFormat.format((float)i/(float)size*100);
        System.out.println("正确个数："+i+"/"+size);
        System.out.println("准确率："+precise+"%");
        return true;
    }
}
