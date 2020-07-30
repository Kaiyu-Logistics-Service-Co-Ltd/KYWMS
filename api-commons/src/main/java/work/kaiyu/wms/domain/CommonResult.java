package work.kaiyu.wms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GLZA
 * Date: 2020/5/31
 * Time: 17:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T>{
    /**
     * 状态码 #必须
     */
    private Integer code;
    /**
     * 信息  #必须
     */
    private String  message;
    /**
     * 数据
     */
    private T       data;
    public CommonResult(Integer code, String  message){
        this(code,message,null);
    }
}
