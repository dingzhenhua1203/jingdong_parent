package jingdong.common.oss;

import com.UpYun;
import com.jingdong.util.StreamHelper;
import com.upyun.RestManager;
import com.upyun.UpYunUtils;

import lombok.Getter;
import lombok.Setter;
import okhttp3.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class UPYUNService {
    /*空间名称*/
    private String bucketName;
    /*操作员名称*/
    private String upYunUser;
    /*操作员密码*/
    private String upYunSecretKey;
    /*图片访问域名*/
    private String domain;
    /*url后缀*/
    private String secretFlex;

    private final String UploadPath = "/jingdongparent/files/";

    // 创建实例
    private RestManager manager;

    public UPYUNService(String bucketName, String upYunUser, String upYunSecretKey) {
        this.bucketName = bucketName;
        this.upYunUser = upYunUser;
        this.upYunSecretKey = upYunSecretKey;
        this.manager = new RestManager(this.bucketName, this.upYunUser, this.upYunSecretKey);
        this.InitUPYunConfig();

    }

    public String uploadImg(String path, File file) {
        // 例1：上传纯文本内容，自动创建父级目录
        String str = "Hello RestManager";
        Map<String, String> params = new HashMap<String, String>();
        // 设置待上传文件的 Content-MD5 值
        // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
        params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(file, 1024));

        // 设置待上传文件的"访问密钥"
        // 注意：
        // 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
        // 举例：
        // 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
        // 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
        params.put(RestManager.PARAMS.CONTENT_SECRET.getValue(), "dc");
        /**
         * filePath 保存到又拍云存储的文件路径，以/开始
         * 第二个参数 接受 InputStream 、 File 和 byte[] 三种类型的数据
         * params 上传额外可选参数，详见 api 文档。
         * response.isSuccessful() 结果为 true 上传文件成功
         */
        try {
            Response result = manager.writeFile(this.UploadPath, file, params);
            return result.isSuccessful() ? this.bucketName + this.UploadPath : "";
        } catch (Exception ex) {
            return "";
        }

    }

    public String uploadImg(String fileName, InputStream stream) throws IOException {

        Map<String, String> params = new HashMap<String, String>();
        // 设置待上传文件的 Content-MD5 值
        // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
        byte[] bytes = StreamHelper.InputStreamToByte(stream);
        params.put(RestManager.PARAMS.CONTENT_MD5.getValue(), UpYunUtils.md5(bytes));

        // 设置待上传文件的"访问密钥"
        // 注意：
        // 仅支持图片空！，设置密钥后，无法根据原文件URL直接访问，需带URL后面加上（缩略图间隔标志符+密钥）进行访问
        // 举例：
        // 如果缩略图间隔标志符为"!"，密钥为"bac"，上传文件路径为"/folder/test.jpg"，
        // 那么该图片的对外访问地址为：http://空间域名 /folder/test.jpg!bac
        params.put(RestManager.PARAMS.CONTENT_SECRET.getValue(), secretFlex);
        /**
         * filePath 保存到又拍云存储的文件路径，以/开始
         * 第二个参数 接受 InputStream 、 File 和 byte[] 三种类型的数据
         * params 上传额外可选参数，详见 api 文档。
         * response.isSuccessful() 结果为 true 上传文件成功
         */
        try {
            Response result = manager.writeFile(this.UploadPath + UUID.randomUUID() + fileName, bytes, params);
            return result.isSuccessful() ? getFilePath(UUID.randomUUID() + fileName) : "";
        } catch (Exception ex) {
            return ex.toString();
        }

    }


    /**
     * 返回上传后生成的url
     *
     * @param fileName 文件名
     * @return 全URL
     */
    public String getFilePath(String fileName) {
        return "https://" + domain + fileName + "!" + secretFlex;
    }

    /**
     * 根据文件路径获取文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf("/") + 1);
    }

    /**
     * 获取文件后缀   如.jpg
     *
     * @param originalFilename
     * @return
     */
    public String getFileSuffix(String originalFilename) {
        if (StringUtils.isBlank(originalFilename))
            return null;
        return originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
    }

    private void InitUPYunConfig() {
        // 手动设置超时时间：默认为30秒
        this.manager.setTimeout(120);
        // 选择最优的接入点
        /*RestManager.ED_AUTO    //根据网络条件自动选择接入点
        RestManager.ED_TELECOM //电信接入点
        RestManager.ED_CNC     //联通网通接入点
        RestManager.ED_CTT     //移动铁通接入点*/
        this.manager.setApiDomain(RestManager.ED_AUTO);
    }


}
