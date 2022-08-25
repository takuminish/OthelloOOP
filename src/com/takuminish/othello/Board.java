package com.takuminish.othello;

import com.takuminish.othello.stone.Stone;
import com.takuminish.othello.stone.StoneFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * オセロ盤のクラス
 */
public class Board {
    /** オセロ盤面を表す */
    private Map<Integer, Stone> board;

    /** オセロ盤面の幅を表す */
    private final int boardWidth;

    private final int SENTINEL_WIDTH = 2;

    /**
     * コンストラクタ
     */
    public Board(final int boardWidth) throws IllegalArgumentException {
        if (boardWidth < 0) {
            throw new IllegalArgumentException("負数は不正です。");
        }

        if ((boardWidth % 2) != 0) {
            throw new IllegalArgumentException("オセロ盤の幅が奇数は無理です。");
        }

        // 設定する盤面の上下左右1辺を番兵として使用するため、幅を+2する
        this.boardWidth = boardWidth + this.SENTINEL_WIDTH;

        // オセロ盤面の初期化
        this.boardInit();
    }

    /**
     * オセロ盤面の初期状態をセットアップ
     */
    private void boardInit() {
        this.board = new HashMap<Integer, Stone>();
        // 盤面全体に石の未配置状態を設定
        for(int y = 0; y < this.boardWidth; y++) {
            for(int x = 0; x < this.boardWidth; x++) {
                final CellPosition InitialEmptyPosition = new CellPosition(new CellPositionValue(x), new CellPositionValue(y));
                this.board.put((InitialEmptyPosition.getCellPosition(this.boardWidth)), StoneFactory.createEmptyStone(InitialEmptyPosition));
            }
        }

        // 盤面の初期状態のセット(白石)
        final CellPosition InitialWhiteStone1Position = new CellPosition(new CellPositionValue(this.boardWidth/2-1), new CellPositionValue(this.boardWidth/2-1));
        this.board.put((InitialWhiteStone1Position.getCellPosition(this.boardWidth)), StoneFactory.createWhiteStone(InitialWhiteStone1Position));

        final CellPosition InitialWhiteStone2Position = new CellPosition(new CellPositionValue(this.boardWidth/2), new CellPositionValue(this.boardWidth/2));
        this.board.put((InitialWhiteStone2Position.getCellPosition(this.boardWidth)), StoneFactory.createWhiteStone(InitialWhiteStone2Position));

        // 盤面の初期状態のセット(黒石)
        final CellPosition InitialBlackStone1Position = new CellPosition(new CellPositionValue(this.boardWidth/2), new CellPositionValue(this.boardWidth/2-1));
        this.board.put((InitialBlackStone1Position.getCellPosition(this.boardWidth)), StoneFactory.createBlackStone(InitialBlackStone1Position));

        final CellPosition InitialBlackStone2Position = new CellPosition(new CellPositionValue(this.boardWidth/2-1), new CellPositionValue(this.boardWidth/2));
        this.board.put((InitialBlackStone2Position.getCellPosition(this.boardWidth)), StoneFactory.createBlackStone(InitialBlackStone2Position));

        //　盤面の上下左右1辺を番兵として使用する
        // 上辺
        for (int x = 0; x < this.boardWidth; x++) {
            final CellPosition SentinelPosition = new CellPosition(new CellPositionValue(x), new CellPositionValue(0));
            this.board.put((SentinelPosition.getCellPosition(this.boardWidth)), StoneFactory.createSentinelStone(SentinelPosition));
        }
        // 下辺
        for (int x = 0; x < this.boardWidth; x++) {
            final CellPosition SentinelPosition = new CellPosition(new CellPositionValue(x), new CellPositionValue(this.boardWidth-1));
            this.board.put((SentinelPosition.getCellPosition(this.boardWidth)), StoneFactory.createSentinelStone(SentinelPosition));
        }
        // 左辺
        for (int y = 0; y < this.boardWidth; y++) {
            final CellPosition SentinelPosition = new CellPosition(new CellPositionValue(0), new CellPositionValue(y));
            this.board.put((SentinelPosition.getCellPosition(this.boardWidth)), StoneFactory.createSentinelStone(SentinelPosition));
        }

        // 右辺
        for (int y = 0; y < this.boardWidth; y++) {
            final CellPosition SentinelPosition = new CellPosition(new CellPositionValue(this.boardWidth-1), new CellPositionValue(y));
            this.board.put((SentinelPosition.getCellPosition(this.boardWidth)), StoneFactory.createSentinelStone(SentinelPosition));
        }
    }

