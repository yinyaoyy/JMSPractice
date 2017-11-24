package com.yinyao.practice;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageProducer {

	private static final String username = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	private static final int connum = 10;
	public static void main(String[] args) {

		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		javax.jms.MessageProducer messageProducer;
		connectionFactory = new ActiveMQConnectionFactory(MessageProducer.username,MessageProducer.password,MessageProducer.url);
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic("First");
			messageProducer = session.createProducer(destination);
			sendMessage(session,messageProducer);
			session.commit();
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	private static void sendMessage(Session session, javax.jms.MessageProducer messageProducer) throws JMSException {

		for (int i = 0; i < MessageProducer.connum; i++) {
			TextMessage textMessage=session.createTextMessage("这是第"+i+"条消息");
			System.out.println("这是第"+i+"条消息");
			messageProducer.send(textMessage);
		}
	}

}
