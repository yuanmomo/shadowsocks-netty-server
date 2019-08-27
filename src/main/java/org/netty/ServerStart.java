package org.netty;

/**
 * socksserver启动类
 *
 * @author zhaohui
 */
public class ServerStart {
    public static void main(String[] args) {
        int count = 1;
        try {
            count = Integer.parseInt(args[0]);
        } catch (Exception e) {
            count = 1;
        }

        for (int i = 0; i < count; i++) {
            final int port = 10000 +i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    new SocksServer().start(port, getRandomString(12));
                }
            }).start();
        }
    }

    public static final String STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int) (Math.random() * 62);
            sb.append(STR.charAt(number));
        }
        return sb.toString();
    }
}
