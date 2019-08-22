package com.byrfb.bluetooth.listener;

import com.byrfb.bluetooth.event.ErrorEvent;
import com.byrfb.bluetooth.event.MessageReceivedEvent;

import java.util.EventListener;

/**
 *
 * @author vkorecky
 */
public interface RFCommClientEventListener extends EventListener {

    /**
     * Error event
     *
     * @param evt
     */
    public void error(ErrorEvent evt);

    /**
     * Message received from bluetooth device
     *
     * @param evt
     */
    public void messageReceived(MessageReceivedEvent evt);
}
