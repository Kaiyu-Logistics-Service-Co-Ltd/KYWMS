import work.kaiyu.wms.domain.User;

public class TTT {
    public static void main(String[] args) {
        User currentUser = new User();
        currentUser.setUserCode("aaaaaaaa");
        StringBuilder relative = new StringBuilder();
        StringBuilder real = new StringBuilder();
        real.append("C:/Users/cssly/OneDrive");
        relative.append("/resources/user").append("/").append(currentUser.getUserCode());
        real.append(relative);
        String newAvatarName = "/avatar.png";
        System.out.println("relative"+relative);
        System.out.println("real"+real);
        real.append(newAvatarName);
        relative.append(newAvatarName);
        System.out.println("relative"+relative);
        System.out.println("real"+real);
    }
}
