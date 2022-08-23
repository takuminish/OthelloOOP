package com.takuminish.othello;


import com.takuminish.othello.stone.Stone;
import com.takuminish.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OthelloGame {

    /** プレイヤー */
    private List<Player> playerList;

    /** 盤面 */
    private Board board;

    /**
     * コンストラクタ
     * @param player1　プレイヤー1
     * @param player2 プレイヤー2
     * @param board 盤面
     * @throws IllegalArgumentException プレイヤー1とプレイヤー2の石が同じ
     */
    OthelloGame(final Player player1, final Player player2, final Board board) {

        // プレイヤーの石が同じ場合、エラー
        if (player1.myStoneType() == player2.myStoneType()) {
            throw new IllegalArgumentException("プレイヤー1とプレイヤー2の石が同じです。");
        }

        this.playerList.add(player1);
        this.playerList.add(player2);
    }

    public void game() {
        int turnCount = 0;
        while(true) {
            // ターンのプレイヤーを決定
            final Player turnplayer = this.playerList.get(turnCount % 2);

            // 盤面に配置する石の位置の候補を取得
            final List<CellPosition> playPossibleCellPositionList = this.board.getPlayPossibleCellPositionList(turnplayer.myStoneType());

            // パス(配置可能な石が存在しない場合、相手のターンに移る)
            if (playPossibleCellPositionList.size() == 0) {
                System.out.println("「パス」相手のターンに移ります。");
                turnCount++;
                continue;
            }

            // 石を配置する位置を決定
            final Stone playHandStone = turnplayer.playHand(playPossibleCellPositionList);

            // 石を配置し、相手の石をひっくり返す

            // プレイヤーの石の数を更新する

            // 盤面の状態の表示

            // ゲーム終了判定
            // ① 相手(ターンじゃないプレイヤー)の石が0個になる
            if(this.board.getStoneTypeNumOnBoard(this.playerList.get((turnCount+1) % 2).myStoneType()) == 0) {
                System.out.println("プレイヤーXの勝ちです。");
                break;
            }

            // ② 盤面のすべてのセルが石で埋まる
            if(this.board.getStoneTypeNumOnBoard(StoneType.Empty) == 0) {
                // 一番石の数が多いプレイヤーが勝利
                break;
            }

            turnCount++;
        }

    }

    /**
     * 石が一番多いプレイヤーを返す
     * @return 石が一番多いプレイヤー
     */
    private Player mostStonePlayerInPlayerList() {
        Map<Player, Integer> stoneNumOfPlayer = this.playerList.stream().collect(Collectors.toMap(player -> player,player -> player.myStoneNum()));
        return stoneNumOfPlayer.entrySet().stream().max((p1 , p2) -> p1.getValue().compareTo(p2.getValue())).get().getKey();
    }

}