    /**
     * オセロ盤面のコンソール出力
     */
    public void outputCLIBoard() {
        for(int i = 0; i < this.boardWidth * this.boardWidth; i++) {
            // 1行出力した後改行
            if(i % this.boardWidth == 0) {
                System.out.println();
            }
            this.board.get(i).outputCLIStone();

            // 見栄えのために空白をあける
            System.out.print(" ");
        }
    }

    /**
     * 引数に受け取った石の種類を配置可能な、まだ石が置かれていない盤面の位置の一覧を返す。
     * 配置可能な位置が存在しない場合は、空のリストを返す。
     * @param stoneType 配置をしたい石の種類
     * @return stoneTypeが配置可能な盤面の位置の一覧
     */
    public List<CellPosition> getPlayPossibleCellPositionList(final StoneType stoneType) {
        List<CellPosition> playPossibleCellPositionList = new ArrayList<CellPosition>();

        for (int y = 1; y < this.boardWidth -1; y++) {
            for (int x = 1; x < this.boardWidth -1; x++) {
                final CellPosition playPossibleCellPosition = new CellPosition(new CellPositionValue(x), new CellPositionValue(y));
                // 左上
                List<CellPosition> possibleReverseListForUpperLeft = this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth * -1 -1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForUpperLeft.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }
                // 上
                List<CellPosition> possibleReverseListForUpperMiddle =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth * -1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForUpperMiddle.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                // 右上
                List<CellPosition> possibleReverseListForUpperRight =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth * -1 +1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForUpperRight.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                //　左
                List<CellPosition> possibleReverseListForMiddleLeft =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), -1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForMiddleLeft.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                // 右
                List<CellPosition> possibleReverseListForMiddleRight =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), +1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForMiddleRight.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                // 左下
                List<CellPosition> possibleReverseListForLowerLeft =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth -1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForLowerLeft.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                // 下
                List<CellPosition> possibleReverseListForLowerMiddle =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForLowerMiddle.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }

                //　右下
                List<CellPosition> possibleReverseListForLowerRight =this.get1wayReversePossibleCellPositionList(StoneFactory.createBlackStone(playPossibleCellPosition), this.boardWidth + 1);
                // ひっくり返せる石が一つでもあれば、石を置くことができる
                if(possibleReverseListForLowerRight.size() > 0) {
                    playPossibleCellPositionList.add(playPossibleCellPosition);
                    continue;
                }
            }
        }


        return playPossibleCellPositionList;
    }

    /**
     * 1方向に対して、相手の石をひっくり返せるかチェックする
     * @param プレイヤーが置く予定の石
     * @param ひっくり返せるかチェックする方向
     * @return ひっくり返せる石のリスト
     * 　　　　　ひっくり返せる石が存在しない場合は空
     */
    private List<CellPosition> get1wayReversePossibleCellPositionList(final Stone playHandStone, final int reverseWay) {
        List<CellPosition> reversePossibleCellPositionList = new ArrayList<CellPosition>();

        int checkReversePosition = playHandStone.stonePosition().getCellPosition(this.boardWidth) + reverseWay;

        // 盤面外の番兵に当たるまでチェックを繰り返す
        while(this.board.get(checkReversePosition).stoneType() != StoneType.SENTINEL) {
            // 1方向で石を確認し、空のセルがある場合はひっくり返せない
            if(this.board.get(checkReversePosition).stoneType() == StoneType.Empty) {
                return new ArrayList<CellPosition>();
            }

            // 1方向で石を確認し、自分とおなじ石または空のセルがあった場合は、チェック終了
            if(this.board.get(checkReversePosition).stoneType() == playHandStone.stoneType()) {
                break;
            }

            // 相手の石はひっくり返せるためリストに格納
            reversePossibleCellPositionList.add(this.board.get(checkReversePosition).stonePosition());

            // 1セル分、チェックするセルを動かす
            checkReversePosition += reverseWay;
        }

        return reversePossibleCellPositionList;
    }

    /**
     * 引数に指定した石の種類の個数を返す
     * @param stoneType 石の種類
     * @return　盤面の石の種類の個数
     */
    public long getStoneTypeNumOnBoard(StoneType stoneType) {
        return this.board.entrySet().stream().filter(cell -> cell.getValue().stoneType().equals(stoneType)).count();
    }
}
