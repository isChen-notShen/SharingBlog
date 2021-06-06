package group.blog.util.mime;

/**
 * @author Mr.Chen
 **/
public abstract class ImageMIMEConverter {

    public static final String jpeg = "image/jpeg";
    public static final String png = "image/png";
    public static final String gif = "image/gif";

    public static String NameExtensionToMIME(String name) {
        switch (name) {
            case "jpg":
                return jpeg;
            case "png":
                return png;
            case "gif":
                return gif;
        }
        return null;
    }

}
