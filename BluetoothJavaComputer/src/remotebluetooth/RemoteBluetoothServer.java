package remotebluetooth;

/*

kardeş bura ana class bekleme yaptıran sınıf burası burada işlem başlar wait thread sınıfına geçer

 */

public class RemoteBluetoothServer {

    public static void main(String[] args) {
        Thread waitThread = new Thread(new WaitThread());
        waitThread.start();
    }
}
