package menu;

import java.util.Scanner;

/*
 * 处理客户信息的增改查
 */
public class CustomMangement {

    Data mydata;

    public void setData(Data data) {
        mydata = data;
    }

    public void show() {
        for (int i = 0; i < mydata.customNo.length; i++) {
            if (mydata.customNo[i] == 0) {
                break;
            } else {
                System.out.println(mydata.customNo[i] + "\t" + mydata.customBirth[i] + "\t" + mydata.customScore[i]);
            }
        }
        // 返回上一级菜单
        returnLastMenu();
    }

    public void add() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("会员号:");
            int customNo = sc.nextInt();
            // 判断会员号是否已经存在
            boolean r = existCustomNo(customNo);
            if (r) {
                System.out.println("会员号" + customNo + "已存在！");
            }else {				// 不存在的情况下继续录入生日和积分
                System.out.print("生日(月/日):");
                String birth = sc.next();
                System.out.print("积分:");
                int score = sc.nextInt();
                // 将数据写入到数据的合适位置
                int index = getIndex();
                mydata.customNo[index] = customNo;
                mydata.customBirth[index] = birth;
                mydata.customScore[index] = score;
                System.out.println("添加成功！");
                break;
            }
        }
        returnLastMenu();
    }

    public void update() {
        System.out.print("请输入会员号:");
        Scanner sc = new Scanner(System.in);
        int i=0;
        int customNo = sc.nextInt();
        // 判断会员号是否已经存在
        boolean r = existCustomNo(customNo);
        if (r) {
            System.out.println("请输入您的生日：");
            String birth=sc.next();
            System.out.println("请输入您的积分：");
            int month=sc.nextInt();
            for (;i< mydata.customNo.length;i++){
                if (mydata.customNo[i]==customNo){
                    break;
                }
            }
            mydata.customBirth[i]=birth;
            mydata.customScore[i]=month;
            System.out.println("修改成功！");
        }else {
            System.out.println("会员号" + customNo + "不存在！");
        }
        returnLastMenu();
    }

    public void search() {
        System.out.println("请输入您要查询的会员号：");
        Scanner scanner=new Scanner(System.in);
        int number=scanner.nextInt();
        boolean r=true;
        for (int i=0;i<mydata.customNo.length;i++){
            if (number==mydata.customNo[i]){
                System.out.println("会员号："+mydata.customNo[i]);
                System.out.println("生日："+mydata.customBirth[i]);
                System.out.println("积分："+mydata.customScore[i]);
                r=false;
            }
        }
        if (r){
            System.out.println("请输入正确的会员号");
        }
        returnLastMenu();
    }

    public void returnLastMenu() {
        System.out.print("按n返回上级菜单");
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String input = sc.next();
            if (input.equals("n")) {
                Menu menu = new Menu();
                menu.setData(mydata); // !!!
                menu.customMenu();
            } else {
                System.out.println("输入有误!请按n返回上级菜单");
                f = false;
            }
        } while (!f);
    }

    public boolean existCustomNo(int customNo) {
        for (int i = 0; i < mydata.customNo.length; i++) {
            if (mydata.customNo[i] == 0) {
                break;
            }
            if (customNo == mydata.customNo[i]) { // 说明存在
                return true;
            }
        }
        return false;
    }

    public int getIndex() {
        int index = 0;
        for (int i = 0; i < mydata.customNo.length; i++) {
            if(mydata.customNo[i] == 0) {
                index = i;
                break;
            }
        }
        return index;
    }
}

