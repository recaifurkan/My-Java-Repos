package com.byrfb.bluetooth.listener;

import com.byrfb.bluetooth.event.ErrorEvent;
import com.byrfb.bluetooth.event.ProgressUpdatedEvent;
import com.byrfb.bluetooth.event.ScanFinishedEvent;

import java.util.EventListener;

/**
 *
 * @author vkorecky
 */
public interface BluetoothScanEventListener extends EventListener {

    /**
     * Error event
     *
     * @param evt
     */
    public void error(ErrorEvent evt);

    /**
     * Scan finished
     *
     * @param evt
     */
    public void scanFinished(ScanFinishedEvent evt);

    /**
     * Progress updated
     *
     * @param evt
     */
    public void progressUpdated(ProgressUpdatedEvent evt);
}
