package org.smslib.camel;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.UriEndpointComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents the component that manages {@link SMSLibEndpoint}.
 * Two "paths" are supported: <code>mock</code> and <code>modem</code>.
 * <p><code>mock</code> uses the {@link org.smslib.gateway.MockGateway} via the {@link MockEndpoint} to provide a dummy interface to test with.</p>
 * <p><code>modem</code> uses the SMSlib based serial modem API via the {@link ModemEndpoint} to use real GSM hardware with camel.
* <p><b>Note:</b> The main focus of this endpoint is to received messages. Therefore the sending capabilities are limited. Sess {@link SMSLibProducer} for details.</p>
 * @author derjust
 */
public class SMSLibComponent extends UriEndpointComponent {
	public SMSLibComponent() {
		super(SMSLibEndpoint.class);
	}

	static Logger logger = LoggerFactory.getLogger(SMSLibComponent.class);

    protected Endpoint createEndpoint(String uri, String path, Map<String, Object> parameters) throws Exception {
    	SMSLibEndpoint endpoint;
    	switch (path) {
    	case "mock":
    		endpoint = new MockEndpoint(uri, this);
    		break;
    	case "modem":
    		endpoint = new ModemEndpoint(uri, this);
    		break;
		default:
			throw new IllegalArgumentException(path + " not definied!");
    	
    	}
        setProperties(endpoint, parameters);
        return endpoint;
    }
    
}
