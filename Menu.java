package menu;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    Data mydata;

    public void setData(Data data) {
        mydata = data;
    }

    public void  loginMenu() {
        System.out.println("          欢迎使用天马行空购物系统1.0版");
        System.out.println("*************************************************");
        System.out.println("                  1.登录系统");
        System.out.println("                  2.退出");
        System.out.println("*************************************************");
        System.out.println("请输入您的选择： ");
    }

    public void mainMenu() {
        // 显示主界面功能项
        // 接受用户选择 1,2,3,4
        // 选择4 -> loginMenu()
        System.out.println("*************************************************");
        System.out.println("                  1.客户信息管理");
        System.out.println("                  2.购物结算");
        System.out.println("                  3.真情回馈");
        System.out.println("                  4.注销");
        System.out.println("*************************************************");
        System.out.println("请输入您的选择：");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        switch(n) {
            case 1:
                customMenu();
                break;
            case 2:
                settleShop();
                break;
            case 3:
                trueFeedback();
                break;
            case 4:
                loginMenu();
                break;
            default:
                break;
        }
    }

    public void customMenu() {
        CustomMangement cm = new CustomMangement();
        cm.setData(mydata);

        System.out.println("*************************************************");
        System.out.println("                  1.显示所有客户信息");
        System.out.println("                  2.添加客户信息");
        System.out.println("                  3.修改客户信息");
        System.out.println("                  4.查询客户信息");
        System.out.println("*************************************************");

        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String n = sc.next();
            switch(n) {
                case "1":
                    cm.show();
                    break;
                case "2":
                    cm.add();
                    break;
                case "3":
                    cm.update();
                    break;
                case "4":
                    cm.search();
                    break;
                case "n":
                    mainMenu();
                    break;
                default:
                    System.out.println("输入有误，请重新输入：");
                    f = false;
                    break;
            }
        }while(!f);
    }

    public void settleShop() {
        String[] shopList = new String[7];
        shopList[0] = "乒乓球拍   ";
        shopList[1] = "Kappa网球裙";
        shopList[2] = "网球拍     ";
        shopList[3] = "addidasT恤 ";
        shopList[4] = "Nike运动鞋 ";
        shopList[5] = "Kappa网球  ";
        shopList[6] = "KappaT恤   ";

        double[] price = new double[7];
        price[0] = 880.0  ;
        price[1] = 300.0  ;
        price[2] = 980.0 ;
        price[3] = 400.0  ;
        price[4] = 800.0  ;
        price[5] = 100.0  ;
        price[6] = 320.0  ;

        System.out.println("*****************************");
        for (int i = 0; i < shopList.length; i++) {
            System.out.println((i + 1) + ". " + shopList[i] + "         " + "价格" + price[i]);
        }
        System.out.println("*****************************");
        Scanner scanner = new Scanner(System.in);
        int num, number, amount,n;
        double sale=0;
        double allPrice=0;
        double[] shopPrice = new double[1];
        String[] shopNumber = new String[1];
        int[] shouNum = new int[1];
        while (true){
            System.out.println("          请输入商品编号：");
            number = scanner.nextInt();
            shopPrice[shopPrice.length-1]=price[number-1];
            shopNumber[shopNumber.length-1]=shopList[number-1];
            System.out.println("          请输入购买数量：");
            amount = scanner.nextInt();
            shouNum[shouNum.length-1]=amount;
            System.out.println("          是否继续： y/n");
            String comtinue = scanner.next();
            if (comtinue.equals("y")){
                shopPrice = Arrays.copyOf(shopPrice,shopPrice.length+1);
                shouNum = Arrays.copyOf(shouNum,shouNum.length+1);
                shopNumber = Arrays.copyOf(shopNumber,shopNumber.length+1);
            }else if (comtinue.equals("n")){
                break;
            }
        }
        System.out.println("请输入会员号： (非会员输入0跳过！)");
        num = scanner.nextInt();
        System.out.println("* * * * * * * * * * 消费清单 * * * * * * * * * *");
        System.out.println("物品           单价          个数         金额");
        for (int j=0;j<shouNum.length;j++){
            System.out.println(shopNumber[j] + "    " + shopPrice[j] + "          " + shouNum[j] + "           " + shopPrice[j] * shouNum[j]);
            allPrice+=shopPrice[j] * shouNum[j];
        }
        for (n=0;n<mydata.customNo.length;n++){
            if (mydata.customNo[n] == num){
                break;
            }
        }
        double integral=mydata.customScore[n];
        if (integral>0&&integral<1000){
            sale=0.95;
        }else if (integral>=1000&&integral<2000){
            sale=0.9;
        }else if (integral>=2000&&integral<3000){
            sale=0.85;
        }else if (integral>=3000&&integral<4000){
            sale=0.8;
        }else if (integral>=4000&&integral<6000){
            sale=0.75;
        }else if (integral>=6000&&integral<8000){
            sale=0.6;
        }else if (integral>=8000){
            sale=0.55;
        }else {
            sale=1.0;
        }
        System.out.println("折扣：    "+"\t"+sale);
        System.out.println("金额总计："+"\t"+"￥"+allPrice*sale);
        System.out.println("实际交费："+"\t"+"￥"+allPrice);
        System.out.println("找零：    "+"\t"+"￥"+(allPrice-allPrice*sale));
        System.out.println("本次购物所获的积分为："+Math.floor(allPrice*sale/100*3));
        mydata.customScore[n]=mydata.customScore[n]+(Math.floor(allPrice*sale/100*3));
        System.out.println("按n返回上级菜单");
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String input = sc.next();
            if (input.equals("n")) {
                Menu menu = new Menu();
                menu.setData(mydata); // !!!
                menu.mainMenu();
            } else {
                System.out.println("输入有误!请按n返回上级菜单");
                f = false;
            }
        } while (!f);
    }

    public void trueFeedback(){
        //幸运大放送
        System.out.println("*************************************************");
        System.out.println("                  1.幸运大放送");
        System.out.println("                  2.幸运抽奖");
        System.out.println("                  3.生日问候");
        System.out.println("                  4.返回上一级");
        System.out.println("*************************************************");
        System.out.println("请输入您的选择：");
        Scanner sc = new Scanner(System.in);
        int userChoose = sc.nextInt();
        switch (userChoose){
            case 1:
                luckStrike();
                break;
            case 2:
                luckDraw();
                break;
            case 3:
                birthGreet();
                break;
            case 4:
                mainMenu();
                break;
                default:
                    break;
        }
    }

    public void luckStrike(){
        int i,n;
        for (i=0;i<mydata.customNo.length;i++){
            if (mydata.customNo[i]==0){
                break;
            }
        }
        double[] integral=new double[i];
        for (int j=0;j<i;j++){
            integral[j]=mydata.customScore[j];
        }
        Arrays.sort(integral);
        for (n=0;n<mydata.customScore.length;n++){
            if (mydata.customScore[n]==integral[i-1]){
                break;
            }
        }
        System.out.println("恭喜会员号为"+mydata.customNo[n]+"的顾客以"+mydata.customScore[n]+"最高积分获得苹果笔记本一台！");
        System.out.println("按n返回上级菜单");
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String input = sc.next();
            if (input.equals("n")) {
                Menu menu = new Menu();
                menu.setData(mydata); // !!!
                menu.trueFeedback();
            } else {
                System.out.println("输入有误!请按n返回上级菜单");
                f = false;
            }
        } while (!f);
    }

    public void luckDraw(){
        int i;
        boolean r=true;
        Random random=new Random();
        int number=random.nextInt(10);
        for (i=0;i<mydata.customNo.length;i++) {
            if ((mydata.customNo[i] % 1000) / 100 == number&&mydata.customNo[i]>0) {
                System.out.println("恭喜会员号为" + mydata.customNo[i] + "的顾客中MP3一台");
                r=false;
            }
        }
        if (r){
            System.out.println("本期没有顾客中奖");
        }

        System.out.println("按n返回上级菜单");
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String input = sc.next();
            if (input.equals("n")) {
                Menu menu = new Menu();
                menu.setData(mydata); // !!!
                menu.trueFeedback();
            } else {
                System.out.println("输入有误!请按n返回上级菜单");
                f = false;
            }
        } while (!f);
    }

    public void birthGreet(){
        System.out.println("请输入当前日期： 例如:02/03");
        Scanner scanner=new Scanner(System.in);
        String birth=scanner.next();
        boolean r=true;
        for (int i=0;i<mydata.customNo.length;i++){
            if (birth.equals(mydata.customBirth[i])){
                System.out.println("热烈祝贺会员号为"+mydata.customNo[i]+"生日快乐！本店赠送您生日礼物MP3一台！");
                r=false;
            }
        }
        if (r){
            System.out.println("今天没有会员生日！");
        }

        System.out.println("按n返回上级菜单");
        Scanner sc = new Scanner(System.in);
        boolean f = true;
        do {
            String input = sc.next();
            if (input.equals("n")) {
                Menu menu = new Menu();
                menu.setData(mydata); // !!!
                menu.trueFeedback();
            } else {
                System.out.println("输入有误!请按n返回上级菜单");
                f = false;
            }
        } while (!f);
    }
}
