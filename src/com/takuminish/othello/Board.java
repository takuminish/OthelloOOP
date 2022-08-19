package com.takuminish.othello;

import com.takuminish.othello.stone.Stone;
import com.takuminish.othello.stone.StoneFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * オセロ盤のクラス
 */
public class Board {
    /** オセロ盤面を表す */
    private Map<Integer, Stone> board;

    /** オセロ盤面の幅を表す */
    private final int boardWidth;

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

        this.boardWidth = boardWidth;

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
}
