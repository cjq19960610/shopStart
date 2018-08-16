
import menu.Data;
import menu.Menu;

import java.util.Scanner;

public class StartShop {

    public static void main(String[] args) {
        // 准备数据空间，并且初始化数据
        Data data=new Data();
        data.init();
        /*
         * 1. 显示系统登录界面 Menu.loginMenu() 2. 接受用户的选择 3. 根据用户选择作出不同的处理 3.1 选择1 ->
         * 提示用户输入用户名和密码 -> 校验用户名和密码是否正确（三次机会）-> 如果正确，显示主功能界面 Menu.mainMenu() -> 接受用户的选择
         * -> 如果选择4（返回上级）-> 显示系统登录界面 -> 如果不正确（满三次），结束程序 loginCheck() 3.2 选择2 -> 结束程序
         *
         */
        Scanner sc = new Scanner(System.in);
        Menu menu=new Menu();
        menu.setData(data);
        menu.loginMenu();
        /*
         * 2. 接受用户的选择 如果选择1 -> loginCheck() 根据loginCheck()的返回值决定是否进入主功能界面： 返回true ->
         * menu.mainMenu() 返回false -> 提示无权登录系统，结束
         */
        while (true) {
            int input = sc.nextInt();
            switch (input) {
                case 1:
                    boolean r = loginCheck();
                    if (r) {
                        menu.mainMenu();
                    } else {
                        System.out.println("无权登录系统!");
                        System.exit(0);
                    }
                    break;
                case 2:
                    System.out.println("谢谢使用!");
                    System.exit(0);
                default:
                    System.out.println("输入有误!请重新输入!请选择:");
            }
        }

    }

    public static boolean loginCheck() {
        // 校验用户名和密码是否正确（三次机会）如果三次以内某次正确，返回true,否则返回false
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            System.out.print("用户名:");
            String username = sc.next();
            System.out.print("密码:");
            String password = sc.next();

            if (username.equals("cjq19960610") && password.equals("cenjiaqi")) {
                return true;
            } else {
                System.out.println("用户名或密码有误!您还有" + (3 - i) + "次机会!");
            }
        }
        return false;
    }

}
