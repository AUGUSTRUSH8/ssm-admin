package dao.test;

import com.ssm.demo.dao.PictureDao;
import com.ssm.demo.entity.Picture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PictureDaoTest {

    @Autowired
    private PictureDao pictureDao;

    /**
     * pictureDao的insertPicture()方法单元测试
     */
    @Test
    public void insertPictureTest() {
        Picture picture = new Picture();
        picture.setPath("path");
        picture.setRemark("DAO单元测试5");
        //Assert为断言语法
        Assert.assertTrue(pictureDao.insertPicture(picture) > 0);
    }

    /**
     * pictureDao的insertPicture()方法单元测试
     */
    @Test
    public void findPictureByIdTest() {
        Picture picture1 = pictureDao.findPictureById(900);
        //Assert为断言语法
        //id为900的图片为空
        Assert.assertTrue(picture1 != null);


        Picture picture2 = pictureDao.findPictureById(16);
        //id为16的图片不为空
        Assert.assertTrue(picture2 == null);
    }

}
