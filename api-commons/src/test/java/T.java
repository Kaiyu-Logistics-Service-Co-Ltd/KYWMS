import org.junit.Test;
import work.kaiyu.wms.domain.CommonResult;
import work.kaiyu.wms.domain.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T {
    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String sysTime1= sdf.format(new Date());
//        String sysTime2= new Date().toString();
//        System.out.println(sysTime2);
        Date utilDate=new java.util.Date();
        Timestamp timestamp= new Timestamp(utilDate.getTime());
        System.out.println(utilDate.getTime());
        System.out.println(utilDate);
        System.out.println(timestamp);
    }
}
