package com.takuminish.othello;

/**
 * オセロの石
 */
public class Stone {
    /** 石の位置 */
    private final CellPosition position;

    /** 石の種類 */
    private final StoneType type;

    /**
     * コンストラクタ
     * @param position 石の位置
     * @param type 石の種類
     */
    private Stone(final CellPosition position, final  StoneType type) {
        this.position = position;
        this.type = type;
    }

    /**
     * 白石のインスタンスを生成
     * @param position 石の位置
     * @return 白石のStoneインスタンス
     */
    static public Stone createWhiteStone(final CellPosition position) {
        return new Stone(position, StoneType.White);
    }

    /**
     * 黒石のインスタンスを生成
     * @param position 石の位置
     * @return 黒石のStoneインスタンス
     */
    static public Stone createBlackStone(final CellPosition position) {
        return new Stone(position, StoneType.Black);
    }

    /**
     * プレイヤーの操作よる石の生成
     * @param position 石の位置
     * @param type 石の種類
     * @return 石のインスタンス
     */
    static public Stone createStone4PlayHand(final CellPosition position, final  StoneType type) {
        return new Stone(position, type);
    }

}
