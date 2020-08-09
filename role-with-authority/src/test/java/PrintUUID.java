import cn.hutool.core.lang.UUID;

public class PrintUUID {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString(true).toUpperCase());
    }
}
