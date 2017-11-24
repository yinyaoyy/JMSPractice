package com.yinyao.practice;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


public class MessageListener2 implements MessageListener {

	public void onMessage(Message message) {
		 try {  
	            System.out.println("收到的消息订阅2："+((TextMessage)message).getText());  
	        } catch (JMSException e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	}
}
