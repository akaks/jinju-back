package com.aguang.jinjuback.controllers.qiniu;

import com.aguang.jinjuback.config.properties.JinjuProperties;
import com.aguang.jinjuback.controllers.base.BaseController;
import com.aguang.jinjuback.model.pojo.Result;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 七牛Controller
 * @author li.kai
 * @since 2018/01/14
 */
@RestController
@RequestMapping("/qiniu")
public class QiniuController extends BaseController {

//    @Value("${qiniu.accessKey}")
//    private String accessKey;
//
//    @Value("${qiniu.secretKey}")
//    private String secretKey;

    @Value("${qiniu.jinju-bucket}")
    private String jinjuBucket;

    @Value("${qiniu.meiwen-bucket}")
    private String meiwenBucket;

    @Value("${qiniu.find-bucket}")
    private String findBucket;

    @Autowired
    private JinjuProperties properties;

    /**
     * 获取七牛云的上传token
     * @return
     */
    @GetMapping("/uploadToken")
    public Result uploadToken(@RequestParam(value = "type", defaultValue = "1") String type) {
        Result result = new Result();

        String accessKey = properties.getQiniu().getKey().getAccessKey();
        String secretKey = properties.getQiniu().getKey().getSecretKey();

        Auth auth = Auth.create(accessKey, secretKey);

        String uploadToken = null;

        if("1".equals(type)) {
            uploadToken = auth.uploadToken(jinjuBucket, null);
        } else if("2".equals(type)) {
            uploadToken = auth.uploadToken(meiwenBucket, null);
        } else if("3".equals(type)) {
            uploadToken = auth.uploadToken(findBucket, null);
        } else {
            result.setError("无效的类型");
        }

        result.setSuccess(uploadToken, "query success");

        return result;
    }
}
