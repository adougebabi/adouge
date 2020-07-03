import cn.hutool.core.codec.Base64;
import com.google.common.base.Utf8;
import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author : Vinson
 * @date : 2020/6/16 9:16 上午
 */
public class testEncodeHeader {
    @Test
    @SneakyThrows
    public void testa(){
        String token = "shacaige:shacaige_secret";
        String encode = Base64.encode(token.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);
    }
}
