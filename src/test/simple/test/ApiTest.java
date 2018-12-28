package simple.test;

import org.junit.Test;

public class ApiTest {

    // 通过substring()获取文件名
    @Test
    public void subStringTest() {
        String url = "https://s.doubanio.com/f/shire/5522dd1f5b742d1e1394a17f44d590646b63871d/pics/book-default-medium.gif";
        url = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(url);
        //book-default-medium.gif
    }
}
