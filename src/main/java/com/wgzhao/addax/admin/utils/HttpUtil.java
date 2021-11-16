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

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class HttpUtil
{
    public static String getData(String url, Map<String, Object> paramMap, String method)
    {
        log.info("http数据请求url:{}", url);
        if (method.equalsIgnoreCase("GET")) {
            return getWithJson(url, paramMap);
        }
        else {
            return postWithJson(url, paramMap);
        }
    }

    public static String postWithJson(String url, Map<String, Object> paramMap)
    {
        String returnValue = "接口调用失败";
        //第一步：创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);

            //第三步：给httpPost设置JSON格式的参数
            String json = JSON.toJSONString(paramMap);
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);

            //第四步：发送HttpPost请求，获取返回值
            //调接口获取返回值时，必须用此方法
            returnValue = httpClient.execute(httpPost, responseHandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }

    public static String getWithJson(String url, Map<String, Object> paramMap)
    {
        String returnValue = "接口调用失败";
        //第一步：创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (paramMap != null) {
                // 添加请求参数
                for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                    uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            //第二步：创建httpGet对象
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            //调接口获取返回值时，必须用此方法
            returnValue = httpClient.execute(httpGet, responseHandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }
}
