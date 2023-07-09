
import java.lang.Math;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test {
    public static void main (String args[]) {
        
        int tryCntAsCnt = 50;
        int tryCnt = tryCntAsCnt * 20;

        BigDecimal money = new BigDecimal((tryCntAsCnt * 2000) * -1);

        DecimalFormat moneyFormat = new DecimalFormat("#,###");
        
        System.out.println("환율 : 10위한 = 2000원");
        System.out.println("츄라이 횟수 : " + tryCnt + "\n");
        String moneyPrint = moneyFormat.format(money);
        System.out.println("최초 게임 지불 가격 : " + moneyPrint);
        Map<List<Integer>, Integer> cntMap = new HashMap<List<Integer>, Integer>();
        for (int z = 0; z < tryCnt; z++) {
            String red[] = new String[9];
            String blue[] = new String[9];
            String yellow[] = new String[9];
            String your[] = new String[9];
            for (int i = 0; i < 9; i++) {
                red[i] = "빨";
                blue[i] = "파";
                yellow[i] = "노";
            }
    
            for (int i = 0; i < 9; i++) {
                int randNum = (int)(Math.random() * 10);
                
                while(randNum == 9) {
                    randNum = (int)(Math.random() * 10);
                }
                String nowList[];
                if (randNum < 3) {
                    nowList = red;
                } else if (2 < randNum & randNum < 6) {
                    nowList = blue;
                } else {
                    nowList = yellow;
                }
                int randPop = (int)(Math.random() * 10);
                while(randPop == 9) {
                    randPop = (int)(Math.random() * 10);
                }

                if (nowList[randPop].equals("")) {
                    i--;
                    continue;
                }
                your[i] = nowList[randPop];
                nowList[randPop] = "";
            }
            int redCount = 0;
            int blueCount = 0;
            int yellowCount = 0;
            for(int i = 0; i < 9; i++) {
                if (your[i].equals("빨")) {
                    redCount += 1;
                } else if (your[i].equals("파")) {
                    blueCount += 1;
                } else {
                    yellowCount += 1;
                }
            }

            List<Integer> countList = new ArrayList<Integer>();
            countList.add(redCount);
            countList.add(blueCount);
            countList.add(yellowCount);
            Collections.sort(countList, Collections.reverseOrder());
            
            if(!cntMap.containsKey(countList)) {
                cntMap.put(countList, (int)0);
            };
            cntMap.put(countList, (int)(cntMap.get(countList) + 1));
        }

        List<List<Integer>> sortedList = new ArrayList<List<Integer>>(cntMap.keySet());
        List<Integer> sizeArray = new ArrayList<Integer>();

        sortedList.forEach(e -> {
            Integer ele = 0;
            List<Integer> lst = e;
            ele += lst.get(0) * 100;
            ele += lst.get(1) * 10;
            ele += lst.get(2);
            sizeArray.add(ele);
        });
        Map<List<Integer>, Integer> sortedMap = new LinkedHashMap<List<Integer>, Integer>();
        Collections.sort(sizeArray);
        List<BigDecimal> moneyList = new ArrayList<BigDecimal>();
        BigDecimal earnMon = new BigDecimal(0);
        moneyList.add(0, earnMon);
        sizeArray.forEach(e -> {
            List<Integer> ele = new ArrayList<Integer>();
            int hund = (int)(e / 100);
            int ten = (int)((e - (hund * 100))) / 10;
            int on = e - (hund * 100) - (ten * 10);
            ele.add(hund);
            ele.add(ten);
            ele.add(on);
            sortedMap.put(ele, cntMap.get(ele));
            DecimalFormat dbl = new DecimalFormat("#.##");
            String dblStr = dbl.format(((double)cntMap.get(ele) / ((double)tryCnt / 100)));
            System.out.print(ele + " : " + dblStr + "%");
            BigDecimal earnMoney = moneyList.get(0);
            BigDecimal earnMonBig = new BigDecimal(moneyTable(ele));
            BigDecimal cntMapBig = new BigDecimal((int)cntMap.get(ele));
            BigDecimal nowGetMoney = earnMonBig.multiply(cntMapBig);
            earnMoney = earnMoney.add(nowGetMoney);
            String moneyPrintIn = moneyFormat.format(nowGetMoney);
            System.out.println(",\t\b\b 얻은 돈 : " + moneyPrintIn + "    ,\t\b\b 횟수 : " + cntMap.get(ele));
            moneyList.set(0, earnMoney);
        });

        moneyPrint = moneyFormat.format(moneyList.get(0));
        String allMoney = moneyFormat.format(money.add(moneyList.get(0)));

        System.out.println("\n ------- 딴 돈 : " + moneyPrint + "  ------ \n        총 소득 : " + allMoney + "\n\n 총 횟수");
        System.out.println(sortedMap);
        
            
    }

    public static int moneyTable(List<Integer> lst) {
        int winMoney = 0;
        int moneyCode = 0;
        moneyCode += lst.get(0) * 100;
        moneyCode += lst.get(1) * 10;
        moneyCode += lst.get(2);
        if (moneyCode == 900) {
            winMoney = 20000;
        } else if (moneyCode == 621) {
            winMoney = 2000;
        } else if (moneyCode == 531) {
            winMoney = 400;
        } else if (moneyCode == 540) {
            winMoney = 400;
        } else if (moneyCode == 333) {
            winMoney = 200;
        } else if (moneyCode == 720) {
            winMoney = 2000;
        } else if (moneyCode == 810) {
            winMoney = 6000;
        } else if (moneyCode == 441) {
            winMoney = 200;
        } else if (moneyCode == 630) {
            winMoney = 2000;
        } else if (moneyCode == 711) {
            winMoney = 4000;
        } else if (moneyCode == 522) {
            winMoney = 200;
        } else if (moneyCode == 432) {
            winMoney = -2000;
        } else {
            winMoney = 0;
            System.out.println(lst + " : Out of moneyTable!");
        }
        return winMoney;
    }
}
