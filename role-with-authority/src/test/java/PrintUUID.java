import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;

import java.util.Date;

public class PrintUUID {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString(true).toUpperCase());
        StringBuilder newAvatarName = new StringBuilder();
        newAvatarName.append("avatar-");
        newAvatarName.append(new Date().getTime());
        newAvatarName.append(".png");
        System.out.println(newAvatarName);
    }
}
