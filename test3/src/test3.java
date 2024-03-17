import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.StringJoiner;

/**
 * 課題3
 *
 */
public class test3 {
    public static void main(String[] args) {

        // 標準入力取得用
        Scanner scanner = new Scanner(System.in);

        // 店舗数
        int shopNum = scanner.nextInt();
        // 自分以外のユーザー数
        int userNum = scanner.nextInt();
        // 基準数
        int criterionNum = scanner.nextInt();

        // 評価配列の作成
        int[][] userRatings = new int[userNum + 1][shopNum];
        // ユーザー+1(自分)の総数
        for (int i = 0; i < userNum + 1; i++) {
            // 店舗数
            for (int j = 0; j < shopNum; j++) {
                userRatings[i][j] = scanner.nextInt();
            }
        }

        // 候補店舗取得処理
        Set<Integer> shops = findShop(shopNum, userNum, criterionNum, userRatings);

        // 結果出力
        if (shops.isEmpty()) {
            // 店舗が存在しない場合
            System.out.println("no");

        } else {
            // 店舗が存在する場合
            StringJoiner sj = new StringJoiner(" ");
            for (Integer shop : shops) {
                sj.add(shop.toString());
            }
            System.out.println(sj.toString());

        }
    }

    /**
     * 候補店舗取得処理
     * @param shopNum 店舗数
     * @param userNum 自分以外のユーザー数
     * @param criterionNum 基準数
     * @param userRatings 評価配列
     * @return 自分が行ったことがなく、かつ対象が星3の評価をしている店舗番号
     */
    public static Set<Integer> findShop(int shopNum, int userNum, int criterionNum, int[][] userRatings) {

        // 自分が行ったことがなく、かつ対象が星3の評価をしている店舗番号
        Set<Integer> shops = new HashSet<>();

        /*** 好みが似ているユーザーを判断 ***/
        // 好みが似ているユーザー
        List<Integer> similarUser = new ArrayList<>();

        // ユーザー数分
        for (int i = 1; i <= userNum; i++) {

            // 自分と対象ユーザーの評価が星3の店舗数
            int count = 0;

            // 店舗数分
            for (int j = 0; j < shopNum; j++) {
                // 自分と対象ユーザーの評価が星3であればカウント
                if (userRatings[0][j] == 3 && userRatings[i][j] == 3) {
                    count++;
                }
                // 基準数に達した場合、好みが似ているユーザーのリストに追加
                if (count >= criterionNum) {
                    similarUser.add(i);
                    break;
                }
            }
        }

        /*** 自分が行ったことがなく、かつ対象が星3の評価をしている店舗を判断 ***/
        // 好みが似ているユーザーのリスト分
        for (Integer index : similarUser) {
            // 店舗数分
            for (int i = 0; i < shopNum; i++) {
                if (userRatings[0][i] == 0 && userRatings[index][i] == 3) {
                    shops.add(i + 1);
                }
            }
        }

        return shops;
    }

}
