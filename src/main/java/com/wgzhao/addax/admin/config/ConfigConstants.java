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

package com.wgzhao.addax.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * 系统配置常量
 *
 * @author aiwenjie
 */
@Component
public class ConfigConstants
{
    public static Long tokenExpirationTime;

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

//    public static String taskInfoUrl;

    public static Long timeOut;

    @Value("${zeus-token-expiration-time}")
    public void setTokenExpirationTime(Long tokenExpirationTime)
    {
        ConfigConstants.tokenExpirationTime = tokenExpirationTime;
    }

    @Value("${zeus-time-out}")
    public void setTimeOut(Long timeOut)
    {
        ConfigConstants.timeOut = timeOut;
    }

//    @Value("${zeus-task-url}")
//    public void setTaskInfoUrl(String taskInfoUrl)
//    {
//        ConfigConstants.taskInfoUrl = taskInfoUrl;
//    }
}
