package com.byrfb.bluetooth.event;

import com.byrfb.bluetooth.entity.RFCommBluetoothDevice;

import java.util.EventObject;
import java.util.List;

/**
 *
 * @author vkorecky
 */
public class ScanFinishedEvent extends EventObject {

    private final List<RFCommBluetoothDevice> foundDevices;

    /**
     * Constructor
     *
     * @param foundDevices Devices
     * @param source source
     */
    public ScanFinishedEvent(List<RFCommBluetoothDevice> foundDevices, Object source) {
        super(source);
        this.foundDevices = foundDevices;
    }

    /**
     * Gets list of found devices
     *
     * @return found devices
     */
    public List<RFCommBluetoothDevice> getFoundDevices() {
        return foundDevices;
    }

}
