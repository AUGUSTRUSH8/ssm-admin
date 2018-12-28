package simple.test;

import com.ssm.demo.utils.MD5Util;
import org.junit.Test;

public class UtilTest {

    // 得到MD5加密的内容
    @Test
    public void md5Test() {
        System.out.println(MD5Util.MD5Encode("123456", "UTF-8"));
        //e10adc3949ba59abbe56e057f20f883e
    }

}
