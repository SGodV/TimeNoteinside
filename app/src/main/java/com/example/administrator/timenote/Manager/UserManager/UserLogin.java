package com.example.administrator.timenote.Manager.UserManager;

import android.webkit.WebMessage;

import com.example.administrator.timenote.Model.BeanUserInformation;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Sprou on 2018/5/26.
 */

public class UserLogin {
    public void getRemoteInfo(String userid) {
        // 命名空间
        String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        String methodName = "LoadUser ";
        // EndPoint
        String endPoint = "http://39.108.124.121:5818/WebService1.asmx";
        // SOAP Action
        String soapAction = "http://tempuri.org//LoadUser /";
        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("useremail", userid);
        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        (new MarshalBase64()).register(envelope);
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);
        HttpTransportSE transport = new HttpTransportSE(endPoint);
        transport.debug = true;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            if (envelope.bodyIn!=null) {
//                System.out.println(envelope.bodyIn);
                SoapObject result = (SoapObject) envelope.bodyIn;
                BeanUserInformation u = new BeanUserInformation();
                u.setUseremail(result.getProperty(0).toString());
                u.setUserpassword(result.getProperty(1).toString());
//                SoapObject detail = (SoapObject) result.getProperty(methodName);
//                for (int i = 0; i < detail.getPropertyCount(); i++) {
//                    SoapObject mstr = (SoapObject) detail.getProperty(i);
//                    String str_result = mstr .getProperty("").toString();
//                    System.out.print(str_result);//这个你要的
//                }
           }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public static void main(String[] args){
        UserLogin userLogin = new UserLogin();
        userLogin.getRemoteInfo("306273815@qq.com");
    }
}
