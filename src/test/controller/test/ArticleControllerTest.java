package controller.test;

import com.alibaba.fastjson.JSONObject;
import com.ssm.demo.entity.Article;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * SpringMVC单元测试类
 *
 * @author 13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:applicationContext.xml","classpath:spring-mvc.xml", "classpath:mybatis-config.xml"})
public class ArticleControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testArticleById() throws Exception {
        //请求方式为get
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.request(HttpMethod.GET, "/articles/info/1");
        mockMvc.perform(mockHttpServletRequestBuilder).andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 测试富文本添加，需模拟真实环境，header设置、请求参数整理、请求方法确认
     * @throws Exception
     */
    @Test
    public void testArticleAdd() throws Exception {
        //创建对象
        Article article = new Article();
        article.setAddName("13");
        article.setArticleTitle("Controller测试文章101");
        article.setArticleContent("Controller单元测试添加文章");
        //封装参数
        String requestParam = JSONObject.toJSONString(article);
        //请求方式为post
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/articles/save");
        mockMvc.perform(mockHttpServletRequestBuilder
                .header("token","e631aac955c5754c8e6d5c296a6f0e1e")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestParam)).andExpect(status().isOk())
                .andDo(print());

    }
}