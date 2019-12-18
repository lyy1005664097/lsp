package com.wulianwang.lsp.activity;

public class sc {
    import com.alibaba.fastjson.JSONObject;
    import com.example.cashbookforapp.pojo.Orderitem;
    import com.example.cashbookforapp.pojo.User;

    import org.apache.commons.httpclient.HttpClient;
    import org.apache.commons.httpclient.HttpException;
    import org.apache.commons.httpclient.methods.PostMethod;
    import org.apache.commons.httpclient.params.HttpMethodParams;

    import java.io.IOException;
    import java.util.Map;
    import java.util.Set;

        public class AddOrderItemService {
            //你要訪問的地址
            static String url="http://10.0.2.2:8080/项目名称/xxxx";
            //这里的class是自己定义的一个实体对象
            public static String postHttp(Class class) {
                //方法一,将class转为json
                String body = JSONObject.toJSONString(class);
                //定义返回的结果
                String mesg = null;
                //构建HttpClient实例
                HttpClient httpClient = new HttpClient();
                //设置请求超时时间
                httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
                //设置响应超时时间
                httpClient.getHttpConnectionManager().getParams().setSoTimeout(60000);

                //构造PostMethod的实例
                PostMethod postMethod=new PostMethod(url);
                postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
                Map<String,Object> map = JSONObject.parseObject(body,Map.class);
                Set<String> set = map.keySet();
                for(String s : set){
                    System.out.println(map.get(s).toString());
                    postMethod.addParameter(s,map.get(s).toString());
                }
                try {
                    //执行post请求
                    httpClient.executeMethod(postMethod);
                    //可以对响应回来的报文进行处理
                    mesg = postMethod.getResponseBodyAsString();
                    System.out.printf(mesg);
                } catch (HttpException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }finally{
                    //关闭连接释放资源的方法
                    postMethod.releaseConnection();
                    //((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
                    httpClient.getHttpConnectionManager().closeIdleConnections(0);
                }
                return mesg;
            }



}
