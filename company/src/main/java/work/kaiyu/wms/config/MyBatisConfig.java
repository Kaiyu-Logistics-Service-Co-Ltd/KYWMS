package work.kaiyu.wms.config;

import com.alibaba.nacos.common.utils.StringUtils;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @auther cssly
 * @create 2020/6/20 1:55
 */
@Configuration
@MapperScan({"work.kaiyu.wms.dao"})
@EnableConfigurationProperties(value = {PageHelperProperties.class})
public class MyBatisConfig {
    private PageHelperProperties pageHelperProperties;
    public MyBatisConfig(ObjectProvider<PageHelperProperties> objectProvider){
        this.pageHelperProperties=objectProvider.getIfUnique();
    }
    /**
     * 注入分页插件，springBoot会将注入的插件自动设置到sqlSessionFactory中
     */
    @Bean
    public Interceptor interceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        //加载配置
        Properties properties = new Properties();
        //方言不设置可以自动选择
        if (StringUtils.isNotBlank(pageHelperProperties.getHelperDialect()))
            properties.setProperty("helperDialect",pageHelperProperties.getHelperDialect());
        properties.setProperty("countSql", String.valueOf(pageHelperProperties.getCountSql()));
        properties.setProperty("reasonable",pageHelperProperties.getHelperDialect());
        properties.setProperty("pageSizeZero",pageHelperProperties.getHelperDialect());
        //设置参数
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
