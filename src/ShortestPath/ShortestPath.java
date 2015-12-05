package ShortestPath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * Created by xl on 15/12/2.
 * 注释：将问题转化为求序列的全组合和全排列，分别求得最小值后再求总的最小值
 */
public class ShortestPath {
    int pointCount;
    int lineOne, lineTwo;
    String fileName;
    File dataFile;
    int[][] pathData;
    String[] num;
    String numStr;
    HashSet<String> allCombination;
    HashSet<String> setOne;

    public ShortestPath(){
        pointCount = 15;
        fileName = "/Users/xl/IdeaProjects/Acmcoder/src/ShortestPath/pathData";
        dataFile = new File(fileName);
        pathData = new int[pointCount][pointCount];
        lineOne = lineTwo = 0;
        num = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        allCombination = new HashSet<String>();
        setOne = new HashSet<String>();

        StringBuilder tempStr = new StringBuilder();
        for (String temp : num){
            tempStr.append(temp+" ");
        }
        numStr = tempStr.toString().trim();

        combination(num);

        for (int i = 0; i < pointCount; i++){
            for (int j = 0; j < pointCount; j++){
                pathData[i][j] = 0;
            }
        }
    }

    /**
     * @desc 将文件中的数据读入到数组中
     */
    public void readDataFromFile(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.dataFile));
            String oneLine = null;
            int line = 0;
            while ((oneLine = reader.readLine()) != null){
                String[] tempLine = oneLine.split(" ");
                for (int i = 0; i < tempLine.length; i++){
                    pathData[line][i] = Integer.parseInt(tempLine[i]);
                }
                line++;
            }

            reader.close();

            for (int i = 0; i < pointCount; i++){
                for (int j = 0; j < pointCount; j++){
                    if (i == j)
                        continue;
                    if (pathData[i][j] != 0)
                        continue;
                    pathData[i][j] = pathData[j][i];
                }
            }
        } catch (Exception e){
            System.out.println("读入文件错误，请检查文件路径是否正确。");
            System.out.println("正确的文件路径栗子：D:/代码/pathData.txt");
            e.printStackTrace();
        }
    }

    /**
     * @param str
     * @param chA
     * @return
     */
    public int countChar(String str, char chA){
        int count = 0;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == chA)
                count++;
        }
        return count;
    }

    /**
     * 计算第一条路线有N个节点时的最小路径
     * @param n
     */
    public void shortestPath(int n){
        int minOne, minTwo;
        HashMap<String, String> oneToTwo = new HashMap<String, String>();
        HashMap<Integer, Integer> pathLength = new HashMap<Integer, Integer>();
        Set<String> lineOneList = new HashSet<String>();
        Set<String> lineTwoList = new HashSet<String>();
        int sum = 0;

        //从全组合中选出N个点的组合，确定剩余的pointCount-N个点的组合，并求出他们的全排列。
        for (String oneCombination : allCombination){
            if (countChar(oneCombination.trim(), ' ') == n - 1){
                setOne.add(oneCombination);
            }
        }

        for (String strOne : setOne){
            String strTwo = complment(strOne, numStr);
            //放入 路线1 => 路线2 的map中。
            oneToTwo.put(strOne, strTwo);
        }

        //查找路线1 ＝> 路线2 中的序列
        for (Map.Entry<String, String> entry : oneToTwo.entrySet()){
            //求出第一条路线和第二条路线的全排列（第一条中未加起点0），分别存放在两个set中
            if (Integer.parseInt(entry.getValue().split(" ")[0]) > 4)
                continue;
            permutation(entry.getKey().split(" "), 0, entry.getKey().split(" ").length - 1, lineOneList);
            permutation(entry.getValue().split(" "), 0, entry.getValue().split(" ").length -1, lineTwoList);

            if (lineOneList.isEmpty() || lineTwoList.isEmpty())
                continue;
            //分解序列，查询pathData中的值，计算出各自路线中的最小值
            minOne = decomposition(lineOneList, true);
            minTwo = decomposition(lineTwoList, false);
            if (pathLength.containsKey(minOne)){
                if (minTwo < pathLength.get(minOne)){
                    pathLength.remove(minOne);
                    pathLength.put(minOne, minTwo);
                }
            } else {
                pathLength.put(minOne, minTwo);
            }

            lineOneList.clear();
            lineTwoList.clear();
        }

        for (Map.Entry<Integer, Integer> entry : pathLength.entrySet()){
            if (sum < (entry.getValue() + entry.getKey())){
                lineOne = entry.getKey();
                lineTwo = entry.getValue();
                sum = entry.getKey() + entry.getValue();
            }
        }

        oneToTwo.clear();
        pathLength.clear();
    }

    /**
     * 分解序列，并计算距离值
     * @param lineSet
     * @param isLineOne
     * @return
     */
    public int decomposition(Set<String> lineSet, boolean isLineOne){
        ArrayList<Integer> pathLength = new ArrayList<Integer>();

        for (String strSeq : lineSet){
            String[] strLine;
            int lengthSum = 0;
            if (isLineOne)
                strSeq = "0 " + strSeq;
            strLine = strSeq.split(" ");
            for (int i = 0; i < strLine.length - 1; i++){
                lengthSum += pathData[Integer.parseInt(strLine[i])][Integer.parseInt(strLine[i+1])];
            }
            pathLength.add(lengthSum);
        }

        return Collections.min(pathLength);
    }

    /**
     * 求字符串A在字符串B中的补集
     * @param strA
     * @param strB
     * @return
     */
    public String complment(String strA, String strB){
        StringBuilder strComp = new StringBuilder();
        String[] arrStrA = strA.split(" ");
        String[] arrStrB = strB.split(" ");

        for (int i = 0; i < arrStrB.length; i++){
            if (Arrays.asList(arrStrA).contains(arrStrB[i])){
                continue;
            } else {
                strComp.append(arrStrB[i]+" ");
            }
        }
        return strComp.toString().trim();
    }

    /* 求出全排列，递归实现
     * 从集合中选出每一个元素，作为排列的第一个元素，然后对剩余的元素进行全排列，如此递归处理，
     * 从而得到所有元素的全排列。以对字符串abc进行全排列为例，我们可以这么做：以abc为例：
     * 固定a，求后面bc的排列：abc，acb，求好后，a和b交换，得到bac
     * 固定b，求后面ac的排列：bac，bca，求好后，c放到第一位置，得到cba
     * 固定c，求后面ba的排列：cba，cab。
     */
