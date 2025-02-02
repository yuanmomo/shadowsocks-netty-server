import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 */

public class XuanFengTest {
    public static final String CONTENT ="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";
    public static final String CONTENT2 ="Qv52Cxues17GH0Y3UFbeUDm5TTbuHorcvj+cRx1dXSn57ud3qsI/1fCFA9Xd8t7XQ0iIWS8UGX1RMy1nhctgidvqFjJbboHtgoydDt5Ieiwx4mByzPzIPDeaENGmlQD54vBhO5+GfqRvXGPqaoFAB6lEykT7A2FgCIxpou4mdoudXcEzWbeTsutstXN6LZZeITgLtejoIhdYfDeewoajz9mkvgAloFqfsHPfinkdS51K7zgAtm+m80eNDBLzyxWgDDrafryq+4dn9qi6T6DYRIdE3fGPa11CwI21Z4qfoASKNh0e+tfCz1hcyfBnB/q2TGgHRzc4H/AtJ8kU2YC2XPeotY9yz/OylaWwk1qxHYmYCSgySP9ilE0UKL4fmbe3pa5pzU7c9/qhbSXaTgG3zELbJBEjVbHS3spNnq9lTS9H2bULujQkyImjGOFRu6aGvmgUcUyehk/LeUVzFMWVZ0rNys5e46WwFUgcWQxpCvoL2wttLYf+4kXOMf3O+tU3WzjSRfkoTXlGVCXFBeDLjFOgsOUQGAUwgE615Ry9Wg8mLJWa7InUTKO4yAaLQfE+LP0EpSnZFM7WvG0FyKMYDBsKqibquRXG4Z/e1NQR/cUds/AgSdTVb3eQlpp8a12R4i3t2Odi/67t6v/qAtpGIEJftW1fGqHnRa9e2i0gWo4wn28lBYwqgF6oPNT/OQYR";
    public static final String CONTENT3 ="RrzAWX/YjZTUWnpfa2d0Z1mXekqb+ogMaPDLynhqDDd7xe8JuJh90xqGcnXTtyLQehHbc7ySv6mhFQG0fUuETA==";
    public static final String CONTENT4 ="RrzAWX/YjZTUWnpfa2d0Z1mXekqb+ogMaPDLynhqDDcAquzSFTGI/7JMAkJcxKor7tYBsVG3YY/3jYdQCbI9IA==";

    public static void countChar(String content){
        char[] array = content.toCharArray();
        Map<String,Integer> map = new TreeMap<>();

        for (char c : array) {
            String charString = String.valueOf(c);
            Integer count = map.get(charString);
            if(count == null){
                map.put(charString,1);
            }else{
                map.put(charString,count + 1);
            }

        }

        map.forEach((k,v)->{
            System.out.println(String.format(" %s : %s",k,v ));
        });
    }

    public static final String KEY = "awdtif20190619ti";

    public static void decrypt(String content) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] arrayOfByte3 = KEY.getBytes("UTF-8");
        SecretKeySpec secretKeySpec = new SecretKeySpec(arrayOfByte3, "AES");
        byte[] arrayOfByte2 = KEY.getBytes("UTF-8");

        IvParameterSpec ivParameterSpec = new IvParameterSpec(arrayOfByte2);
        cipher.init(2, (Key)secretKeySpec, (AlgorithmParameterSpec)ivParameterSpec);
        byte[] arrayOfByte1 = cipher.doFinal(Base64Util.decode(content, 0));
        System.out.println(new String(arrayOfByte1,"UTF-8"));

    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        decrypt(CONTENT);
        decrypt(CONTENT2);
        decrypt(CONTENT3);
        decrypt(CONTENT4);
    }

}
