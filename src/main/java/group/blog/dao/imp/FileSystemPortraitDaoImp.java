package group.blog.dao.imp;

import group.blog.dao.PortraitDao;
import group.blog.entity.User;

import java.io.*;

/**
 * 基于文件系统的用户头像DAO
 * @author Mr.Chen
 **/
public class FileSystemPortraitDaoImp implements PortraitDao {

    private static final File portraitDoc = new File("D:\\SharingBlog\\user\\portrait");

    static {
        if (!portraitDoc.exists() || !portraitDoc.isDirectory()) {
            portraitDoc.mkdir();
        }
    }

    @Override
    public byte[] queryPortrait(User user) {
        File[] files = portraitDoc.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.split("\\.")[0].equals(user.getId() + "");
            }
        });

        if (files != null && files[0] != null) {
            try {
                byte[] image = new byte[(int) files[0].length()];
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(files[0]));
                inputStream.read(image);
                inputStream.close();
                return image;
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int insertPortrait(User user, byte[] image, String type) {
        File file = new File(portraitDoc, user.getId() + "." + type);
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false));
            outputStream.write(image);
            outputStream.flush();
            outputStream.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public int updatePortrait(User user, byte[] image, String type) {
        deletePortrait(user);
        return insertPortrait(user, image, type);
    }

    @Override
    public int deletePortrait(User user) {
        File[] files = portraitDoc.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.split("\\.")[0].equals(user.getId() + "");
            }
        });
        if (files != null && files[0] != null) {
            if (files[0].delete())
                return 1;
        }
        return 0;
    }

    @Override
    public String getType(User user) {
        File[] files = portraitDoc.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.split("\\.")[0].equals(user.getId() + "");
            }
        });
        if (files != null && files[0] != null) {
            return files[0].getName().split("\\.")[1];
        }
        return null;
    }
}
