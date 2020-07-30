package org.example.ec.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.example.ec.entities.CommonResult;
import org.example.ec.entities.Message;
import org.example.ec.service.CSRSService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther cssly
 * @create 2020/6/27 15:41
 */
@Slf4j
@RestController
@RequestMapping("/csrService")
public class CSRServiceController {

    @Value("${config.info}")
    private String configInfo;

    @Resource
    private CSRSService csrsService;

    /**
     * 用户请求客服服务
     * @return
     */
    @GetMapping("/beginNewService")
    @SentinelResource(value = "beginNewService",fallback = "beginNewServiceFallback",blockHandler = "beginNewServiceBlockHandler")
    public CommonResult beginNewService(){

        try {
            Integer i  = csrsService.beginNewService();
            log.info(i.toString());
            if (i!=Integer.MIN_VALUE){
                return new CommonResult(200,"创建成功");
            }else {
                return new CommonResult(403,"创建失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"创建失败");
        }
    }

    /**
     * 客服请求结束服务
     * @return
     */
    @GetMapping("/waiterEndService")
    @SentinelResource(value = "waiterEndService",fallback = "waiterEndServiceFallback",blockHandler = "waiterEndServiceBlockHandler")
    public CommonResult waiterEndService(){
        try {
            Integer i  = csrsService.waiterEndService();
            log.info(i.toString());
            if (i!=Integer.MIN_VALUE){
                return new CommonResult(200,"结束服务成功");
            }else {
                return new CommonResult(403,"结束服务失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"结束服务失败");
        }
    }

    /**
     * 用户自行结束对话
     * @param userId
     * @return
     */
    @GetMapping("/userEndService")
    @SentinelResource(value = "userEndService",fallback = "userEndServiceFallback",blockHandler = "userEndServiceBlockHandler")
    public CommonResult userEndService(@RequestParam Long userId){
        try {
            Integer i  = csrsService.userEndService(userId);
            log.info(i.toString());
            if (i!=Integer.MIN_VALUE){
                return new CommonResult(200,"结束服务成功");
            }else {
                return new CommonResult(403,"结束服务失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"结束服务失败");
        }
    }

    /**
     * 查询对话
     * @return
     */
    @GetMapping("/queryDialogue")
    @SentinelResource(value = "queryDialogue",fallback = "queryDialogueFallback",blockHandler = "queryDialogueBlockHandler")
    public CommonResult queryDialogue(){
        try {
            List<Message> messageList = csrsService.queryDialogue();
            if (!messageList.isEmpty()&&messageList!=null){
                return new CommonResult(200,"查询对话成功",messageList);
            }else {
                return new CommonResult(204,"暂无对话",messageList);
            }
        }catch (Exception e){
            return new CommonResult(500,"查询对话失败");
        }
    }

    /**
     * 发送对话
     * @param content
     * @return
     */
    @GetMapping("/sendMsg")
    @SentinelResource(value = "sendMsg",fallback = "sendMsgFallback",blockHandler = "sendMsgBlockHandler")
    public CommonResult sendMsg(@RequestParam String content){
        try {
            Integer i  = csrsService.sendMsg(content);
            log.info(i.toString());
            if (i!=Integer.MIN_VALUE){
                return new CommonResult(200,"发送成功");
            }else {
                return new CommonResult(403,"发送失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"发送失败");
        }
    }

    /**
     * 删除服务信息表
     * @param tableName
     * @return
     */
    @GetMapping("/dropTable")
    public CommonResult dropTable(@RequestParam String tableName){
        try {
            Integer i  = csrsService.dropTableByPrimaryKey(tableName);
            log.info(i.toString());
            if (i!=Integer.MIN_VALUE){
                return new CommonResult(200,"删除成功");
            }else {
                return new CommonResult(403,"删除失败");
            }
        }catch (Exception e){
            return new CommonResult(500,"删除失败");
        }
    }

    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult beginNewServiceFallback(Throwable e){
        return new CommonResult(500,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult beginNewServiceBlockHandler(BlockException e){
        return new CommonResult(403,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult waiterEndServiceFallback(Long waiterId,Throwable e){
        return new CommonResult(500,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult waiterEndServiceBlockHandler(Long waiterId,BlockException e){
        return new CommonResult(403,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult userEndServiceFallback(Long userId,Throwable e){
        return new CommonResult(500,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult userEndServiceBlockHandler(Long userId,BlockException e){
        return new CommonResult(403,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult queryDialogueFallback(Throwable e){
        return new CommonResult(500,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult queryDialogueBlockHandler(BlockException e){
        return new CommonResult(403,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult sendMsgFallback(String content,Throwable e){
        return new CommonResult(500,"兜底异常handlerFallback===>exception==>"+e.getMessage());
    }
    //↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
    public CommonResult sendMsgBlockHandler(String content,BlockException e){
        return new CommonResult(403,"blockHandler-Sentinel限流===>无此流水==>blockException=>"+e.getClass().getCanonicalName()+"服务不可用");
    }

    @PostConstruct
    private void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<FlowRule>();

        FlowRule rule1 = new FlowRule();
        rule1.setResource("beginNewService");
        rule1.setCount(5);
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);

        FlowRule rule2 = new FlowRule();
        rule2.setResource("waiterEndService");
        rule2.setCount(5);
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);

        FlowRule rule3 = new FlowRule();
        rule3.setResource("userEndService");
        rule3.setCount(5);
        rule3.setGrade(RuleConstant.FLOW_GRADE_QPS);

        FlowRule rule4 = new FlowRule();
        rule4.setResource("queryDialogue");
        rule4.setCount(5);
        rule4.setGrade(RuleConstant.FLOW_GRADE_QPS);

        FlowRule rule5 = new FlowRule();
        rule5.setResource("sendMsg");
        rule5.setCount(5);
        rule5.setGrade(RuleConstant.FLOW_GRADE_QPS);

        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);
        rules.add(rule4);
        rules.add(rule5);

        FlowRuleManager.loadRules(rules);
    }

    @PostConstruct
    public void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        rule.setResource("beginNewService");
        // 80s内调用接口出现异常次数超过5的时候, 进行熔断
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rule.setCount(5);
        rule.setTimeWindow(80);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }
}
