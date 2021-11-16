/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.wgzhao.addax.admin.utils;

import com.wgzhao.addax.admin.config.ConfigConstants;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * @author yangkai
 */
public class UUIDUtil
{
    //字符库
    public static String[] chars = new String[] {"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private UUIDUtil()
    {
        throw new IllegalStateException("UUIDUtil class");
    }

    public static String generate()
    {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        StringBuilder shortBuffer = new StringBuilder();
        //分为8组
        for (int i = 0; i < 8; i++) {
            //每组4位
            String str = uuid.substring(i * 4, i * 4 + 4);
            //输出str在16进制下的表示
            int x = Integer.parseInt(str, 16);
            //用该16进制数取模62（十六进制表示为314（14即E）），结果作为索引取出字符
            shortBuffer.append(chars[x % 0x3E]);
        }
        //生成8位字符
        return shortBuffer.toString();
    }

    /**
     * 生成随机数字和字母,
     *
     * @param length 长度
     * @return String
     */
    public static String getStringRandom(int length)
    {

        StringBuilder val = new StringBuilder();
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val.append((char) (random.nextInt(26) + temp));
            }
            else {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return DigestUtils.md5Hex(val.toString()+ ConfigConstants.formatter.format(LocalDateTime.now()));
    }
}
