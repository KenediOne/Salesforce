package storeservice.encryption;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class Encryption {

    public Encryption() {
    }

    public String encoding(String text) throws UnsupportedEncodingException {
        return Base64.encode(text.getBytes("UTF-8"));
    }

    public String decoding(String text) throws UnsupportedEncodingException {
        String string = new String(Base64.decode(text),"UTF-8");
        return string;
    }

}
