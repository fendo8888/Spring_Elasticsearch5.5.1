package com.fendo.config;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class TransportClientFactory implements FactoryBean<TransportClient>,InitializingBean,DisposableBean{

	
    private String clusterName;
    private String host;
    private int port;
    private TransportClient client;
    
    
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
	@Override
	public void destroy() throws Exception {
		if(client!=null)
            client.close();
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Settings settings=Settings.builder().put("cluster.name",this.clusterName).build();
        client=new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(this.host),this.port));		
	}

	@Override
	public TransportClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
