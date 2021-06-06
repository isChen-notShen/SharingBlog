package group.blog.dao.imp;

import group.blog.dao.PortraitDao;
import group.blog.entity.Portrait;
import group.blog.entity.User;

import java.io.*;

/**
 * 基于文件系统的用户头像DAO
 *
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
    public Portrait getPortraitByUserId(int userId) {
        File[] files = portraitDoc.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.split("\\.")[0].equals(userId + "");
            }
        });

        if (files != null && files[0] != null) {
            Portrait result = new Portrait();
            try {
                byte[] image = new byte[(int) files[0].length()];
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(files[0]));
                result.setSize(inputStream.read(image));
                result.setData(image);
                result.setName(String.valueOf(userId));
                result.setType(files[0].getName().split("\\.")[1]);
                inputStream.close();
                return result;
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int insertPortrait(Portrait portrait) {
        File file = new File(portraitDoc, portrait.getName() + "." + portrait.getType());
        try {
            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, false));
            outputStream.write(portrait.getData());
            outputStream.flush();
            outputStream.close();
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

    @Override
    public int updatePortraitByUserId(int userId, byte[] image, String type) {
        Portrait newPortrait = new Portrait(String.valueOf(userId), image, type);
        deletePortraitByUserId(userId);
        return insertPortrait(newPortrait);
    }

    @Override
    public int deletePortraitByUserId(int userId) {
        File[] files = portraitDoc.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.split("\\.")[0].equals(userId + "");
            }
        });
        if (files != null && files[0] != null) {
            if (files[0].delete())
                return 1;
        }
        return 0;
    }
}
