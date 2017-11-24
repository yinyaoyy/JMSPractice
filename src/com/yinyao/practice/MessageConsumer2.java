package com.yinyao.practice;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageConsumer2 {

	private static final String username = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String password = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String url = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	public static void main(String[] args) {
		ConnectionFactory connectionFactory;
		Connection connection = null;
		Session session;
		Destination destination;
		javax.jms.MessageProducer messageProducer;
		connectionFactory = new ActiveMQConnectionFactory(MessageConsumer2.username,MessageConsumer2.password,MessageConsumer2.url);
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			destination = session.createTopic("First");
			messageProducer = session.createProducer(destination);
			session.setMessageListener(new MessageListener2());
			
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
}