/*    public void permutation(String str, String result, int length, Set<String> set){
        if (result.length() == length)
            set.add(result);
        else {
            for (int i = 0; i < str.length(); i++){
                if (result.indexOf(str.charAt(i)) < 0){
                    permutation(str, result + str.charAt(i), length, set);
                }
            }
        }
    }*/

    /**
     * 求全排列
     * @param str
     * @param first
     * @param end
     */
    public void permutation(String[] str , int first, int end, Set<String> lineSet) {
        if(first == end) {    //记录一个排列方式
            StringBuilder builder = new StringBuilder();
            for(int j=0; j<= end ;j++) {
/*                if (j < end && Math.abs(Integer.parseInt(str[j]) - Integer.parseInt(str[j + 1])) > 3)
                    return;*/
                builder.append(str[j]+" ");
            }
            lineSet.add(builder.toString().trim());
        }

        for(int i = first; i <= end ; i++) {
            if (i < end && Math.abs(Integer.parseInt(str[i]) - Integer.parseInt(str[i + 1])) > 4)
                return;
            swap(str, i, first);
            permutation(str, first+1, end, lineSet);  //固定好当前一位，继续排列后面的
            swap(str, i, first);
        }
    }

    /**
     * 交换数据
     * @param str
     * @param i
     * @param first
     */
    private void swap(String[] str, int i, int first) {
        String tmp;
        tmp = str[first];
        str[first] = str[i];
        str[i] = tmp;
    }

    /**
     * 求出全组合
     * @param strArr
     */
    public void combination(String[] strArr){
        int n = strArr.length;
        int nbit = 1 << n;
        for(int i = 1 ;i < nbit ; i++) {
            StringBuilder builder = new StringBuilder();
            for(int j = 0; j < n ; j++) {
                int tmp = 1 << j ;
                if((tmp & i) != 0) {
                    builder.append(strArr[j] + " ");
                }
            }
            allCombination.add(builder.toString().trim());
        }
    }


    public int factorial(int n){
        if (n == 1){
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static void main(String args[]){
        ShortestPath eg = new ShortestPath();
        eg.readDataFromFile();

//        eg.printData();
//        System.out.println(eg.allCombination.size());

/*        eg.shortestPath(1);
        System.out.println(eg.lineOne + " " + eg.lineTwo);*/
        System.out.println("\n第一条路线的点数\t第一条最短总长\t第二条最短总长");
        for (int i = 1; i < (eg.pointCount - 1); i++){
            eg.lineOne = 0;
            eg.lineTwo = 0;
            eg.shortestPath(i);
            System.out.println(i + "\t\t\t\t" + eg.lineOne + "\t\t\t\t" + eg.lineTwo);
        }
    }

    public void testFunc(ShortestPath testObj){
        HashSet<String> strSet = new HashSet<String>();
        String numStr = "1 2 3 4 5 6 7 8 9 10";
        testObj.permutation(numStr.split(" "), 0, numStr.split(" ").length - 1, strSet);
        System.out.println(strSet.size());

        System.out.println(testObj.allCombination.size());
        int times = 0;
        for (String strCom : testObj.allCombination){
            if (times > 20)
                break;
            System.out.println(strCom);
            times++;
        }
    }

    public void printData(){
        for (int i = 0; i < pointCount; i++){
            for (int j = 0; j < pointCount; j++) {
                System.out.print(pathData[i][j] + " ");
                if (j % (pointCount - 1) == 0 && j != 0)
                    System.out.println("\n");
            }
        }
    }
}
